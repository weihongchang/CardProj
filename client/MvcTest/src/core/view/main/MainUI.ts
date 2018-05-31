/**
 * 主界面
 */
module game {

    export class MainUI extends eui.Component {
        private isHide: Boolean = false;
        public constructor() {
            super();
            this.skinName = "resource/eui_skins/MainUISkin.exml";

            //画一个红色的正方形
            var square1: egret.Shape = new egret.Shape();
            square1.graphics.beginFill(0xff0000);
            square1.x = this.functionBar.x;
            square1.y = this.functionBar.y;
            square1.graphics.drawRect(0,0,this.functionBar.width,this.functionBar.height);
            square1.graphics.endFill();
            this.addChild(square1);
            
            // 画一个红色的正方形
            //var square2: egret.Shape = new egret.Shape();
            //square2.graphics.beginFill(0xff0000);
            //square2.x = this.activityBar.x;
            //square2.y = this.activityBar.y;
            //square2.graphics.drawRect(0,0,this.activityBar.width,this.activityBar.height);
            //square2.graphics.endFill();
            //this.addChild(square2);
            
            this.mainBtn.addEventListener(egret.TouchEvent.TOUCH_TAP,this.onMainBtnClick,this);
            this.functionBar.mask = square1;
            //this.activityBar.mask = square2;
            
            this.functionBar.roleBtn.addEventListener(egret.TouchEvent.TOUCH_TAP,this.onRoleBtnClick,this);
            this.functionBar.backpackBtn.addEventListener(egret.TouchEvent.TOUCH_TAP,this.onBackpackBtnClick,this);
            this.functionBar.qianghuaBtn.addEventListener(egret.TouchEvent.TOUCH_TAP,this.onQianghuaBtnClick,this);
            this.functionBar.zhaoXianBtn.addEventListener(egret.TouchEvent.TOUCH_TAP,this.onZhaoXianBtnClick,this);
            this.functionBar.chuangDangBtn.addEventListener(egret.TouchEvent.TOUCH_TAP,this.onChuangDangBtnClick,this);
            this.functionBar.shopBtn.addEventListener(egret.TouchEvent.TOUCH_TAP,this.onShopBtnClick,this);
            this.functionBar.mapBtn.addEventListener(egret.TouchEvent.TOUCH_TAP,this.onMapBtnClick,this);

            //this.btnAtk.addEventListener(egret.TouchEvent.TOUCH_TAP,this.onAct,this);

            this.roleInfo.imgHead.source="head_lusu_png";

            // DataManager.getInstance().InitTestData();
        }

        private onMainBtnClick(e: egret.TouchEvent) {
            if(this.isHide){
                this.isHide = !this.isHide;
                egret.Tween.get(this.functionBar).to({ x: 0 },300,egret.Ease.backOut);     
               // egret.Tween.get(this.activityBar).to({ y: 660 },300,egret.Ease.backOut);   
            }else{
                this.isHide = !this.isHide;
                egret.Tween.get(this.functionBar).to({ x: GameConfig.curWidth() },300,egret.Ease.backOut);
                //egret.Tween.get(this.activityBar).to({ y: GameConfig.curHeight() },300,egret.Ease.backOut);                       
            }
        }
        
        
        private onRoleBtnClick(e: egret.TouchEvent) {
            game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_ROLE);
        }
        
        private onBackpackBtnClick(e: egret.TouchEvent) {
            game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_BACKPACK);
        }
        
        private onQianghuaBtnClick(e: egret.TouchEvent) {
            game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_QIANGHUA);
        }
        
        private onZhaoXianBtnClick(e: egret.TouchEvent) {
            game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_ZHAOXIAN);
        }

        private onChuangDangBtnClick(e: egret.TouchEvent) {
            // game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_CHUANGDANG);
            game.AppFacade.getInstance().sendNotification(MainNotify.CLOSE_MAIN);
            game.AppFacade.getInstance().sendNotification(SceneNotify.CLOSE_HOME);
            game.AppFacade.getInstance().sendNotification(SceneNotify.OPEN_BATTLE);
        }
 
        private onShopBtnClick(e: egret.TouchEvent) {
            game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_SHOP);
        }
  
        private onMapBtnClick(e: egret.TouchEvent) {
            game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_MAP);
        }        

        private onAct(e:egret.TouchEvent){
            //EffectUtils.showTips("旋转特效",5);
            // game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_SHOP);
            game.AppFacade.getInstance().sendNotification(MainNotify.CLOSE_MAIN);
            game.AppFacade.getInstance().sendNotification(SceneNotify.CLOSE_HOME);
            game.AppFacade.getInstance().sendNotification(SceneNotify.OPEN_BATTLE);
            
        }

        //public activityBar: game.ActivityBar;
        public functionBar: game.FunctionBar;
        public roleInfo: game.RoleInfo;
        public mainBtn: eui.Button;
        //public btnAtk:eui.Button;

        public partAdded(partName: string,instance: any): void {
            super.partAdded(partName,instance);
        }
    }
}