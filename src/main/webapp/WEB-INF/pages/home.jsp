<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>StarkOverflow</title>
</head>
<body>
    <h1>StarkOverflow</h1>

    <div id="questions">
        <c:forEach items="${questions}" var="question">
            <div class="question">
                <h2 class="question-title">${question.title}</h2>
                <p class="question-content">${question.content}</p>
            </div>
        </c:forEach>
    </div>
</body>
</html>
