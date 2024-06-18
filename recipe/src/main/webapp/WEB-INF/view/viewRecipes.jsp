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
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
}

input[type="submit"]:hover, button[type="submit"]:hover {
	background-color: #45a049;
}

/* Title style */
h1 {
	margin-bottom: 20px; /* Add space below the title */
	color: blue; /* Change color */
	font-size: 36px; /* Increase font size */
}

h2 {
	color: orange;
}
/* Style other field values */
.card p {
	color: green; /* Change color */
	font-size: 18px; /* Adjust font size */
}

.uploaded-by-text {
	color: #800080;
	/* Change to the color you prefer for "Uploaded By:" text */
}

.uploaded-by-username {
	color: #800080; /* Change to the color you prefer for the username */
}
</style>
</head>
<body>

	<h1>Search Recipes:</h1>
	<form action="searchRecipes.htm" method="post">
		<label for="keyword">Keyword:</label> <input type="text" id="keyword"
			name="keyword"><br> <br> <label for="parameter">Search
			By:</label> <select id="parameter" name="parameter">
			<option value="title">Title</option>
			<option value="ingredients">Ingredients</option>
			<option value="description">Description</option>
		</select><br> <br> <input type="submit" value="Search">
	</form>

	<div style="display: flex; justify-content: start;">
		<form action="allRecipes.htm" method="GET">
			<button type="submit">Show all Recipes</button>
		</form>

		<form action="logout.htm" method="post" style="margin-left: 10px;">
			<button type="submit">Logout</button>
		</form>
	</div>
	<c:forEach var="recipe" items="${recipes}">
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

				<p>
					<b><span class="uploaded-by-text">Uploaded By:</span></b> <span
						class="uploaded-by-username"> <c:out
							value="${recipe.user.username}" />
					</span>
				</p>
			</div>
		</div>
	</c:forEach>
</body>
</html>
