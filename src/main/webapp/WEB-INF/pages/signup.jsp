<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>

  <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

  <title>Sign Up</title>
  <style>
    body {
      background: url("/resources/images/site-background-6-small-dark-4.jpg");
      background-size: 100%;
    }
  </style>
</head>
<body>
<div class="signup">
    <h1 class="text-center">Sign Up</h1>

    <form:form method="post" commandName="user">
      <div class="form-group">
        <form:label for="inputUsername" path="name">Username:</form:label>
        <form:input class="form-control" id="inputUsername" placeholder="Username" path="name"/>
        <form:errors cssClass="error" path="name"/>
      </div>
      <div class="form-group">
        <form:label for="inputPassword" path="password">Password:</form:label>
        <form:password class="form-control" id="inputPassword" placeholder="Password" path="password"/>
        <form:errors cssClass="error" path="password"/>
      </div>
      <div class="form-group">
        <form:label for="confirmPassword" path="passwordConfirm">Confirm Password:</form:label>
        <form:password class="form-control" id="confirmPassword" placeholder="Confirm password" path="passwordConfirm"/>
        <form:errors cssClass="error" path="passwordConfirm"/>
      </div>
      <div class="form-group">
        <form:label for="inputEmail" path="email">Email:</form:label>
        <form:input class="form-control" id="inputEmail" placeholder="Email" path="email"/>
        <form:errors cssClass="error" path="email"/>
      </div>
      <button type="submit" class="btn btn-success">Sumbit</button>
    </form:form>

    <a href="/" class="btn btn-info">Go Home</a>
</div>
</body>
</html>
