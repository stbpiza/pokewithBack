<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>채팅 서비스</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


</head>
<body>
<input type="text" id="nickname" class="form-inline" placeholder="닉네임을 입력해주세봐" required autofocus>
<button class = "btn btn-primary" id = "name">확인</button>
<label for="roomId" class="label label-default">방 번호</label>
<label th:text="*{room.roomId}" id="roomId" class="form-inline"></label>
<br>
<label for="roomName" class="label label-default">방 이름</label>
<label th:text="*{room.name}" id="roomName" class="form-inline"></label>
<div id="divButtonCreateRoom" style="width:20%; height:100%; padding: 5px; float: left;">
						<button type="button" onclick="exitRoom()" class="btn btn-info" style="float:right;">나가기</button>
					</div>
<div id = "chatroom" style = "overflow:auto; width:400px;  height: 600px; border:1px solid; background-color : gray"></div>
<input type = "text" id = "message" style = "height : 30px; width : 340px" placeholder="내용을 입력하세요" autofocus>
<button class = "btn btn-primary" id = "send">전송</button>
<script>

</script>
</body>
<script th:inline = "javascript">
    var webSocket;
    var nickname;
    /* <![CDATA[*/
    var roomId = /*[[${room.roomId}]]*/
    /* ]]> */
    document.getElementById("name").addEventListener("click",function(){
        nickname = document.getElementById("nickname").value;
        document.getElementById("nickname").style.display = "none";
        document.getElementById("name").style.display = "none";
        connect();
    })
    document.getElementById("send").addEventListener("click",function(){
        send();
    })
    function connect(){
        webSocket = new WebSocket("ws://192.168.1.97:8080/chat");
        webSocket.onopen = onOpen;
        webSocket.onclose = onClose;
        webSocket.onmessage = onMessage;
    }
    function disconnect(){
        webSocket.send(JSON.stringify({chatRoomId : roomId,type:'LEAVE',writer:nickname}));
        webSocket.close();
    }
    function send(){
        msg = document.getElementById("message").value;
        webSocket.send(JSON.stringify({chatRoomId : roomId,type:'CHAT',writer:nickname,message : msg}));
        document.getElementById("message").value = "";
    }
    function onOpen(){
        webSocket.send(JSON.stringify({chatRoomId : roomId,type:'ENTER',writer:nickname}));
    }
    function onMessage(e){
        data = e.data;
        chatroom = document.getElementById("chatroom");
        chatroom.innerHTML = chatroom.innerHTML + "<br>" + data;
    }
    function onClose(){
        disconnect();
    }

</script>
</html>