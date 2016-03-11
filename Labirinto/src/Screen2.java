import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Screen2 extends JPanel implements  ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private final static int CELL_SIZE = 25;

	private int width;
	private int height;

	
	public int x_bloco;
	public int y_bloco;
	public int x_bloco_azul;
	public int xLimiteRight;
	public int xLimiteLeft;
	public int yLimiteDown;
	public int yLimiteUp;
	
	protected Image image;
	
	private boolean[][] labyrinth;
	

	public Screen2(boolean[][] labyrinth) {
		this.labyrinth = labyrinth;

		this.width = this.labyrinth[0].length;
		this.height = this.labyrinth.length;
		
		x_bloco = 25;
		y_bloco = 25;
		x_bloco_azul = 50;
		xLimiteRight = this.width-x_bloco;
		xLimiteLeft = x_bloco;
		yLimiteDown = y_bloco;
		yLimiteUp = this.width-y_bloco;
		

		setPreferredSize(new Dimension(this.width * CELL_SIZE, this.height * CELL_SIZE));
		
		image = new ImageIcon(getClass().getResource("/BATMAN.png")).getImage();
		
		Timer timer = new Timer(1000, this);

		// Iniciamos o relógio.
		timer.start();
	}

	public void paintComponent(Graphics g) {
		for(int i = 0; i < this.height; i++) {
			int y = i * CELL_SIZE;

			for(int j = 0; j < this.width; j++) {
				int x = j * CELL_SIZE;

				if(labyrinth[i][j]) {
					g.setColor(Color.WHITE);
				}
				else {
					g.setColor(Color.BLACK);
				}
				g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
			}
		}
		
		g.drawImage(image, x_bloco, y_bloco,CELL_SIZE,CELL_SIZE, null);
		
		g.setColor(Color.BLUE);
		g.fillRect(x_bloco_azul, y_bloco, CELL_SIZE, CELL_SIZE);
		
    	getToolkit().sync();
    }
	
	public void actionPerformed(ActionEvent e) {

		// Se o quadrado está à esquerda...
		if(x_bloco_azul == 50) {
			// ...movemos para a direita!
			x_bloco_azul = 75;
		}

		// Se o quadrado está à direita...
		else {
			// ...movemos para a esquerda!
			x_bloco_azul = 50;
		}

		// De uma forma ou de outra o quadrado se moveu,
		// então sempre precisamos redesenhar a tela!
		repaint();
	}
	
    @Override
	public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();

    	// Se a tecla apertada foi a seta para a esquerda...
    	if(key == KeyEvent.VK_LEFT) {
    		//...e o limite esquerdo for atingido, mantemos o boneco..
    		if(x_bloco == xLimiteLeft - CELL_SIZE) {
    			x_bloco = xLimiteLeft - CELL_SIZE;
    		}
    		// ... do contrario, movemos o boneco para a esquerda...
    		else{
    		x_bloco = x_bloco - CELL_SIZE;
    		}
    		//System.out.println("left");
    		// ...e redesenhamos a tela.
    		repaint();
    	}

    	// Se a tecla apertada foi a seta para a direita...
    	if(key == KeyEvent.VK_RIGHT) {
    		//...e o limite direito for atingido, mantemos o boneco..
    		if(x_bloco == 950) {
    			x_bloco = 950;
    		}
    		// ...do contrario, movemos o boneco para a direita!
    		else{
    		x_bloco = x_bloco + CELL_SIZE;
    		}
    		// ...e redesenhamos a tela.
    		repaint();

    		
    	}
    	// Se a tecla apertada foi a seta para cima...
    	if(key == KeyEvent.VK_UP) {
    		//...e o limite superior for atingido, mantemos o boneco..
    		if(y_bloco == 0){
    			y_bloco = 0;
    		}
    		// ...do contrario, movemos o boneco para cima!
    		else{
    		y_bloco = y_bloco - CELL_SIZE;
    		}
    		// ...e redesenhamos a tela.
    		repaint();

    	}
    	// Se a tecla apertada foi a seta para baixo...
    	if(key == KeyEvent.VK_DOWN) {
    		//...e o limite inferior for atingido, mantemos o boneco..
    		if(y_bloco == 450){
    			y_bloco = 450;
    		}
    		// ...do contrario, movemos o boneco para baixo!
    		else{
    		y_bloco = y_bloco + 25;
    		}
    		// ...e redesenhamos a tela.
    		repaint();
    		System.out.println("y_bloco: " + y_bloco);
    		System.out.println("yLimiteDown: " + yLimiteDown);
    	}
	}

    @Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}
}

