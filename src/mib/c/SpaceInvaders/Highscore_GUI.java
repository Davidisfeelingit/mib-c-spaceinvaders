package mib.c.SpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Highscore_GUI extends JPanel {

    private Main_GUI main_gui;

    public Highscore_GUI(Main_GUI main_GUI) {
        this.main_gui = main_GUI;

        setSize(800, 600);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        // Title
        JLabel titleLabel = new JLabel("HiGh ScOrE");
        titleLabel.setFont(new Font("Chiller", Font.BOLD, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        JPanel contentPanel = new JPanel(null);

        String[] columnNames = {"Name", "Score"};
        JTable highscoreT = new JTable(Highscore.readHighscore(), columnNames);
        highscoreT.setEnabled(false);
        highscoreT.setLayout(new FlowLayout());
        highscoreT.setSize(300, 300);
        highscoreT.setBounds(300, 50, 200, 160);
        highscoreT.setLayout(new FlowLayout());
        highscoreT.setPreferredScrollableViewportSize(new Dimension(200, 100));
        highscoreT.setFillsViewportHeight(true);
        highscoreT.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(highscoreT);

        JButton leaveHighB = new JButton(new ImageIcon(("src/images/exit.png")));
        leaveHighB.setOpaque(false);
        leaveHighB.setContentAreaFilled(true);
        leaveHighB.setBorderPainted(true);
        leaveHighB.setBounds(340, 250, 120, 31);
        leaveHighB.setPreferredSize(new Dimension(120, 31));
        leaveHighB.addActionListener(e -> {
                main_gui.openMenuGUI();
        });
        contentPanel.add(leaveHighB);

        add(contentPanel);
        setVisible(true);
    }
}