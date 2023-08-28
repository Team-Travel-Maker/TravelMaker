const $inquiryList = $('.inquiry-list');
var categoryType = "";
var cType = {
    "ALL" : "전체",
    "INQUIRY" : "문의",
    "DECLARATION" : "신고"
}

$(document).ready(function() {
    console.log("페이지 로딩");
    $('.topPickPostView-header-title-span').text("나의 " + cType.ALL + " 내역");
    getMyListFive(0, "", "ALL");
    categoryType = "ALL";
    // 문의 버튼의 글 목록을 기본으로 보이도록 설정
    showCategoryPosts("ALL");

    $(".select-category").on("click", function() {
        let category = $(this).data("category");
        categoryType = category;
        $('.search-input').val("");
        console.log(category);

        if (category == "ALL") {
            $('.topPickPostView-header-title-span').text("나의 " + cType.ALL + " 내역");
        } else if(category == "INQUIRY") {
            $('.topPickPostView-header-title-span').text("나의 " + cType.INQUIRY + " 내역");
        } else {
            $('.topPickPostView-header-title-span').text("나의 " + cType.DECLARATION + " 내역");
        }

        getMyListFive(0, "", category);

        // 선택한 카테고리의 글 목록을 보여주기 위한 함수 호출
        showCategoryPosts(category);
    });

    function showCategoryPosts(category) {
        $(".select-category").removeClass('now-button');
        $(".select-category[data-category='" + category + "']").addClass('now-button');
    }

    var currentPage = 1;
    var totalPages = 0;

    function updatePageNumbers(totalPages, currentPage) {
        var maxButtonsToShow = 5; // 표시할 최대 버튼 수
        var paginationContainer = $('.pagination');
        paginationContainer.empty();

        // "<" 버튼 추가 (첫 페이지에서 숨김)
        if (currentPage > 1) {
            paginationContainer.append('<li class="page-item" id="first"><a class="page-link" href="#">First</a></li>');
            paginationContainer.append('<li class="page-item" id="previous"><a class="page-link" href="#">Previous</a></li>');
        }

        // 페이지 번호 버튼 추가
        var startPage = Math.max(1, Math.floor((currentPage - 1) / maxButtonsToShow) * maxButtonsToShow + 1);
        var endPage = Math.min(startPage + maxButtonsToShow - 1, totalPages);

        for (var i = startPage; i <= endPage; i++) {
            paginationContainer.append('<li class="page-item" id="page-' + i + '"><a class="page-link" href="#">' + i + '</a></li>');
        }

        // ">" 버튼 추가 (마지막 페이지에서 숨김)
        if (currentPage < totalPages) {
            paginationContainer.append('<li class="page-item" id="next"><a class="page-link" href="#">Next</a></li>');
            paginationContainer.append('<li class="page-item" id="last"><a class="page-link" href="#">Last</a></li>');
        }

        // 현재 페이지 활성화
        $('#page-' + currentPage).addClass('active');

        paginationContainer.on('click', 'li', function () {
            var clickedPage = $(this).attr('id');

            if (clickedPage === 'first') {
                currentPage = 1;
            } else if (clickedPage === 'last') {
                currentPage = totalPages;
            } else if (clickedPage === 'previous') {
                if (currentPage > 1) {
                    currentPage--;
                }
            } else if (clickedPage === 'next') {
                if (currentPage < totalPages) {
                    currentPage++;
                }
            } else {
                currentPage = parseInt(clickedPage.replace('page-', ''), 10);
            }

            // 페이지 번호 업데이트
            getMyListFive(currentPage - 1, $('.search-input').val(), categoryType)
        });
    }
    $(document).on('click', '.post-title', function(e) {
        e.preventDefault(); // 기본 링크 동작 방지

        let postContent = $(this).data("content");
        $(".post-content[data-content='" + postContent + "']").slideToggle('slow');
    });

    $(".search-input").keydown(function(e) {
        if( e.keyCode == 13 ){
            let keyword= $(this).val();
            getMyListFive(0, keyword, categoryType);
        }
    });

    $("#search-form").on("submit", function(e) {
        e.preventDefault(); // 페이지 리로딩 방지
    });

    function getMyListFive(page, keyword, csType) {
        let url = "/api/myPages/inquiries?page=" + page +"&keyword=" + keyword;
        if(csType != "ALL") {
            url += "&csType=" + csType;
        }
        $.ajax({
            type: "GET", //전송방식을 지정한다 (POST,GET)
            url: url,
            dataType: "json",
            async: false,
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (inquiries) {
                console.log("통신성공" + "OK");
                console.log(inquiries);
                setHtml(inquiries);
                // 초기 페이지 번호 설정
                totalPages = inquiries.totalPages;
                currentPage = page + 1;
                updatePageNumbers(totalPages, currentPage);
            }
        });
    }

    function setHtml(inquiries) {
        let text = "";
        if (inquiries.content.length > 0) {
            $.each(inquiries.content, function (i) {
                let idx = i + 1;
                text += '<div class="post-row" style="text-align: center;">\n' +
                    '        <div>' +
                                idx +
                    '        </div>\n' +
                    '        <div>\n' +
                    '            <a href="#" class="post-title" data-content=inquiry-"' +
                                    inquiries.content[i].id +
                                 '">' +
                                        inquiries.content[i].csTitle +
                    '            </a>\n' +
                    '        </div>\n' +
                    '        <div>' +
                                inquiries.content[i].memberName +
                    '        </div>\n' +
                    '        <div>' +
                                inquiries.content[i].memberEmail +
                    '        </div>\n' +
                    '        <div>' +
                                inquiries.content[i].updatedDate.split("T")[0] +
                    '        </div>\n' +
                    '        <div>' +
                                inquiries.content[i].csType.name +
                    '        </div>\n';
                if(inquiries.content[i].csAnswers.length == 0) {
                    text +=
                    '        <div style="color: #e35b66">답변 미완료</div>\n';
                } else {
                    text +=
                    '        <div style="color: #00aead">답변 완료</div>\n';
                }
                text +=
                    '    </div>\n' +
                    '    <div class="board-info-box-layout post-content" data-content=inquiry-"' +
                            inquiries.content[i].id +
                         '">\n' +
                    '        <div class="board-info-box">\n' +
                    '            <div class="board-info-title-box">\n' +
                    '                <span>문의글 상세보기</span>\n' +
                    '            </div>\n' +
                    '            <hr />\n' +
                    '            <div class="info-table">\n' +
                    '                <div class="board-title">\n' +
                    '                    <span class="span-bold">제목 :</span>\n' +
                    '                    <span>' +
                                            inquiries.content[i].csTitle +
                    '                    </span>\n' +
                    '                </div>\n' +
                    '                <div class="board-info board-writer">\n' +
                    '                    <span class="span-bold">작성자 :</span>\n' +
                    '                    <span>' +
                                            inquiries.content[i].memberName +
                    '                    </span>\n' +
                    '                </div>\n' +
                    '                <div class="board-info board-writer">\n' +
                    '                    <span class="span-bold">작성자 이메일 :</span>\n' +
                    '                    <span>' +
                                            inquiries.content[i].memberEmail +
                    '                    </span>\n' +
                    '                </div>\n' +
                    '                <div class="board-info board-register-date">\n' +
                    '                    <span class="span-bold">작성 날짜 :</span>\n' +
                    '                    <span>' +
                                            inquiries.content[i].createdDate +
                    '                    </span>\n' +
                    '                </div>\n' +
                    '                <div class="board-info board-update-date">\n' +
                    '                    <span class="span-bold">수정 날짜 :</span>\n' +
                    '                    <span>' +
                                            inquiries.content[i].updatedDate +
                    '                    </span>\n' +
                    '                </div>\n' +
                    '                <div class="board-info board-register-date">\n' +
                    '                    <span class="span-bold">문의/신고 :</span>\n' +
                    '                    <span>' +
                                            inquiries.content[i].csType.name +
                    '                    </span>\n' +
                    '                </div>\n' +
                    '                <hr />\n' +
                    '                <div class="inquiry-content">' +
                                            inquiries.content[i].csContent +
                    '                </div>\n' +
                    '            </div>\n' +
                    '            <hr style="border: 0px;border-top: 19px solid #F9F9F9;margin-bottom: 0px !important;"/>\n' +
                    '            <div class="board-info-title-box">\n' +
                    '                <span>답변 상세보기</span>\n' +
                    '            </div>\n' +
                    '            <hr />\n';
                if (inquiries.content[i].csAnswers.length == 0) {
                    text +=
                    '            <div class="info-table">\n' +
                    '                <div class="board-info board-register-date" style="padding-bottom: 15px">\n' +
                    '                    <span class="span-bold" style="color: #e35b66">답변 미완료 상태 입니다.</span>\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '        </div>\n' +
                    '    </div>\n';
                } else {
                    text +=
                    '            <div class="info-table">\n' +
                    '                <div class="board-info board-register-date">\n' +
                    '                    <span class="span-bold">작성 날짜 :</span>\n' +
                    '                    <span>' +
                                            inquiries.content[i].csAnswers[0].createdDate +
                    '                    </span>\n' +
                    '                </div>\n' +
                    '                <div class="board-info board-update-date">\n' +
                    '                    <span class="span-bold">수정 날짜 :</span>\n' +
                    '                    <span>' +
                                            inquiries.content[i].csAnswers[0].updatedDate +
                    '                    </span>\n' +
                    '                </div>\n' +
                    '                <hr />\n' +
                    '                <div class="answer-wrapper modify-wrapper" style="padding-bottom: 15px">\n' +
                                        inquiries.content[i].csAnswers[0].answerContent +
                    '                </div>\n' +
                    '            </div>\n' +
                    '        </div>\n' +
                    '    </div>\n';
                }
            })
        } else {
            text += '<span class="span-bold">문의/신고 정보가 없습니다.</span>';
        }

        $inquiryList.html(text);
    }

    // // 체크박스
    //
    // // 전체 선택 및 전체 선택 해제
    // $("#allSelect").click(function() {
    //     if($("#allSelect").is(":checked")) {
    //         $("input[name=questionChk]").prop("checked", true);
    //         // 체크박스 전체 선택 했을 때 삭제 버튼 활성화
    //         $(".Button-qBtn").addClass("now-button");
    //     }
    //     else {
    //         $("input[name=questionChk]").prop("checked", false);
    //         // 체크박스 전체 선택 해제 했을 때 삭제 버튼 비활성화
    //         $(".Button-qBtn").removeClass("now-button");
    //     }
    // });
    // $("#allSelect-repot").click(function() {
    //     if($("#allSelect-repot").is(":checked")) {
    //         $("input[name=repotChk]").prop("checked", true);
    //         // 체크박스 전체 선택 했을 때 삭제 버튼 활성화
    //         $(".Button-rBtn").addClass("now-button");
    //     }
    //     else {
    //         $("input[name=repotChk]").prop("checked", false);
    //         // 체크박스 전체 선택 해제 했을 때 삭제 버튼 비활성화
    //         $(".Button-rBtn").removeClass("now-button");
    //     }
    // });
    //
    //
    // // 부분 선택 및 부분 선택 해제 & 모든 체크박스 선택했을 때 전체선택 버튼도 체크 되게
    // $("input[name=questionChk]").click(function() {
    //
    //     let questionTotal = $(".questionChk").length;
    //     let questionChk = $("input[name=questionChk]:checked").length;
    //
    //     console.log("문의 토탈 체크 : " + questionTotal);
    //     console.log("문의 선택 체크 : " + questionChk);
    //
    //     $(this).prop("checked") ? onChecked() : noChecked();
    //
    //     if(questionTotal != questionChk){
    //         $("#allSelect").prop("checked", false);
    //     } else {
    //         $("#allSelect").prop("checked", true);
    //     }
    //
    //     // 선택 된 체크박스가 없으면 삭제버튼 비활성화
    //     if(questionChk > 0){
    //         $(".Button-qBtn").addClass("now-button");
    //     } else {
    //         $(".Button-qBtn").removeClass("now-button");
    //     }
    //
    // });
    //
    // $("input[name=repotChk]").click(function() {
    //     let repotTotal = $(".repotChk").length;
    //     let repotChk = $("input[name=repotChk]:checked").length;
    //
    //     console.log("신고 토탈 체크 : " + repotTotal);
    //     console.log("신고 선택 체크 : " + repotChk);
    //
    //     $(this).prop("checked") ? onChecked() : noChecked();
    //
    //     if(repotTotal != repotChk){
    //         $("#allSelect-repot").prop("checked", false);
    //     } else {
    //         $("#allSelect-repot").prop("checked", true);
    //     }
    //
    //     if(repotChk > 0){
    //         $(".Button-rBtn").addClass("now-button");
    //     } else {
    //         $(".Button-rBtn").removeClass("now-button");
    //     }
    //
    // });
    //
    // function onChecked() {
    //     $(this).prop("checked");
    //     if($(this).is($("input[name=questionChk]"))){
    //         console.log("어떤 인풋?" +$("input[name=questionChk]"));
    //         $(".Button-qBtn").addClass("now-button");
    //     } else if($(this).is($("input[name=repotChk]"))){
    //         $(".Button-rBtn").addClass("now-button");
    //     }
    // }
    //
    // function noChecked() {
    //     !$(this).prop("checked")
    // }
});
