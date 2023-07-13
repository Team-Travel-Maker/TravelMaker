// 모달
const modal = $("div.modalOpen");

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