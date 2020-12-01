package mib.c.game;

import java.util.List;

public class Game {

    public enum MoveDirection {
        MoveUp,
        MoveDown,
        MoveLeft,
        MoveRight
    }

    /**
     * True while the player is alive and no invader reached the bottom of the game.
     */
    Boolean isGameOver;

    /**
     * The player object.
     */
    Player player;

    /**
     * Actual score.
     */
    int score;

    /**
     * Remaining life.
     */
    int lifes;

    /**
     * Number of wave the player reached.
     */
    int wave;

    /**
     * Invaders that are currently in the game.
     */
    List<Invader>invaders;

    /**
     * Shots fired from the player.
     */
    List<Shot> playerShots;

    /**
     * Shots fired from invaders.
     */
    List<Shot> invaderShots;

    /**
     * Initiates the game object.
     */
    public void Game() {}

    /**
     * Draws the next frame.
     */
    public void Draw() {}

    /**
     * Starts the games.
     */
    public void Start() {}

    /**
     * Handle the players input and moves him.
     */
    public void MovePlayer() {}

    /**
     * Finishes all currently active processes for the wave.
     */
    public void waveComplete() {}
}
