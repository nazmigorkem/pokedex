<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact Search</title>
    <link rel="stylesheet" type="text/css" href="./css/main.css">
</head>
<body>
<div class="container">
    <a href="index.jsp">Search Contact</a>
    <h2>Contact Search</h2>
    <form action="searchContact" method="get">
        <label for="searchId">Search by ID:</label>
        <input type="text" id="searchId" name="id">
        <input type="submit" value="Search by ID">
    </form>

<%--    <form action="searchContacts.jsp" method="get">--%>
<%--        <label for="searchName">Search by Name:</label>--%>
<%--        <input type="text" id="searchName" name="name">--%>
<%--        <input type="submit" value="Search by Name">--%>
<%--    </form>--%>
</div>
</body>
</html>