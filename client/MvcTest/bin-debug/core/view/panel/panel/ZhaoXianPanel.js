/**
 *
 */
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
var game;
(function (game) {
    var ZhaoXianPanel = (function (_super) {
        __extends(ZhaoXianPanel, _super);
        function ZhaoXianPanel() {
            var _this = _super.call(this) || this;
            // this.skinName = "src/core/view/panel/ui/ZhaoXianSkin.exml";
            _this.skinName = "resource/eui_skins/Panel_ZhaoXianSkin.exml";
            _this.addEventListener(eui.UIEvent.COMPLETE, _this.createCompleteEvent, _this);
            _this.btn_card1.addEventListener(egret.TouchEvent.TOUCH_TAP, _this.card1Click, _this);
            return _this;
        }
        ZhaoXianPanel.prototype.createCompleteEvent = function (event) {
            this.removeEventListener(eui.UIEvent.COMPLETE, this.createCompleteEvent, this);
            // game.AppFacade.getInstance().registerMediator( new RoleMediator(this) );
        };
        ZhaoXianPanel.prototype.partAdded = function (partName, instance) {
            _super.prototype.partAdded.call(this, partName, instance);
        };
        ZhaoXianPanel.prototype.card1Click = function (e) {
            MessageSend.sendBuyHero(1);
        };
        return ZhaoXianPanel;
    }(eui.Component));
    game.ZhaoXianPanel = ZhaoXianPanel;
    __reflect(ZhaoXianPanel.prototype, "game.ZhaoXianPanel");
})(game || (game = {}));
//# sourceMappingURL=ZhaoXianPanel.js.map