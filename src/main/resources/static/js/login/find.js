// 핸드폰 인증 완료 플래그
let $phoneFlg = 0;

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

// 인증 완료 후 계속 버튼
const $nextBtn = $('.find-continue-button')

// 인증 번호 플래그 (정상적으로 번호 인증이 완료 되었을 때 1로 변경 예정)
let $chkCodeFlg = 0;

// 인증번호 테스트용 코드(테스트용이라서 나중에 삭제 필요)
let $testCheckCode = '1234';

/*타이머 구현*/
// 토큰 유효시간 타이머
let counter = 0;
let timeleft = 180; // 제한 시간 지정 값
let setinterval;

// 나라 번호가 바뀔 때 마다
$countryNmSelect.on('change', function () {
    // 휴대폰 관련 태그 초기화
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
    // 계속버튼 초기화
    $nextBtn.attr("disabled", true);
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

    // 계속버튼 초기화
    $nextBtn.attr("disabled", true);

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
        $nextBtn.attr("disabled", false);
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