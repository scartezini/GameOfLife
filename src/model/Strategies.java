package model;

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
	
	
	public abstract GameStrategy getGameStrategy();
	
	public static GameStrategy fabricate(Strategies strategy) {		
		return strategy.getGameStrategy();
	}
	
	public static Strategies getStrategy(int strategy) throws InvalidStrategyException {
		switch (strategy) {
		 case 1: return CONWAY;
		 case 2: return BUBBLE;
		 case 3: return HIGH_LIFE;
		 case 4: return LIFE_WITH_OUT_DEATH;
		 case 5: return LIVE_FREE_OR_DIE;
		 case 6: return SEEDS;
		}
		throw new InvalidStrategyException();
	}
	
	public static boolean validateStrategy(int strategy) {
		int amountOfStrategies = Strategies.values().length;
		
		return (strategy > 0) && (strategy <= amountOfStrategies);
	}
}
