package com.avans.handlers.logger;

/*
    Created By Robin Egberts On 12/23/2018
    Copyrighted By OrbitMines ©2018
*/

import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private String fileName;
    private boolean append;

    public Logger(String fileName, boolean append) {
        this.fileName = fileName;
        this.append = append;

        this.clear();
    }

    public void log(String log) {
        try {
            FileWriter writer = new FileWriter(fileName, append);
            writer.write(log + "\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void clear() {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
