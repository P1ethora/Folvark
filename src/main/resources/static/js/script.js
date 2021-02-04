function addToCart(element) {

    let response = fetch('/maps/addToCart', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(element.getAttribute('data-id'))
    });
    let result = response.json();
    alert(result.message);
}