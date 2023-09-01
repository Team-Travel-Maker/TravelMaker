
//======= 전화번화 인증 관련 부분 ==========
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
const $nextBtn = $('#change-mobile-btn')

// 인증 번호 플래그 (정상적으로 번호 인증이 완료 되었을 때 1로 변경 예정)
let $chkCodeFlg = 0;

// 인증번호 테스트용 코드(테스트용이라서 나중에 삭제 필요)
let $testCheckCode = '1234';

/*타이머 구현*/
// 토큰 유효시간 타이머
let counter = 0;
let timeleft = 180; // 제한 시간 지정 값
let setinterval;

//===========================================================

const $updatePossibleName = $('.update-possible-name');
const $updatePossiblePhone = $('.update-possible-phone');
const $updatePossiblePass = $('.update-possible-password');
const $goWithdrawal = $('.go-withdrawal');

const $modalName = $('.modal-name');
const $modalPassword = $('.modal-password');
const $modalphone = $('.modal-phone');
$updatePossibleName.on('click', function () {
    $modalName.css('display', 'flex');
});

$updatePossiblePhone.on('click', function () {
    $modalphone.css('display', 'flex');
    $('input[name="mobile"]').val("")
    $('.change-phone-div1').css("display", "block");
    $('.change-phone-div2').css("display", "none");
});

$updatePossiblePass.on('click', function () {
    $('input[name="oldPassword"]').val("")
    $('input[name="password"]').val("")
    $('input[name="passwordConfirm"]').val("")
    $modalPassword.css('display', 'flex');
});

$goWithdrawal.on('click', function () {
    window.location.href = '/mypage/withdrawal';
})

//취소 버튼
const closebtn = $('.modal-submit-close');
//x버튼
const xbtn = $('.modal-btn');

//클릭하면 modal 안보이게하기
xbtn.on('click', function () {
    $modalName.css('display', 'none');
    $modalPassword.css('display', 'none');
    $modalphone.css('display', 'none');
});

closebtn.on('click', function () {
    $modalName.css('display', 'none');
    $modalPassword.css('display', 'none');
    $modalphone.css('display', 'none');
});

let fileDTO = ""
$(document).ready(function () {
    // 프로필 사진 등록 변경
    $("#input-file").on("change", function () {
        var fileInput = document.getElementById("input-file");
        var file = fileInput.files[0];

        if (file) {
            setFile(file);
            console.log(fileDTO)
            $.ajax({
                url: "/api/myPages/accountManager/uploadProfile",
                type: "POST",
                data: JSON.stringify(fileDTO),
                contentType: "application/json",
                dataType: "text",
                success: function (result) {
                    console.log("통신성공" + "OK");
                    console.log(result);
                    showWarnModal("프로필 파일이 성공적으로 수정 및 등록되었습니다.")
                    $(".modal").on("click", function () {
                        window.location.href = result;
                    })
                },
                error: function (error) {
                    alert("통신실패!!!!");
                }
            });
        } else {
            alert("파일을 선택해주세요.");
        }
    });

    // 이름 변경
    $('#change-name-btn').on('click', function () {
        var newMemberName = $('#member-name').val()

        $.ajax({
            type: "PUT", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/accountManager/name",
            data: JSON.stringify({memberName: newMemberName}),
            contentType: "application/json",
            dataType: "text",
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (result) {
                console.log("통신성공" + "OK");
                console.log(result);
                showWarnModal("사용자 이름이 변경되었습니다.")
                $(".modal").on("click", function () {
                    window.location.href = result;
                })
            }
        });
    })

    // 비밀번호 변경
    $('#update-pass-btn').on('click', function () {
        var oldPass = $('input[name="oldPassword"]').val()
        var newPass = $('input[name="password"]').val()
        var newPassConfirm = $('input[name="passwordConfirm"]').val()
        if(oldPass=="") {
            showWarnModal("현재 비밀번호를 제대로 작성해 주세요.");
            return;
        }
        if(newPass=="" || newPassConfirm=="") {
            showWarnModal("새 비밀번호, 새 비밀번호 확인을 제대로 작성해 주세요.")
        } else if (newPass != newPassConfirm) {
            showWarnModal("새 비밀번호, 새 비밀번호 확인이 일치하지 않습니다.")
        } else {
            chkOldPass(oldPass)
                .done(function () {
                    // 비밀번호 변경 처리
                    $.ajax({
                        type: "PUT", //전송방식을 지정한다 (POST,GET)
                        url: "/api/myPages/accountManager/password",
                        data: JSON.stringify({newPassword: newPass}),
                        contentType: "application/json",
                        dataType: "text",
                        async: false,
                        error: function () {
                            alert("통신실패!!!!");
                        },
                        success: function (result) {
                            $modalPassword.css('display', 'none');
                            console.log("통신성공" + "OK");
                            console.log(result);
                            showWarnModal("비밀번호가 변경되었습니다.")
                            $(".modal").on("click", function () {
                                window.location.href = result;
                            })
                        }
                    });
                })
                .fail(function(error) {
                    showWarnModal("현재 비밀번호가 일치하지 않습니다.")
                });
        }
    })

    // 전화번호 변경
    $nextBtn.on('click', function () {
        $.ajax({
            type: "PUT", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/accountManager/mobile",
            data: JSON.stringify({mobile: $phoneInput.val()}),
            contentType: "application/json",
            dataType: "text",
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (result) {
                console.log("통신성공" + "OK");
                console.log(result);
                showWarnModal("전화번호를 변경하였습니다.")
                $(".modal").on("click", function () {
                    window.location.href = result;
                })
            }
        });
    })
});

function chkOldPass(oldPass) {
    // 현재 비밀번호 일치 확인
    return $.ajax({
        type: "POST", //전송방식을 지정한다 (POST,GET)
        url: "/api/myPages/accountManager/oldPassword",
        data: JSON.stringify({oldPassword: oldPass}),
        contentType: "application/json",
        async: false
    });

}

function setFile(file) {
    //파일 이름 담는 배열 새로 파일이 담길 때마다 초기화
    const name = [];
    const sizes = [];

    const formData = new FormData();
    //경로 생성을 위한 yy/mm/dd 설정
    let now = new Date();
    let year = now.getFullYear();
    let month = now.getMonth() + 1;
    let date = now.getDate();

    month = month < 10 ? "0" + month : month;
    date = date < 10 ? "0" + date : date;

    let filePath = year + "/" + month + "/" + date

    formData.append("uploadFile", file);
    sizes.push(file.size);
    name.push(file.name);

    $.ajax({
        url: "/api/files/upload",
        type: "post",
        async: false,
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuids) {
            console.log("통신 성공");
            for (let i = 0; i < uuids.length; i++) {
                console.log(uuids[i]);
                let file = {}
                file.filePath = filePath;
                file.fileName = name[i];
                file.fileSize = sizes[i];
                file.fileUuid = uuids[i];
                file.fileType = "GENERAL";
                fileDTO = file;
            }
        },
        error: function () {
            alert("통신 실패");
        }
    })
}

// 새로운 번호 인증 받을 div보여주기
$('.change-phone-btn').on("click", function () {
    $('.change-phone-div1').css("display", "none");
    $('.change-phone-div2').css("display", "block");
})

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
