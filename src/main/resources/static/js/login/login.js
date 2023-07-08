// 이메일 입력란
const $emailInput = $('.email-input');

// 에러 메시지
const $emailErrMsg = $('.email-format-error-p');

// 이메일로 계속하기 버튼
const $continueBtn = $('.continue-email-button');

$emailInput.on("input", function () {
    console.log("수정 이메일 입력")
    if (fn_emailChk($emailInput.val())) {
        // 이메일 유효성 검사에 통과 o
        $emailInput.addClass('email-input');
        $emailErrMsg.hide();
        $continueBtn.attr("disabled", false);
    } else if($emailInput.val().length === 0) {
        // 입력 안했을 때
        $emailInput.val('');
        $emailInput.addClass('email-input');
        $emailErrMsg.hide();
        $continueBtn.attr("disabled", true);
    } else {
        // 이메일 유효성 검사에 통과 x
        $emailInput.removeClass('email-input');
        $emailErrMsg.show();
        $continueBtn.attr("disabled", true);
    }
})

// 이메일 유효성 검사 함수
function fn_emailChk(email) {
    let regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{1,4}$/;
    if (!regExp.test(email)) {
        return false;
    }
    return true;
}