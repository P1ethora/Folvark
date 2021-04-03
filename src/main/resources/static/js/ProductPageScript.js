$(function () {
    const el = document.getElementById('wrb');
    $(".add-comment").click(function () {
        el.style.display = 'block';
    });
});

$(function () {
    const el = document.getElementById('wrb');
    $(".btn-send-cancel").click(function () {
        el.style.display = 'none';
    });
});

$(function () {
    $(".btn-send-comment").click(function () {
        let url = document.location.href;

        let response = fetch(url + '/addComment', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(document.getElementById("txt_com").value)
        });

        let productComment = document.createElement('div');
        let imgComment = document.createElement('img');


        let commentBlock = document.createElement('div');
        let topComment = document.createElement('div');
        let commentName = document.createElement('div');
        let commentData = document.createElement('div');

        let commentText = document.createElement('div');

        let commentAnswer = document.createElement('div');
        let buttonReply = document.createElement('button');


        productComment.className = 'product-comment';
        imgComment.className = 'photo-user';
        commentBlock.className = 'comment-block';
        topComment.className = 'top-comment';
        commentName.className = 'comment-name';
        commentData.className = 'comment-data';
        commentText.className = 'comment-text';
        commentAnswer.className = 'comment-answer';
        buttonReply.className = 'reply-to-comment';

        buttonReply.innerHTML = 'Ответить';
        commentText.innerHTML = document.getElementById("txt_com").value;

        topComment.appendChild(commentName);
        topComment.appendChild(commentData);

        commentBlock.appendChild(topComment);
        commentBlock.appendChild(commentText);

        commentAnswer.appendChild(buttonReply);

        commentBlock.appendChild(commentAnswer);

        productComment.appendChild(imgComment);
        productComment.appendChild(commentBlock);

        // let blockWithComments = document.getElementsByClassName('block_with_comments').item(0);
        //
        // blockWithComments.appendChild(productComment);
        $(".block_with_comments").prepend(productComment);

    });
});

function answerToComment(button) {

    let blockWithComments = document.getElementsByClassName('block_with_comments').item(0);
    const that = $(button).closest('.product-comment');

    console.log(that);


    // let commentNameUser = that.getElementsByClassName('comment-name').item(0);

    let el = document.createElement('div');
    el.innerHTML = 'ТУТ';

    blockWithComments.insertBefore(el, that);

    // let valueComment = commentNameUser.value;

    // let response = fetch(url + '/addComment', {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json;charset=utf-8'
    //     },
    //     body: JSON.stringify(document.getElementById("txt_com").value)
    // });

}