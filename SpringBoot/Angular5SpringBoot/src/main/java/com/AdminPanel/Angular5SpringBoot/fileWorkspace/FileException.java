package com.AdminPanel.Angular5SpringBoot.fileWorkspace;

public class FileException extends Exception {
    int number;

    public int getNum() {
        return number;
    }

    public FileException(String message, int num) {
       super(message);
       number = num;
    }
}
