const $detailSection = $('.communityPostDetail-contents');
// 게시글 상세 정보를 가져와서 화면에 표시하는 함수
// function displayPostDetails(gowith) {
//     document.getElementById("gowith-title").textContent = gowith.goWithTitle;
//     document.getElementById("gowith-content").textContent = gowith.goWithContent;
//     document.getElementById("gowith-username").textContent = gowith.member.memberName
//     document.getElementById('userinfo-badge').textContent = gowith.member.memberInterestRegion;
//     document.getElementById('region').textContent = gowith.goWithRegionType.name;
//     document.getElementById('gowith-reply').textContent = gowith.replyCount;
//     document.getElementById('mbti').textContent = gowith.goWithMbti;
//     document.getElementById("post-create").textContent = gowith.createdDate;
// };
// console.log("goWith : ", ${goWith});
// console.log(`goWithId :`${goWithId});
// // API에서 게시글 데이터 가져오기
// fetch("/api/goWith/goWith-detail/" + goWithId,{
//     method: "GET",
//     headers: {
//         "Content-Type": "application/json"
//     }
// })
//     .then(response => response.json())
//     .then(data => {
//         // 가져온 데이터를 화면에 표시
//         displayPostDetails(data);
//     })
//     .catch(error => {
//         console.error("Error fetching post details:", error);
//     });
//
//
// function displayPostDetails(data) {
// document.getElementById("gowith-title").textContent = gowith.goWithTitle;
// document.getElementById("gowith-content").textContent = gowith.goWithContent;
// document.getElementById("gowith-username").textContent = gowith.member.memberName
// document.getElementById('userinfo-badge').textContent = gowith.member.memberInterestRegion;
// document.getElementById('region').textContent = gowith.goWithRegionType.name;
// document.getElementById('gowith-reply').textContent = gowith.replyCount;
// document.getElementById('mbti').textContent = gowith.goWithMbti;
// document.getElementById("post-create").textContent = gowith.createdDate;

// const form = new FormData();
// const $detailSection =$('.communityPostDetail-contents');


// data.content.forEach(goWith => {

