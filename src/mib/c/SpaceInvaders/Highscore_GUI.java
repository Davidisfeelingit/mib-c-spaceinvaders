package spaceSip;
import javax.swing.*;
import java.awt.*;


public class Highscore_GUI extends JScrollPane {

    private spaceSip.Menu_GUI Menu_GUI;
    private spaceSip.Highscore_GUI Highscore_GUI;
    private JTable highscoreT;

    public Highscore_GUI(){
        super();
        highscoreT = new JTable();
        highscoreT.setLayout(new FlowLayout());
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

        add(highscoreT);
    }

    public static void main(String[]args)
    {
        Highscore_GUI Highscore_GUI = new Highscore_GUI();
        //Highscore_GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Highscore_GUI.setSize(800,600);
        Highscore_GUI.setVisible(true);
        //Highscore_GUI.setTitle("Highscore");
    }

}
