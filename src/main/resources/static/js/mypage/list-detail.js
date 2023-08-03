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


    $(".post-title").on("click", function (e) {
        e.preventDefault(); // 기본 링크 동작 방지

        let postContent = $(this).data("content");
        $(".post-content[data-content='" + postContent + "']").slideToggle('slow');
    });




    // 체크박스

    // 전체 선택 및 전체 선택 해제
    $("#allSelect").click(function() {
        if($("#allSelect").is(":checked")) {
            $("input[name=check]").prop("checked", true);
            // 체크박스 전체 선택 했을 때 삭제 버튼 활성화
            $(".Button-btn").addClass("now-button");
        }
        else {
            $("input[name=check]").prop("checked", false);
            // 체크박스 전체 선택 해제 했을 때 삭제 버튼 비활성화
            $(".Button-btn").removeClass("now-button");
        }
    });


    // 부분 선택 및 부분 선택 해제 & 모든 체크박스 선택했을 때 전체선택 버튼도 체크 되게
    $("input[name=check]").click(function() {
        let total = $("input[name=check]").length;
        let checked = $("input[name=check]:checked").length;


        // $(this).prop("checked") ? $(this).prop("checked") : !$(this).prop("checked")
        $(this).prop("checked") ? onChecked() : noChecked();

        if(total != checked){
            $("#allSelect").prop("checked", false);
        } else {
            $("#allSelect").prop("checked", true);
        }

        if(checked > 0){
            $(".Button-btn").addClass("now-button");
        } else {
            $(".Button-btn").removeClass("now-button");
        }

    });

    function onChecked() {
        $(this).prop("checked")
        $(".Button-btn").addClass("now-button");
    }

    function noChecked() {
        !$(this).prop("checked")
    }












});







