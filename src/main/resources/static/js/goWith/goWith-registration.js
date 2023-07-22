// 모달 띄우기
const modal = $("button.modalOpen");

modal.on("click", e => {
    console.log("클릭됐냐?")
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
$(document).on('keyup',function(evt){
    console.log('현재 눌린 키의 번호는 : '+ evt.which); //눌린 키의 번호 확인
    if(evt.which=='27'){
        $("div.modal").hide();
    }else{

    }
});


// 모달 바깥 부분 클릭 시 모달 닫힘
$(document).ready(function() {
    $('.modal').click(function(event) {
        if ($(event.target).hasClass('modal') || $(event.target).attr('id') === 'modal-wrap') {
            $('.modal').hide();
        }
    });
});



$(document).ready(function() {
    // 선택된 태그 취소 버튼 감춰놓기!
    $(".tagPanel-localScroll").children().css("display", "none");
    $(".tagPanel-mbtiScroll").children().css("display", "none");


    // 선택 될 태그의 값을 변수로 사용하기 위해 선언
    let selectedLocal = $('.local-btn').click(function (e) {
        e.val();
        console.log(e.val());
    })
    let selectedMBTI = $('.mbti-btn').val();

    // 여행 지역 및 MBTI 선택
    $('.Button-submit').click(function(){
        $('.tagPanel-selectedLocal').children('span:first-child').text(selectedLocal);
        $(".tagPanel-localScroll").children().css("display", "flex");
        $('.tagPanel-selectedMBTI').children('span:first-child').text(selectedMBTI);
        $(".tagPanel-mbtiScroll").children().css("display", "flex");
        if(selectedMBTI != null && selectedLocal != null){
            $(".tagPanel-placeholder").hide();
            $('.modal').hide();
        }
    });


    // // MBTI 선택
    // $('.modal-btn.mbti-btn').click(function() {
    //     let selectedMBTI = $(this).val();
    //     if(selectedMBTI != null) {
    //         $('.tagPanel-selectedMBTI').children('span:first-child').text(selectedMBTI);
    //         $(".tagPanel-mbtiScroll").children().css("display", "flex");
    //         $(".tagPanel-placeholder").hide();
    //         $('.modal').hide();
    //     }
    // });
    //
    //
    // 여행 지역 선택 취소
    $('.selectedLocal-deletedBtn').click(function() {
        let selectedLocal = $(this).val("");
        $('.tagPanel-selectedLocal').children('span:first-child').text(selectedLocal);
        $(".tagPanel-placeholder").show();
        $(".tagPanel-mbtiScroll").children().css("display", "none");
    });

    // MBTI 선택 취소
    $('.selectedMBTI-deletedBtn').click(function() {
        let selectedMBTI = $(this).val("");
        $('.tagPanel-selectedMBTI').children('span:first-child').text(selectedMBTI);
        $(".tagPanel-placeholder").show();
        $(".tagPanel-LocalScroll").children().css("display", "none");
    });



});



