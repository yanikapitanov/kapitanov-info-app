const getWeatherByLocation = () => {
    let location = document.getElementById("weatherInput").value;
    getWeatherFromApi(location)
}

const getWeatherFromApi = (location) => {
    fetch("http://localhost:8111/api/weather?location=" + location)
        .then(response => response.json())
        .then(data => parseToOutPut(data, location))
        .catch(error => console.log(error));
}

const parseToOutPut = (data, location) => {
    const weatherObject = data.main;
    const country = data.sys.country;
    let str = `<h2> ${location.charAt(0).toUpperCase() + location.slice(1)}, ${country} </h2></br>`;
    str += "<ul class='list-group'>";
    str += `<li class="list-group-item">Temp: ${weatherObject["temp"]} C</li>`
    str += `<li class="list-group-item">Feels like: ${weatherObject["feels_like"]} C</li>`
    str += "</ul>"
    document.getElementById("weatherPlaceholder").innerHTML = str;
}