/**
 * 
 */

module game {

    export class ZhaoXianPanel extends eui.Component {

        public constructor() {
            super();
            // this.skinName = "src/core/view/panel/ui/ZhaoXianSkin.exml";
            this.skinName = "resource/eui_skins/Panel_ZhaoXianSkin.exml";
            this.addEventListener(eui.UIEvent.COMPLETE , this.createCompleteEvent, this);
            this.btn_card1.addEventListener(egret.TouchEvent.TOUCH_TAP,this.card1Click,this);
        }

        public createCompleteEvent(event:eui.UIEvent):void{
            this.removeEventListener(eui.UIEvent.COMPLETE , this.createCompleteEvent, this);
            // game.AppFacade.getInstance().registerMediator( new RoleMediator(this) );
        }

        public closeBtn: eui.Button;
        public btn_card1:eui.Button;
        
        public partAdded(partName:string, instance:any):void{
            super.partAdded(partName, instance);
        }

        private card1Click(e: egret.TouchEvent) {
            
            MessageSend.sendBuyHero(1);
        }        
    }
}