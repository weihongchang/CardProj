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
  * 背包面板
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved.
  */
var game;
(function (game) {
    var BattleMediator = (function (_super) {
        __extends(BattleMediator, _super);
        function BattleMediator(viewComponent) {
            if (viewComponent === void 0) { viewComponent = null; }
            var _this = _super.call(this, BattleMediator.NAME, viewComponent) || this;
            _this.battlePanel = new game.BattlePanel();
            return _this;
        }
        BattleMediator.prototype.listNotificationInterests = function () {
            return [
                PanelNotify.OPEN_Battle,
                PanelNotify.CLOSE_Battle
            ];
        };
        BattleMediator.prototype.handleNotification = function (notification) {
            var data = notification.getBody();
            switch (notification.getName()) {
                case PanelNotify.OPEN_Battle: {
                    //显示角色面板
                    this.showUI(this.battlePanel, false, 0, 0, 5);
                    break;
                }
                case PanelNotify.CLOSE_Battle: {
                    this.battlePanel.RemoveAllChildren();
                    this.closePanel(1);
                    break;
                }
            }
        };
        /**
         * 初始化面板ui
         */
        BattleMediator.prototype.initUI = function () {
            // this.battlePanel.closeBtn.addEventListener(egret.TouchEvent.TOUCH_TAP,this.closeButtonClick,this);
        };
        /**
         * 初始化面板数据
         */
        BattleMediator.prototype.initData = function () {
            this.battlePanel.initData();
            egret.setTimeout(function () {
                EffectUtils.showTips("3", 4, 50, false, GameConfig.curWidth() / 2, GameConfig.curHeight() / 2);
            }, this, 1000);
            egret.setTimeout(function () {
                EffectUtils.showTips("2", 4, 50, false, GameConfig.curWidth() / 2, GameConfig.curHeight() / 2);
            }, this, 2000);
            egret.setTimeout(function () {
                EffectUtils.showTips("1", 4, 50, false, GameConfig.curWidth() / 2, GameConfig.curHeight() / 2);
            }, this, 3000);
            egret.setTimeout(function () {
                this.battlePanel.PlayBattle();
            }, this, 4000);
        };
        BattleMediator.prototype.readExcelButtonClick = function (event) {
        };
        BattleMediator.prototype.closeButtonClick = function (event) {
            this.closePanel(1);
        };
        BattleMediator.NAME = "BattleMediator";
        return BattleMediator;
    }(BaseMediator));
    game.BattleMediator = BattleMediator;
    __reflect(BattleMediator.prototype, "game.BattleMediator");
})(game || (game = {}));
//# sourceMappingURL=BattleMediator.js.map