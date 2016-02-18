package com.plter.adbrowser.ext;

/**
 * Created by plter on 2/18/16.
 */
public class StringOutput implements IOutputable {


    private StringBuilder content;

    public StringOutput() {
        content = new StringBuilder();
    }

    @Override
    public void appendText(String txt) {
        content.append(txt);
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
