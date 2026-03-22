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
		if(readMap(filename)) {
			printMap();
		} else {
			System.out.println("Failed to load map: " + filename);
		}
		
	}
	
	public void printMap() {
		
		int levels = map[0][0].length;
		int rows = map.length;
		int cols = map[0].length;
		
		for (int level = 0; level < levels; level++) {
			System.out.println("Level" + level);
			for(int row = 0; row < rows; row++) {
				for(int col = 0; col < cols; col++) {
					System.out.println(map[row][col][level] + " ");
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
			map = new char[rows][cols][levels];
			for(int level = 0; level<levels; level++) {
				for(int row = 0; row<rows; row++) {
					
					String line = s.nextLine();
					
					if(line.length() < cols) {
						System.out.println("Line too short \nRow: " + row + "\nLevel: "+level);
						return false;
					}
					
					
					for(int col = 0; col<cols; col++) {
						//checking for invalid characters
						char c = line.charAt(col);
						if(c != 'W' && c != '|' && c != '$' && c != '@' && c != '.') {
							System.out.println("Invalid character: '" + c +"' \nPoint: ("+row+","+col+")\nLevel: "+level); 
							return false;
						}
						map[row][col][level]= c;
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
			
			if(rows < 1 || cols < 1 || levels < 1) {
				System.out.println("Filem does not start with 3 positive non-zero numbers");;
				return false;
			}
			
			s.nextLine();
			map = new char[rows][cols][levels];
			
			// filling everything with open pathway
			for (int row = 0; row < rows; row++) {
			    for (int col = 0; col < rows; col++) {
			    	for(int level = 0; level<levels; level++) {
			    		map[row][col][level] = '.';
			    	}
			    }
			}
			while(s.hasNext()) {
				char type = s.next().charAt(0);
				int row = s.nextInt();
				int col = s.nextInt();
				int level = s.nextInt();
				
				if(type != 'W' && type != '|' && type != '$' && type != '@' && type != '.') {
					System.out.println("Invalid character: '" + type + "'\nPoint: (" + row + "," + col + ")\nLevel: " + level);
				}
				
				if(row < 0 || row >= rows || col < 0 || col >= cols || level < 0 || level >= levels) {
					System.out.println("Coordinates out of bounds!\nPoint: (" + row + "," + col + ")\nLevel: " + level);
					return false;
				}
				map[row][col][level] = type;
			}
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public void solveQueue() {
		int rows   = map.length;
	    int cols   = map[0].length;
	    int levels = map[0][0].length;
	    
	    int startRow = -1;
	    int startCol = -1;
	    int startLevel = 0;
	    boolean found = false;
	    
	    
	    for(int level = 0; level < levels; level++) {
	    	for(int row = 0; row < rows; row++) {
	    		for(int col = 0; col < cols; col++) {
	    			if(!found && map[row][col][level] == 'W') {
	    				startRow = row;
	    				startCol = col;
	    				startLevel = level;
	    				found = true;
	    			}
	    			
	    		}
	    	}
	    }
	}
	
	
}

