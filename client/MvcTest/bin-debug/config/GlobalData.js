/**
  * 全局数据存储
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved.
  */
var GlobalData;
(function (GlobalData) {
    //我的名字
    GlobalData.myName = "dily";
    GlobalData.heroTemplate = [];
    GlobalData.itemTemplate = [];
    /**
     * 根据heroID获取模板
     */
    function getHero(heroID) {
        // console.log("heroid"+heroID);
        for (var i = 0; i < GlobalData.heroTemplate.length; i++) {
            if (GlobalData.heroTemplate[i].heroid == heroID) {
                return GlobalData.heroTemplate[i];
            }
        }
        return null;
    }
    GlobalData.getHero = getHero;
    /**
     * 根据itemID获取模板
     */
    function getItemTemplate(itemID) {
        console.log("itemID" + itemID);
        for (var i = 0; i < GlobalData.itemTemplate.length; i++) {
            if (GlobalData.itemTemplate[i].itemid == itemID) {
                return GlobalData.itemTemplate[i];
            }
        }
        return null;
    }
    GlobalData.getItemTemplate = getItemTemplate;
})(GlobalData || (GlobalData = {}));
//# sourceMappingURL=GlobalData.js.map