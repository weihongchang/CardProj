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
var __reflect = (this && this.__reflect) || function (p, c, t) {
    p.__class__ = c, t ? t.push(c) : t = [c], p.__types__ = p.__types__ ? t.concat(p.__types__) : t;
};
var __extends = this && this.__extends || function __extends(t, e) { 
 function r() { 
 this.constructor = t;
}
for (var i in e) e.hasOwnProperty(i) && (t[i] = e[i]);
r.prototype = e.prototype, t.prototype = new r();
};
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = y[op[0] & 2 ? "return" : op[0] ? "throw" : "next"]) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [0, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var Main = (function (_super) {
    __extends(Main, _super);
    function Main() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    Main.prototype.createChildren = function () {
        _super.prototype.createChildren.call(this);
        egret.lifecycle.addLifecycleListener(function (context) {
            // custom lifecycle plugin
        });
        egret.lifecycle.onPause = function () {
            egret.ticker.pause();
        };
        egret.lifecycle.onResume = function () {
            egret.ticker.resume();
        };
        //inject the custom material parser
        //注入自定义的素材解析器
        var assetAdapter = new AssetAdapter();
        egret.registerImplementation("eui.IAssetAdapter", assetAdapter);
        egret.registerImplementation("eui.IThemeAdapter", new ThemeAdapter());
        //游戏自定义容器添加到舞台上
        this.addChild(GameLayerManager.gameLayer());
        this.runGame().catch(function (e) {
            console.log(e);
        });
    };
    Main.prototype.runGame = function () {
        return __awaiter(this, void 0, void 0, function () {
            var _a, _b, userInfo;
            return __generator(this, function (_c) {
                switch (_c.label) {
                    case 0: return [4 /*yield*/, this.loadResource()];
                    case 1:
                        _c.sent();
                        this.createGameScene();
                        // const result = await RES.getResAsync("description_json")
                        // this.startAnimation(result);
                        _a = GlobalData;
                        return [4 /*yield*/, RES.getResAsync("Hero_json")];
                    case 2:
                        // const result = await RES.getResAsync("description_json")
                        // this.startAnimation(result);
                        _a.heroTemplate = _c.sent();
                        _b = GlobalData;
                        return [4 /*yield*/, RES.getResAsync("Item_json")
                            // JSON.parse(GlobalData.heroTemplate)
                            // this.starHeroFile(result);
                        ];
                    case 3:
                        _b.itemTemplate = _c.sent();
                        // JSON.parse(GlobalData.heroTemplate)
                        // this.starHeroFile(result);
                        return [4 /*yield*/, platform.login()];
                    case 4:
                        // JSON.parse(GlobalData.heroTemplate)
                        // this.starHeroFile(result);
                        _c.sent();
                        return [4 /*yield*/, platform.getUserInfo()];
                    case 5:
                        userInfo = _c.sent();
                        return [2 /*return*/];
                }
            });
        });
    };
    Main.prototype.loadResource = function () {
        return __awaiter(this, void 0, void 0, function () {
            var loadingView, e_1;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        _a.trys.push([0, 5, , 6]);
                        loadingView = new LoadingUI();
                        GameLayerManager.gameLayer().addChild(loadingView);
                        return [4 /*yield*/, RES.loadConfig("resource/default.res.json", "resource/")];
                    case 1:
                        _a.sent();
                        //await RES.loadConfig("resource/resource.json", "resource/");
                        return [4 /*yield*/, this.loadTheme()];
                    case 2:
                        //await RES.loadConfig("resource/resource.json", "resource/");
                        _a.sent();
                        return [4 /*yield*/, RES.loadGroup("loading", 1)];
                    case 3:
                        _a.sent();
                        return [4 /*yield*/, RES.loadGroup("preload", 0, loadingView)];
                    case 4:
                        _a.sent();
                        GameLayerManager.gameLayer().removeChild(loadingView);
                        return [3 /*break*/, 6];
                    case 5:
                        e_1 = _a.sent();
                        console.error(e_1);
                        return [3 /*break*/, 6];
                    case 6: return [2 /*return*/];
                }
            });
        });
    };
    Main.prototype.loadTheme = function () {
        var _this = this;
        return new Promise(function (resolve, reject) {
            // load skin theme configuration file, you can manually modify the file. And replace the default skin.
            //加载皮肤主题配置文件,可以手动修改这个文件。替换默认皮肤。
            var theme = new eui.Theme("resource/default.thm.json", _this.stage);
            theme.addEventListener(eui.UIEvent.COMPLETE, function () {
                resolve();
            }, _this);
        });
    };
    /**
     * 创建场景界面
     * Create scene interface
     */
    Main.prototype.createGameScene = function () {
        game.DataManager.getInstance().InitTestData();
        this.imgBG = new egret.Sprite;
        var sky = this.createBitmapByName("PreLoadingBg_jpg");
        var bg2 = this.createBitmapByName("bg2_png");
        this.imgBG.addChild(sky);
        this.imgBG.addChild(bg2);
        var stageW = this.stage.stageWidth;
        var stageH = this.stage.stageHeight;
        sky.width = stageW;
        sky.height = stageH;
        var topMask = new egret.Shape();
        topMask.graphics.beginFill(0x000000, 0.5);
        topMask.graphics.drawRect(0, 0, stageW, 172);
        topMask.graphics.endFill();
        topMask.y = 33;
        this.imgBG.addChild(topMask);
        var icon = this.createBitmapByName("mainBtn_png");
        this.imgBG.addChild(icon);
        icon.x = 26;
        icon.y = 33;
        var line = new egret.Shape();
        line.graphics.lineStyle(2, 0xffffff);
        line.graphics.moveTo(0, 0);
        line.graphics.lineTo(0, 117);
        line.graphics.endFill();
        line.x = 172;
        line.y = 61;
        this.imgBG.addChild(line);
        var colorLabel = new egret.TextField();
        colorLabel.textColor = 0xffffff;
        colorLabel.width = stageW - 172;
        colorLabel.textAlign = "center";
        colorLabel.text = "Hello Egret";
        colorLabel.size = 24;
        colorLabel.x = 172;
        colorLabel.y = 80;
        this.imgBG.addChild(colorLabel);
        var textfield = new egret.TextField();
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
        var button = new EButton(this, "enter_h_png", this.onButtonClick, "", 30, 2, null);
        button.x = stageW / 2 - button.width / 2;
        button.y = stageH / 4 * 3;
        // this.imgBG.addChild(button);
        var button1 = new EButton(this, "enter_h_png", this.onButton1Click, "", 30, 2, null);
        button1.x = stageW / 2 - button.width / 2;
        button1.y = stageH / 4 * 3 + 100;
        this.imgBG.addChild(button1);
        GameLayerManager.gameLayer().mainLayer.addChild(this.imgBG);
        game.AppFacade.getInstance().startUp(GameLayerManager.gameLayer());
        // SocketManager.connectServer("127.0.0.1",8888);
        SocketManager.connectServer("192.168.0.111", 8888);
        // EXML.load("resource/eui_skins/MainSkin.exml",this.onLoaded,this);
    };
    Main.prototype.onLoaded = function (clazz) {
        var group = new clazz();
        this.imgBG.addChild(group);
    };
    /**
     * 根据name关键字创建一个Bitmap对象。name属性请参考resources/resource.json配置文件的内容。
     * Create a Bitmap object according to name keyword.As for the property of name please refer to the configuration file of resources/resource.json.
     */
    Main.prototype.createBitmapByName = function (name) {
        var result = new egret.Bitmap();
        var texture = RES.getRes(name);
        result.texture = texture;
        return result;
    };
    /**
     * 描述文件加载成功，开始播放动画
     * Description file loading is successful, start to play the animation
     */
    Main.prototype.startAnimation = function (result) {
        var _this = this;
        var parser = new egret.HtmlTextParser();
        var textflowArr = result.map(function (text) { return parser.parse(text); });
        var textfield = this.textfield;
        var count = -1;
        var change = function () {
            count++;
            if (count >= textflowArr.length) {
                count = 0;
            }
            var textFlow = textflowArr[count];
            // 切换描述内容
            // Switch to described content
            textfield.textFlow = textFlow;
            var tw = egret.Tween.get(textfield);
            tw.to({ "alpha": 1 }, 200);
            tw.wait(2000);
            tw.to({ "alpha": 0 }, 200);
            tw.call(change, _this);
        };
        change();
    };
    /**
     * 点击按钮
     * Click the button
     */
    Main.prototype.onButtonClick = function (e) {
        console.log(game.DataManager.getInstance().player.name);
        game.AppFacade.getInstance().startUp(GameLayerManager.gameLayer());
        // let panel = new eui.Panel();
        // panel.title = "Title";
        // panel.horizontalCenter = 0;
        // panel.verticalCenter = 0;
        // this.addChild(panel);
        // SocketManager.connectServer("127.0.0.1",8888);
        SocketManager.connectServer("192.168.0.111", 8888);
        // game.AppFacade.getInstance().sendNotification(SceneNotify.OPEN_HOME);
        // game.AppFacade.getInstance().sendNotification(MainNotify.OPEN_MAIN);
        // GameLayerManager.gameLayer().removeChild(this.imgBG);
    };
    Main.prototype.onButton1Click = function (e) {
        if (SocketManager.connState) {
            if (this.nameLabel.text != "") {
                UserInfoRequest.sendUserInfo(this.nameLabel.text, "123456");
                GameLayerManager.gameLayer().mainLayer.removeChild(this.imgBG);
            }
            else {
                EffectUtils.showTipsMid("用户名不能为空", true);
            }
        }
        else {
            EffectUtils.showTipsMid("网络连接异常！", true);
        }
    };
    return Main;
}(eui.UILayer));
__reflect(Main.prototype, "Main");
//# sourceMappingURL=Main.js.map