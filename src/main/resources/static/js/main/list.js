
const $lists = $('.title-list-slick-track')

let option1 = {}
let option2 = {}
option1.container = $lists.eq(0);
option1.url = "/api/admins/store/main"
option1.contentText= $(`
            <div class="title-list-slick-slide">
                    <div class="card-container">
                        <a href="#">
                            <header role="img" data-name="storeFiles" style="background-image: url('');"></header>
                            <div class="card-body-wrap">
                                <div class="card-body-flexbox">
                                    <div class="card-body-info">
                                        <p class="card-body-info-title" data-class="storeTitle"></p>
                                        <p class="card-body-info-category" data-class="storeType.name"></p>
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
            `);
option2.container =$lists.eq(1);
option2.url = "/api/admins/coupon/main"
/*option2.contentText= $(`
            <div class="title-list-slick-slide">
                    <div class="card-container">
                        <a href="#">
                            <header role="img" data-name="files" style="background-image: url('');"></header>
                            <div class="card-body-wrap">
                                <div class="card-body-flexbox">
                                    <div class="card-body-info">
                                        <p class="card-body-info-title" data-class="giftCardTitle"></p>
                                        <p class="card-body-info-category" data-class="giftCardRegion"></p>
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
            `);*/

option2.contentText= $(`
            <div class="title-list-slick-slide">
                    <div class="card-container">
                        <a href="#">
                            <header data-role="img" data-type="file" data-key="files" style="background-image: url('');"></header>
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
            `);




/*mainService.getList(showList, option1);*/

mainService.getList(showList, option2);



