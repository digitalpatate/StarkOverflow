<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Home">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-8 box-content">
                <h2 class="border-bottom mb-3">${user.username}</h2>
                <img class="mb-3" width="200px" src="${user.getProfilePicture()}" alt="">
                <h3 class="mb-3 text-secondary">${user.firstname} ${user.lastname}</h3>
                <h4 class="mb-3">${user.email}</h4>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-8 box-content">
                <h2 class="border-bottom mb-3">Asked questions</h2>
                <c:choose>
                    <c:when test="${questions.getQuestions().size() > 0}">
                        <div class="question-list">
                            <c:forEach items="${questions.getQuestions()}" var="question">
                                <a href="/question/${question.id}">
                                    <div class="question">
                                        <h2 class="question-title">${question.title}</h2>
                                        <ul class="tag-list">
                                            <c:forEach items="${question.getTags().getTags()}" var="tag">
                                                <li class="tag" style="background-color: ${tag.getColor()};">${tag.getName()}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>Cet utilisateur est un génie, il a pas de question...</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</template:defaultLayout>
