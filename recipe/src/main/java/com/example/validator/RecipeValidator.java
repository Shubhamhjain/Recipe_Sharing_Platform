/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.validator;

import com.example.pojo.Recipe;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author shubhamjain
 */
@Component
public class RecipeValidator implements Validator {
     @Override
    public boolean supports(Class<?> type) {
        return Recipe.class.isAssignableFrom(type);
    }
    
    @Override
    public void validate(Object command, Errors errors) {
        Recipe recipe = (Recipe) command;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "empty-title", "Title cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "empty-description", "Description cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ingredients", "empty-ingredients", "Ingredients cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cookingInstructions", "empty-cookingInstructions", "cookingInstructions cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creationDate", "empty-creationDate", "creationDate cannot be empty");

        if (recipe.getPhoto().getSize() == 0)
            errors.rejectValue("photo", "empty-file", "File is empty");
        
        if (!recipe.getPhoto().getOriginalFilename().endsWith(".jpeg"))
            errors.rejectValue("photo", "file-type", "File MUST BE .jpeg");
    }
}
