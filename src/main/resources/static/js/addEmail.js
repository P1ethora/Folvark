function addEmail() {

    let response = fetch('/addEmail', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(document.getElementById("text-email").value)
    });
    document.getElementById('text-email').value = "";
}

// btn.onclick = function() {
//
//     // let response = fetch('/addEmail', {
//     //     method: 'POST',
//     //     headers: {
//     //         'Content-Type': 'application/json;charset=utf-8'
//     //     },
//     //     body: JSON.stringify(doc.getElementsByClassName('.txtb').value)
//     // });
//     doc.getElementsByClassName('.txtb').value = "";
// };