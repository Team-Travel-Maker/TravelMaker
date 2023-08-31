const $thumbnailFile = $('#upload1');
const $thumbnailAttach = $('.thumbnail-attach');
const $thumbnailImg = $('.thumbnail-img');
const $thumbnailX = $('.thumbnail-x');

const uploadFiles = [];

$thumbnailFile.on('change', function(event) {
    uploadFiles[0] = event.target.files[0];
    var reader = new FileReader();

    $thumbnailAttach.show();
    $thumbnailImg.show();
    $thumbnailX.show();

    reader.onload = function(e) {
        $thumbnailImg.attr("src", e.target.result);
    };

    reader.readAsDataURL(uploadFiles[0]);
});

let goWithDTO = {"files": [] };

const selectedLocal = $('.local-btn');
const selectedMBTI = $('.mbti-btn');

const modal = $("button.modalOpen");

modal.on("click", e => {
    console.log("짜잔! 모달 나타남!");
    $("div.modal").css("display", "flex");
});

let modalCheck;

function showWarnModal(modalMessage){
    modalCheck = false;
    $("div#modal-wrap").html(modalMessage);
    $("div.warn-modal").css("animation", "popUp 0.5s");
    $("div.modal").css("display", "flex").hide().fadeIn(500);
    setTimeout(function(){modalCheck = true;}, 500);
}

$("div.modal").on("click", function(){
    if(modalCheck){
        $("div.warn-modal").css("animation", "popDown 0.5s");
        $("div.modal").fadeOut(500);
    }
});

$(document).ready(function() {
    $('.modal').click(function(e) {
        if ($(e.target).hasClass('modal') || $(e.target).attr('id') === 'modal-wrap') {
            $('.modal').hide();
        }
    });
});

let selectedLocalVal = '';
let selectedMBTIVal = '';
let localCheck = false;
let mbtiCheck = false;
const modalBtn = $('.Button-submit');
const commitBtn = $('#commit-btn');

$(document).ready(function() {
    $(".tagPanel-localScroll").children().css("display", "none");
    $(".tagPanel-mbtiScroll").children().css("display", "none");

    selectedMBTI.on('click', function () {
        selectedMBTI.removeClass('selected');
        $(this).addClass('selected');
        mbtiCheck = true;
        selectedMBTIVal = $(this).val();

        checkSubmitButton();

        $('.tagPanel-selectedMBTI').children('span:first-child').text(selectedMBTIVal);
        console.log("선택한 MBTI : " + selectedMBTIVal);
        console.log(mbtiCheck.valueOf())
    });

    if(localCheck == false && mbtiCheck == false){
        $('.submit-wrap').removeClass('btn-hover')
    }

    modalBtn.on('click', function () {
        if(selectedLocalVal == '' || selectedMBTIVal == ''){
            modalBtn.parent().removeClass("btn-hover");
        } else {
            console.log("데이터 가져옴");
            $('.tagPanel-selectedLocal').children('span:first-child').text(selectedLocalVal);
            $('.tagPanel-selectedMBTI').children('span:first-child').text(selectedMBTIVal);
            $(".tagPanel-localScroll").children().css("display", "flex");
            $(".tagPanel-mbtiScroll").children().css("display", "flex");
            $(".tagPanel-placeholder").hide();
            $('.modal').hide();
            console.log("선택한 지역2 : " + selectedLocalVal);
            console.log("선택한 MBTI2 : " + selectedMBTIVal);
        }
    });

    selectedLocal.on('click', function () {
        selectedLocal.removeClass('selected');
        $(this).addClass('selected');
        localCheck = true;
        selectedLocalVal = $(this).val();

        checkSubmitButton();

        $('.tagPanel-selectedLocal').children('span:first-child').text(selectedLocalVal);
        console.log("선택한 지역 : " + selectedLocalVal);
        console.log(localCheck.valueOf())
    });

    $('.selectedLocal-deletedBtn').click(function() {
        $('.tagPanel-selectedLocal').children('span:first-child').text('');
        $(".local-placeholder").show();
        $(".tagPanel-localScroll").children().css("display", "none");
    });

    $('.selectedMBTI-deletedBtn').click(function() {
        $('.tagPanel-selectedMBTI').children('span:first-child').text('');
        $(".mbti-placeholder").show();
        $(".tagPanel-mbtiScroll").children().css("display", "none");
    });

    function checkSubmitButton() {
        let title = $('.AutoTextarea-textarea[name="title"]').val().trim();
        let content = $('.AutoTextarea-textarea[name="content"]').val().trim();
        if (title === '' || content === '' || localCheck === false || mbtiCheck === false) {
            $('.Button-btn').attr('disabled', true);
        } else {
            $('.Button-btn').attr('disabled', false);
            $('.registration-headerBtn').addClass('valid');
        }
    }

    $('.AutoTextarea-textarea').on('input', checkSubmitButton);

    function autoResizeTextarea() {
        $('.AutoTextarea-textarea').each(function() {
            let $this = $(this);
            $this.css('height', 0);
            $this.css('height', $this[0].scrollHeight + 'px');
        });
    }

    autoResizeTextarea();
    $('.AutoTextarea-textarea').on('input', autoResizeTextarea);

    $('#upload-image').change(function() {
        const fileName = $(this).val().split('\\').pop();
        // 파일 이름을 표시하거나 처리하는 코드 추가 가능
        console.log('선택한 파일 이름:', fileName);
    });

});


function openFile() {
    document.getElementById('upload1').click();
}

commitBtn.on("click", function () {

    setFile();

    goWithDTO.goWithRegionType = selectedLocalVal;
    goWithDTO.goWithMbti = selectedMBTIVal;
    goWithDTO.goWithTitle = $('.AutoTextarea-textarea[name="title"]').val().trim();
    goWithDTO.goWithContent =$('.AutoTextarea-textarea[name="content"]').val().trim();

    console.log(goWithDTO);

    $.ajax({
        url: `/api/goWith/goWith-registration`,
        type: `post`,
        data		:  JSON.stringify(goWithDTO),
        contentType : "application/json",
        error: function () {
            alert("통신실패!!!!");
        },
        success: function(url){
            alert("등록 완료");
            console.log("성공")
            location.href = url
        }
    })
})


function setFile() {
    console.log(uploadFiles.length);
    console.log("==================="+uploadFiles);
    if(uploadFiles.length == 0) {
        return;
    } else if (uploadFiles[0].length == ""){
        showWarnModal("썸네일 사진부터 등록해 주세요.");
        return;
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

    $(uploadFiles).each((i) => {
        console.log(uploadFiles[i])
        if (uploadFiles[i] == undefined) {
            uploadFiles[i] = [];
        }
        if(uploadFiles[i].length == 0) {
            console.log("이 파일은 비어 있음");
        } else {
            formData.append("uploadFile", uploadFiles[i]);
            sizes.push(uploadFiles[i].size);
            name.push(uploadFiles[i].name);
            // ajax로 통신하기 전에 alert 띄우고 막기
            if (sizes[i] > 41943040) {
                alert("파일 사이즈가 너무 큽니다.")
                return;
            }
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
                goWithDTO.files.push(test);
            }
        },
        error      : function () {
            alert("통신 실패");
        }
    })
}


