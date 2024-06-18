<%-- 
    Document   : chefPage
    Created on : 29-Mar-2024, 2:08:52 am
    Author     : shubhamjain
--%>

<%@page import="com.example.pojo.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.example.pojo.Recipe"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<style>
.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	transition: 0.3s;
	width: 30%;
	margin: 15px;
	padding: 10px;
	display: inline-block;
}

.card:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

.container {
	padding: 2px 16px;
}

form {
	margin-bottom: 20px; /* Add some space between forms */
}

label {
	font-weight: bold;
}

input[type="text"], select {
	width: 300px; /* Fixed width */
	padding: 8px;
	margin: 6px 0;
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 4px;
}

input[type="submit"], button[type="submit"] {
	background-color: #4CAF50;
	color: white;
	padding: 10px 20px;
	margin-top: 10px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
}

input[type="submit"]:hover, button[type="submit"]:hover {
	background-color: #45a049;
}

/* Arrange buttons horizontally */
.button-group {
	margin-top: 20px;
	text-align: center;
}

.button-group form, .button-group button {
	display: inline-block;
	vertical-align: middle;
}

/* Style the welcome message */
.welcome-message {
	font-size: 24px;
	font-weight: bold;
}

h2 {
	color: orange;
}

.container p{
	color: green;
	font-size:18px
}

.button-container {
    display: flex;
}

.button-container form {
    margin-right: 10px;
}

</style>
</head>
<body>

	<p class="welcome-message">
		Welcome
		<%=session.getAttribute("user") != null ? ((User) session.getAttribute("user")).getUsername() : "user"%></p>

	<div class="button-group">
		<form action="addRecipe.htm" method="get">
			<input type="submit" value="Add Recipe">
		</form>
		<form action="viewChefRecipes.htm" method="GET">
			<button type="submit">Show all Recipes</button>
		</form>
		<form action="logout.htm" method="post">
			<button type="submit">Logout</button>
		</form>
	</div>

	<h2>Uploaded Recipes</h2>
	<c:forEach var="recipe" items="${chefRecipes}">
		<div class="card">
			<img src="/covers/<c:out value="${recipe.photoPath}"/>"
				style="width: 100%">
			<div class="container">
				<h2>
					<b><c:out value="${recipe.title}" /></b>
				</h2>
				<p>
					<b>Description:</b>
					<c:out value="${recipe.description}" />
				</p>
				<p>
					<b>Ingredients:</b>
					<c:out value="${recipe.ingredients}" />
				</p>
				<p>
					<b>Cooking Instructions:</b>
					<c:out value="${recipe.cookingInstructions}" />
				</p>
				<p>
					<b>Creation Date:</b>
					<c:out value="${recipe.creationDate}" />
				</p>
				
				<div class="button-container">
				<form action="updateRecipeForm.htm" method="post">
					<input type="hidden" name="recipeId" value="${recipe.recipeId}">
					<input type="submit" value="Update">
				</form>

				<form action="deleteRecipe.htm" method="post">
					<input type="hidden" name="recipeId" value="${recipe.recipeId}">
					<input type="submit" value="Delete">
				</form>
				</div>

			</div>
		</div>
	</c:forEach>
</body>
</html>


