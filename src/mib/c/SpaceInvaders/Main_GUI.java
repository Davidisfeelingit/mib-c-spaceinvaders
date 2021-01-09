package mib.c.SpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Main_GUI extends JFrame {

    public Main_GUI() throws FileNotFoundException {
        setSize(800, 600);
        Container mainPanel = getContentPane();
        setLocationRelativeTo(null);
        mainPanel.setLayout(null);
        mainPanel.setVisible(true);

        // Create Content Panels here
        Menu_GUI menu = new Menu_GUI();
        getContentPane().add(menu);

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String args[]) {
        try {
            new Main_GUI();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
