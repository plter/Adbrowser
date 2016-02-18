package com.plter.adbrowser.conponents;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Created by plter on 2/18/16.
 */
public class FilesListView extends ListView<FilesListViewCellData> {

    public FilesListView() {
        setCellFactory(new Callback<ListView<FilesListViewCellData>, ListCell<FilesListViewCellData>>() {
            @Override
            public ListCell<FilesListViewCellData> call(ListView<FilesListViewCellData> param) {
                return new FilesListCell();
            }
        });
    }
}
