<%--
  Created by IntelliJ IDEA.
  User: barodriguez
  Date: 1/1/2018
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="../js/signup.js"></script>

<%@include file="header.jsp" %>
<div class="box background_pale centered form">
  <span class="small_font">
    <h1 id="display_goals_title">Your Suggested Fitness and Nutrition Goals</h1>
    <table width="700px">
            <tr>
                <th width="35%">Nutritional Goals</th>
                <th width="65%">Target</th>
            </tr>
            <tr>
                <td>Net Calories Consumed / Day*</td>
                <td style="text-align:center;">${sessionScope.userprofile.calorieGoalPerDay} Calories / Day </td>
            </tr>
        </table>
  </span>
  <br>
  <i class="fity_percent_font">Net Calories Consumed = Total Calories Consumed - Exercise Calories burned.
    So the more you exercise, the more you can eat.</i>
  <br><br>
  <p>If you follow this plan, your projected weight ${sessionScope.userprofile.gainOrLoss} is
    ${sessionScope.userprofile.changeInPoundsPerWeek} pound(s) per week. <br>
    You should ${sessionScope.userprofile.gainOrLose} ${sessionScope.userprofile.fiveWeekProjection} pound(s) by
    ${sessionScope.fiveWeeksFromNow}.</p>

  <form id="displayGoalsToHomeForm" action="userHomepage" method="POST">
    <input type="hidden" name="username" value="${sessionScope.userprofile.username}">
    <input type="submit" name="submit" value="Get Started" class="button standard_bold_font">


<%@include file="footer.jsp" %>
