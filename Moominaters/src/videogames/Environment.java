package videogames;

import java.awt.Point;
import java.awt.Rectangle;


public class Environment{
	private Rectangle[][][] platforms;
	private Rectangle[][] exits;
	private Point init[];
	private Rectangle noExit;
	
	public Environment(){
		noExit = new Rectangle(0,0,0,0);
		init = new Point[10];
		init[0] = new Point(100,800);
		init[1] = new Point(0,490);
		platforms = new Rectangle[10][2][20];
		platforms[0][0][0] = new Rectangle(0,Globals.height-80,Globals.width,80);
		platforms[0][1][0] = new Rectangle(1522,915,90,Globals.height-915); 
		platforms[0][1][1] = new Rectangle(1612,828,114,Globals.height-828); 
		platforms[0][1][2] = new Rectangle(1726,739,93,Globals.height-739); 
		platforms[0][1][3] = new Rectangle(1819,647,Globals.width-1819,Globals.height-647); 
		
		platforms[1][0][0] = new Rectangle(145,960,Globals.width-145,Globals.height-960);
		platforms[1][0][1] = new Rectangle(0,490,145,Globals.height-490);
		platforms[1][0][2] = new Rectangle(1780,470,Globals.width-1780,Globals.height-470);
		platforms[1][1][0] = new Rectangle(145,960,Globals.width-145,Globals.height-960);
		platforms[1][1][1] = new Rectangle(0,490,145,Globals.height-490);

		
		exits = new Rectangle[10][2];
		exits[0][0] =  noExit;
		exits[0][1] = new Rectangle(1800,350,Globals.width-1800,300);
		exits[1][0] = new Rectangle(1780,141,Globals.width-1780,320);
		exits[1][1] = noExit;
		
	}
	
	public Point getInit(int level){
		return init[level];
	}
	
	public int isTop(int level, int universe, Point p1, Point p2){
		for(int i = 0; platforms[level][universe][i] != null; i++){
			if((platforms[level][universe][i].getMinX() < p1.x && platforms[level][universe][i].getMaxX() > p1.x) ||
					platforms[level][universe][i].getMinX() < p2.x && platforms[level][universe][i].getMaxX() > p2.x){
				if(platforms[level][universe][i].getMinY() < p1.y  && platforms[level][universe][i].getMinY() > p1.y-20){
					return (int)platforms[level][universe][i].getMinY();
				}
			}
		}
		return 0;
	}
	
	public int isLeft(int level, int universe, Point p1, Point p2){
		for(int i = 0; platforms[level][universe][i] != null; i++){
			if((platforms[level][universe][i].getMinX() > p2.x-10 && platforms[level][universe][i].getMinX() < p2.x)){
				if(platforms[level][universe][i].getMinY() < p1.y  && platforms[level][universe][i].getMaxY() > p1.y){
					return (int)platforms[level][universe][i].getMinX();
				}
			}
		}
		return 0;
	}
	
	public int isRight(int level, int universe, Point p1, Point p2){
		for(int i = 0; platforms[level][universe][i] != null; i++){
			if((platforms[level][universe][i].getMaxX() > p1.x && platforms[level][universe][i].getMaxX() < p1.x+10)){
				if(platforms[level][universe][i].getMinY() < p1.y  && platforms[level][universe][i].getMaxY() > p1.y){
					return (int)platforms[level][universe][i].getMaxX();
				}
			}
		}
		return 0;
	}

	
	
	public boolean checExit(int level, int universe, Point p1){
		if(exits[level][universe].contains(p1))
			return true;
		
		return false;
	}
}
