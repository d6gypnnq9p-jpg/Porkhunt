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
		for (int j = 0; j < 1; j++)
			for (int i = 0; i < 1; i++) {
				//int index = j * 12 + i;
				int index = 0;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
	}

	public void draw(Graphics g) {
    // Get the actual loaded data array
    int[][] data = levelOne.getLevelData();

    for (int j = 0; j < data.length; j++) // Loops exactly 14 times (the height of your image)
        for (int i = 0; i < data[j].length; i++) { // Loops exactly across the image width
            int index = levelOne.getSpriteIndex(i, j);
            g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
        }
	}

	public void update() {

	}

	public Level getCurrentLevel() {
		return levelOne;
	}

}
