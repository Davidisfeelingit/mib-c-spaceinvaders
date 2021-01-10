package mib.c.SpaceInvaders;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Character_GUI extends JPanel {
    private Main_GUI main_gui;
    private JLabel titleLabel;
    private JPanel characterPanel;
    private JButton selectButtonL;
    private JButton selectButtonR;
    private JLabel selCharLabel;

    public Character_GUI(Main_GUI main_gui) {
        this.main_gui = main_gui;
        setSize(800, 600);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        // Title
        titleLabel = new JLabel("Choose your Spaceship");
        titleLabel.setFont(new Font("Chiller", Font.BOLD, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        //choose-character Panel
        characterPanel = new JPanel(null);
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.LINE_AXIS));
        Dimension minSize = new Dimension(5, 100);
        Dimension prefSize = new Dimension(5, 100);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
        characterPanel.add(new Box.Filler(minSize, prefSize, maxSize));

        // selection Button right
        BufferedImage leftArrow = null;
        try {
            leftArrow = ImageIO.read(new File("src/images/leftArrow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage rzdleftArrow = main_gui.character.createResizedCopy(leftArrow, 100, 100, false);
        selectButtonL = new JButton(new ImageIcon(rzdleftArrow));
        selectButtonL.setOpaque(false);
        selectButtonL.setContentAreaFilled(false);
        selectButtonL.setBorderPainted(false    );
        selectButtonL.setPreferredSize(new Dimension(120, 31));
        selectButtonL.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectButtonL.addActionListener((ActionListener) e -> {
            main_gui.character.chooseCharacter(main_gui.character.getChosenCharacter()-1);
            ReloadImage();
        });
        characterPanel.add(selectButtonL);
        characterPanel.add(new Box.Filler(minSize, prefSize, maxSize));

        //selected-Character
        BufferedImage CharacterImage = null;
        try {
            CharacterImage = ImageIO.read(new File(main_gui.character.getChaSource()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage resizedChaImg = main_gui.character.createResizedCopy(CharacterImage, 100, 100, false);
        selCharLabel = new JLabel(new ImageIcon(resizedChaImg));
        selCharLabel.setOpaque(false);
        selCharLabel.setPreferredSize(new Dimension(100, 100));
        characterPanel.add(selCharLabel);
        characterPanel.add(Box.createHorizontalGlue());

        // selection Button right
        BufferedImage rightArrow = null;
        try {
            rightArrow = ImageIO.read(new File("src/images/rightArrow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage rzdRightArrow = main_gui.character.createResizedCopy(rightArrow, 100, 100, false);
        selectButtonR = new JButton(new ImageIcon(rzdRightArrow));
        selectButtonR.setOpaque(false);
        selectButtonR.setContentAreaFilled(false);
        selectButtonR.setBorderPainted(false    );
        selectButtonR.setPreferredSize(new Dimension(120, 31));
        selectButtonR.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectButtonR.addActionListener((ActionListener) e -> {
            main_gui.character.chooseCharacter(main_gui.character.getChosenCharacter()+1);
            ReloadImage();
        });
        characterPanel.add(selectButtonR);
        characterPanel.add(new Box.Filler(minSize, prefSize, maxSize));

        add(characterPanel);

        //Back - to - menu button
        JButton leaveCharB = new JButton(new ImageIcon(("src/images/exit.png")));
        leaveCharB.setOpaque(false);
        leaveCharB.setContentAreaFilled(true);
        leaveCharB.setBorderPainted(true);
        leaveCharB.setBounds(340, 250, 120, 31);
        leaveCharB.setPreferredSize(new Dimension(120, 31));
        leaveCharB.addActionListener(e -> {
            main_gui.openMenuGUI();
        });
        this.add(leaveCharB);



    }

    private void ReloadImage() {
        BufferedImage CharacterImage = null;
        try {
            CharacterImage = ImageIO.read(new File(main_gui.character.getChaSource()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage resizedChaImg = main_gui.character.createResizedCopy(CharacterImage, 100, 100, false);
        selCharLabel.setIcon(new ImageIcon(resizedChaImg));
    }

}
