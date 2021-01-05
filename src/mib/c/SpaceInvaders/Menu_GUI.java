package mib.c.SpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Menu_GUI extends JFrame implements ActionListener {
    private JLabel titleL;
    private JButton startB, highscoreB, exitB, leftScrollB, rightScrollB;
    private JTextField score;
    private JTextField name;
    private JLabel currentNumber;
    private String[] namesHighscore = {"highscore.txt"};
    private ArrayList<String[]> currentHighscore;
    private int currentIndex = 0;
    private String[] temp1Array = new String[2];

    public static JFrame frame1 = new JFrame();

    public Menu_GUI() {
        frame1.setSize(640, 480);
        Container mainP = frame1.getContentPane();
        mainP.setLayout(null);
        mainP.setVisible(true);

        titleL = new JLabel("SpAcE InVaDeRs");
        startB = new JButton(new ImageIcon(("C:\\Users\\Jan\\Desktop\\AoP 2\\Intelli\\untitled\\src\\start.png")));
        highscoreB = new JButton(new ImageIcon(("C:\\Users\\Jan\\Desktop\\AoP 2\\Intelli\\untitled\\src\\highscore.png")));
        highscoreB.setOpaque(false);
        highscoreB.setContentAreaFilled(false);
        highscoreB.setBorderPainted(false);

        exitB = new JButton(new ImageIcon(("C:\\Users\\Jan\\Desktop\\AoP 2\\Intelli\\untitled\\src\\exit.png")));
        exitB.setOpaque(false);
        exitB.setContentAreaFilled(false);
        exitB.setBorderPainted(false);

        mainP.add(titleL);
        titleL.setFont(new Font("Chiller", Font.BOLD, 50));
        titleL.setBounds(100, 20, 400, 50);

        mainP.add(startB);
        startB.setMnemonic(KeyEvent.VK_S);
        startB.setBounds(200, 80, 180, 29);
        startB.setPreferredSize(new Dimension(180, 29));

        mainP.add(highscoreB);
        highscoreB.setMnemonic(KeyEvent.VK_H);
        highscoreB.setBounds(200, 135, 200, 26);
        highscoreB.setPreferredSize(new Dimension(200, 26));
        highscoreB.addActionListener(e -> {
            try {
                readFile("highscore.txt");
                System.out.println("Hat geklappt");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        mainP.add(exitB);
        exitB.setMnemonic(KeyEvent.VK_E);
        exitB.setBounds(200, 190, 120, 31);
        exitB.setPreferredSize(new Dimension(120, 31));

        startB.addActionListener(this);
        highscoreB.addActionListener(this);
        exitB.addActionListener(this);

        frame1.setVisible(true);
        frame1.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void updateHighscore() {
        score.setText(currentHighscore.get(currentIndex)[0]);
        currentNumber.setText(" Highscore " + (currentIndex + 1) + " von " + currentHighscore.size());
    }

    public static void main(String[] args) {
        new Menu();
    }

    private void saveInternal() {
        temp1Array[0] = name.getText();
        temp1Array[1] = score.getText();
    }

    public static ArrayList readFile(String name) throws FileNotFoundException {

        BufferedReader reader = null;

        ArrayList<String[]> highscore = new ArrayList<String[]>();
        try {
            String str = null;
            reader = new BufferedReader(new FileReader(name));
            int i = 0;

            String[] tempArray = new String[3];

            while ((str = reader.readLine()) != null) {
                //Falls index < 6, dann schreibe String in String tempArray
                if (i < 2) {
                    tempArray[i] = str;
                }
                //Falls index == 2, dann alle notwendigen Zeilen gelesen
                if ((i == 2)) {
                    //Index wird wieder auf 0 gesetzt
                    i = 0;
                    //befülltes String Array wird der ArrayListe angefügt
                    highscore.add(tempArray);
                    tempArray = new String[3];
                }
                //System.out.println(str);
                //Falls String kein Inhalt besitzt, setze mit while-Schleife fort
                if (str.isEmpty()) {
                    continue;
                }
                i++;
            }
        } catch (final IOException e) {
            System.out.println("Error reading file");
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
        return highscore;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}