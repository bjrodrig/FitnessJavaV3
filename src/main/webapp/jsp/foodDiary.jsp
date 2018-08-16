<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: barodriguez
  Date: 1/13/2018
  Time: 12:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>

<script src="../js/foodDiary.js"></script>

<div class="box background_pale centered form">
    <form method='POST' action="${pageContext.request.contextPath}/foodDiary"
          id='foodDiaryDateForm'>
      <span id="food_diary_title" class="small_font">Your Food Diary for: </span>
    <span id="foodDiaryToday">${sessionScope.foodDiaryJSP.todayString}</span>
    <input type="hidden" id="dp" />
    <input type="hidden" id="foodDiarySelectedDate" name="foodDiarySelectedDate" />
    <input type="hidden" name="reasonForPost" id="reasonForPost" value="Change Date" />
    <input type="submit" name="foodDiaryChangeDateSubmit" id="foodDiaryChangeDateSubmit" hidden>
    </form>

  <table>
    <tr>
      <td></td>
      <td></td>
      <td></td>
      <td style="text-align:center;">Calories</td>
    </tr>
    <tr>
      <td></td>
      <td></td>
      <td></td>
      <td style="text-align:center;"><i>kcal</i></td>
    </tr>

    <c:forEach var="item" items="${sessionScope.foodDiaryJSP.meals}" varStatus="loop">
      <tr>
        <td>
        <b><c:out value="${item}"/></b>
        </td>
      </tr>

      <c:forEach var="elem" items="${sessionScope.foodDiaryJSP.foodDiaryItems}">
        <c:if test = "${elem.meal == item}" >
          <tr>
            <td>
                ${elem.foodItem.foodName}
            </td>
            <td>
            </td>
            <td>
                ${elem.quantity} ${elem.foodItem.servingUnit}(s)
            </td>
            <td style="text-align:center;">
                ${elem.totalCalories}
            </td>
            <td>
              <a href=
                     "${pageContext.request.contextPath}/foodDiary?reasonForGet=delete&fooddiaryId=${elem.id}&
                     todayString=${sessionScope.foodDiaryJSP.todayString}">
                Delete
              </a>
            </td>
          </tr>
        </c:if>
      </c:forEach>



      <tr>
        <td>
          <form action="${pageContext.request.contextPath}/displayFoodDatabase" method="post">
            <input type="submit" class="button" value="Add Food" style="float:left;">
            <input type="hidden" name="meal" value="<c:out value='${item}'/>" >
            <input type="hidden" name="isFoodItemSelected" value="false">
            <input type="hidden" name="meals" value="${sessionScope.foodDiaryJSP.meals}">
            <input type="hidden" name="today" value="${sessionScope.foodDiaryJSP.dateAdded}">
            <input type="hidden" name="username" value="${sessionScope.foodDiaryJSP.username}">
          </form>
        </td>
        <td>
          <div class="dropdown${loop.index}">
            <button onclick="activateQuickToolsDropDown(${loop.index})" id="dropbtn${loop.index}"
                    class="dropbtn" style="float:left;">Quick Tools</button>
            <div id="myDropdown${loop.index}" class="dropdown-content${loop.index} dropdown-content">
              <a id="copyFromYesterdayLink${loop.index}" href="#" onclick="selectMealYesterdayDropdown(${loop.index})">
                Copy from Yesterday
              </a>
              <a id="copyToTomorrowLink${loop.index}" href="#" onclick="selectMealTomorrowDropdown(${loop.index})">
                Copy to Tomorrow
              </a>
            </div>
            <div id="copyYesterday${loop.index}"
                 class="copyYesterday${loop.index} dropdown-meal-content${loop.index} dropdown-meal-content">
              Copy from which meal?
              <a id="BreakfastYesterday${loop.index}"
                 href="${pageContext.request.contextPath}/foodDiary?reasonForGet=copyYesterday&fromMeal=Breakfast&toMeal=${item}">
                Breakfast
              </a>
              <a id="LunchYesterday${loop.index}"
                 href="${pageContext.request.contextPath}/foodDiary?reasonForGet=copyYesterday&fromMeal=Lunch&toMeal=${item}">
                Lunch
              </a>
              <a id="DinnerYesterday${loop.index}"
                 href="${pageContext.request.contextPath}/foodDiary?reasonForGet=copyYesterday&fromMeal=Dinner&toMeal=${item}">
                Dinner
              </a>
              <a id="SnacksYesterday${loop.index}"
                 href="${pageContext.request.contextPath}/foodDiary?reasonForGet=copyYesterday&fromMeal=Snacks&toMeal=${item}">
                Snacks
              </a>
            </div>
            <div id="copyTomorrow${loop.index}"
                 class="copyTomorrow${loop.index} dropdown-meal-content${loop.index} dropdown-meal-content">
              Copy to which meal?
              <a id="BreakfastTomorrow${loop.index}"
                 href="${pageContext.request.contextPath}/foodDiary?reasonForGet=copyTomorrow&fromMeal=${item}&toMeal=Breakfast">
                Breakfast
              </a>
              <a id="LunchTomorrow${loop.index}"
                 href="${pageContext.request.contextPath}/foodDiary?reasonForGet=copyTomorrow&fromMeal=${item}&toMeal=Lunch">
                Lunch
              </a>
              <a id="DinnerTomorrow${loop.index}"
                 href="${pageContext.request.contextPath}/foodDiary?reasonForGet=copyTomorrow&fromMeal=${item}&toMeal=Dinner">
                Dinner
              </a>
              <a id="SnacksTomorrow${loop.index}"
                 href="${pageContext.request.contextPath}/foodDiary?reasonForGet=copyTomorrow&fromMeal=${item}&toMeal=Snacks">
                Snacks
              </a>
          </div>
          </div>
        </td>
        <td></td>
        <td style="text-align: center;">
          <b>${sessionScope.foodDiaryJSP.caloriesPerMeal[loop.index]}</b>
        </td>
      </tr>
      <tr><td></td>   </tr>
      <tr><td></td>   </tr>
      <tr><td></td>   </tr>
    </c:forEach>
    <tr><td></td></td><td><b>Total Calories:</b></td><td></td><td
        style="text-align: center;"><b>${sessionScope.foodDiaryJSP.totalCaloriesForDay}</b></td></tr>
    <tr><td></td><td><b>Your Daily Goal:</b></td><td></td>
      <td style="text-align: center; font-weight: bold">
        ${sessionScope.foodDiaryJSP.userprofile.calorieGoalPerDay}
      </td>
    </tr>
    <tr>
      <td></td>
      <td>
        <b>Remaining:</b>
      </td>
      <td></td>
      <td>
        <c:set var="zero" value="${0.0}" />
        <c:choose>
          <c:when test="${sessionScope.foodDiaryJSP.caloriesRemaining > zero}">
            <span style="font-weight: bold; text-align: center;" class="green_font">
              ${sessionScope.foodDiaryJSP.caloriesRemaining}
            </span>
          </c:when>
          <c:otherwise>
            <span style="font-weight: bold; text-align: center;" class="red_font">
                ${sessionScope.foodDiaryJSP.caloriesRemaining}
            </span>
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
  </table>

  <br><br>

</div>


<%@include file="footer.jsp" %>
