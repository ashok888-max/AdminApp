package com.app.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.demo.entity.Admin;
import com.app.demo.entity.Doctor;
import com.app.demo.entity.Hospital;
import com.app.demo.entity.Patient;
import com.app.demo.entity.Users;
import com.app.demo.exceptions.InvalidInputException;
import com.app.demo.repository.AdminRepository;
import com.app.demo.repository.HospitalRepository;
import com.app.demo.service.AdminService;
import com.app.demo.service.DoctorService;
import com.app.demo.service.PatientService;
import com.app.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private UserService userService;

	@Autowired
	private HospitalRepository hospitalRepository;

	@GetMapping({ "/", "/login" })
	public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("error", "Invalid username or password");
		}
		return "login";
	}

	@PostMapping("/perform_login")
	public String loginUser(@RequestParam String identifier, @RequestParam String password, HttpSession session,
			RedirectAttributes redirectAttributes) {

		Admin admin = adminService.findUserByAdminId(identifier);
		if (admin == null) {

			System.out.println("Admin not found by ID, checking email...");
			admin = adminService.findAdminByEmail(identifier);
		}

		if (admin != null) {

			System.out.println("User found: " + admin.getAdminId());

			if (adminService.validatePassword(password, admin.getPassword())) {

				session.setAttribute("adminId", admin.getAdminId());

				System.out.println("Login successful for adminId: " + admin.getAdminId());
				return "redirect:/home";
			} else {

				System.out.println("Invalid password for adminId: " + admin.getAdminId());
			}
		} else {

			System.out.println("No user found with identifier: " + identifier);
		}

		redirectAttributes.addFlashAttribute("error", "Invalid adminId/email or password");

		return "redirect:/login";
	}

	@GetMapping("/home")
	public String showHomePage() {
		return "home";
	}

	@GetMapping("/hospital")
	public String showHospitalPage() {
		return "hospital";
	}

	@GetMapping("/users")
	public String showUsersPage() {
		return "users";
	}

	@GetMapping("/addHospital")
	public String showAddHospitalPage() {
		return "addHospital";
	}

	@GetMapping("/patient")
	public String showPatientsPage() {
		return "patients";
	}

	@GetMapping("/addDoctor")
	public String showAddDoctorPage() {
		return "addDoctor";
	}

	@GetMapping("/addUser")
	public String showAddUserPage() {
		return "addUser";
	}

	@PostMapping("/saveAdmin")
	public ResponseEntity<String> saveAdmin(@RequestBody Admin admin) {

		String rawPassword = admin.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);

		admin.setPassword(encodedPassword);

		adminRepository.save(admin);

		return new ResponseEntity<>("Admin saved successfully", HttpStatus.OK);
	}

	@GetMapping("/user/details/{id}")
	public String getUserDetailsPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		Users user = userService.getUserDetailsById(id);

		if (user != null) {
			model.addAttribute("user", user);
			return "getMoreDetails";
		} else {
			// Add error message as a flash attribute for redirection
			redirectAttributes.addFlashAttribute("error", "User not found with ID: " + id);
			return "redirect:/users";
		}
	}

	@GetMapping("/userspage")
	public String getUser(@RequestParam Long patientId, Model model) {
		// Logic to retrieve user data based on the patient ID
		Patient patient = patientService.getPatientDetailsById(patientId); // Assuming you have a service to fetch
																			// patient data

		if (patient != null) {
			// Add patient data to the model
			model.addAttribute("patient", patient);
			return "users"; // Return the view name (e.g., userDetails.html)
		} else {
			// Handle case where patient is not found (optional)
			model.addAttribute("errorMessage", "Patient not found");
			return "error"; // Return an error view (e.g., error.html)
		}
	}

	@PostMapping("/addDoctor")
	public String addDoctor(@RequestParam("name") String name, @RequestParam("specialization") String specialization,
			@RequestParam("yearsOfExperience") int yearsOfExperience, RedirectAttributes redirectAttributes) {
		try {
			Doctor doctor = new Doctor(name, specialization, yearsOfExperience);
			doctorService.addDoctor(doctor);

			// Set success message
			redirectAttributes.addFlashAttribute("successMessage", "Doctor added successfully!");
		} catch (Exception e) {
			// Set error message
			redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while adding the doctor.");
		}
		return "redirect:/addDoctor";
	}

	@PostMapping("/createHospital")
	public ResponseEntity<Hospital> addHospital(@Valid @RequestBody Hospital hospital, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder("Invalid credentials: ");
			bindingResult.getFieldErrors()
					.forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
			throw new InvalidInputException(errorMessage.toString());
		}

		Hospital addHospital = hospitalRepository.save(hospital);
		return new ResponseEntity<>(addHospital, HttpStatus.CREATED);
	}

}
