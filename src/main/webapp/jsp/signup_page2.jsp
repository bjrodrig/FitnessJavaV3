<%--
  Created by IntelliJ IDEA.
  User: barodriguez
  Date: 12/21/2017
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="../js/signup.js"></script>

<%@include file="header.jsp" %>
<div class="box background_pale centered form">
  <span id="signup_pg2_title" class="title">Sign Up - Continued</span>
  <br><br>
  <span id="signup_pg2_completion_warning" class="medium_font red">
    If you leave this page without completing the form, your account
    will not be created.
  </span>
  <br><br>
  <div class="medium_font standard_bold_font">
    <form id="signup_pg2_form"
          action="${pageContext.request.contextPath}/signup_pg2"
          onsubmit="return validateSignUpPg2()"
          method="post">
      <span id="signup_pg2_form_errors" class="warning small_font"></span>
      <span id="signup_pg2_help" class="help_text">
          All fields are required. Submitting the form without completing all fields may cause you to lose your data.
      </span>
      <table id="signup_table_pg2">
        <tr><td>Height:</td>
          <td>Ft:
          <select name="heightFt" required>
            <option value="1" >1</option>
            <option value="2" >2</option>
            <option value="3" >3</option>
            <option value="4" >4</option>
            <option value="5" >5</option>
            <option value="6" >6</option>
            <option value="7" >7</option>
            <option value="8" >8</option>
          </select>

            In:
           <select name="heightIn" required>
            <option value="0" >0</option>
            <option value="1" >1</option>
            <option value="2" >2</option>
            <option value="3" >3</option>
            <option value="4" >4</option>
            <option value="5" >5</option>
            <option value="6" >6</option>
            <option value="7" >7</option>
            <option value="8" >8</option>
            <option value="9" >9</option>
            <option value="10" >10</option>
            <option value="11" >11</option>
           </select>
          </td>
        </tr>
        <tr>
          <td>Weight:</td>
          <td>
            <input type="number" name="weight" id="signup_weight" required onkeyup="validateWeight()">
            <p id="signup_weight_error" class="warning"></p>
          </td>
        </tr>
        <tr>
          <td>
            Birthdate:
          </td>
          <td>
            <input type="date" id="signup_birthdate" name="birthdate" required onkeyup="validateBirthDate()">
            <p id="signup_birthdate_error" class="warning"></p>
          </td>
        </tr>
        <tr>
          <td>
            Gender:
          </td>
          <td>
            <input id="signup_male" type="radio" name="gender" value="M" required>Male
            <input id="signup_female" type="radio" name="gender" value="F" required>Female
          </td>
      </tr>
        <tr>
          <td>
            Lifestyle:
          </td>
          <td>
            <input id="signup_sedentary" class="two_px_padding" type="radio" name="lifestyle" value="S" required />
              Sedentary: Spend most of the day sitting
            (e.g. desk job)
            <br />
            <input id="signup_lightly_active" class="two_px_padding" type="radio" name="lifestyle" value="LA"
                   required />
              Ligthly Active: Spend a good portion fo the day on your feet

            (e.g. teacher, salesperson)
            <br />
            <input id="signup_active" class="two_px_padding" type="radio" name="lifestyle" value="A" required />
              Active: Spend a good portion of the day doing some physical activity
            (e.g. food server, postal carrier)
            <br />
            <input id="signup_very_active" class="two_px_padding" type="radio" name="lifestyle" value="VA" required />
              Very Active: Spend most of the day doing heavy physical activity
            (e.g. bike messenger, carpenter)
            <br />
          </td>
        </tr>
        <tr>
          <td>
            Weight Goals:
          </td>
          <td>
            <select name="weightGoals" required>
              <option value="L2">Lose 2 pounds per week</option>
              <option value="L1">Lose 1 pound per week</option>
              <option value="L.5">Lose .5 pounds per week</option>
              <option value="M">Lose 0 pounds per week</option>
              <option value="G.5">Gain .5 pounds per week</option>
              <option value="G1">Gain 1 pound per week</option>
            </select>
          </td>
        </tr>
        <tr>
          <td>
            <input type="hidden" name="username" value="<c:out value="${sessionScope.username}" />" /></td>
          <td>

          <input type="hidden" name="hashedPassword" value="<c:out value="${sessionScope.hashedPassword}" />"  />
        </td>

        </tr>
        <tr>
          <td>
            <input id="signup_submit" name="signup_submit" type="submit" class="button standard_bold_font"
                   value="Submit">
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
<%@include file="footer.jsp" %>
