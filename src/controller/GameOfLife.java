package controller;

import javax.swing.JOptionPane;

import model.GameEngine;
import view.GameView;
import view.GUI.GUIView;

public class GameOfLife {
	
	private GameEngine engine;
	private GameController controller;
	private GameView view;
	
	public GameOfLife(){
		engine = new GameEngine(getHeigth(), getwidth());
		controller = new GameController();
		controller.setEngine(engine);
		view = new GUIView(controller, engine);
		view.start();
	}

	private int getHeigth() {
		try{
			int heigth = Integer.parseInt (JOptionPane.showInputDialog("Digite a altura do tabuleiro: ") );
			
			while ( heigth < 10 || heigth > 30) {
				heigth = Integer.parseInt (JOptionPane.showInputDialog(null, "Voce deve digitar um numero entre 10 e 30!") );
			}
			return heigth;
		}catch(NumberFormatException ex){
			System.exit(1);
			return 0;
		}
	}

	private int getwidth() {
		try{
			int width = Integer.parseInt (JOptionPane.showInputDialog("Digite a largura do tabuleiro: ") );
			
			while ( width < 10 || width > 30) {
				width = Integer.parseInt (JOptionPane.showInputDialog(null, "Voce deve digitar um numero entre 10 e 30!") );
			}
			
			return width;
		}catch(NumberFormatException ex){
			System.exit(1);
			return 0;
		}
	}
}
