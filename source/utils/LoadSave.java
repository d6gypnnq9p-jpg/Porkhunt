package source.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import source.main.Game;

public class LoadSave {

	public static final String PLAYER_ATLAS = "player_sprites.png";
	public static final String LEVEL_ATLAS = "boden.png";
	public static final String LEVEL_ONE_DATA = "level_one_data.png";
	//public static final String LEVEL_ONE_DATA = "level_one_data_long.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String PAUSE_BACKGROUND = "pause_menu.png";
	public static final String URM_BUTTONS = "urm_buttons.png";

	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	public static int[][] GetLevelData() {
    BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
    
    // Dynamically size the array based on your updated Game constants
    int[][] lvlData = new int[img.getHeight()][img.getWidth()]; 

    // Loop through all 16 rows using img.getHeight() or Game.TILES_IN_HEIGHT
    for (int j = 0; j < img.getHeight(); j++) {
        for (int i = 0; i < img.getWidth(); i++) {
            Color color = new Color(img.getRGB(i, j));
            int value = color.getRed();
            if (value >= 48)
                value = 0;

			
            lvlData[j][i] = value;
        }
    }
    return lvlData;
}
}
