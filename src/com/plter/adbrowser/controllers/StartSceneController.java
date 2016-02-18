package com.plter.adbrowser.controllers;

import com.plter.adbrowser.conponents.FilesListView;
import com.plter.adbrowser.conponents.FilesListViewCellData;
import com.plter.adbrowser.ext.Dialogs;
import com.plter.adbrowser.ext.IOutputable;
import com.plter.adbrowser.ext.StringOutput;
import com.plter.adbrowser.ext.TextAreaOutput;
import com.plter.adbrowser.prefs.Preferences;
import com.plter.adbrowser.views.ViewLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class StartSceneController implements Initializable {


    public TextField tfAdbPath;
    public VBox rootView;
    public TextArea taOut;
    public FilesListView lvFiles;
    public Button btnStartBrowse;
    public Label lbCurrentDirPath;
    private String currentDirPath = "/";
    private File adbFile = null;
    private TextAreaOutput textAreaOutput;

    public static Scene createScene() {
        return new Scene(ViewLoader.loadView("StartScene.fxml").getView(), 400, 600);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        textAreaOutput = new TextAreaOutput(taOut);

        String path = Preferences.getInstance().readAdbPath();
        if (path != null) {
            adbFile = new File(path);
            tfAdbPath.setText(path);
        }

        addListeners();
    }

    private void addListeners() {
        lvFiles.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    FilesListViewCellData item = lvFiles.getSelectionModel().getSelectedItem();

                    if (item.getFile().isDirectory()) {
                        currentDirPath += item.getFile().getName().replaceAll(" ", "\\ ") + "/";
                        checkToShowCurrentDirContent();
                    }
                }
            }
        });
    }

    public void btnStartBrowseClickedHandler(ActionEvent actionEvent) {
        checkToShowCurrentDirContent();
    }

    private void checkToShowCurrentDirContent() {
        if (adbFile != null) {
            Process process = null;
            try {
                Runtime runtime = Runtime.getRuntime();
                process = runtime.exec(new String[]{adbFile.getAbsolutePath(), "shell", "ls", "-l", currentDirPath});

                if (process.waitFor() == 0) {
                    StringOutput output = new StringOutput();

                    InputStream in = process.getInputStream();
                    readInputStreamTo(in, output);
                    in.close();

                    String[] lines = output.toString().split("\n");
                    if (lines.length > 0) {
                        lbCurrentDirPath.setText(currentDirPath);
                        lvFiles.getItems().clear();

                        for (String line : lines) {
                            lvFiles.getItems().add(new FilesListViewCellData(new com.plter.adbrowser.ext.File(line)));
                        }
                    }

                    hideBtnStartBrowse();
                } else {
                    Dialogs.showMessageDialog("尚未连接设备", "提示");
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();

                Dialogs.showMessageDialog("执行命令失败", "提示");
            }
        }

        currentDirPath = lbCurrentDirPath.getText();
    }

    public void readInputStreamTo(InputStream in, IOutputable outputable) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = br.readLine()) != null) {
            outputable.appendText(line + "\n");
        }
    }

    public void btnBrowseForAdbClickedHandler(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("请选择adb文件的路径");
        File result = fc.showOpenDialog(rootView.getScene().getWindow());
        if (result != null) {
            tfAdbPath.setText(result.getAbsolutePath());
            adbFile = result;

            Preferences.getInstance().saveAdbPath(adbFile.getPath());
        }
    }


    public void btnClearOutputClickedHandler(ActionEvent actionEvent) {
        taOut.clear();
    }

    private void showBtnStartBrowse() {
        btnStartBrowse.setVisible(true);
        btnStartBrowse.setManaged(true);
    }

    private void hideBtnStartBrowse() {
        btnStartBrowse.setVisible(false);
        btnStartBrowse.setManaged(false);
    }

    public void btnToParentClickedHandler(ActionEvent actionEvent) {
        if (!currentDirPath.equals("/")) {
            currentDirPath = currentDirPath.substring(0, currentDirPath.lastIndexOf('/'));
            currentDirPath = currentDirPath.substring(0, currentDirPath.lastIndexOf('/')) + "/";

            checkToShowCurrentDirContent();
        }
    }
}
