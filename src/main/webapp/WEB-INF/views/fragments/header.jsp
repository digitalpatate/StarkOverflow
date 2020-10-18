<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header id="header">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/"><h1 class="logo">StarkØverflØw</h1></a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/statistic">statistics</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/users">Utilisateurs</a>
                </li>

                <c:choose>
                    <c:when test="${currentUser != null}">
                        <li class="nav-item dropdown user-dropdown">
                            <div class="user-image-container" style="background-image: url(${currentUser.profilePicture})"></div>
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                ${currentUser.username}
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="/profile">
                                    Mon Profile
                                </a>
                                <form class="dropdown-item" action="/logout" method="POST">
                                    <button type="submit" class="btn btn-link nav-link">Logout</button>
                                </form>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="/register">register</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/login">login</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-item">
                    <a class="nav-link" href="/statistic">statistics</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <c:choose>
                <c:when test="${errors != null}">
                    <div class="alert alert-danger" role="alert">
                            ${errors}
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>

</header>
