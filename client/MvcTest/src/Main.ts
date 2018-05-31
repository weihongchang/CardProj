//////////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (c) 2014-present, Egret Technology.
//  All rights reserved.
//  Redistribution and use in source and binary forms, with or without
//  modification, are permitted provided that the following conditions are met:
//
//     * Redistributions of source code must retain the above copyright
//       notice, this list of conditions and the following disclaimer.
//     * Redistributions in binary form must reproduce the above copyright
//       notice, this list of conditions and the following disclaimer in the
//       documentation and/or other materials provided with the distribution.
//     * Neither the name of the Egret nor the
//       names of its contributors may be used to endorse or promote products
//       derived from this software without specific prior written permission.
//
//  THIS SOFTWARE IS PROVIDED BY EGRET AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
//  OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
//  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
//  IN NO EVENT SHALL EGRET AND CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
//  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
//  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;LOSS OF USE, DATA,
//  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
//  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
//  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
//  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//
//////////////////////////////////////////////////////////////////////////////////////

class Main extends eui.UILayer {


private imgBG :egret.Sprite;
    protected createChildren(): void {
        super.createChildren();

        egret.lifecycle.addLifecycleListener((context) => {
            // custom lifecycle plugin
        })

        egret.lifecycle.onPause = () => {
            egret.ticker.pause();
        }

        egret.lifecycle.onResume = () => {
            egret.ticker.resume();
        }

        //inject the custom material parser
        //注入自定义的素材解析器
        let assetAdapter = new AssetAdapter();
        egret.registerImplementation("eui.IAssetAdapter", assetAdapter);
        egret.registerImplementation("eui.IThemeAdapter", new ThemeAdapter());

        //游戏自定义容器添加到舞台上
        this.addChild(GameLayerManager.gameLayer());

        this.runGame().catch(e => {
            console.log(e);
        })

    }

    private async runGame() {
        await this.loadResource()
        this.createGameScene();
        const result = await RES.getResAsync("description_json")
        this.startAnimation(result);
        await platform.login();
        const userInfo = await platform.getUserInfo();
        // console.log(userInfo);

    }

    private async loadResource() {
        try {
            const loadingView = new LoadingUI();
            GameLayerManager.gameLayer().addChild(loadingView);
            await RES.loadConfig("resource/default.res.json", "resource/");
            //await RES.loadConfig("resource/resource.json", "resource/");
            await this.loadTheme();
           await RES.loadGroup("loading",1);
            
            await RES.loadGroup("preload", 0, loadingView);
            GameLayerManager.gameLayer().removeChild(loadingView);
        }
        catch (e) {
            console.error(e);
        }
    }

    private loadTheme() {
        return new Promise((resolve, reject) => {
            // load skin theme configuration file, you can manually modify the file. And replace the default skin.
            //加载皮肤主题配置文件,可以手动修改这个文件。替换默认皮肤。
            let theme = new eui.Theme("resource/default.thm.json", this.stage);
            theme.addEventListener(eui.UIEvent.COMPLETE, () => {
                resolve();
            }, this);

        })
    }

