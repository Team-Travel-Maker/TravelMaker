/* ------------------------ 플래그 변수 관련 ------------------------*/
// 가입하기 버튼 활성화를 위한 확인 flg들
// 이름 입력 확인 flg
let $nameFlg = 0;
// 핸드폰 인증완료 플래그
let $phoneFlg = 0;
// 인증 번호 플래그 (정상적으로 번호 인증이 완료 되었을 때 1로 변경 예정)
let $chkCodeFlg = 0;
// 비밀번호 유효성 확인 플래그
let $passChkFlg = 0;
// 만 14세 이상 동의 체크 플래그
let above14Flg = 0;
// 이용약관 동의 체크 플래그
let termsConditionsFlg = 0;
// 개인정보 수집 및 이용 체크 플래그
let collectInfoFlg = 0;


/* ------------------------ 태그 변수 관련 ------------------------*/
/* ---------- 이름 변수 ----------*/
// 이름 입력란
const $nameInput = $('#join-name');


/* ---------- 회원타입 변수 ----------*/
// 회원 타입 버튼들
const $memberTypeBtns = $('.join-member-type-button');


/* ---------- 인증번호 변수 ----------*/
// 나라 번호 select
const $countryNmSelect = $('.find-select-country');

// 휴대폰 입력란
const $phoneInput = $('#mobile');

// 휴대폰 에러 표시 p태그
const $phoneErr = $('.find-phone-format-error-p');

// 인증번호 받기 버튼
const $phoneChkBtn = $('#phone-check-button')

// 인증 번호 입력란
const $chkCode = $('#check-code');

// 인증 하기 버튼
const $codeChkBtn = $('.find-check-num-button');

// 인증 요청 문구
const $chkTimeInP = $('.find-check-timein-p');

// 인증 시간 표시
const $chkTimeInTimeP = $('.find-check-timein-time-p');

// 인증 시간 초과 문구
const $chkTimeOutP = $('.find-check-timeout-p');

// 인증 시간 0초 표시
const $chkTimeOutTimeP = $('.find-check-timeout-time-p');

// 인증 번호 실패 문구
const $chkCodeWrongP = $('.find-check-wrong-p');

// 인증 성공 문구
const $chkCodeOkP = $('.find-check-ok-p');

// 인증번호 테스트용 코드(테스트용이라서 나중에 삭제 필요)
let $testCheckCode = '1234';


/* ---------- 비밀번호 변수 ----------*/
// 비밀번호 입력란
const $newPassInput = $('.new-pass-input');

// 비밀번호 확인 입력란
const $newPassConfirmInput = $('.new-pass-confirm-input');

// 강도 관련 변수선언
// 강도 : 약함
const $securityLevelLow = $('.security-level-low');

//강도 : 보통
const $securityLevelNormal = $('.security-level-normal');

//강도 : 강함
const $securityLevelHigh = $('.security-level-high');

// 올바르지 않은 비밀번호 문구
const $newPassErrorP = $('#pass-format-wrong');

// 비밀번호, 비밀번호 확인 불일치 문구
const $newPassNotMatch = $('#pass-not-match');

// 비밀번호 사용 가능 문구
const $newPassAvailable = $('#pass-available')


/* ---------- 동의 변수 ----------*/
// 전체 동의 버튼
const $agreeAllChkBox = $('#is_agree_all');

// 만 14세 이상 동의 버튼
const $above14ChkBox = $('#is_above_14');

// 이용약관 동의 버튼
const $termsConditionsChkBox = $('#is_terms_conditions');

// 개인정보 수집 및 이용 동의 버튼
const $collectInfoChkBox = $('#is_collect_information');

// 맞춤 정보 받기 동의 버튼
const $acceptEventAllChkBox = $('#is_accept_event_all');


/* ---------- 마지막 가입하기 버튼 변수 ----------*/
// 모든 입력란 마다 버튼 플래그 확인 함수를 불러서 다 확인 완료이면 버튼이 활성화 될 수 있도록
const $joinBtn = $('.join-button');


/* ------------------------ 처리 관련 ------------------------*/
/* ---------- 이름 관련 처리 ----------*/
//이름이 입력 되어 있으면 이름 입력 확인 flg
$nameInput.on("input", function () {
    console.log("이름 입력중")
    if ($nameInput.val().length > 0) {
        $nameFlg = 1;
    } else {
        $nameFlg = 0;
    }
})

/* ---------- 타입 관련 처리 ----------*/
// 타입 버튼 이벤트
$memberTypeBtns.on("click", function(){
    console.log("들어옴")

    $memberTypeBtns.removeClass("is-selected");

    if(!$(this).hasClass("is-selected")){
        $(this).addClass("is-selected")
    }

})

/* ---------- 인증번호 관련 처리 ----------*/
/*타이머 구현*/
// 토큰 유효시간 타이머
let counter = 0;
let timeleft = 180; // 제한 시간 지정 값
let setinterval;

