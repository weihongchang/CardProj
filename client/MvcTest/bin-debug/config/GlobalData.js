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
    /**
     * 根据heroID获取模板
     */
    function getHero(heroID) {
        console.log("heroid" + heroID);
        for (var i = 0; i < GlobalData.heroTemplate.length; i++) {
            if (GlobalData.heroTemplate[i].heroid == heroID) {
                return GlobalData.heroTemplate[i];
            }
        }
        return null;
    }
    GlobalData.getHero = getHero;
})(GlobalData || (GlobalData = {}));
//# sourceMappingURL=GlobalData.js.map