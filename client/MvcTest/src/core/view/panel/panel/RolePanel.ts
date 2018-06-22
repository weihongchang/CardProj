/**
 * 
 */

module game {

    export class RolePanel extends eui.Component {
        private listHero:eui.List = null;
        private listHeroHead:eui.List = null;


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
        public img_formation_box6:eui.Image;
        public img_formation_box7:eui.Image;
        public img_formation_box8:eui.Image;
        


        private currentPointX:number;
        private isMoving:boolean = false;
        private currentPage = 0;

        private isImgMove:boolean = false;

        private formationBox:eui.Image[] = [];


        private moveHeroOldx:number = 0;
        private moveHeroOldy:number = 0;
        private isMoveHeroSuccess:boolean = false;

        /**
         * 临时阵型
         */
        private formationTemp:number[] = [];

        /**
         * 阵型中的英雄头像
         */
        private formationHeroTemp:any = [];

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
            this.formationBox[6] = this.img_formation_box6;
            this.formationBox[7] = this.img_formation_box7;
            this.formationBox[8] = this.img_formation_box8;

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
            this.moveHeroOldx = e.currentTarget.x;
            this.moveHeroOldy = e.currentTarget.y;
        }
        public imgTouchMove(e:egret.TouchEvent)
        {
            if(this.isImgMove)
            {
                e.currentTarget.x = e.$stageX- e.currentTarget.width/2;
                e.currentTarget.y = e.$stageY - e.currentTarget.height/2;
            }
        }
        public imgToucEnd(e:egret.TouchEvent)
        {
            this.isImgMove = false;
            //判断是否移动到新坐标
            for(var i=0;i<this.formationBox.length;i++)
            {
                if( Global.isOverlap(e.currentTarget,this.formationBox[i]))
                {
                    e.currentTarget.x = this.formationBox[i].x + (this.formationBox[i].width - e.currentTarget.width)/2;
                    e.currentTarget.y = this.formationBox[i].y + (this.formationBox[i].height - e.currentTarget.height)/2;
                    this.isMoveHeroSuccess = true;
                    break;
                }
            }

            if( !this.isMoveHeroSuccess )
            {
                e.currentTarget.x = this.moveHeroOldx;
                e.currentTarget.y = this.moveHeroOldy;
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

            for (var i:number = 0; i < DataManager.getInstance().fightBoxMaxNum; i++){
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

            for (var i:number = 0; i < DataManager.getInstance().fightBoxMaxNum; i++){
                sourceArr.push({name:"item"+i,index:i});
            }
            this.listHeroHead.dataProvider = new eui.ArrayCollection(sourceArr);
            this.scroller_head.viewport = this.listHeroHead;
        }

        /**
         * 初始化阵型panel
         */
        public initFormation()
        {
            this.clearFormation();
            this.formationTemp = DataManager.getInstance().formation["formationID"];
            // formationHeroTemp
            for(var i=0;i<this.formationTemp.length;i++)
            {
                if( this.formationTemp[i] > 0 )
                {
                    let imgIcon:eui.Image = new eui.Image();
                    imgIcon.source = "head_zhangjiao_png";
                    imgIcon.width = 80;
                    imgIcon.height = 80;
                    this.formationHeroIconPoint(i,imgIcon);

                    imgIcon.addEventListener(egret.TouchEvent.TOUCH_BEGIN,this.imgTouchBegin,this);
                    imgIcon.addEventListener(egret.TouchEvent.TOUCH_MOVE,this.imgTouchMove,this);
                    imgIcon.addEventListener(egret.TouchEvent.TOUCH_END,this.imgToucEnd,this);
                    imgIcon.addEventListener(egret.TouchEvent.TOUCH_CANCEL,this.imgToucEnd,this);

                    this.formationHeroTemp.push({index:i,heroid:this.formationTemp[i],icon:imgIcon});
                    this.group_formation.addChild( imgIcon );
                }
            }
        }

        /**
         * 清空阵型
         */
        private clearFormation()
        {
            if( this.formationHeroTemp && this.formationHeroTemp.length>0)
            {
                for( var i=0;i<this.formationHeroTemp.length;i++ )
                {
                    this.group_formation.removeChild(this.formationHeroTemp[i].icon);
                }
            }
            this.formationHeroTemp = [];
        }

        /**
         * 阵型英雄头像位置
         */
        public formationHeroIconPoint(index:number,imgHeroIcon:eui.Image)
        {
            imgHeroIcon.x = this.formationBox[index].x + (this.formationBox[index].width - imgHeroIcon.width)/2;
            imgHeroIcon.y = this.formationBox[index].y + (this.formationBox[index].height - imgHeroIcon.height)/2;
        }

        /**
         * 保存阵型
         */
        private saveFormation()
        {
            
            for( var i=0;i<this.formationTemp.length;i++ )
            {
                this.formationTemp[i] = 0;
            }

            for( var i=0;i<this.formationHeroTemp.length;i++ )
            {
                this.formationTemp[this.formationHeroTemp[i].index] =  this.formationHeroTemp[i].heroid;
            }
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
            this.initFormation();
            this.group_formation.visible=true;
        }

        public onChangeHero(e:egret.TouchEvent)
        {
            console.log("切换英雄");
        }
        
        public onFormationClose(e:egret.TouchEvent)
        {
            this.saveFormation();
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