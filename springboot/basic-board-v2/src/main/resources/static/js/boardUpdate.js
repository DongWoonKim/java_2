let selectedFile = null;

$(document).ready(() => {
    checkToken();
    setupAjax();
    getUserInfo().then((userInfo) => {
        console.log('user info :: ', userInfo)
        $('#hiddenUserName').val(userInfo.userName);
        $('#hiddenUserId').val(userInfo.userId);
        $('#userId').val(userInfo.userId);
    }).catch((error) => {
        console.error('Error get user info : ', error)
    });
    loadBoardDetail();

    $('#hiddenFileFlag').val(false);
    $('#file').on('change', (event) => {
        selectedFile = event.target.files[0]; // 선택된 파일을 변수에 저장
        $('#hiddenFileFlag').val(true);
        updateFileList();
    });

    $('#submitBtn').on('click', (event) => {
        event.preventDefault();

        let formData = new FormData($('#writeForm')[0]);

        $.ajax({
            type: 'PUT',
            url: '/api/board',
            data: formData,
            contentType: false,
            processData: false,
            success: () => {
                alert('게시글이 성공적으로 수정되었습니다!');
                window.location.href = '/'
            },
            error: (error) => {
                console.log('오류발생 : ', error);
                alert('게시글 수정 중 오류가 발생했습니다.');
            }
        });

    });

});

// 파일 목록 업데이트 함수 (파일 하나만)
let updateFileList = () => {
    $('#fileList').empty(); // 기존 목록 비우기

    if (selectedFile) {
        $('#fileList').append(`
                    <li>
                        ${selectedFile.name} <button type="button" class="remove-btn">X</button>
                    </li>
                `);

        // X 버튼 클릭 시 파일 제거
        $('.remove-btn').on('click', function() {
            selectedFile = null; // 선택된 파일 제거
            $('#file').val(''); // 파일 input 초기화
            updateFileList(); // 파일 목록 갱신
        });
    }
}

let loadBoardDetail = () => {
    let hId = $('#hiddenId').val();

    $.ajax({
        type: 'GET',
        url: '/api/board/' + hId,
        success: (response) => {
            console.log('loadBoard update : ', response);
            $('#title').val(response.title);
            $('#content').val(response.content);
            $('#userId').val(response.userId);

            if (response.filePath && response.filePath.length > 0) {
                let filePath = response.filePath;
                $('#hiddenFilePath').val(filePath)
                let fileName = filePath.substring(filePath.lastIndexOf('\\') + 1); // 파일명 추출
                let fileElement = `
                    <li>
                        ${fileName} <button type="button" class="remove-btn">X</button>
                    </li>`;
                $('#fileList').append(fileElement);

                // X 버튼 클릭 시 파일 제거
                $('.remove-btn').on('click', function() {
                    selectedFile = null; // 선택된 파일 제거
                    $('#file').val(''); // 파일 input 초기화
                    updateFileList(); // 파일 목록 갱신
                });
            } else {
                $('#fileList').append('<li>첨부된 파일이 없습니다.</li>')
            }

        },
        error: (error) => {
            console.error('board list error :: ', error);
        }
    });
}