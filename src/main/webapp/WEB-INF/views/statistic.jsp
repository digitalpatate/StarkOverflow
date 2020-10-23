<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Statistics">
    <div class="container">
        <div class="row">
            <h2>Statistics</h2>
        </div>
        <div class="statistic">
            <table class="table table-striped">
                <thead>
                    <tr>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">Total number of users</th>
                        <td>${nbUsers}</td>
                    </tr>
                    <tr>
                        <th scope="row">Total number of questions</th>
                        <td>${nbQuestions}</td>
                    </tr>
                    <tr>
                        <th scope="row">Total number of answers</th>
                        <td>${nbAnswers}</td>
                    </tr>
                    <tr>
                        <th scope="row">Total number of comments</th>
                        <td>${nbComments}</td>
                    </tr>
                    <tr>
                        <th scope="row">Total number of votes</th>
                        <td>${nbVotes}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template:defaultLayout>
