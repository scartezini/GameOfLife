package model;

import java.util.ArrayList;

import model.rules.*;


/**
 * responsavel para instanciar a strategia, futuramente
 * sera usado a injeção de dependeincia 
 * 
 * TODO Injeção de Dependencia
 * @author Eduardo Scartezini
 *
 */
public enum Strategies {
	CONWAY{
		public GameStrategy getGameStrategy(){
			return new Conway();
		}
		@Override
		public String toString(){
			return "Conway";
		}
	},
	BUBBLE{
		public GameStrategy getGameStrategy(){
			return new Bubble();
		}
		@Override
		public String toString(){
			return "Bubble";
		}
	},
	HIGH_LIFE{
		public GameStrategy getGameStrategy(){
			return new HighLife();
		}
		@Override
		public String toString(){
			return "Highlife";
		}
	},
	LIFE_WITH_OUT_DEATH{
		public GameStrategy getGameStrategy(){
			return new LifeWithoutDeath();
		}
		@Override
		public String toString(){
			return "Life without death";
		}
	},
	LIVE_FREE_OR_DIE{
		public GameStrategy getGameStrategy(){
			return new LiveFreeOrDie();
		}
		@Override
		public String toString(){
			return "Live free or die";
		}
	},
	SEEDS{
		public GameStrategy getGameStrategy(){
			return new Seeds();
		}
		@Override
		public String toString(){
			return "Seeds";
		}
	};
	
	
	public abstract GameStrategy getGameStrategy();
	
	public static GameStrategy fabricate(Strategies strategy) {		
		return strategy.getGameStrategy();
	}
	
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
		if(strategy >= Strategies.values().length || strategy < 0) {
			throw new InvalidStrategyException();
		}
		return Strategies.values()[strategy];
	}
	
	public static boolean validateStrategy(int strategy) {
		int amountOfStrategies = Strategies.values().length;
		
		return (strategy >= 0) && (strategy < amountOfStrategies);
	}
}
