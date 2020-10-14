<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<template:defaultLayout title="Users">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-8">
                <c:forEach items="${users.getUsers()}" var="user">
                    <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="${user.getProfilePicture()}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${user.getUsername()}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">${user.getFirstname()} ${user.getLastname()}</h6>
                            <p>${user.getEmail()}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</template:defaultLayout>
