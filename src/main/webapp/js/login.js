console.log("hello");
$(document).ready(function() {
    $("#login_form").submit((function (event) {
        event.preventDefault();
        let form = this;
        // get username and password data
        let username = $("#login_username").val();
        let password = $("#login_password").val();
        let dataString = "username=" + username + "&password=" + password;
        //make the AJAX request, dataType is set to json
        // meaning we are expecting JSON data in response from the server
        $.ajax({
            method: "POST",
            url: contextPath + "/validate_login",
            data: dataString,
            cache: false,
            dataType: "json",

            //if received a response from the server
            success: function (data, textStatus, jqXHR, response) {
                localStorage.setItem("data", data);
                if (!data.success) {
                    $("#loginAjaxResponse").html("<b class='warning'>Username and password combination is not valid.</b>");
                    this.break;
                }
                else {
                    form.submit();
                }
            },

            //if there was no response from the server
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("something really bad happened " + textStatus);
                $("#loginAjaxResponse").html(jqXHR.responseText);
            },
        });

    })
    )});