    private textfield: egret.TextField;
    private nameLabel: egret.TextField;
    /**
     * 创建场景界面
     * Create scene interface
     */
    protected createGameScene(): void {

        this.imgBG = new egret.Sprite;
        let sky = this.createBitmapByName("PreLoadingBg_jpg");
        let bg2 = this.createBitmapByName("bg2_png");
        this.imgBG.addChild(sky);
        this.imgBG.addChild(bg2);
        let stageW = this.stage.stageWidth;
        let stageH = this.stage.stageHeight;
        sky.width = stageW;
        sky.height = stageH;

        let topMask = new egret.Shape();
        topMask.graphics.beginFill(0x000000, 0.5);
        topMask.graphics.drawRect(0, 0, stageW, 172);
        topMask.graphics.endFill();
        topMask.y = 33;
        this.imgBG.addChild(topMask);

        let icon: egret.Bitmap = this.createBitmapByName("mainBtn_png");
        this.imgBG.addChild(icon);
        icon.x = 26;
        icon.y = 33;

        let line = new egret.Shape();
        line.graphics.lineStyle(2, 0xffffff);
        line.graphics.moveTo(0, 0);
        line.graphics.lineTo(0, 117);
        line.graphics.endFill();
        line.x = 172;
        line.y = 61;
        this.imgBG.addChild(line);


        let colorLabel = new egret.TextField();
        colorLabel.textColor = 0xffffff;
        colorLabel.width = stageW - 172;
        colorLabel.textAlign = "center";
        colorLabel.text = "Hello Egret";
        colorLabel.size = 24;
        colorLabel.x = 172;
        colorLabel.y = 80;
        this.imgBG.addChild(colorLabel);

        let textfield = new egret.TextField();
        this.imgBG.addChild(textfield);
        textfield.alpha = 0;
        textfield.border = true;
        textfield.width = stageW - 172;
        textfield.textAlign = egret.HorizontalAlign.CENTER;
        textfield.size = 24;
        textfield.textColor = 0xffffff;
        textfield.x = 172;
        textfield.y = 135;
        // textfield.touchEnabled = true;
        this.textfield = textfield;

        

        this.nameLabel = new egret.TextField();
        this.nameLabel.textColor = 0xffffff;
        this.nameLabel.width = stageW - 172;
        this.nameLabel.height = 60;
        this.nameLabel.border = true;
        //设置显示背景
        this.nameLabel.background = true;
        //设置背景颜色
        this.nameLabel.backgroundColor = 0xffffff;

        this.nameLabel.text = "用户名";
         //设置边框颜色
        this.nameLabel.borderColor = 0x00ff00;
        //设置字体
        this.nameLabel.fontFamily = "Arial";
        //设置文本颜色
        this.nameLabel.textColor = 0xFF0000;
        //设置字号
        this.nameLabel.size = 30;
        this.nameLabel.x = 172;
        this.nameLabel.y = 880;
        this.nameLabel.multiline = false;
        // nameLabel.lineSpacing = 5;
        this.nameLabel.verticalAlign = egret.VerticalAlign.MIDDLE;
        this.nameLabel.type = egret.TextFieldType.INPUT;

        this.imgBG.addChild(this.nameLabel);




        let button:EButton  = new EButton(this,"enter_h_png",this.onButtonClick,"",30,2,null);
        button.x = stageW/2-button.width/2;
        button.y = stageH/4*3;
        this.imgBG.addChild(button);


        let button1:EButton  = new EButton(this,"enter_h_png",this.onButton1Click,"",30,2,null);
        button1.x = stageW/2-button.width/2;
        button1.y = stageH/4*3+100;
        this.imgBG.addChild(button1);

        GameLayerManager.gameLayer().mainLayer.addChild(this.imgBG);

        // EXML.load("resource/eui_skins/MainSkin.exml",this.onLoaded,this);
    }

    private onLoaded(clazz:any):void{
        var group:eui.Group = new clazz();
        this.imgBG.addChild(group);
    }
    /**
     * 根据name关键字创建一个Bitmap对象。name属性请参考resources/resource.json配置文件的内容。
     * Create a Bitmap object according to name keyword.As for the property of name please refer to the configuration file of resources/resource.json.
     */
    private createBitmapByName(name: string): egret.Bitmap {
        let result = new egret.Bitmap();
        let texture: egret.Texture = RES.getRes(name);
        result.texture = texture;
        return result;
    }
    /**
     * 描述文件加载成功，开始播放动画
     * Description file loading is successful, start to play the animation
     */
    private startAnimation(result: Array<any>): void {
        let parser = new egret.HtmlTextParser();

        let textflowArr = result.map(text => parser.parse(text));
        let textfield = this.textfield;
        let count = -1;
        let change = () => {
            count++;
            if (count >= textflowArr.length) {
                count = 0;
            }
            let textFlow = textflowArr[count];

            // 切换描述内容
            // Switch to described content
            textfield.textFlow = textFlow;
            let tw = egret.Tween.get(textfield);
            tw.to({ "alpha": 1 }, 200);
            tw.wait(2000);
            tw.to({ "alpha": 0 }, 200);
            tw.call(change, this);

        };

        change();

    }

    /**
     * 点击按钮
     * Click the button
     */
    private onButtonClick(e: egret.TouchEvent) {

        game.AppFacade.getInstance().startUp(GameLayerManager.gameLayer());
        let panel = new eui.Panel();
        panel.title = "Title";
        panel.horizontalCenter = 0;
        panel.verticalCenter = 0;
        this.addChild(panel);
        SocketManager.connectServer("127.0.0.1",8888);

        

        
        // game.AppFacade.getInstance().sendNotification(SceneNotify.OPEN_HOME);
        // game.AppFacade.getInstance().sendNotification(MainNotify.OPEN_MAIN);
        // GameLayerManager.gameLayer().removeChild(this.imgBG);
       
    }

     private onButton1Click(e: egret.TouchEvent) {
        
         if(this.nameLabel.text != "")
         {
            UserInfoRequest.sendUserInfo(this.nameLabel.text,"123456");
            GameLayerManager.gameLayer().mainLayer.removeChild(this.imgBG);
            
         }
         else
         {
             console.log("用户名不能为空！！");
         }
    }
}
