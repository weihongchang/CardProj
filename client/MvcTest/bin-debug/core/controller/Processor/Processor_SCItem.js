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
    var Processor_SCItem = (function (_super) {
        __extends(Processor_SCItem, _super);
        function Processor_SCItem() {
            return _super.call(this) || this;
        }
        /**
         * 注册消息
         */
        Processor_SCItem.prototype.register = function () {
            this.facade.registerCommand("Processor_SCItem", Processor_SCItem);
        };
        Processor_SCItem.prototype.executeData = function (data) {
            var heroList = Global.getMessage("SCItemOne");
            //反序列化
            // DataManager.getInstance().heroList = heroList.decode(data.buffer);
            // console.log("buyhero反序列化数据：",DataManager.getInstance().heroList);
        };
        Processor_SCItem.prototype.execute = function (notification) {
        };
        Processor_SCItem.NAME = "Processor_SCItem";
        return Processor_SCItem;
    }(puremvc.SimpleCommand));
    game.Processor_SCItem = Processor_SCItem;
    __reflect(Processor_SCItem.prototype, "game.Processor_SCItem", ["puremvc.ICommand", "puremvc.INotifier"]);
})(game || (game = {}));
//# sourceMappingURL=Processor_SCItem.js.map