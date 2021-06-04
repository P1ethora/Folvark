function flyProduct(element) {
    const that = $(element).closest('.product-flex');
    const cart = $(".cart");
    // that.style.border = '1px solid #00a550';
    var incr = document.querySelector('.counterPr');
    const w = that.width();
    that.clone().css({
        'width': w,
        'position': 'absolute',
        'z-index': '999999',

        top: that.offset().top,
        left: that.offset().left
    }).appendTo("body")
        .animate({
            opacity: 0.05,
            left: cart.offset()['left'],
            top: cart.offset()['top'],
            width: 20
        }, 1000, function () {
            $(this).remove();
        });
    element.text = "Уже в корзине";
    let buttAddCart = element.closest('.add-to-cart');
    let prod = element.closest('.product-flex');
    buttAddCart.getElementsByTagName('a').item(0).removeAttribute('onclick');
    buttAddCart.className='add-to-cart-none'
    //prod.className = 'product-flex-added';
    prod.style.border = '1px solid #00a550';
    incr.innerHTML = +incr.innerHTML + 1
}