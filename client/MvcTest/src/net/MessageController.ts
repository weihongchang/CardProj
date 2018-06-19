module game {
	export class MessageController {
		private static instance:MessageController;

		public static getInstance():MessageController{

			if(this.instance == null){
				this.instance = new MessageController();
			}
			return this.instance;
		}

		public fireMessage(msgid:number,data:egret.ByteArray)
		{
			console.log("收到消息:"+msgid);
			switch(msgid)
			{
				case 0:
					new Processor_100().executeData(data);
					break;
				case 101:
					console.log("101");
					break;
				case 102:
					console.log("102");
					new Processor_100().executeData(data);
					break;
				case 104:
					console.log("104");
					new Processor_SCBuyHero().executeData(data);
					break
				case 106:
					console.log("106");
					new Processor_SCHeroList().executeData(data);
					break
				case 108:
					console.log("108-SCHeroOne");
					new Processor_SCHero().executeData(data);
					break
				case 110:
					console.log("110");
					new Processor_SCItemList().executeData(data);
					break
				case 112:
					console.log("112");
					new Processor_SCItem().executeData(data);
					break
				case 116:
					console.log("116");
					new Processor_SCFight().executeData(data);
					break
			}
		}

	}
}