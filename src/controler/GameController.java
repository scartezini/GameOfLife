package controler;

import java.security.InvalidParameterException;


import model.GameEngine;
import model.Statistics;
import model.Strategies;


/**
 * Classe que atua como um controlador do 
 * padr�o MVC, separando os componentes da 
 * camada de apresentacao e model. 
 * 
 * @author rbonifacio
 */
public class GameController {

	private GameEngine engine;
	private Statistics statistics;
	
	public GameEngine getEngine() {
		return engine;
	}
	
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}
	
	
	
	
	
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	
	public void halt() {
		//oops, nao muito legal fazer sysout na classe Controller
		System.out.println("\n \n");
		statistics.display();
		System.exit(0);
	}
	
	public void makeCellAlive(int i, int j) {
		try {
			engine.makeCellAlive(i, j);
		}
		catch(InvalidParameterException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void nextGeneration() {
		engine.nextGeneration();
	}

	public int numberOfAliveCells() {
		return engine.numberOfAliveCells();
	}

	/**
	 * sai do sitema
	 */
	public void exit() {
		System.exit(0);
	}
	
	/**
	 * Mata todas as celulas
	 */
	public void killOfAllCells() {
		engine.killOfAllCells();
	}
	
	/**
	 * Faz com que a game engine atualize a 
	 * strategia a ser usada
	 * @param strategy
	 */
	public void defineStrategy(Strategies strategy) {
		engine.setEstrategia(Strategies.fabricate(strategy));
	}

	public int getHeight() {
		return engine.getHeight();
	}

	public int getWidth() {
		return engine.getWidth();
	}

	public boolean isCellAlive(int i, int j) {
		return engine.isCellAlive(i, j);
	}

	public void makeCellDead(int i, int j) {
		try {
			engine.makeCellDead(i, j);
		}
		catch(InvalidParameterException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
