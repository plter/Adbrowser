package com.plter.adbrowser;

import com.plter.adbrowser.controllers.StartSceneController;
import com.plter.adbrowser.ext.File;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Adbrowser");
        primaryStage.setScene(StartSceneController.createScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
