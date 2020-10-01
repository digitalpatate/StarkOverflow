<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Statistics">
    <h2>Statistics Page</h2>
    <div class="statistic">
        <c:forEach items="${statistics}" var="statistic">
            <p class="statistic-content">${statistic.data}</p>
        </c:forEach>
    </div>
</template:defaultLayout>
