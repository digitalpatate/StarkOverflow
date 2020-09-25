<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Register">
    <h2>Register Page</h2>
    <div id="register">
        <form action="register.do" method="post">
            <label for="email">Email:</label> <input id="email" name="email" type="email"><br>
            <label for="profilePicture">Profile Picture URL:</label> <input id="profilePicture" name="profilePicture" type="url"><br>
            <label for="name">Name:</label> <input id="name" name="name" type="text">
            <label for="surname">Surname:</label> <input id="surname" name="surname" type="text"><br>
            <label for="password">Password:</label> <input id="password" name="password" type="password"><br>
            <input type="submit" value="Register">
        </form>
    </div>
</template:defaultLayout>