//             let profileImageSrc;
//             let member = goWith.member;
//             if (member.snsProfile) {
//                 profileImageSrc = member.snsProfile;
//             } else if (member.fileId) {
//                 profileImageSrc = getFileUrl(member.fileId);
//             } else {
//                 profileImageSrc = "https://static.wanted.co.kr/oneid-user/profile_default.png";
//             }
//             const postHtml = `
// <section class="communityPostDetail-contents">
//         <article class="postDetail-contents">
//         <div class="postContents-header">
//         <div class="postContents-author">
//         <a href="/mypage/main">
//         <div class="authorbox authorbox-xlarge">
//         <div class="authorBox-authorbox-verticalArea">
//         <div class="authorBox-avatarWrapper avatar-Wrapper">
//         <div class="userAvater authorBox-avater avatar">
//         <img src="${profileImageSrc}" alt="">
//
//         </div>
//         <div class="authorBox-vertical">
//         <div class="authorBox-authorbox-userinfo">
//         <div class="authorBox-authorBox-username username" id="gowith-username">${goWith.member.memberName}</div>
//         <div class="author-info">
//         <div class="userinfo-badge">${goWith.member.memberInterestRegion ? goWith.member.memberInterestRegion : ''}</div>
//         </div>
//         </div>
//         <span class="authorBox-authorbox-createAt create-time" id="post-create">${goWith.createdDate}</span></div>
//     </div>
//     </div>
//     </a>
//     </div>
//     <h1 itemprop="headline" class="postContents-title" id="gowith-title">${goWith.goWithTitle}</h1></div>
//     <div class="postContents-body" id="gowith-content">${goWith.goWithContent}</div>
//
// <div class="postContents-metaTags"></div>
//         <div class="postContents-image"><!--'            <img src="/api/files/display?fileName=-->
// <!--                gowith[i].files[0].filePath + "/" +-->
// <!--                gowith[i].files[0].fileUuid + "_" +-->
// <!--                gowith[i].files[0].fileName +-->
// <!--                    '           " style="width: 130px; height: 100px">\\n'-->
// <!--    <img src="/images/mypage/대표이미지없음.png" style="width: 300px; height: 100px">-->
// </div>
//         </div>
//         <div class="postContents-tags">
//         <a class="postContents-tag-btn"id="mbti">${goWith.goWithRegionType.name}</a>
//         <a class="postContents-tag-btn"id="region" >${goWith.goWithMbti}</a></div>
//     <div class="postContents-btnActions">
//         <div class="postContents-bottomActions-comment">
//         <button type="button" class="btn-lgb">
//         <svg width="24" height="24" viewBox="0 0 24 24">
//         <path fill="currentColor" transform="matrix(-1 0 0 1 24 0)"
//     d="M9.826 19.561c.71.125 1.438.189 2.174.189 5.912 0 10.75-4.112 10.75-9.25S17.912 1.25 12 1.25c-5.913 0-10.75 4.112-10.75 9.25 0 3.273 1.841 6.389 4.882 7.747a.75.75 0 0 0 .611-1.37C4.267 15.772 2.75 13.205 2.75 10.5c0-4.251 4.116-7.75 9.25-7.75 5.133 0 9.25 3.5 9.25 7.75s-4.117 7.75-9.25 7.75c-.747 0-1.482-.074-2.194-.22a.75.75 0 0 0-.578.12l-4.656 3.234a.75.75 0 0 0 .856 1.232l4.398-3.055z"></path>
//         </svg>
//         <span class="btn-count-L1 count"id="gowith-reply">1</span></button>
//     </div>
//     <button type="button" class="postContent-menu-link">
//         <span class="svgIcon-root"><svg
// class="svgIcon-root-svg" viewBox="0 0 24 24"><path fill-rule="evenodd" clip-rule="evenodd" d="M20.8419 5.13156C20.8419 3.34425 19.3929 1.89474 17.6051 1.89474C15.8181 1.89474 14.3682 3.34457 14.3682 5.13156L14.3777 5.37944C14.384 5.46157 14.3934 5.54316 14.4058 5.62409L14.4152 5.67411L6.48256 9.86022L6.41109 9.78015C5.79271 9.13677 4.91641 8.75021 3.9697 8.75021C2.1352 8.75021 0.631592 10.194 0.631592 11.9933C0.631592 13.7933 2.13485 15.2364 3.9697 15.2364L4.20171 15.2286C5.04647 15.1719 5.82161 14.8075 6.3874 14.2307L6.45098 14.1587L15.0769 18.7102C15.2605 18.8071 15.4707 18.841 15.6754 18.8067L15.7022 18.8022L15.8292 18.7718C16.077 18.6942 16.2842 18.5172 16.3988 18.28L16.4722 18.148C16.5528 18.0206 16.6553 17.9073 16.7783 17.8116C17.3632 17.3544 18.2064 17.4579 18.6625 18.0422C19.1191 18.627 19.0157 19.4704 18.4326 19.9256C17.8475 20.3823 17.004 20.2788 16.5478 19.6945L16.4536 19.5905C16.1191 19.272 15.593 19.238 15.2181 19.5307C14.8057 19.8527 14.7324 20.4481 15.0543 20.8605C16.1547 22.2699 18.1889 22.5196 19.5985 21.4192C21.007 20.3199 21.2563 18.2855 20.156 16.8762C19.0556 15.4667 17.0211 15.2169 15.6126 16.3178L15.4226 16.4779C15.3616 16.5334 15.303 16.5911 15.2426 16.6547L6.6973 12.1478L6.67428 12.1292C6.60376 12.0831 6.52833 12.0476 6.45025 12.0225L6.41688 12.0139L6.49246 11.9899C6.53023 11.9752 6.56816 11.9579 6.60613 11.9379L15.9611 7.00213L16.1289 6.88917L16.159 6.86384L16.2612 6.76385C16.5102 6.48039 16.57 6.07014 16.4003 5.72293L16.3408 5.5818C16.2895 5.43808 16.263 5.28694 16.263 5.13156C16.263 4.391 16.8645 3.78948 17.6051 3.78948C18.3463 3.78948 18.9471 4.39053 18.9471 5.13156C18.9471 5.87326 18.3467 6.47363 17.6051 6.47363L17.4765 6.48228C17.0141 6.54501 16.6577 6.94138 16.6577 7.421C16.6577 7.94422 17.0818 8.36837 17.6051 8.36837C19.3932 8.36837 20.8419 6.91969 20.8419 5.13156ZM2.52634 11.9933C2.52634 11.2581 3.16483 10.6449 3.9697 10.6449L4.14547 10.6549C4.60709 10.7073 5.00699 10.9637 5.22962 11.334L5.26108 11.395L5.28192 11.4515C5.38641 11.702 5.59409 11.9092 5.85157 12.0023L5.89393 12.0152L5.85009 12.0269C5.61829 12.1049 5.41331 12.2729 5.296 12.5268L5.22001 12.668C4.96724 13.0762 4.49734 13.3416 3.9697 13.3416C3.16435 13.3416 2.52634 12.7292 2.52634 11.9933Z"></path></svg></span>
//     </button>
//     <button type="button" class="postcontent-menu-btn">
//         <svg width="24" height="24" viewBox="0 0 24 24">
//         <path fill="currentColor"
//     d="M12 10a2 2 0 1 1-.001 4.001A2 2 0 0 1 12 10zm7 0a2 2 0 1 1-.001 4.001A2 2 0 0 1 19 10zM5 10a2 2 0 1 1-.001 4.001A2 2 0 0 1 5 10z"></path>
//         </svg>
//         </button>
//         </div>
//         </article>
//         <!--        댓글-->
//         <div class="communitypostDetail-commentsview">
//         <div class="commentItem-commtentItem">
//         <div class="commentItem-commtentItem-header"><a href="/community/profile/WuUkA24ewprFEKnbvXCDB4">
//         <div class="authorbox authorbox-xlarge commentItem-commtentItem-author">
//         <div class="authorBox-authorbox-verticalArea">
//         <div class="authorBox-avatarWrapper avatar-Wrapper">
//         <div class="userAvater authorBox-avater avatar"><img
//     src="https://static.wanted.co.kr/images/avatars/2958997/284bd3e6.jpg" alt="">
//         </div>
//         </div>
//         <div class="authorBox-vertical">
//         <div class="authorBox-authorbox-userinfo">
//         <div class="authorBox-authorBox-username username">원티드 AI</div>
//     </div>
//     <span class="authorBox-authorbox-createAt create-time">4분 전</span></div>
//     </div>
//     </div>
//     </a>
//     <button type="button" class="commentItem-commtentmenu">
//         <svg width="18" height="18" viewBox="0 0 24 24">
//         <path fill="currentColor"
//     d="M12 10a2 2 0 1 1-.001 4.001A2 2 0 0 1 12 10zm7 0a2 2 0 1 1-.001 4.001A2 2 0 0 1 19 10zM5 10a2 2 0 1 1-.001 4.001A2 2 0 0 1 5 10z"></path>
//         </svg>
//         </button>
//         </div>
//         <div class="commtentItem-content">설문조사에 참여해 주셔서 감사합니다! 저희 DND 6팀은 온라인 강의 관리 서비스를 기획
//     중입니다. 설문 결과를 토대로 더 나은 서비스를 제공할 수 있도록 노력하겠습니다. 기프티콘 추첨에 참여하시는 것도 잊지 마세요!
//     </div>
//     </div>
//     <div class="commentWrite-commtentWrite">
//         <div class="commentWrite-commtentWrite-top">
//         <div class="userAvater commentWrite-commtentWrite-avatar"><img
//     src="https://static.wanted.co.kr/oneid-user/profile_default.png" alt=""></div>
//         <span class="commentWrite-commtentWrite-username">김광민</span></div>
//     <div class="commentWrite-commtentWrite-bottom">
//         <form><textarea class="commentWrite-commtentWrite-textarea" placeholder="댓글 남기기"></textarea>
//         <button class="button-root button-contained button-containedPrimary button-containedSizeMedium button-containedDisabled button-disabled commentwrite-saveBtn" type="submit" disabled="">
//         <span class="btn-label">등록</span></button>
//     </form>
//     </div>
//     </div>
//     <button class="button-root btn-outlined btn-outlinedAssistive btn-outlinedSizeMedium communityPostDetail-bakckList"
//     type="button">
//         <span class="btn-label"><span
// class="btn-startIcon btn-startIcon-sizeMedium"><span
// class="svgIcon-root"><svg class="svgIcon-root-svg"
//     viewBox="0 0 24 24"><path
//     d="M15.42 20.7879C14.9267 21.2812 14.1269 21.2812 13.6336 20.7879L5.73887 12.8932C5.24557 12.3999 5.24557 11.6001 5.73887 11.1068L13.6336 3.21207C14.1269 2.71878 14.9267 2.71878 15.42 3.21207C15.9133 3.70537 15.9133 4.50515 15.42 4.99845L8.41843 12L15.42 19.0015C15.9133 19.4948 15.9133 20.2946 15.42 20.7879Z"></path></svg></span></span>목록으로</span><span
// class="btn-interaction"></span></button>
//     </div>
//    </section>`
//             $detailSection.append(postHtml);
//         }
//     )};
//
$(document).ready(function () {
    showdetail;
});

