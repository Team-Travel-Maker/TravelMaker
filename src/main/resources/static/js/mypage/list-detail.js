$(document).ready(function() {
    // 문의 버튼의 글 목록을 기본으로 보이도록 설정
    showCategoryPosts("question");

    $(".select-category").on("click", function() {
        let category = $(this).data("category");

        console.log(category);

        // 선택한 카테고리의 글 목록을 보여주기 위한 함수 호출
        showCategoryPosts(category);
    });

    function showCategoryPosts(category) {
        // 모든 글 목록을 숨김 처리
        $(".recommendedPostView-wrapper").hide();
        $(".select-category").removeClass('now-button');
        // 선택한 카테고리의 글 목록만 보여줌
        $(".recommendedPostView-wrapper[data-category='" + category + "']").show();
        $(".select-category[data-category='" + category + "']").addClass('now-button');
    }




    // $(".post-title").on("click", function(e) {
    //     let postContent = $(this).data("content");
    //
    //     e.preventDefault(); // 기본 링크 동작 방지
    //     console.log("내용 펼치기!")
    //     console.log(postContent)
    //
    //     // 클릭한 글의 상세 내용 펼침/접힘 토글 (아래 방향)
    //
    //     // $(this).closest("tr").next(".post-content").slideToggle();
    //     $(".post-content[data-content='" + postContent + "']").slideToggle(300);
    //
    //     console.log($(this).closest("tr").next(".post-content"))
    // });

    $(".post-title").on("click", function (e) {
        e.preventDefault(); // 기본 링크 동작 방지

        let postContent = $(this).data("content");
        $(".post-content[data-content='" + postContent + "']").slideToggle('slow');
    });

    function slideContent(postContent) {

    }






});




// 체크박스

// $("#allSelect").click(function() {
//     if($("#allSelect").is(":checked")) $("input[name=check]").prop("checked", true);
//     else $("input[name=check]").prop("checked", false);
// });
//
// $("input[name=check]").click(function() {
//     var total = $("input[name=check]").length;
//     var checked = $("input[name=check]:checked").length;
//
//     if(total != checked) $("#allSelect").prop("checked", false);
//     else $("#allSelect").prop("checked", true);
// });
//
// const pageButton = $(".page-button");

