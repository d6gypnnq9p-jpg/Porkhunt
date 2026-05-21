package source.main;

import source.entities.Player;

public class Game {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    private Player player;

    public Game() {
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        player = new Player();
    }
}