function showdetail() {
    $.ajax({
        url: "/api/goWith/goWith-detail/" +goWithId,
        method: 'GET',
        dataType: 'json',
        success: function (response) {
            console.log(response);
            console.log("성공");

            response.content.forEach(function (goWith) {
                let $postItem = $('<section class="communityPostDetail-contents"></section>');
                let profileImageSrc;
                let member = goWith.member;

                if (member.snsProfile) {
                    profileImageSrc = member.snsProfile;
                } else if (member.fileId) {
                    profileImageSrc = getFileUrl(member.fileId);
                } else {
                    profileImageSrc = "https://static.wanted.co.kr/oneid-user/profile_default.png";
                }
                console.log(member.snsProfile);
                console.log("snsprofile")

                $postItem.append(`
<!--                <section class="communityPostDetail-contents">-->
                        <article class="postDetail-contents">
                        <div class="postContents-header">
                        <div class="postContents-author">
                        <a href="/mypage/main">
                        <div class="authorbox authorbox-xlarge">
                        <div class="authorBox-authorbox-verticalArea">
                        <div class="authorBox-avatarWrapper avatar-Wrapper">
                        <div class="userAvater authorBox-avater avatar">
                        <img src="${profileImageSrc}" alt="">
                        
                        </div>
                        <div class="authorBox-vertical">
                        <div class="authorBox-authorbox-userinfo">
                        <div class="authorBox-authorBox-username username" id="gowith-username">${goWith.member.memberName}</div>
                        <div class="author-info">
                        <div class="userinfo-badge">${goWith.member.memberInterestRegion ? goWith.member.memberInterestRegion : ''}</div>
                        </div>
                        </div>
                        <span class="authorBox-authorbox-createAt create-time" id="post-create">${goWith.createdDate}</span></div>
                    </div>
                    </div>
                    </a>
                    </div>
                    <h1 itemprop="headline" class="postContents-title" id="gowith-title">${goWith.goWithTitle}</h1></div>
                    <div class="postContents-body" id="gowith-content">${goWith.goWithContent}</div>
                
                <div class="postContents-metaTags"></div>
                        <div class="postContents-image"><!--'            <img src="/api/files/display?fileName=-->
                <!--                gowith[i].files[0].filePath + "/" +-->
                <!--                gowith[i].files[0].fileUuid + "_" +-->
                <!--                gowith[i].files[0].fileName +-->
                <!--                    '           " style="width: 130px; height: 100px">\\n'-->
                <!--    <img src="/images/mypage/대표이미지없음.png" style="width: 300px; height: 100px">-->
                </div>
                        </div>
                        <div class="postContents-tags">
                        <a class="postContents-tag-btn"id="mbti">${goWith.goWithRegionType.name}</a>
                        <a class="postContents-tag-btn"id="region" >${goWith.goWithMbti}</a></div>
                    <div class="postContents-btnActions">
                        <div class="postContents-bottomActions-comment">
                        <button type="button" class="btn-lgb">
                        <svg width="24" height="24" viewBox="0 0 24 24">
                        <path fill="currentColor" transform="matrix(-1 0 0 1 24 0)"
                    d="M9.826 19.561c.71.125 1.438.189 2.174.189 5.912 0 10.75-4.112 10.75-9.25S17.912 1.25 12 1.25c-5.913 0-10.75 4.112-10.75 9.25 0 3.273 1.841 6.389 4.882 7.747a.75.75 0 0 0 .611-1.37C4.267 15.772 2.75 13.205 2.75 10.5c0-4.251 4.116-7.75 9.25-7.75 5.133 0 9.25 3.5 9.25 7.75s-4.117 7.75-9.25 7.75c-.747 0-1.482-.074-2.194-.22a.75.75 0 0 0-.578.12l-4.656 3.234a.75.75 0 0 0 .856 1.232l4.398-3.055z"></path>
                        </svg>
                        <span class="btn-count-L1 count"id="gowith-reply">1</span></button>
                    </div>
                    <button type="button" class="postContent-menu-link">
                        <span class="svgIcon-root"><svg
                class="svgIcon-root-svg" viewBox="0 0 24 24"><path fill-rule="evenodd" clip-rule="evenodd" d="M20.8419 5.13156C20.8419 3.34425 19.3929 1.89474 17.6051 1.89474C15.8181 1.89474 14.3682 3.34457 14.3682 5.13156L14.3777 5.37944C14.384 5.46157 14.3934 5.54316 14.4058 5.62409L14.4152 5.67411L6.48256 9.86022L6.41109 9.78015C5.79271 9.13677 4.91641 8.75021 3.9697 8.75021C2.1352 8.75021 0.631592 10.194 0.631592 11.9933C0.631592 13.7933 2.13485 15.2364 3.9697 15.2364L4.20171 15.2286C5.04647 15.1719 5.82161 14.8075 6.3874 14.2307L6.45098 14.1587L15.0769 18.7102C15.2605 18.8071 15.4707 18.841 15.6754 18.8067L15.7022 18.8022L15.8292 18.7718C16.077 18.6942 16.2842 18.5172 16.3988 18.28L16.4722 18.148C16.5528 18.0206 16.6553 17.9073 16.7783 17.8116C17.3632 17.3544 18.2064 17.4579 18.6625 18.0422C19.1191 18.627 19.0157 19.4704 18.4326 19.9256C17.8475 20.3823 17.004 20.2788 16.5478 19.6945L16.4536 19.5905C16.1191 19.272 15.593 19.238 15.2181 19.5307C14.8057 19.8527 14.7324 20.4481 15.0543 20.8605C16.1547 22.2699 18.1889 22.5196 19.5985 21.4192C21.007 20.3199 21.2563 18.2855 20.156 16.8762C19.0556 15.4667 17.0211 15.2169 15.6126 16.3178L15.4226 16.4779C15.3616 16.5334 15.303 16.5911 15.2426 16.6547L6.6973 12.1478L6.67428 12.1292C6.60376 12.0831 6.52833 12.0476 6.45025 12.0225L6.41688 12.0139L6.49246 11.9899C6.53023 11.9752 6.56816 11.9579 6.60613 11.9379L15.9611 7.00213L16.1289 6.88917L16.159 6.86384L16.2612 6.76385C16.5102 6.48039 16.57 6.07014 16.4003 5.72293L16.3408 5.5818C16.2895 5.43808 16.263 5.28694 16.263 5.13156C16.263 4.391 16.8645 3.78948 17.6051 3.78948C18.3463 3.78948 18.9471 4.39053 18.9471 5.13156C18.9471 5.87326 18.3467 6.47363 17.6051 6.47363L17.4765 6.48228C17.0141 6.54501 16.6577 6.94138 16.6577 7.421C16.6577 7.94422 17.0818 8.36837 17.6051 8.36837C19.3932 8.36837 20.8419 6.91969 20.8419 5.13156ZM2.52634 11.9933C2.52634 11.2581 3.16483 10.6449 3.9697 10.6449L4.14547 10.6549C4.60709 10.7073 5.00699 10.9637 5.22962 11.334L5.26108 11.395L5.28192 11.4515C5.38641 11.702 5.59409 11.9092 5.85157 12.0023L5.89393 12.0152L5.85009 12.0269C5.61829 12.1049 5.41331 12.2729 5.296 12.5268L5.22001 12.668C4.96724 13.0762 4.49734 13.3416 3.9697 13.3416C3.16435 13.3416 2.52634 12.7292 2.52634 11.9933Z"></path></svg></span>
                    </button>
                    <button type="button" class="postcontent-menu-btn">
                        <svg width="24" height="24" viewBox="0 0 24 24">
                        <path fill="currentColor"
                    d="M12 10a2 2 0 1 1-.001 4.001A2 2 0 0 1 12 10zm7 0a2 2 0 1 1-.001 4.001A2 2 0 0 1 19 10zM5 10a2 2 0 1 1-.001 4.001A2 2 0 0 1 5 10z"></path>
                        </svg>
                        </button>
                        </div>
                        </article>
                        <!--        댓글-->
                        <div class="communitypostDetail-commentsview">
                        <div class="commentItem-commtentItem">
                        <div class="commentItem-commtentItem-header"><a href="/community/profile/WuUkA24ewprFEKnbvXCDB4">
                        <div class="authorbox authorbox-xlarge commentItem-commtentItem-author">
                        <div class="authorBox-authorbox-verticalArea">
                        <div class="authorBox-avatarWrapper avatar-Wrapper">
                        <div class="userAvater authorBox-avater avatar"><img
                    src="https://static.wanted.co.kr/images/avatars/2958997/284bd3e6.jpg" alt="">
                        </div>
                        </div>
                        <div class="authorBox-vertical">
                        <div class="authorBox-authorbox-userinfo">
                        <div class="authorBox-authorBox-username username">원티드 AI</div>
                    </div>
                    <span class="authorBox-authorbox-createAt create-time">4분 전</span></div>
                    </div>
                    </div>
                    </a>
                    <button type="button" class="commentItem-commtentmenu">
                        <svg width="18" height="18" viewBox="0 0 24 24">
                        <path fill="currentColor"
                    d="M12 10a2 2 0 1 1-.001 4.001A2 2 0 0 1 12 10zm7 0a2 2 0 1 1-.001 4.001A2 2 0 0 1 19 10zM5 10a2 2 0 1 1-.001 4.001A2 2 0 0 1 5 10z"></path>
                        </svg>
                        </button>
                        </div>
                        <div class="commtentItem-content">설문조사에 참여해 주셔서 감사합니다! 저희 DND 6팀은 온라인 강의 관리 서비스를 기획
                    중입니다. 설문 결과를 토대로 더 나은 서비스를 제공할 수 있도록 노력하겠습니다. 기프티콘 추첨에 참여하시는 것도 잊지 마세요!
                    </div>
                    </div>
                    <div class="commentWrite-commtentWrite">
                        <div class="commentWrite-commtentWrite-top">
                        <div class="userAvater commentWrite-commtentWrite-avatar"><img
                    src="https://static.wanted.co.kr/oneid-user/profile_default.png" alt=""></div>
                        <span class="commentWrite-commtentWrite-username">김광민</span></div>
                    <div class="commentWrite-commtentWrite-bottom">
                        <form><textarea class="commentWrite-commtentWrite-textarea" placeholder="댓글 남기기"></textarea>
                        <button class="button-root button-contained button-containedPrimary button-containedSizeMedium button-containedDisabled button-disabled commentwrite-saveBtn" type="submit" disabled="">
                        <span class="btn-label">등록</span></button>
                    </form>
                    </div>
                    </div>
                    <button class="button-root btn-outlined btn-outlinedAssistive btn-outlinedSizeMedium communityPostDetail-bakckList"
                    type="button">
                        <span class="btn-label"><span
                class="btn-startIcon btn-startIcon-sizeMedium"><span
                class="svgIcon-root"><svg class="svgIcon-root-svg"
                    viewBox="0 0 24 24"><path
                    d="M15.42 20.7879C14.9267 21.2812 14.1269 21.2812 13.6336 20.7879L5.73887 12.8932C5.24557 12.3999 5.24557 11.6001 5.73887 11.1068L13.6336 3.21207C14.1269 2.71878 14.9267 2.71878 15.42 3.21207C15.9133 3.70537 15.9133 4.50515 15.42 4.99845L8.41843 12L15.42 19.0015C15.9133 19.4948 15.9133 20.2946 15.42 20.7879Z"></path></svg></span></span>목록으로</span><span
                class="btn-interaction"></span></button>
                    </div>`);
                   // </section>`);

                $detailSection.append($postItem);
            });


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