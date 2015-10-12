package model;

import java.util.ArrayList;

import model.rules.*;


/**
 * Maneira utilizada para que a view tenha acesso a instâncias e nomes
 * das estratégias.
 *
 */
public enum Strategies {
	CONWAY{
		public GameStrategy getGameStrategy(){
			return new Conway();
		}
	},
	BUBBLE{
		public GameStrategy getGameStrategy(){
			return new Bubble();
		}
	},
	HIGH_LIFE{
		public GameStrategy getGameStrategy(){
			return new HighLife();
		}
	},
	LIFE_WITH_OUT_DEATH{
		public GameStrategy getGameStrategy(){
			return new LifeWithoutDeath();
		}
	},
	LIVE_FREE_OR_DIE{
		public GameStrategy getGameStrategy(){
			return new LiveFreeOrDie();
		}
	},
	SEEDS{
		public GameStrategy getGameStrategy(){
			return new Seeds();
		}
	};
	
	@Override
	public String toString(){
		return this.getGameStrategy().getName();
	}
	
	public abstract GameStrategy getGameStrategy();
	
	public static GameStrategy fabricate(Strategies strategy) {		
		return strategy.getGameStrategy();
	}
	/**
	 * 	Cria um array de strings com todos os nomes de estratégias
	 * existentes na enumeration, em ordem.
	 * @return Nomes das estrategias, em ordem.
	 */
	public static String[] getNames() {
		String[] result = new String[Strategies.values().length];
		ArrayList<String> names = new ArrayList<String>();
		for(Strategies s : Strategies.values()){
			names.add(s.toString());
		}
		names.toArray(result);
		return result;
	}
	
	public static Strategies getStrategy(int strategy) throws InvalidStrategyException {
		if(!Strategies.validateStrategy(strategy)) {
			throw new InvalidStrategyException();
		}
		return Strategies.values()[strategy];
	}
	
	public static boolean validateStrategy(int strategy) {		
		return (strategy >= 0) && (strategy < Strategies.values().length);
	}
}
