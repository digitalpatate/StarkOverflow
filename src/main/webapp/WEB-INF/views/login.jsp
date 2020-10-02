<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Statistics">
    <div class="container">
        <div class="row">
            <div id="login">
                <h2>Login Page</h2>
                <form action="login.do" method="post">
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input id="email" class="form-control" name="email" type="email">
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input id="password" class="form-control" name="password" type="password">
                    </div>
                    <input type="submit" class="btn btn-primary" value="Login">
                </form>
            </div>
        </div>
    </div>
</template:defaultLayout>
