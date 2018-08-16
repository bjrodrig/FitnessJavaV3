$(document).ready(function() {
    console.log("hello");
    $("#dp").datepicker({
        buttonImage: '../images/calendar_green.png',
        buttonImageOnly: true,
        changeMonth: true,
        changeYear: true,
        showOn: 'both',
        dateFormat: 'MM d, yy',
        onSelect: function(date) {
            let foodDiaryDate = $("#dp").datepicker({ dateFormat: 'MM d, yy' }).val();
            $("#foodDiaryToday").html(foodDiaryDate);
            $("#foodDiarySelectedDate").val(foodDiaryDate);
            let dataString = "reasonForPost=Change Date&foodDiarySelectedDate=" + foodDiaryDate;
            /*let formData =$("#foodDiaryDateForm").serialize();*/
            /*$.post(contextPath + "/foodDiary", formData, function(response) {*/

            /*});*/
            /*event.preventDefault();*/
            $.ajax({
                method: "POST",
                url: contextPath + "/changeDate",
                data: dataString,
                dataType: "json",
                /*contentType: "application/json",
                mineType: "application/json",*/

                success: function (data, textStatus, jqXHR, response) {
                    localStorage.setItem("data", data);
                    $("#reasonForPost").val("Change Date");
                    $("#foodDiarySelectedDate").val(data.todayString);
                    $("#foodDiaryDateForm")[0].submit();
                },

                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("something really bad happened " + textStatus);
                },
            });
    }});

});

/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function activateQuickToolsDropDown(id) {
    toggleDropDown(id);
}

function toggleDropDown(id) {
    let myDropdownClassList = document.getElementById("myDropdown" + id).classList;
    let copyYesterdayClassList = document.getElementById("copyYesterday" + id).classList;
    let copyTomorrowClassList = document.getElementById("copyTomorrow" + id).classList;
    if (!myDropdownClassList.contains("show") && !copyYesterdayClassList.contains("show") &&
            !copyTomorrowClassList.contains("show")) {
        myDropdownClassList.add("show");
    }
    else {
        myDropdownClassList.remove("show");
        copyYesterdayClassList.remove("show");
        copyTomorrowClassList.remove("show");
    }
}

function selectMealYesterdayDropdown(id) {
    let date = "Yesterday";
    selectMealDropdown(id, date);
}

function selectMealTomorrowDropdown(id) {
    let date = "Tomorrow";
    selectMealDropdown(id, date);
}

function selectMealDropdown(id, date) {
    let dropdownContentClass = "dropdown-content" + id;
    let dropdownLinks = document.getElementsByClassName(dropdownContentClass);
    let j;
    for (j = 0; j < dropdownLinks.length; j++) {
        let openDropdown = dropdownLinks[j];
        if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
        }
    }
    let copyDate = "copy" + date + id;
    dropdownLinks = document.getElementsByClassName(copyDate);
    let k;
    for (k = 0; k < dropdownLinks.length; k++) {
        openDropdown = dropdownLinks[k];
        if (!openDropdown.classList.contains("show")) {
            openDropdown.classList.add("show");
        }
    }
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
    if (!event.target.matches('#dropbtn0')) {
        closeDropdown(0);
    }
    if (!event.target.matches('#dropbtn1')) {
        closeDropdown(1);
    }
    if (!event.target.matches('#dropbtn2')) {
        closeDropdown(2);
    }
    if (!event.target.matches('#dropbtn3')) {
        closeDropdown(3);
    }

};

function closeDropdown(id) {
    let dropdowns = document.getElementsByClassName("dropdown-content" + id);
    let i;
    for (i = 0; i < dropdowns.length; i++) {
        let openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
        }
    }
    if (!event.target.matches('#copyFromYesterdayLink' + id) && !event.target.matches('#copyToTomorrowLink' + id)) {
        dropdowns = document.getElementsByClassName("dropdown-meal-content" + id);
        for (i = 0; i < dropdowns.length; i++) {
            let openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}
