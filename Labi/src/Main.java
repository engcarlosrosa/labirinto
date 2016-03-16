import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFrame;

public class Main {
	
	private static String[][] labyrinthHash;
	private static boolean[][] labyrinth;
	private static boolean[][] labyrinth1;
	
	
	public boolean[][] getLabyrinth() {
		return labyrinth;
	}
	
	public boolean[][] getLabyrinth1(){
		return labyrinth1;
	}



	private static void readFiles(String fileName) throws IOException{
		
			List<String> letters = Files.lines(Paths.get(fileName))
		    //.map(line -> line.split("")).flatMap(Arrays::stream)
		    .collect(Collectors.toList());
			
			int count = 0;
			labyrinthHash = new String[letters.size()][];
			for(String i : letters){
				String[] temp = i.split("");
				labyrinthHash[count] = temp;
				count++;
			}
	}
	
	
	private static void convertToBoolean(){
		labyrinth = new boolean[labyrinthHash.length][labyrinthHash[0].length];
		labyrinth1 = new boolean[labyrinthHash.length][labyrinthHash[0].length];

		for(int j = 0; j != labyrinthHash.length; j++){
			for(int i = 0; i != labyrinthHash[0].length; i++){
				if(labyrinthHash[j][i].startsWith("#")){
					labyrinth[j][i] = false;
					labyrinth1[j][i] = false;
				}
				else{
					labyrinth[j][i] = true;
					labyrinth1[j][i] = true;
				}
			}
		}
	}
	public Main() throws IOException{
		readFiles("labyrinth.txt");
		convertToBoolean();
	}
	
	public static void main(String[] args){
		LabyrinthGenerator.generateLabyrinth();
	javax.swing.SwingUtilities.invokeLater(new Runnable() {            
		public void run() {
			
		try {
			new Main();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("erro");
		}
		
		Screen2 screen;
		try {
			screen = new Screen2();
            JFrame frame = new JFrame("Batman vs Bane");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setContentPane(screen);
            frame.pack();
            frame.setVisible(true);
            frame.addKeyListener(screen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
    }
    });
}
}
