var __reflect = (this && this.__reflect) || function (p, c, t) {
    p.__class__ = c, t ? t.push(c) : t = [c], p.__types__ = p.__types__ ? t.concat(p.__types__) : t;
};
/**
  * 场景消息
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved.
  */
var SceneNotify = (function () {
    function SceneNotify() {
    }
    //打开主城场景
    SceneNotify.OPEN_HOME = "SceneNotify_OPEN_HOME";
    //关闭主城场景
    SceneNotify.CLOSE_HOME = "SceneNotify_CLOSE_HOME";
    //进入战斗场景
    SceneNotify.OPEN_BATTLE = "SceneNotify_OPEN_BATTLE";
    //关闭战斗场景
    SceneNotify.CLOSE_BATTLE = "SceneNotify_CLOSE_BATTLE";
    return SceneNotify;
}());
__reflect(SceneNotify.prototype, "SceneNotify");
//# sourceMappingURL=SceneNotify.js.map