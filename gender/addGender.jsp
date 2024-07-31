<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Gender</title>
<!-- <link href="NewFile.css" rel="stylesheet" /> -->
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

    #banner {
    margin-top: 10px;
    width: 40%;
    padding: 20px ;
    border: 2px solid #3498db;
    
    border-radius: 10px;
    
}
img {
    max-width: 100%;
    height: auto;
}
.image-container {
    max-width: 100%;
    overflow: hidden;
}

.image-container img {
    width: 100%;
    height: auto;
}
    h2 {
        color: #3498db;
        text-align: center;
    }

    form {
        max-width: 400px;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    label {
        display: inline-block; /* Display labels on the same line */
        width: 30%; /* Set a width for labels */
        margin-bottom: 8px;
        color: #3498db;
    }

    input[type="text"] {
        display: inline-block; /* Display input boxes on the same line */
        width: 65%; /* Set a width for input boxes */
        padding: 5px 8px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    input[type="submit"], button {
        background-color: #3498db;
        color: #fff;
        padding: 10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type="submit"]:hover, button:hover {
        background-color: #2980b9;
       
    }

    /* Apply margin to both buttons */
    input[type="submit"], button {
        margin-left: 10px;
        margin-top:10px;
    }
    .button-group {
    text-align: center;
}
    
</style>
</head>
<body>
	<center><div id="banner">
		<img alt="nitttr-bpl" src="banner.jpg" width="100%" height="auto">
	</div></center>

	<div class= "container">
		<h2 style="color: #6A679E;">Add Gender</h2>

		<form action="GenderCntrl2" method="post">
			<input type="hidden" name="action" value="addOrUpdate"> <label
				for="code">Code:</label> <input type="text" id="code" name="code"
				required><br> <label for="value">Value:</label> <input
				type="text" id="value" name="value" required><br>

			<!-- Save Button -->
			<div class="button-group">
				<input type="submit" name="opr" value="Save">
				<!-- Close Button -->
				<button onclick="window.location.href = 'genderDashboard.jsp'">Close</button>
			</div>

		</form>
		<%-- <%-- 
       <center>
       <div class="button-group">
            <!-- Close Button -->
            <button class="see" onclick="window.location.href='genderDashboard.jsp'">Close </button>

            <!-- Go Back to Dashboard Button -->
            <a href="genderDashboard.jsp"><button>Go Back To Dashboard 
             </button></a>
         </div>

        <!-- Display Gender List (if available) -->
        
        <div class="button-group">
            <c:forEach var="gender" items="${genderList}">
                <p>${gender.code} - ${gender.value}</p>
            </c:forEach>
        </div>  
    </div></center> 
     --%>  --%>
</body>
</html>
