package source.gamestates;

import java.awt.Color;
import java.awt.Font;
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
	private Game game;
	private boolean paused = false;
	private boolean showControls = false;

	private int xLvlOffset;
	private int leftBorder = (int) (0.3 * Game.GAME_WIDTH);
	private int rightBorder = (int) (0.65 * Game.GAME_WIDTH);
	private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
	private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
	private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;

	private static float xSpawn = 200;
	private static float ySpawn = 600;

	public Playing(Game game) {
		super(game);
		this.game = game;
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

	private void checkCloseToBorder() {
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

		if (showControls)
			drawControls(g);
	}

	private void drawControls(Graphics g) { // Tastenbelegung anzeigen
		String[] lines = {
			"Tastenbelegung",
			"Tastenbelegung anzeigen: C",
			"Bewegen:           A / D",
			"Springen:           Space",
			"Level zurücksetzen: Löschen"
		};

		int padding = 12;
		int lineHeight = 22;
		int boxWidth = 220;
		int boxHeight = padding * 2 + lines.length * lineHeight;
		int boxX = 20;
		int boxY = 20;

		// Hintergrund
		g.setColor(new Color(0, 0, 0, 160));
		g.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 12, 12);

		// Rand
		g.setColor(new Color(255, 255, 255, 80));
		g.drawRoundRect(boxX, boxY, boxWidth, boxHeight, 12, 12);

		// Überschrift
		g.setFont(new Font("Arial", Font.BOLD, 14));
		g.setColor(Color.WHITE);
		g.drawString(lines[0], boxX + padding, boxY + padding + lineHeight - 4);

		// Einträge
		g.setFont(new Font("Arial", Font.PLAIN, 13));
		g.setColor(new Color(220, 220, 220));
		for (int i = 1; i < lines.length; i++) {
			g.drawString(lines[i], boxX + padding, boxY + padding + (i + 1) * lineHeight - 4);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) { // Eingabe des Spielers
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_C:
			showControls = !showControls;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { // Eingabe des Spielers
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