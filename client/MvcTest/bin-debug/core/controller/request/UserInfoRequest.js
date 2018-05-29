/**
  * 请求用户信息例子
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved.
  */
var UserInfoRequest;
(function (UserInfoRequest) {
    function sendUserInfo(userId, userName) {
        //创建user_login_class
        var user_login_class = Global.getMessage("CSLogin");
        //创建一条消息
        var user_login = new user_login_class({
            "email": userId + "",
            "password": userName,
            "sessionKey": userName,
            "serverid": 12
        });
        //序列化
        var bytes = user_login.toArrayBuffer();
        SocketManager.sendMessage(101, 1, bytes);
    }
    UserInfoRequest.sendUserInfo = sendUserInfo;
})(UserInfoRequest || (UserInfoRequest = {}));
//# sourceMappingURL=UserInfoRequest.js.map