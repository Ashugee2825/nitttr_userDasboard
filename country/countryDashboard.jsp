<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="country.*" %>
<%@page import="java.util.*" %> 
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Country Dashboard</title>
    <link href="tablestyle.css" rel="stylesheet" />
    <!-- Add jQuery library -->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <!-- Add DataTables library -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
    <!-- Custom script for dynamic table and search -->
    <script>
    function confirmDelete() {
        return confirm("Are you sure you want to delete?");
    }
</script>
    
    <script>
        $(document).ready(function() {
            // Initialize DataTable
            var table = $('#example').DataTable();

           
            // Apply search on input change
            $('#searchInput').on('keyup', function() {
                table.search(this.value).draw();
            });
        });
    </script>
</head>
<body>
<center>
<div id="banner" align="center">
        <img alt="nitttr-bpl" src="banner.jpg" >
    </div></center>
    <br>
    <a href="addCountry.jsp"><button>Add New Record</button></a>
    <% 
        CountryDBService countryDBService = new CountryDBService();
        //fetch the record from database
        ArrayList<Country> countryList = countryDBService.getCountryList();
        countryDBService.closeConnection();
    %>
    
    <Center>
        <table id="example">
            <thead>
                <th>SN</th>
                <th>ID</th>
                <th>Country Code</th>
                <th>Country Value</th>
                <th>View</th>
                <th>Edit</th>
                <th>Delete</th>
            </thead>
            <tbody>
                <!--  iterate the records -->
                <% int sn = 1; %>
                <% for(Country country:countryList){ %> 
    <tr>
        <td><%= sn++ %></td>
        <td><%=country.getId()%></td>
        <td><%=country.getCode()%></td>
        <td><%=country.getValue()%></td>
        <td>
            <a href="CountryCntrl?page=countryDashboard&opr=view&id=<%=country.getId()%>">
                <button>View</button>
            </a>
        </td>
        <td>
            <a href="CountryCntrl?page=countryDashboard&opr=edit&id=<%=country.getId()%>">
                <button>Edit</button>
            </a>
        </td>
        <td>
            <a href="CountryCntrl?page=countryDashboard&opr=delete&id=<%=country.getId()%>"
               onclick="return confirmDelete();">
               <button>Delete</button>
            </a>
        </td>
    </tr>
<%}%>

            </tbody>
        </table>
    </Center>
</body>
</html>
