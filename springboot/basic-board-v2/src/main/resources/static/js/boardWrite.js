
$(document).ready(() => {
    checkToken();
    setupAjax();
    getUserInfo().then((userInfo) => {
        $('#welcome-message').text(userInfo.userName + '님 환영합니다!');
    }).catch((error) => {
        console.error('Error get user info : ', error)
    });

    $('#submitBtn').on('click', (event) => {
        event.preventDefault();

        let formData = new FormData($('#writeForm')[0]);

        // for (let [key, value] of formData.entries()) {
        //     if (value instanceof File) {
        //         console.log('Key:', key);
        //         console.log('Name:', value.name);
        //         console.log('Size:', value.size);
        //         console.log('Type:', value.type);
        //     } else {
        //         console.log(key + ': ' + value);
        //     }
        // }


    });

});