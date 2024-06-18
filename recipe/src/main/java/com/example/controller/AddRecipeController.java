/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.RecipeDAO;
import com.example.pojo.Recipe;
import com.example.pojo.User;
import com.example.validator.RecipeValidator;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author shubhamjain
 */
@Controller
public class AddRecipeController {

    @Autowired
    RecipeValidator validator;

    @GetMapping("/addRecipe.htm")
    public String showForm(ModelMap model, Recipe recipe, HttpServletRequest request) {
        
        // Check if the user is logged in
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            
            // User is not logged in, redirect to login page
            return "loginUser";
        }
        
     // Check if the user is a chef
        if (!"chef".equals(user.getRole())) {
            // User is not a chef, redirect to error pages
            return "error";
        }
        
        model.addAttribute("recipe", recipe);
        return "recipe-form";
    }                                                                   

    @PostMapping("/addRecipe.htm")
    public String showSuccess(@ModelAttribute("recipe") Recipe recipe, HttpServletRequest request, BindingResult result, SessionStatus status, RecipeDAO recipedao) {
        
        HttpSession session = request.getSession();
        
        // Fetch the current user from the session
        User user = (User) session.getAttribute("user");

        // Set the user in the recipe object
        recipe.setUser(user);
        
        // Validate the results
        validator.validate(recipe, result);

        // If the form has errors, it redirects back to the form page
        if (result.hasErrors()) {
            return "recipe-form";
        }

        //Saving images in a directory
        //Image path stored in db
        try {
            File file = new File("/Users/shubhamjain/Desktop/Recipe_Photos/", recipe.getTitle() + ".jpeg");
            recipe.getPhoto().transferTo(file);
            recipe.setPhotoPath(recipe.getTitle() + ".jpeg");
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } catch (IllegalStateException ex) {
            System.out.println("IllegalStateException: " + ex.getMessage());
        }
        
       
        status.setComplete();

        recipedao.saveRecipe(recipe);

        return "chefPage";
    }
}
