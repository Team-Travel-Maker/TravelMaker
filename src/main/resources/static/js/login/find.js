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
$phoneInput.on('input', function () {

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

    if (!fn_mbtlnumChk($phoneInput.val()) && numericVal.length != 0) {
        //번호의 유효성이 잘못 되었거나 1자 이상 10자 이하로 입력되어 있을 경우
        $phoneInput.removeClass('find-input');
        $phoneErr.show();
        $phoneChkBtn.attr("disabled", true);
    } else if (numericVal.length === 0) { // 아무것도 입력되어 있지 않은 경우
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
function fn_mbtlnumChk(mbtlnum) {
    var regExp = /^010\d{8}$/;
    if (!regExp.test(mbtlnum)) {
        return false;
    }
    return true;
}

// 인증 번호 눌렀을 때 함수
$phoneChkBtn.on("click", function () {
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

// 인증하기 버튼 눌렀을 때 처리(2가지 : 인증 실패, 인증 성공)
// 인증 성공하면 인증번호받기(인증번호 재전송)버튼 비활성화 되어야함.
// 인증 실패시 인증실패 문구만 출력하고 나머지는 그대로
$codeChkBtn.on('click', function () {

    if ($testCheckCode == $chkCode.val()) {
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


    /* const to = $('#mobile').val();

 $.ajax ({
     url: '/api/accounts/check/sendSMS',
     type: 'GET',
     data: {
         "to" : to
     },
     success: function(data) {
         const checkNum = data;
         $codeChkBtn.on('click', function () {
             const userNum = $chkCode.val();
             if(checkNum === userNum) {
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
                 showWarnModal("인증에 성공하셨습니다")
             }
             else {
                 $chkTimeInP.hide();
                 $chkTimeInTimeP.hide();
                 $chkTimeOutP.hide();
                 $chkTimeOutTimeP.show();
                 $chkCodeWrongP.show();
                 showWarnModal('인증 실패하였습니다. 다시 입력해주세요.')
             }
             joinBtnCheck()
         });

     }
 });*/


})

/*혹시 모를 이 페이지가 열리면 타이머 값 초기화*/
$(document).ready(function () {
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
    } else if ((s) == 150) {
        //30초가 지나면 인증번호 재전송 버튼 확성화
        $phoneChkBtn.attr("disabled", false);
    }

    if (sec.toString().length == 1) {
        return "유효시간 0" + min + ':' + "0" + sec;
    }
    return "유효시간 0" + min + ':' + sec;
}

/*이종문*/
let text = "";

$nextBtn.on("click", function () {

    $phoneInput.val();

    $.ajax({
        url: '/api/accounts/find-id',
        type: 'POST',
        data: {
            "memberPhoneNumber": $phoneInput.val()
        },
        success: function (result) {
            text = "";
            $('#find__next').html('')

            text = `                       
                               <div class="found-div1">
                                    <div class="found-div2">
                                        <div class="found-container">
                                            <div class="find-header-container">
                                                <div class="find-back-button-container">
                                                    <button type="button" class="find-back-button">
                                                        <span class="button_span">
                                                            <svg viewbox="0 0 18 18" class="find-back-button-svg">
                                                                <path d="m6.045 9 5.978-5.977a.563.563 0 1 0-.796-.796L4.852 8.602a.562.562 0 0 0 0 .796l6.375 6.375a.563.563 0 0 0 .796-.796L6.045 9z"></path>
                                                            </svg>
                                                        </span>
                                                    </button>
                                                </div>
                                                <div class="find-logo-container">
                                                    <p color="var(--theme-palette-colors-black-100)" class="find-logo-p">계정 찾기</p>
                                                </div>
                                                <div class="find-logo-right"></div>
                                            </div>
                                            <div class="found-body-container">
                                 `
            if (result.length != 0) {
                text += `
                                        <h1 color="var(--theme-palette-colors-black-100)" class="found-body-h1">계정을 찾았어요.</h1>
                                        <h2 color="var(--theme-palette-colors-gray-600)" class="found-body-h2">이 전화번호로 가입한 계정을 알려드려요.</h2>    
                                           `
                result.forEach(item => {
                    text +=`
                              <div class="found-email-container">
                                                    <div class="found-email-inner-container">
                                                        <div class="found-email-inner-container2">
                                                            <div class="found-email-logo-container">
                                                                <div class="found-email-logo-inner-container">
                                                                    <span class="found-email-logo-span">
                            `
                    if (item.memberJoinAccountType.code == "NAVER") {
                        text += `
                             <!--네이버-->
                            <svg class="found-email-logo-svg" xmlns="http://www.w3.org/2000/svg" width="280" height="280" version="1.0" viewBox="0 0 210 210">
                                <path fill="#03C75AFF" stroke="#03C75AFF" d="M83 1.891C41.309 11.031 9.278 43.933 1.434 85.674c-2.24 11.923-1.55 34.532 1.389 45.501 10.001 37.323 38.228 65.578 76.002 76.079 6.564 1.824 10.354 2.133 26.175 2.133 15.821 0 19.611-.309 26.175-2.133 37.763-10.497 65.582-38.316 76.079-76.079 1.824-6.564 2.133-10.354 2.133-26.175 0-15.821-.309-19.611-2.133-26.175-10.5-37.772-38.696-65.944-76.079-76.012C119.963-.207 94.768-.689 83 1.891m7.321 64.359c.649.688 1.785 2.426 2.525 3.864.741 1.438 2.428 3.78 3.75 5.206C97.918 76.746 99 78.184 99 78.516 99 79.631 118.428 107 119.219 107c.429 0 .781-9.45.781-21V65h25v80.06l-12.662-.28-12.662-.28-2.088-3.692c-1.148-2.031-2.988-4.737-4.088-6.014-1.1-1.277-2.606-3.492-3.346-4.922-.741-1.43-2.428-3.766-3.75-5.192-1.322-1.426-2.404-2.861-2.404-3.189C104 120.369 91.601 103 90.8 103c-.44 0-.8 9.45-.8 21v21H65V65h12.071c8.362 0 12.434.384 13.25 1.25">
                                </path>
                            </svg>
                              `
                    } else if (item.memberJoinAccountType.code == "KAKAO") {
                        text += `                                        
                            <!--카카오-->
                           <svg viewBox="0 0 57 56" class="found-email-logo-svg">
                                <path d="M0.5 28C0.5 12.536 13.036 0 28.5 0C43.964 0 56.5 12.536 56.5 28C56.5 43.464 43.964 56 28.5 56C13.036 56 0.5 43.464 0.5 28Z" fill="#FEE500"></path>
                                <path fill-rule="evenodd" clip-rule="evenodd" d="M28.5 16.2063C21.5606 16.2063 15.9286 20.5812 15.9286 25.9617C15.9286 29.3183 18.1034 32.2474 21.4223 34.0326L20.0269 39.1492C20.0005 39.2509 20.006 39.3583 20.0424 39.4569C20.0788 39.5555 20.1446 39.6406 20.2307 39.7008C20.3169 39.761 20.4195 39.7934 20.5246 39.7937C20.6297 39.7939 20.7324 39.7621 20.8189 39.7023L26.9286 35.6417C27.444 35.6417 27.972 35.7297 28.5 35.7297C35.4394 35.7297 41.0714 31.3549 41.0714 25.9617C41.0714 20.5686 35.4394 16.2063 28.5 16.2063Z" fill="#181600"></path>
                           </svg>
                          `
                    } else {
                        text += `
                            <!--일반-->
                            <svg viewBox="0 0 13 14" class="found-email-logo-svg">
                                <path d="M6.05471 10.2422C7.18752 10.2422 7.68752 9.46875 7.88283 9.07031H7.96096C8.07814 9.78906 8.83596 10.3047 9.85158 10.3047C11.5156 10.3125 12.7422 8.92188 12.7422 6.52344C12.75 3.13281 10.211 0.601562 6.50783 0.601562C2.84377 0.601562 0.250018 3.29688 0.25783 6.99219C0.25783 10.9297 2.57814 13.3984 6.44533 13.3984C7.47658 13.3984 8.42189 13.2578 9.24221 13.0234V11.7734C8.32814 12.0234 7.54689 12.1094 6.44533 12.1172C3.25002 12.1406 1.57033 10.0938 1.57033 6.99219C1.57814 4.14062 3.50002 1.89062 6.50783 1.88281C9.42189 1.88281 11.4453 3.74219 11.4297 6.57031C11.4219 8.08594 10.8438 9.10938 9.96096 9.10156C9.49221 9.10938 9.26564 8.82812 9.24221 8.25781V3.88281H7.88283V4.82031H7.80471C7.60939 4.42969 7.13283 3.75781 6.05471 3.75781C4.58596 3.75781 3.42189 4.9375 3.41408 6.99219C3.42189 9.03906 4.54689 10.2422 6.05471 10.2422ZM4.88283 6.97656C4.87502 5.875 5.41408 5.04688 6.39846 5.03906C7.39846 5.04688 7.90627 5.8125 7.91408 6.97656C7.90627 8.17188 7.39064 8.95312 6.39846 8.96094C5.42189 8.95312 4.87502 8.10938 4.88283 6.97656Z" fill="var(--theme-palette-colors-gray-700)"></path>
                            </svg>
                            `
                    }
                    text += `      
                                                </span>
                                            </div>
                                        </div>
                                        <p color="var(--theme-palette-colors-black-100)" class="found-email-p">${item.memberEmail}</p>
                                    </div>
                                </div>
                            </div>
                             `
                })
            } else {
                text += `
                      <h1 color="var(--theme-palette-colors-black-100)" class="found-body-h1">가입된 계정이 없어요.</h1>
                      <h2 color="var(--theme-palette-colors-gray-600)" class="found-body-h2">다른 전화번호로 계속해보시거나 새로 가입해보세요.</h2>
                        `
            }

            text += `
                                                <button type="button" data-testid="Button" class="found-goto-main-button">
                                                    <span color="var(--theme-palette-colors-black-100)" class="found-goto-main-span">메인으로 돌아가기</span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                `
            $('#find__next').html(text)
        }
    });
})

$(document).on("click", '.found-goto-main-button', function () {
    location.href = `/`
})