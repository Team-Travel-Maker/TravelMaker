const $thumbnailFile = $('#upload1');
const $sub1File = $('#upload2');
const $sub2File = $('#upload3');
const $thumbnailAttach = $('.thumbnail-attach');
const $sub1Attach = $('.sub1-attach');
const $sub2Attach = $('.sub2-attach');
const $thumbnailImg = $('.thumbnail-img');
const $sub1Img = $('.sub1-img');
const $sub2Img = $('.sub2-img');
const $thumbnailX = $('.thumbnail-x');
const $sub1X = $('.sub1-x');
const $sub2X = $('.sub2-x');

const uploadFiles = [];
let fileDelChk = "no";

const queryParams = new URLSearchParams(window.location.search);
const storeId = queryParams.get('storeId');

console.log('Received item ID:', storeId);

// 파일 수정 채크
let $fileChangeChk = 0;

// 업데이트 업체 아이디
let $hiddenStoreId;
let subChk = 0;

const status = {
    "PENDING" : "대기",
    "APPROVED" : "승인",
    "REJECTED" : "반려"
}

$(document).ready(function () {
    $.ajax({
        type: "GET", //전송방식을 지정한다 (POST,GET)
        url: "/api/myPages/store?storeId=" + storeId,
        dataType: "json",
        error: function () {
            alert("통신실패!!!!");
        },
        success: function (store) {
            console.log("통신성공" + "OK");
            console.log(store);

            $hiddenStoreId = store.id;

            $inputTag.eq(0).val(store.storeTitle);
            $inputTextArea.eq(0).val(store.storeContent);
            $inputTag.eq(1).val(store.address.postcode);
            $inputTag.eq(3).val(store.address.address);
            const ad = store.address.addressDetail.split("|");
            $inputTag.eq(4).val(ad[0]);
            $inputTag.eq(5).val(ad[1]);
            $inputTag.eq(6).val(ad[2]);
            $('.store-status').text(store.storeStatus.name);
            if(store.storeStatus.code == "PENDING") {
                $('.store-status').css("color", "rgb(0, 189, 222)");
            } else if(store.storeStatus.code == "APPROVED") {
                $('.store-status').css("color", "rgb(7, 186, 156)");
            } else {
                $('.store-status').css("color", "red");
            }
            $('.status-result').text(store.storeResult);
            $('#category').val(store.storeType.code).prop("selected",true);

            const fileLength = store.files.length;
            if(fileLength == 0) return;
            store.files.forEach((file, i) => {
                console.log(i)
                console.log(file);
                if(3 == fileLength) {
                    console.log("여기여기" + file);
                    if (file.fileType.code == "REPRESENTATIVE") {
                        $thumbnailAttach.show()
                        $thumbnailImg.show();
                        $thumbnailX.show();
                        $thumbnailImg.attr("src", "/api/files/display?fileName=" +
                            file.filePath + "/" +
                            file.fileUuid + "_" +
                            file.fileName);
                    } else if(subChk == 0) {
                        $sub1Attach.show()
                        $sub1Img.show();
                        $sub1X.show();
                        $sub1Img.attr("src", "/api/files/display?fileName=" +
                            file.filePath + "/" +
                            file.fileUuid + "_" +
                            file.fileName);
                        subChk++;
                    } else {
                        $sub2Attach.show()
                        $sub2Img.show();
                        $sub2X.show();
                        $sub2Img.attr("src", "/api/files/display?fileName=" +
                            file.filePath + "/" +
                            file.fileUuid + "_" +
                            file.fileName);
                    }
                }
                if(2 == fileLength) {
                    if (file.fileType == "REPRESENTATIVE") {
                        $thumbnailAttach.show()
                        $thumbnailImg.show();
                        $thumbnailX.show();
                        $thumbnailImg.attr("src", "/api/files/display?fileName=" +
                            store.files[0].filePath + "/" +
                            store.files[0].fileUuid + "_" +
                            store.files[0].fileName);
                    } else {
                        $sub1Attach.show()
                        $sub1Img.show();
                        $sub1X.show();
                        $sub1Img.attr("src", "/api/files/display?fileName=" +
                            file.filePath + "/" +
                            file.fileUuid + "_" +
                            file.fileName);
                    }
                }
                if(1 == fileLength) {
                    $thumbnailAttach.show()
                    $thumbnailImg.show();
                    $thumbnailX.show();
                    $thumbnailImg.attr("src", "/api/files/display?fileName=" +
                        file.filePath + "/" +
                        file.fileUuid + "_" +
                        file.fileName);
                }
            })
        }
    });
});

$thumbnailFile.on('change', function(event) {
    checkAll();
    $fileChangeChk = 1;
    uploadFiles[0] = event.target.files[0];
    var reader = new FileReader();

    $thumbnailAttach.show()
    $thumbnailImg.show();
    $thumbnailX.show();

    reader.onload = function(e) {
        $thumbnailImg.attr("src", e.target.result);
    }

    reader.readAsDataURL(uploadFiles[0]);
});

$thumbnailX.on('click', function () {
    checkAll();
    $fileChangeChk = 1;
    $thumbnailImg.attr("src", "");
    $thumbnailImg.hide()
    $thumbnailX.hide();
    uploadFiles[0] = [];
})

