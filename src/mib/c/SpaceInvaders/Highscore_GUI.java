package mib.c.SpaceInvaders;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Highscore_GUI extends JFrame {

    private Menu_GUI menu_GUI;
    private Highscore_GUI highscore_GUI;
    JTable highscoreT;

    public static JFrame frame1 = new JFrame();

    public Highscore_GUI(){
        String[] columnNames = {"Name","Score"};
        highscoreT = new JTable(getHighscore(), columnNames);
        this.highscore_GUI = highscore_GUI;
        frame1.setSize(800,600);
        Container mainP = frame1.getContentPane();
        highscoreT.setLayout(new FlowLayout());
        highscoreT.setBounds(250, 135, 100, 100);
        frame1.setSize(800,600);
        highscoreT.setLayout(new FlowLayout());
        highscoreT.setBounds(250, 135, 100, 100);
        highscoreT.setPreferredScrollableViewportSize(new Dimension(200,100));
        highscoreT.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(highscoreT);
        mainP.add(scrollPane);
        mainP.add(highscoreT);
        mainP.setVisible(true);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    public static String[][] getHighscore(){
        FileReader readFile = null;
        BufferedReader reader = null;
        String[][] highscore = new String[10][2];
        try {
            readFile = new FileReader("highscore.txt");
            reader = new BufferedReader(readFile);
            String tempHigh = reader.readLine();
            int counter = 0;
            while (tempHigh != null) {
                highscore[counter][0] = tempHigh;
                highscore[counter][1] = reader.readLine();;
                tempHigh = reader.readLine();
                counter++;
            }
            return highscore;
        }
        catch (Exception e){
            return null;
        }



        finally{
            try{
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
