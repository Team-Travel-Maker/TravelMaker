const $myProfileBtn = $('.profile-button');

const $mypageMenuBar = $('.mypage-dropdown-menu-container');

$myProfileBtn.on('click', function() {
    if ($mypageMenuBar.css("display") == "none") {
        $mypageMenuBar.show();
    } else {
        $mypageMenuBar.hide();
    }
})