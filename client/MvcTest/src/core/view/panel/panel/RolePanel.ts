/**
 * 
 */

module game {

    export class RolePanel extends eui.Component {
        private listHero:eui.List = null;
        private listHeroHead:eui.List = null;

        private size:number = 6;


        public closeBtn: eui.Button;
        public scroller:eui.Scroller;
        public scroller_head:eui.Scroller;
        public img_rightBtn:eui.Image;
        public btn_changeHero:eui.Button;
        public btn_formationClose:eui.Image;
        public group_formation:eui.Group;

        public img_fromation_hero0:eui.Image;
        //阵型位置
        public img_formation_box0:eui.Image;
        public img_formation_box1:eui.Image;
        public img_formation_box2:eui.Image;
        public img_formation_box3:eui.Image;
        public img_formation_box4:eui.Image;
        public img_formation_box5:eui.Image;


        private currentPointX:number;
        private isMoving:boolean = false;
        private currentPage = 0;

        private isImgMove:boolean = false;

        private formationBox:eui.Image[] = [];


        private moveHeroOldx:number = 0;
        private moveHeroOldy:number = 0;
        private isMoveHeroSuccess:boolean = false;

        public constructor() {
            super();
            // this.skinName = "src/core/view/panel/ui/RoleSkin.exml";
            this.skinName = "resource/eui_skins/Panel_RoleSkin.exml";

            this.formationBox[0] = this.img_formation_box0;
            this.formationBox[1] = this.img_formation_box1;
            this.formationBox[2] = this.img_formation_box2;
            this.formationBox[3] = this.img_formation_box3;
            this.formationBox[4] = this.img_formation_box4;
            this.formationBox[5] = this.img_formation_box5;

            this.group_formation.visible=false;
            this.addEventListener(eui.UIEvent.COMPLETE , this.createCompleteEvent, this);
            this.scroller.throwSpeed=0;
            this.scroller.addEventListener(egret.TouchEvent.TOUCH_BEGIN,this.onMoveBegin,this);
            this.scroller.addEventListener(eui.UIEvent.CHANGE_END,this.onMoveEnd,this);

            this.img_rightBtn.addEventListener(egret.TouchEvent.TOUCH_TAP,this.onFormation,this);
            this.btn_changeHero.addEventListener(egret.TouchEvent.TOUCH_TAP,this.onChangeHero,this);
            this.btn_formationClose.addEventListener(egret.TouchEvent.TOUCH_TAP,this.onFormationClose,this);


            this.img_fromation_hero0.addEventListener(egret.TouchEvent.TOUCH_BEGIN,this.imgTouchBegin,this);
            this.img_fromation_hero0.addEventListener(egret.TouchEvent.TOUCH_MOVE,this.imgTouchMove,this);
            this.img_fromation_hero0.addEventListener(egret.TouchEvent.TOUCH_END,this.imgToucEnd,this);
            this.img_fromation_hero0.addEventListener(egret.TouchEvent.TOUCH_CANCEL,this.imgToucEnd,this);

            // this.scroller.addEventListener(egret.TouchEvent.TOUCH_END,this.onMoveEnd,this);
            // this.scroller.addEventListener(egret.TouchEvent.TOUCH_MOVE,this.onMove,this);
            // this.scroller.addEventListener(egret.TouchEvent.TOUCH_CANCEL,this.onMoveCancel,this);
            
        }

        public imgTouchBegin(e:egret.TouchEvent)
        {
            this.isImgMove = true;
            this.isMoveHeroSuccess = false;
            this.moveHeroOldx = this.img_fromation_hero0.x;
            this.moveHeroOldy = this.img_fromation_hero0.y;
        }
        public imgTouchMove(e:egret.TouchEvent)
        {
            if(this.isImgMove)
            {
                this.img_fromation_hero0.x = e.$stageX-this.img_fromation_hero0.width/2;
                this.img_fromation_hero0.y = e.$stageY - this.img_fromation_hero0.height/2;
            }
        }
        public imgToucEnd(e:egret.TouchEvent)
        {
            this.isImgMove = false;
            //判断是否移动到新坐标
            for(var i=0;i<this.formationBox.length;i++)
            {
                if( Global.isOverlap(this.img_fromation_hero0,this.formationBox[i]))
                {
                    this.img_fromation_hero0.x = this.formationBox[i].x + (this.formationBox[i].width - this.img_fromation_hero0.width)/2;
                    this.img_fromation_hero0.y = this.formationBox[i].y + (this.formationBox[i].height - this.img_fromation_hero0.height)/2;
                    this.isMoveHeroSuccess = true;
                    break;
                }
            }

            if( !this.isMoveHeroSuccess )
            {
                this.img_fromation_hero0.x = this.moveHeroOldx;
                this.img_fromation_hero0.y = this.moveHeroOldy;
            }
        }

