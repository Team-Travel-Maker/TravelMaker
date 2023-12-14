$modifyBtn = $(".modify-btn-li");

$(document).ready(function () {
    $modifyBtn.prop("disabled", false);


    //수정하기 버튼 색 들어오게하기
    $('.AutoTextarea_AutoTextarea').on('input',function(){

        const write1 = $('.AutoTextarea_AutoTextarea').eq(0).val().length;
        const write2 = $('.AutoTextarea_AutoTextarea').eq(2).val().length;
        if(write1 && write2){
            $modifyBtn.css('backgroundColor','#36f');
            $modifyBtn.css('color','#fff');
            $modifyBtn.prop("disabled", false);
        }else if(!write1 || !write2){
            $modifyBtn.css('backgroundColor', '');
            $modifyBtn.css('color', '');
        }
    });

    $("button.modify-btn-li").on("click", function () {
        let communityType = $("#communityType option:selected").val();
        $("#cType").val(communityType);
        $("#write").submit();
    })


})