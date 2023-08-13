$(document).ready(function () {

    console.log("인식")

    const $detailContainer = $('.board-info-box-layout');

    let noticeText ="";

    const form = new FormData();

    let formData = new FormData();

    /*파일 인풋태그*/
    const $fileInput = $('.upload');

    const $thumbnail = $("label.attach img.thumbnail");

    let noticeRequestDTO = {"files" : []}

    let fileSize = {width : 575,height : 772}

    let noticeService = (function () {

        if(noticeId != ""){
            function getDetail(callback){
                $.ajax({
                    url: `/api/admins/notice/detail/${noticeId}`,
                    type: `get`,
                    success: function(result){
                        console.log(result);
                        if(callback){
                            callback(result);
                        }
                    }
                })
            }
        }

        function write(form, callback){
            $.ajax({
                url: `/api/admins/notice`,
                type: `post`,
                enctype: "multipart/form-data", //form data 설정
                processData : false,
                contentType : false,
                data: form,
                success: function(){
                  if(callback) callback();
                }
            })
        }

        return {write : write, getDetail : getDetail}
    })()


    if(noticeId != ""){
        /*상세*/
        noticeService.getDetail(showDetail);
    }

    
    function showDetail(result) {
        let fileName = result.files[0].filePath + "/t_" + result.files[0].fileUuid + "_" + result.files[0].fileName;
        console.log(result.files[0]);
        noticeText =`
                      <div class="board-info-box">
                        <div class="board-info-title-box" style="position: relative">
                            <span>공지사항 상세보기</span>
                            <a class="btn-done">수정 하기</a>
                        </div>
                        <div class="info-table">
                            <div class="info-member">
                                <span class="span-bold">제목 :</span>
                                <!-- 제목 -->
                                <span>${result.noticeTitle}</span>
                            </div>
                            <hr />
                            <div class="info-member">
                                <span class="span-bold">작성 날짜 :</span>
                                <!-- 작성 날짜 -->
                                <span>${result.createdDate}</span>
                            </div>
                            <hr />
                            <div class="info-member">
                                <span class="span-bold">수정 날짜 :</span>
                                <!-- 작성 날짜 -->
                                <span>${result.updatedDate}</span>
                            </div>
                            <hr />
                            <div class="info-member">
                                <span class="span-bold">내용 이미지</span>
                                <br>
                                <!-- 대표 이미지 -->
                                <span class="notice-image"><img src="/api/files/display?fileName=${fileName}" alt=""></span>
                            </div>
                        </div>
                        <hr />
                        <div class="info-table">
                            <div>${result.noticeContent}</div>
                        </div>
                      </div>
                    `

        $detailContainer.html(noticeText);
    }
    


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
        let i = $fileInput.index($(this));

        formData = new FormData();

        noticeRequestDTO.files =[];

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
                    noticeRequestDTO.files.push(test);
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

    $('.ok-button').on("click", function () {

        // 공지사항 제목 유효성 검사
        if ($("#noticeTitle").val() == "") {
            showWarnModal("공지사항 제목을 입력해주세요.");
            $("#noticeTitle").focus();
            return false;
        };

        // 공지사항 내용 유효성 검사
        if ($("#noticeContent").val() == "") {
            showWarnModal("공지사항 내용을 입력해주세요.");
            $("#noticeContent").focus();
            return false;
        };

        showWarnModal("공지 등록이 완료되었습니다.");
        $('.modal').on("click", function () {
            noticeRequestDTO.noticeTitle = $('#noticeTitle').val()
            noticeRequestDTO.noticeContent = $('#noticeContent').val()
            console.log(noticeRequestDTO);

            form.append("noticeRequestDTO", new Blob([JSON.stringify(noticeRequestDTO)], { type: "application/json" }));
            noticeService.write(form,function () {
                location.href="/admins/notice/list"
            })
        })
    })


})