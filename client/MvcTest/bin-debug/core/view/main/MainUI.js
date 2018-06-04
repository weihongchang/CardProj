var __reflect = (this && this.__reflect) || function (p, c, t) {
    p.__class__ = c, t ? t.push(c) : t = [c], p.__types__ = p.__types__ ? t.concat(p.__types__) : t;
};
var __extends = this && this.__extends || function __extends(t, e) { 
 function r() { 
 this.constructor = t;
}
for (var i in e) e.hasOwnProperty(i) && (t[i] = e[i]);
r.prototype = e.prototype, t.prototype = new r();
};
/**
 * 主界面
 */
var game;
(function (game) {
    var MainUI = (function (_super) {
        __extends(MainUI, _super);
        function MainUI() {
            var _this = _super.call(this) || this;
            _this.isHide = false;
            _this.skinName = "resource/eui_skins/MainUISkin.exml";
            //画一个红色的正方形
            var square1 = new egret.Shape();
            square1.graphics.beginFill(0xff0000);
            square1.x = _this.functionBar.x;
            square1.y = _this.functionBar.y;
            square1.graphics.drawRect(0, 0, _this.functionBar.width, _this.functionBar.height);
            square1.graphics.endFill();
            _this.addChild(square1);
            // 画一个红色的正方形
            //var square2: egret.Shape = new egret.Shape();
            //square2.graphics.beginFill(0xff0000);
            //square2.x = this.activityBar.x;
            //square2.y = this.activityBar.y;
            //square2.graphics.drawRect(0,0,this.activityBar.width,this.activityBar.height);
            //square2.graphics.endFill();
            //this.addChild(square2);
            _this.mainBtn.addEventListener(egret.TouchEvent.TOUCH_TAP, _this.onMainBtnClick, _this);
            _this.functionBar.mask = square1;
            //this.activityBar.mask = square2;
            _this.functionBar.roleBtn.addEventListener(egret.TouchEvent.TOUCH_TAP, _this.onRoleBtnClick, _this);
            _this.functionBar.backpackBtn.addEventListener(egret.TouchEvent.TOUCH_TAP, _this.onBackpackBtnClick, _this);
            _this.functionBar.qianghuaBtn.addEventListener(egret.TouchEvent.TOUCH_TAP, _this.onQianghuaBtnClick, _this);
            _this.functionBar.zhaoXianBtn.addEventListener(egret.TouchEvent.TOUCH_TAP, _this.onZhaoXianBtnClick, _this);
            _this.functionBar.chuangDangBtn.addEventListener(egret.TouchEvent.TOUCH_TAP, _this.onChuangDangBtnClick, _this);
            _this.functionBar.shopBtn.addEventListener(egret.TouchEvent.TOUCH_TAP, _this.onShopBtnClick, _this);
            _this.functionBar.mapBtn.addEventListener(egret.TouchEvent.TOUCH_TAP, _this.onMapBtnClick, _this);
            //this.btnAtk.addEventListener(egret.TouchEvent.TOUCH_TAP,this.onAct,this);
            // console.log(GlobalData.HeroTemplate[0].heroid);
            _this.roleInfo.imgHead.source = "head_lusu_png";
            return _this;
            // DataManager.getInstance().InitTestData();
        }
        MainUI.prototype.onMainBtnClick = function (e) {
            if (this.isHide) {
                this.isHide = !this.isHide;
                egret.Tween.get(this.functionBar).to({ x: 0 }, 300, egret.Ease.backOut);
                // egret.Tween.get(this.activityBar).to({ y: 660 },300,egret.Ease.backOut);   
            }
            else {
                this.isHide = !this.isHide;
                egret.Tween.get(this.functionBar).to({ x: GameConfig.curWidth() }, 300, egret.Ease.backOut);
                //egret.Tween.get(this.activityBar).to({ y: GameConfig.curHeight() },300,egret.Ease.backOut);                       
            }
        };
        MainUI.prototype.onRoleBtnClick = function (e) {
            game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_ROLE);
        };
        MainUI.prototype.onBackpackBtnClick = function (e) {
            game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_BACKPACK);
        };
        MainUI.prototype.onQianghuaBtnClick = function (e) {
            game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_QIANGHUA);
        };
        MainUI.prototype.onZhaoXianBtnClick = function (e) {
            game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_ZHAOXIAN);
        };
        MainUI.prototype.onChuangDangBtnClick = function (e) {
            // game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_CHUANGDANG);
            game.AppFacade.getInstance().sendNotification(MainNotify.CLOSE_MAIN);
            game.AppFacade.getInstance().sendNotification(SceneNotify.CLOSE_HOME);
            game.AppFacade.getInstance().sendNotification(SceneNotify.OPEN_BATTLE);
        };
        MainUI.prototype.onShopBtnClick = function (e) {
            game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_SHOP);
        };
        MainUI.prototype.onMapBtnClick = function (e) {
            game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_MAP);
        };
        MainUI.prototype.onAct = function (e) {
            //EffectUtils.showTips("旋转特效",5);
            // game.AppFacade.getInstance().sendNotification(PanelNotify.OPEN_SHOP);
            game.AppFacade.getInstance().sendNotification(MainNotify.CLOSE_MAIN);
            game.AppFacade.getInstance().sendNotification(SceneNotify.CLOSE_HOME);
            game.AppFacade.getInstance().sendNotification(SceneNotify.OPEN_BATTLE);
        };
        //public btnAtk:eui.Button;
        MainUI.prototype.partAdded = function (partName, instance) {
            _super.prototype.partAdded.call(this, partName, instance);
        };
        return MainUI;
    }(eui.Component));
    game.MainUI = MainUI;
    __reflect(MainUI.prototype, "game.MainUI");
})(game || (game = {}));
//# sourceMappingURL=MainUI.js.map