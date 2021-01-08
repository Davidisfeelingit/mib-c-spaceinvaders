package mib.c.SpaceInvaders;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Highscore_GUI extends JFrame {

    private Menu_GUI menu_GUI;
    private Highscore_GUI highscore_GUI;
    JTable highscoreT;

    public static JFrame frame1 = new JFrame();

    public Highscore_GUI(){
        String[] columnNames = {"Name","Score"};
        Object[][]currentHighscore = {
                {"Lucas","1000"},
                {"David","2000"},
                {"Jan","3000"}
        };
        highscoreT = new JTable(currentHighscore, columnNames);
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

    public static void main(String[]args)
    {
        getHighscore();
    }
    public static String getHighscore(){
        FileReader readFile = null;
        BufferedReader reader = null;
        try{
            readFile = new FileReader("highscore.txt");
            reader = new BufferedReader(readFile);
            //System.out.println(reader.readLine());
            return reader.readLine();

        }
        catch (Exception e)
        {
            return "0";
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
