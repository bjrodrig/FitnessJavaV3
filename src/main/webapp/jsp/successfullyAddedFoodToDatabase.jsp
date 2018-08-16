<%--
  Created by IntelliJ IDEA.
  User: barodriguez
  Date: 1/15/2018
  Time: 9:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<div class="box background_pale centered form standard_bold_font">
  <h1>You have successfully added ${sessionScope.foodName} to the database.</h1>
  <br>
  <h3><a href="${pageContext.request.contextPath}/addFoodToDatabase?username=${sessionScope.username}">
    Add Another Food Item
  </a></h3><br>
  <h3><a href="${pageContext.request.contextPath}/foodDiary?username=${sessionScope.username}&reasonForGet='addedFood'">
    Back to Food Diary
  </a></h3>
  <br>
  <h3><a href="${pageContext.request.contextPath}/homepage">Back to Home Page</a></h3><br>
</div>




<%@include file="footer.jsp" %>