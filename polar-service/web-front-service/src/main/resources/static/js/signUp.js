$(document).ready(() => {

    $('#signup').click((event) => {
        // 폼의 기본 제출 동작을 막음.
        event.preventDefault();

        let userId = $('#user_id').val();
        let password = $('#password').val();
        let userName = $('#user_name').val();
        let role = $('#role').val();

        let formData = {
            userId : userId,
            password : password,
            userName : userName,
            role : role
        }
        
        console.log('formData :: ', formData);
        $.ajax({
            type: 'POST',
            url: '/join',
            data: JSON.stringify(formData), // 데이터를 JSON 형식으로 변환
            contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
            dataType: 'json', // 서버에서 받을 데이터의 타입
            success: (response) => {
                console.log('response : ', response)
                alert('회원가입이 성공했습니다.\n로그인해주세요.');
                if (response.success) {
                    window.location.href = response.url;
                }
            },
            error: (error) => {
                console.log('오류발생 : ', error);
                alert('회원가입 중 오류가 발생했습니다.');
            }
        });

    });

});