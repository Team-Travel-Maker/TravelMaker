const $myProfileBtn = $('.profile-button');

const $mypageMenuBar = $('.mypage-dropdown-menu-container');

$myProfileBtn.on('click', function() {
    console.log("들어옴")
    if ($mypageMenuBar.css("display") == "none") {
        $mypageMenuBar.show();
    } else {
        $mypageMenuBar.hide();
    }
})