/**
  * 服务器命令返回
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved. 
  */
module game {

    export class Processor_SCHeroList extends puremvc.SimpleCommand implements puremvc.ICommand {
        public constructor() {
            super();
        }

        public static NAME: string = "Processor_SCHeroList";

        /**
         * 注册消息
         */
        public register(): void {
            this.facade.registerCommand("Processor_SCHeroList",Processor_SCHeroList);
        }

        public executeData(data:egret.ByteArray): void {
           
            var heroList = Global.getMessage("SCHeroList");
            
            //反序列化
            DataManager.getInstance().heroList = heroList.decode(data.buffer);
            console.log("buyhero反序列化数据：",DataManager.getInstance().heroList);
            
        }
        public execute(notification: puremvc.INotification): void {
          
        }
    }
}
