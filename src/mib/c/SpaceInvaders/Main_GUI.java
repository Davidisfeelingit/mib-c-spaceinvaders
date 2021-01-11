package mib.c.SpaceInvaders;

import javax.swing.*;
import java.awt.*;

public class Main_GUI extends JFrame {

    /**
     * JFrame thats holds all the other GUI´s
     */
    public Main_GUI() {
        setSize(800, 600);
        Container mainPanel = getContentPane();
        setLocationRelativeTo(null);
        mainPanel.setLayout(null);
        mainPanel.setVisible(true);

        openMenuGUI();

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        new Main_GUI();
    }

    /**
     * Load the MenuGUI
     */
    public void openMenuGUI() {
        this.getContentPane().removeAll();
        this.getContentPane().add(new Menu_GUI(this));
        this.setVisible(true);
    }

    /**
     * Load the HighscoreGUI
     */
    public void openHighScoreGUI() {
        this.getContentPane().removeAll();
        this.getContentPane().add(new Highscore_GUI(this));
        this.setVisible(true);
    }

    /**
     * Start a new game.
     */
    public void openGameBoard() {
        this.setVisible(false);
        EventQueue.invokeLater(() -> {
            var ex = new SpaceInvaders(this);
            ex.setVisible(true);
        });
    }
}
