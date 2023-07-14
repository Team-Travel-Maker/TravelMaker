// 태그 슬라이드 버튼으로 스크롤 이동

$(document).ready(function () {

    $(".tagSlide-rightArrow").click(function (e) {
        $(".tagSlide").scrollLeft($(".tagSlide").scrollLeft() + 300).animate(screenTop, 200);
        $(".tagSlide-arrow").css("display", "flex");
        $(".tagSlide-rightArrow").css("display", "none");
    })

    $(".tagSlide-leftArrow").click(function (e) {
        $(".tagSlide").scrollLeft($(".tagSlide").scrollLeft() - 260).animate(screenTop, 200);
        $(".tagSlide-leftArrow").css("display", "none");
        $(".tagSlide-rightArrow").css("display", "flex");
    })

})