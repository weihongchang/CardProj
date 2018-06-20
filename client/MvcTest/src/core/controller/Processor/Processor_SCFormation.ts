/**
  * 服务器命令返回
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved. 
  */
module game {

    export class Processor_SCFormation extends puremvc.SimpleCommand implements puremvc.ICommand {
        public constructor() {
            super();
        }

        public static NAME: string = "Processor_SCFormation";

        /**
         * 注册消息
         */
        public register(): void {
            this.facade.registerCommand("Processor_SCFormation",Processor_SCFormation);
        }

        public executeData(data:egret.ByteArray): void {
           
            var heroList = Global.getMessage("SCFormation");
            
            //反序列化
            DataManager.getInstance().formation = heroList.decode(data.buffer);
        }
        public execute(notification: puremvc.INotification): void {
          
        }
    }
}
