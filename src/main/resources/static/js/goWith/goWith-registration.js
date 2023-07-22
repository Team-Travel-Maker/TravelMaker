// 태그 선택 버튼 전역 변수 선언
const selectedLocal = $('.local-btn');
const selectedMBTI = $('.mbti-btn');

let selectedLocalVal = '';
let selectedMBTIVal = '';

// 모달 띄우기
const modal = $("button.modalOpen");

modal.on("click", e => {
    console.log("짜잔! 모달 나타남!")
    $("div.modal").css("display", "flex")
})


let modalCheck;
function showWarnModal(modalMessage){
    modalCheck = false;
    $("div#modal-wrap").html(modalMessage)
    $("div.warn-modal").css("animation", "popUp 0.5s");
    $("div.modal").css("display", "flex").hide().fadeIn(500);
    setTimeout(function(){modalCheck = true;}, 500);

}

$("div.modal").on("click", function(){
    if(modalCheck){
        $("div.warn-modal").css("animation", "popDown 0.5s");
        $("div.modal").fadeOut(500);
    }
});


//esc 키를 이용한 닫기 설정 - keyup이벤트 : 키보드를 누르고 뗄떼 발생
$(document).on('keyup',function(e){
    console.log('현재 눌린 키의 번호는 : '+ e.which); //눌린 키의 번호 확인
    if(e.which == '27'){
        $("div.modal").hide();
    }
});


// 모달 바깥 부분 클릭 시 모달 닫힘
$(document).ready(function() {
    $('.modal').click(function(e) {
        if ($(e.target).hasClass('modal') || $(e.target).attr('id') === 'modal-wrap') {
            $('.modal').hide();
        }
    });
});




// if(selectedLocal.val() == null || selectedMBTI.val() == null){
//     $('.Button-submit').disabled();
// } else if(selectedLocal.val() != null && selectedMBTI.val() != null){
//     $('.Button-submit').on('click', function () {
//         $('.tagPanel-selectedLocal').children('span:first-child').text(selectedLocal.val());
//         $(".tagPanel-localScroll").children().css("display", "flex");
//         $('.tagPanel-selectedMBTI').children('span:first-child').text(selectedMBTI.val());
//         $(".tagPanel-mbtiScroll").children().css("display", "flex");
//         $(".tagPanel-placeholder").hide();
//         $('.modal').hide();
//     })
// }

// 완료 버튼 변수 선언
const submitBtn = $('.Button-submit');

$(document).ready(function() {
    // 선택된 태그 취소 버튼 감춰놓기!
    $(".tagPanel-localScroll").children().css("display", "none");
    $(".tagPanel-mbtiScroll").children().css("display", "none");

    // 태그 선택 전에는 완료 버튼 비활성화
    if(selectedLocal.val() == null || selectedMBTI.val() == null){
        submitBtn.disabled();
    } else if(selectedLocal.val() != null || selectedMBTI.val() != null){
        submitBtn.addClass('.onSubmit');
    }

    // 여행 지역 선택
    selectedLocal.on('click', function () {
        selectedLocal.each( (e) => {
            selectedLocal.removeClass('selected');
        });
        $(this).addClass('selected');
        selectedLocalVal = $(this).val();
        $('.tagPanel-selectedLocal').children('span:first-child').text(selectedLocalVal);
        console.log($(this).val());
        console.log(selectedLocalVal);
    });

// MBTI 선택
    selectedMBTI.on('click', function () {
        selectedMBTI.each( (e) => {
            selectedMBTI.removeClass('selected');
        });
        $(this).addClass('selected');
        selectedMBTIVal = $(this).val();
        $('.tagPanel-selectedMBTI').children('span:first-child').text(selectedMBTIVal);
        console.log($(this).val());
        console.log(selectedMBTIVal);
    });


    console.log("선택한 지역 : " + selectedLocalVal);
    console.log("선택한 MBTI : " + selectedMBTIVal);

    submitBtn.on('click', function () {
        if(selectedLocalVal == '' || selectedMBTIVal == ''){
            submitBtn.attr("disabled", true);
            submitBtn.parent().removeClass("btn-hover");
            console.log("왜 호버 안없어져?")
        } else if (selectedLocalVal != '' && selectedMBTIVal != ''){
            console.log("데이터 가져옴")
            $('.tagPanel-selectedLocal').children('span:first-child').text(selectedLocalVal);
            $('.tagPanel-selectedMBTI').children('span:first-child').text(selectedMBTIVal);
            $(".tagPanel-placeholder").hide();
            $('.modal').hide();
            console.log("선택한 지역 : " + selectedLocalVal);
            console.log("선택한 MBTI : " + selectedMBTIVal);
        }
    })



    // 여행 지역 선택 취소
    $('.selectedLocal-deletedBtn').click(function() {
        $('.tagPanel-selectedLocal').children('span:first-child').text('');
        $(".tagPanel-placeholder").show();
        $(".tagPanel-mbtiScroll").children().css("display", "none");
    });

    // MBTI 선택 취소
    $('.selectedMBTI-deletedBtn').click(function() {
        $('.tagPanel-selectedMBTI').children('span:first-child').text('');
        $(".tagPanel-placeholder").show();
        $(".tagPanel-LocalScroll").children().css("display", "none");
    });



});



