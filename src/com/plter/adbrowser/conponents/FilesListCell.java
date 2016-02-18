package com.plter.adbrowser.conponents;

import com.plter.adbrowser.controllers.FilesListCellController;
import com.plter.adbrowser.res.Images;
import com.plter.adbrowser.views.ViewLoader;
import javafx.scene.control.ListCell;

/**
 * Created by plter on 2/18/16.
 */
public class FilesListCell extends ListCell<FilesListViewCellData> {

    private ViewLoader.Result result;
    private FilesListCellController controller;

    public FilesListCell() {
        result = FilesListCellController.createLoadViewResult();
        controller = result.getController();
    }


    @Override
    protected void updateItem(FilesListViewCellData item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            if (item.getFile().isDirectory()) {
                controller.ivIcon.setImage(Images.getImage("folder.png"));
            } else {
                controller.ivIcon.setImage(Images.getImage("file.png"));
            }

            setText(item.getFile().getName());
            setGraphic(result.getView());
        } else {
            setText(null);
            setGraphic(null);
        }
    }
}
