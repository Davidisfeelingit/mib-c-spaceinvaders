package mib.c.SpaceInvaders;

import javax.swing.*;

public class SpaceInvaders extends JFrame {

    public SpaceInvaders(Main_GUI main_GUI) {
        initUI(main_GUI);
    }

    private void initUI(Main_GUI main_GUI) {
        add(new Game(main_GUI));

        setTitle("Space Invaders");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}

