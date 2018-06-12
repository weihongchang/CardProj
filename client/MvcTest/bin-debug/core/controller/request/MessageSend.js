/**
  * 请求用户信息例子
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved.
  */
var MessageSend;
(function (MessageSend) {
    function sendUserInfo(UserName, Pwd) {
        //创建user_login_class
        var user_login_class = Global.getMessage("CSLogin");
        //创建一条消息
        var user_login = new user_login_class({
            "email": UserName,
            "password": Pwd,
            "sessionKey": UserName,
            "serverid": 12
        });
        //序列化
        var bytes = user_login.toArrayBuffer();
        SocketManager.sendMessage(101, 1, bytes);
    }
    MessageSend.sendUserInfo = sendUserInfo;
    /**
     * 购买英雄
     */
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
    MessageSend.sendBuyHero = sendBuyHero;
    /**
     * 请求战斗
     */
    function sendFight(courseID) {
        //创建user_login_class
        var buyhero = Global.getMessage("CSFight");
        //创建一条消息
        var user_login = new buyhero({
            "courseID": courseID
        });
        //序列化
        var bytes = user_login.toArrayBuffer();
        SocketManager.sendMessage(115, 1, bytes);
    }
    MessageSend.sendFight = sendFight;
})(MessageSend || (MessageSend = {}));
//# sourceMappingURL=MessageSend.js.map