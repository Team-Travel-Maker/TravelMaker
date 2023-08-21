const $bookmarkList = $('.bookmark-list');

$(document).ready(function () {
    console.log("북마크 페이지")
    $.ajax({
        type: "GET", //전송방식을 지정한다 (POST,GET)
        url: "/api/myPages/bookmarks",
        dataType: "json",
        error: function () {
            alert("통신실패!!!!");
        },
        success: function (bookmarks) {
            let text = "";
            console.log("통신성공" + "OK");
            console.log(bookmarks);
            if (bookmarks.length > 0) {
                $.each(bookmarks, function (i) {
                    const content = bookmarks[i].themeContent.length <= 25 ? bookmarks[i].themeContent : bookmarks[i].themeContent.substring(0, 25) + '...';
                    text += '<li id="' +
                                bookmarks[i].themeId +
                        '    ">\n' +
                        '        <div class="card">\n' +
                        '            <a href="#">\n' +
                        '                <header style="background-image: url(/files/' +
                                                bookmarks[i].files[0].filePath + '/' +
                                                bookmarks[i].files[0].fileUuid + '_' +
                                                bookmarks[i].files[0].fileName +
                        '                       );">\n' +
                        '                    <button id="' +
                                                        bookmarks[i].id +
                        '                               "\n' +
                        '                           class="bookmark-btn" type="button">\n' +
                        '                        <svg width="22" height="22" viewBox="0 0 18 18" fill="none" xmlns="https://www.w3.org/2000/svg">\n' +
                        '                            <path fill-rule="evenodd" clip-rule="evenodd" d="M3.58065 1C3.25997 1 3 1.26206 3 1.58533V16.4138C3 16.8632 3.48164 17.145 3.86873 16.922L9.00004 13.9662L14.1313 16.922C14.5184 17.145 15 16.8632 15 16.4138V1.58533C15 1.26206 14.74 1 14.4194 1H9.00004H3.58065ZM8.71195 12.7838C8.89046 12.681 9.10961 12.681 9.28812 12.7838L13.8387 15.4052V2.17067H9.00004H4.1613V15.4052L8.71195 12.7838Z" fill="white"></path><path d="M9.28812 12.7838C9.10961 12.681 8.89046 12.681 8.71195 12.7838L4.1613 15.4052V2.17067H9.00004H13.8387V15.4052L9.28812 12.7838Z" fill="#3366FF"></path>\n' +
                        '                        </svg>\n' +
                        '                    </button>\n' +
                        '                </header>\n' +
                        '                 <div class="body">\n' +
                        '                    <div class="card-theme">' +
                                                    bookmarks[i].themeTitle +
                        '                    </div>\n' +
                        '                    <div class="card-userName">' +
                                                    bookmarks[i].themeStartDate.split("T")[0] +
                                                    ' ~ ' +
                                                    bookmarks[i].themeEndDate.split("T")[0] +
                        '                    </div>\n' +
                        '                    <div class="card-grade">' +
                                                    content +
                        '                    </div>\n' +
                        '                    <div class="card-badge"></div>\n' +
                        '                </div>\n' +
                        '            </a>\n' +
                        '        </div>\n' +
                        '    </li>'
                })
            } else {
                text += "북마크 정보가 없습니다."
            }

            $bookmarkList.html(text);
        }
    });

    $(document).on('click', '.bookmark-btn', function(e) {
        console.log("북마크 버튼 클릭")
        console.log($(this).attr("id"));
        const bookmarkId = $(this).attr("id");

        $.ajax({
            type: "DELETE",
            url: "/api/myPages/bookmarks?bookmarkId="+bookmarkId,
            dataType: "text",
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (url) {
                showWarnModal("선택한 항목이 북마크에서 삭제되었습니다.")
                $(".modal").on("click", function () {
                    location.href = url
                })
                console.log("통신성공" + "OK")
            }
        });
    })
    
})