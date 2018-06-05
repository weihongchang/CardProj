/**
  * 服务器命令返回
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved. 
  */
module game {

    export class Processor_SCItemList extends puremvc.SimpleCommand implements puremvc.ICommand {
        public constructor() {
            super();
        }

        public static NAME: string = "Processor_SCItemList";

        /**
         * 注册消息
         */
        public register(): void {
            this.facade.registerCommand("Processor_SCItemList",Processor_SCItemList);
        }

        public executeData(data:egret.ByteArray): void {
           
            var itemList = Global.getMessage("SCItemList");
            
            //反序列化
            DataManager.getInstance().itemList = itemList.decode(data.buffer);
            console.log("Itemlist 数据：",DataManager.getInstance().itemList);
            
        }
        public execute(notification: puremvc.INotification): void {
          
        }
    }
}
