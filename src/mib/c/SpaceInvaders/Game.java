package mib.c.SpaceInvaders;

import mib.c.Main;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.IOException;

public class Game extends JPanel {
    private Dimension d;
    private List<Alien> aliens;
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
        aliens = new ArrayList<>();
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
        playSound("src/music/retro.wav");
        playSound("src/music/startup.wav");
    }

    private void createAliens() {
        aliens.clear();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                var alien = new Alien(Commons.ALIEN_INIT_X + 44 * j,
                        Commons.ALIEN_INIT_Y + 44 * i);
                aliens.add(alien);
            }
        }
    }

    private boolean areAliensAlive() {
        for (Alien alien: aliens)
            if (alien.isVisible())
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

        for (Alien alien : aliens) {
            if (alien == null)
                continue;

            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {
                alien.die();
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
        for (Alien a : aliens) {
            Alien.Bomb b = a.getBomb();

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
        playSound("src/music/gameover.wav");
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

            for (Alien alien : aliens) {
                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + Commons.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + Commons.ALIEN_HEIGHT)) {

                        var ii = new ImageIcon(explImg);
                        alien.setImage(ii.getImage());
                        alien.setDying(true);
                        playSound("src/music/explosion.wav");
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
        for (Alien alien : aliens) {
            int x = alien.getX();

            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) {
                direction = -1;

                Iterator<Alien> i1 = aliens.iterator();

                while (i1.hasNext()) {
                    Alien a2 = i1.next();
                    a2.setY(a2.getY() + Commons.GO_DOWN);
                }
            }

            if (x <= Commons.BORDER_LEFT && direction != 1) {
                direction = 1;

                Iterator<Alien> i2 = aliens.iterator();

                while (i2.hasNext()) {
                    Alien a = i2.next();
                    a.setY(a.getY() + Commons.GO_DOWN);
                }
            }
        }

        Iterator<Alien> it = aliens.iterator();
        while (it.hasNext()) {
            Alien alien = it.next();

            if (alien.isVisible()) {
                int y = alien.getY();

                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    inGame = false;
                }

                alien.act(direction);
            }
        }

        // Bombs
        var generator = new Random();

        for (Alien alien : aliens) {
            int shot = generator.nextInt(15);
            Alien.Bomb bomb = alien.getBomb();

            if (shot == Commons.CHANCE && alien.isVisible() && bomb.isDestroyed()) {
                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
                playSound("src/music/alienlaser.wav");
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
                    playSound("src/music/explosion.wav");
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
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
                player.keyPressed(e);
            }
            int x = player.getX();
            int y = player.getY();


            if (key == KeyEvent.VK_SPACE) {
                if (inGame) {
                    if (!shot.isVisible()) {
                        shot = new Shot(x, y);
                        playSound("src/music/playerlaser.wav");
                    }
                }
            }
        }
    }
    public void playSound(String filepath) {
        Path path = Path.of(filepath);
        File clipFile = new File (filepath);
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(clipFile);
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public static void playClip(File clipFile) throws IOException,
            UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        class AudioListener implements LineListener {
            private boolean done = false;
            @Override public synchronized void update(LineEvent event) {
                LineEvent.Type eventType = event.getType();
                if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
            }
            public synchronized void waitUntilDone() throws InterruptedException {
                while (!done) { wait(); }
            }
        }
        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
        try {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try {
                clip.start();
                listener.waitUntilDone();
            } finally {
                clip.close();
            }
        } finally {
            audioInputStream.close();
        }
    }

}




