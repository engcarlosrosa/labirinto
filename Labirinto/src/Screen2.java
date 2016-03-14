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

	private int width;
	private int height;
	
	private Stack<Crumb> path = new Stack<>();
	
	private int xUser;
	private int yUser;
	private int xMazeRunner;
	private int yMazeRunner;

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
		
		xUser = CELL_SIZE;
		yUser = CELL_SIZE;
		
		Crumb crumb = new Crumb(0,0);
		path.add(crumb);

		setPreferredSize(new Dimension(this.width * CELL_SIZE, this.height * CELL_SIZE));
		
		imageBatman = new ImageIcon(getClass().getResource("/BATMAN.png")).getImage();
		imageBane = new ImageIcon(getClass().getResource("/BanePXL.png")).getImage();
		
		Timer timer = new Timer(100, this);
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
		
		g.drawImage(imageBatman, xUser, yUser,CELL_SIZE,CELL_SIZE, null);
		g.drawImage(imageBane, xMazeRunner, yMazeRunner,CELL_SIZE,CELL_SIZE, null);
    	getToolkit().sync();
    }
	
	public void actionPerformed(ActionEvent e) {
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
	
	@Override
	public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
    	
    	if(key == KeyEvent.VK_LEFT) {
    		if(xUser == 0) {
    			xUser = 0;
    		}
    		if(labyrinth[yUser/CELL_SIZE][(xUser/CELL_SIZE)-1]){
        		xUser = xUser - CELL_SIZE;
    		}
    		repaint();
    	}
    	
    	if(key == KeyEvent.VK_RIGHT) {
    		if(xUser == (this.width-1)*CELL_SIZE) {
    			xUser = (this.width-1)*CELL_SIZE;
    		}
    		if(labyrinth[yUser/CELL_SIZE][(xUser/CELL_SIZE)+1]){
    		xUser = xUser + CELL_SIZE;
    		}
    		repaint();

    		
    	}
    	if(key == KeyEvent.VK_UP) {
    		if(yUser == 0){
    			yUser = 0;
    		}
    		if(labyrinth[(yUser/CELL_SIZE)-1][xUser/CELL_SIZE]){
    		yUser = yUser - CELL_SIZE;
    		}
    		repaint();

    	}
    	if(key == KeyEvent.VK_DOWN) {
    		if(yUser == (this.height-1)*CELL_SIZE){
    			yUser = (this.height-1)*CELL_SIZE;
    		}
    		if(labyrinth[(yUser/CELL_SIZE)+1][xUser/CELL_SIZE]){
    		yUser = yUser + CELL_SIZE;
    		}
    		repaint();
    	}
	}

    @Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}
}

