const getWeatherByLocation = () => {
    let location = document.getElementById("weatherInput").value;
    getWeatherFromApi(location)
}

// const getWeatherFromApi = (location) => {
//     if(location == null) {
//         document.getElementById("weatherPlaceholder").innerHTML = `Location cannot be empty`;
//     }
//     fetch("https://kapitanovslog.herokuapp.com/api/weather/get?location=" + location)
//         .then(response => response.json())
//         .then(data => parseToOutPut(data, location))
//         .catch(error => console.log(error));
// }

const getWeatherFromApi = (location) => {
    if (location === "" || location == null) {
        document.getElementById("weatherPlaceholder").innerHTML =
            `<div class="alert alert-warning alert-dismissible">
                Location cannot be empty
            </div>`;
    } else {
        fetch("http://localhost:8111/api/weather/get?location=" + location)
            .then(response => response.json())
            .then(data => parseToOutPut(data, location))
            .catch(error => console.log(error));
    }
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