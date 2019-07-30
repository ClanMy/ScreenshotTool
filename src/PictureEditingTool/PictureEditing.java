package PictureEditingTool;

import ScreenshotTool.ScreenshotTool;
import Utensil.ScreenInformation;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class PictureEditing {

    //图片编辑窗口
    private Stage stage = new Stage();
    //截图编辑界面
    public void shotEditing(Image image) {

        //获取电脑屏幕宽高
        ScreenInformation screenInformation = new ScreenInformation();

        //获取传入图片的信息
        double width = image.getWidth();
        double height = image.getHeight();
        String imageasd = "     宽高：" + (int) width + " × " + (int) height + " 像素";

        //布局类
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #dedede");

        //顶部操作栏
        BorderPane top_border = new BorderPane();
        top_border.setStyle("-fx-background-color: #ffffff");

        //左边按钮
        HBox top_LefthBox = new HBox();
        top_LefthBox.setAlignment(Pos.BASELINE_LEFT);
        top_LefthBox.setPadding(new Insets(7, 10, 7, 10));
        //新建截图
        Button newScreenshot = new Button("新建截图");
        newScreenshot.setId("saveToLocal");

        top_LefthBox.getChildren().addAll(newScreenshot);

        //保存提示信息
        Label promptMessage = new Label();
        promptMessage.setStyle("-fx-text-fill: #232323;" +
                "-fx-font-size: 20;" +
                "-fx-padding: 0 100 0 0");

        //右边按钮
        HBox top_RighthBox = new HBox();
        top_RighthBox.setAlignment(Pos.BASELINE_RIGHT);
        top_RighthBox.setPadding(new Insets(7, 10, 7, 10));
        //保存按钮
        Button saveToLocal = new Button("保存到本地");
        saveToLocal.setId("saveToLocal");

        top_RighthBox.getChildren().addAll(saveToLocal);

        top_border.setLeft(top_LefthBox);
        top_border.setCenter(promptMessage);
        top_border.setRight(top_RighthBox);

        //主图片HBox
        HBox image_hbox = new HBox();
        image_hbox.setAlignment(Pos.CENTER);
        //主图图片
        ImageView imageView = new ImageView(image);

        //判断图片宽高自适应
        if (width>=height) {
            //设置宽
            imageView.setFitWidth(screenInformation.adaptation_W(700));
            //设置高随宽自动
            imageView.setPreserveRatio(true);
        }else {
            //设置高
            imageView.setFitHeight(screenInformation.adaptation_H(500));
            //设置宽随高自动
            imageView.setPreserveRatio(true);
        }


        image_hbox.getChildren().addAll(imageView);
        //最大的布局BorderPane
        borderPane.setTop(top_border);
        borderPane.setCenter(image_hbox);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().addAll("PictureEditingTool/CSS/PictureEditingTool.css");
        stage.setScene(scene);
        stage.setTitle("图片编辑" + imageasd);
        stage.getIcons().add(new Image("PictureEditingTool/Images/PictureEditingIcon.png"));

        stage.setWidth(screenInformation.adaptation_W(1300));
        stage.setHeight(screenInformation.adaptation_H(800));
        stage.show();


        //新建截图按钮
        newScreenshot.setOnAction(event -> {
            //关闭图片编辑
            stage.close();
            //调用截图
            new ScreenshotTool().screenshotTool();

        });

        //单击事件保存到本地
        saveToLocal.setOnAction(event -> {

            //根据系统添加不同的斜杠
            String separator = File.separator;

            //当前用户桌面路径  //这里要加一个斜杠,不然找不到不到路径
            String absolutePath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + separator;
            //日期个格式
            String dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-S").format(new Date());
            //生成UUID随机码
            //名称过于冗长  暂时去掉uuid命名
//            String uuid = UUID.randomUUID().toString();
            //添加后缀
            //String suffix = ".png";

            //文件给下面的保存到路径提供信息
            File pictureFile = new File(absolutePath + dateFormat + ".png");
            //先转换接收回过来的图片
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

            try {
                //存入本地
                boolean image_boolean = ImageIO.write(bufferedImage, "png", pictureFile);
                //当image_boolean为ture时输出的提示信息
                promptMessage.setText(image_boolean ? "保存成功" : "保存失败");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }


}
