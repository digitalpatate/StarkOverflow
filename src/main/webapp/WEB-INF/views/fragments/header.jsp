<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header id="header">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/"><h1 class="logo">StarkØverflØw</h1></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/profile">Mon Profile</a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/statistic">statistics</a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/login">login</a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a>
                </li>
            </ul>
        </div>
    </nav>
</header>
