module game {
	export class PlayerData {
			public account:string = "";
			public playerid:string = "";
			public name:string= "";
			public exp:number = 0;
			public level:number = 0;
			public money:number = 0;
			public gold:number = 0;
			public ip:string= "";
			public createTime:string= "";
			public lastLoginTime:string= "";
	}

	export class HeroData {
			public templateid:number = 0;
			public playerid:number = 0;
			public exp:number = 0;
			public level:number = 0;
	}
}