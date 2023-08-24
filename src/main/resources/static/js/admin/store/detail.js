const $detailContainer =$('.board-info-box-layout')

let form = new FormData();
let resultContent = {};
let storeText =''
let answerText =''
let answerContent ='';


    let storeService = (function () {

    function changeStatus(){
        $.ajax({
            url: `/api/admins/store/modify`,
            type: `put`,
            async: false,
            enctype: "multipart/form-data", //form data 설정
            processData : false,
            contentType : false,
            data: form,
            success: function(){
            }
        })
    }


    if(storeId != ""){
        function getDetail(callback){
            $.ajax({
                url: `/api/admins/store/detail/${storeId}`,
                type: `get`,
                async: false,
                success: function(result){
                    if(callback){
                        callback(result);
                    }
                }
            })
        }
    }




    return {changeStatus : changeStatus, getDetail : getDetail}
})()

storeService.getDetail(showDetail);


function showDetail(result) {
    $detailContainer.eq(0).html('');
    $detailContainer.eq(1).html('');
    storeText =''
    answerText=''


    storeText = `
                 <div class="board-info-box">
                    <div class="board-info-title-box">
                        <span>업체 상세보기</span>
                    </div>
                    <hr />
                    <div class="info-table">
                        <div class="board-info board-writer">
                            <span class="span-bold">회원 ID :</span>
                            <!-- 신청 제목 -->
                            <span>${result.memberEmail}</span>
                        </div>
                        <div class="board-info board-writer">
                            <span class="span-bold">업체명 :</span>
                            <!-- 업체명 -->
                            <span>${result.storeTitle}</span>
                        </div>
                        <div class="board-info board-writer">
                            <span class="span-bold">업체 주소 :</span>
                            <!-- 업체주소 -->
                            <span>우편번호 : ${result.postCode}<br>${result.address}<br>${result.addressDetail}</span>
                        </div>
                        <div class="board-info board-register-date">
                            <span class="span-bold">작성 날짜 :</span>
                            <!-- 작성 날짜 -->
                            <span>${result.createdDate}</span>
                        </div>
                        <div class="board-info board-update-date">
                            <span class="span-bold">수정 날짜 :</span>
                            <!-- 작성 날짜 -->
                            <span>${result.updatedDate}</span>
                        </div>
                        <div class="board-info board-register-date">
                            <span class="span-bold">종류 : </span>
                            <!-- 작성 날짜 -->
                            <span>${result.storeType.name}</span>
                        </div>
                        <div class="board-info board-register-date">
                            <span class="span-bold">상태 : </span>
                            <!-- 작성 날짜 -->
                            <span>${result.storeStatus.name}</span>
                        </div>
                        <hr />
                        `
    if(result.storeFiles.length >0){
        result.storeFiles.forEach((file, index) =>{
          let fileName = "";
            if(file.fileType.code=="REPRESENTATIVE"){
                fileName =file.filePath + "/t_" + file.fileUuid + "_" + file.fileName;
                storeText+=`
                        <div class="info-member">
                            <span class="span-bold">대표 이미지</span>
                            <br>
                            <!-- 대표 이미지 -->
                            <span class="notice-image"><a href="/api/files/download/${file.id}"><img src="/api/files/display?fileName=${fileName}" style="width: 100px; height: 100px"></span></a>
                            <br>
                        </div>
                    `
            }else{
                fileName =file.filePath+ "/" + file.fileUuid + "_" + file.fileName;
                storeText+=`
                        <hr />
                        <div class="info-member">
                            <span class="span-bold">내용 이미지${index}</span>
                            <br>
                            <!-- 대표 이미지 -->
                            <span class="notice-image"><a href="/api/files/download/${file.id}"><img src="/api/files/display?fileName=${fileName}" style="width: 100px; height: 100px"></span></a>
                             <br>
                        </div>
                        `
            }
        })
    }
            storeText+= `
                        <span class="span-bold">이미지 클릭시 다운로드 됩니다.</span>
                        <hr />
                        <!-- 게시판 내용 -->
                        <div class="board-content">
                        <span class="span-bold">신청 내용 : </span>
                        <br>
                        ${result.storeContent}
                        </div>
                        `
            if(result.storeResult ==null){
                storeText+=`
                            <hr />
                            <div class="answer-button-wrapper">
                                <button>
                                    답변하기
                                </button>
                            </div>
                            `
            }else{
              answerContent = `${result.storeResult}`;
                storeText+=`
                         <hr />   
                        <div class="board-content answer-content">
                        <span class="span-bold">등록 답변 : </span>
                        <br>
                        ${result.storeResult}
                         <br>
                        <div>
                            <button type="button" class="btn-done modify-btn">답변 수정</button>
                        </div>
                        </div>
                       
                           `
            }
            storeText+= `
                            </div>
                        </div>
                        `
    $detailContainer.eq(0).html(storeText);

}

$(document).on("click",".answer-button-wrapper" ,function () {
    answerText=''
    answerText+= `
                        <div class="board-info-box">
                            <div class="board-info-title-box">
                                <span>업체 신청 답변</span>
                            </div>
                            <div class="info-table">
                                    <hr>
                                    <div>
                                        <textarea class="board-content" id="boardContent" name="boardContent"></textarea>
                                    </div>
                                    <hr>
                                    <div>
                                        <button id="APPROVED" type="button" class="btn-done change-result">승인</button>
                                        <button id="REJECTED" type="button" class="btn-done change-result">반려</button>
                                    </div>
                            </div>
                        </div>
                    `
    $detailContainer.eq(1).html(answerText)
})

/** 일반 답변 등록*/
$(document).on("click",'.change-result',function () {
    resultContent = {};
    form.delete("result");
    resultContent.id = storeId;
    resultContent.storeResult = $('#boardContent').val();
    resultContent.storeStatus = $(this).attr("id");
    form.append("result", new Blob([JSON.stringify(resultContent)],{ type: "application/json" }));
    showWarnModal("업체 신청 결과를 등록하였습니다");
    $('.modal').on("click",function () {
        storeService.changeStatus();
        storeService.getDetail(showDetail);
    })
})


/** 답변이 있을때 수정 버튼*/
$(document).on("click", '.modify-btn', function () {
        $('.answer-content').hide();
        answerText=''
        answerText+= `
                            <div class="board-info-box">
                                <div class="board-info-title-box">
                                    <span>업체 신청 답변 수정</span>
                                </div>
                                <div class="info-table">
                                        <hr>
                                        <div>
                                            <textarea class="board-content" id="boardContent" name="boardContent"></textarea>
                                        </div>
                                        <hr>
                                        <div>
                                            <button id="APPROVED" type="button" class="btn-done change-result">승인</button>
                                            <button id="REJECTED" type="button" class="btn-done change-result">반려</button>
                                        </div>
                                </div>
                            </div>
                        `
        $detailContainer.eq(1).html(answerText)

    $('#boardContent').text(answerContent);

})

