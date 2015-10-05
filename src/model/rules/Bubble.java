package model.rules;


import model.*;


/**
 * Estrategia do jogo bubble
 * 
 * regra:
 * 		viva - sempre morre
 * 		morta - renace se ao menos uma celula vizinha for viva
 * @author scartezini
 *
 */
public class Bubble extends GameStrategy{

	@Override
	public boolean shouldKeepAlive(int i, int j) {
		return false;
	}

	@Override
	public boolean shouldRevive(int i, int j) {
		return (!engine.isCellAlive(i, j))
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 1) 
				|| (engine.numberOfNeighborhoodAliveCells(i, j) == 2) 
				|| (engine.numberOfNeighborhoodAliveCells(i, j) == 3) 
				|| (engine.numberOfNeighborhoodAliveCells(i, j) == 4) 
				|| (engine.numberOfNeighborhoodAliveCells(i, j) == 5) 
				|| (engine.numberOfNeighborhoodAliveCells(i, j) == 6) 
				|| (engine.numberOfNeighborhoodAliveCells(i, j) == 7) 
				|| (engine.numberOfNeighborhoodAliveCells(i, j) == 8);
	}

	@Override
	public String getName() {
		return "Bubble";
	}

}
