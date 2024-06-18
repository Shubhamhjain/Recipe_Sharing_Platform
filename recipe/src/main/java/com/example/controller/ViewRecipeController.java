/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.RecipeDAO;
import com.example.pojo.Recipe;
import com.example.pojo.User;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author shubhamjain
 */
@Controller
public class ViewRecipeController {

    @GetMapping("/allRecipes.htm")
    public ModelAndView allRecipes(@ModelAttribute Recipe recipe, RecipeDAO recipedao, HttpServletRequest request) {
        // get the current session
        HttpSession session = request.getSession();

        // Check if the user is logged in
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            // User is not logged in, redirect to login page
            return new ModelAndView("loginUser");
        }

        // calling the getAllRecipes method from the respective dao
        List<Recipe> recipe_list = recipedao.getAllRecipesWithUser();
 
        // saving it in session obj
        return new ModelAndView("viewRecipes","recipes",recipe_list);
    }

    @GetMapping("/viewChefRecipes.htm")
    public ModelAndView viewUserRecipes(@ModelAttribute Recipe recipe, RecipeDAO recipedao, HttpServletRequest request) {
        
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if (user == null) {
            // User is not logged in, redirect to login page
            return new ModelAndView("loginUser");
        }
        int userId = user.getUserId();
        System.out.println(userId);
        
        // finding recipes by user id
        List<Recipe> chefRecipes = recipedao.findRecipesByUserId(userId);

        return new ModelAndView("chefPage","chefRecipes",chefRecipes);
    }
    
    @PostMapping("/searchRecipes.htm")
    public ModelAndView searchRecipes(@ModelAttribute Recipe recipe, RecipeDAO recipedao, HttpServletRequest request, HttpServletResponse response) {
        // get the current session
        HttpSession session = request.getSession();

        // Check if the user is logged in
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {

            // User is not logged in, redirect to login page
            return new ModelAndView("loginUser");
        }

        // search based on keyword
        String keyword = request.getParameter("keyword");
        String parameter = request.getParameter("parameter");
        List<Recipe> recipe_list;
        switch (parameter) {
            case "title":
                recipe_list = recipedao.searchRecipes(parameter, keyword);
                break;
            case "ingredients":
                recipe_list = recipedao.searchRecipes(parameter, keyword);
                break;
            case "description":
                recipe_list = recipedao.searchRecipes(parameter, keyword);
                break;
            default:
                return new ModelAndView("viewRecipes");
        	}
        
        return new ModelAndView("viewRecipes","recipes",recipe_list);

    }
}
