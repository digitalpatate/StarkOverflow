<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Home">
    <div class="container">
        <div class="row">
            <div class="profile">
                <h2>Profile Page</h2>
                <p>${currentUser.username}</p>
            </div>
        </div>
    </div>
</template:defaultLayout>
