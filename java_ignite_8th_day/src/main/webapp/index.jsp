<%--suppress ALL --%>
<%@ page import="com.example.java_ignite_8th_day.Model.Contact" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Form</title>
    <link rel="stylesheet" type="text/css" href="./css/main.css">
</head>
<body>
<div class="container">
    <jsp:include page="nav.jsp" />

    <h2>Registration Form</h2>
    <% if (request.getAttribute("contact") != null) {
        Contact contact = (Contact) request.getAttribute("contact");
    %>
    <form action="editContact" method="post">
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" value="<%= contact.getId() %>" required><br><br>

        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" value="<%= contact.getFirstName() %>" required><br><br>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="<%= contact.getLastName() %>" required><br><br>

        <label for="phoneNumber">Phone Number:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" value="<%= contact.getPhoneNumber() %>" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= contact.getEmail() %>" required><br><br>

        <input type="submit" value="Submit">
    </form>
    <% } else { %>
    <form action="createContact" method="post">
        <label for="id">ID:</label>
        <input type="number" id="id" name="id" required>

        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required>

        <label for="phoneNumber">Phone Number:</label>
        <input type="number" id="phoneNumber" name="phoneNumber" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <input type="submit" value="Submit">
    </form>
    <% } %>

</div>
</body>
</html>