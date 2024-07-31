
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>

<%
    Connection conn = null;
    Statement stmt = null;

    try {
        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/";  //your_database_host:your_database_port/your_database_name";
        String username = "nitttr";
        String password = "123456";
        conn = DriverManager.getConnection(jdbcUrl, username, password);

        // Get values from the form
        String id = request.getParameter("id");
        String code = request.getParameter("code");
        String value = request.getParameter("value");

        // Execute SQL query to insert data into the table
        String sql = "INSERT INTO nitttr (id, code, value) VALUES ('" + id + "', '" + code + "', '" + value + "')";
        stmt = conn.createStatement();
        int rowsAffected = stmt.executeUpdate(sql);

        // Display success message
        out.println("<h2>Data saved successfully!</h2>");
    } catch (Exception e) {
        // Handle exceptions
        out.println("<h2>Error: " + e.getMessage() + "</h2>");
    } finally {
        // Close the resources
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>


