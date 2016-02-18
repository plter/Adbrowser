package com.plter.adbrowser.ext;

/**
 * Created by plter on 2/18/16.
 */
public class File {

    private String cmdLine;
    private boolean directory = false;
    private boolean link = false;
    private boolean file = false;
    private String permission = null, user = null, group = null, date = null, time = null;
    private long size = 0;
    private int currentIndex = 0;
    private char ch;
    private StringBuilder currentString = new StringBuilder();
    private String name = null;


    public File(String cmdLine) {
        this.cmdLine = cmdLine;

        if (cmdLine.length()<=0){
            return;
        }

        switch (cmdLine.charAt(0)) {
            case 'd':
                directory = true;
                break;
            case '-':
                file = true;
                break;
            case 'l':
                link = true;
                break;
        }

        permission = read();
        user = read();
        group = read();
        if (isFile()) {
            size = Long.parseLong(read());
        }
        date = read();
        time = read();

        String lastStatement = cmdLine.substring(currentIndex).trim();
        name = lastStatement.split("->")[0];
    }


    public boolean isDirectory() {
        return directory;
    }

    public boolean isLink() {
        return link;
    }

    public boolean isFile() {
        return file;
    }

    public String getPermission() {
        return permission;
    }

    public String getUser() {
        return user;
    }

    public String getGroup() {
        return group;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public long getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    private String read() {
        String str = null;
        boolean foundStart = false;

        for (; currentIndex < cmdLine.length(); currentIndex++) {
            ch = cmdLine.charAt(currentIndex);

            if (ch != ' ') {
                currentString.append(ch);

                if (!foundStart) {
                    foundStart = true;
                }
            } else {
                if (foundStart) {
                    str = currentString.toString();
                    currentString.delete(0, currentString.length());
                    break;
                }
            }
        }
        if (foundStart && str == null) {
            str = currentString.toString();
        }
        return str;
    }
}
