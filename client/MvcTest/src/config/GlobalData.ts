/**
  * 全局数据存储
  * by dily
  * (c) copyright 2014 - 2035
  * All Rights Reserved. 
  */
module GlobalData {

    //我的名字
    export var myName: string = "dily";
    export var heroTemplate:any = [];
    export var itemTemplate:any = [];

    /**
     * 根据heroID获取模板
     */
    export  function getHero(heroID:string):Object
    {
        console.log("heroid"+heroID);
        for(var i:number = 0;i<heroTemplate.length;i++)
        {
            if(heroTemplate[i].heroid == heroID)
            {
              return heroTemplate[i];
            }
        }
        return null;
    }


    /**
     * 根据itemID获取模板
     */
    export  function getItemTemplate(itemID:string):Object
    {
        console.log("itemID"+itemID);
        for(var i:number = 0;i<itemTemplate.length;i++)
        {
            if(itemTemplate[i].heroid == itemID)
            {
              return itemTemplate[i];
            }
        }
        return null;
    }


    

}


