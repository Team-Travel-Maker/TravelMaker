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

const form = new FormData();

let formData = new FormData();

let csDTO = {"files" : []}


/*let test= {}*/


let customService = (function () {
    function write(form, callback){
        $.ajax({
            url: `/api/informations`,
            type: `post`,
            enctype: "multipart/form-data", //form data 설정
            processData : false,
            contentType : false,
            data: form,
            success: function(){
                if(callback){
                    callback();
                }
            }
        })
    }
    return {write: write};
})();


/*// 휴대폰 입력시 유효성 검사 및 입력 방지
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
}*/

$submitBtn.on('click', function () {
    $nameErr.hide();
    $emailErr.hide();
    $emailFormatErr.hide();
    $subjectErr.hide();
    $descriptionErr.hide();
    $agreeErr.hide();

/*    if($nameInput.val() == "") {
        $nameErr.css("display","table");
    }
    
    if($emailInput.val() == "") {
        $emailErr.css("display","table");
    } else if(!fn_emailChk($emailInput.val())){
        $emailFormatErr.css("display","table");
    }*/
    
    if($subjectInput.val() == "") {
        $subjectErr.show();
    }
    
    if($descriptionTextarea.val() == "") {
        $descriptionErr.show();
    }
    
    if(!$agreeCheckbox.is(':checked')) {
        $agreeErr.show();
    }



    if($subjectInput.val() != ""
        && $descriptionTextarea.val() != ""
        && $agreeCheckbox.is(':checked')) {

        showWarnModal("문의·신고 등록이 완료되었습니다.");
        $('.modal').on("click", function () {

            csDTO.csTitle = $('#request_subject').val()
            csDTO.csContent = $('#request_description').val()
            csDTO.csType = $('#cs-type').val()
            console.log(csDTO);

            form.append("customServiceDTO", new Blob([JSON.stringify(csDTO)], { type: "application/json" }));
            customService.write(form,function () {
                location.href="/"
            })
        })
        return;
    }

})

/*이종문 추가*/

/*파일 인풋태그*/
const $fileInput = $('#request-attachments');

const $fileListContainer = $('#request-attachments-pool');

//삭제 카운트
let count = 0;
let sizes = [];
let name = [];
// 새파일
let text = "";
// 화면에 추가하기 위한 변수선언
let plusText = "";
// 파일 인풋

$fileInput.on("change", function () {
    formData = new FormData();

    csDTO.files =[];

    plusText =""
    //파일 이름 담는 배열 새로 파일이 담길 때마다 초기화
    name = [];
    sizes = [];
    // input hidden 파일 수정할 때마다 초기화
    text = "";

    // 다음 파일들
    let files = $(this)[0].files;

    //경로 생성을 위한 yy/mm/dd 설정
    let now = new Date();
    let year = now.getFullYear();
    let month = now.getMonth() + 1;
    let date = now.getDate();


    month = month < 10 ? "0" + month : month;
    date = date < 10 ? "0" + date : date;

    let filePath = year + "/" + month + "/" + date

    $(files).each((i, file) => {
        formData.append("uploadFile", file);
        sizes.push(files[i].size);
        name.push(files[i].name);

    // ajax로 통신하기 전에 alert 띄우고 막기
    if(sizes[i] > 41943040){
        alert("파일 사이즈가 너무 큽니다.")
        return false;
    }


    // 화면상에만 추가되어 보이기
    plusText += `
                   <li class="upload-item" data-upload-item="" aria-busy="false">
                                <a class="upload-link" target="_blank" data-upload-link="" href="/images/information/notice1.png">${name[i]}</a>
                                <span class="upload-remove" data-upload-remove=""></span>
                    </li>
                `
    })

    // 아래 파일 정보 input 태그 생성 막고 input 안에 value 초기화
    for (let i = 0; i < sizes.length; i++) {
        if(sizes[i] > 41943040){
            $(this).val("");
            return;
        }
    }

    $fileListContainer.html(plusText);

    //담긴 파일이 없으면 ajax 호출전 리턴
    if(files.length == 0){return;}
    $.ajax({
        url        : "/api/files/upload",
        type       : "post",
        async: false,
        data       : formData,
        contentType: false,
        processData: false,
        success    : function (uuids) {
            for (let i = 0; i < uuids.length; i++) {
                let test= {}
                test.filePath = filePath
                test.fileName = name[i]
                test.fileSize = sizes[i]
                test.fileUuid = uuids[i]
                test.fileType = "GENERAL"
                csDTO.files.push(test);
            }
        },
        error      : function () {
            alert("예상치 못한 오류 발생입니다.")
        }
    })
})