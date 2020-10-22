<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Home">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-8">
                <div class="question-details">
                    <h2 class="question-title">${question.title}</h2>
                    <ul class="tag-list">
                        <c:forEach items="${question.getTags().getTags()}" var="tag">
                            <li class="tag" style="background-color: ${tag.getColor()};">${tag.getName()}</li>
                        </c:forEach>
                    </ul>
                    <hr />
                    <div class="question-content-container">
                        <p class="question-content">${question.content}</p>
                        <div class="question-infos">
                            <div class="author">
                                <div class="user-image-container" style="background-image: url(${question.user.profilePicture})"></div>
                                <p class="author-username">${question.user.username}</p>
                            </div>
                            <p class="creationDate">${question.creationDate}</p>
                        </div>
                    </div>
                    <hr />
                </div>
                <div class="answer-list">
                    <c:forEach items="${question.getAnswers().getAnswers()}" var="answer">
                        <div class="answer-details">
                            <div class="author">
                                <div class="user-image-container" style="background-image: url(${answer.user.profilePicture})"></div>
                                <p class="author-username">${answer.user.username}</p>
                            </div>
                            <p class="answer-content">${answer.content}</p>
                            <div class="answer-actions">
                                <c:choose>
                                    <c:when test="${empty question.acceptedAnswerId}">
                                        <c:choose>
                                            <c:when test="${question.user.id == currentUser.id}">
                                                <form class="acceptation-form" method="post" action="/answer/accept">
                                                    <input type="hidden" name="questionId" value="${question.id}">
                                                    <input type="hidden" name="answerId" value="${answer.id}" />
                                                    <button class="reset-btn accept-btn" type="submit">✓</button>
                                                </form>
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${question.acceptedAnswerId == answer.id}">
                                                <p class="accepted-answer">✓</p>
                                            </c:when>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="vote-container">
                                <p class="vote-nb">${answer.nbVotes}</p>
                                <form class="vote-form" method="post" action="/vote">
                                    <input type="hidden" name="questionId" value="${question.id}" />
                                    <input type="hidden" name="answerId" value="${answer.id}" />
                                    <c:choose>
                                        <c:when test="${answer.user.id != currentUser.id}">
                                            <button class="reset-btn vote-btn" type="submit">
                                                <c:choose>
                                                    <c:when test="${answer.voted}">
                                                        <i class="fas fa-lightbulb"></i>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <i class="far fa-lightbulb"></i>
                                                    </c:otherwise>
                                                </c:choose>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="reset-btn vote-btn" type="submit" disabled>
                                                <i class="fas fa-lightbulb disabled"></i>
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                            </div>
                        </div>
                        <hr />
                    </c:forEach>
                </div>
                <form action="/answer" method="post">
                    <div class="form-group">
                        <input type="hidden" name="questionId" value="${question.id}">
                        <label for="answerContent">Answer</label>
                        <textarea class="form-control" id="answerContent" name="answerContent" rows="3" required="true"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Post</button>
                </form>

            </div>
        </div>
    </div>
</template:defaultLayout>
