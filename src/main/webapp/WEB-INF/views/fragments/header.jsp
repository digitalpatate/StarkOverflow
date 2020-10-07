<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header id="header">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/"><h1 class="logo">StarkØverflØw</h1></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse">
            <c:choose>
                <c:when test="${currentUser != null}">
                    <div>
                        ${currentUser.username}
                    </div>
                    <form action="/logout" method="POST">
                        <button type="submit">Logout</button>
                    </form>
                </c:when>
            </c:choose>

            <div class="navbar-nav">
                <a class="nav-link" href="/statistic">statistics</a>
                <a class="nav-link" href="/register">Register</a>
                <a class="nav-link" href="/login">login</a>
                <a class="nav-link" href="/profile">Mon Profile</a>
            </div>
        </div>
    </nav>
</header>
