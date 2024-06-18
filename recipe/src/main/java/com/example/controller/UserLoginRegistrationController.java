/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.UserDAO;
import com.example.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author shubhamjain
 */
@Controller
public class UserLoginRegistrationController {

	@GetMapping("/toRegisterPage.htm")
	public String UserRegister() {

		// redirect to register page
		return "registerUser";
	}

	@GetMapping("/toLoginPage.htm")
	public String UserLogin() {

		// redirect to login page
		return "loginUser";
	}

	@PostMapping("/registerNewUser.htm")
	public String RegisterNewUser(@ModelAttribute User user, UserDAO userdao) {

		if (user.getFirstName() == null || user.getLastName() == null || user.getEmail() == null
				|| user.getUsername() == null || user.getPassword() == null || user.getRole() == null) {
			// Redirect to error page
			return "error";
		}
		// Hash the password
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

		// Set the hashed password to the user object
		user.setPassword(hashedPassword);

		// checking if the user is already registered
		if (userdao.isUserRegistered(user.getUsername())) {
			return "alreadyRegistered";
		}
		// registering a new user
		userdao.saveUser(user);

		return "loginUser";
	}

	@PostMapping("/loginByRole.htm")
	public String loginByRole(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response,
			UserDAO userdao) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		User userLogin = userdao.loginByUsernamePassword(userName, password);

		// first check if userLogin is null. if null then redirect to login page
		if (userLogin != null) {
			
			if (BCrypt.checkpw(password, userLogin.getPassword())) {
				System.out.println(password);
				System.out.println(userLogin.getPassword());

				// if not null then get the role from dao
				String userRole = userLogin.getRole();

				// check if the selected role is chef and is equal to the fetched role from db
				// check if the selected role is normal user and is equal to the fetched role from db
				if (("chef".equals(userRole) && "chef".equals(user.getRole()))
						|| ("user".equals(userRole) && "user".equals(user.getRole()))) {

					// saving it in session obj
					session.setAttribute("user", userLogin);

					// return respective jsp page
					return userRole + "Page";
				}
			}
		}
		return "loginUser";
	}

}
