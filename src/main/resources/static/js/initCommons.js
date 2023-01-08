//TODO nie wiem co z tym zrobić - skąd my mamy to brać? O_o otrząśnij sie
const serverDomain = 'http://26.39.40.108/ggxxreload'

const sendHttpRequest = (method, url, contentType, body) => {
    return fetch(url, {
            method: method,
            body: JSON.stringify(body),
            headers: {
                'Content-Type': contentType
            }
        }).then(response => {
            return response.text();
        })
        .catch(err => {
            console.log(err, err.data)
        })
}