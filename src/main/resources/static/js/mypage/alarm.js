$(document).ready(function() {
    function setAlarm(settingValue, alarm) {
        $.ajax({
            type: "PUT", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/alarm?settingValue=" + settingValue + "&alarm=" + alarm,
            dataType: "text",
            async: false,
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (result) {
                console.log("통신성공" + "OK");
                console.log(result);
                //window.location.href = result;
            }
        });
    }

    $("#toggle1").click(function() {
        if ($("#toggle1").prop("checked")) {
            setAlarm(1, "emailBenefitEvent")
        } else {
            setAlarm(0, "emailBenefitEvent")
        }
    });

    $("#toggle2").click(function() {
        if ($("#toggle2").prop("checked")) {
            setAlarm(1, "emailSuggestion")
        } else {
            setAlarm(0, "emailSuggestion")
        }
    });

    $("#toggle3").click(function() {
        if ($("#toggle3").prop("checked")) {
            setAlarm(1, "snsBenefitEvent")
        } else {
            setAlarm(0, "snsBenefitEvent")
        }
    });
});