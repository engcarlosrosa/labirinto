import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Screen2 extends JPanel implements  ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private final static int CELL_SIZE = 25;

	public static int getCellSize() {
		return CELL_SIZE;
	}
	
	private Timer timer;
	
	private int width;
	private int height;

	
<<<<<<< HEAD
	private Stack<Crumb> path = new Stack<>();
=======
	public int x_bloco;
	public int y_bloco;
	public int x_bloco_azul;
	public int xLimiteRight;
	public int xLimiteLeft;
	public int yLimiteDown;
	public int yLimiteUp;
>>>>>>> 37d5f2172c7f29a468c49a1c2d2f0085a980db9d
	
	private int xUser;
	private int yUser;
	private int xMazeRunner;
	private int yMazeRunner;
	
	private int xObjetivo;
	private int yObjetivo;

	protected Image imageBatman;
	protected Image imageBane;
	
	private boolean[][] labyrinth;
	
	public boolean[][] getLabyrinth(){
		return labyrinth;
	}

	public Screen2(boolean[][] labyrinth) {
		this.labyrinth = labyrinth;

		this.width = this.labyrinth[0].length;
		this.height = this.labyrinth.length;
		
<<<<<<< HEAD
		this.xObjetivo = width - 1;
		this.yObjetivo = height - 1;
		
		xUser = CELL_SIZE;
		yUser = CELL_SIZE;
		
		Crumb crumb = new Crumb(0,0);
		path.add(crumb);
=======
		x_bloco = 25;
		y_bloco = 25;
		x_bloco_azul = 50;
		xLimiteRight = this.width-x_bloco;
		xLimiteLeft = x_bloco;
		yLimiteDown = y_bloco;
		yLimiteUp = this.width-y_bloco;
		
>>>>>>> 37d5f2172c7f29a468c49a1c2d2f0085a980db9d

		setPreferredSize(new Dimension(this.width * CELL_SIZE, this.height * CELL_SIZE));
		
		imageBatman = new ImageIcon(getClass().getResource("/BATMAN.png")).getImage();
		imageBane = new ImageIcon(getClass().getResource("/BanePXL.png")).getImage();
		
		timer = new Timer(1, this);
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
		xMazeRunner = path.peek().getX()*CELL_SIZE;
		yMazeRunner = path.peek().getY()*CELL_SIZE;
		
		g.setColor(Color.BLUE);
		g.fillRect(xObjetivo*CELL_SIZE, yObjetivo*CELL_SIZE, CELL_SIZE, CELL_SIZE);
		
		g.drawImage(imageBatman, xUser, yUser,CELL_SIZE,CELL_SIZE, null);
		g.drawImage(imageBane, xMazeRunner, yMazeRunner,CELL_SIZE,CELL_SIZE, null);
    	getToolkit().sync();
    }
	
	public void actionPerformed(ActionEvent e) {
		if((xMazeRunner/CELL_SIZE) != xObjetivo || (yMazeRunner/CELL_SIZE) != yObjetivo){
			Crumb crumb = path.peek();
			int x = crumb.getX();
			int y = crumb.getY();
			labyrinth[y][x] = false;
			switch(crumb.getPasses()){
			case 0:
				if(x-1 >= 0){
					if(labyrinth[y][x-1]){
						path.add(new Crumb(x-1,y));
					}
				}
				crumb.addPass();
				break;
			case 1:
				if(y-1 >= 0){
					if(labyrinth[y-1][x]){
						path.add(new Crumb(x,y-1));
					}
				}
				crumb.addPass();
				break;
			case 2:
				if(x+1 < width){
					if(labyrinth[y][x+1]){
						path.add(new Crumb(x+1,y));
					}
				}
				crumb.addPass();
				break;
			case 3:
				if(y+1 < height){
					if(labyrinth[y+1][x]){
						path.add(new Crumb(x,y+1));
					}
				}
				crumb.addPass();
				break;
			case 4:
				path.pop();
				break;
			}
			
			repaint();
		}
		else{
			timer.stop();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
    	
    	if(key == KeyEvent.VK_LEFT) {
<<<<<<< HEAD
    		if(xUser == 0) {
    			xUser = 0;
    		}
    		else if(labyrinth[yUser/CELL_SIZE][(xUser/CELL_SIZE)-1]){
        		xUser = xUser - CELL_SIZE;
    		}
=======
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
>>>>>>> 37d5f2172c7f29a468c49a1c2d2f0085a980db9d
    		repaint();
    	}
    	
    	if(key == KeyEvent.VK_RIGHT) {
<<<<<<< HEAD
    		if(xUser == (this.width-1)*CELL_SIZE) {
    			xUser = (this.width-1)*CELL_SIZE;
    		}
    		else if(labyrinth[yUser/CELL_SIZE][(xUser/CELL_SIZE)+1]){
    		xUser = xUser + CELL_SIZE;
    		}
=======
    		//...e o limite direito for atingido, mantemos o boneco..
    		if(x_bloco == 950) {
    			x_bloco = 950;
    		}
    		// ...do contrario, movemos o boneco para a direita!
    		else{
    		x_bloco = x_bloco + CELL_SIZE;
    		}
    		// ...e redesenhamos a tela.
>>>>>>> 37d5f2172c7f29a468c49a1c2d2f0085a980db9d
    		repaint();

    		
    	}
<<<<<<< HEAD
    	if(key == KeyEvent.VK_UP) {
    		if(yUser == 0){
    			yUser = 0;
    		}
    		else if(labyrinth[(yUser/CELL_SIZE)-1][xUser/CELL_SIZE]){
    		yUser = yUser - CELL_SIZE;
    		}
    		repaint();

    	}
    	if(key == KeyEvent.VK_DOWN) {
    		if(yUser == (this.height-1)*CELL_SIZE){
    			yUser = (this.height-1)*CELL_SIZE;
    		}
    		else if(labyrinth[(yUser/CELL_SIZE)+1][xUser/CELL_SIZE]){
    		yUser = yUser + CELL_SIZE;
    		}
=======
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
>>>>>>> 37d5f2172c7f29a468c49a1c2d2f0085a980db9d
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

