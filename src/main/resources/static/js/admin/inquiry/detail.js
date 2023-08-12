$(document).ready(function () {

    const form = new FormData();

    let csText ='';
    let answerText ='';

    let csAnswerDTO = {}


    const $csContentBoxes = $('.board-info-box-layout');

    let customService = (function () {
        function getDetail(callback){
            $.ajax({
                url: `/api/admins/inquiry/detail/${customServiceId}`,
                type: `get`,

                success: function(result){
                    console.log(result);
                    if(callback){
                        callback(result);
                    }
                }
            })
        }

        function write(){
            $.ajax({
                url: `/api/admins/answer/${customServiceId}`,
                type: `post`,
                enctype: "multipart/form-data", //form data 설정
                processData : false,
                contentType : false,
                data: form,
                success: function(){
                    showWarnModal("답변이 등록 되었습니다.")
                    $(".modal").on("click", function () {
                        location.href = `/admins/inquiry/detail/${customServiceId}`
                    })
                    console.log("성공")
                }
            })
        }

        return {getDetail: getDetail, write : write};
    })();

    customService.getDetail(showDetail);


    function showDetail(result){

        csText = `
                 <div class="board-info-box">
                    <div class="board-info-title-box">
                        <span>문의글</span>
                    </div>
                    <div class="info-table">
                        <div class="board-title">
                            <span class="span-bold">제목 : </span>
                            <!-- 제목 -->
                            <span>${result.csTitle}</span>
                        </div>
                        <div class="board-info board-writer">
                            <span class="span-bold">작성자 : </span>
                            <!-- 작성자 명 -->
                            <span>${result.memberName}</span>
                        </div>
                        <div class="board-info board-writer">
                            <span class="span-bold">작성자 이메일 : </span>
                            <!-- 작성자 명 -->
                            <span>${result.memberEmail}</span>
                        </div>
                        <div class="board-info board-register-date">
                            <span class="span-bold">작성 날짜 : </span>
                            <!-- 작성 날짜 -->
                            <span>${result.createdDate}</span>
                        </div>
                        <div class="board-info board-register-date">
                            <span class="span-bold">문의/신고 :</span>
                            <!-- 작성 날짜 -->
                            <span>${result.csType.name}</span>
                        </div>
                        <hr>
                        <!-- 작성 내용 -->
                        <div class="board-content">${result.csContent}</div>
                        `
                    if(result.csAnswers.length == 0){
                        csText += `
                                       <hr />
                                    <div class="answer-button-wrapper">
                                        <button>
                                            답변하기
                                        </button>
                                    </div>
                                  `
                     }
                csText+=`
                            </div>
                        </div>
                        `
        if(result.csAnswers.length != 0){
            result.csAnswers.forEach(answer =>{
                answerText = `
                         <div class="board-info-box">
                                <div class="board-info-title-box">
                                    <span>등록 답변</span>
                                </div>
                                <div class="info-table">
                                    <div class="board-info board-register-date">
                                        <span class="span-bold">작성 날짜 : </span>

                                        <!-- 등록 날짜 -->
                                        <span>${answer.createdDate}</span>
                                    </div>
                                    <div class="board-info board-register-date">
                                        <span class="span-bold">수정 날짜 : </span>

                                        <!-- 등록 날짜 -->
                                        <span>${answer.updatedDate}</span>
                                    </div>
                                    <form action="">
                                        <hr>
                                        <!-- 등록 내용 -->
                                        <div class="board-content">${answer.answerContent}</div>
                                        <hr>
                                        <div>
                                            <button type="button" class="btn-done-modify">
                                                수정
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                     `
            })
            $($csContentBoxes[1]).html(answerText);
        }
        $($csContentBoxes[0]).html(csText);
    }

    $(document).on("click", ".btn-done-modify", function () {
        location.href = `/admins/inquiry/modify/${customServiceId}`
    })

    $(document).on("click", ".answer-button-wrapper", function () {
        $($csContentBoxes[2]).show();
    })
    $('.write-btn').on("click", function () {

        csAnswerDTO.answerContent = $('#boardContent').val();

        form.append("csAnswerDTO", new Blob([JSON.stringify(csAnswerDTO)], { type: "application/json" }));

        customService.write();
    })

})