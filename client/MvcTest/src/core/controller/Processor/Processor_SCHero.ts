/**
  * 服务器命令返回
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved. 
  */
module game {

    export class Processor_SCHero extends puremvc.SimpleCommand implements puremvc.ICommand {
        public constructor() {
            super();
        }

        public static NAME: string = "Processor_SCHero";

        /**
         * 注册消息
         */
        public register(): void {
            this.facade.registerCommand("Processor_SCHero",Processor_SCHero);
        }

        public executeData(data:egret.ByteArray): void {
           
            var heroList = Global.getMessage("SCHeroOne");
            
            //反序列化
            DataManager.getInstance().heroList = heroList.decode(data.buffer);
            // console.log("buyhero反序列化数据：",DataManager.getInstance().heroList);
            
        }
        public execute(notification: puremvc.INotification): void {
          
        }
    }
}
