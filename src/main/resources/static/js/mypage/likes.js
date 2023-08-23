const $likeList = $('.like-list');
const type = {
    "COMMUNITY" : "커뮤니티",
    "STORY" : "스토리"
}
Object.freeze(type); // 객체를 동결시키기 위함
console.log(type.COMMUNITY);

$(document).ready(function () {

    console.log("좋아요 페이지")
    if ($('.like-type').val() == "COMMUNITY") getCommunityLikes();
    function getCommunityLikes() {
        $.ajax({
            type: "GET", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/communityLikes",
            dataType: "json",
            async: false,
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (likes) {
                let text = "";
                console.log("통신성공" + "OK");
                console.log(likes);
                if (likes.length > 0) {
                    $.each(likes, function (i) {
                        const content = likes[i].communityContent.length <= 25 ? likes[i].communityContent : likes[i].communityContent.substring(0, 25) + '...';
                        text += '<li id="' +
                                    likes[i].communityId +
                            '    ">\n' +
                            '        <div class="card">\n' +
                            '            <a href="#">\n' +
                            '                <header style="background-image: url(/api/files/display?fileName=' +
                                                    likes[i].files[0].filePath + '/' +
                                                    likes[i].files[0].fileUuid + '_' +
                                                    likes[i].files[0].fileName +
                            '                       );">\n' +
                            '                    <button id="' +
                                                            likes[i].id +
                            '                               "\n' +
                            '                        type="button" class="like-btn">\n' +
                            '                        <span class="svgIcon-root">\n' +
                            '                            <svg class="svgIcon-svg" viewBox="0 0 24 24">\n' +
                            '                                <path d="M11.9999 6.49201L13.4848 5.00461C15.5225 2.9634 18.8529 2.9634 20.8905 5.00445C22.9308 7.04707 22.9308 10.3876 20.8928 12.4291L13.4587 19.9397L13.4565 19.9419C13.067 20.332 12.5427 20.5339 11.9999 20.5261C11.4563 20.5339 10.9319 20.3321 10.5402 19.9397L3.10804 12.4311C1.06908 10.3875 1.06908 7.04719 3.10835 5.00445C5.14712 2.96345 8.47614 2.96345 10.5151 5.00461L11.9999 6.49201Z"></path>\n' +
                            '                            </svg>\n' +
                            '                        </span>' +
                                                        likes[i].communityLikeCount +
                            '                           \n' +
                            '                    </button>\n' +
                            '                </header>\n' +
                            '                <div class="body">\n' +
                            '                    <div class="card-theme">' +
                                                    likes[i].communityTitle +
                            '                    </div>\n' +
                            '                    <div class="card-userName">' +
                                                    content +
                            '                    </div>\n' +
                            '                    <div class="card-grade">' +
                                                    likes[i].memberName +
                            '                    </div>\n' +
                            '                    <div class="card-grade">' +
                                                    likes[i].updatedDate.split("T")[0] +
                            '                    </div>\n' +
                            '                    <div class="card-location"><span>' +
                                                    likes[i].communityCategory.name +
                            '                    </span></div>\n' +
                            '                    <div class="card-badge"></div>\n' +
                            '                </div>\n' +
                            '            </a>\n' +
                            '        </div>\n' +
                            '    </li>'

                    })
                } else {
                    text += "좋아요 정보가 없습니다."
                }

                $likeList.html(text);
            }
        });
    }

    function getStoryLikes() {
        $.ajax({
            type: "GET", //전송방식을 지정한다 (POST,GET)
            url: "/api/myPages/storyLikes",
            dataType: "json",
            async: false,
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (likes) {
                let text = "";
                console.log("통신성공" + "OK");
                console.log(likes);
                if (likes.length > 0) {
                    $.each(likes, function (i) {
                        const content = likes[i].storyContent.length <= 25 ? likes[i].storyContent : likes[i].storyContent.substring(0, 25) + '...';
                        text += '<li id="' +
                                    likes[i].storyId +
                            '    ">\n' +
                            '        <div class="card">\n' +
                            '            <a href="#">\n' +
                            '                <header style="background-image: url(/api/files/display?fileName=' +
                                                        likes[i].files[0].filePath + '/' +
                                                        likes[i].files[0].fileUuid + '_' +
                                                        likes[i].files[0].fileName +
                            '                       );">\n' +
                            '                    <button id="' +
                                                        likes[i].id +
                            '                               "\n' +
                            '                        type="button" class="like-btn">\n' +
                            '                        <span class="svgIcon-root">\n' +
                            '                            <svg class="svgIcon-svg" viewBox="0 0 24 24">\n' +
                            '                                <path d="M11.9999 6.49201L13.4848 5.00461C15.5225 2.9634 18.8529 2.9634 20.8905 5.00445C22.9308 7.04707 22.9308 10.3876 20.8928 12.4291L13.4587 19.9397L13.4565 19.9419C13.067 20.332 12.5427 20.5339 11.9999 20.5261C11.4563 20.5339 10.9319 20.3321 10.5402 19.9397L3.10804 12.4311C1.06908 10.3875 1.06908 7.04719 3.10835 5.00445C5.14712 2.96345 8.47614 2.96345 10.5151 5.00461L11.9999 6.49201Z"></path>\n' +
                            '                            </svg>\n' +
                            '                        </span>' +
                                                            likes[i].storyLikeCount +
                            '                           \n' +
                            '                    </button>\n' +
                            '                </header>\n' +
                            '                <div class="body">\n' +
                            '                    <div class="card-theme">' +
                                                            likes[i].storyTitle +
                            '                    </div>\n' +
                            '                    <div class="card-userName">' +
                                                            content +
                            '                    </div>\n' +
                            '                    <div class="card-grade">' +
                                                            likes[i].memberName +
                            '                    </div>\n' +
                            '                    <div class="card-grade">' +
                                                            likes[i].updatedDate.split("T")[0] +
                            '                    </div>\n' +
                            '                    <div class="card-badge"></div>\n' +
                            '                </div>\n' +
                            '            </a>\n' +
                            '        </div>\n' +
                            '    </li>'
                    })
                } else {
                    text += "좋아요 정보가 없습니다."
                }

                $likeList.html(text);
            }
        });
    }

    function deleteCommunityLike(likeId) {
        $.ajax({
            type: "DELETE",
            url: "/api/myPages/communityLikes?communityLikeId="+likeId,
            dataType: "text",
            async: false,
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (result) {
                console.log(result + "OK")
                showWarnModal("선택한 항목이 좋아요에서 삭제되었습니다.")
                $(".modal").on("click", function () {
                    getCommunityLikes()
                })
            }
        });
    }

    function deleteStoryLike(likeId) {
        $.ajax({
            type: "DELETE",
            url: "/api/myPages/storyLikes?storyLikeId="+likeId,
            dataType: "text",
            async: false,
            error: function () {
                alert("통신실패!!!!");
            },
            success: function (result) {
                console.log(result + "OK")
                showWarnModal("선택한 항목이 좋아요에서 삭제되었습니다.")
                $(".modal").on("click", function () {
                    getStoryLikes()
                })
            }
        });
    }

    // 커뮤니티, 스토리 좋아요 리스트 가져오기
    $(document).on('change', '.like-type', function(e) {
        const $likeTypeP = $('.like-type-p');
        console.log($(this).val())
        const likeType = $(this).val();
        if (likeType=="COMMUNITY") {
            $likeTypeP.text(type.COMMUNITY)
            getCommunityLikes();
        } else {
            $likeTypeP.text(type.STORY)
            getStoryLikes();
        }
        console.log("선택 바뀜");
    })

    // 커뮤니티, 스토리 좋아요 삭제
    $(document).on('click', '.like-btn', function(e) {
        console.log("좋아요 버튼 클릭")
        console.log($(this).attr("id"));
        console.log($('.like-type').val());

        const likeId = $(this).attr("id");

        if ($('.like-type').val()=="COMMUNITY") {
            deleteCommunityLike(likeId);
        } else {
            deleteStoryLike(likeId);
        }

    })
})