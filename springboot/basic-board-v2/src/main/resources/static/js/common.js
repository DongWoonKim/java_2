let handleTokenExpiration = () => {
    $.ajax({
        type: 'POST',
        url: '/refresh-token',
        contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
        dataType: 'json', // 서버에서 받을 데이터의 타입
        xhrFields: {
            withCredentials: true // 쿠키를 포함해서 요청을 보냄
        },
        success: (response) => {
            console.log(response);
            localStorage.setItem('accessToken', response.token);
        },
        error: () => {
            alert('로그인이 필요합니다. 다시 로그인해주세요.')
            window.location.href = '/member/login'
        }
    });
}

let checkToken = () => {
    let token = localStorage.getItem('accessToken');
    if (token == null || token.trim() === '') {
        window.location.href = '/member/login';
    }
}

let setupAjax = () => {
    // 모든 Ajax 요청에 JWT Access Token을 포함.
    $.ajaxSetup({
        beforeSend: (xhr) => {
            let token = localStorage.getItem('accessToken');
            token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTc0MTU2NzkxNSwiZXhwIjoxNzQxNTY3OTE2LCJzdWIiOiJ0ZXN0IiwiaWQiOjAsInJvbGUiOiJST0xFX1VTRVIiLCJ1c2VyTmFtZSI6InRlc3QifQ.Ix63HA1Tgs11Bh2bs1NfxmaXEvJutm1NBtdWYayU3YXkKe-702jtAePrzadJuKGt3XLooBsQJJqaHyMdDmMlaQ';
            if (token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + token)
            }
        }
    })
}

let getUserInfo = () => {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: 'GET',
            url: '/user/info',
            success: (response) => {
                resolve(response);
            },
            error: (xhr) => {
                console.log('xhr :: ', xhr)
                if (xhr.status === 401) {
                    handleTokenExpiration();
                } else {
                    reject(xhr);
                }
            }
        })
    });
}