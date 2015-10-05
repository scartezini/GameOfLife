package model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import model.rules.*;
import observer.Observer;
import observer.Subject;





/**
 * Representa um ambiente (environment) do jogo GameOfLife.
 * 
 * Essa implementacao eh nao inifinita, ou seja, nem todas as celulas possuem
 * oito celulas vizinhas. Por exemplo, a celula de coordenada (0,0) possui
 * apenas tres celulas vizinhas, (0,1), (1,0) e (1,1).
 * 
 * Um ambiente eh representado como um array bidimensional de celulas, com
 * altura (height) e comprimento (width).
 * 
 * @author rbonifacio
 */
public class GameEngine implements Subject{
	private int height;
	private int width;
	private Cell[][] cells;

	private GameStrategy strategy;
	
	private List<Observer> observers = new ArrayList<Observer>();

	/**
	 * Construtor da classe Environment.
	 * 
	 * @param height
	 *            dimensao vertical do ambiente
	 * @param width
	 *            dimentsao horizontal do ambiente
	 */
	public GameEngine(int height, int width) {
		this.height = height;
		this.width = width;

		cells = new Cell[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = new Cell();
			}
		}
		
		strategy = new Conway();
		strategy.setEngine(this);
	}

	
	public void setEstrategia(GameStrategy e) {
		e.setEngine(this);
		strategy = e;
	}
	
	public GameStrategy getEstrategia() {
		return strategy;
	}

	/**
	 * Calcula uma nova geracao do ambiente. Essa implementacao delega para 
	 * a estrategia de derivacao a logica necessaria para identificar 
	 * se uma celula deve se tornar viva ou ser mantida viva na proxima 
	 * geracao. 
	 */
	public void nextGeneration() {
		List<Cell> mustRevive = new ArrayList<Cell>();
		List<Cell> mustKill = new ArrayList<Cell>();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if ((!strategy.shouldKeepAlive(i, j)) && cells[i][j].isAlive()) {
					mustKill.add(cells[i][j]);
				}
				else if (strategy.shouldRevive(i, j)) {
					mustRevive.add(cells[i][j]);
				} 				
			}
		}
		
		for (Cell cell : mustRevive) {
			cell.revive();	
		}
		
		for (Cell cell : mustKill) {
			cell.kill();
		}
		
		notifyObserver();
		
	}

	/**
	 * Torna possivel o mundo infinito atraves das bordas
	 * @param x numero a ser normalizado
	 * @return
	 */
	public int normalize(int x){
		if(x >= height){
			return x - height;
		}
		else if (x < 0){
			return x + height;
		}
		
		return x;
	}
	
	
	/**
	 * Torna a celula de posicao (i, j) viva
	 * 
	 * @param i posicao vertical da celula
	 * @param j posicao horizontal da celula
	 * 
	 * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
	 */
	public void makeCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			cells[i][j].revive();
			notifyObserver();
		}
		else {
			new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}
	
	public void makeCellDead(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			cells[i][j].kill();
			notifyObserver();
		}
		else {
			new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}
	
	/**
	 * Verifica se uma celula na posicao (i, j) estah viva.
	 * 
	 * @param i Posicao vertical da celula
	 * @param j Posicao horizontal da celula
	 * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
	 * 
	 * @throws InvalidParameterException caso a posicao (i,j) nao seja valida. 
	 */
	public boolean isCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			return cells[i][j].isAlive();
		}
		else {
			throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}

	/**
	 * Retorna o numero de celulas vivas no ambiente. 
	 * Esse metodo eh particularmente util para o calculo de 
	 * estatisticas e para melhorar a testabilidade.
	 * 
	 * @return  numero de celulas vivas.
	 */
	public int numberOfAliveCells() {
		int aliveCells = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(isCellAlive(i,j)) {
					aliveCells++;
				}
			}
		}
		return aliveCells;
	}

	/*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
	public int numberOfNeighborhoodAliveCells(int i, int j) {
		int alive = 0;
		for (int a = i - 1; a <= i + 1; a++) {
			for (int b = j - 1; b <= j + 1; b++) {
				int aux_a = normalize(a);
				int aux_b = normalize(b);
				if (validPosition(aux_a, aux_b)  && (!(aux_a==i && aux_b == j)) && cells[aux_a][aux_b].isAlive()) {
					alive++;
				}
			}
		}
		return alive;
	}

	public void setStrategy(GameStrategy strategy) {
		strategy.setEngine(this);
		this.strategy = strategy;
	}
	
	/*
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 */
	private boolean validPosition(int a, int b) {
		return a >= 0 && a < height && b >= 0 && b < width;
	}

	/* Metodos de acesso as propriedades height e width */
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Mata todas as celulas
	 */
	public void killOfAllCells() {
		for(int i = 0;  i < height; i++){
			for(int j = 0; j < width; j++){
				cells[i][j].kill();
			}
		}
		notifyObserver();
		
	}


	@Override
	public void attachObserver(Observer observer) {
		observers.add(observer);
		
	}
	
	@Override
	public void detachObserver(Observer observer) {
		observers.remove(observer);		
	}

	@Override
	public void notifyObserver() {
		for (Observer observer : observers) {
			observer.update();
		}
	}


	


	
	

}
