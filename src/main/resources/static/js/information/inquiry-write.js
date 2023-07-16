// 제출하기 버튼
const $submitBtn = $('#submit-button');

const $phoneInput = $('.phone-number-input');

const $emailInput = $('.email-input');

const $nameInput = $('.name-input');

const $subjectInput = $('.subject-input');

const $descriptionTextarea = $('.description-textarea');

const $agreeCheckbox = $('.agree-checkbox');

// 에러들
const $nameErr = $('#request_anonymous_requester_name_error');
const $emailErr = $('#request_anonymous_requester_email_error');
const $emailFormatErr = $('#request_anonymous_requester_email_format_error');
const $subjectErr = $('#request_subject_error')
const $descriptionErr = $('#request_description_error')
const $agreeErr = $('#request_custom_fields_360025875971_error')

// 휴대폰 입력시 유효성 검사 및 입력 방지
$phoneInput.on('input',function() {
    let numericVal = $(this).val().replace(/[^0-9]/g, '');

    if (numericVal.length > 11) {
        numericVal = numericVal.substr(0, 11); // 최대 11자리까지만 유지
    }

    $(this).val(numericVal);
});

// 이메일 유효성 검사 함수
function fn_emailChk(email) {
    let regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{1,4}$/;
    if (!regExp.test(email)) {
        return false;
    }
    return true;
}

$submitBtn.on('click', function () {
    $nameErr.hide();
    $emailErr.hide();
    $emailFormatErr.hide();
    $subjectErr.hide();
    $descriptionErr.hide();
    $agreeErr.hide();

    if($nameInput.val() == "") {
        $nameErr.css("display","table");
    }
    
    if($emailInput.val() == "") {
        $emailErr.css("display","table");
    } else if(!fn_emailChk($emailInput.val())){
        $emailFormatErr.css("display","table");
    }
    
    if($subjectInput.val() == "") {
        $subjectErr.show();
    }
    
    if($descriptionTextarea.val() == "") {
        $descriptionErr.show();
    }
    
    if(!$agreeCheckbox.is(':checked')) {
        $agreeErr.show();
    }
    
    if($nameInput.val() != ""
        && $emailInput.val() != ""
        && fn_emailChk($emailInput.val())
        && $subjectInput.val() != ""
        && $descriptionTextarea.val() != ""
        && $agreeCheckbox.is(':checked')) {
        showWarnModal("문의·신고 등록이 완료되었습니다.");
        return;
    }
}) 