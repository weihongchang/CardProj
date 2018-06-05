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
    var Processor_SCItemList = (function (_super) {
        __extends(Processor_SCItemList, _super);
        function Processor_SCItemList() {
            return _super.call(this) || this;
        }
        /**
         * 注册消息
         */
        Processor_SCItemList.prototype.register = function () {
            this.facade.registerCommand("Processor_SCItemList", Processor_SCItemList);
        };
        Processor_SCItemList.prototype.executeData = function (data) {
            var itemList = Global.getMessage("SCItemList");
            //反序列化
            game.DataManager.getInstance().itemList = itemList.decode(data.buffer);
            console.log("Itemlist 数据：", game.DataManager.getInstance().itemList);
        };
        Processor_SCItemList.prototype.execute = function (notification) {
        };
        Processor_SCItemList.NAME = "Processor_SCItemList";
        return Processor_SCItemList;
    }(puremvc.SimpleCommand));
    game.Processor_SCItemList = Processor_SCItemList;
    __reflect(Processor_SCItemList.prototype, "game.Processor_SCItemList", ["puremvc.ICommand", "puremvc.INotifier"]);
})(game || (game = {}));
//# sourceMappingURL=Processor_SCItemList.js.map