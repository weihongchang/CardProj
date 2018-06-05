/**
  * 服务器命令返回
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved. 
  */
module game {

    export class Processor_SCItem extends puremvc.SimpleCommand implements puremvc.ICommand {
        public constructor() {
            super();
        }

        public static NAME: string = "Processor_SCItem";

        /**
         * 注册消息
         */
        public register(): void {
            this.facade.registerCommand("Processor_SCItem",Processor_SCItem);
        }

        public executeData(data:egret.ByteArray): void {
           
            var heroList = Global.getMessage("SCItemOne");
            
            //反序列化
            // DataManager.getInstance().heroList = heroList.decode(data.buffer);
            // console.log("buyhero反序列化数据：",DataManager.getInstance().heroList);
            
        }
        public execute(notification: puremvc.INotification): void {
          
        }
    }
}
