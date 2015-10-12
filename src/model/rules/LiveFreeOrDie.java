package model.rules;

import model.*;

/**
 * Implementacao de uma estrategia de derivacao baseda nas 
 * regras LiveFreeOrDie. 
 * 
 * @author rbonifacio
 */

public class LiveFreeOrDie extends GameStrategy {

	@Override
	public String getName() {
		return "Live Free or Die";
	}

	@Override
	public boolean shouldKeepAlive(int i, int j) {
		return engine.numberOfNeighborhoodAliveCells(i, j) == 0;
	}

	@Override
	public boolean shouldRevive(int i, int j) {
		return engine.numberOfNeighborhoodAliveCells(i, j) == 2;
	}

}
