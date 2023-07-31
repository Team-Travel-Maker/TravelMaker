// 태그 선택 버튼 전역 변수 선언
const selectedLocal = $('.local-btn');
const selectedMBTI = $('.mbti-btn');


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

// 처음 페이지가 로딩되었을 때 선택 된 데이터가 없게 선언
let selectedLocalVal = '';
let selectedMBTIVal = '';

// 태그 선택 체크
let localCheck = false;
let mbtiCheck = false;

// 완료 버튼 변수 선언
const submitBtn = $('.Button-submit');

// 글 제목, 내용 체크
let titleCheck = false;
let contentCheck = false;

// 등록하기 버튼 변수 선언
const btn = $('.btn-toggle');


$(document).ready(function() {


    // 태그 취소 버튼 감춰놓기!
    $(".tagPanel-localScroll").children().css("display", "none");
    $(".tagPanel-mbtiScroll").children().css("display", "none");

    // selectedLocalVal == '' && selectedMBTIVal == '' ? $('.submit-wrap').removeClass('btn-hover') : submitBtn.addClass('onSubmit');



// MBTI 선택
    selectedMBTI.on('click', function () {
        selectedMBTI.each( (e) => {
            selectedMBTI.removeClass('selected');
        });
        $(this).addClass('selected');
        mbtiCheck = true;
        selectedMBTIVal = $(this).val();

        if(localCheck == true && mbtiCheck == true){
            $('.submit-wrap').addClass('btn-hover');
            submitBtn.addClass('onSubmit');
        }
        $('.tagPanel-selectedMBTI').children('span:first-child').text(selectedMBTIVal);
        console.log("선택한 MBTI : " + selectedMBTIVal);
        console.log(mbtiCheck.valueOf())
    });


    // 지역과 MBTI 태그 둘 다 선택하기 전에는 완료 버튼 비활성화 하기!
    if(localCheck == false && mbtiCheck == false){
        $('.submit-wrap').removeClass('btn-hover')

    }

    if(localCheck == true && mbtiCheck == true){
        $('.submit-wrap').addClass('btn-hover');
        submitBtn.addClass('onSubmit');
    }


    submitBtn.on('click', function () {
        if(selectedLocalVal == '' || selectedMBTIVal == ''){
            // submitBtn.attr("disabled", true);
            submitBtn.parent().removeClass("btn-hover");
            console.log("왜 호버 안없어져?")
        } else if (selectedLocalVal != '' && selectedMBTIVal != ''){
            console.log("데이터 가져옴")
            $('.tagPanel-selectedLocal').children('span:first-child').text(selectedLocalVal);
            $('.tagPanel-selectedMBTI').children('span:first-child').text(selectedMBTIVal);
            $(".tagPanel-localScroll").children().css("display", "flex");
            $(".tagPanel-mbtiScroll").children().css("display", "flex");
            $(".tagPanel-placeholder").hide();
            $('.modal').hide();
            console.log("선택한 지역2 : " + selectedLocalVal);
            console.log("선택한 MBTI2 : " + selectedMBTIVal);
        }
    })

    // 여행 지역 선택
    selectedLocal.on('click', function () {
        selectedLocal.each( (e) => {
            selectedLocal.removeClass('selected');
        });
        $(this).addClass('selected');
        localCheck = true;
        selectedLocalVal = $(this).val();

        if(localCheck == true && mbtiCheck == true){
            $('.submit-wrap').addClass('btn-hover');
            submitBtn.addClass('onSubmit');
            checkSubmitButton();
        }
        $('.tagPanel-selectedLocal').children('span:first-child').text(selectedLocalVal);
        console.log("선택한 지역 : " + selectedLocalVal);
        console.log(localCheck.valueOf())
    });

    // MBTI 선택
    selectedMBTI.on('click', function () {
        selectedMBTI.each( (e) => {
            selectedMBTI.removeClass('selected');
        });
        $(this).addClass('selected');
        mbtiCheck = true;
        selectedMBTIVal = $(this).val();

        if(localCheck == true && mbtiCheck == true){
            $('.submit-wrap').addClass('btn-hover');
            submitBtn.addClass('onSubmit');
            checkSubmitButton();
        }
        $('.tagPanel-selectedMBTI').children('span:first-child').text(selectedMBTIVal);
        console.log("선택한 MBTI : " + selectedMBTIVal);
        console.log(mbtiCheck.valueOf())
    });



    // 여행 지역 선택 취소
    $('.selectedLocal-deletedBtn').click(function() {
        $('.tagPanel-selectedLocal').children('span:first-child').text('');
        $(".local-placeholder").show();
        $(".tagPanel-localScroll").children().css("display", "none");
    });

    // MBTI 선택 취소
    $('.selectedMBTI-deletedBtn').click(function() {
        $('.tagPanel-selectedMBTI').children('span:first-child').text('');
        $(".mbti-placeholder").show();
        $(".tagPanel-mbtiScroll").children().css("display", "none");
    });


//    제목
    const titleText = $('.writePaper-title').children('textarea:first-child');
    let titleTextVal = titleText.val();

    console.log(titleText.val());


//    내용
    const contentText = $('.writePaper-content').children('textarea:first-child');
    let contnetTextVal = contentText.val();

    console.log(contentText.val());


    //등록하기 버튼 색 들어오게하기
    function checkSubmitButton() {
        let title = $('.AutoTextarea-textarea[name="title"]').val().trim();
        let content = $('.AutoTextarea-textarea[name="content"]').val().trim();


        // title과 content 둘 중 하나라도 비어있는 경우 버튼 비활성화
        if (title === '' || content === '' || localCheck === false || mbtiCheck === false) {
            $('.Button-btn').attr('disabled', true);
            // btn.css('backgroundColor', '');
            // btn.css('color', '');
            console.log("css 제거")
        } else {
            $('.Button-btn').attr('disabled', false);
            $('.registration-headerBtn').addClass('valid');
            console.log("css 추가")
        }
    }

    // 텍스트 입력 시 등록하기 버튼 상태 변경
    $('.AutoTextarea-textarea').on('input', function() {
        checkSubmitButton();
    });




    // 텍스트 영역의 높이를 자동으로 조절하는 함수
    function autoResizeTextarea() {
        // 모든 텍스트 영역 요소를 선택합니다
        $('.AutoTextarea-textarea').each(function() {
            let $this = $(this);

            // 스크롤 높이를 임시로 0으로 설정한 후, 스크롤 높이를 콘텐츠 높이에 맞게 조절합니다
            $this.css('height', 0);
            $this.css('height', $this[0].scrollHeight + 'px');
        });
    }

    // 페이지 로드 시와 텍스트 영역 내용이 변경될 때 자동으로 autoResizeTextarea 함수를 호출합니다
    autoResizeTextarea();
    $('.AutoTextarea-textarea').on('input', autoResizeTextarea);

    function autoCheckTag(){

    }




//    이미지 업로드
    $('.PhotoButton').click(function (e) {
        e.preventDefault();
        $('#upload-image').click();
    })













});


