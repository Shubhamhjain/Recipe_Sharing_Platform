<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NomNom - A Foodie's Paradise</title>
        <style>
            body {
                font-family: Arial, sans-serif;
				background-image: url('/images/background.jpg'); /* Replace 'background_image.jpg' with the path to your image */
                background-size: cover; /* Adjusts the size of the background image to cover the entire viewport */
                background-repeat: no-repeat; /* Ensures the background image does not repeat */
                background-position: center; /* Centers the background image */
                margin: 0;
                padding: 0;
                text-align: center;
            }

            .container {
                max-width: 600px;
                margin: 50px auto;
                padding: 20px;
                background-color: #f2f2f2;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            h1, h2 {
                color: #333;
            }

            p {
                color: #666;
                font-size: 16px;
                line-height: 1.6;
                margin-bottom: 20px;
            }

            button {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin: 10px;
            }

            button:hover {
                background-color: #45a049;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <h1>Welcome to NomNom - A Foodie's Paradise!</h1>
            <p>Embark on a culinary journey like no other! Explore a world of mouthwatering recipes and culinary inspiration - we have something to satisfy every craving.</p>
            <p>Sign up now to unlock access to our extensive recipe collection</p>
            <form action="toRegisterPage.htm" method="GET">
                <button type="submit">Register</button>
            </form>
            <p>Already a member? Log in and indulge in our delicious offerings.</p>
            <form action="toLoginPage.htm" method="GET">
                <button type="submit">Login</button>
            </form>
        </div>
    </body>
</html>
