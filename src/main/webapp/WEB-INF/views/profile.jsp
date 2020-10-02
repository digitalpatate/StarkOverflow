<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Home">
    <h1>StarkOverflow</h1>
    <h2>Profile Page</h2>
    <div class="profile">
        <p class="question-content">${currentUser.username}</p>
    </div>
</template:defaultLayout>
