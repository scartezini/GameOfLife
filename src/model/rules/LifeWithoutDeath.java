package model.rules;

import model.*;


/**
 * Estrategia do jogo Life With out Death
 * 
 * Regras:
 * 		viva - sempre permanece viva
 * 		morta- renace se exitir 3 vizinhos vivos
 * 
 * @author scartezini
 *
 */
public class LifeWithoutDeath extends GameStrategy {

	@Override
	public boolean shouldKeepAlive(int i, int j) {
		return true;
	}

	@Override
	public boolean shouldRevive(int i, int j) {
		return (!engine.isCellAlive(i, j))
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 3);
	}

	@Override
	public String getName() {
		return "Life With out Death";
	}
	

}
