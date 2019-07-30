package ScreenshotTool.Manner;

import ScreenshotTool.Manner.Screenshot.Screenshot;
import Utensil.ScreenInformation;


public class InterceptFullScreen {

    //全屏截图
    public void interceptFullScreen(){

        //获取屏幕宽高
        ScreenInformation screenInformation = new ScreenInformation();

        //调用截图核心
        new Screenshot().getScreenImg(0, 0,(int) screenInformation.screen_width(),(int) screenInformation.screen_height());


    }

}
