const countries = [
    {name: 'USA'},
    {name: 'India'},
    {name: 'Argentina'},
    {name: 'Armenia'},
    {name: 'Indonesia'},
    {name: 'Ingushet'},
    {name: 'Indus'},
    {name: 'Inei'}
];

function ser(element) {
    console.log("text");
    const input = element.value;
    document.getElementById('sugg').innerHTML = '';
    document.getElementById('field_sear').style.borderBottomLeftRadius = "0";
    document.getElementById('field_sear').style.borderTopLeftRadius = "0";
    const suggestions = countries.filter(function (country) {
        return country.name.toLowerCase().startsWith(input);
    });

    if (suggestions.length === 0) {
        const div = document.createElement('div');
        div.className = 'nothing-search';
        div.innerHTML = 'Не найдено';

        document.getElementById('sugg').appendChild(div);
    }

    suggestions.forEach(function (suggested) {
        const div = document.createElement('div');
        div.innerHTML = suggested.name;
        document.getElementById('sugg').appendChild(div);
    });
    if (input === '') {
        document.getElementById('field_sear').style.borderBottomLeftRadius = "30px";
        document.getElementById('field_sear').style.borderTopLeftRadius = "30px";
        document.getElementById('sugg').innerHTML = '';
    }
}
