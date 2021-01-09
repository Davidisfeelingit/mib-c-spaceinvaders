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
        openMenuGUI();

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void openMenuGUI() throws FileNotFoundException {
        //this.setEnabled(false);
        this.getContentPane().removeAll();
        this.getContentPane().add(new Menu_GUI(this));
        this.setVisible(true);
    }

    public void openHighScoreGUI() {
        //this.setEnabled(false);
        this.getContentPane().removeAll();
        this.getContentPane().add(new Highscore_GUI(this));
        this.setVisible(true);
    }

    public static void main(String args[]) {
        try {
            new Main_GUI();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
