package mib.c.SpaceInvaders;

import jdk.jfr.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Menu_GUI extends JFrame implements ActionListener {
    private JLabel titleL, highscoreL, nameL;
    private JButton startB, highscoreB, exitB;
    private JTable highscoreT;
    private JLabel currentNumber;
    private String[] namesHighscore = {"highscore.txt"};
    private int currentIndex = 0;
    private String[] temp1Array = new String[2];
    private Menu_GUI menu_GUI;
    private Highscore_GUI highscore_GUI;
    private JPanel panelMain, panel;

    public static JFrame frame1 = new JFrame();

    public Menu_GUI() throws FileNotFoundException {
        frame1.setSize(800, 600);
        Container mainP = frame1.getContentPane();
        frame1.setLocationRelativeTo(null);
        mainP.setLayout(null);
        mainP.setVisible(true);

        titleL = new JLabel("SpAcE InVaDeRs");
        titleL.setFont(new Font("Chiller", Font.BOLD, 50));
        titleL.setBounds(100, 20, 400, 50);
        mainP.add(titleL);

        startB = new JButton(new ImageIcon(("src/images/start.png")));
        startB.addActionListener((e) -> startGame());
        startB.setOpaque(false);
        startB.setContentAreaFilled(true);
        startB.setBorderPainted(true);
        startB.setBounds(200, 80, 180, 29);
        startB.setPreferredSize(new Dimension(180, 29));
        startB.setOpaque(false);
        mainP.add(startB);

        exitB = new JButton(new ImageIcon(("src/images/exit.png")));
        exitB.setOpaque(false);
        exitB.setContentAreaFilled(true);
        exitB.setBorderPainted(true);
        exitB.setBounds(200, 190, 120, 31);
        exitB.setPreferredSize(new Dimension(120, 31));
        exitB.addActionListener(e -> {
            System.exit(0);
        });
        mainP.add(exitB);

        File currentDir = new File("");
        System.out.println(currentDir.getAbsolutePath());

        highscoreB = new JButton(new ImageIcon("src/images/highscore.png"));
        highscoreB.setOpaque(false);
        highscoreB.setContentAreaFilled(true);
        highscoreB.setBorderPainted(true);
        highscoreB.setBounds(200, 135, 200, 26);
        highscoreB.setPreferredSize(new Dimension(200, 26));
        highscoreB.addActionListener(e -> {
            try {
                readFile("highscore.txt");
                Highscore_GUI highscore_gui = new Highscore_GUI();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        mainP.add(highscoreB);

        startB.addActionListener(this);
        highscoreB.addActionListener(this);
        exitB.addActionListener(this);

        frame1.setVisible(true);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void startGame() {
        EventQueue.invokeLater(() -> {
            var ex = new SpaceInvaders();
            ex.setVisible(true);
        });
        this.setVisible(false);
        this.dispose();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Menu_GUI();
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