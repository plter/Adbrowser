package com.plter.adbrowser.conponents;

import com.plter.adbrowser.ext.File;

/**
 * Created by plter on 2/18/16.
 */
public class FilesListViewCellData {

    private File file;

    public FilesListViewCellData(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        String prefix = "";
        if (getFile().isDirectory()){
            prefix = "[DIR]";
        }
        if (getFile().isLink()){
            prefix = "[LINK]";
        }
        if (getFile().isFile()){
            prefix = "[FILE]";
        }
        return prefix + getFile().getName();
    }
}
