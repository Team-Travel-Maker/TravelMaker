
let fileForm = /\.(jpg|gif|tif|bmp|png)$/i;
const form = new FormData();

let formData = new FormData();

/*파일 인풋태그*/
const $fileInput = $('.upload');

const $thumbnail = $("label.attach img.thumbnail");

let giftCardDTO = {"files" : []}

let fileSize = {width : 250,height : 400}

let couponService = (function () {
    function write(form, callback){
        $.ajax({
            url: `/api/admins/coupon`,
            type: `post`,
            enctype: "multipart/form-data", //form data 설정
            processData : false,
            contentType : false,
            async : false,
            data: form,
            success: function(){
                if(callback) callback();
            }
        })
    }

    return {write : write}
})()



//삭제 카운트
let sizes = [];
let name = [];
// 새파일
let text = "";
// 파일 인풋

$fileInput.on("change", function () {

    if(!$(this).val().match(fileForm)){
        alert("이미지 파일만 업로드 가능합니다.")
        return;
    }

    let i = $fileInput.index($(this));

    formData = new FormData();

    giftCardDTO.files =[];

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

    })

    formData.append("fileSize",   new Blob([JSON.stringify(fileSize)], { type: "application/json" }))

    // 아래 파일 정보 input 태그 생성 막고 input 안에 value 초기화
    for (let i = 0; i < sizes.length; i++) {
        if(sizes[i] > 41943040){
            $(this).val("");
            return;
        }
    }

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
            $("label.attach").eq(i).find("h6").hide();
            $("div.x").eq(i).show();
            $("img.thumbnail").eq(i).show();

            let now = new Date();
            let year = now.getFullYear();
            let month = now.getMonth() + 1;
            let date = now.getDate();

            month = month < 10 ? "0" + month : month;
            date = date < 10 ? "0" + date : date;

            let fileName = year + "/" + month + "/" + date + "/t_" + uuids[0] + "_" + name;
            $("img.thumbnail").eq(i).attr("src", `/api/files/display?fileName=${fileName}`);


            for (let i = 0; i < uuids.length; i++) {
                let test= {}
                test.filePath = filePath
                test.fileName = name[i]
                test.fileSize = sizes[i]
                test.fileUuid = uuids[i]
                giftCardDTO.files.push(test);
            }
        },
        error      : function () {
            alert("예상치 못한 오류 발생입니다.")
        }
    })
})



$("div.x").on("click", function(e){
    e.preventDefault();
    let i = $("div.x").index($(this));
    sizes = [];
    $fileInput.val("");
    $("label.attach").eq(i).find("h6").show();
    $("div.x").eq(i).hide();
    $thumbnail.eq(i).attr('src', "");
    $thumbnail.eq(i).hide();
});



$("button.btn-done").on("click", function () {

    // 상품권 이름 유효성 검사
    if ($("#notice-title").val() == "") {
        showWarnModal("상품권 이름을 입력해주세요.");
        $("#noticeTitle").focus();
        return false;
    };

    // 상품권 지역 유효성 검사
    if ($("#coupon-location").val() == "") {
        showWarnModal("상품권 지역을 입력해주세요.");
        $("#coupon-location").focus();
        return false;
    };


    // 상품권 상세지역 유효성 검사
    if ($("#coupon-location-detail").val() == "") {
        showWarnModal("상품권 상세지역을 입력해주세요.");
        $("#coupon-location-detail").focus();
        return false;
    };


    // 상품권 가격 유효성 검사
    if ($("#coupon-price").val() == "") {
        showWarnModal("상품권 내용을 입력해주세요.");
        $("#coupon-price").focus();
        return false;
    };


    /** 등록*/
    showWarnModal("상품권 등록이 완료되었습니다.");
    $('.modal').on("click", function () {
        giftCardDTO.giftCardTitle = $('#notice-title').val()
        giftCardDTO.giftCardRegion = $('#coupon-location').val()
        giftCardDTO.giftCardRegionDetail = $('#coupon-location-detail').val()
        giftCardDTO.giftCardPrice = $('#coupon-price').val()

        form.append("giftCardDTO", new Blob([JSON.stringify(giftCardDTO)], { type: "application/json" }));
        couponService.write(form,function () {
            location.href="/admins/coupon/list"
        })
    })



});