package mib.c.SpaceInvaders;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

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
        playSound("src/music/space invader.wav");
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
    public void playSound(String filepath) {
        Path path = Path.of(filepath);
        File clipFile = new File (filepath);
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(clipFile);
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
