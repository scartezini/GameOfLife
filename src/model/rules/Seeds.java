package model.rules;

import model.*;


/**
 * Estrategia do jogo Seeds
 * 
 * Regras: 
 * 		viva - uma celula viva sempre morre
 * 		morta - revive se tiver exatamente 2 vizinhos vivos
 * 
 * @author scartezini
 *
 */
public class Seeds extends GameStrategy{

	@Override
	public boolean shouldKeepAlive(int i, int j) {
		return false;
	}

	@Override
	public boolean shouldRevive(int i, int j) {
		return (!engine.isCellAlive(i, j))
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 2);
	}

	@Override
	public String getName() {
		return "Seeds";
	}

}
