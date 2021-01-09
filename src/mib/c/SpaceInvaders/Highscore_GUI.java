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

public class Highscore_GUI extends JFrame {

    private Highscore_GUI highscore_GUI;
    private JButton saveB,leaveHighB;
    JTable highscoreT;

    public static JFrame frame1 = new JFrame();

    public Highscore_GUI(){
        String[] columnNames = {"Name","Score"};
        highscoreT = new JTable(readHighScore(), columnNames);
        this.highscore_GUI = highscore_GUI;
        highscoreT.setEnabled(false);

        frame1.setSize(800,600);
        Container mainP = frame1.getContentPane();
        Container panel = frame1.getContentPane();
        Container panel1 = frame1.getContentPane();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.setBounds(250, 50, 200, 90);
        panel1.setLayout(null);

        //frame1.setLayout(new BoxLayout(mainP, BoxLayout.PAGE_AXIS));
        highscoreT.setLayout(new FlowLayout());
        highscoreT.setBounds(250, 50, 200, 160);
        highscoreT.setLayout(new FlowLayout());
        highscoreT.setPreferredScrollableViewportSize(new Dimension(200,100));
        highscoreT.setFillsViewportHeight(true);
        highscoreT.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(highscoreT);
        panel.add(highscoreT);
        mainP.setVisible(true);
        panel.setVisible(true);
        panel1.setVisible(true);

        leaveHighB = new JButton(new ImageIcon(("src/images/exit.png")));
        leaveHighB.setOpaque(false);
        leaveHighB.setContentAreaFilled(true);
        leaveHighB.setBorderPainted(true);
        leaveHighB.setBounds(450, 250, 120, 31);
        leaveHighB.setPreferredSize(new Dimension(120, 31));
        leaveHighB.addActionListener(e -> {
            try {
                new Menu_GUI();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        //mainP.add(leaveHighB);
        panel1.add(leaveHighB);

        saveB = new JButton(new ImageIcon(("src/images/save.png")));
        saveB.setOpaque(false);
        saveB.setContentAreaFilled(true);
        saveB.setBorderPainted(true);
        saveB.setBounds(124, 250, 120, 36);
        saveB.setPreferredSize(new Dimension(120, 36));
        saveB.setOpaque(false);
        //mainP.add(saveB);
        panel1.add(saveB);

        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
        setHighscore(highscoreT);
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
