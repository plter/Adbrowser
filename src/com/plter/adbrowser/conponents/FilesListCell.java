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

            switch (item.getFile().getFileType()){
                case DIRECTORY:
                    controller.ivIcon.setImage(Images.getImage("folder.png"));
                    break;
                case LINK:
                    controller.ivIcon.setImage(Images.getImage("link.png"));
                    break;
                default:
                    controller.ivIcon.setImage(Images.getImage("file.png"));
            }

            if (item.getFile().isLink()) {
                setText(item.getFile().getName() + " -> " + item.getFile().getLinkTarget());
            } else {
                setText(item.getFile().getName());
            }
            setGraphic(result.getView());
        } else {
            setText(null);
            setGraphic(null);
        }
    }
}
