package Mian;

import ScreenshotTool.ScreenshotTool;
import javafx.application.Application;
import javafx.stage.Stage;

public class ScreenshotMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //调用截图
        new ScreenshotTool().screenshotTool();



    }
}
