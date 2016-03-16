public class Crumb {
	private int x;
	private int y;
	private int pass;
		
	public Crumb(int xMazeRunner, int yMazeRunner){
		this.x = xMazeRunner;
		this.y = yMazeRunner;
		pass = 0;	
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void addPass(){
		pass++;
	}
	public int getPasses(){
		return pass;
	}
}
