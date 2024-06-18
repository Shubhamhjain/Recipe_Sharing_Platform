package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.RecipeDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author shubhamjain
 */
@Controller
public class DeleteRecipe {

	@PostMapping("/deleteRecipe.htm")
	public String deleteRecipe(@RequestParam("recipeId") int recipeId, Model model, HttpServletRequest request,
			RecipeDAO recipedao) {

		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {

			// User is not logged in, redirect to login page
			return "loginUser";
		}

		try {
			int result = recipedao.deleteRecipeById(recipeId);
			model.addAttribute("deleteSuccess", false);

			if (result == 1) {

				model.addAttribute("deleteSuccess", true);

				// Delete successful, redirect to the page showing all recipes
				return "chefPage";
			} else {

				System.out.println("Delete failed");

				return "error";
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid recipe ID: " + recipeId);

			return "error";
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());

			return "error"; 
		}

	}

}
