package com.plter.adbrowser.ext;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Created by plter on 2/18/16.
 */
public class Dialogs {


    public static void showMessageDialog(String body,String title){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setContentText(body);
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("关闭", ButtonBar.ButtonData.OK_DONE));
        dialog.show();
    }
}
