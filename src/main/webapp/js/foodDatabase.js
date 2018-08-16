console.log("hello");
$(document).ready(function() {
    $("#addFoodToDatabaseForm").submit((function (event) {
        event.preventDefault();
        let form = this;
        let foodName = $("#addFoodDatabaseFoodName").val();
        let dataString = "foodName=" + foodName;
        $.ajax({
            method: "POST",
            url: contextPath + "/addFoodToDatabase",
            data: dataString,
            cache: false,
            dataType: "json",

            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                localStorage.setItem("data", data);
                if (!data.success) {
                    $("#foodNameError").html("<b class='warning'>Food name already in database. Select another.</b>");

                    this.break;
                }
                else {
                    form.submit();
                }
            },

            //if there was no response from the server
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("something really bad happened " + textStatus);
                $("#ajaxResponse").html(jqXHR.responseText);
            }
        });
    }))

});



function validateFoodName() {
    let foodName = $("#addFoodDatabaseFoodName").val();
    let dataString = "foodName=" + foodName;
    //make the AJAX request, dataType is set to json
    // meaning we are expecting JSON data in response from the server
    $.ajax({
        method: "POST",
        url: contextPath + "/addFoodToDatabase",
        data: dataString,
        cache: false,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
            localStorage.setItem("data", data);
            if (!data.success) {
                $("#foodNameError").html("<b class='warning'>Food name already in database. Select another.</b>");
            }
            else {
                $("#foodNameError").html("<b>Food Name is available</b>");
            }

        },

        //if there was no response from the server
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("something really bad happened " + textStatus);
            $("#ajaxResponse").html(jqXHR.responseText);
        },
    });

}


