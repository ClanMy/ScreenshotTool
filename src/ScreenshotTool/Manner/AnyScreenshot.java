package ScreenshotTool.Manner;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

public class AnyScreenshot {

    public void anyScreenshot(Stage stage , Stage stagePanel){

        //提示弹窗
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setHeaderText("待开发");

        Optional<ButtonType> buttonType = alert.showAndWait();

        if(buttonType.isPresent() && buttonType.get() == ButtonType.OK){
            stagePanel.close();
            stage.show();
        }


    }


}
