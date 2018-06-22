/**
 *
 */
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
    var RolePanel = (function (_super) {
        __extends(RolePanel, _super);
        function RolePanel() {
            var _this = _super.call(this) || this;
            _this.listHero = null;
            _this.listHeroHead = null;
            _this.isMoving = false;
            _this.currentPage = 0;
            _this.isImgMove = false;
            _this.formationBox = [];
            _this.moveHeroOldx = 0;
            _this.moveHeroOldy = 0;
            _this.isMoveHeroSuccess = false;
            /**
             * 临时阵型
             */
            _this.formationTemp = [];
            /**
             * 阵型中的英雄头像
             */
            _this.formationHeroTemp = [];
            // this.skinName = "src/core/view/panel/ui/RoleSkin.exml";
            _this.skinName = "resource/eui_skins/Panel_RoleSkin.exml";
            _this.formationBox[0] = _this.img_formation_box0;
            _this.formationBox[1] = _this.img_formation_box1;
            _this.formationBox[2] = _this.img_formation_box2;
            _this.formationBox[3] = _this.img_formation_box3;
            _this.formationBox[4] = _this.img_formation_box4;
            _this.formationBox[5] = _this.img_formation_box5;
            _this.formationBox[6] = _this.img_formation_box6;
            _this.formationBox[7] = _this.img_formation_box7;
            _this.formationBox[8] = _this.img_formation_box8;
            _this.group_formation.visible = false;
            _this.addEventListener(eui.UIEvent.COMPLETE, _this.createCompleteEvent, _this);
            _this.scroller.throwSpeed = 0;
            _this.scroller.addEventListener(egret.TouchEvent.TOUCH_BEGIN, _this.onMoveBegin, _this);
            _this.scroller.addEventListener(eui.UIEvent.CHANGE_END, _this.onMoveEnd, _this);
            _this.img_rightBtn.addEventListener(egret.TouchEvent.TOUCH_TAP, _this.onFormation, _this);
            _this.btn_changeHero.addEventListener(egret.TouchEvent.TOUCH_TAP, _this.onChangeHero, _this);
            _this.btn_formationClose.addEventListener(egret.TouchEvent.TOUCH_TAP, _this.onFormationClose, _this);
            _this.img_fromation_hero0.addEventListener(egret.TouchEvent.TOUCH_BEGIN, _this.imgTouchBegin, _this);
            _this.img_fromation_hero0.addEventListener(egret.TouchEvent.TOUCH_MOVE, _this.imgTouchMove, _this);
            _this.img_fromation_hero0.addEventListener(egret.TouchEvent.TOUCH_END, _this.imgToucEnd, _this);
            _this.img_fromation_hero0.addEventListener(egret.TouchEvent.TOUCH_CANCEL, _this.imgToucEnd, _this);
            return _this;
            // this.scroller.addEventListener(egret.TouchEvent.TOUCH_END,this.onMoveEnd,this);
            // this.scroller.addEventListener(egret.TouchEvent.TOUCH_MOVE,this.onMove,this);
            // this.scroller.addEventListener(egret.TouchEvent.TOUCH_CANCEL,this.onMoveCancel,this);
        }
        RolePanel.prototype.imgTouchBegin = function (e) {
            this.isImgMove = true;
            this.isMoveHeroSuccess = false;
            this.moveHeroOldx = e.currentTarget.x;
            this.moveHeroOldy = e.currentTarget.y;
        };
        RolePanel.prototype.imgTouchMove = function (e) {
            if (this.isImgMove) {
                e.currentTarget.x = e.$stageX - e.currentTarget.width / 2;
                e.currentTarget.y = e.$stageY - e.currentTarget.height / 2;
            }
        };
        RolePanel.prototype.imgToucEnd = function (e) {
            this.isImgMove = false;
            //判断是否移动到新坐标
            for (var i = 0; i < this.formationBox.length; i++) {
                if (Global.isOverlap(e.currentTarget, this.formationBox[i])) {
                    e.currentTarget.x = this.formationBox[i].x + (this.formationBox[i].width - e.currentTarget.width) / 2;
                    e.currentTarget.y = this.formationBox[i].y + (this.formationBox[i].height - e.currentTarget.height) / 2;
                    this.isMoveHeroSuccess = true;
                    break;
                }
            }
            if (!this.isMoveHeroSuccess) {
                e.currentTarget.x = this.moveHeroOldx;
                e.currentTarget.y = this.moveHeroOldy;
            }
        };
        RolePanel.prototype.createCompleteEvent = function (event) {
            this.removeEventListener(eui.UIEvent.COMPLETE, this.createCompleteEvent, this);
            // game.AppFacade.getInstance().registerMediator( new RoleMediator(this) );
        };
        RolePanel.prototype.createChildren = function () {
            //初始化后改变滚动的位置
            this.scroller.viewport.validateNow();
            this.scroller.viewport.scrollV = 0;
            this.scroller.horizontalScrollBar.visible = false;
            this.scroller.verticalScrollBar.visible = false;
        };
        RolePanel.prototype.initData = function () {
            this.initHeroList();
            this.initHeroHeadList();
        };
        /**
         * 初始话英雄列表
         */
        RolePanel.prototype.initHeroList = function () {
            //创建一个列表
            if (this.listHero == null)
                this.listHero = new eui.List();
            this.listHero.useVirtualLayout = true;
            this.listHero.layout = new eui.HorizontalLayout();
            this.listHero.itemRenderer = game.Item_Hero;
            var sourceArr = [];
            for (var i = 0; i < game.DataManager.getInstance().fightBoxMaxNum; i++) {
                sourceArr.push({ name: "item" + i, index: i });
            }
            this.listHero.dataProvider = new eui.ArrayCollection(sourceArr);
            this.scroller.viewport = this.listHero;
        };
        /**
        * 初始话英雄头像列表
        */
        RolePanel.prototype.initHeroHeadList = function () {
            //创建一个列表
            if (this.listHeroHead == null)
                this.listHeroHead = new eui.List();
            this.listHeroHead.useVirtualLayout = true;
            this.listHeroHead.layout = new eui.HorizontalLayout();
            this.listHeroHead.itemRenderer = game.Item_HeroHeadList;
            var sourceArr = [];
            for (var i = 0; i < game.DataManager.getInstance().fightBoxMaxNum; i++) {
                sourceArr.push({ name: "item" + i, index: i });
            }
            this.listHeroHead.dataProvider = new eui.ArrayCollection(sourceArr);
            this.scroller_head.viewport = this.listHeroHead;
        };
        /**
         * 初始化阵型panel
         */
        RolePanel.prototype.initFormation = function () {
            this.clearFormation();
            this.formationTemp = game.DataManager.getInstance().formation["formationID"];
            // formationHeroTemp
            for (var i = 0; i < this.formationTemp.length; i++) {
                if (this.formationTemp[i] > 0) {
                    var imgIcon = new eui.Image();
                    imgIcon.source = "head_zhangjiao_png";
                    imgIcon.width = 80;
                    imgIcon.height = 80;
                    this.formationHeroIconPoint(i, imgIcon);
                    imgIcon.addEventListener(egret.TouchEvent.TOUCH_BEGIN, this.imgTouchBegin, this);
                    imgIcon.addEventListener(egret.TouchEvent.TOUCH_MOVE, this.imgTouchMove, this);
                    imgIcon.addEventListener(egret.TouchEvent.TOUCH_END, this.imgToucEnd, this);
                    imgIcon.addEventListener(egret.TouchEvent.TOUCH_CANCEL, this.imgToucEnd, this);
                    this.formationHeroTemp.push({ index: i, icon: imgIcon });
                    this.group_formation.addChild(imgIcon);
                }
            }
        };
        /**
         * 清空阵型
         */
        RolePanel.prototype.clearFormation = function () {
            if (this.formationHeroTemp && this.formationHeroTemp.length > 0) {
                for (var i = 0; i < this.formationHeroTemp.length; i++) {
                    this.group_formation.removeChild(this.formationHeroTemp[i].icon);
                }
            }
            this.formationHeroTemp = [];
        };
        /**
         * 阵型英雄头像位置
         */
        RolePanel.prototype.formationHeroIconPoint = function (index, imgHeroIcon) {
            imgHeroIcon.x = this.formationBox[index].x + (this.formationBox[index].width - imgHeroIcon.width) / 2;
            imgHeroIcon.y = this.formationBox[index].y + (this.formationBox[index].height - imgHeroIcon.height) / 2;
        };
        /**
         * 保存阵型
         */
        RolePanel.prototype.saveFormation = function () {
        };
        RolePanel.prototype.partAdded = function (partName, instance) {
            _super.prototype.partAdded.call(this, partName, instance);
        };
        RolePanel.prototype.onMoveBegin = function (e) {
            // this.currentPointX = e.$stageX;
            // console.log("begin");
        };
        RolePanel.prototype.onMove = function (e) {
            console.log("move");
        };
        RolePanel.prototype.onMoveCancel = function (e) {
            console.log("onMoveCancel");
        };
        RolePanel.prototype.onMoveEnd = function (e) {
            // this.currentPointX = 0;
            this.getCurrentPage();
            console.log("end");
        };
        RolePanel.prototype.onFormation = function (e) {
            console.log("切换阵型");
            this.initFormation();
            this.group_formation.visible = true;
        };
        RolePanel.prototype.onChangeHero = function (e) {
            console.log("切换英雄");
        };
        RolePanel.prototype.onFormationClose = function (e) {
            this.group_formation.visible = false;
        };
        RolePanel.prototype.getCurrentPage = function () {
            var moNum = this.scroller.viewport.scrollH % this.stage.stageWidth;
            var pageNum = this.scroller.viewport.scrollH / this.stage.stageWidth;
            pageNum = Math.floor(pageNum);
            // console.log( this.scroller.viewport.scrollH+"     "+ moNum+"   "+chuNum);
            if (moNum > this.stage.stageWidth / 2) {
                pageNum++;
            }
            // if( this.currentPage>0 && pageNum < this.currentPage )
            // {
            //     if(moNum < this.stage.stageWidth/2)
            //     {
            //         pageNum--;
            //     }
            // }
            this.currentPage = pageNum;
            var npoint = pageNum * this.stage.stageWidth;
            this.MovePanel(npoint);
        };
        RolePanel.prototype.MovePanel = function (nPoint) {
            var _this = this;
            // if(this.page<0)
            //     return;
            if (!this.isMoving) {
                this.isMoving = true;
                console.log("move to " + nPoint);
                egret.Tween.get(this.scroller.viewport, { loop: false })
                    .to({ scrollH: nPoint }, 200).call(function () { return _this.MoveStatusChange(); }, this);
            }
        };
        RolePanel.prototype.MoveStatusChange = function () {
            this.isMoving = false;
        };
        return RolePanel;
    }(eui.Component));
    game.RolePanel = RolePanel;
    __reflect(RolePanel.prototype, "game.RolePanel");
})(game || (game = {}));
//# sourceMappingURL=RolePanel.js.map