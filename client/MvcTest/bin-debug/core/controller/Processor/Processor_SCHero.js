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
  * 服务器命令返回
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved.
  */
var game;
(function (game) {
    var Processor_SCHero = (function (_super) {
        __extends(Processor_SCHero, _super);
        function Processor_SCHero() {
            return _super.call(this) || this;
        }
        /**
         * 注册消息
         */
        Processor_SCHero.prototype.register = function () {
            this.facade.registerCommand("Processor_SCHero", Processor_SCHero);
        };
        Processor_SCHero.prototype.executeData = function (data) {
            var heroList = Global.getMessage("SCHeroOne");
            //反序列化
            game.DataManager.getInstance().heroList = heroList.decode(data.buffer);
            // console.log("buyhero反序列化数据：",DataManager.getInstance().heroList);
        };
        Processor_SCHero.prototype.execute = function (notification) {
        };
        Processor_SCHero.NAME = "Processor_SCHero";
        return Processor_SCHero;
    }(puremvc.SimpleCommand));
    game.Processor_SCHero = Processor_SCHero;
    __reflect(Processor_SCHero.prototype, "game.Processor_SCHero", ["puremvc.ICommand", "puremvc.INotifier"]);
})(game || (game = {}));
//# sourceMappingURL=Processor_SCHero.js.map