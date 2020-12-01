package mib.c.game;

import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @org.junit.jupiter.api.Test
    void player() {
    }

    @org.junit.jupiter.api.Test
    void move() {
        Player player = new Player();

        // Move left
        try {
            player.Move(Game.MoveDirection.MoveLeft);
            assertEquals(-1, player.position_x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Move left
        try {
            player.Move(Game.MoveDirection.MoveLeft);
            assertEquals(-2, player.position_x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Move right
        try {
            player.Move(Game.MoveDirection.MoveRight);
            assertEquals(-1, player.position_x);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Moving down and up is not allowed
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