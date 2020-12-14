package mib.c.game;

import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    /**
     * Constructor ...
     */
    @org.junit.jupiter.api.Test
    void player() {
    }
    /**
     * test function
     */
    @org.junit.jupiter.api.Test
    void move() {
        Player player = new Player();

        /**
         * Player moves left
         */
        try {
            player.Move(Game.MoveDirection.MoveLeft);
            assertEquals(-1, player.position_x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * Player moves left
         */
        try {
            player.Move(Game.MoveDirection.MoveLeft);
            assertEquals(-2, player.position_x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * Player moves right
         */
        try {
            player.Move(Game.MoveDirection.MoveRight);
            assertEquals(-1, player.position_x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * Moving up and down not allowed
         */
        try {
            player.Move(Game.MoveDirection.MoveDown);
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @org.junit.jupiter.api.Test
    void draw() {
    }

    @org.junit.jupiter.api.Test
    void loadPlayer() {
    }
}