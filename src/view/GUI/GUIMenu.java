package view.GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import controler.GameController;

import model.InvalidStrategyException;
import model.Strategies;


@SuppressWarnings("serial")
/**
 * Define o comportamento do menu 
 * que de interação com o usuario
 * 
 * @author Eduardo Scartezini
 *
 */
public class GUIMenu extends JPanel implements ActionListener{
	
	private GameController controller;
	
	private String[] strategies = Strategies.getNames();
	
	private JButton animate;
	private JButton nexGenerat;
	private JButton exit;
	private JButton killEveryone;
	private JComboBox<String> strategyBox;
	
	private Timer delay;
	private int displayedGenerations;
	private int generations;
	
	/**
	 * Construtor do menu
	 * @param controller
	 */
	public GUIMenu(GameController controller) {
		super();
		this.controller = controller;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
		setStrategyBox();
		setNexGenerationButton();
		setAnimateButton();
		setKillEveryone();
		setExitButton();
	}
	
	/**
	 * Cria uma caixa de escolha 
	 * com as opçoes das strategias 
	 * de jogo
	 */
	private void setStrategyBox(){
		strategyBox = new JComboBox<String>(strategies);
		strategyBox.addActionListener(this);
		add(strategyBox);
	}
	
	/**
	 * Cria o botão para calcular 
	 * a proxima geração
	 */
	private void setNexGenerationButton(){
		nexGenerat = new JButton("Next Generation");
		nexGenerat.addActionListener(this);
		add(nexGenerat);
	}
	
	/**
	 * Cria o botão para produzir a
	 * animação
	 */
	private void setAnimateButton(){
		animate= new JButton("Animate");
		animate.addActionListener(this);
		add(animate);
	}
	
	/**
	 * Criar o botão que mata 
	 * todas as celulas
	 */
	private void setKillEveryone(){
		killEveryone = new JButton("Kill All Cells");
		killEveryone.addActionListener(this);
		add(killEveryone);
	}
	
	/**
	 * Cria o botao de sair do 
	 * jogo
	 */
	private void setExitButton(){
		exit = new JButton("Exit");
		exit.addActionListener(this);
		add(exit);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == nexGenerat){
			nextGeneration();
		}else if(e.getSource() == animate){
			animate();
		}else if(e.getSource() == strategyBox){
			defineStrategy();
		}else if(e.getSource() == killEveryone){
			killAllCells();
		}else if(e.getSource() == exit){
			exit();
		}else if(e.getSource() == delay){
			animationDelay();
		}
		
		
	}

	private void animationDelay() {
	
		
		if((displayedGenerations == generations) || (controller.numberOfAliveCells() == 0)){
			if(controller.numberOfAliveCells() == 0){
				JOptionPane.showMessageDialog(null, "A população morreu completamente!");
			}
			delay.stop();
		}
		if(generations != 0){
			nextGeneration();
			displayedGenerations++;
		}
	}

	private void exit() {
		controller.exit();
		
	}

	private void killAllCells() {
		controller.killOfAllCells();
	}

	private void defineStrategy() {
		int strategy = strategyBox.getSelectedIndex();
		try{
			controller.defineStrategy(Strategies.getStrategy(strategy));
		}catch (InvalidStrategyException e) {
			JOptionPane.showMessageDialog(null, "Estrategia invalida!");
		}
		
	}

	private void animate() {
		try {
			generations = Integer.parseInt(JOptionPane.showInputDialog("Quantas gerações quer calcular? "));
			displayedGenerations = 0;
			delay = new Timer(250, this);
			delay.setRepeats(true);
			delay.start();
		} catch (NumberFormatException ex) {
			if(ex.getMessage().equals("null")){
				
			}else{
				JOptionPane.showMessageDialog(null, "Escolha um numero valido de gerações!");
				animate();
			}
		}	
	}

	private void nextGeneration() {
		controller.nextGeneration();
	}

}
