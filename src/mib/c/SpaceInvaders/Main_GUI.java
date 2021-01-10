package mib.c.SpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main_GUI extends JFrame {
    public Character character = new Character();
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

    public void openMenuGUI() {
        this.getContentPane().removeAll();
        this.getContentPane().add(new Menu_GUI(this));
        this.setVisible(true);
    }

    public void openHighScoreGUI() {
        this.getContentPane().removeAll();
        this.getContentPane().add(new Highscore_GUI(this));
        this.setVisible(true);
    }

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
