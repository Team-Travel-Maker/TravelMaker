window.onload = function() {
    console.log("들어옴");
    const ul = document.getElementById('replies-ul');
    console.log(ul);
    const liCount = ul.getElementsByTagName('li').length;
    console.log(liCount);
    const percent = 100 / liCount;

    // 각 li 요소에 동적으로 퍼센트 값을 할당합니다.
    const liItems = ul.getElementsByTagName('li');
    for (let i = 0; i < liItems.length; i++) {
        liItems[i].style.width = percent + '%';
    }
}

$(document).ready(function() {
    // 업체 회원이면 등록 업체 개수 가져오기
    if ($('.member-type').val() == "COMPANY") {
        $.ajax({
            type: "GET", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/companyCount",
            contentType: "application/json",
            dataType: "text",
            error: function () {
                alert("등록 업체 개수 가져오기 실패!!!!");
            },
            success: function (result) {
                console.log("통신성공" + "OK");
                console.log(result);
                $('.company-cnt').text(result);
            }
        });
    }

    // 문의/신고 개수 가져오기
    $.ajax({
        type: "GET", //전송방식을 지정한다 (POST,GET)
        url: "/api/myPages/customServiceCount",
        contentType: "application/json",
        dataType: "text",
        error: function () {
            alert("문의/신고 개수 가져오기 실패!!!!");
        },
        success: function (result) {
            console.log("통신성공" + "OK");
            console.log(result);
            $('.custom-service-cnt').text(result);
        }
    });

    // 상품권 개수 가져오기
    $.ajax({
        type: "GET", //전송방식을 지정한다 (POST,GET)
        url: "/api/myPages/giftCardCount",
        contentType: "application/json",
        dataType: "text",
        error: function () {
            alert("상품권 개수 가져오기 실패!!!!");
        },
        success: function (result) {
            console.log("통신성공" + "OK");
            console.log(result);
            $('.gift-card-cnt').text(result)
        }
    });

    if($('.member-type').val() === "COMPANY") {
        // 내가 쓴 스토리 개수 가져오기
        $.ajax({
            type: "GET", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/storiesCount",
            dataType: "text",
            error: function () {
                alert("스토리 개수 가져오기 실패!!!!");
            },
            success: function (result) {
                console.log("통신성공" + "OK");
                console.log(result);
                $('.stories-count').text(result);
            }
        });
    }

    // 내가 쓴 커뮤니티 개수 가져오기
    $.ajax({
        type: "GET", //전송방식을 지정한다 (POST,GET)
        url: "/api/myPages/communitiesCount",
        dataType: "text",
        error: function () {
            alert("커뮤니티 개수 가져오기 실패!!!!");
        },
        success: function (result) {
            console.log("통신성공" + "OK");
            console.log(result);
            $('.communities-count').text(result);
        }
    });

    // 내가 쓴 에코 개수 가져오기
    $.ajax({
        type: "GET", //전송방식을 지정한다 (POST,GET)
        url: "/api/myPages/ecosCount",
        dataType: "text",
        error: function () {
            alert("에코 개수 가져오기 실패!!!!");
        },
        success: function (result) {
            console.log("통신성공" + "OK");
            console.log(result);
            $('.ecos-count').text(result);
        }
    });

    // 내가 쓴 같이가요 개수 가져오기
    $.ajax({
        type: "GET", //전송방식을 지정한다 (POST,GET)
        url: "/api/myPages/goWithsCount",
        dataType: "text",
        error: function () {
            alert("같이가요 개수 가져오기 실패!!!!");
        },
        success: function (result) {
            console.log("통신성공" + "OK");
            console.log(result);
            $('.goWiths-count').text(result);
        }
    });

    // 북마크 개수 가져오기
    $.ajax({
        type: "GET", //전송방식을 지정한다 (POST,GET)
        url: "/api/myPages/bookmarksCount",
        dataType: "text",
        error: function () {
            alert("북마크 개수 가져오기 실패!!!!");
        },
        success: function (result) {
            console.log("통신성공" + "OK");
            console.log(result);
            $('.bookmarks-count').text(result);
        }
    });
    // 북마크 최대 4개 가져오기
    $.ajax({
        type: "GET", //전송방식을 지정한다 (POST,GET)
        url: "/api/myPages/bookmarksMax4",
        dataType: "json",
        error: function () {
            alert("북마크 가져오기 실패!!!!");
        },
        success: function (bookmarks) {
            let text = "";
            console.log("통신성공" + "OK");
            console.log(bookmarks);
            if (bookmarks.length > 0) {
                $.each(bookmarks, function (i) {
                    const content = bookmarks[i].themeContent.length <= 20 ? bookmarks[i].themeContent : bookmarks[i].themeContent.substring(0, 20) + '...';
                    text += '<li class="manage-info-li">\n' +
                        '        <a href="#" class="">\n' +
                        '            <div class="manage-info-logo"\n' +
                        '                style="background-image: url(/api/files/display?fileName=' +
                                            bookmarks[i].files[0].filePath + '/' +
                                            bookmarks[i].files[0].fileUuid + '_' +
                                            bookmarks[i].files[0].fileName +
                        '                   );"></div>\n' +
                        '            <div class="manage-info-content">\n' +
                        '                <h3>' +
                                            bookmarks[i].themeTitle +
                        '                </h3>\n' +
                        '                <div>\n' +
                        '                    <p>' +
                                                content +
                        '                    </p>\n' +
                        '                    <span>' +
                                                bookmarks[i].themeStartDate.split("T")[0] +
                                                ' ~ ' +
                                                bookmarks[i].themeEndDate.split("T")[0] +
                        '                    </span>\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '        </a>\n' +
                        '    </li>'
                })
            } else {
                text += "북마크 정보가 없습니다."
            }

            $('.bookmarks-list').html(text);
        }
    });

    // 좋아요 개수 가져오기
    $.ajax({
        type: "GET", //전송방식을 지정한다 (POST,GET)
        url: "/api/myPages/likesCount",
        dataType: "text",
        error: function () {
            alert("좋아요 개수 가져오기 실패!!!!");
        },
        success: function (result) {
            console.log("통신성공" + "OK");
            console.log(result);
            $('.likes-count').text(result);
        }
    });

    // 좋아요 최대 6개,
    $.ajax({
        type: "GET", //전송방식을 지정한다 (POST,GET)
        url: "/api/myPages/likesMax6",
        dataType: "json",
        error: function () {
            alert("좋아요 가져오기 실패!!!!");
        },
        success: function (likes) {
            let text = ""
            console.log("통신성공" + "OK");
            console.log(likes);
            if (likes.length > 0) {
                $.each(likes, function (i) {
                    text += '<li class="manage-info-li">\n' +
                        '        <a href="#" class="">\n' +
                        '            <div class="manage-info-logo"\n' +
                        '                 style="background-image: url(/api/files/display?fileName=' +
                                                    likes[i].files[0].filePath + '/' +
                                                    likes[i].files[0].fileUuid + '_' +
                                                    likes[i].files[0].fileName +
                        '            );"></div>\n' +
                        '            <div class="manage-info-content">\n' +
                        '                <h3>' +
                                            likes[i].likeTitle +
                        '                </h3>\n' +
                        '                <div>\n' +
                        '                    <p>' +
                                            likes[i].likeType +
                        '                    </p>\n' +
                        '                    <span>' +
                                            likes[i].likeName +
                        '                    </span>\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '        </a>\n' +
                        '    </li>'
                })
            } else {
                text += "좋아요 정보가 없습니다."
            }

            $('.likes-list').html(text);
        }
    });
});