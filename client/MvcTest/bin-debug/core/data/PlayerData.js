var __reflect = (this && this.__reflect) || function (p, c, t) {
    p.__class__ = c, t ? t.push(c) : t = [c], p.__types__ = p.__types__ ? t.concat(p.__types__) : t;
};
var game;
(function (game) {
    var PlayerData = (function () {
        function PlayerData() {
            this.account = "";
            this.playerid = "";
            this.name = "";
            this.exp = 0;
            this.level = 0;
            this.money = 0;
            this.gold = 0;
            this.ip = "";
            this.createTime = "";
            this.lastLoginTime = "";
        }
        return PlayerData;
    }());
    game.PlayerData = PlayerData;
    __reflect(PlayerData.prototype, "game.PlayerData");
})(game || (game = {}));
//# sourceMappingURL=PlayerData.js.map