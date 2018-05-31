/**
  * 请求用户信息例子
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved. 
  */

module UserInfoRequest {
    export function sendUserInfo(UserName:string,Pwd:string): void {
        //创建user_login_class
        var user_login_class = Global.getMessage("CSLogin");
        
        //创建一条消息
        var user_login = new user_login_class({
            "email": UserName,
            "password": Pwd,
            "sessionKey":UserName,
            "serverid":12
        });
        
        //序列化
        var bytes = user_login.toArrayBuffer();

        SocketManager.sendMessage(101,1,bytes)  
    }
}
