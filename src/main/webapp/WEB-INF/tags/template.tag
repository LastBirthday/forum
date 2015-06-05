<%@ tag description="Template Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
<head>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/themeUtil.js" />"></script>
    <script src="<c:url value="/resources/js/subthemeUtil.js" />"></script>
    <script src="<c:url value="/resources/js/messageUtil.js" />"></script>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

    <title>My Forum</title>
    <style>
        body {
            background: url("/resources/images/site-background-6-small-dark-4.jpg");
            background-size: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
  <h1><a href="/">My Forum</a></h1>

  <div class="login-link-container">
    <sec:authorize access="isAnonymous()">
        <p>
            <a href="/spring_security_login"><button class="btn btn-success">Sign In</button></a>
        </p>
        <p>
            <a href="/signup"><button type="button" class="btn btn-info">Register</button></a>
        </p>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a href="/j_spring_security_logout"><button type="button" class="btn btn-warning">Sign Out</button></a>
    </sec:authorize>
  </div>
  <jsp:doBody/>
</div>
</body>
</html>
