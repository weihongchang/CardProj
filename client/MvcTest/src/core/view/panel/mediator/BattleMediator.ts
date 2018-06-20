/**
  * 背包面板
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved. 
  */
module game {

    export class BattleMediator extends BaseMediator {
        public static NAME: string = "BattleMediator";

        public constructor(viewComponent: any = null) {
            super(BattleMediator.NAME,viewComponent);
        }

        public listNotificationInterests(): Array<any> {
            return [
                PanelNotify.OPEN_Battle,
                PanelNotify.CLOSE_Battle
            ];
        }
        private battlePanel: BattlePanel = new BattlePanel();
        public handleNotification(notification: puremvc.INotification): void {
            var data: any = notification.getBody();
            switch(notification.getName()) {
                case PanelNotify.OPEN_Battle: {
                    //显示角色面板
                    this.showUI(this.battlePanel,false,0,0,5);
                    break;
                }
                case PanelNotify.CLOSE_Battle: {
                    this.battlePanel.RemoveAllChildren();
                    this.closePanel(1);
                    break;
                }
            }
        }       

        /**
         * 初始化面板ui
         */
        public initUI(): void {
            // this.battlePanel.closeBtn.addEventListener(egret.TouchEvent.TOUCH_TAP,this.closeButtonClick,this);

        }	


        /**
         * 初始化面板数据
         */
        public initData(): void {

            this.battlePanel.initData();

            egret.setTimeout(function () {
                EffectUtils.showTips("3",4,50,false,GameConfig.curWidth()/2,GameConfig.curHeight()/2);
            },this,1000);   

            egret.setTimeout(function () {
                EffectUtils.showTips("2",4,50,false,GameConfig.curWidth()/2,GameConfig.curHeight()/2);
            },this,2000);   

            egret.setTimeout(function () {
                EffectUtils.showTips("1",4,50,false,GameConfig.curWidth()/2,GameConfig.curHeight()/2);
            },this,3000);   

            egret.setTimeout(function () {
                this.battlePanel.PlayBattle();
            },this,4000);     
            
        }

        private readExcelButtonClick(event: egret.TouchEvent): void {

        }
        private closeButtonClick(event: egret.TouchEvent): void {
            
            this.closePanel(1);
        }
    }
}