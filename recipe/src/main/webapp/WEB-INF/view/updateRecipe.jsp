<%-- 
    Document   : updateRecipe
    Created on : 15-Apr-2024, 3:17:25 pm
    Author     : shubhamjain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.example.pojo.Recipe"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Recipe</title>
    <!-- Include your CSS -->
</head>
<body>
    <h1>Update Recipe:</h1>
    <%-- Retrieve the recipe details based on the passed recipeId --%>
 
    <form action="updateRecipeAction.htm" method="post">
        <input type="hidden" name="recipeId" value="<c:out value="${recipe.recipeId}"/>">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" value="<c:out value="${recipe.title}"/>"><br><br>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" value="<c:out value="${recipe.description}"/>"><br><br>
        <label for="ingredients">Ingredients:</label>
        <input type="text" id="ingredients" name="ingredients" value="<c:out value="${recipe.ingredients}"/>"><br><br>
        <label for="cookingInstructions">Cooking Instructions:</label>
        <input type="text" id="cookingInstructions" name="cookingInstructions" value="<c:out value="${recipe.cookingInstructions}"/>"><br><br>
        <input type="submit" value="Update">
    </form>
</body>
</html>

