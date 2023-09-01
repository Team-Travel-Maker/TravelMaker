
const $lists = $('.title-list-slick-track')

let option1 = {}
let option2 = {}
option1.container = $lists.eq(0);
option1.url = "/api/admins/store/main"
/** 반복 돌리고 싶은 틀을 전체를 div로 감싸준다 이유 html으로 가져오기 편함*/
option1.contentText= $(`
        <div>
            <div class="title-list-slick-slide">
                    <div class="card-container">
                        <a href="#">
                            <header 
                            data-role="img"
                            data-type="file"
                            data-key="storeFiles">
                            </header>
                            <div class="card-body-wrap">
                                <div class="card-body-flexbox">
                                    <div class="card-body-info">
                                        <p class="card-body-info-title" data-key="storeTitle"></p>
                                        <p class="card-body-info-category" data-key="storeType.name"></p>
                                    </div>
                                </div>
                                <button type="button" class="card-body-button">
                                    <span class="card-body-button-label">바로가기</span>
                                    <span class="card-body-button-interaction"></span>
                                </button>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            `);
option2.container =$lists.eq(1);
option2.url = "/api/admins/coupon/main"
option2.contentText= $(`
         <div>
            <div class="title-list-slick-slide">
                    <div class="card-container">
                        <a href="#">
                            <header data-role="img"
                                    data-type="file" 
                                    data-key="files">
                            </header>
                            <div class="card-body-wrap">
                                <div class="card-body-flexbox">
                                    <div class="card-body-info">
                                        <p class="card-body-info-title" data-key="giftCardTitle"></p>
                                        <p class="card-body-info-category" data-key="giftCardRegion"></p>
                                    </div>
                                </div>
                                <button type="button" class="card-body-button">
                                    <span class="card-body-button-label">바로가기</span>
                                    <span class="card-body-button-interaction"></span>
                                </button>
                            </div>
                        </a>
                    </div>
                </div>
           <div>   
            `);




mainService.getList(showList, option1);

mainService.getList(showList, option2);



