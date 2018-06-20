/**
  * 服务器命令返回
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved. 
  */
module game {

    export class Processor_SCFight extends puremvc.SimpleCommand implements puremvc.ICommand {
        public constructor() {
            super();
        }

        public static NAME: string = "Processor_SCFight";

        /**
         * 注册消息
         */
        public register(): void {
            this.facade.registerCommand("Processor_SCFight",Processor_SCFight);
        }

        public executeData(data:egret.ByteArray): void {
           
            //创建user_login_class
            var user_login_class = Global.getMessage("SCFight");
            
            
            DataManager.getInstance().battleLog = user_login_class.decode(data.buffer);
            console.log("buyhero反序列化数据：",DataManager.getInstance().battleLog );
            game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_Battle);

        }
        public execute(notification: puremvc.INotification): void {
          
        }
    }
}
