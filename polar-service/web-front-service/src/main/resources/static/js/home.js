$(document).ready(() => {
    setupAjax();
    checkToken();
});

let getCatalog = () => {

}

let createCatalog = () => {
    let isbn = $('#isbn').val();
    let title = $('#title').val();
    let author = $('#author').val();
    let price = $('#price').val();

    let sendData = {
        isbn : isbn,
        title : title,
        author : author,
        price : price
    }

    $.ajax({
        method: 'POST',
        url: '/webs/api/catalog',
        data: JSON.stringify(sendData), // 데이터를 JSON 형식으로 변환
        contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
        dataType: 'json', // 서버에서 받을 데이터의 타입
        success: (response) => {
            console.log('response :: ', response)
        },
        error: (xhr) => {
            if (xhr.status === 419) {
                // Refresh Token을 통해 Access Token 재발급 요청
                handleTokenExpiration();
                alert('다시 한번 시도해주세요.');
            } else {
                // 다른 오류 처리
                console.error('요청 오류 발생:', xhr);
            }
            location.reload(true);
        }
    });
}