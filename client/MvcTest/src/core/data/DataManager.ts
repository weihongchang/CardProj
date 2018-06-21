module game {
	export class DataManager {
		private static instance:DataManager;

		public static getInstance():DataManager{

			if(this.instance == null){
				this.instance = new DataManager();
			}
			return this.instance;
		}

		/**
		 * 阵型最大格子数
		 */
		public fightBoxMaxNum:number = 9;

		public dungeonList:DungeonData[] = [];
		public dungeonLevelList:DungeonLevelData[] = [];
		public player:PlayerData ;
		public heroList:any = [];
		public itemList:any = [];
		public formation:any = [];
		public battleLog:any = [];
		

		public InitTestData(){
			this.player = new PlayerData();

			for (var i:number = 1; i < 10; i++){
				var dungeon = new DungeonData();
				dungeon.dungeonID = i;
				dungeon.dungeonName ="test"+i;
				dungeon.dungeonType = 1;
				dungeon.dungeonImg = "dungeon"+ i +"_jpg";
				this.dungeonList.push(dungeon);
			}

		}

		/**
		 * 根据id获取副本
		 */
		public getDungeonForID( index:number):DungeonData{
			for (let entry of this.dungeonList) {
				if( entry.dungeonID == index )
				{
					return entry;
				}
			}
			return null;
		}
	}
}