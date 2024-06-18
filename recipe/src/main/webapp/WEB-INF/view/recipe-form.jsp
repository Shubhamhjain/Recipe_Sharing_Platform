<%-- 
    Document   : addRecipe
    Created on : 29-Mar-2024, 2:10:45 am
    Author     : shubhamjain
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style>
        form {
            margin-bottom: 20px; /* Add some space between forms */
        }

        input[type="text"],
        select,
        input[type="file"] {
            width: 300px; /* Fixed width */
            padding: 8px;
            margin: 6px 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        
         input[type="text"].large {
            height: 100px; /* Set height to 100px */
        }

        input[type="submit"],
        button[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            margin-top: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover,
        button[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Add a new recipe</h1>
    <form:form modelAttribute="recipe" method="post" enctype="multipart/form-data">
        Title <form:input path="title"/> <form:errors path="title" /><br/>
        Description <form:input path="description" class="large"/> <form:errors path="description" /><br/>
        Ingredients <form:input path="ingredients" class="large"/> <form:errors path="ingredients" /><br/>
        Instructions <form:input path="cookingInstructions" class="large"/> <form:errors path="cookingInstructions" /><br/>
        Creation Date <form:input path="creationDate"/> <form:errors path="creationDate" /><br/>
        Recipe Photo <input type="file" name="photo" required="required" style="width: 300px; padding: 8px; margin: 6px 10px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px;"/><form:errors path="photo" /><br/>
        <input type="submit" value="Add Recipe"/>
    </form:form>
</body>
</html>
