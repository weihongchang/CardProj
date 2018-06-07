/**
  * 游戏公用方法汇总
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved.
  * 使用方法如：Global.setCookie()
  */
var Global;
(function (Global) {
    //显示等待界面
    function showWaritPanel() {
        Global.waitPanel = new WaitPanel(1);
        GameLayerManager.gameLayer().maskLayer.removeChildren();
        GameLayerManager.gameLayer().maskLayer.addChild(Global.waitPanel);
    }
    Global.showWaritPanel = showWaritPanel;
    //移除界面
    function hideWaritPanel() {
        if ((Global.waitPanel != null) && GameLayerManager.gameLayer().maskLayer.contains(Global.waitPanel)) {
            GameLayerManager.gameLayer().maskLayer.removeChild(Global.waitPanel);
        }
    }
    Global.hideWaritPanel = hideWaritPanel;
    //获取html文本
    function getTextFlow(str) {
        var styleParser = new egret.HtmlTextParser();
        return styleParser.parser(str);
    }
    Global.getTextFlow = getTextFlow;
    function getMessage(str) {
        if (Global.message == null) {
            //初始化template_proto
            Global.message = dcodeIO.ProtoBuf.loadProto(RES.getRes("template_proto"));
        }
        return Global.message.build(str);
    }
    Global.getMessage = getMessage;
    /**
    * @brief 判断两个轴对齐的矩形是否重叠
    * @param rc1 第一个矩阵的位置
    * @param rc2 第二个矩阵的位置
    * @return 两个矩阵是否重叠（边沿重叠，也认为是重叠）
    */
    function isOverlap(rc1, rc2) {
        if (rc1.x + rc1.width > rc2.x &&
            rc2.x + rc2.width > rc1.x &&
            rc1.y + rc1.height > rc2.y &&
            rc2.y + rc2.height > rc1.y)
            return true;
        else
            return false;
    }
    Global.isOverlap = isOverlap;
    //获取大写数字
    function getNumber(num) {
        switch (num) {
            case 0: {
                return "零";
                break;
            }
            case 1: {
                return "一";
                break;
            }
            case 2: {
                return "二";
                break;
            }
            case 3: {
                return "三";
                break;
            }
            case 4: {
                return "四";
                break;
            }
            case 5: {
                return "五";
                break;
            }
            case 6: {
                return "六";
                break;
            }
            case 7: {
                return "七";
                break;
            }
            case 8: {
                return "八";
                break;
            }
            case 9: {
                return "九";
                break;
            }
            default: {
                // TODO: Implemente default case
                console.log("default case");
            }
        }
    }
    Global.getNumber = getNumber;
})(Global || (Global = {}));
//# sourceMappingURL=Global.js.map