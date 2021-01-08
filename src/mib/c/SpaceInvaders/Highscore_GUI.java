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
        this.highscore_GUI = highscore_GUI;
        frame1.setSize(800,600);
        Container mainP = frame1.getContentPane();
        //highscoreT.setLayout(new FlowLayout());
        highscoreT.setBounds(250, 135, 100, 100);
        String[] columnNames = {"Name","Score"};
        Object[][]currentHighscore = {
                {"Lucas","1000"},
                {"David","2000"},
                {"Jan","3000"}
        };
        highscoreT = new JTable(currentHighscore, columnNames);
        highscoreT.setPreferredScrollableViewportSize(new Dimension(200,100));
        highscoreT.setFillsViewportHeight(true);
        frame1.setLocationRelativeTo(null);

        mainP.add(highscoreT);
       // mainP.setLayout(null);
        mainP.setVisible(true);
    }

    public static void main(String[]args)
    {

    }
    public String getHighscore(){
        FileReader readFile = null;
        BufferedReader reader = null;
        try{
            readFile = new FileReader("highscore.txt");
            reader = new BufferedReader(readFile);
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
