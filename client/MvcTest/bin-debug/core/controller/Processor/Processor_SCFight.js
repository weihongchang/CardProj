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
    var Processor_SCFight = (function (_super) {
        __extends(Processor_SCFight, _super);
        function Processor_SCFight() {
            return _super.call(this) || this;
        }
        /**
         * 注册消息
         */
        Processor_SCFight.prototype.register = function () {
            this.facade.registerCommand("Processor_SCFight", Processor_SCFight);
        };
        Processor_SCFight.prototype.executeData = function (data) {
            //创建user_login_class
            var user_login_class = Global.getMessage("SCFight");
            //反序列化
            var new_user_login = user_login_class.decode(data.buffer);
            console.log("buyhero反序列化数据：", new_user_login);
            // console.log(game.DataManager.getInstance().player.name);
            if (new_user_login.status >= 0) {
                for (var i = 1; i < new_user_login.heroid.length; i++) {
                    var hero = GlobalData.getHero(new_user_login.heroid[i]);
                    if (hero) {
                        console.log("获得武将[" + hero["heroname"] + "]");
                    }
                }
                game.AppFacade.getInstance().sendNotification(SceneNotify.OPEN_HOME);
                game.AppFacade.getInstance().sendNotification(MainNotify.OPEN_MAIN);
            }
            else {
                console.error("登陆失败！");
            }
        };
        Processor_SCFight.prototype.execute = function (notification) {
        };
        Processor_SCFight.NAME = "Processor_SCFight";
        return Processor_SCFight;
    }(puremvc.SimpleCommand));
    game.Processor_SCFight = Processor_SCFight;
    __reflect(Processor_SCFight.prototype, "game.Processor_SCFight", ["puremvc.ICommand", "puremvc.INotifier"]);
})(game || (game = {}));
//# sourceMappingURL=Processor_SCFight.js.map