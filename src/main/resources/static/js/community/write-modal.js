// const $openModal = $('#write-openModal');
//     console.log($openModal);
//
//     $openModal.on('click',function(){
//
//     })
let modalMessage = `<div class="modal-container modal-scroll write–modal-body">
    <div class="modal-title selectTagsModal-title">
        <h4>태그 선택</h4>
        <span class="select-tagsModal">2</span>
        <button type="button" class="modal-close">
            <span class="SvgIcon_SvgIcon__root__8vwon"><svg class="SvgIcon_SvgIcon__root__svg__DKYBi" viewBox="0 0 24 24"><path d="M17.97 19.03a.749.749 0 1 0 1.06-1.06l-6.5-6.5a.749.749 0 0 0-1.06 0l-6.5 6.5a.749.749 0 1 0 1.06 1.06L12 13.06l5.97 5.97zM12 10.94 6.03 4.97a.749.749 0 1 0-1.06 1.06l6.5 6.5a.749.749 0 0 0 1.06 0l6.5-6.5a.749.749 0 1 0-1.06-1.06L12 10.94z"></path></svg></span>
        </button>
    </div>
    <div class="modalContent-ModalContent">
        <div class="selectTagsModal-SelectTagsModal">
            <p class="selectTagsModal-message">작성글 주제에 맞는 태그를 선택해주세요. (1~3개)</p>
            <div class="interestsTags">
                <div class="interestsTags-interestsTags-curation">
                    <ul>
                        <li class="curationTags">
                            <div class="curationTags-iconImg"><img src="https://static.wanted.co.kr/images/tags/0a15b242.png" alt="직장인 공감"><span class="curationTags-tagTitle">지역</span></div>
                            <ul class="curationTags-subTags">
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                        <span>서 울</span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                부 산
                                                            </span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                인 천
                                                            </span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                대 구
                                                            </span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                대 전
                                                            </span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                세 종
                                                            </span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                광 주
                                                            </span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                울 산
                                                            </span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                경기도
                                                            </span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                충청북도
                                                            </span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                충청남도
                                                            </span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                전라북도
                                                            </span>
                                    </button>
                                </li><li>
                                <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                전라남도
                                                            </span>
                                </button>
                            </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                경상북도
                                                            </span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                경상남도
                                                            </span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                강원도
                                                            </span>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="location-category" style="transform: translateX(0px);">
                                                            <span>
                                                                제주도
                                                            </span>
                                    </button>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-action select-action">
        <button class="modal-btn btn-contained btn-containedPrimary btn-containedSizeLarge btn-fullwidth">
            <span class="button-label">완료</span><span class="btn-interaction"></span>
        </button>
    </div>
</div>`;


let modalCheck;

function showModal(modalMessage) {
    modalCheck = false;
    $("div#content-wrap").html(modalMessage)
    $("div.warn-modal").css("animation", "popUp 0.5s");
    $("div.modal").css("display", "flex");

}

//
$("div.modal").on("click", function () {
    const $closeModal = $('.modal-close');
    if (modalCheck) {
        $("div.warn-modal").css("animation", "popDown 0.5s");
        $("div.modal").fadeOut(500);
    }
    $closeModal.on('click',function(){
        // modalCheck = false;
        $("div.modal").css("display","none");
        // $("div.modal").css("display", "flex").hide().fadeIn(500);
        console.log($closeModal);
    });
});

// const $closeModal = $('.modal-close');
// console.log($closeModal);
//
// $closeModal.on('click',function(){
//     $("div.modal").css("display", "flex").hide().fadeIn(500);
// });