<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Statistics">
    <h2>Login Page</h2>
    <div id="login">
        <form action="login.do" method="post">
            <label for="email">Email:</label> <input id="email" name="email" type="email">
            <label for="password">Password:</label> <input id="password" name="password" type="password">
            <input type="submit" value="Login">
        </form>
    </div>
</template:defaultLayout>
