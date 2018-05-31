/**
 * 角色信息
 */
module game {

    export class RoleInfo extends eui.Component {

        public constructor() {
            super();
            this.skinName = "resource/eui_skins/RoleInfoSkin.exml";

            this.addEventListener(eui.UIEvent.COMPLETE , this.createCompleteEvent, this);
        }

        public createCompleteEvent(event:eui.UIEvent):void{
            this.removeEventListener(eui.UIEvent.COMPLETE , this.createCompleteEvent, this);
            if(DataManager.getInstance().player != null)
            {
                // console.log("roleinfo....."+game.DataManager.getInstance().player.name);
                this.labelName.$setText(DataManager.getInstance().player.name);
                this.labelMoney.text = DataManager.getInstance().player.money+"";
                this.labelGold.text = DataManager.getInstance().player.gold+"";
            }
        }
        public imgHead:eui.Image;
        public labelName:eui.Label;
        public labelMoney:eui.Label;
        public labelGold:eui.Label;
    }
}