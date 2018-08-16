window.onpopstate = function (e) {
    var res = localStorage.getItem('data');
    success(res);
}

function validateUsername() {
    // get username data
    let username = $("#username").val();
    dataString = "username=" + username;
    //make the AJAX request, dataType is set to json
    // meaning we are expecting JSON data in response from the server
    $.ajax({
        method: "POST",
        url: contextPath + "/validate_username",
        data: dataString,
        cache: false,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
            localStorage.setItem("data", data);
            success(data);
            // username taken

        },

        //if there was no response from the server
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("something really bad happened " + textStatus);
            $("#ajaxResponse").html(jqXHR.responseText);
        },
    });
    let username_error = $("b#username_error").text();
    if (username_error == "Username is taken. Select another.") {
        return false;
    }
    else {
        return true;
    }
}

function success(data) {
    let a = "hello";
    if (!data.success) {
        $("#username_error").html("<b class='warning'>Username is taken. Select another.</b>");
    }
    else {
        $("#username_error").html("Username is available");
    }
}


function validateForm() {
    let username = document.forms["signup_form"]["username"].value;
    let isValidUsername = validateUsername();
    let password = document.forms["signup_form"]["password"].value;
    let confirm_password = document.forms["signup_form"]["confirm_password"].value;

    const form_errors_message = "Fix the following errors before resubmitting this form:";
    const username_error = "&#9658 Username is required.";
    const taken_username_error = "&#9658 Username is taken. Select another.";
    const password_length_error = "&#9658 Password must be at least 8 characters.";
    const password_match_error = "&#9658 Password field must match Confirm Password field.";
    let form_errors = "";
    if (!username) {
        form_errors = form_errors + username_error + "<br />";
    }
    if (!isValidUsername) {
        form_errors = form_errors + taken_username_error + "<br />";
    }
    if (password.length < 8) {
        form_errors = form_errors + password_length_error + "<br />";
    }
    if (password != confirm_password) {
        form_errors = form_errors +password_match_error + "<br />";
    }

    if (form_errors) {
        form_errors = form_errors_message + "<br />" + form_errors;
        document.getElementById("form_errors").innerHTML = form_errors;
        return false;
    }
}



    function validatePasswordsMatch() {
        let password = document.getElementById("password");
        let confirm_password = document.getElementById("confirm_password");
        const no_match_error = "Passwords do not match.";
        let password_errors = [];
        if (password.value != confirm_password.value) {
            password_errors.push(no_match_error)
        }
        else {
            if (password_errors.includes(no_match_error)) {
                let index = password_errors.indexOf(this.no_match_error);
                password_errors.splice(index, 1)
            }
        }
        getPasswordErrors(password_errors);
        return password_errors;
    }

    function validatePasswordLength() {
        password_errors = validatePasswordsMatch();
        const bad_length = "The password must be at least 8 characters.";
        if (password.value.length < 8) {
            password_errors.push(bad_length)
        }
        else {
            if (password_errors.includes(bad_length)) {
                let index = password_errors.indexOf(this.bad_length);
                password_errors.splice(index, 1);
            }
        }
        getPasswordErrors(password_errors)
    }

    function getPasswordErrors(password_errors) {
        password_errors_string = password_errors.join(" ");
        document.getElementById("password_error").innerHTML = password_errors_string;
    }

    function validateWeight() {
        let weight = document.getElementById("signup_weight");
        let weight_error_message = "Weight must be at least 50 pounds.";
        if (weight.value < 50) {
            document.getElementById("signup_weight_error").innerHTML = weight_error_message;
            return "&#9658 " + weight_error_message + "<br />";
        }
        else {
            document.getElementById("signup_weight_error").innerHTML = "";
            return "";
        }
    }

    function validateBirthDate() {
        let birthDate = document.getElementById("signup_birthdate");
        let validBirthDate = "";
        let firstValidDate = new Date('1900-01-01');
        let dayCount = -365 * 5;
        let today = new Date();
        let lastValidDate = new Date(today.getTime() + dayCount * 24 * 60 * 60 * 1000);
        let options = {
            year: "numeric", month: "long", day: "numeric"
        };
        let lastValidDateString = lastValidDate.toLocaleDateString("en-us", options);
        let birthday_error_message =
                "Must be a valid birthdate between January 1, 1900 and " + lastValidDateString + ".";
        validBirthDate = Date.parse(birthDate.value);
        if (validBirthDate > firstValidDate && validBirthDate <= lastValidDate) {
            document.getElementById("signup_birthdate_error").innerHTML = "";
            return "";
        }
        else {
            document.getElementById("signup_birthdate_error").innerHTML = birthday_error_message;
            return "&#9658 " + birthday_error_message + "<br />";
        }
    }

    function validateSignUpPg2() {
        let form_errors_message = "Fix the following errors before resubmitting this form:<br />";
        let weight_error_message = validateWeight();
        let birthday_error_message = validateBirthDate();
        form_errors_message = form_errors_message + weight_error_message + birthday_error_message;
        if (weight_error_message || birthday_error_message) {
            document.getElementById("signup_pg2_form_errors").innerHTML = form_errors_message;
            return false;
        }
    }



