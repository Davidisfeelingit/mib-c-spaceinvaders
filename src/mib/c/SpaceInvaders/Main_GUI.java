package mib.c.SpaceInvaders;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Main_GUI extends JFrame {


    public Character character = new Character();

    /**
     * JFrame thats holds all the other GUI´s
     */
    public Main_GUI() {
        setSize(800, 600);
        Container mainPanel = getContentPane();
        setTitle("Space Invaders");
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

    public void openCharacterGUI() {
        this.getContentPane().removeAll();
        this.repaint();
        this.getContentPane().add(new Character_GUI(this));
        this.setVisible(true);
    }

}
