package com.avans.handlers.logger;

/*
    Created By Robin Egberts On 12/23/2018
    Copyrighted By OrbitMines ©2018
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private FileWriter writer;

    public Logger(String fileName) {
        try {
            this.writer = new FileWriter(new File(getClass().getResourceAsStream(fileName).toString()));
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void log(String log){
        try {
            writer.write(log);
            writer.flush();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void close(){
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
