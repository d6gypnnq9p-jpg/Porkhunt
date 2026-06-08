package source.main;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jFrame;
    
    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();


        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beendet das Programm, wenn das Fenster geschlossen wird
		jFrame.add(gamePanel); // Panel und Fenster verbinden
		jFrame.setResizable(false);
		jFrame.pack(); // Fenster an GamePanel anpassen
		jFrame.setTitle("Porkhunt");
		jFrame.setExtendedState(jFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		jFrame.setLocationRelativeTo(null); // Fenster erscheint in der Mitte des Bildschirms
		jFrame.setVisible(true); // Macht das Fenster sichtbar (MUSS GENAU HIER STEHEN, sonst entsteht in seltenen Fällen ein leeres Fenster)
		//jFrame.addWindowFocusListener(new WindowFocusListener() { // wenn man runter vom fenster geht
			
    }
}
