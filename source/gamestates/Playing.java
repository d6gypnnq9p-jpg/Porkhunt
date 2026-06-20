package source.gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import source.entities.Player;
import source.levels.LevelManager;
import source.main.Game;
import source.utils.LoadSave;

public class Playing extends State implements Statemethods {
	private Player player;
	private LevelManager levelManager;
	private boolean paused = false;

	private int xLvlOffset;
	private int leftBorder = (int) (0.3 * Game.GAME_WIDTH);
	private int rightBorder = (int) (0.65 * Game.GAME_WIDTH); // Bildschrim beginnt bei 65% des sichtbaren Levels nach rechts zu scrollen
	private int lvlTilesWide = LoadSave.GetLevelData()[0].length; // Wie weit das Level ist
	private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
	private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;

	private static float xSpawn = 200; // x Spawn Koordinate des Spielers
	private static float ySpawn = 600; // y Spawn Koordinate des Spielers

	public Playing(Game game) {
		super(game);
		initClasses();
	}

	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(xSpawn, ySpawn, (int) (32 * Game.SCALE), (int) (32 * Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
	}

	@Override
	public void update() {
		if (!paused) {
			levelManager.update();
			player.update();
			checkCloseToBorder();
		}
	}

	private void checkCloseToBorder() { // Muss der screen bewegt werden?
		int playerX = (int) player.getHitbox().x;
		int diff = playerX - xLvlOffset;

		if (diff > rightBorder)
			xLvlOffset += diff - rightBorder;
		else if (diff < leftBorder)
			xLvlOffset += diff - leftBorder;

		if (xLvlOffset > maxLvlOffsetX)
			xLvlOffset = maxLvlOffsetX;
		else if (xLvlOffset < 0)
			xLvlOffset = 0;
	}

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g, xLvlOffset);
		player.render(g, xLvlOffset);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A: // Bewegung nach links
			player.setLeft(true);
			break;
		case KeyEvent.VK_D: // Bewegung nach rechts
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE: // Springen
			player.setJump(true);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		case KeyEvent.VK_BACK_SPACE:
			player.resetPlayerPos();
			break;
		}

	}

	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	public void unpauseGame() {
		paused = false;
	}

	public void windowFocusLost() { player.resetDirBooleans(); }

	public Player getPlayer() { return player; }

	public static float getxSpawn() { return xSpawn; }

	public static float getySpawn() { return ySpawn; }

}
