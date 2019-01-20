package com.avans.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class showTableListener implements ActionListener {

    private JLabel Column;
    private JLabel row;

    public showTableListener(JLabel column, JLabel row){
        this.Column = column;
        this.row = row;
    }

    @Override
    public void actionPerformed(ActionEvent ae){

    }

}
