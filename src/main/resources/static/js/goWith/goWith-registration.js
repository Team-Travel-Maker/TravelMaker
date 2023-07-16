// 모달
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
    console.log('현재 눌린 키의 번호는 : '+ evt.which); //눌린키의 번호를 알수있음
    if(evt.which=='27'){
        // $lightbox.hide();
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
    $(".tagPanel-scroll").children().css("display", "none");

    // MBTI 선택
    $('.modal-btn.mbti-btn').click(function() {
        let selectedMBTI = $(this).val();
        $('.tagPanel-selectedTag').children('span:first-child').text(selectedMBTI);
        $(".tagPanel-scroll").children().css("display", "flex");
        $(".tagPanel-placeholder").hide();
        $('.modal').hide();
    });

    // MBTI 선택 취소
    $('.selectedTag-deletedBtn').click(function() {
        let selectedMBTI = $(this).val("");
        $('.tagPanel-selectedTag').children('span:first-child').text(selectedMBTI);
        $(".tagPanel-placeholder").show();
        $(".tagPanel-scroll").children().css("display", "none");
    });



});



