package mib.c.SpaceInvaders;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Invader extends Sprite {

    private String[] sources = {"src/images/Invader1.png", "src/images/Invader2.png", "src/images/Invader3.png", "src/images/Invader4.png", "src/images/Invader5.png", "src/images/Invader6.png", "src/images/Invader7.png", "src/images/Invader8.png", "src/images/Invader9.png", "src/images/Invader10.png", "src/images/Invader11.png", "src/images/Invader12.png", "src/images/Invader13.png", "src/images/Invader14.png", "src/images/Invader15.png", "src/images/Invader16.png", "src/images/Invader17.png", "src/images/Invader18.png", "src/images/Invader19.png", "src/images/Invader20.png"};
    private Bomb bomb;

    public Invader(int x, int y) {

        initAlien(x, y);
    }

    private void initAlien(int x, int y) {

        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);

        Random generator = new Random();
        BufferedImage alienImage = null;
        try {
            alienImage = ImageIO.read(new File(sources[generator.nextInt(20)]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage rzdAlienImage = createResizedCopy(alienImage, 32, 32, false);
        var ii = new ImageIcon(rzdAlienImage);

        setImage(ii.getImage());
    }

    public void act(int direction) {

        this.x += direction;
    }

    public Bomb getBomb() {

        return bomb;
    }

    public class Bomb extends Sprite {

        private boolean destroyed;

        public Bomb(int x, int y) {

            initBomb(x, y);
        }

        private void initBomb(int x, int y) {

            setDestroyed(true);

            this.x = x;
            this.y = y;

            var bombImg = "src/images/bomb.png";
            var ii = new ImageIcon(bombImg);
            setImage(ii.getImage());
        }

        public void setDestroyed(boolean destroyed) {

            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {

            return destroyed;
        }


}
    BufferedImage createResizedCopy(Image originalImage,
                                    int scaledWidth, int scaledHeight,
                                    boolean preserveAlpha)
    {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
}