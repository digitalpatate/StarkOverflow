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
                    <div class="form-group">
                        <select class="tag-select form-control" multiple="multiple" name="tags">
                            <c:forEach items="${tags.getTags()}" var="tag">
                                <option>${tag.name}</option>
                            </c:forEach>
                        </select>

                        <!-- Load select2 -->
                        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
                        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>

                        <!-- Select2 code -->
                        <script>
                            $(document).ready(function() {
                                $(".tag-select").select2({
                                    placeholder: "Tags",
                                    tags: true
                                });
                            });
                        </script>
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
                                <ul class="tag-list">
                                    <c:forEach items="${question.getTags().getTags()}" var="tag">
                                        <li class="tag" style="background-color: ${tag.getColor()};"><a href="?tag=${tag.getName()}">${tag.getName()}</a></li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</template:defaultLayout>
