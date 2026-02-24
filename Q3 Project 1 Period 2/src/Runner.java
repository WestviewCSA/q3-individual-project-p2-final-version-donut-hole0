import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.sound.sampled.Line;

public class Runner{
	
	private char[][][] map;
	
	public static void main(String[] arg) {
		if (arg.length<1) {
			System.out.println("No file name provided");
		}
		else {
			Runner m = new Runner(arg[0]);
		}
	}
	
	public Runner(String filename) {
//		readCoordBased("easyMap1coords.txt");
//		printMap();
		
		
		if(readMap(filename)) {
			printMap();
		} else {
			System.out.println("Failed to load map: " + filename);
		}
		
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
	
	public boolean readMap(String filename) {
		File file = new File(filename);
		try {
			Scanner s = new Scanner(file);

			int rows = s.nextInt();
			int cols = s.nextInt();
			int levels = s.nextInt();
			
			if(rows < 1 || cols < 1 || levels < 1) {
				System.out.println("File does not start with 3 positive non-zero numbers!");
				return false;
			}
			
			s.nextLine();
			map = new char[levels][rows][cols];
			for(int z = 0; z<levels; z++) {
				for(int y = 0; y<rows; y++) {
					
					String line = s.nextLine();
					
					if(line.length() < cols) {
						System.out.println("Line too short \nRow: " + y + "\nLevel: "+z);
						return false;
					}
					
					
					for(int x = 0; x<cols; x++) {
						
						//checking for invalid characters
						if(line.charAt(x) != 'W'&&line.charAt(x) != '|'
								&&line.charAt(x) != '$'&&line.charAt(x) != '@'&&line.charAt(x) != '.') {
							System.out.println("Invalid character: '" + line.charAt(x) +"' \nPoint: ("+y+","+x+")\nLevel: "+z); 
							return false;
						}
						map[z][y][x]= line.charAt(x);
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean readCord(String filename) {
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
				if(type.charAt(0) != 'W'&&type.charAt(0) != '|'
						&&type.charAt(0) != '$'&&type.charAt(0) != '@'&&type.charAt(0) != '.') {
					System.out.println("Invalid character: '" + type.charAt(0) +"' \nPoint: ("+row+","+col+")\nLevel: "+level); 
					return false;
				}
			}			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
}

