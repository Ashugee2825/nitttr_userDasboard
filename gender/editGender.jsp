<%@page import="com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="gender.*" %>
<%@page import="java.util.*" %>  
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
<!-- begin: submit buttons -->
 <link href="tablestyle.css" rel="stylesheet" />

<!-- end: submit buttons -->
<!--  Begin: Date Picker -->
<link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/themes/base/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js"></script>
<script>
</script>
<!-- End: Date Picker -->
 <link rel="stylesheet" href="topMenu.css">
 <link rel="stylesheet" href="leftMenu.css">
<style>
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
</style>
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
<%@include file="topMenu.jsp" %> --%>
</div>
<!--  end: top menu -->
<!--  begin: container -->
<div class="container">
    
    <!-- begin: side navigation -->
    <!-- end: side navigation -->
    <%-- <%@include file="leftMenu.jsp" %> --%>
    <!--  begin: main content -->
    <div class="flex-item" align="center">
    
    <h1> Gender Content </h1>
    
    <!--  Begin: Dynamic Codes -->

<%

Gender gender = (Gender)request.getAttribute("Gender");

// System.out.print(gender.getCode());
%>
<form action="" id="myform"  name="myform" accept-charset="UTF-8" >

<table cellspacing="10px">

<tr>

<td> ID:</td>
<td> <%=gender.getId()%></td>
</tr>

<tr>

<td>
Code:<font color="red">*</font>:
</td>
 
 <td>
 <input required type ="text" name="code" value="<%=gender.getCode()%>" size="30">
 </td>

 </tr>
 
 <tr> 
 
<td>
Value:
</td>

<td>
<input type ="text" name="value" value="<%=gender.getValue()%>" size="30">
</td>

</tr>

</table>

<input type="hidden" name="page" value= "editGender">
<input type="hidden" name="id" value= "<%=gender.getId()%>">

</form>
<button type="submit" form="myform"  name="opr" value= "Update" class="form-submit-button">Update</button>
<button class="form-submit-button" onclick="window.location.href = 'genderDashboard.jsp'"> Close </button> 






           <!--  End: Dynamic Codes -->
    </div>
           <!--  end: main content -->
</div>
           <!--  end: container -->
</div>

</body>
</html>
 
                