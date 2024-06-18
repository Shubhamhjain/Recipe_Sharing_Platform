/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.RecipeDAO;
import com.example.pojo.Recipe;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author shubhamjain
 */
@Controller
public class UpdateRecipe {

    @PostMapping("/updateRecipeAction.htm")
    public String updateRecipeAction(@RequestParam("recipeId") int recipeId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("ingredients") String ingredients,
            @RequestParam("cookingInstructions") String cookingInstructions,
            HttpServletRequest request,
            RecipeDAO recipedao
    ) {
        try {
            // Call your DAO method to update the recipe
            int result = recipedao.updateRecipe(title, description, ingredients, cookingInstructions, recipeId);
            System.out.println(result);

            if (result == 1) {
                // Update successful, redirect to the page showing all recipes
                return "chefPage";
            } else {
                // Update failed, handle error or show a message
                System.out.println("Update failed");

                return "error"; // Redirect to error page
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid recipe ID: " + recipeId);

            return "error"; 
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());

            return "error"; 
        }
    }

    @PostMapping("/updateRecipeForm.htm")
    public String UpdateRecipeForm(@RequestParam("recipeId") int recipeId, Model model, RecipeDAO recipedao) {
        Recipe recipe = recipedao.getRecipeById(recipeId);
        model.addAttribute("recipe", recipe);
        //redirect to update recipe page
        return "updateRecipe";
    }

}
