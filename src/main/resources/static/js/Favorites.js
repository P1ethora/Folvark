function addFavorite(button) {

    let that = button.closest('.product-flex');

    let response = fetch('/addFavorite', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(that.getAttribute("id"))
    });

    let color = that.style.borderColor;

    if(color !== "rgb(0, 165, 80)") {
        that.style.border = "1px solid #890000"
    }

    that.getElementsByClassName("heard-void").item(0).style.display="none";
    that.getElementsByClassName("heard-full").item(0).style.display="flex";

}

function deleteFavorite(button) {

    let that = button.closest('.product-flex');

    let response = fetch('/deleteFavorite', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(that.getAttribute("id"))
    });
    let color = that.style.borderColor;

    if(color !== "rgb(0, 165, 80)") {
        that.style.border = "1px solid #bbbbbb"
    }

    that.getElementsByClassName("heard-full").item(0).style.display="none";
    that.getElementsByClassName("heard-void").item(0).style.display="flex";
}
