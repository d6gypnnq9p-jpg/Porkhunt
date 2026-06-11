package source.main;

import java.awt.Dimension;
import javax.swing.JPanel;
import source.inputs.KeyboardInputs;
import source.inputs.MouseInputs;

public class GamePanel extends JPanel{ // Zeichenfläche
    
	private MouseInputs mouseInputs;
    private Game game;

	public GamePanel(Game game) {
		mouseInputs = new MouseInputs(this);
		this.game = game;
		
		setPanelSize();

		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}

    private void setPanelSize() {
        Dimension size = new Dimension(800, 400);
		setPreferredSize(size);
    }
}