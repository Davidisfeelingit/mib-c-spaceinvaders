package mib.c.game;

import java.awt.*;

public class Invader {
    public enum InvaderType {
        Bug;
    //    Saucer;
    //    Satellite;
    //    Spaceship;
    //    Star;
    }
    /**
     * defines current invaders position
     */
    public Point position;

    /**
     * is true when invader is alive
     */
    public boolean alive;

    /**
     * defines Area in which Invader is hittable
     */
    public Rectangle CollisionArea;

    /**
     *  defines Image of Invaders Bitmap
     */
   // public Bitmap Image;

    public void Invader(InvaderType type){

    }
    public void Move (Game.MoveDirection direction){

    }
    public void Draw(Graphics graphics){

    }
    public void LoadInvader() {

    }

}
