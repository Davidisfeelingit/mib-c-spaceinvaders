package mib.c.SpaceInvaders;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character {
    private int character = 0;
    final private String [] sources = {"src/images/player.png", "src/images/1.png", "src/images/2.png", "src/images/3.png", "src/images/4.png", "src/images/5.png", "src/images/6.png", "src/images/7.png", "src/images/8.png"};

    public Character(){}

    public void chooseCharacter(int character){
        this.character = Math.floorMod(character, sources.length);
    }
    public String getChaSource(){
        return sources[character];
    }
    public int getChosenCharacter(){
        return character;
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
