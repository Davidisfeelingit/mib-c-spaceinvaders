package mib.c.SpaceInvaders;
import org.json.JSONArray;
import org.json.JSONObject;;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Highscore_GUI extends JPanel {

    private Main_GUI main_gui;

    public Highscore_GUI(Main_GUI main_GUI){
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

        String[] columnNames = {"Name","Score"};
        JTable highscoreT = new JTable(readHighScore(), columnNames);
        highscoreT.setEnabled(false);
        highscoreT.setLayout(new FlowLayout());
        highscoreT.setSize(300, 300);
        highscoreT.setBounds(300, 50, 200, 160);
        highscoreT.setLayout(new FlowLayout());
        highscoreT.setPreferredScrollableViewportSize(new Dimension(200,100));
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
            try {
                main_gui.openMenuGUI();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        contentPanel.add(leaveHighB);

        add(contentPanel);
        setVisible(true);
    }

    private static String[][] readHighScore() {
        try {
            // Read json file.
            Path path = Path.of("highscore.json");
            String content = Files.readString(path, StandardCharsets.UTF_8);

            // Convert to json array.
            JSONObject object = new JSONObject(content);
            JSONArray highscore = object.getJSONArray("highscore");

            // Convert to 2d array.
            String[][] highscoreTable = new String[highscore.length()][2];
            for (int  i = 0; i < highscore.length(); i++) {
                String score = highscore.getString(i);
                String[] splits =  score.split(":");

                highscoreTable[i][0] = splits[0];
                highscoreTable[i][1] = splits[1];
            }

            return highscoreTable;
        } catch (Exception e) {
            System.out.print(e);
        }

        return null;
    }

    private void setHighscore(JTable table){
        try {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter("highscore.txt"));
            myWriter.write("");
            for(int i = 0; i <= table.getRowCount()-1; i++){

                myWriter.append(table.getValueAt(i, 0).toString());
                myWriter.append(table.getValueAt(i, 1).toString());
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}