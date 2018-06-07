/**
 * 网络公共类
 * by dily
 * (c) copyright 2014 - 2035
 * All Rights Reserved.
 * 存放网络公共方法
 * 注意：是同步请求，不是异步
 */
var SocketManager;
(function (SocketManager) {
    var sock = new egret.WebSocket();
    SocketManager.connState = false;
    //连接服务器
    function connectServer(host, port) {
        if (host === void 0) { host = ""; }
        if (port === void 0) { port = 80; }
        Global.showWaritPanel();
        this.sock = new egret.WebSocket();
        this.sock.type = "webSocketTypeBinary";
        this.sock.addEventListener(egret.ProgressEvent.SOCKET_DATA, this.onReceiveMessage, this);
        this.sock.addEventListener(egret.Event.CONNECT, this.onSocketOpen, this);
        this.sock.addEventListener(egret.Event.CLOSE, this.SocketClose, this);
        this.sock.addEventListener(egret.IOErrorEvent.IO_ERROR, this.SocketError, this);
        this.sock.connect(host, port);
    }
    SocketManager.connectServer = connectServer;
    //连接成功返回
    function onSocketOpen() {
        this.connState = true;
        Global.hideWaritPanel();
        game.AppFacade.getInstance().sendNotification(SysNotify.CONNECT_SERVER_SUCCESS);
    }
    SocketManager.onSocketOpen = onSocketOpen;
    //连接异常
    function SocketError(e) {
        this.connState = false;
        Global.hideWaritPanel();
        EffectUtils.showTipsMid("网络连接异常", true);
        // game.AppFacade.getInstance().sendNotification(SysNotify.CONNECT_SERVER_SUCCESS);
    }
    SocketManager.SocketError = SocketError;
    //连接关闭
    function SocketClose() {
        this.connState = false;
        Global.hideWaritPanel();
        // game.AppFacade.getInstance().sendNotification(SysNotify.CONNECT_SERVER_SUCCESS);
    }
    SocketManager.SocketClose = SocketClose;
    //消息返回  
    function onReceiveMessage() {
        Global.hideWaritPanel();
        var _arr = new egret.ByteArray();
        this.sock.readBytes(_arr);
        var msg_length = _arr.readShort();
        var mainId = _arr.readShort();
        //var subId = _arr.readShort();
        var subId = 1;
        var cmdDataBA = new egret.ByteArray();
        _arr.readBytes(cmdDataBA);
        game.MessageController.getInstance().fireMessage(mainId, cmdDataBA);
    }
    SocketManager.onReceiveMessage = onReceiveMessage;
    //向服务端发送消息
    function sendMessage(mainId, subId, msg) {
        Global.showWaritPanel();
        var bodyMsg = new egret.ByteArray();
        bodyMsg.writeShort(mainId);
        //bodyMsg.writeShort(subId);
        bodyMsg.writeBytes(new egret.ByteArray(msg));
        var sendMsg = new egret.ByteArray();
        sendMsg.writeInt(bodyMsg.length + 4);
        sendMsg.writeBytes(new egret.ByteArray(bodyMsg.bytes));
        // console.log("反序列化数据："+sendMsg.length);  
        this.sock.writeBytes(sendMsg);
        this.sock.flush();
    }
    SocketManager.sendMessage = sendMessage;
    function sendBuyHero(btype) {
        //创建user_login_class
        var buyhero = Global.getMessage("CSBuyHero");
        //创建一条消息
        var user_login = new buyhero({
            "bType": btype
        });
        //序列化
        var bytes = user_login.toArrayBuffer();
        SocketManager.sendMessage(103, 1, bytes);
    }
    SocketManager.sendBuyHero = sendBuyHero;
})(SocketManager || (SocketManager = {}));
//# sourceMappingURL=SocketManager.js.map