
//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postCode').value = data.zonecode;
            document.getElementById("roadAddress").value = roadAddr;
            document.getElementById("jibunAddress").value = data.jibunAddress;

            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}

//    input태그들
const $inputTag = $('.input-style');
const $inputTextArea = $('.input-style-1');

//에러 메세지
const errorTag =$('.has-error');

//업체 등록 버튼
const $submitBtn = $('.submit-btn');

// 등록하기
$inputTag.on("input", function () {
    // 모든 필수 입력 필드가 작성되었는지 확인
    var allWrite = true;
    $inputTag.each(function () {
        if ($(this).val() === "") {
            allWrite = false;
            return false; // 필드가 비어있으면 반복문 종료
        }
    });

    // 모든 필수 입력 필드가 작성되었다면 파란색 불 클래스 추가
    if (allWrite) {
        $submitBtn.addClass("ok-btn");
    } else {
        $submitBtn.removeClass("ok-btn");
    }
});

    $submitBtn.on("click",function(){
        //제목
        if($inputTag.eq(0).val() ==""){
            $inputTag.eq(0).focus();
            $inputTag.eq(0).css("border-color","red");
            errorTag.eq(0).show();
            return;
        }
        //업체 설명
        if($inputTextArea.eq(0).val() ==""){
            $inputTextArea.eq(0).focus();
            $inputTextArea.eq(0).css("border-color","red");
            errorTag.eq(1).show();
            return;
        }
        //업체 이름
        if($inputTag.eq(1).val() ==""){
            errorTag.eq(2).show();
            $inputTag.eq(1).css("border-color","red");
            $inputTag.eq(1).focus();
            return;
        }
        if($inputTag.eq(2).val() ==""){
            $inputTag.eq(2).focus();
            $inputTag.eq(2).css("border-color","red");
            errorTag.eq(3).show();
            return;
        }
        if($inputTag.eq(3).val() ==""){
            $inputTag.eq(3).focus();
            $inputTag.eq(3).css("border-color","red");
            errorTag.eq(3).show();
            return;
        }
        if($inputTag.eq(4).val() ==""){
            $inputTag.eq(4).focus();
            $inputTag.eq(4).css("border-color","red");
            errorTag.eq(3).show();
            return;
        }
        if($inputTag.eq(5).val() ==""){
            $inputTag.eq(5).focus();
            errorTag.eq(5).show();
            $inputTag.eq(4).css("border-color","red");
            return;
        }
        if($inputTag.eq(6).val() ==""){
            $inputTag.eq(6).focus();
            $inputTag.eq(6).css("border-color","red");
            errorTag.eq(4).show();
            return;
        }
        $submitBtn.addClass("ok-btn");
    });


