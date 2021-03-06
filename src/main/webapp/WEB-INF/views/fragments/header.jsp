<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header id="header">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <c:choose>
            <c:when test="${currentUser != null}">
                <a class="chocolate" href="/key"><img width="25" height="25" src="https://icons.iconarchive.com/icons/paomedia/small-n-flat/512/key-icon.png" /></a>
            </c:when>
            <c:otherwise>
                <a class="chocolate" href="/login"><img width="25" height="25" src="https://icons.iconarchive.com/icons/paomedia/small-n-flat/512/key-icon.png" /></a>
            </c:otherwise>
        </c:choose>
        <a class="navbar-brand" href="/"><h1 class="logo">StarkØverflØw</h1></a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
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
                <li class="nav-item">
                    <a class="nav-link" href="/users">users</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/pointScales">PointScales</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <c:choose>
                <c:when test="${errors != null}">
                    <div id="notification" class="alert alert-danger" role="alert">
                            ${errors}
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>

</header>
