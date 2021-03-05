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
    const suggestions = countries.filter(function (country) {
        return country.name.toLowerCase().startsWith(input);
    });
    suggestions.forEach(function (suggested) {
        const div = document.createElement('div');
        div.innerHTML = suggested.name;
        document.getElementById('sugg').appendChild(div);
    });
    if (input === '') {
        document.getElementById('sugg').innerHTML = '';
    }
}
