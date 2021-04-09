$(function () {
    const el = document.getElementById('wrb');
    $(".add-comment").click(function () {
        el.style.display = 'block';
    });
});

function cancelReplyComment() {
    const el = document.getElementById('wrb1');
    el.remove();
}

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

        let topElementComment = document.createElement('div');
        let productComment = document.createElement('div');
        let imgComment = document.createElement('img');


        let commentBlock = document.createElement('div');
        let topComment = document.createElement('div');
        let commentName = document.createElement('div');
        let commentData = document.createElement('div');

        let commentText = document.createElement('div');

        let commentAnswer = document.createElement('div');
        let buttonReply = document.createElement('button');

        topElementComment.className = 'top_element_comment';
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

        topElementComment.appendChild(commentBlock);
        topElementComment.appendChild(imgComment);

        productComment.appendChild(topElementComment);

        $(".block_with_comments").prepend(productComment);
        document.getElementById('wrb').remove();

    });
});

function answerToComment(button) {

    let blockWithComments = document.getElementsByClassName('block_with_comments').item(0);
    let that = button.closest('.product-comment');
    let list = that.getElementsByClassName('reply_block').item(0);

    if (blockWithComments.getElementsByClassName('send-comment').item(0) != null) {
        blockWithComments.getElementsByClassName('send-comment').item(0).remove();
    }

    let sendComments = document.createElement('div');
    sendComments.className = 'send-comment';
    sendComments.id = 'wrb1';
    sendComments.setAttribute('parent', that.getAttribute('id'));

    let topSendComment = document.createElement('div');
    topSendComment.className = 'top-send-comment';

    let imgProfile = document.createElement('img');
    imgProfile.className = 'imgProfile';

    let textComment = document.createElement('input');
    textComment.className = 'text-comment';
    textComment.id = 'txt-comment-reply';

    let bottomSendComment = document.createElement('div');
    bottomSendComment.className = 'bottom-send-comment';

    let btnSendCancel = document.createElement('div');
    btnSendCancel.className = 'btn-send-cancel';
    btnSendCancel.innerHTML = 'Отмена';
    btnSendCancel.setAttribute('onclick', 'cancelReplyComment()');


    let btnSendComment = document.createElement('div');
    btnSendComment.className = 'btn-send-comment';
    btnSendComment.innerHTML = 'Оставить отзыв';
    btnSendComment.setAttribute('onclick', "sendReplyComment(this)");

    topSendComment.appendChild(imgProfile);
    topSendComment.appendChild(textComment);

    bottomSendComment.appendChild(btnSendCancel);
    bottomSendComment.appendChild(btnSendComment);

    sendComments.appendChild(topSendComment);
    sendComments.appendChild(bottomSendComment);

    that.insertBefore(sendComments, that.getElementsByClassName('open_reply').item(0));

    // let valueComment = commentNameUser.value;

    // let response = fetch(url + '/addComment', {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json;charset=utf-8'
    //     },
    //     body: JSON.stringify(document.getElementById("txt_com").value)
    // });

}


function sendReplyComment(button) {
    // let blockWithComments = document.getElementsByClassName('block_with_comments').item(0);
    let that = button.closest('.send-comment');
    let comment = that.closest('.product-comment');
    let url = document.location.href;

    // let response = fetch(url + '/addComment', {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json;charset=utf-8'
    //     },
    //     body: JSON.stringify(document.getElementById("txt-comment-reply").value)
    // });

    let productComment = document.createElement('div');
    let replyToCommentBlock = document.createElement('div');
    let imgComment = document.createElement('img');


    let commentBlock = document.createElement('div');
    let topComment = document.createElement('div');
    let commentName = document.createElement('div');
    let commentData = document.createElement('div');

    let commentText = document.createElement('div');

    let commentAnswer = document.createElement('div');
    let buttonReply = document.createElement('button');


    productComment.className = 'reply-to-comment';
    replyToCommentBlock.className = 'reply-to-comment-block';
    imgComment.className = 'reply-photo-user';
    commentBlock.className = 'reply-comment-block';
    topComment.className = 'reply-top-comment';
    commentName.className = 'reply-comment-name';
    commentData.className = 'reply-comment-data';
    commentText.className = 'reply-comment-text';
    commentAnswer.className = 'reply-comment-answer';
    buttonReply.className = 'btn-reply-comment';

    buttonReply.innerHTML = 'Ответить';
    buttonReply.setAttribute('onclick', "replyToReply(this)");
    // commentText.innerHTML = commentFromReply.getElementsByClassName("text-comment").item(0).value;
    commentText.innerHTML = document.getElementById('txt-comment-reply').value;

    topComment.appendChild(commentName);
    topComment.appendChild(commentData);

    commentBlock.appendChild(topComment);
    commentBlock.appendChild(commentText);

    commentAnswer.appendChild(buttonReply);

    commentBlock.appendChild(commentAnswer);

    replyToCommentBlock.appendChild(imgComment);
    replyToCommentBlock.appendChild(commentBlock);

    productComment.appendChild(replyToCommentBlock);

    that.remove();
    comment.appendChild(productComment);
}

function openListReply(button) {
    let that = button.closest('.product-comment');
    let list = that.getElementsByClassName('reply_block').item(0);

    button.setAttribute('onclick', 'closeListReply(this)');
    button.innerHTML = 'Скрыть';

    for (let el of that.children) {
        if (el.className === 'reply-to-comment') {
            list.appendChild(el);
        }
    }
    list.style.display = 'flex';

}

function closeListReply(button) {
    let that = button.closest('.product-comment');
    let list = that.getElementsByClassName('reply_block').item(0);
    list.style.display = 'none';

    button.setAttribute('onclick', 'openListReply(this)');
    button.innerHTML = 'Показать 9 ответов';

    for (let el of that.children) {
        if (el.className === 'reply-to-comment') {
            el.style.display = ''
        }
    }
}

function replyToReply(button) {
    let blockWithComments = document.getElementsByClassName('block_with_comments').item(0);
    if (blockWithComments.getElementsByClassName('send-comment').item(0) != null) {
        blockWithComments.getElementsByClassName('send-comment').item(0).remove();
    }
    let that = button.closest('.reply-to-comment');


    let sendComments = document.createElement('div');
    sendComments.className = 'send-comment';
    sendComments.id = 'wrb1';
    sendComments.setAttribute('parent', that.getAttribute('id'));

    let topSendComment = document.createElement('div');
    topSendComment.className = 'top-send-comment';

    let imgProfile = document.createElement('img');
    imgProfile.className = 'imgProfile';

    let textComment = document.createElement('input');
    textComment.className = 'text-comment';
    textComment.id = 'txt-comment-reply';

    let bottomSendComment = document.createElement('div');
    bottomSendComment.className = 'bottom-send-comment';

    let btnSendCancel = document.createElement('div');
    btnSendCancel.className = 'btn-send-cancel';
    btnSendCancel.innerHTML = 'Отмена';
    btnSendCancel.setAttribute('onclick', 'cancelReplyComment()');


    let btnSendComment = document.createElement('div');
    btnSendComment.className = 'btn-send-comment';
    btnSendComment.innerHTML = 'Оставить отзыв';
    // btnSendComment.setAttribute('onclick', "sendReplyComment(this)");


    topSendComment.appendChild(imgProfile);
    topSendComment.appendChild(textComment);

    bottomSendComment.appendChild(btnSendCancel);
    bottomSendComment.appendChild(btnSendComment);

    sendComments.appendChild(topSendComment);
    sendComments.appendChild(bottomSendComment);

    that.appendChild(sendComments);

}