// 나라 번호가 바뀔 때 마다
$countryNmSelect.on('change', function () {
    //휴대폰 관련 태그 초기화
    console.log("나라 번호 바뀜")
    $phoneFlg = 0;
    $chkCodeFlg = 0;
    $phoneInput.val('');
    $("#phone-check-button-span").html("인증번호 받기");
    $phoneChkBtn.attr("disabled", true);
    $chkCode.val('');
    $chkCode.attr("disabled", true);
    $phoneChkBtn.attr("disabled", true);
    $codeChkBtn.attr("disabled", true);

    // 인증 관련 문구 초기화
    $chkTimeInP.hide();
    $chkTimeInTimeP.hide();
    $chkTimeOutP.hide();
    $chkTimeOutTimeP.hide();
    $chkCodeWrongP.hide();
    $chkCodeOkP.hide();
    // 인증시간 초기화
    clearInterval(setinterval);
    counter = 0;
})

// 휴대폰 입력과 동시에 유효성 검사 및 입력 테두리 변경
$phoneInput.on('input',function() {

    $phoneFlg = 0;
    $chkCodeFlg = 0;

    // 번호 수정시 버튼 및 입력란 초기화
    $("#phone-check-button-span").html("인증번호 받기");
    $phoneChkBtn.attr("disabled", true);
    $chkCode.val('');
    $chkCode.attr("disabled", true);
    $phoneChkBtn.attr("disabled", true);
    $codeChkBtn.attr("disabled", true);
    // 인증 관련 문구 초기화
    $chkTimeInP.hide();
    $chkTimeInTimeP.hide();
    $chkTimeOutP.hide();
    $chkTimeOutTimeP.hide();
    $chkCodeWrongP.hide();
    $chkCodeOkP.hide();
    //인증시간 초기화
    clearInterval(setinterval);
    counter = 0;

    let numericVal = $(this).val().replace(/[^0-9]/g, '');

    if (numericVal.length > 11) {
        numericVal = numericVal.substr(0, 11); // 최대 11자리까지만 유지
    }

    $(this).val(numericVal);

    if(!fn_mbtlnumChk($phoneInput.val()) && numericVal.length != 0){
        //번호의 유효성이 잘못 되었거나 1자 이상 10자 이하로 입력되어 있을 경우
        $phoneInput.removeClass('find-input');
        $phoneErr.show();
        $phoneChkBtn.attr("disabled", true);
    } else if (numericVal.length === 0){ // 아무것도 입력되어 있지 않은 경우
        $phoneInput.addClass('find-input');
        $phoneErr.hide();
        $phoneChkBtn.attr("disabled", true);
    } else { // 번호가 유효성에 맞게 입력되어 있는 경우
        $phoneInput.addClass('find-input');
        $phoneErr.hide();
        $phoneChkBtn.attr("disabled", false);
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
    $chkCode.attr("disabled", false);
    $chkCode.focus();

    // 인증 요청 문구 출력
    $chkTimeInP.show();
    $chkTimeInTimeP.show();
    $chkTimeOutP.hide();
    $chkTimeOutTimeP.hide();
    $chkCodeWrongP.hide();

    //인증시간 표시
    clearInterval(setinterval);
    counter = 0;
    setinterval = setInterval(timeIt, 1200);
})

// 인증 번호 입력란 유효성 검사
$chkCode.on('input', function() {
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

// 인증하기 버튼 눌렀을 때 처리(2가지 : 인증 실패, 인증 성공)
// 인증 성공하면 인증번호받기(인증번호 재전송)버튼 비활성화 되어야함.
// 인증 실패시 인증실패 문구만 출력하고 나머지는 그대로
$codeChkBtn.on('click', function () {
    if($testCheckCode == $chkCode.val()) {
        $chkTimeInP.hide();
        $chkTimeInTimeP.hide();
        $chkTimeOutP.hide();
        $chkTimeOutTimeP.hide();
        $chkCodeWrongP.hide();
        $chkCodeOkP.show();
        $phoneChkBtn.attr("disabled", true);
        $chkCode.attr("disabled", true);
        $codeChkBtn.attr("disabled", true);
        // 인증 시간 초기화
        clearInterval(setinterval);
        counter = 0;
        $chkCodeFlg = 1;

    } else {
        $chkTimeInP.hide();
        $chkTimeInTimeP.hide();
        $chkTimeOutP.hide();
        $chkTimeOutTimeP.show();
        $chkCodeWrongP.show();
    }
})

/*혹시 모를 이 페이지가 열리면 타이머 값 초기화*/
$(document).ready(function(){
    clearInterval(setinterval);
    counter = 0;
})

/*convertSeconds타이머 함수 사용*/
/*타이머 태그 안에 남은 시간 넣기*/
$chkTimeInTimeP.html(convertSeconds(timeleft - counter));
$chkTimeOutTimeP.html(convertSeconds(timeleft - counter));

/*실시간 인터벌 주기전 타이머 변경 함수*/
function timeIt() {
    if (counter == 180) {
        //3분후 클리어
        clearInterval(setinterval);
        return;   // 시간이 끝났을 때 멈추는 역할
    }
    counter++
    console.log(convertSeconds(timeleft - counter));
    $chkTimeInTimeP.html(convertSeconds(timeleft - counter));
    $chkTimeOutTimeP.html(convertSeconds(timeleft - counter));
}

/*convertSeconds타이머 함수*/
function convertSeconds(s) {
    console.log(s)
    let min = Math.floor(s / 60);
    let sec = s % 60;

    /*시간이 다 된 경우*/
    if ((s) == 0) {
        //3분이 다 지나면 인증 시간 초과 문구 출력
        $chkTimeInP.hide();
        $chkTimeInTimeP.hide();
        $chkTimeOutP.show();
        $chkTimeOutTimeP.show();
        $chkCodeWrongP.hide();
        $chkCode.attr("disabled", true);
        $codeChkBtn.attr("disabled", true);
    } else if ((s) == 150 ){
        //30초가 지나면 인증번호 재전송 버튼 확성화
        $phoneChkBtn.attr("disabled", false);
    }

    if (sec.toString().length == 1) {
        return "유효시간 0" + min + ':' + "0" + sec;
    }
    return "유효시간 0" + min + ':' + sec;
}

/* ---------- 우편번호 관련 처리 ----------*/
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

/* ---------- 비밀번호 관련 처리 ----------*/
$newPassInput.on('input', function () {
    // 강도 초기화
    $securityLevelLow.hide();
    $securityLevelNormal.hide();
    $securityLevelHigh.hide();

    // 비밀번호 유효성 확인 플래그 초기화
    $passChkFlg = 0;

    let inputVal = $(this).val();
    if((!fn_passChk(inputVal) || inputVal.length < 8 || inputVal.length > 16)
        && inputVal.length != 0) { //올바르지 않은 비밀번호 형식
        $newPassInput.removeClass('new-pass-input');
        $newPassErrorP.show();
    } else if (inputVal.length === 0){ // 아무것도 입력 되어 있지 않은 경우(썼다 지운 경우)
        $newPassInput.addClass('new-pass-input');
        $newPassErrorP.hide();
    } else { //올바른 형식의 비밀번호
        $passChkFlg = 1;
        if (inputVal.length === 8) { // 강도 : 약함
            $securityLevelLow.show();
        } else if (inputVal.length < 12) { // 강도 : 보통
            $securityLevelNormal.show();
        } else { // 강도 :  강함
            $securityLevelHigh.show();
        }
        $newPassInput.addClass('new-pass-input');
        $newPassErrorP.hide();
    }
})

$newPassConfirmInput.on('input', function () {
    console.log("들어옴2");
    $newPassAvailable.hide();
    let inputVal = $(this).val();
    if(inputVal != $newPassInput.val() && inputVal.length != 0) {
        // 비밀번호, 비밀번화 확인 입력 불일치
        $newPassConfirmInput.removeClass('new-pass-confirm-input');
        $newPassNotMatch.show();
    } else if(inputVal.length === 0) {
        // 아무것도 입력 되어 있지 않은 경우(썼다 지운 경우)
        $newPassConfirmInput.addClass('new-pass-confirm-input');
        $newPassNotMatch.hide();
    } else {
        // 비밀번호, 비밀번화 확인 입력 일치
        $newPassConfirmInput.addClass('new-pass-confirm-input');
        $newPassNotMatch.hide();
        if($passChkFlg === 1) {
            $newPassAvailable.show();
        }
    }
})

// 비밀번호 유효성 검사 함수
//비밀번호에 한개 이상의 대문자 포함 (?=.*[A-Z])
//비밀번호에 한개 이상의 소문자 포함 (?=.*[a-z])
//비밀번호에 최소 하나의 숫자 포함 (?=.*[0-9])
//특수문자 하나 이상 포함 ([^A-Za-z0-9])
function fn_passChk(pass) {
    let strongPassword = new RegExp('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])');
    if (!strongPassword.test(pass)) {
        return false;
    }
    return true;
}


/* ---------- 가입하기 버튼 관련 처리 ----------*/
$joinBtn.on('click', function () {
    if($newPassConfirmInput.val() != $newPassInput.val()) {
        // 비밀번호, 비밀번화 확인 입력 불일치
        $joinBtn.attr("disabled", true);
        $newPassConfirmInput.focus();
        $newPassConfirmInput.removeClass('new-pass-confirm-input');
        $newPassNotMatch.show();
    } else {
        // 비밀번호, 비밀번화 확인 입력 일치
        console.log("비밀번호 설정은 성공");
    }
})


/* ---------- 동의 관련 처리 ----------*/
$agreeAllChkBox.on('click', function() {
    if($agreeAllChkBox.is(":checked")) {
        console.log("전체 동의 체크");
        $("agree-checkbox").prop("checked", true);
    } else {
        console.log("전체 동의 해지");
        $("agree-checkbox").prop("checked", false);
    }
});

$("agree-checkbox").on('click', function() {
    var total = $("agree-checkbox").length;
    var checked = $("agree-checkbox:checked").length;

    if(total != checked) $agreeAllChkBox.prop("checked", false);
    else $agreeAllChkBox.prop("checked", true);
});

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
