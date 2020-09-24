<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="statistic">
        <h2 class="statistic-title">${statistic.title}</h2>
        <p class="statistic-content">${statistic.data}</p>
    </div>
</body>
</html>
