<%--
  Created by IntelliJ IDEA.
  User: barodriguez
  Date: 12/20/2017
  Time: 8:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<script>let contextPath = "${pageContext.request.contextPath}";</script>
<script src="../js/signup.js"></script>

<%@include file="header.jsp" %>
<div class="box background_pale centered form">
  <span id="signup_title" class="title">Sign Up</span>
  <br><br>
  <span id="ajaxResponse"></span>
  <span id="signup_completion_warning" class="medium_font red">
    There is a second page to the sign up form.
    If you leave this page without completing the form, your account
    will not be created.
  </span>
  <br><br>
  <div class="medium_font standard_bold_font">
  <form id="signup_form" action="${pageContext.request.contextPath}/signup" onsubmit="return validateForm()" method="post" >
    <span id="form_errors" class="warning medium_font">
    ${sessionScope.invalidUsername}
    </span>
    <span class="help_text" id="signup_help">
      All fields are required. Submitting the form without completing all fields may cause you to lose your data.
    </span>
    <table id="signup_table">
      <tr><td>Username:</td>
        <td><input id="username" name="username" type="text" onblur="validateUsername()" ></td>
        <td><b id="username_error"></b></td>
      </tr>
      <tr><td>Password:</td>
        <td>
          <input id="password" name="password" type="password" required onkeyup="validatePasswordLength()">
        </td>
        <td><p id="password_error" class="warning"></p></td>
      </tr>
      <tr><td>Confirm Password:</td>
        <td>
          <input id="confirm_password" name="confirm_password" type="password" required onkeyup="validatePasswordLength()">
        </td>
      </tr>
      <tr></tr>
      <tr>
        <td>
        <input id="signup_next" name="signup_next" type="submit" class="button standard_bold_font" value="Next">
        </td>
      </tr>
    </table>
  </form>
  </div>
</div>
<%@include file="footer.jsp" %>
