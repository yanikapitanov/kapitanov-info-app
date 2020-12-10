const getTransportDisruptions = () => {
    let location = document.getElementById("lines").value;
    getTransportFromApi(location)
}

const getTransportFromApi = (lines) => {
let url = "https://kapitanovslog.herokuapp.com/api/transport/interruptions";
if (lines) {
    url += "?transportLines=" + lines;
}
    fetch(url)
        .then(response => response.json())
        .then(data => parseToOutPut(data))
        .catch(error => console.log(error));
}

// const getTransportFromApi = (lines) => {
//     let url = "http://localhost:8111/api/transport/interruptions";
//     if (lines) {
//         url += "?transportLines=" + lines;
//     }
//     fetch(url)
//         .then(response => response.json())
//         .then(data => parseToOutPut(data))
//         .catch(error => console.log(error));
// }

const parseToOutPut = (data) => {
    let str = '';
    for (let res in data) {
        if (data[res].title != null) {
            str += `<h3> ${data[res].title}</h3><br>`
        }
        if (data[res].lines != null) {
            str += `<p> <b>Lines:</b> ${data[res].lines.map(l => l.line)} </p>`
        }
        if (data[res].duration.text != null) {
            str += `<p> <b>Duration:</b> ${data[res].duration.text}</p>`
        }
        if (data[res].text) {
            str += `<p> ${data[res].text}</p>`
        }
    }
    document.getElementById("weatherPlaceholder").innerHTML = str;
}