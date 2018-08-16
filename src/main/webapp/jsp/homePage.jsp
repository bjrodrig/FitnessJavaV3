<%--
  Created by IntelliJ IDEA.
  User: barodriguez
  Date: 1/12/2018
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="header.jsp" %>

<div class="box background_pale centered form">
  <span id="home_page_title" class="title">Hello ${sessionScope.username}</span>
  <br><br>
  <a class="button standard_bold_font"
     href="${pageContext.request.contextPath}/foodDiary?reasonForGet=FromHomePage">Food Diary</a>
</div>

<%@include file="footer.jsp" %>
