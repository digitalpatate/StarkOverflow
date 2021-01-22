<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Home">
    <div class="container">
        <div class="row justify-content-center">
            <h3>${pointScaleName}</h3>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Username</th>
                    <th scope="col">Score</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${leaderboard.leaderboard.scores}" var="score">
                    <tr>
                        <td>${score.username}</td>
                        <td>${score.score}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <c:choose>
            <c:when test="${leaderboard.numberOfPage > 1}">
                <div class="row justify-content-center">

                    <div class="pagination">
                        <c:forEach var="counter" begin="0" end="${leaderboard.numberOfPage-1}">
                            <a href="/leaderboard/${pointScaleName}/page/${counter}">${counter}</a>
                        </c:forEach>
                    </div>
                </div>
            </c:when>
        </c:choose>
    </div>
</template:defaultLayout>
