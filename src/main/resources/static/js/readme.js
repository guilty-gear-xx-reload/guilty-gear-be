const readme = document.getElementById('readme');
const getBtn = document.getElementById('get-btn');
var stringData;

const sendHttpRequest = (method, url, contentType, body) => {
    return fetch(url, {
        method: method,
        body: JSON.stringify(body),
        headers: { 'Content-Type': contentType }
    }).then(response => {
        return response.text();
    })
    .catch(err => {
        console.log(err, err.data)
    })

}

const getRawReadme = () => {
    sendHttpRequest('GET',
       'https://raw.githubusercontent.com/AlbertKozera/ggnlobby-java/main/README.md',
       'text/plain')
        .then(responseData => {
            stringData = responseData;
        })
}

const getParsedReadme = () => {
    sendHttpRequest('POST',
       'https://api.github.com/markdown',
       'application/json',
       {'mode': 'markdown', 'text': ''/*call getRawReadme() somehow there or just get README from local resources*/})
        .then(responseData => {
            document.getElementById('readme').innerHTML = responseData;
        })
}


document.addEventListener('DOMContentLoaded', getParsedReadme);