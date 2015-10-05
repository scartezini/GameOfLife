package view.GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

import controler.GameController;
import observer.Observer;

@SuppressWarnings("serial")
public class GUIBoard extends JPanel implements Observer {
	
	private ImageIcon liveCell = new ImageIcon("live.png");
	private ImageIcon deadCell = new ImageIcon("dead.png");
	
	
	private int height;
	private int width;
	
	private GUICell[][] board;
	private GameController controller;
	
	
	public GUIBoard(GameController controller){
		super();
		height = controller.getHeight();
		width = controller.getWidth();
		this.controller = controller;
		
		createBoard();
		update();
		
		GridLayout manager = new GridLayout(height,width);
		setLayout(manager);
		
		for( int i = 0; i< height; i++){
			for( int j =0 ; j<width; j++){
				add(board[i][j]);
			}
		}
		
		setVisible(true);
	}
	
	public void createBoard(){
		board = new GUICell[height][width];
		
		for( int i = 0; i< height; i++){
			for( int j =0 ; j<width; j++){
				board[i][j] = new GUICell(i, j);
				board[i][j].setBackground(Color.gray);
				board[i][j].setBorderPainted(false);
				board[i][j].addActionListener(new CellRead(board[i][j]));
			}
		}		
	}
	
	private class CellRead implements ActionListener{
		
		private GUICell button;
		
		public CellRead(GUICell button) {
			this.button = button;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(controller.isCellAlive(button.i,button.j)){
				button.setIcon(deadCell);
				controller.makeCellDead(button.i, button.j);
			}else{
				button.setIcon(liveCell);
				controller.makeCellAlive(button.i, button.j);
			}	
		}
	}
	
	
	@Override
	public void update() {
		for (int i = 0 ; i < height;i++){
			for(int j = 0; j < width ; j++){
				if(controller.isCellAlive(i, j)){
					board[i][j].setIcon(liveCell);
				}else{
					board[i][j].setIcon(deadCell);
				}
				board[i][j].setVisible(true);
			}
		}
		
	}

}
