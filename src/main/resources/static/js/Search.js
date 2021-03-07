function ser(element) {

    const input = element.value;
    document.getElementById('subsugg').innerHTML = '';
    document.getElementById('field_sear').style.borderBottomLeftRadius = "0";
    document.getElementById('field_sear').style.borderBottomRightRadius = "0";

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
                const subsugg = document.getElementById('subsugg');
                subsugg.appendChild(div);
                subsugg.style.overflowY = 'hidden';
                subsugg.style.borderBottom = '0';
                document.getElementById('all-res-sear').style.height = '0';
                document.getElementById('sugg').style.borderBottom = '1px solid #d9d9d9';
            } else {
                res.forEach(function (suggested) {
                    let obj = JSON.parse(suggested);

                    const div = document.createElement('div');
                    const link = document.createElement('a');
                    const photo = document.createElement('div');
                    const name = document.createElement('div');
                    const price = document.createElement('div');

                    div.className = "element-search-quick";
                    photo.className = "img-el";
                    name.className = "name-el";
                    price.className = "price-el";

                    div.appendChild(link);
                    link.appendChild(photo);
                    link.appendChild(name);
                    link.appendChild(price);
                    link.href = '/product/' + obj.id;
                    name.innerHTML = obj.name;
                    photo.style.backgroundImage = 'url(' + obj.urlPicture + ')';
                    price.innerHTML = obj.price + ' руб';
                    document.getElementById('subsugg').appendChild(div);
                });
                const allRes = document.getElementById('all-res-sear');
                allRes.style.height = '80px';

                if (document.getElementById('link-search-el') === null) {
                    const allResLink = document.createElement('a');
                    allResLink.id = 'link-search-el';
                    allResLink.innerText = 'все результаты';
                    allResLink.href = '/search/' + input;
                    allRes.appendChild(allResLink);
                } else {
                    document.getElementById('link-search-el').href = '/search/' + input;
                }

                const subsugg = document.getElementById('subsugg');
                subsugg.style.borderBottom = '1px solid #d9d9d9';
                subsugg.style.overflowY = 'scroll';
                document.getElementById('sugg').style.borderBottom = '1px solid #d9d9d9';
            }
        });

    }

    if (input === '') {
        document.getElementById('field_sear').style.borderBottomLeftRadius = "10px";
        document.getElementById('field_sear').style.borderBottomRightRadius = "10px";
        const allRes = document.getElementById('all-res-sear');
        allRes.style.height = '0';
        if (document.getElementById('link-search-el') !== null) {
            document.getElementById('link-search-el').remove();
        }
        let mainDoc = document.getElementById('sugg');
        let subsugg = document.getElementById('subsugg');
        subsugg.style.borderBottom = '0';
        mainDoc.style.borderBottom = '0';
    }

}