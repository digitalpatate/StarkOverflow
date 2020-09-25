<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>StarkOverflow</title>
</head>
<body>
    <h1>StarkOverflow</h1>

    <div id="register">
        <form action="register.do" method="post">
            <label for="email">Email:</label> <input id="email" name="email" type="text">
            <label for="password">Password:</label> <input id="password" name="password" type="password">
            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>
