var __reflect = (this && this.__reflect) || function (p, c, t) {
    p.__class__ = c, t ? t.push(c) : t = [c], p.__types__ = p.__types__ ? t.concat(p.__types__) : t;
};
var game;
(function (game) {
    var MessageController = (function () {
        function MessageController() {
        }
        MessageController.getInstance = function () {
            if (this.instance == null) {
                this.instance = new MessageController();
            }
            return this.instance;
        };
        MessageController.prototype.fireMessage = function (msgid, data) {
            console.log("收到消息:" + msgid);
            switch (msgid) {
                case 0:
                    new game.Processor_100().executeData(data);
                    break;
                case 101:
                    console.log("101");
                    break;
                case 102:
                    console.log("102");
                    new game.Processor_100().executeData(data);
                    break;
                case 104:
                    console.log("104");
                    new game.Processor_SCBuyHero().executeData(data);
                    break;
                case 106:
                    console.log("106");
                    new game.Processor_SCHeroList().executeData(data);
                    break;
                case 108:
                    console.log("108-SCHeroOne");
                    new game.Processor_SCHero().executeData(data);
                    break;
                case 110:
                    console.log("110");
                    new game.Processor_SCItemList().executeData(data);
                    break;
                case 112:
                    console.log("112");
                    new game.Processor_SCItem().executeData(data);
                    break;
                case 116:
                    console.log("116");
                    new game.Processor_SCFight().executeData(data);
                    break;
            }
        };
        return MessageController;
    }());
    game.MessageController = MessageController;
    __reflect(MessageController.prototype, "game.MessageController");
})(game || (game = {}));
//# sourceMappingURL=MessageController.js.map