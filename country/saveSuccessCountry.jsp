<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data Inserted Successfully</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        button {
            background-color: #3498db;
            color: #fff;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
        }

        a {
            color: #007bff;
            text-decoration: none;
            font-size: 18px;
            display: block;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Data Inserted Successfully</h1>
        <button onclick="closePage()">Close</button>
       <!--  <a href="addCountry.jsp">Data saved successfully. Click here to go back.</a> -->
    </div>

    <script>
        function closePage() {
            // You can customize this function based on your needs
            // For now, it simply navigates back to the 'addCountry.jsp' page
            window.location.href = 'addCountry.jsp';
        }
    </script>
</body>
</html>
