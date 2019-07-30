package ScreenshotTool;

import ScreenshotTool.Manner.AnyScreenshot;
import ScreenshotTool.Manner.InterceptFullScreen;
import ScreenshotTool.Manner.RectangularScreenshot;
import Utensil.ScreenInformation;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ScreenshotTool {
    //新建一个窗口
    private Stage stage;
    //新建一个截图面板窗口
    private Stage stagePanel;

    //截图方法
    public void screenshotTool() {

        //获取电脑屏幕宽高
        ScreenInformation screenInformation = new ScreenInformation();

        //新建一个窗口
        stage = new Stage();
        HBox hBox = new HBox();

        hBox.setPrefWidth(screenInformation.adaptation_W(275));
        hBox.setPrefHeight(screenInformation.adaptation_H(60));

        hBox.setPadding(new Insets(screenInformation.adaptation_H(15),screenInformation.adaptation_W(5),0,screenInformation.adaptation_W(5)));

        Button rectangularScreenshot = new Button("矩形截图");
        Button interceptFullScreen = new Button("截取全屏");
        Button anyScreenshot = new Button("任意截图");
        Button closeScreenshot = new Button("×");
        //去除最后一个按钮的边框
        closeScreenshot.setId("closeScreenshot");
        //优化字体大小
        rectangularScreenshot.setFont(Font.font(screenInformation.adaptation_H(14)));
        interceptFullScreen.setFont(Font.font(screenInformation.adaptation_H(14)));
        anyScreenshot.setFont(Font.font(screenInformation.adaptation_H(14)));
        closeScreenshot.setFont(Font.font(screenInformation.adaptation_H(14)));

        //将按钮放入水平布局中
        hBox.getChildren().addAll(rectangularScreenshot, interceptFullScreen, anyScreenshot, closeScreenshot);
        Scene scene = new Scene(hBox);
        scene.getStylesheets().addAll("ScreenshotTool/CSS/ScreenshotTool.css");
        stage.setScene(scene);
        stage.getIcons().add(new Image("ScreenshotTool/Images/ScreenshotIcon.png"));
        stage.setTitle("截图");

        //优化计算
        double x = (screenInformation.screen_width() - hBox.getPrefWidth()) / 2;

        stage.setX(x);
        stage.setY(0);
        //去除标题栏
        stage.initStyle(StageStyle.TRANSPARENT);
        //窗口不可拉伸
        stage.setResizable(false);
        //窗口置顶
        stage.setAlwaysOnTop(true);
        stage.show();

        //矩形截图
        rectangularScreenshot.setOnAction(event -> {
            stage.close();
            //调用截图面板 0 为矩形截图
            screenshotPanel(0);
        });

        //矩形截图快捷键  绑定事件
        KeyCombination keyCombination = KeyCombination.valueOf("ctrl+shift+alt+p");
        scene.addMnemonic(new Mnemonic(rectangularScreenshot, keyCombination));

        //截取全屏
        interceptFullScreen.setOnAction(event -> {
            stage.close();
            //直接调用全屏截图
            new InterceptFullScreen().interceptFullScreen();
        });

        //任意截图
        anyScreenshot.setOnAction(event -> {

            stage.close();
            //调用截图面板 1 为矩形任意
            screenshotPanel(1);
        });

        //关闭窗口
        closeScreenshot.setOnAction(event -> stage.close());

    }

    //创建，一个截图面板
    private void screenshotPanel(int callJudgment) {

        //一个截图面板
        stagePanel = new Stage();

        AnchorPane anchorPane = new AnchorPane();
        //设置一个半透明
        anchorPane.setStyle("-fx-background-color: rgba(17,17,17,0.19)");

        Scene scene = new Scene(anchorPane);
        scene.setFill(Paint.valueOf("#ffffff00"));
        stagePanel.setScene(scene);
        //去除全屏幕时的文字提示
        stagePanel.setFullScreenExitHint("");
        //去除窗口样式
        stagePanel.initStyle(StageStyle.TRANSPARENT);
        //设置全屏（窗口上必须要有场景图和根节点）
        stagePanel.setFullScreen(true);
        stagePanel.show();

        //监听事件  按esc按键关闭窗口
        scene.setOnKeyPressed(event -> {
            //设置快捷键
            if (event.getCode() == KeyCode.ESCAPE) {
                //关闭截屏窗口
                stagePanel.close();
                //打开截图工具
                stage.show();
            }
        });

        //按鼠标右键关闭窗口
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            String name = event.getButton().name();

            if (name.equals(MouseButton.SECONDARY.name())) {
                //关闭截屏窗口
                stagePanel.close();
                //打开截图工具
                stage.show();
            }

        });

        //判断调用那个截图方法
        switch (callJudgment) {
            case 0:
                //调用矩形截图方法
                new RectangularScreenshot().rectangularScreenshot(anchorPane, stage, stagePanel);
                break;
            case 1:
                //调用任意截图方法
                new AnyScreenshot().anyScreenshot(stage, stagePanel);
                break;
        }


    }

}
