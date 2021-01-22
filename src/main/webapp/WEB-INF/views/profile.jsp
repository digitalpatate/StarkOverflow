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
        <div class="row text-center">
            <div class="col-8 box-content">
                <h2 class="border-bottom mb-3">Badges</h2>
                <c:choose>
                    <c:when test="${badges.size() > 0}">
                        <div class="badge-list">
                            <div class="row">
                                <c:forEach items="${badges}" var="badge">
                                    <div class="col">
                                        <div class="card mb-3" style="width: 10rem;">
                                            <img class="card-img-top" src="${badge.imageUrl}" alt="Card image cap">
                                            <div class="card-body">
                                                <span class="badge badge-pill badge-success">${badge.occurence}</span>
                                                <p class="card-text">${badge.name}</p>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>Pas encore de badge !</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="row text-center">
            <div class="col-8 box-content">
                <h2 class="border-bottom mb-3">Point Rewards</h2>
                <c:choose>
                    <c:when test="${pointRewards.size() > 0}">
                        <div class="pointRewards-list">
                            <div class="row">
                                <c:forEach items="${pointRewards}" var="pointReward">
                                    <div class="col">
                                        <div class="card text-white bg-warning mb-3" style="max-width: 18rem;">
                                            <div class="card-header">${pointReward.pointScaleName}</div>
                                            <div class="card-body">
                                                <h5 class="card-title">${pointReward.nbPoint}</h5>
                                                <p class="card-text">${pointReward.pointScaleDescription}</p>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>Pas encore de point gagné !</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</template:defaultLayout>
