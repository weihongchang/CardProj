module game {
	export class Item_HeroList extends eui.ItemRenderer {
		
		public imgHead:eui.Image;
		public label_level:eui.Label;
		public label_Name:eui.Label;

		public constructor() {
			super();

			// this.imgHero = new eui.Image();
		}

		protected partAdded(partName:string,instance:any):void
		{
			super.partAdded(partName,instance);
		}


		protected childrenCreated():void
		{
			super.childrenCreated();
		}

		protected dataChanged(): void {
			// console.trace(this.data, ">>>>>>>>>>>>>>>>>>>>>>>>>>")
			console.log(this.data, ">>>>>>>>>>>>>>>>>>>>>>>>>>");
			
			var heroTemplate = GlobalData.getHero( this.data["templateid"]);
			if(heroTemplate)
			{
				this.label_level.text = "1";
				this.label_Name.text = heroTemplate["heroname"];
				if( this.data.index == 1 || this.data.index == 3 )
				{
					// this.imgbg.source = "follower_normal_1_png";
					this.imgHead.source = "head_caifuren_png";
					
				}
				else
				{
					// this.imgbg.source = "follower_normal_2_png";
					this.imgHead.source = "head_caocao_png";
				}
			}
		}
	}
}