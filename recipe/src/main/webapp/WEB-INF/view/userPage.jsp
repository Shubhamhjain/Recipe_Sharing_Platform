<%-- 
    Document   : userPage
    Created on : 29-Mar-2024, 2:09:07 am
    Author     : shubhamjain
--%>

<%@page import="com.example.pojo.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('/images/background3.jpg'); /* Add the path to your background image */
            background-size: cover;
            background-repeat: no-repeat;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 400px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
        }

        .welcome-message {
            margin-bottom: 20px;
            font-size: 24px;
            font-weight: bold;
        }

        .button-container {
            margin-top: 20px;
        }

        .button, .logout-button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            margin-right: 10px; /* Add some spacing between buttons */
            border: none; /* Remove the border */
            outline: none; 
        }

        .button:hover, .logout-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="welcome-message">
            <p>Welcome <%= session.getAttribute("user") != null ? ((User) session.getAttribute("user")).getUsername() : "user"%></p>
        </div>
        <div class="button-container">
            <a href="allRecipes.htm" class="button">View All Recipes</a>
            
            <!-- Logout form -->
            <form action="logout.htm" method="post" class="button-container">
                <button type="submit" class="logout-button">Logout</button>
            </form>
        </div>
    </div>
</body>
</html>


