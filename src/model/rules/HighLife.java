package model.rules;

import model.*;

/**
 * Implementacao de uma estrategia de derivacao baseda nas 
 * regras HighLife. Note que essa estrategia reusa o comportamento 
 * definido nas regras do Conway mas sobrescreve (overrides) a 
 * implementacao do metodo shouldRevive. 
 * 
 * @author rbonifacio
 */
public class HighLife extends GameStrategy{	
	@Override
	public String getName() {
		return "HighLife"; 
	}
	
	@Override
	public boolean shouldRevive(int i, int j) {
		return !engine.isCellAlive(i, j) &&
				(engine.numberOfNeighborhoodAliveCells(i, j) == 3 ||
				 engine.numberOfNeighborhoodAliveCells(i, j) == 6);
	}

	@Override
	public boolean shouldKeepAlive(int i, int j) {
		return engine.isCellAlive(i, j) && 
				engine.numberOfNeighborhoodAliveCells(i, j) == 2 || 
				engine.numberOfNeighborhoodAliveCells(i, j) == 3;
	}

	
}
