// 이메일 입력란
const $emailInput = $('.email-input');

$(document).ready(function () {
// 에러 메시지
    const $emailErrMsg = $('.email-format-error-p');

// 이메일로 계속하기 버튼
    const $continueBtn = $('.continue-email-button');

    $emailInput.on("input", function () {
        if (fn_emailChk($emailInput.val())) {
            // 이메일 유효성 검사에 통과 o
            $emailInput.addClass('email-input');
            $emailErrMsg.hide();
            $continueBtn.attr("disabled", false);
        } else if ($emailInput.val().length === 0) {
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

    /*이종문*/

    let checkEmail= {};

    $continueBtn.on("click", function () {
        checkEmail.memberEmail = $('#email_input').val();
        console.log(checkEmail);
        checkId(checkEmail);
    })


    function checkId(email) {
        $.ajax({
            url: `/api/accounts/check`,
            type: `post`,
            data : email,
            dataType: "json",
            success: function(result){
                console.log(result);
                if(!result){
                    /*아이디 없음*/
                    $('#member-email').val(checkEmail.memberEmail);
                    $('#join-form').submit();
                }else{
                    /*아이디 있음*/
                    location.href =`/accounts/password/input`
                }
            }
        })

    }





});