
const $lists = $('.title-list-slick-track')

let option = {}
option.container = $lists.eq(0);
option.url = "/api/admins/store"
option.contentText= $(`
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



mainService.getList(showList, option);

