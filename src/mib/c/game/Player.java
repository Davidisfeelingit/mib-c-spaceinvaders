package mib.c.game;


public class Player extends Character {
    int position_x;
    int position_y;
    boolean alive;

    //TODO: implement missing imports
    /*
    rectangle collisionArea;
    bitmap image;
    */

    /**
     * Constructor ...
     */
    public void Player() {
        position_x = 0;
        position_y = 0;
    }

    /**
     * Function which will move the player.
     */
    public void Move(Game.MoveDirection direction) throws Exception {
        if (direction == Game.MoveDirection.MoveLeft)
            position_x -= 1;

        else if (direction == Game.MoveDirection.MoveRight)
            position_x += 1;

        else
            throw new Exception("Wrong Input!");
    }

    /**
     * Function which draws the player build.
     */
    public void Draw(){

    }

    /**
     * Function which updates the current player.
     */
    public void loadPlayer(){

    }


}
