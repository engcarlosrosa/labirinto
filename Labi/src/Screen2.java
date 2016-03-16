import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Stack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	
	private Clip clip;
	
	private int width;
	private int height;
	
	private Stack<Crumb> path = new Stack<>();
	
	private int xUser;
	private int yUser;
	private int xMazeRunner;
	private int yMazeRunner;
	
	private int xObjetivo;
	private int yObjetivo;

	protected Image imageBatman;
	protected Image imageBane;
	
	protected Image imageBatmanWin;
	private boolean batmanWins;
	
	protected Image imageBaneWin;
	private boolean baneWins;
	
	private boolean[][] labyrinth;
	private boolean[][] labyrinth1;
	
	public Screen2() throws IOException {
		Main main = new Main();
		this.labyrinth = main.getLabyrinth();
		this.labyrinth1 = main.getLabyrinth1();

		this.width = this.labyrinth[0].length;
		this.height = this.labyrinth.length;
		
		this.xObjetivo = width - 1;
		this.yObjetivo = height - 1;
		
		xUser = CELL_SIZE;
		yUser = CELL_SIZE;
		
		Crumb crumb = new Crumb(0,0);
		path.add(crumb);

		setPreferredSize(new Dimension(this.width * CELL_SIZE, this.height * CELL_SIZE));
		
		imageBatman = new ImageIcon(getClass().getResource("/BATMAN.png")).getImage();
		imageBatmanWin = new ImageIcon(getClass().getResource("/Logo_Batman.png")).getImage();
		imageBane = new ImageIcon(getClass().getResource("/BanePXL.png")).getImage();
		imageBaneWin = new ImageIcon(getClass().getResource("/banemask.png")).getImage();

		
		
		try{
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(Screen2.class.getResource("Batman.wav"));
		clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();
		}
		catch(UnsupportedAudioFileException ue) {
			System.out.println(ue);
		}
		catch(IOException ioe) {
			System.out.println(ioe);
		}
		catch(LineUnavailableException lun){
			System.out.println(lun);
		}



		
		timer = new Timer(75, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		for(int i = 0; i < this.height; i++) {
			int y = i * CELL_SIZE;

			for(int j = 0; j < this.width; j++) {
				int x = j * CELL_SIZE;

				if(labyrinth1[i][j]) {
					g.setColor(Color.WHITE);
				}
				else {
					g.setColor(Color.BLACK);
				}
				g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
			}
		}
		if(batmanWins){
			g.drawImage(imageBatmanWin, (width/4)*CELL_SIZE, (height/4)*CELL_SIZE,(width*2/3)*CELL_SIZE,(height*2/3)*CELL_SIZE, null);
		}
		else if(baneWins){
			g.drawImage(imageBaneWin, (width/4)*CELL_SIZE, (height/4)*CELL_SIZE,(width*2/3)*CELL_SIZE,(height*2/3)*CELL_SIZE, null);
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
			baneWins = true;
			clip.stop();
			timer.stop();
			repaint();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if((xUser/CELL_SIZE) != xObjetivo || (yUser/CELL_SIZE) != yObjetivo){
	    	int key = e.getKeyCode();
	    	if(key == KeyEvent.VK_LEFT) {
	    		if(xUser == 0) {
	    			xUser = 0;
	    		}
	    		else if(labyrinth1[yUser/CELL_SIZE][(xUser/CELL_SIZE)-1]){
	        		xUser = xUser - CELL_SIZE;
	    		}
	    		repaint();
	    	}
	    	
	    	if(key == KeyEvent.VK_RIGHT) {
	    		if(xUser == (this.width-1)*CELL_SIZE) {
	    			xUser = (this.width-1)*CELL_SIZE;
	    		}
	    		else if(labyrinth1[yUser/CELL_SIZE][(xUser/CELL_SIZE)+1]){
	    		xUser = xUser + CELL_SIZE;
	    		}
	    		repaint();
	
	    		
	    	}
	    	if(key == KeyEvent.VK_UP) {
	    		if(yUser == 0){
	    			yUser = 0;
	    		}
	    		else if(labyrinth1[(yUser/CELL_SIZE)-1][xUser/CELL_SIZE]){
	    		yUser = yUser - CELL_SIZE;
	    		}
	    		repaint();
	
	    	}
	    	if(key == KeyEvent.VK_DOWN) {
	    		if(yUser == (this.height-1)*CELL_SIZE){
	    			yUser = (this.height-1)*CELL_SIZE;
	    		}
	    		else if(labyrinth1[(yUser/CELL_SIZE)+1][xUser/CELL_SIZE]){
	    		yUser = yUser + CELL_SIZE;
	    		}
	    		repaint();
	    	}
		}
		else{
			batmanWins = true;
			timer.stop();
			clip.stop();
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

