package videogames;

import java.awt.Point;
import java.awt.Rectangle;

public class Environment{
	private Rectangle[][][] platforms;
	public Environment(){
		platforms = new Rectangle[10][2][20];
		platforms[0][0][0] = new Rectangle(0,Globals.height-80,Globals.width,80); 
		platforms[0][1][0] = new Rectangle(1522,915,90,Globals.height-915); 
		platforms[0][1][1] = new Rectangle(1612,828,114,Globals.height-828); 
		platforms[0][1][2] = new Rectangle(1726,739,93,Globals.height-739); 
		platforms[0][1][3] = new Rectangle(1819,647,Globals.width-1819,Globals.height-647); 
		
	}
	
	public int checkRecs(int level, int universe, Point p1, Point p2){
		
		for(int i = 0; platforms[level][universe][i] != null; i++){
			if(platforms[level][universe][i].contains(p1) || platforms[level][universe][i].contains(p2))
				return (int)platforms[level][universe][i].getMinY();
		}
		
		return 0;
	}
}
