<%@ page import="com.example.java_ignite_8th_day.Model.Contact" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Contact List</title>
  <link rel="stylesheet" type="text/css" href="./css/main.css">
</head>
<body>
<div class="container">
  <h2>Contact List</h2>
  <table>
    <tr>
      <th>ID</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Phone Number</th>
      <th>Email</th>
    </tr>
    <%
      ArrayList<Contact> contacts = (ArrayList<Contact>) request.getAttribute("contacts");
      if (contacts != null && !contacts.isEmpty()) {
        for (Contact contact : contacts) {
    %>
    <tr>
      <td><%= contact.getId() %></td>
      <td><%= contact.getFirstName() %></td>
      <td><%= contact.getLastName() %></td>
      <td><%= contact.getPhoneNumber() %></td>
      <td><%= contact.getEmail() %></td>
      <td>
        <form action="searchContact" method="get">
          <input type="hidden" name="id" value="<%= contact.getId() %>">
          <input type="hidden" name="editable" value="true">
          <input type="submit" value="Edit">
        </form>
      </td>
    </tr>
    <%     }
    } else {
    %>
    <tr>
      <td colspan="5">No contacts found.</td>
    </tr>
    <% } %>
  </table>
</div>
</body>
</html>