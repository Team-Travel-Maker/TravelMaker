const $categoryBtns = $('.category-Item');
const writeBtn = $("#goWrite");
const detailBtn = $(".goToDetail");
let memberId = $("input[name='memberId']").val();
let date = $("input[name='createTime']");
let $post = $(".PostItem_PostItem");
let postId = $("input[name='postId']").val();


$(document).ready(function () {
    $(".selected").addClass("category-btn-list")
    console.log($categoryBtns);

    $categoryBtns.on('click',function(){
        let communityType = $(this).find(".category-btn-label").attr("value");
        console.log(communityType);

        $.ajax({
            type : "get",
            url : "/api/communities/board/list",
            contentType : 'application/json',
            data : {communityType : communityType},
            success : function(data) { // 결과 성공 콜백함수
                console.log("성공 여부 : " + communityType);
                console.log(data)


                if (data && data.length > 0) {
                    console.log("글 목록 뿌리는 중")
                    let postList = $('.postReview');
                    postList.empty();

                    data.forEach(function(post) {
                        // 가져온 데이터를 사용하여 게시글 요소 생성
                        let postItem = '<article class="PostItem_PostItem" th:each="postList : ${postList}">';
                        postItem += '<input type="hidden" name="postId" id="postId" class="postId" th:value="${postList.id}" style="display: none">' + post.id;
                        postItem += '<div class="PostItem_PostItem_top">';
                        postItem += '<a href="">';
                        postItem += '<div class="AuthorBox_AuthorBox">'
                        postItem += '<div class="AuthorBox_AuthorBox_verticalArea">';
                        postItem += '<div class="AuthorBox_avatar_wrapper">';
                        postItem += '<div class="mycommunity-myprofile-img  avatar">';
                        postItem += '<img src="https://static.wanted.co.kr/oneid-user/6m2fVJuPVURemknYfFVHDf/d3WNVAQRrj829gFa2qz4vj.jpg">';
                        postItem += '</div></div>';
                        postItem += '<div class="AuthorBox_AuthorBox__verticalBox">';
                        postItem += '<div class="AuthorBox_AuthorBox__userInfo">';
                        postItem += '<div class="AuthorBox_AuthorBox_username username">' + post.memberName + '</div>';
                        postItem += '<div class="Author-wrapper">';
                        postItem += '<div class="UserInfoGrade">계란</div>';
                        postItem += '<div class="UserInfoRegion">서울</div>';
                        postItem += '</div></div>';
                        postItem += '<input type="hidden" class="createTime" name="createTime" style="display: none">' + post.createTime;
                        postItem += '<span class="postCreateTime create_time postElapsedTime createTime"></span>';
                        postItem += '</div></div></div></a></div>';
                        postItem += '<a class="PostItem_body" href="#">';
                        // 게시글 내용 및 정보를 가져와서 요소에 추가
                        postItem += '<h3 class="PostItem_title">' + post.postTitle + '</h3>';
                        postItem += '<p class="PostItem_content">' + post.postContent + '</p>';
                        // 게시글 태그 추가 (태그 데이터를 가져와서 반복)
                        // postItem += '<div class="PostItem_tag_list">';
                        // post.tags.forEach(function(tag) {
                        //     postItem += '<button type="button" class="PostItem_tag">' + tag + '</button>';
                        // });
                        // postItem += '</div>';
                        // 좋아요와 댓글 버튼 등의 내용을 추가
                        postItem += '</div></article>';




                        $("input[name='createTime']").each(function() {
                            let createTime = $(this).val();
                            let writeTime = new Date(createTime); // 작성 시간을 Date 객체로 파싱
                            let currentTime = new Date(); // 현재 시간을 가져옴
                            let timeDiff = currentTime - writeTime; // 시간 차이 계산 (밀리초 단위)

                            // 시간 차이를 경과 시간으로 변환 (초, 분, 시간 등)
                            let elapsedTime = calculateElapsedTime(timeDiff);

                            // 해당 게시글의 경과 시간을 표시할 요소를 선택하고 텍스트 설정
                            $(this).siblings(".postElapsedTime").text(elapsedTime);
                        })


                        // 게시글을 게시글 목록에 추가
                        postList.append(postItem);


                    });
                }
            },
            error : function(request, status, error) { // 결과 에러 콜백함수
                console.log("에러 : " + error)
            }

        })

        $categoryBtns.removeClass("category-btn-list");
        if(!$(this).hasClass("category-btn-list")){
            $(this).addClass("category-btn-list");
        }
    });



    // writeBtn.on('click', function () {
    //     console.log("글쓰기 버튼 클릭")
    //     $.ajax({
    //         type : 'get',           // 타입 (get, post, put 등등)
    //         url : '/community/board/write',           // 요청할 서버url
    //         success : function(result) { // 결과 성공 콜백함수
    //             // location.href = "write"
    //             console.log("성공 여부" + result);
    //         },
    //         error : function(request, status, error) { // 결과 에러 콜백함수
    //             console.log("에러 : " + error)
    //         }
    //     })
    // })

    console.log(memberId);

//    사이드 메뉴(나의 커뮤니티로 이동)
    detailBtn.on('click', function (e) {
        $.ajax({
            type : 'get',           // 타입 (get, post, put 등등)
            url : '/community/board/detail',           // 요청할 서버url
            success : function(result) { // 결과 성공 콜백함수
                location.href = "detail"
                console.log("성공 여부" + result);
            },
            error : function(request, status, error) { // 결과 에러 콜백함수
                console.log("에러 : " + error)
            }
        })
    })


    // 모든 게시글의 작성 시간 요소를 선택
        $("input[name='createTime']").each(function() {
            let createTime = $(this).val();
            let writeTime = new Date(createTime); // 작성 시간을 Date 객체로 파싱
            let currentTime = new Date(); // 현재 시간을 가져옴
            let timeDiff = currentTime - writeTime; // 시간 차이 계산 (밀리초 단위)

            // 시간 차이를 경과 시간으로 변환 (초, 분, 시간 등)
            let elapsedTime = calculateElapsedTime(timeDiff);

            // 해당 게시글의 경과 시간을 표시할 요소를 선택하고 텍스트 설정
            $(this).siblings(".postElapsedTime").text(elapsedTime);
        })

    // 시간 차이를 경과 시간으로 변환하는 함수
    function calculateElapsedTime(timeDiff) {
        let seconds = Math.floor(timeDiff / 1000);
        let minutes = Math.floor(seconds / 60);
        let hours = Math.floor(minutes / 60);
        let days = Math.floor(hours / 24);

        if (days > 0) {
            return days + "일 전";
        } else if (hours > 0) {
            return hours + "시간 전";
        } else if (minutes > 0) {
            return minutes + "분 전";
        } else {
            return "방금 전";
        }

    }


//    해당 글의 상세보기
    $(".PostItem_body").on('click', function () {
        console.log("디테일 페이지 가자")
        let id = $(this).closest(".PostItem_PostItem").find("input[name='postId']").val();
        console.log("postId : " + id);

        $.ajax({
            type : 'get',
            url : "/community/board/detail/" + id,
            success : function (result) {
                console.log("통신 성공!")
                location.href = "/community/board/detail/" + id;
            },
            error : function (request, status, error) {

            }
        })

    });






})





