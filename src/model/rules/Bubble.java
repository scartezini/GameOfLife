package model.rules;


import model.*;


/**
 * Estrategia do jogo bubble
 * 
 * regra:
 * 		viva - sempre morre
 * 		morta - renasce se ao menos uma celula vizinha for viva
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
				&& (engine.numberOfNeighborhoodAliveCells(i, j) >= 1);
	}

	@Override
	public String getName() {
		return "Bubble";
	}

}
