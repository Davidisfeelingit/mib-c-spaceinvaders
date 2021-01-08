package mib.c.SpaceInvaders;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Highscore_GUI extends JFrame {

    private Menu_GUI menu_GUI;
    private Highscore_GUI highscore_GUI;
    private JButton saveB,leaveHighB;
    JTable highscoreT;

    public static JFrame frame1 = new JFrame();

    public Highscore_GUI(){
        String[] columnNames = {"Name","Score"};
        highscoreT = new JTable(getHighscore(), columnNames);
        this.highscore_GUI = highscore_GUI;
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
        //panel.add(scrollPane);
        panel.add(highscoreT);
        //mainP.add(scrollPane);
        //mainP.add(highscoreT);
        mainP.setVisible(true);
        panel.setVisible(true);
        panel1.setVisible(true);

        leaveHighB = new JButton("LeaveHigh");
        leaveHighB.setOpaque(false);
        leaveHighB.setContentAreaFilled(true);
        leaveHighB.setBorderPainted(true);
        leaveHighB.setBounds(450, 250, 120, 31);
        leaveHighB.setPreferredSize(new Dimension(120, 31));
        leaveHighB.addActionListener(e -> {
            Object[] options = {"Yes","No","Cancel"};
            int n = JOptionPane.showOptionDialog(frame1,
                    "Continue?",
                    "Would you like to continue?",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);
            if (n == JOptionPane.YES_OPTION) {
                System.out.println("Clicked Yes");
                System.exit(0);
            } else if (n == JOptionPane.NO_OPTION) {
                System.out.println("Clicked No");
                JOptionPane.getRootFrame().dispose();
            } else if (n == JOptionPane.CANCEL_OPTION) {
                System.out.println("Clicked Cancel");
                JOptionPane.getRootFrame().dispose();
            } else {
                System.out.println("something else (like clicked the 'x' button)");
            }
        });
        //mainP.add(leaveHighB);
        panel1.add(leaveHighB);

        saveB = new JButton("Save");
        saveB.setOpaque(false);
        saveB.setContentAreaFilled(true);
        saveB.setBorderPainted(true);
        saveB.setBounds(124, 250, 120, 31);
        saveB.setPreferredSize(new Dimension(120, 31));
        saveB.setOpaque(false);
        //mainP.add(saveB);
        panel1.add(saveB);

        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);

    }

    public static String[][] getHighscore() {
        FileReader readFile = null;
        BufferedReader reader = null;
        String[][] highscore = new String[10][2];
        try {
            readFile = new FileReader("highscore.txt");
            reader = new BufferedReader(readFile);
            String tempHigh = reader.readLine();
            int counter = 0;
            for (int i = 0; i < 10; i++){
                if (!tempHigh.trim().isEmpty()) {
                    highscore[i][0] = tempHigh;
                    highscore[i][1] = reader.readLine();
                }
                tempHigh = reader.readLine();
            }
            return highscore;
        } catch (IOException e) {
            return null;
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
