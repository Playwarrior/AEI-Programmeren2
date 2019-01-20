package com.avans.UserInterface;

import javax.swing.*;
import java.awt.*;


public class UILogic implements Runnable{

    private JFrame jFrame;

    public UILogic(){
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Netflix Statistix");
        frame.setPreferredSize(new Dimension(600, 300));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        GridLayout layout = new GridLayout(5, 3);
        container.setLayout(layout);
        container.setBackground(Color.darkGray);

        JLabel homeLogo = new JLabel("Netflix\n" +
                "Statistix", SwingConstants.CENTER);
        homeLogo.setFont( new Font("Helvetica Neue", Font.ITALIC, 20));
        homeLogo.setForeground(Color.red);

        JLabel credits = new JLabel("Door: Robin Egberts, Bryan Kho, Jur Nagtzaam", SwingConstants.CENTER);
        credits.setFont( new Font("Helvetica Neue",Font.ITALIC, 12));
        credits.setForeground(Color.white);


        container.add(homeLogo);
        container.add(credits);
    }

    public JFrame getFrame() {
        return jFrame;
    }

}
