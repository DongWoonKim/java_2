$(document).ready(() => {
    checkToken();
    setupAjax();
    getUserInfo().then((userInfo) => {
        $('#welcome-message').text(userInfo.userName + '님 환영합니다!');
        $('#hiddenUserId').val(userInfo.userId);
        $('#hiddenUserName').val(userInfo.userName);
    }).catch((error) => {
        console.error('board list user info error : ', error)
    });
    getBoards();
});

let getBoards = () => {
    let currentPage = 1;
    const pageSize = 10; // 한 페이지에 보여줄 게시글의 수

    loadBoard(currentPage, pageSize);

    // 다음 페이지 버튼 클릭 이벤트
    $('#nextPage').on('click', () => {
        currentPage++;
        loadBoard(currentPage, pageSize);
    });
    // 이전 페이지 버튼 클릭 이벤트
    $('#prevPage').on('click', () => {
        if (currentPage > 1) {
            currentPage--;
            loadBoard(currentPage, pageSize);
        }
    });
}

let loadBoard = (page, size) => {
    $.ajax({
        type: 'GET',
        url: '/api/board',
        data: {
            page: page,
            size: size
        },
        success: (response) => {
            console.log('loadBoard : ', response);
            $('#boardContent').empty(); // 기존 게시글 내용 비우기
            if (response.articles.length <= 0) {
                // 게시글이 없는 경우 메시지 출력
                $('#boardContent').append(
                    `<tr>
                        <td colspan="4" style="text-align: center;">글이 존재하지 않습니다.</td>
                    </tr>`
                );
            } else {
                response.articles.forEach((article) => {
                    $('#boardContent').append(
                        `
                            <tr>
                                <td>${article.id}</td>
                                <td><a href="/detail?id=${article.id}">${article.title}</a></td>
                                <td>${article.userId}</td>
                                <td>${article.created}</td>
                            </tr>
                    `
                    );
                });
            }

            // 페이지 정보 업데이트
            $('#pageInfo').text(page);
            // 이전/다음 버튼 상태 설정
            $('#prevPage').prop('disabled', page === 1);
            $('#nextPage').prop('disabled', response.last);
        },
        error: (error) => {
            console.error('board list error :: ', error);
        }
    });
}

let logout = () => {
    $.ajax({
        type: 'POST',
        url: '/logout',
        success: () => {
            alert('로그아웃이 성공했습니다.');
            localStorage.removeItem('accessToken');
            window.location.href = '/member/login'
        },
        error: (error) => {
            console.log('오류발생 : ', error);
            alert('로그아웃 중 오류가 발생했습니다.');
        }
    });
}