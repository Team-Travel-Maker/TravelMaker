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

// 마지막 계속 버튼
const $continueBtn = $('.continue-button');

// 비밀번호 유효성 확인 플래그
let $passChkFlg = 0;

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
    $continueBtn.attr("disabled", true);
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
            $continueBtn.attr("disabled", false);
        }
    }
})

$continueBtn.on('click', function () {
    if($newPassConfirmInput.val() != $newPassInput.val()) {
        // 비밀번호, 비밀번화 확인 입력 불일치
        $continueBtn.attr("disabled", true);
        $newPassConfirmInput.focus();
        $newPassConfirmInput.removeClass('new-pass-confirm-input');
        $newPassNotMatch.show();
    } else {
        // 비밀번호, 비밀번화 확인 입력 일치
        console.log("비밀번호 재설정 성공");
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