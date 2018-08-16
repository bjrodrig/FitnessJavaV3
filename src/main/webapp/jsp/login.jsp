<%--
  Created by IntelliJ IDEA.
  User: barodriguez
  Date: 1/9/2018
  Time: 10:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="header.jsp" %>
<script src="../js/login.js"></script>

<div class="box background_pale centered form">
  <span id="login_title" class="title">Login</span>
  <br><br>
  <span id="loginAjaxResponse" ></span>
  <form id="login_form" action="${pageContext.request.contextPath}/homepage" method="post">
  <table id="login_table">
    <tr><td>Username:</td>
      <td><input id="login_username" name="username" type="text" ></td>
    </tr>
    <tr><td>Password:</td>
      <td>
        <input id="login_password" name="password" type="password" required>
      </td>
    <tr>
      <td>
        <input id="login_submit" name="login_submit" type="submit" class="button standard_bold_font" value="Login">
      </td>
    </tr>
  </table>
  </form>
</div>


<%@include file="footer.jsp" %>