        public createCompleteEvent(event:eui.UIEvent):void{
            this.removeEventListener(eui.UIEvent.COMPLETE , this.createCompleteEvent, this);
            // game.AppFacade.getInstance().registerMediator( new RoleMediator(this) );
        }

        protected createChildren() {
            //初始化后改变滚动的位置
            
            this.scroller.viewport.validateNow();
            this.scroller.viewport.scrollV = 0;
            this.scroller.horizontalScrollBar.visible = false;
            this.scroller.verticalScrollBar.visible = false;
            
        }

        public initData()
        {
           this.initHeroList();
           this.initHeroHeadList();
        }

        /**
         * 初始话英雄列表
         */
        public initHeroList(){
            //创建一个列表
            if(this.listHero == null)
                this.listHero = new eui.List();
            
            this.listHero.useVirtualLayout = true;
            this.listHero.layout = new eui.HorizontalLayout();
            this.listHero.itemRenderer = Item_Hero;
            var sourceArr:any[] = [];

            for (var i:number = 0; i < this.size; i++){
                sourceArr.push({name:"item"+i,index:i});
            }
            this.listHero.dataProvider = new eui.ArrayCollection(sourceArr);
            this.scroller.viewport = this.listHero;
        }
         /**
         * 初始话英雄头像列表
         */
        public initHeroHeadList(){
            //创建一个列表
            if(this.listHeroHead == null)
                this.listHeroHead = new eui.List();
            
            this.listHeroHead.useVirtualLayout = true;
            this.listHeroHead.layout = new eui.HorizontalLayout();
            this.listHeroHead.itemRenderer = Item_HeroHeadList;
            var sourceArr:any[] = [];

            for (var i:number = 0; i < this.size; i++){
                sourceArr.push({name:"item"+i,index:i});
            }
            this.listHeroHead.dataProvider = new eui.ArrayCollection(sourceArr);
            this.scroller_head.viewport = this.listHeroHead;
        }

        
        
        public partAdded(partName:string, instance:any):void{
            super.partAdded(partName, instance);
        }

       

        public onMoveBegin(e:egret.TouchEvent)
        {
            // this.currentPointX = e.$stageX;
           // console.log("begin");
        }
        
        public onMove(e:egret.TouchEvent)
        {
            console.log("move");
        }
        public onMoveCancel(e:egret.TouchEvent)
        {
            console.log("onMoveCancel");
        }
        public onMoveEnd(e:egret.TouchEvent)
        {
            // this.currentPointX = 0;
            this.getCurrentPage();
            console.log("end");
        }

        public onFormation(e:egret.TouchEvent)
        {
            console.log("切换阵型");
            this.group_formation.visible=true;
        }

        public onChangeHero(e:egret.TouchEvent)
        {
            console.log("切换英雄");
        }
        
        public onFormationClose(e:egret.TouchEvent)
        {
            this.group_formation.visible=false;
        }


        private getCurrentPage()
        {
            var moNum = this.scroller.viewport.scrollH % this.stage.stageWidth;
            let pageNum:number = this.scroller.viewport.scrollH / this.stage.stageWidth;
            pageNum = Math.floor(pageNum);
            // console.log( this.scroller.viewport.scrollH+"     "+ moNum+"   "+chuNum);
            if(moNum > this.stage.stageWidth/2)
            {
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
            var npoint = pageNum* this.stage.stageWidth;
            this.MovePanel(npoint);
        }

        private MovePanel(nPoint:number)
        {
            // if(this.page<0)
            //     return;
            if(!this.isMoving)
            {
                this.isMoving = true;
                console.log("move to "+nPoint);
                egret.Tween.get(this.scroller.viewport, { loop: false })
                .to({scrollH: nPoint}, 200).call(()=>this.MoveStatusChange(),this);
            }
        }
        
        private MoveStatusChange(){
            this.isMoving=false;
        }

    }
}