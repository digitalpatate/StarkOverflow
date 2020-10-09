<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Home">
    <div class="container">
        <div class="row">
            <div class="col">
                <h2>Post your question</h2>
                <form action="/questions" method="post">
                    <div class="form-group">
                        <label for="questionTitle">Title</label>
                        <input type="text" class="form-control" id="questionTitle" name="questionTitle" aria-describedby="question title" placeholder="title"  required="true">
                    </div>
                    <div class="form-group">
                        <label for="questionContent">Question</label>
                        <textarea class="form-control" id="questionContent" name="questionContent" rows="3" required="true"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Post</button>
                </form>
            </div>
            <div class="col">
                <h2>Last questions</h2>
                <div class="question-list">
                    <c:forEach items="${questions.getQuestions()}" var="question">
                        <a href="/question/${question.id}">
                            <div class="question">
                                <h2 class="question-title">${question.title}</h2>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</template:defaultLayout>
