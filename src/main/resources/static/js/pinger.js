window.addEventListener('load', (e) => {
    const socket = new SockJS('/ggxxreload/networks')
    const stompClient = Stomp.over(socket)
    stompClient.connect({}, (frame) => {
        stompClient.subscribe('/topic/pings', (playerPings) => {
            updatePing(JSON.parse(playerPings.body))
            console.log('message from server: ' + playerPings.body)
        });
    });
})

function updatePing(playerPings) {
    playerPings.forEach(player => {
        var xpath = "//td[@id='playerId'][contains(text(),\"" + player.playerId + "\")]/following-sibling::td[@id='ping']"
        var matchingElement = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
        console.log('xpath: ' + xpath)
        console.log('matchingElement: ' + matchingElement)
        matchingElement.textContent = player.ping
    });

}
