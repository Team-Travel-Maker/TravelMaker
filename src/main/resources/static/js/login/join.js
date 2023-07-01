// 이름 입력 확인 flg
let $nameFlg = 0;

// 이름 입력란
const $nameInput = $('#join-name');

// 회원 타입 버튼들
const $memberTypeBtns = $('.join-member-type-button');

// 나라 번호 select
const $countryNmSelect = $('.find_select_country');

// 휴대폰 입력란
const $phoneInput = $('#mobile');

// 휴대폰 유효성 플래그
let $phoneFlg = 0;

// 휴대폰 에러 표시 p태그
const $phoneErr = $('.find_phone_format_error_p');

// 인증번호 받기 버튼
const $phoneChkBtn = $('#phone-check-button')

// 인증 번호 플래그 (정상적으로 번호 인증이 완료 되었을 때 1로 변경 예정)
let $chkCodeFlg = 0;

// 인증 번호 입력란
const $chkCode = $('#check-code');

// 인증 하기 버튼
const $codeChkBtn = $('.find_check_num_button');

//이름이 입력 되어 있으면 이름 입력 확인 flg
$nameInput.on("input", function () {
    console.log("이름 입력중")
    if ($nameInput.val().length > 0) {
        $nameFlg = 1;
    } else {
        $nameFlg = 0;
    }
})

// 타입 버튼 이벤트
$memberTypeBtns.on("click", function(){
    console.log("들어옴")

    $memberTypeBtns.removeClass("is-selected");

    if(!$(this).hasClass("is-selected")){
        $(this).addClass("is-selected")
    }

})

// 나라 번호가 바뀔 때 마다 휴대폰 관련 태그 초기화
$countryNmSelect.on('change', function () {
    console.log("나라 번호 바뀜")
    $phoneFlg = 0;
    $phoneInput.val('');
    $("#phone-check-button-span").html("인증번호 받기");
    $phoneChkBtn.attr("disabled", true);
    $chkCode.val('');
    $chkCode.attr("readonly", true);
    $phoneChkBtn.attr("disabled", true);
    $codeChkBtn.attr("disabled", true);

})

// 휴대폰 입력과 동시에 유효성 검사 및 입력 테두리 변경
$phoneInput.on('input',function() {
    console.log("들어옴2")

    // 번호 수정시 버튼 및 입력란 초기화
    $("#phone-check-button-span").html("인증번호 받기");
    $phoneChkBtn.attr("disabled", true);
    $chkCode.val('');
    $chkCode.attr("readonly", true);
    $phoneChkBtn.attr("disabled", true);
    $codeChkBtn.attr("disabled", true);

    let numericVal = $(this).val().replace(/[^0-9]/g, '');

    if (numericVal.length > 11) {
        numericVal = numericVal.substr(0, 11); // 최대 11자리까지만 유지
    }

    $(this).val(numericVal);

    if(!fn_mbtlnumChk($phoneInput.val()) && numericVal.length != 0){ //번호의 유효성이 잘못 되었거나 1자 이상 10자 이하로 입력되어 있을 경우
        //$phoneInput.css("border-color","red");
        $phoneErr.show();
        $phoneChkBtn.attr("disabled", true);
        $phoneFlg = 0;
    } else if (numericVal.length === 0){ // 아무것도 입력되어 있지 않은 경우
        //$phoneInput.css("border-color","blue");
        $phoneErr.hide();
        $phoneChkBtn.attr("disabled", true);
        $phoneFlg = 0;
    } else { // 번호가 유효성에 맞게 입력되어 있는 경우
        //$phoneInput.css("border-color","blue");
        $phoneErr.hide();
        $phoneChkBtn.attr("disabled", false);
        $phoneFlg = 1;
    }
});

// 휴대폰 정규식
function fn_mbtlnumChk(mbtlnum){
    var regExp = /^010\d{8}$/;
    if(!regExp.test(mbtlnum)){

        return false;
    }
    return true;
}

// 인증 번호 눌렀을 때 함수
$phoneChkBtn.on("click", function(){
    console.log("인증번호 버튼 누름");
    // 인증번호 재전송으로 바뀌고 버튼 비활성화 됨
    $("#phone-check-button-span").html("인증번호 재전송");
    $phoneChkBtn.attr("disabled", true);
    $chkCode.attr("readonly", false);
    $chkCode.focus();
})

// 인증번호 받기 Or 인증번호 재전송 버튼 누른후 30초 뒤에 버튼 활성화 시키기

// 인증 번호 입력란 유효성 검사
$chkCode.on('input', function () {
    let numericVal = $(this).val().replace(/[^0-9]/g, '');

    if (numericVal.length > 4) {
        numericVal = numericVal.substr(0, 4); // 최대 11자리까지만 유지
    }

    if (numericVal.length === 4) {
        $codeChkBtn.attr("disabled", false);
    } else {
        $codeChkBtn.attr("disabled", true);
    }

    $(this).val(numericVal);
});

// 우편 api
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }


            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zip-code').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("address-detail").focus();
        }
    }).open();
}



//jqury

// $memberTypeBtns.css("color", "red");
// $memberTypeBtns.attr("disabled", true);
//


let idFlag = false;
let passwordFlag = false;

function flagCheck() {
    if(passwordFlag && idFlag ){
}

}
