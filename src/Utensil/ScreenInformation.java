package Utensil;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ScreenInformation {

    private Screen primary = Screen.getPrimary();
    private Rectangle2D bounds = primary.getBounds();

    //必须在stage窗口调用
    //获取屏幕的宽
    public double screen_width() {
        return bounds.getWidth();
    }

    //获取屏幕的高
    public double screen_height() {
        return bounds.getHeight();
    }

    //屏幕按宽自适应
    public double adaptation_W(int adaptation) {

        //按照1920计算比率
        double ratio = adaptation / 1920.0;

        //计算宽度并保留两位小数
        double i = ((int) (screen_width() * ratio * 100)) / 100;

        //四舍五入并返回
        return Math.round(i);
    }

    //屏幕按宽高自适应
    public double adaptation_H(int adaptation) {

        //按照1920计算比率
        double ratio = adaptation / 1080.0;

        //计算宽度并保留两位小数
        double i = ((int) (screen_height() * ratio * 100)) / 100;

        //四舍五入并返回
        return Math.round(i);
    }

}
