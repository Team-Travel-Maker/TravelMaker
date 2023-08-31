const $goWithList = $('.postItem');
let currentPage = 0;
const itemsPerPage = 10;
let loading = false;
let regionCode = null;

$(document).ready(function () {
    loadGoWithList(); // 초기 로딩 시 전체 목록

    $(window).on('scroll', function () {
        if ($(window).scrollTop() + $(window).height() >= $goWithList.height() && !loading) {
            currentPage++;
            loadGoWithList();
        }
    });

    // 처음 페이지 로딩 시 전체 선택
    $(".location-category span:contains('전체')").parent().addClass("active");

    $(".location-category").on("click", function () {
        $(".location-category").removeClass("active");
        $(this).addClass("active");

        let selectedLocation = $(this).find("span").text();
        console.log(selectedLocation);


        switch (selectedLocation) {
            case "전체":
                regionCode = "ALL";
                break;
            case "서울":
                regionCode = "SEOUL";
                break;
            case "경기도":
                regionCode = "GYEONGGI_DO";
                break;
            case "인천":
                regionCode = "INCHEON";
                break;
            case "강원도":
                regionCode = "GANG_WONDO";
                break;
            case "세종":
                regionCode = "SEJONG";
                break;
            case "충청남도":
                regionCode = "CHUNGCHEONGNAM_DO";
                break;
            case "충청북도":
                regionCode = "CHUNGCHEONG_BUKDO";
                break;
            case "대전":
                regionCode = "DAEJEON";
                break;
            case "전라남도":
                regionCode = "JEOLLANAM_DO";
                break;
            case "전라북도":
                regionCode = "JEOLLABUK_DO";
                break;
            case "광주":
                regionCode = "GWANGJU";
                break;
            case "경상남도":
                regionCode = "GYEONGSANGNAM_DO";
                break;
            case "경상북도":
                regionCode = "GYEONGSANGNBUK_DO";
                break;
            case "대구":
                regionCode = "DAEGU";
                break;
            case "울산":
                regionCode = "ULSAN";
                break;
            case "부산":
                regionCode = "BUSAN";
                break;
            case "제주도":
                regionCode = "JEJU_ISLAND";
                break;
            default:
                regionCode = null;
                break;
        }

        currentPage = 0; // 새로운 지역을 선택했으므로 페이지를 0으로 초기화
        loadGoWithList();
    });
});

function loadGoWithList() {
    loading = true;

    $.ajax({
        type: 'get',
        url: '/api/goWith/goWith-list',
        data: {regionType: regionCode, page: currentPage, size: itemsPerPage},
        success: function (response) {
            console.log(response);
            console.log("성공");
            if (currentPage === 0) {
                $goWithList.empty();
            }

            response.content.forEach(function (goWith) {
                let $postItem = $('<article class="postItem"></article>');
                let member = goWith.member;

                console.log("snsprofile")
                console.log(member.snsProfile);
                let profileImageSrc;

                if (member.snsProfile) {
                    profileImageSrc = member.snsProfile;
                } else if (member.fileId) {
                    profileImageSrc = getFileUrl(member.fileId);
                } else {
                    profileImageSrc = "https://static.wanted.co.kr/oneid-user/profile_default.png";
                }
                $postItem.append(`
                    <div class="postItem-top">
                        <a href="/api/goWith/goWith-detail/${goWith.id}">
                            <div class="userInfo-postBox">
                                <div class="userInfo-verticalArea">
                                    <div class="userInfo-avatarWrapper avatar-wrapper">
                                        <div class="userAvatar userInfo-avatar avatar">
                                                <img src="${profileImageSrc}" alt="">
                                        </div>
                                    </div>
                                    <div class="userInfo-verticalBox">
                                        <div class="userInfo-wrapper">
                                            <div class="userInfo-userName userName">${goWith.member.memberName}</div>
                                            <div class="userInfo-userCareer">
                                                <div class="userInfo-userBadge">${goWith.member.memberInterestRegion ? goWith.member.memberInterestRegion : ''}
                                                </div>
                                            </div>
                                        </div>
                                        <span class="userInfo-createAt create-time">${goWith.createdDate}</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <a class="postItem-body postItem-image" href="/api/goWith/goWith-detail/${goWith.id}">
                        <h3 class="postItem-title">${goWith.goWithTitle}</h3>
                        <p class="postItem-content">
                            ${goWith.goWithContent}
                        </p>
                        <picture>
                            <source media="(min-width: 1200px)">
                            ${goWith.files[0] ? `<img class="postItemImage" src="${getFileUrl(goWith.files[0])}" alt="GoWith Image">` : ''}
                        </picture>
                        <div class="postItem-tagList">
                            <button type="button" class="postItem-tag">${goWith.goWithRegionType.name}</button>
                            <button type="button" class="postItem-tag">${goWith.goWithMbti}</button>
                        </div>
                        <div class="postItem-bottom">
                           
                            <div class="Button-btn postItem-comments" >
                                <svg width="18" height="18" viewBox="0 0 18 18">
                                    <path fill="currentColor" transform="matrix(-1 0 0 1 18 0)" d="M9 1c4.377 0 8 3.14 8 7s-3.623 7-8 7c-.317 0-.593-.026-.954-.088l-.395-.074-.205-.043-3.295 2.089a.75.75 0 0 1-.968-.143l-.067-.09a.75.75 0 0 1 .143-.968l.09-.067 3.55-2.25a.75.75 0 0 1 .551-.1l.652.132.301.052c.228.036.408.05.597.05 3.592 0 6.5-2.52 6.5-5.5S12.592 2.5 9 2.5C5.407 2.5 2.5 5.02 2.5 8c0 1.858 1.039 3.573 2.773 4.348a.75.75 0 1 1-.612 1.37C2.37 12.693 1 10.432 1 8c0-3.86 3.622-7 8-7z"></path>
                                </svg>
                                <span class="Button-count count">${goWith.replyCount}</span>
                            </div>
                        </div>
                    </a>
                `);

                $goWithList.append($postItem);
            });

            if (response.content.length === 0 && currentPage === 0) {
                $goWithList.html("<p>데이터가 없습니다.</p>");
            }

            loading = false;
        },
        error: function (error) {
            console.log("에러: " + error);
            alert("가져오기 실패 !");
            loading = false;
        }
    });
}

function getFileUrl(file) {
    return file ? `/api/files/display?fileName=${file.filePath}/${file.fileUuid}_${file.fileName}` : '';
}