
const $storyBuyWrap = $('.theme-wrap');

$(window).scroll(function () {
    let height = $(document).scrollTop();
    if(height == 1000){
        $storyBuyWrap.addClass("not-fixed");
        $storyBuyWrap.removeClass("fixed");
        $storyBuyWrap.removeClass("story-buy-wrap");
    }else if(height <=1000){
        $storyBuyWrap.removeClass("not-fixed");
        $storyBuyWrap.addClass("fixed");
        $storyBuyWrap.addClass("story-buy-wrap");
    }
})

