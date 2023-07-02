// 비밀 번호 입력 화면
// type="password"일 때 비번 입력란
const $passModeInput = $('.pass-mode');

// type="text"일 때 비번 입력란
const $textModeInput = $('.text-mode');

// 모드 확인
let $inputMode = "pass";

// 다음 버튼 후 비번이 틀렸을 때의 비번 입력란
const $errorModeInput = $('.error-mode');

// 뜬 눈 버튼
const $openEyeBtn = $('.open-eye-button');

// 감은 눈 버튼
const $closeEyeBtn = $('.close-eye-button');

// 비번이 틀렸을 때 에러 메세지
const $wrongPassMsg = $('.pass-input-wrong-p');

// 비번이 5회 이상 틀렸을 때 에러 메세지
const $overTimeMsg = $('.pass-input-count-over-p');

// 비번 입력 초과 카운트
let $passErrCnt = 0;

// 비번 실패 확인 플래그
let $passChkFlg = 0;

// 다음 버튼
const $nextBtn = $('.pass-next-button');

// 비밀번호 초기화/변경 버튼
const $passResetBtn = $('.pass-init-update-button');

// 테스트용 hidden타입 비번
const $testPass = $('#test-pass');
console.log($('.error-mode').find('input').val())

// pass -> text모드로 변경
$openEyeBtn.on("click" ,function () {
    console.log("뜬 눈 클릭")
    $passModeInput.hide();
    $textModeInput.show();
    $textModeInput.find('input').val($passModeInput.find('input').val());
    $inputMode = "text";
})

// text -> pass모드로 변경
$closeEyeBtn.on("click" ,function () {
    console.log("감은 눈 클릭")
    console.log("감은 눈 클릭")
    $passModeInput.show();
    $passModeInput.find('input').focus();
    if ($passChkFlg != 0) {
        $passModeInput.find('input').removeClass('pass-input');
    } else {
        $passModeInput.find('input').addClass('pass-input');
    }
    $textModeInput.hide();
    $inputMode = "pass";
})

// 비번이 입력 되면 다음 버튼 활성화
$passModeInput.find('input').on("input", function () {
    console.log("비번 입력중")
    $passChkFlg = 0;
    $wrongPassMsg.hide();
    $overTimeMsg.hide();
    $passModeInput.find('input').addClass('pass-input');
    if($passModeInput.find('input').val().length > 0) {
        console.log("비번 입력중22")
        $nextBtn.attr("disabled", false);
    } else {
        $nextBtn.attr("disabled", true);
    }
})

// 다음 버튼 눌렀을 때 비번이 다르면 에러 입력창과 에러 메세지
$nextBtn.on("click", function () {
    console.log("다음 버튼 클릭")
    console.log($testPass.val());
    console.log($passModeInput.find('input').val());
    if ($testPass.val() == $passModeInput.find('input').val()
        || $testPass.val() == $textModeInput.find('input').val()) {
        if($passErrCnt < 5) {
            // 로그인 성공 -> 메인 화면으로 가도록 처리
            console.log("로그인 성공")
        }
    } else {
        // 비밀번호 오류
        $passErrCnt += 1;
        if($inputMode === "pass") {
            $passModeInput.find('input').removeClass('pass-input');
            $passChkFlg = 1;
        }
        if($passErrCnt < 5) {
            $wrongPassMsg.show();
            $nextBtn.attr("disabled", true);
        }
    }
    console.log("실패횟수:"+$passErrCnt);
    // 비번이 5이상 틀린 경우, 입력 초과 메세지
    // 맞는 비번을 치더라도 초과 메세지 유지
    if($passErrCnt > 4) {
        $passChkFlg = 1;
        $passModeInput.find('input').removeClass('pass-input');
        $wrongPassMsg.hide();
        $overTimeMsg.show();
        $nextBtn.attr("disabled", true);
    }
})




