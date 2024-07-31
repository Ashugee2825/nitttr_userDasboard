<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Page</title>
    <link href="tableStyle.css" rel="stylesheet" />
    <style>
       
            body {
        font-family: Arial, sans-serif;
        background-color: #ecf0f1;
        color: #3498db;
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
            
        
 
        h1 {
            color: #333;
        }

        a {
            display: inline-block;
            margin: 10px 0;
            padding: 10px 20px;
            text-decoration: none;
            color: #fff;
            background-color: #3498db;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: #0056b3;
        }
        
        
    </style>
</head>
<body>
<center><div id="banner">
        <img alt="nitttr-bpl" src="banner.jpg" >
    </div></center>
    <h1>Welcome to the Admin Page</h1>
    <p>This is the Admin page.</p>

    <a href="../userProfile/addUserProfile.jsp">User Profile</a>
    <a href="../user/addUser.jsp">User</a>
    <a href="../group/addGroup.jsp">Group</a>
    <a href="../state/addState.jsp">State</a>
    <a href="../country/addCountry.jsp">Country</a>
    <a href="../gender/addGender.jsp">Gender</a>
</body>
</html>
