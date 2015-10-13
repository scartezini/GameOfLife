package model.rules;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.GameEngine;

public class RulesTest {

	GameEngine engine;
	
	@Before
	public void setUp() throws Exception {
		this.engine = new GameEngine(10, 10);
	}
	
	@Test
	public void BubbleTest() {
		int i = 4;
		int j = 4;
		
		engine.setEstrategia(new Bubble());
		
		engine.makeCellAlive(i, j);
		
		engine.nextGeneration();
		for(int a = i-1; a <= i+1; ++a){
			for (int b = j-1; b <= j+1; ++b){
				if(a!=i || b!=j){
					assertTrue(engine.isCellAlive(a, b));
				}
				else{
					assertFalse(engine.isCellAlive(a, b));
				}
			}
		}
	}
	
	@Test
	public void ConwayTest() {
		engine.setEstrategia(new Conway());
		int i[] = {3, 4};
		int j[] = {3, 4};
		
		for(int a:i){
			for(int b:j){
				engine.makeCellAlive(a, b);
			}
		}
		engine.makeCellDead(4, 4);
		engine.nextGeneration();
		
		for(int a:i){
			for(int b:j){
				assertTrue(engine.isCellAlive(a, b));
				engine.makeCellDead(a, b);
			}
		}
		
		engine.nextGeneration();
		
		for(int a = 0; a<10; ++a){
			for(int b=0; b<10; ++b){
				assertFalse(engine.isCellAlive(a, b));
				engine.makeCellAlive(a, b);
			}
		}
		
		engine.nextGeneration();
		
		for(int a = 0; a<10; ++a){
			for(int b=0; b<10; ++b){
				assertFalse(engine.isCellAlive(a, b));
			}
		}
	}
	
	@Test
	public void HighLifeTest() {
		engine.setEstrategia(new HighLife());
		int i = 4;
		int j = 4;
		
		engine.makeCellAlive(i-1, j-1);
		engine.makeCellAlive(i, j);
		engine.makeCellAlive(i+1, j+1);
		
		engine.nextGeneration();
		
		for(int a = 0; a<10; ++a){
			for(int b=0; b<10; ++b){
				if(a==i && b==j){
					assertTrue(engine.isCellAlive(a, b));
				} else {
					assertFalse(engine.isCellAlive(a, b));
				}
			}
		}
		
		engine.nextGeneration();
		
		for(int a = 0; a<10; ++a){
			for(int b=0; b<10; ++b){
				assertFalse(engine.isCellAlive(a, b));
			}
		}
		
		engine.killOfAllCells();
		
		engine.makeCellAlive(i-1, j);
		engine.makeCellAlive(i, j-1);
		engine.makeCellAlive(i, j);
		engine.makeCellAlive(i, j+1);
		engine.makeCellAlive(i+1, j);
		
		int a = 0;
		while(engine.numberOfAliveCells() > 0){
			++a;
			engine.nextGeneration();
		}
		
		assertEquals(10, a);
		
	}
	
	//TODO: Testes das estratégias restantes.

}
