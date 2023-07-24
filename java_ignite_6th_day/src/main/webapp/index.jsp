<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%!
  public static String test() {
    return "Test";
  }
%>
<!DOCTYPE html>
<html>
  <head>
    <title>JSP - Hello World</title>
  </head>
  <body>
    <h1><%= "Hello World!" + test()%></h1>
    <br/>
    <a href="hello-servlet">Hello Servlet</a>
  </body>
</html>