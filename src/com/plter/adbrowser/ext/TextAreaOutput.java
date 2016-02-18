package com.plter.adbrowser.ext;

import javafx.scene.control.TextArea;

/**
 * Created by plter on 2/18/16.
 */
public class TextAreaOutput implements IOutputable {


    private TextArea textArea;


    public TextAreaOutput(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void appendText(String txt) {
        textArea.appendText(txt);
    }
}
