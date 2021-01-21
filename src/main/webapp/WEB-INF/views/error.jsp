<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Login">
    <div class="container">
        <div class="row justify-content-center l-error">
           <h2>${statusCode} - Error</h2>
            <p>${message}</p>
        </div>
    </div>
</template:defaultLayout>
