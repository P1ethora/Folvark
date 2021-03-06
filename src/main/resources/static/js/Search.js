function ser(element) {

    const input = element.value;
    document.getElementById('sugg').innerHTML = '';
    document.getElementById('field_sear').style.borderBottomLeftRadius = "0";
    document.getElementById('field_sear').style.borderTopLeftRadius = "0";

    if (input !== '') {
        fetch('/search', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(input)
        }).then((result) => {
            return result.json();
        }).then((res) => {
            if (res.length === 0) {
                const div = document.createElement('div');
                div.className = 'nothing-search';
                div.innerHTML = 'Не найдено';
                document.getElementById('sugg').appendChild(div);
            } else {
                res.forEach(function (suggested) {
                    let obj = JSON.parse(suggested);
                    const div = document.createElement('div');
                    const photo = document.createElement('div');
                    const name = document.createElement('div');
                    const price = document.createElement('div');
                    div.className = "element-search-quick";
                    photo.className = "img-el";
                    name.className = "name-el";
                    price.className = "price-el";
                    div.appendChild(photo);
                    div.appendChild(name);
                    div.appendChild(price);
                    // div.innerHTML = obj.id;
                    name.innerHTML = obj.name;
                    photo.style.backgroundImage = 'url(\'' + obj.urlPicture + '\')';
                    price.innerHTML = obj.price;
                    document.getElementById('sugg').appendChild(div);
                });
            }
            if (input === '') {
                document.getElementById('field_sear').style.borderBottomLeftRadius = "30px";
                document.getElementById('field_sear').style.borderTopLeftRadius = "30px";
                document.getElementById('sugg').innerHTML = '';
            }
        });
    }
}