package model.rules;

import model.*;


/**
 * Estrategia do jogo Conway 
 * 
 * Regras:
 * 		vivo - 2 ou 3 vizinhos, permanece viva
 * 		morta - 3 vizinhos vivos, renace 
 * 
 * @author scartezini
 *
 */
public class Conway extends GameStrategy {

	@Override
	public boolean shouldKeepAlive(int i, int j) {
		return (engine.isCellAlive(i, j))
			&& (engine.numberOfNeighborhoodAliveCells(i, j) == 2 || engine.numberOfNeighborhoodAliveCells(i, j) == 3);
	}

	@Override
	public boolean shouldRevive(int i, int j) {
		return (!engine.isCellAlive(i, j))
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 3);
	}

	@Override
	public String getName() {
		return "Conway";
	}

}
