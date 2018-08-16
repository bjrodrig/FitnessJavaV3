<%--
  Created by IntelliJ IDEA.
  User: barodriguez
  Date: 12/20/2017
  Time: 4:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="stylesheets/fitness.css">
  <title>Fitness</title>
</head>
<body id="main" class="fruit standard_bold_font">
    <div id="header">
    <span id="signup" class="button standard_bold_font">
      <a id="signup_link" href="${pageContext.request.contextPath}/signup">Sign Up</a>
    </span>
    <span id="login" class="button standard_bold_font">
      <a href="${pageContext.request.contextPath}/login">Login</a>
    </span>
    </div>


  <div id="welcome_message" class="box centered">
    Welcome to our fitness website. By creating an account, you can enter your personal information,
    such as weight or height, and fitness goals and receive a recommendation on how many calories
    you should consume per day. From there, you can use our food diary to calculate how many calories
    you are eating per day, in order to meet your fitness goals. Once you finish entering food for the day,
    submit your entry to see your expected weight in five weeks, if your calorie intake for the next five
    weeks matched today's entry.
  </div>


<%@include file="jsp/footer.jsp" %>
