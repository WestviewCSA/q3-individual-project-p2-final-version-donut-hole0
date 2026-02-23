import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runner{
	
	private char[][][] map;
	
	public static void main(String[] arg) {
		Runner m = new Runner();
	}
	
	public Runner() {
//		readCoordBased("easyMap1coords.txt");
//		printMap();
		
		
		readMapBased("impossibleMap1.txt");
		printMap();
	}
	
	public void printMap() {
		for (int z = 0; z < map.length; z++) {
	        System.out.println("  Level " + z);
	        for (int y = 0; y < map[z].length; y++) {
	            for (int x = 0; x < map[z][y].length; x++) {
	                System.out.print(map[z][y][x] + " ");
	            }
	            System.out.println();
	        }
	        System.out.println();
	    }
	}
	
	public void readMapBased(String filename) {
		File file = new File(filename);
		try {
			Scanner s = new Scanner(file);
			int rows = s.nextInt();
			int cols = s.nextInt();
			int levels = s.nextInt();
			s.nextLine();
			map = new char[levels][rows][cols];
			for(int z = 0; z<levels; z++) {
				for(int y = 0; y<rows; y++) {
					String line = s.nextLine();
					for(int x = 0; x<cols; x++) {
						map[z][y][x]= line.charAt(x);
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readCoordBased(String filename) {
		File file = new File(filename);
		try {
			Scanner s = new Scanner(file);
			int rows = s.nextInt();
			int cols = s.nextInt();
			int levels = s.nextInt();
			
			s.nextLine();
			map = new char[levels][rows][cols];
			for (int z = 0; z < levels; z++) {
			    for (int y = 0; y < rows; y++) {
			    	for(int x = 0; x<cols; x++) {
			    		map[z][y][x] = '.';
			    		}
			    	}
			    }
			while(s.hasNext()) {
				String type = s.next();
				int row = s.nextInt();
				int col = s.nextInt();
				int level = s.nextInt();
				
				map[level][row][col] = type.charAt(0);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

