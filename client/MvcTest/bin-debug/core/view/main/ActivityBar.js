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
 * 活动条
 */
var game;
(function (game) {
    var ActivityBar = (function (_super) {
        __extends(ActivityBar, _super);
        function ActivityBar() {
            var _this = _super.call(this) || this;
            _this.skinName = "resource/eui_skins/ActivityBarSkin.exml";
            return _this;
        }
        return ActivityBar;
    }(eui.Component));
    game.ActivityBar = ActivityBar;
    __reflect(ActivityBar.prototype, "game.ActivityBar");
})(game || (game = {}));
//# sourceMappingURL=ActivityBar.js.map