<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Data Save Successfully</title>
    <style>
        body {
            font-family: Arial, sans-serif;
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
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #0066A2;
        }

        p {
            color: #333;
        }

        button {
            background-color: #0066A2;
            color: #fff;
            border: none;
            padding: 10px 20px;
            text-decoration: none;
            cursor: pointer;
            border-radius: 4px;
            margin-top: 10px;
        }

        button a {
            color: #fff;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Record Added Successfully</h2>
        <p>Code and Value saved successfully!</p>
        <button><a href="addGender.jsp">Close</a></button>
        <!-- Uncomment the line below if you want to display a link for adding more records -->
        <!-- <p><a href="addGender.jsp">Go back to add more records</a></p> -->
    </div>
</body>
</html>
