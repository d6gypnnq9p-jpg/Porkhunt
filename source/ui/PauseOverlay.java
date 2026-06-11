package source.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import source.gamestates.Gamestate;
import source.gamestates.Playing;
import source.main.Game;
import source.utils.Constants;
import source.utils.LoadSave;
import static source.utils.Constants.UI.PauseButtons.*;
import static source.utils.Constants.UI.URMButtons.*;
import static source.utils.Constants.UI.VolumeButtons.*;

public class PauseOverlay {

	private Playing playing;
	private BufferedImage backgroundImg;
	private int bgX, bgY, bgW, bgH;

	public PauseOverlay(Playing playing) {
		this.playing = playing;
		loadBackground();
		createSoundButtons();
		createUrmButtons();
		createVolumeButton();

	}

	private void createVolumeButton() {
		int vX = (int) (309 * Game.SCALE);
		int vY = (int) (278 * Game.SCALE);
	}

	private void createUrmButtons() {
		int menuX = (int) (313 * Game.SCALE);
		int replayX = (int) (387 * Game.SCALE);
		int unpauseX = (int) (462 * Game.SCALE);
		int bY = (int) (325 * Game.SCALE);
	}

	private void createSoundButtons() {
		int soundX = (int) (450 * Game.SCALE);
		int musicY = (int) (140 * Game.SCALE);
		int sfxY = (int) (186 * Game.SCALE);

	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
		bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int) (25 * Game.SCALE);

	}

	public void update() {

	}

	public void draw(Graphics g) {
		// Background
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		//
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

}
