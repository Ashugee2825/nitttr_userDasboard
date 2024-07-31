<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="gender.*"%>
<%@page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gender Dashboard</title>
<link href="tablestyle.css" rel="stylesheet" />
<!-- Add jQuery library -->
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<!-- Add DataTables library -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
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
	<div id="banner" align="center">
		<img alt="nitttr-bpl" src="banner.jpg" width="30%" height="70%">
	</div>
	<center>
		<a href="addGender.jsp"><button>Add New Record</button></a>
	</center>
	<% 
        GenderDBService genderDBService = new GenderDBService();
        //fetch the record from database
        ArrayList<Gender> genderList = genderDBService.getgenderList();
        genderDBService.closeConnection(); 
    %>

	<center>
		<table id="example">
			<thead>
				<th>SN</th>
				<th>ID</th>
				<th>Gender Code</th>
				<th>Gender Value</th>
				<th>View</th>
				<th>Edit</th>
				<th>Delete</th>
			</thead>
			<tbody>
				<!-- iterate the records -->
				<% int sn=1; %>
				<% for(Gender gender:genderList){ %>
				<tr>
					<td><%=sn++%></td>
					<td><%=gender.getId()%></td>
					<td><%=gender.getCode()%></td>
					<td><%=gender.getValue()%></td>
					<td><a href="GenderCntrl2?page=genderDashboard&opr=view&id=<%=gender.getId()%>">
							<button>View</button>
					</a></td>
					<td><a href="GenderCntrl2?page=genderDashboard&opr=edit&id=<%=gender.getId()%>">
							<button>Edit</button>
					</a></td>
					<td><a href="GenderCntrl2?page=genderDashboard&opr=delete&id=<%=gender.getId()%>"
					onclick="return confirmDelete();">
							<button>Delete</button>
					</a></td>
				</tr>
				<%}
              %>


			</tbody>
		</table>
	</center>
</body>
</html>
