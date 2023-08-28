$(document).ready(function () {
    const $detailContainer =$('.board-info-box-layout')

    let fileForm = /\.(jpg|gif|tif|bmp|png)$/i;

    let noticeText =''
    let flag;
    const form = new FormData();
    let formData = new FormData();
    let noticeRequestDTO = {"files" : [], "deleteFiles":[]}
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

        function modify(form, callback){
            $.ajax({
                url: `/api/admins/notice`,
                type: `put`,
                enctype: "multipart/form-data", //form data 설정
                processData : false,
                contentType : false,
                data: form,
                success: function(){
                    if(callback) callback();
                }
            })
        }

        return {modify : modify, getDetail : getDetail}
    })()

    if(noticeId != ""){
        /*상세*/
        noticeService.getDetail(showDetail);
    }

    function showDetail(result) {
        noticeText =`
                      <div class="board-info-box">
                            <div class="board-info-title-box">
                                    <span>공지사항 수정하기</span>
                                </div>
                                <hr />
                            <div class="info-table">
                                    <input id="noticeId" type="hidden" name="id" value="${result.id}">
                                    <div>
                                        <input
                                            id="noticeTitle"
                                            type="text"
                                            class="board-title"
                                            name="noticeTitle"
                                            placeholder="제목을 입력해주세요."
                                            value="${result.noticeTitle}"
                                        />
                                    </div>
                                    <hr />
                                    <div>
                                        <textarea
                                            id="noticeContent"
                                            class="board-content"
                                            name="noticeContent"
                                        ></textarea>
                                    </div>
                            <hr />
                            `
        if(result.files.length !=0){
            flag = true;
            let fileName = result.files[0].filePath + "/t_" + result.files[0].fileUuid + "_" + result.files[0].fileName;
            noticeText+= `    
                            <div class="attach-wrap">
                                <label for="upload1" class="attach">
                                    <img id="${result.files[0].id}" src="/api/files/display?fileName=${fileName}" class="thumbnail">
                                    <div class="x">
                                        <img src="/images/admin/x.png">
                                    </div>
                                    <h6 class="plus">+</h6>
                                    <h6 style="text-align: center">
                                        내용 이미지
                                    </h6>
                                    <span class="file-size">${(result.files[0].fileSize / 1024).toFixed(2)}KB</span>
                                </label>
                            </div>
                            <input type="file" id="upload1" class="upload" disabled style="display: none;">
                            `
        }else{
            flag = false;
            noticeText+=`
                        <div class="attach-wrap">
                                <label for="upload1" class="attach">
                                <img src="" class="thumbnail">
                                <div class="x">
                                <img src="/images/admin/x.png">
                                </div>
                                <h6 class="plus">+</h6>
                                <h6 style="text-align: center">
                                내용 이미지<br>(썸네일 부분)
                                </h6>
                            <span class="file-size"></span>
                                </label>
                                </div>
                                <input type="file" id="upload1" class="upload" style="display: none;">
                `
        }
        noticeText+= `
                  <div>
                        <button style="margin-top: 15px;" class="ok-button btn-done" type="button">수정 완료</button>
                  </div>
                    `

        $detailContainer.html(noticeText);

        /*텍스트 area 내용 채우기*/
        $('.board-content').append(result.noticeContent);

        let $thumbnail = $("label.attach img.thumbnail");

        /*파일 인풋태그*/
        const $fileInput = $('.upload');

        //삭제 카운트
        let sizes = [];
        let name = [];
// 새파일
        let text = "";

        $fileInput.on("change", function () {
            if(!$(this).val().match(fileForm)){
                alert("이미지 파일만 업로드 가능합니다.")
                return;
            }

            $(this).attr("disabled", true);
            let i = $fileInput.index($(this));

            formData = new FormData();

            noticeRequestDTO.files =[];

            flag=true;


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

            $("span.file-size").eq(i).text((files[0].size / 1024).toFixed(2) + "KB");
            showSize($("span.file-size").eq(i));
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
                        check()
                    }
                },
                error      : function () {
                    alert("예상치 못한 오류 발생입니다.")
                }
            })
        })
        check()

        function check() {
            if(flag){
                $("label.attach").eq(0).find("h6").hide();
                $("div.x").eq(0).show();
                $thumbnail.eq(0).show();

                $("div.x").on("click", function(e){
                    e.preventDefault();
                    noticeRequestDTO.files =[];
                    noticeRequestDTO.deleteFiles = [];
                    let i = $("div.x").index($(this));
                    sizes[i] = 0;
                    $("span.file-size").eq(i).text(sizes[i]);
                    showSize($("span.file-size").eq(i));
                    $('#upload1').attr("disabled", false);
                    $fileInput.eq(i).val("");
                    $("label.attach").eq(i).find("h6").show();
                    $("div.x").eq(i).hide();
                    $thumbnail.eq(i).attr('src', "");
                    $thumbnail.eq(i).hide();
                    console.log($thumbnail.eq(i).attr("id"));
                    noticeRequestDTO.deleteFiles.push($thumbnail.eq(i).attr("id"));
                });
                flag=false;
            }
        }
    }


    $(document).on("click",'.ok-button', function () {
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

        showWarnModal("공지 수정이 완료되었습니다.");
        $('.modal').on("click", function () {
            noticeRequestDTO.id = $('#noticeId').val();
            noticeRequestDTO.noticeTitle = $('#noticeTitle').val()
            noticeRequestDTO.noticeContent = $('#noticeContent').val()

            form.append("noticeRequestDTO", new Blob([JSON.stringify(noticeRequestDTO)], { type: "application/json" }));
            noticeService.modify(form,function () {
                location.href="/admins/notice/detail/"+ noticeId;
            })
        })
    })

    function showSize(span){
        if(span.text() == 0){
            span.hide();
        }else{
            span.show();
        }
    }
});