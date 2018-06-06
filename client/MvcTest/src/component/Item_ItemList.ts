module  game {
	export class Item_ItemList extends eui.ItemRenderer{
		public constructor() {
			super();
		}

		protected partAdded(partName:string,instance:any):void
		{
			super.partAdded(partName,instance);
		}


		protected childrenCreated():void
		{
			super.childrenCreated();
		}

		public imgHead:eui.Image;
		public label_level0:eui.Label;
		public label_Name:eui.Label;
		



		protected dataChanged(): void 
		{
			// console.trace(this.data, ">>>>>>>>>>>>>>>>>>>>>>>>>>")
			console.log(this.data, ">>>>>>>>>>>>>>>>>>>>>>>>>>Itemlist");
			
			var itemTemplate = GlobalData.getItemTemplate( this.data["templateid"]);
			if(itemTemplate)
			{
				this.label_level0.text = "1";
				this.label_Name.text = itemTemplate["itemnameid"];
				if( this.data.index == 1 || this.data.index == 3 )
				{
					this.imgHead.source = "item_1104009_png";	
				}
				else
				{
					this.imgHead.source = "item_1209003_png";
				}
			}
		}
	}
}