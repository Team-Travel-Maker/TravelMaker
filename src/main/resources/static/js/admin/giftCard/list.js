const form = new FormData();

let text = "";
const $couponListContainer = $('.coupon-list');


let deleteIds = [];


let customService = (function () {

    function getList(callback, page) {
        $.ajax({
            url: `/api/admins/coupon?page=` + page,
            type: `get`,
            async: false,
            success: function (result) {
                console.log(result)
                if (callback) {
                    callback(result);
                }
            }
        })
    }

    function deleteGiftCard() {
        $.ajax({
            url: `/api/admins/coupon`,
            type: `delete`,
            async: false,
            enctype: "multipart/form-data", //form data 설정
            processData: false,
            contentType: false,
            data: form,
            success: function (response) {
                console.log(response);
            }

        })
    }

    return {getList: getList, deleteGiftCard: deleteGiftCard};

})()

customService.getList(showList)

function showList(result) {
    $couponListContainer.html('');
    $pagingWrap.html('');
    pagingText = "";
    text = "";

    result.content.forEach(coupon => {
        text += `
                    <tr>
                        <td class="checkbox-line">
                            <input
                                    type="checkbox"
                                    class="couponCheckbox"
                                    name="check"
                                    value="${coupon.id}"/>
                        </td>
                        <td>1</td>
                        <td>
                            <a href="/admins/coupon/detail/${coupon.id}" style="text-underline: black">${coupon.giftCardTitle}</a>
                        </td>
                        <td>${coupon.giftCardRegion}</td>
                        <td>${coupon.giftCardRegionDetail}</td>
                        <td>${coupon.createdDate}</td>
                        <td>${coupon.updatedDate}</td>
                        <td class="image-wrap">
                        `

            if(coupon.files.length !=0){
                coupon.files.forEach(result => {
                    let fileName = result.filePath + "/t_" + result.fileUuid + "_" + result.fileName;
                  text+=    `
                            <img src="/api/files/display?fileName=${fileName}" alt="" style="width: 150px; height: 150px">
                            `
                })
            }else{
                `<span>이미지 없음</span>`
            }
              text+=`  
                        </td>
                    </tr>
                    `
    });
    $couponListContainer.html(text);
    pagaing(result.pageable.pageSize, result.totalElements, result.pageable.pageNumber);

}


/*삭제버튼*/
$('.delete-button').on("click",async function () {
    form.delete("ids");
    deleteIds = [];
    $('.couponCheckbox').each((index,checkBox) => {
        if($(checkBox).is(":checked")){
            deleteIds.push($(checkBox).val());
        }
    })
    form.append("ids", new Blob([JSON.stringify(deleteIds)],{ type: "application/json" }))
    customService.deleteGiftCard();
    customService.getList(showList);
    showWarnModal("삭제되었습니다");
    $('#allSelect').prop("checked", false);
})
