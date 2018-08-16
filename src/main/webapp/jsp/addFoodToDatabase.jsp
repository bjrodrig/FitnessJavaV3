<%--
  Created by IntelliJ IDEA.
  User: barodriguez
  Date: 1/15/2018
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<script src="../js/foodDatabase.js"></script>

<div class="box background_pale centered form standard_bold_font">
  <h1>Add Food to Database</h1>
  <form id="addFoodToDatabaseForm" action="${pageContext.request.contextPath}/successfullyAddedFoodToDatabase"
        method="POST">
    <table>
      <tr id="foodNameFormError" class="warning">${sessionScope.foodNameFormError}</tr>
      <tr><td>Name of Food Item </td>
        <td>
          <input type="text" id="addFoodDatabaseFoodName" name="foodName" required
                   onblur="validateFoodName()">
        </td>
        <td id="foodNameError"></td>
      </tr>
      <tr><td>Serving Size      </td>
        <td><input type="number" step="0.01" min=".01" name="servingSize" required></td></tr>
      <tr><td>Serving unit</td>
        <td><input type="text" name="servingUnit" required></td>
        <td><i>i.e. grams, ounces, etc.</i></td></tr>
      <tr><td>Calories per serving size</td>
        <td><input type="number" min=".01" step=".01" name="calories" required></td></tr>
      <tr><td><input type="hidden" name="username" value="${sessionScope.username}">
      <tr><td>
      <tr><td><input type="submit" name="Add Food Item" class="button"></td></tr>
    </table>

  </form>
</div>

<%@include file="footer.jsp" %>