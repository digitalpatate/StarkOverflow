<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Register">
    <div class="container">
        <div class="row justify-content-center">
            <div id="register" class="box-content col-4">
                <h2>Register Page</h2>
                <form action="register" method="post">
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input id="email" class="form-control" name="email" type="email">
                    </div>
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input id="username" class="form-control" name="username" type="text">
                    </div>
                    <div class="form-group">
                        <label for="profilePicture">Profile Picture URL:</label>
                        <input id="profilePicture" class="form-control" name="profilePicture" type="url">
                    </div>
                    <div class="form-group">
                        <label for="firstname">Firstname:</label>
                        <input id="firstname" class="form-control" name="firstname" type="text">
                    </div>
                    <div class="form-group">
                        <label for="lastname">Lastname:</label>
                        <input id="lastname" class="form-control" name="lastname" type="text">
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input id="password" class="form-control" name="password" type="password">
                    </div>
                    <input type="submit" class="btn btn-primary" value="Register">
                </form>
            </div>
        </div>
    </div>
</template:defaultLayout>
