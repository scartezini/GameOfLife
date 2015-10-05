package view.GUI;

import javax.swing.SwingUtilities;

import controler.GameController;
import model.GameEngine;
import view.GameView;

public class GUIView extends GameView implements Runnable{

	private GameWindow frame;
	
	public GUIView(GameController controller, GameEngine engine) {
		super(controller, engine);
		SwingUtilities.invokeLater(this);
	}

	@Override
	public void update() {
		frame.updateBoard();
	}

	@Override
	public void run() {
		frame = new GameWindow(controller);
		
	}

	@Override
	public void start() {		
	}

}
