<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="gender.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
 <link href="tablestyle.css" rel="stylesheet" />
<!-- begin: submit buttons -->

<!-- <style>
div#banner {
background-color: white;
height: 120px;
}
div#main {
border: solid 2px blue;
margin-left: 100px;
min-height: 300px;
background-color: yellow;
}
div#leftMenu {
background-color: green;
width: 80px;
position: relative;
top: 0px;
min-height: 300px;
}
div#topMenu {
border: 0px solid red;
background-color: orange;
}
div#main {
border: 2px solid red;
background-color: red;
}
div#content {
border: 2px solid red;
background-color: red;
}
.container {
    background-color:white;
    display:flex;
}
.fixed-left-menu {
    background-color:white;
    width: 200px;
}
.flex-item {
    background-color:white;
    flex-grow: 1;
}
</style> -->
</head>
<meta charset="ISO-8859-1">

<title>Dashboard Template</title>

</head>
<body>
<div id="outer">
<!-- begin: banner -->
<div id="banner" align="center">
<img alt="nitttr-bpl" src="banner.jpg" width="70%" height="100%" >
</div>
<!-- begin: banner -->
<br/>
<!--  begin: top menu -->
<%-- <div id="topMenu" class="topMenu">
<%@include file="topMenu.jsp" %>
</div> --%>
<!--  end: top menu -->
<!--  begin: container -->
<div class="container">
    
 <%--    <!-- begin: side navigation -->
    <!-- end: side navigation -->
    <%@include file="leftMenu.jsp" %>
    <!--  begin: main content -->     --%>
    <div class="flex-item" align="center">
    
    <h1>Gender  Registration Form</h1>
    <% Gender gender = (Gender)request.getAttribute("gender");
  
    %>
    <h3>Record (id= <%=gender.getId()%>) Deleted successfully</h3><br/>
     <button class="form-submit-button" onclick="window.location.href = 'genderDashboard.jsp'"> Close </button> 
    </div>
    <!--  end: main content -->
</div>
<!--  end: container -->
</div>
</body>
</html>
