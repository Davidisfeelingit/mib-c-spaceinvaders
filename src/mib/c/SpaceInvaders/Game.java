package mib.c.SpaceInvaders;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game extends JPanel {
    private Dimension d;
    private List<Invader> invaders;
    private Player player;
    private Shot shot;

    private int direction = -1;
    private int deaths = 0;

    private boolean inGame = true;
    private String explImg = "src/images/explosion.png";

    private Timer timer;
    private int delay;

    private Main_GUI main_gui;

    public Game(Main_GUI main_GUI) {
        this.main_gui = main_GUI;

        delay = Commons.DELAY;
        initBoard();
        gameInit();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }

    private void gameInit() {
        invaders = new ArrayList<>();
        createAliens();

        player = new Player();
        BufferedImage buffPlayerImg = null;
        try {
            buffPlayerImg = ImageIO.read(new File(main_gui.character.getChaSource()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image playerImg = main_gui.character.createResizedCopy(buffPlayerImg, 50, 50, false);
        player.setImage(playerImg);
        shot = new Shot();
    }

    private void createAliens() {
        invaders.clear();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                var alien = new Invader(Commons.ALIEN_INIT_X + 44 * j,
                        Commons.ALIEN_INIT_Y + 44 * i);
                invaders.add(alien);
            }
        }
    }

    private boolean areAliensAlive() {
        for (Invader invader : invaders)
            if (invader.isVisible())
                return true;

        return false;
    }

    private void drawAliens(Graphics g) {
        if (!areAliensAlive()) {
            createAliens();

            // Each new level is executed faster.
            if (delay > 0) {
                delay--;
                timer.setDelay(delay);
            }
        }

        for (Invader invader : invaders) {
            if (invader == null)
                continue;

            if (invader.isVisible()) {
                g.drawImage(invader.getImage(), invader.getX(), invader.getY(), this);
            }

            if (invader.isDying()) {
                invader.die();
            }
        }
    }

    private void drawPlayer(Graphics g) {
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {
            player.die();
            inGame = false;
        }
    }

    private void drawHighScore(Graphics g) {
        var small = new Font("Helvetica", Font.BOLD, 10);
        String highscore = String.format("Highscore: %d", deaths);

        g.setColor(Color.green);
        g.setFont(small);
        g.drawString(highscore, 0, 10);
    }

    private void drawShot(Graphics g) {
        if (shot.isVisible()) {
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    private void drawBombing(Graphics g) {
        for (Invader a : invaders) {
            Invader.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (inGame) {
            g.drawLine(0, Commons.GROUND,
                    Commons.BOARD_WIDTH, Commons.GROUND);

            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);
            drawHighScore(g);

        } else {
            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver();
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver() {
        showGameOverAlert();
    }

    private void goBackToMenu() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        this.main_gui.openMenuGUI();
        topFrame.dispose();
    }

    private void showGameOverAlert() {
        String result = (String)JOptionPane.showInputDialog(
                this,
                String.format("Your highscore:    %d", deaths),
                "Game Over",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                ""
        );

        if(result != null && result.length() > 0){
            String highscore = String.format("%s:%d", result, deaths);
            Highscore.saveHighscore(highscore);

        }

        goBackToMenu();
    }

    private void update() {
        // player
        player.act();

        // shot
        if (shot.isVisible()) {
            int shotX = shot.getX();
            int shotY = shot.getY();

            for (Invader invader : invaders) {
                int alienX = invader.getX();
                int alienY = invader.getY();

                if (invader.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + Commons.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + Commons.ALIEN_HEIGHT)) {

                        var ii = new ImageIcon(explImg);
                        invader.setImage(ii.getImage());
                        invader.setDying(true);
                        deaths++;
                        shot.die();
                    }
                }
            }

            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }

        // aliens
        for (Invader invader : invaders) {
            int x = invader.getX();

            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) {
                direction = -1;

                Iterator<Invader> i1 = invaders.iterator();

                while (i1.hasNext()) {
                    Invader a2 = i1.next();
                    a2.setY(a2.getY() + Commons.GO_DOWN);
                }
            }

            if (x <= Commons.BORDER_LEFT && direction != 1) {
                direction = 1;

                Iterator<Invader> i2 = invaders.iterator();

                while (i2.hasNext()) {
                    Invader a = i2.next();
                    a.setY(a.getY() + Commons.GO_DOWN);
                }
            }
        }

        Iterator<Invader> it = invaders.iterator();
        while (it.hasNext()) {
            Invader invader = it.next();

            if (invader.isVisible()) {
                int y = invader.getY();

                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    inGame = false;
                }

                invader.act(direction);
            }
        }

        // Bombs
        var generator = new Random();

        for (Invader invader : invaders) {
            int shot = generator.nextInt(15);
            Invader.Bomb bomb = invader.getBomb();

            if (shot == Commons.CHANCE && invader.isVisible() && bomb.isDestroyed()) {
                bomb.setDestroyed(false);
                bomb.setX(invader.getX());
                bomb.setY(invader.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !bomb.isDestroyed()) {
                if ( bombX >= (playerX)
                        && bombX <= (playerX + Commons.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + Commons.PLAYER_HEIGHT)){

                    var ii = new ImageIcon(explImg);
                    player.setImage(ii.getImage());
                    player.setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {
                bomb.setY(bomb.getY() + 1);

                if (bomb.getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) {
                    bomb.setDestroyed(true);
                }
            }
        }
    }

    private void doGameCycle() {
        update();
        repaint();
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();
            if (key == KeyEvent.VK_SPACE) {
                if (inGame) {
                    if (!shot.isVisible()) {
                        shot = new Shot(x, y);
                    }
                }
            }
        }
    }
}