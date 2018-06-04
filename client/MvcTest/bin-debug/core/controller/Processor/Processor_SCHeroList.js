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
    var Processor_SCHeroList = (function (_super) {
        __extends(Processor_SCHeroList, _super);
        function Processor_SCHeroList() {
            return _super.call(this) || this;
        }
        /**
         * 注册消息
         */
        Processor_SCHeroList.prototype.register = function () {
            this.facade.registerCommand("Processor_SCHeroList", Processor_SCHeroList);
        };
        Processor_SCHeroList.prototype.executeData = function (data) {
            var heroList = Global.getMessage("SCHeroList");
            //反序列化
            game.DataManager.getInstance().heroList = heroList.decode(data.buffer);
            console.log("buyhero反序列化数据：", game.DataManager.getInstance().heroList);
        };
        Processor_SCHeroList.prototype.execute = function (notification) {
        };
        Processor_SCHeroList.NAME = "Processor_SCHeroList";
        return Processor_SCHeroList;
    }(puremvc.SimpleCommand));
    game.Processor_SCHeroList = Processor_SCHeroList;
    __reflect(Processor_SCHeroList.prototype, "game.Processor_SCHeroList", ["puremvc.ICommand", "puremvc.INotifier"]);
})(game || (game = {}));
//# sourceMappingURL=Processor_SCHeroList.js.map