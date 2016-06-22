<%--
  Created by IntelliJ IDEA.
  User: xj
  Date: 2016/6/21
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<div>
    <input type="submit" value="Start" onclick="start()"/>
</div>
<div id="messages"></div>
<script type="text/javascript">
    var webSocket = new WebSocket('ws://localhost:8080/websocket');

    webSocket.onerror = function (event) {
        onError(event)
    };

    webSocket.onopen = function (event) {
        onOpen(event)
    };

    webSocket.onmessage = function (event) {
        onMessage(event)
    };

    function onMessage(event) {
        document.getElementById('messages').innerHTML
                += '<br />' + event.data;
    }

    function onOpen(event) {
        document.getElementById('messages').innerHTML
                = 'Connection established';
    }

    function onError(event) {
        alert(event.data);
        alert("error");
    }

    function start() {
        webSocket.send('hello');
        return false;
    }
</script>
</body>
</html>
