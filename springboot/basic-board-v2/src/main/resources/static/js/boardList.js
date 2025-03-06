$(document).ready(() => {
    checkToken();
});

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