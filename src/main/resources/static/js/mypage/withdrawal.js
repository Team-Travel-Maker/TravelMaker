const $btn = $('.leave-agree-btn');
console.log($btn);

$('.leave-close-btn').on('click', function () {
    window.history.back();
})


$(document).ready(function() {
    $('.leave-agree-btn').on('click', function () {
        $.ajax({
            type: "DELETE", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/withdrawal",
            dataType: "text",
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (result) {
                console.log("통신성공" + "OK");
                console.log(result);
                showWarnModal("정상적으로 회원 탈퇴 되었습니다.")
                $(".modal").on("click", function () {
                    window.location.href = result;
                })
            }
        });
    })
});

$("input[name=check]").click(function() {
    var total = $("input[name=check]").length;
    var checked = $("input[name=check]:checked").length;
    console.log(checked);
    $btn.attr("disabled",'disabled');
    if(checked==2){
        $btn.removeAttr("disabled")
    }
});