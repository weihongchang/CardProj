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
    var Processor_SCFormation = (function (_super) {
        __extends(Processor_SCFormation, _super);
        function Processor_SCFormation() {
            return _super.call(this) || this;
        }
        /**
         * 注册消息
         */
        Processor_SCFormation.prototype.register = function () {
            this.facade.registerCommand("Processor_SCFormation", Processor_SCFormation);
        };
        Processor_SCFormation.prototype.executeData = function (data) {
            var heroList = Global.getMessage("SCFormation");
            //反序列化
            game.DataManager.getInstance().formation = heroList.decode(data.buffer);
        };
        Processor_SCFormation.prototype.execute = function (notification) {
        };
        Processor_SCFormation.NAME = "Processor_SCFormation";
        return Processor_SCFormation;
    }(puremvc.SimpleCommand));
    game.Processor_SCFormation = Processor_SCFormation;
    __reflect(Processor_SCFormation.prototype, "game.Processor_SCFormation", ["puremvc.ICommand", "puremvc.INotifier"]);
})(game || (game = {}));
//# sourceMappingURL=Processor_SCFormation.js.map