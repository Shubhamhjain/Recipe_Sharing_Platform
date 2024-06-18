/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.pojo.Recipe;
import java.util.List;
import org.springframework.stereotype.Component;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

/**
 *
 * @author shubhamjain
 */
@Component
public class RecipeDAO extends DAO {

    public void saveRecipe(Recipe recipe) {
        try {
            beginTransaction();
            getSession().persist(recipe);
            commitTransaction();
            closeSession();
        } catch (HibernateException e) {
            rollbackTransaction();
        }
    }
    
    public List<Recipe> getAllRecipesWithUser() {
        Query<Recipe> query = getSession().createQuery("SELECT r FROM Recipe r JOIN FETCH r.user", Recipe.class);
        List<Recipe> recipe_list = query.list();
        return recipe_list;
    }

    public List<Recipe> findRecipesByUserId(int userId) {
        beginTransaction();
        Query<Recipe> query = getSession().createQuery("from Recipe where user.userId = :userId", Recipe.class);
        query.setParameter("userId", userId);
        List<Recipe> recipes = query.list();
        commitTransaction();
        closeSession();
        return recipes;
    }
    
    public List<Recipe> searchRecipes(String searchParameter, String searchText) {
        beginTransaction();
        String queryString = "from Recipe where ";
        switch (searchParameter) {
            case "title":
                queryString += "title LIKE :searchText";
                break;
            case "ingredients":
                queryString += "ingredients LIKE :searchText";
                break;
            case "description":
                queryString += "description LIKE :searchText";
                break;
            default:
                break;
        }

        Query<Recipe> query = getSession().createQuery(queryString, Recipe.class);
        query.setParameter("searchText", "%" + searchText + "%"); 
        List<Recipe> recipes = query.list();
        commitTransaction();
        closeSession();
        return recipes;
    }
    
    public int updateRecipe(String title, String description, String ingredients, String cookingInstructions, int recipeId){
        beginTransaction();
        Query query = getSession().createQuery("update Recipe set title = :title, description = :description, ingredients = :ingredients, cookingInstructions = :cookingInstructions WHERE recipeId = :recipeId");
        query.setParameter("title", title);
        query.setParameter("description", description);
        query.setParameter("ingredients", ingredients);
        query.setParameter("cookingInstructions", cookingInstructions);
        query.setParameter("recipeId", recipeId);

        int result = query.executeUpdate();
        commitTransaction();
        closeSession(); 
        return result;
    }
    
    public Recipe getRecipeById(int recipeId) {
        try {
            beginTransaction();
            Query query = getSession().createQuery("from Recipe where recipeId = :recipeId", Recipe.class);
            query.setParameter("recipeId", recipeId);
            Recipe recipe = (Recipe) query.uniqueResult();
            commitTransaction();
            return recipe;
        } catch (HibernateException e) {
            rollbackTransaction();
            return null;
        } finally {
            closeSession();
        }
    }
    
    public int deleteRecipeById(int recipeId) {
        try {
            beginTransaction();
            Query query = getSession().createQuery("delete from Recipe where recipeId = :recipeId");
            query.setParameter("recipeId", recipeId);
            int result = query.executeUpdate();
            commitTransaction();
            return result;
        } catch (HibernateException e) {
            rollbackTransaction();
            return 0; 
        } finally {
            closeSession();
        }
    }
}
