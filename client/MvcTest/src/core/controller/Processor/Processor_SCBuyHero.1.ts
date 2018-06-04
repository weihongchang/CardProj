/**
  * 服务器命令返回
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved. 
  */
module game {

    export class Processor_SCBuyHero extends puremvc.SimpleCommand implements puremvc.ICommand {
        public constructor() {
            super();
        }

        public static NAME: string = "Processor_SCBuyHero";

        /**
         * 注册消息
         */
        public register(): void {
            this.facade.registerCommand("Processor_SCBuyHero",Processor_SCBuyHero);
        }

        public executeData(data:egret.ByteArray): void {
           
            //创建user_login_class
            var user_login_class = Global.getMessage("SCBuyHero");
            
            //反序列化
            var new_user_login = user_login_class.decode(data.buffer);
            console.log("buyhero反序列化数据：",new_user_login);
            // console.log(game.DataManager.getInstance().player.name);
            if( new_user_login.status >= 0 )
            {   
                for (var i:number = 1; i < new_user_login.heroid.length; i++){
                    var hero = GlobalData.getHero( new_user_login.heroid[i] );
                    if(hero)
                    {
                        console.log("获得武将["+hero["heroname"]+"]");
                    }
                }
               
                game.AppFacade.getInstance().sendNotification(SceneNotify.OPEN_HOME);
                game.AppFacade.getInstance().sendNotification(MainNotify.OPEN_MAIN);
            }
            else
            {
                console.error("登陆失败！");
            }
        }
        public execute(notification: puremvc.INotification): void {
          
        }
    }
}
