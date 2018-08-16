<%--
  Created by IntelliJ IDEA.
  User: barodriguez
  Date: 1/15/2018
  Time: 4:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>

<div class="box background_pale centered form">
  <h3>Add Food to Food Diary</h3>
  <table id="FoodDatabaseTable">
    <tr>
      <th>Food Name</th>
      <th>Serving Size</th>
      <th>Serving Unit</th>
      <th>Calories</th>
    </tr>
    <c:forEach var="item" items="${sessionScope.foodDatabaseItems}" varStatus="loop">
      <tr>
        <td>${item.foodName}</td>
        <td>${item.servingSize}</td>
        <td>${item.servingUnit}</td>
        <td>${item.calories}</td>
        <td>
          <form action="${pageContext.request.contextPath}/displayFoodDatabase" method="post">
            <input type="hidden" name="foodItemIndex" value="<c:out value='${item.foodName}'/>">
            <input type="hidden" name="meal" value="${sessionScope.meal}">
            <input type="hidden" name="today" value="${sessionScope.today}">
            <input type="hidden" name="username" value="${sessionScope.username}">
            <input type="hidden" name="isFoodItemSelected" value="true">
            <input type="submit" value="Select" class="button">
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>

  <c:choose>
    <c:when test="${sessionScope.isFoodItemSelected == 'false'}">

    </c:when>
    <c:otherwise>
      <c:forEach var="item" items="${sessionScope.foodItem}" varStatus="loop">
      <form action="${pageContext.request.contextPath}/foodDiary" method="post">
        <b>${item[foodName]} </b>
        <br>
        <input type="number" min=".01" step="0.01" name="unitsConsumed" required>  servings of
          ${item.servingSize} ${item.servingUnit}(s)
        for <select name="meal">
        <c:forEach var="mealItem" items="${sessionScope.meals}" varStatus="loop">
          <c:choose>
            <c:when test="${mealItem == sessionScope.meal}">
              <option value="${mealItem}" selected="selected" class="button">${mealItem}</option>
            </c:when>
            <c:otherwise>
              <option value="${mealItem}" class="button">${mealItem}</option>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </select><br>
        <input type="hidden" name="foodItem" value="${item.foodName}">
        <input type="submit" class="button">
      </form>
      </c:forEach>


    </c:otherwise>
  </c:choose>
  Can't find what you're looking for?
  <a href="${pageContext.request.contextPath}/addFoodToDatabase?username=${sessionScope.username}">
    Add Food to the database
  </a>
</div>

<%@include file="footer.jsp" %>
