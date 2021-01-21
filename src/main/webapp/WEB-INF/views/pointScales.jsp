<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Home">
    <div class="container">
        <div class="row justify-content-center">
            <h2 class="ps-title">PointsScales</h2>
            <c:forEach items="${pointScales}" var="pointScale">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">${pointScale.name}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">${pointScale.description}</h6>
                        <a href="/leaderboard/${pointScale.name}" class="card-link">Go to leaderboard</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</template:defaultLayout>
