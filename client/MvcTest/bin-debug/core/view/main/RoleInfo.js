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
 * 角色信息
 */
var game;
(function (game) {
    var RoleInfo = (function (_super) {
        __extends(RoleInfo, _super);
        function RoleInfo() {
            var _this = _super.call(this) || this;
            _this.skinName = "resource/eui_skins/RoleInfoSkin.exml";
            _this.addEventListener(eui.UIEvent.COMPLETE, _this.createCompleteEvent, _this);
            return _this;
        }
        RoleInfo.prototype.createCompleteEvent = function (event) {
            this.removeEventListener(eui.UIEvent.COMPLETE, this.createCompleteEvent, this);
            if (game.DataManager.getInstance().player != null) {
                // console.log("roleinfo....."+game.DataManager.getInstance().player.name);
                this.labelName.$setText(game.DataManager.getInstance().player.name);
                this.labelMoney.text = game.DataManager.getInstance().player.money + "";
                this.labelGold.text = game.DataManager.getInstance().player.gold + "";
            }
        };
        return RoleInfo;
    }(eui.Component));
    game.RoleInfo = RoleInfo;
    __reflect(RoleInfo.prototype, "game.RoleInfo");
})(game || (game = {}));
//# sourceMappingURL=RoleInfo.js.map