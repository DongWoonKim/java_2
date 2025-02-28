$(document).ready(() => {

    $('#signup').click(() => {

        let userId = $('#user_id').val();
        let password = $('#password').val();
        let userName = $('#user_name').val();

        console.log('user id :: ', userId);
        console.log('user pw :: ', password);
        console.log('user name :: ', userName);

        let formData = {
            userId : userId,
            password : password,
            userName : userName
        }

        $.ajax({
            type : 'POST',
            url : '/join', // 서버의 엔드포인트 URL
            data : JSON.stringify(formData), // 데이터를 JSON 형식으로 변환
            contentType : 'application/json; charset=utf-8', // 전송 데이터의 타입
            dataType : 'json', // 서버에서 받을 데이터의 타입
            success: (response) => {
                alert('회원가입이 성공했습니다!\n로그인해주세요.');
            },
            error: (error) => {
                console.log('오류발생 : ', error);
                alert('회원가입 중 오류가 발생하였습니다.');
            }
        });

    });

});