package source.main;

import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel{ // Zeichenfläche
    
    private Game game;

	public GamePanel(Game game) {
		this.game = game;
		
		setPanelSize();
	}

    private void setPanelSize() {
        Dimension size = new Dimension(800, 400);
		setPreferredSize(size);
    }
}