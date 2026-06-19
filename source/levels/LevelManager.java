package source.levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import source.main.Game;
import source.utils.LoadSave;

public class LevelManager {

	private Game game;
	private BufferedImage[] levelSprite;
	private Level levelOne;

	public LevelManager(Game game) {
		this.game = game;
		importOutsideSprites();
		levelOne = new Level(LoadSave.GetLevelData());
	}

	private void importOutsideSprites() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[48];
		for (int j = 0; j < 2; j++)
			for (int i = 0; i < 5; i++) {
				int index = j * 5 + i;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
	}

	public void draw(Graphics g, int xLvlOffset) {
    int[][] data = levelOne.getLevelData();

    for (int j = 0; j < data.length; j++) // Loop jede Höhe des Levels
        for (int i = 0; i < data[j].length; i++) { // Loop jede Breite des Levels
            int index = levelOne.getSpriteIndex(i, j);
            g.drawImage(levelSprite[index], Game.TILES_SIZE * i - xLvlOffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null); // Textur zeichnen
        }
	}

	public void update() {

	}

	public Level getCurrentLevel() {
		return levelOne;
	}

}
