package model;



/**
 * Classe abstrata que define o contrato de regras do jogos
 * fica a dever da sub classes desta definir e implementar  
 * as regras especificas de cada modo de jogo 
 * 
 *  Tem um atributo que atualiza a engine atual
 *  
 * @author scartezini
 *
 */
public abstract class GameStrategy {
	
	protected GameEngine engine;
	
	/** Retorna o nome da regra de derivacao */	
	public abstract String getName();
	
	
	/**
	 * Método booleano para definir se a célula viva permanece assim.
	 * 
	 * @param i
	 * 		linha da célula
	 * @param j
	 * 		coluna da célula
	 * 		
	 * @return
	 * 		valor booleano como resposta
	 */
	public abstract boolean shouldKeepAlive(int i, int j);

	
	/**
	 * Método booleano para definir se a célula morta deve renacer 
	 * @param i
	 * 		linha da célula
	 * @param j
	 * 		coluna da célula
	 * @return
	 * 		valor booleano como resposta
	 */
	public abstract boolean shouldRevive(int i, int j);
	
	public void setEngine(GameEngine engine){
		this.engine = engine;
	}
	
}
