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
var game;
(function (game) {
    var Item_HeroList = (function (_super) {
        __extends(Item_HeroList, _super);
        function Item_HeroList() {
            return _super.call(this) || this;
            // this.imgHero = new eui.Image();
        }
        Item_HeroList.prototype.partAdded = function (partName, instance) {
            _super.prototype.partAdded.call(this, partName, instance);
        };
        Item_HeroList.prototype.childrenCreated = function () {
            _super.prototype.childrenCreated.call(this);
        };
        Item_HeroList.prototype.dataChanged = function () {
            // console.trace(this.data, ">>>>>>>>>>>>>>>>>>>>>>>>>>")
            console.log(this.data, ">>>>>>>>>>>>>>>>>>>>>>>>>>");
            var heroTemplate = GlobalData.getHero(this.data["templateid"]);
            if (heroTemplate) {
                this.label_level.text = "1";
                this.label_Name.text = heroTemplate["heroname"];
                if (this.data.index == 1 || this.data.index == 3) {
                    // this.imgbg.source = "follower_normal_1_png";
                    this.imgHead.source = "head_caifuren_png";
                }
                else {
                    // this.imgbg.source = "follower_normal_2_png";
                    this.imgHead.source = "head_caocao_png";
                }
            }
        };
        return Item_HeroList;
    }(eui.ItemRenderer));
    game.Item_HeroList = Item_HeroList;
    __reflect(Item_HeroList.prototype, "game.Item_HeroList");
})(game || (game = {}));
//# sourceMappingURL=Item_HeroList.js.map