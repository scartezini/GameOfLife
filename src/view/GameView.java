package view;


import controller.GameController;
import model.GameEngine;
import observer.Observer;

public abstract class GameView implements Observer {

	protected GameController controller;
	
	public GameView(GameController controller, GameEngine engine) {
		this.controller = controller;
		engine.attachObserver(this);
	}
	
	/**
	 * Metodos para iniciar o jogo
	 */
	public abstract void start();


}