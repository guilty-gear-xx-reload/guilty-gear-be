window.addEventListener('load', (e) => {
    const socket = new SockJS('/ggxxreload/networks')
    const stompClient = Stomp.over(socket)
    stompClient.connect({}, (frame) => {

        stompClient.subscribe('/topic/errors', (greeting) => {
            console.warn('errors: ' + greeting.body)
        });

        stompClient.subscribe('/topic/pings', (greeting) => {
            console.log('greetings: ' + greeting.body)
        });

    });
})