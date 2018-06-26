/**
  * 请求用户信息例子
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved. 
  */

module MessageSend {
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

    /**
     * 购买英雄
     */
    export function sendBuyHero(btype:number): void {
        //创建user_login_class
        var buyhero = Global.getMessage("CSBuyHero");
        
        //创建一条消息
        var user_login = new buyhero({
            "bType": btype
        });
        
        //序列化
        var bytes = user_login.toArrayBuffer();

        SocketManager.sendMessage(103,1,bytes)  
    }

    /**
     * 请求战斗
     */
    export function sendFight(courseID:number): void {
        //创建user_login_class
        var buyhero = Global.getMessage("CSFight");
        
        //创建一条消息
        var user_login = new buyhero({
            "courseID": courseID
        });
        
        //序列化
        var bytes = user_login.toArrayBuffer();

        SocketManager.sendMessage(115,1,bytes)  
    }
    
    /**
     * 保存阵型
     */
    export function sendSaveFormation(formation:number[]): void {
        //创建user_login_class
        var buyhero = Global.getMessage("CSFormation");
        
        //创建一条消息
        var user_login = new buyhero({
            "formationID": formation
        });
        
        //序列化
        var bytes = user_login.toArrayBuffer();

        SocketManager.sendMessage(113,1,bytes)  
    }
}
