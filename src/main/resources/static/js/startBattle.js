
function onClickStartBattle() {
    var gameLocationPath = document.getElementById('gameLocationPath').value;
    sendHttpRequest('POST',
            serverDomain + '/battles/start',
            'application/json',
            {'gameLocationPath': gameLocationPath})
            .then(responseData => {
                console.log('Process ggxx.exe started!');
            })
}