$sub1File.on('change', function(event) {
    checkAll();
    $fileChangeChk = 1;
    uploadFiles[1] = event.target.files[0];
    var reader = new FileReader();

    $sub1Attach.show()
    $sub1Img.show();
    $sub1X.show();

    reader.onload = function(e) {
        $sub1Img.attr("src", e.target.result);
    }

    reader.readAsDataURL(uploadFiles[1]);
});

$sub1X.on('click', function () {
    checkAll();
    $fileChangeChk = 1;
    $sub1Img.attr("src", "");
    $sub1Img.hide()
    $sub1X.hide();
    uploadFiles[1] = [];
})

$sub2File.on('change', function(event) {
    checkAll();
    $fileChangeChk = 1;
    uploadFiles[2] = event.target.files[0];
    var reader = new FileReader();

    $sub2Attach.show()
    $sub2Img.show();
    $sub2X.show();

    reader.onload = function(e) {
        $sub2Img.attr("src", e.target.result);
    }

    reader.readAsDataURL(uploadFiles[2]);
});

$sub2X.on('click', function () {
    checkAll();
    $fileChangeChk = 1;
    $sub2Img.attr("src", "");
    $sub2Img.hide()
    $sub2X.hide();
    uploadFiles[2] = [];
    console.log(uploadFiles);
})

//    input태그들
const $inputTag = $('.input-style');
const $inputTextArea = $('.input-style-1');

//업체 등록 버튼
const $submitBtn = $('.submit-btn');

// 등록하기
$inputTag.on("input", function () {
    checkAll();
});

$inputTextArea.on("input", function () {
    checkAll();
});


let storeDTO = {"files" : [], "address" : {}}

function checkAll() {
    if($inputTag.eq(0).val() != "" &&
        $inputTextArea.eq(0).val() != "" &&
        $inputTag.eq(1).val() != "" &&
        $inputTag.eq(2).val() != "" &&
        $inputTag.eq(3).val() != "" &&
        $inputTag.eq(4).val() != "" &&
        $inputTag.eq(5).val() != "") {
        $submitBtn.removeClass("submit-btn");
        $submitBtn.addClass("ok-btn");
    } else {
        $submitBtn.removeClass("ok-btn");
        $submitBtn.addClass("submit-btn");
    }
}

$submitBtn.on("click", function () {
    if ($fileChangeChk == 1 && uploadFiles.length != 0) {
        setFile();
    } else if ($fileChangeChk == 1 && uploadFiles.length == 0) {
        fileDelChk = "yes";
    } else {
        storeDTO.files = [];
    }

    console.log("=======여기여기여기여기"+storeDTO.files.toString())

    storeDTO.id = storeId;
    storeDTO.storeTitle = $inputTag.eq(0).val()
    storeDTO.storeContent = $inputTextArea.eq(0).val()
    storeDTO.address.address = $inputTag.eq(3).val()
    storeDTO.address.addressDetail = $inputTag.eq(4).val()
                                    + "|" + $inputTag.eq(5).val()
                                    + "|" + $inputTag.eq(6).val()
    storeDTO.address.postcode = $inputTag.eq(1).val()
    storeDTO.storeType = $inputTag.eq(7).val()
    storeDTO.storeStatus = "PENDING"
    storeDTO.storeResult = ""

    console.log(storeDTO);

    $.ajax({
        url: `/api/myPages/store?fileDel=` + fileDelChk,
        type: `PUT`,
        data		:  JSON.stringify(storeDTO),
        contentType : "application/json",
        error: function () {
            alert("통신실패!!!!");
        },
        success: function(data){
            showWarnModal("업체 수정이 완료되었습니다.")
            $(".modal").on("click", function () {
                location.href = data
            })
            console.log("성공")
        }
    })
})

function setFile(i) {
    console.log("++++++++++++++++" + uploadFiles.length)
    if(uploadFiles.length == 0) {
        return;
    } else if (!$thumbnailImg.attr('src')){
        showWarnModal("썸네일부터 사진을 등록해 주세요.");
    }

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

    console.log("====================")
    console.log(uploadFiles);
    console.log("====================")

    $(uploadFiles).each((i) => {
        console.log(uploadFiles[i])
        if(uploadFiles[i].length == 0) {
            console.log("이 파일은 비어 있음")
        }
        formData.append("uploadFile", uploadFiles[i]);
        sizes.push(uploadFiles[i].size);
        name.push(uploadFiles[i].name);

        // ajax로 통신하기 전에 alert 띄우고 막기
        if (sizes[i] > 41943040) {
            alert("파일 사이즈가 너무 큽니다.")
            return false;
        }
    })


    $.ajax({
        url        : "/api/files/upload",
        type       : "post",
        async      : false,
        data       : formData,
        contentType: false,
        processData: false,
        success    : function (uuids) {
            console.log("통신 성공");
            for (let i = 0; i < uuids.length; i++) {
                console.log(uuids[i]);
                let test= {}
                test.filePath = filePath
                test.fileName = name[i]
                test.fileSize = sizes[i]
                test.fileUuid = uuids[i]
                if(i == 0) {
                    test.fileType = "REPRESENTATIVE";
                } else {
                    test.fileType = "GENERAL";
                }
                storeDTO.files[i].push(test);
            }
        },
        error      : function () {
            alert("통신 실패");
        }
    })
}



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
                document.getElementById("roadAddress").value = expRoadAddr;
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                document.getElementById("jibunAddress").value = expJibunAddr;
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}
