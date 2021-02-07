function flyProduct(element) {
    const that = $(element).closest('.product-flex');
    const cart = $(".cart");
    var incr = document.querySelector('.counterPr');
    const w = that.width();
    that.clone().css({
        'width': w,
        'position': 'absolute',
        'z-index': '9999',

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
    incr.innerHTML = +incr.innerHTML + 1
}