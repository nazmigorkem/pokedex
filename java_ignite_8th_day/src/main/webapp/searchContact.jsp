<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact Search</title>
    <link rel="stylesheet" type="text/css" href="./css/main.css">
</head>
<body>
<div class="container">
    <jsp:include page="nav.jsp" />

    <h2>Contact Search</h2>
    <form action="searchContact" method="get">
        <label for="searchId">Search by ID:</label>
        <input type="text" id="searchId" name="id">
        <input class="submitButton" type="submit" value="Search by ID">
    </form>
</div>
</body>
</html>