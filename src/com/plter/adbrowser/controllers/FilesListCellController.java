package com.plter.adbrowser.controllers;

import com.plter.adbrowser.views.ViewLoader;
import javafx.scene.image.ImageView;

/**
 * Created by plter on 2/18/16.
 */
public class FilesListCellController {

    public ImageView ivIcon;

    public static ViewLoader.Result createLoadViewResult() {
        return ViewLoader.loadView("FilesListCellView.fxml");
    }
}
