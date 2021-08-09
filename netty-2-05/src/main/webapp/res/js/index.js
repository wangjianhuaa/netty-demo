var socket;
$(function () {

    if(!window.WebSocket){
        window.WebSocket = window.MozWebSocket;
    }

    if(!window.WebSocket){
        alert("您的浏览器不支持WebSocket协议!推荐使用谷歌浏览器 \r\n 谷歌浏览器下载地址:https://www.google.cn/chrome/");
        return;
    }

    socket = new WebSocket("ws://localhost:7397/websocket");

    socket.onmessage = function (event) {
        var msg = JSON.parse(event.data);

        // 链接消息;1.自发消息 2群发消息
        if(1 == msg.type){
            jQuery.data(document.body,'channelId',msg.channelId);
            return;
        }
        if(2 === msg.type){
            var channelId = msg.channelId;
            //自己
            if(channelId == jQuery.data(document.body,'channelId')){
                var module = $(".msgBlockOwnerClone").clone();
                module.removeClass("msgBlockOwnerClone").addClass("msgBlockOwner").css({display:"block"});
                module.find(".headPoint").attr("src","res/img/"+msg.userHeadImg);
                module.find(".msgBlock_msgInfo .msgPoint").text(msg.msgInfo);

                $("#msgPoint").before(module);

                util.divScroll();
            }
            //好友
            else{
                var module = $(".msgBlockOwnerClone").clone();
                module.removeClass("msgBlockOwnerClone").addClass("msgBlockOwner").css({display:"block"});
                module.find(".headPoint").attr("src","res/img/"+msg.userHeadImg);
                module.find(".msgBlock_channelId").text("ID："+msg.channelId);
                module.find(".msgBlock_msgInfo .msgPoint").text(msg.msgInfo);
                $("#msgPoint").before(module);

                util.divScroll();
            }
        }
    };

    socket.onopen = function (event) {
        console.info("打开webSocket服务正常，浏览器支持webSocket！")
        var clientMsgProtocol = {};
        clientMsgProtocol.type = 1;
        clientMsgProtocol.msgInfo = '个人信息';
        socket.send(JSON.stringify(clientMsgProtocol));
    };

    socket.onclose = function (event) {
        console.info("webScoket关闭");
    };

    document.onkeydown = function (e) {
        if(13==e.keyCode && e.ctrlKey){
            util.send();
        }
    }
}
);

util ={
    send: function () {
        if(!window.WebSocket){
            return;
        }
        if(socket.readyState==WebSocket.OPEN){
            var clientMsgProtocol = {};
            clientMsgProtocol.type = 2;
            clientMsgProtocol.msgInfo =$("#sendBox").val();
            socket.send(JSON.stringify(clientMsgProtocol));
            $("#sendBox").val("");
        }
        else {
            alert("WebSocket没有建立成功！")
        }
    },
    divScroll: function () {
        var div =document.getElementById('show');
        div.scrollTop =div.scrollHeight
    }
};