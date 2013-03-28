package videogames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Environment{
	private Rectangle[][][] platforms;
	private Rectangle[][] exits;
	private Rectangle[][] interactive;
	private Rectangle[][] push;
	private Point initial[];
	private Rectangle noop;
	
	private RainDrop[] drops, water;
	private int dropAmount, amount;
	private int universe, level;
	private Image[] []moving, moving1;
	private Image[][] backgrounds;
	private int [] sizeX, sizeY;
	private int drawCurr, drawCurr1;
	private int rotate, rotate1;
	private int repitition;
	private Point [] movePlat;
	private boolean test;
	private boolean neg, neg1;
	private boolean init, init1;
	
	private int[][][] dx, dy;
	
	public Environment(){
		backgrounds = new Image[10][2];
		universe = 0;
		level = 0;
	    ImageIcon ii = new ImageIcon("src/Backgrounds/backdrop0-0.png");
	    backgrounds[0][0] = ii.getImage();
	    ii = new ImageIcon("src/Backgrounds/backdrop0-1.png");
	    backgrounds[0][1] = ii.getImage();
	    ii = new ImageIcon("src/Backgrounds/backdrop1-0.png");
	    backgrounds[1][0] = ii.getImage();
	    ii = new ImageIcon("src/Backgrounds/backdrop1-1.png");
	    backgrounds[1][1] = ii.getImage();
		Globals.height = ii.getIconHeight();
		Globals.width = ii.getIconWidth();
		
		repitition = 0;
		dropAmount = 200;
		initRain(dropAmount);
		neg = false;
		neg1 = false;
		init = false;
		init1 = false;
		test = false;
		amount = 0;
		movePlat = new Point[1];

		dx = new int[10][2][20];
		dy = new int[10][2][20];

		noop = new Rectangle(0,0,0,0);
		initial = new Point[10];
		initial[0] = new Point(100,800);
		initial[1] = new Point(0,320);
		initial[2] = new Point(0,0);
		
		platforms = new Rectangle[10][2][20];
		platforms[0][0][0] = new Rectangle(0,Globals.height-80,Globals.width,80);
		dx[0][0][0] = 0;
		dy[0][0][0] = 0;
		platforms[0][1][0] = new Rectangle(1522,915,90,Globals.height-915); 
		dx[0][1][0] = 0;
		dy[0][1][0] = 0;
		platforms[0][1][1] = new Rectangle(1612,828,114,Globals.height-828); 
		dx[0][1][1] = 0;
		dy[0][1][1] = 0;
		platforms[0][1][2] = new Rectangle(1726,739,93,Globals.height-739); 
		dx[0][1][2] = 0;
		dy[0][1][2] = 0;
		platforms[0][1][3] = new Rectangle(1819,647,Globals.width-1819,Globals.height-647); 
		dx[0][1][3] = 0;
		dy[0][1][3] = 0;
		
		platforms[1][0][0] = new Rectangle(145,960,385,Globals.height-960);
		dx[1][0][0] = 0;
		dy[1][0][0] = 0;
		platforms[1][0][1] = new Rectangle(0,320,145,Globals.height-320);
		dx[1][0][1] = 0;
		dy[1][0][1] = 0;
		platforms[1][0][2] = new Rectangle(1780,470,Globals.width-1780,Globals.height-470);
		dx[1][0][2] = 0;
		dy[1][0][2] = 0;
		platforms[1][0][3] = new Rectangle(200,200,260,260);
		dx[1][0][3] = 2;
		dy[1][0][3] = 2;
		platforms[1][1][0] = new Rectangle(145,960,385,Globals.height-960);
		dx[1][1][0] = 0;
		dy[1][1][0] = 0;
		platforms[1][1][1] = new Rectangle(0,320,145,Globals.height-320);
		dx[1][1][1] = 0;
		dy[1][1][1] = 0;

		
		exits = new Rectangle[10][2];
		exits[0][0] = noop;
		exits[0][1] = new Rectangle(1800,350,Globals.width-1800,300);
		exits[1][0] = new Rectangle(1780,141,Globals.width-1780,320);
		exits[1][1] = noop;
		exits[2][0] = noop;
		exits[2][1] = noop;
		exits[3][0] = noop;
		exits[3][1] = noop;
		exits[4][0] = noop;
		
		//Interactive are rectangles that can be modified like the pump
		interactive = new Rectangle[10][2];
		interactive[0][0] = noop;
		interactive[0][1] = noop;
		interactive[1][0] = new Rectangle(1325,606,360,336);
		interactive[1][1] = noop;
		
		//Push are rectangles that modify your velocity
		push = new Rectangle[10][2];
		push[0][0] = noop;
		push[0][1] = noop;
		push[1][0] = noop;
		push[1][1] = new Rectangle(1600,400,100,320);
		push[2][0] = noop;
		push[2][1] = noop;
		push[3][0] = noop;
		push[3][1] = noop;
		
	}
	//Gets the pre-determined initial location values
	public Point getInit(int level){
		return initial[level];
	}
	
	//These three functions return a value that modifies your location if you are touching
	//a platform. If you're not, then return is 0
	public int [] isTop(int level, int universe, Point p1, Point p2){
		int [] ret = new int [2];
		ret[0] = ret[1] = 0;
		for(int i = 0; platforms[level][universe][i] != null; i++){
			if((platforms[level][universe][i].getMinX() < p1.x && platforms[level][universe][i].getMaxX() > p1.x) ||
					platforms[level][universe][i].getMinX() < p2.x && platforms[level][universe][i].getMaxX() > p2.x){
				if(platforms[level][universe][i].getMinY() < p1.y  && platforms[level][universe][i].getMinY() > p1.y-20){
					ret[0] = dx[level][universe][i];
					ret[1] = (int)platforms[level][universe][i].getMinY();
					return ret;
				}
			}
		}
		return ret;
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

	public void update(int level, int universe, Point p){

		if(level == 1){
			if(universe == 0){
				platforms[level][universe][3] = new Rectangle(p.x,p.y+70,260,130);
			}
		}
	}
	
	public boolean checExit(int level, int universe, Point p1){
		if(exits[level][universe].contains(p1))
			return true;
		
		return false;
	}
	
	public boolean checkInter(int level, int universe, Point p1){
		if(interactive[level][universe].contains(p1)){
			return true;
		}
		return false;
	}
	
	public boolean isInside(int level, int universe, Point p1){
		if(push[level][universe].contains(p1)){
			return true;
		}
		return false;
	}
	
	//Paint Rain and call drawEnv
		public void paint(Graphics g){
			drawEnv(g,level,universe);
			g.setColor(new Color(0,0,0));
			for(int i = 0; i < dropAmount; i++){
				g.drawLine(drops[i].getX(), drops[i].getY(), drops[i].getX()+drops[i].getAng(), drops[i].getY()+drops[i].getSize());
			}
			for(int i = 0; i < amount; i++){
				g.drawLine(water[i].getX(), water[i].getY(), water[i].getX()+water[i].getAng(), water[i].getY()+water[i].getSize());
			}

		}
		//Update Raindrops
		public void update(){
			for(int i = 0; i < dropAmount; i++){
				drops[i].setY(drops[i].getY()+30);
				drops[i].setX(drops[i].getX()+drops[i].getAng());
				if(drops[i].getY() > Globals.height){
					drops[i].setX((int) (Math.random() * Globals.width));
					drops[i].setY(0);
				}
			}
			if(universe == 0){
				for(int i = 0; i < amount; i++){
					water[i].setY(water[i].getY()+(repitition*5));
					if(water[i].getY() > Globals.height){
						water[i].setX((int) (Math.random() * 20 + 1655));
						water[i].setY((int) (Math.random() * 50 + 818));
					}
				}
			}
			else{
				for(int i = 0; i < amount; i++){
					water[i].setY(water[i].getY()-(int)(((double)water[i].getY()-400.0)/50.0) - (repitition*10));
					if(water[i].getY() < 400){
						water[i].setX((int) (Math.random() * 20 + 1655));
						water[i].setY((int) (720- Math.random() * 50));
					}
				}
			}

		}
		//Run when starting new level or pressing Enter
		public void init(){
			neg = false;
			neg1 = false;
			init = false;
			init1 = false;
			test = false;
			amount = 0;
			repitition = 0;
		}
		
		public void initRain(int dropAmount){
			drops = new RainDrop[dropAmount];
			for(int i = 0; i < dropAmount; i++){
				drops[i] = new RainDrop((int) (Math.random() * 10 + 7),(int) (Math.random() * Globals.width),
						(int) (Math.random() * 3), (int) (Math.random() * Globals.height));
			}
		}
		
		public void setTest(){
			test = !test;
		}
		
		public boolean getTest(){
			return test;
		}
		
		public void changeLevel(int level){
			this.level = level;
			init = false;
			init1 = false;
			test = false;
			repitition = 0;
		}
		//Function to update pump
		public void repeat(int r){
			repitition +=r;
			drawCurr = 1 - drawCurr;
			drawCurr1 = 1 - drawCurr1;
			amount = repitition*10;
			initWater(amount);
		}
		//Variable change for pump
		public int getRepitition(){
			return repitition;
		}
		
		public void changeUniverse(int universe){
			this.universe = universe;
		}
		
		//Water for pump
		public void initWater(int amount){
			water = new RainDrop[amount];
			if(universe == 0){
				for(int i = 0; i < amount; i++){
					water[i] = new RainDrop((int) (Math.random() * 10 + 7),(int) (Math.random() * 20 + 1655),
							0, (int) (Math.random() * 360 + 720));
				}
			}
			else{
				for(int i = 0; i < amount; i++){
					water[i] = new RainDrop((int) (Math.random() * 10 + 7),(int) (Math.random() * 20 + 1655),
							0, (int) (Math.random() * 320 + 400));
				}
			}
		}
		
		public Point getMovePlat(int num){
			return movePlat[num];
		}
		
		
		public void drawEnv(Graphics g, int l, int u){
			if(l == 0){
				if(u == 0){
					//Only run when initializing
					if(!init){
						moving = new Image[1][21];
						ImageIcon ii = new ImageIcon("src/Environment/tree0-0--5.png");
						moving[0][0] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-0--4.png");
						moving[0][1] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-0--3.png");
						moving[0][2] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-0--2.png");
						moving[0][3] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-0--1.png");
						moving[0][4] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-0-0.png");
						moving[0][5] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-0-1.png");
						moving[0][6] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-0-2.png");
						moving[0][7] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-0-3.png");
						moving[0][8] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-0-4.png");
						moving[0][9] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-0-5.png");
						moving[0][10] = ii.getImage();
						sizeX = new int[1];
						sizeX[0] = ii.getIconWidth();
						sizeY = new int[1];
						sizeY[0] = ii.getIconHeight();
						drawCurr = 0;
						init = true;
					}
				    g.drawImage(backgrounds[l][u], 0, 0, null);
					g.drawImage(moving[0][drawCurr], 555, 0, sizeX[0], sizeY[0], null);
					if(rotate == 0){
						if(neg){
							if(drawCurr == 0){
								neg = false;
								drawCurr +=1;							
							}
							else{
								drawCurr -= 1;
							}
						}
						else{
							if(drawCurr == 10){
								neg = true;
								drawCurr -= 1;
							}
							else{
								drawCurr += 1;
							}
						}
						if(((int) (Math.random() * 20)) == 1)
							neg = !neg;
					}

					rotate = (rotate + 1 )% 15;
					
				}
				else{
					if(!init1){
						moving1 = new Image[1][10];
						ImageIcon ii = new ImageIcon("src/Environment/tree0-1--4.png");
						moving1[0][0] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-1--3.png");
						moving1[0][1] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-1--2.png");
						moving1[0][2] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-1--1.png");
						moving1[0][3] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-1-0.png");
						moving1[0][4] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-1-1.png");
						moving1[0][5] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-1-2.png");
						moving1[0][6] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-1-3.png");
						moving1[0][7] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-1-4.png");
						moving1[0][8] = ii.getImage();
						ii = new ImageIcon("src/Environment/tree0-1-5.png");
						moving1[0][9] = ii.getImage();
						sizeX = new int[1];
						sizeX[0] = ii.getIconWidth();
						sizeY = new int[1];
						sizeY[0] = ii.getIconHeight();
						drawCurr1 = 0;
						init1 = true;
					}
				    g.drawImage(backgrounds[l][u], 0, 0, null);
					g.drawImage(moving1[0][drawCurr1], 555, 0, sizeX[0], sizeY[0], null);
					if(rotate1 == 0){
						if(neg1){
							if(drawCurr1 == 0){
								neg1 = false;
								drawCurr1 +=1;							
							}
							else{
								drawCurr1 -= 1;
							}
						}
						else{
							if(drawCurr1 == 9){
								neg1 = true;
								drawCurr1 -= 1;
							}
							else{
								drawCurr1 += 1;
							}
						}
					}

					rotate1 = (rotate1 + 1 )% 12;
					
				}
			}
			else if(l == 1){
				if(u == 0){
					if(!init){
						moving = new Image[2][2];
						ImageIcon ii = new ImageIcon("src/Environment/pump1-0-0.png");
						moving[0][0] = ii.getImage();
						ii = new ImageIcon("src/Environment/pump1-0-1.png");
						moving[0][1] = ii.getImage();
						sizeX = new int[2];
						sizeY = new int[2];
						sizeX[0] = ii.getIconWidth();
						sizeY[0] = ii.getIconHeight();
						ii = new ImageIcon("src/Environment/plane1-0-0.png");
						moving[1][0] = ii.getImage();
						ii = new ImageIcon("src/Environment/plane1-0-1.png");
						moving[1][1] = ii.getImage();
						sizeX[1] = ii.getIconWidth();
						sizeY[1] = ii.getIconHeight();
						movePlat = new Point[1];
						movePlat[0] = new Point(140,260);
						drawCurr = 0;
						drawCurr1 = 0;
						dx[level][universe][3] = 2;
						dy[level][universe][3] = 1;
						init = true;
						neg = false;
					}
				    g.drawImage(backgrounds[l][u], 0, 0, null);
					g.drawImage(moving[0][drawCurr], 1325, 606, sizeX[0], sizeY[0], null);
					g.drawImage(moving[1][drawCurr1], movePlat[0].x, movePlat[0].y, sizeX[1], sizeY[1], null);
					if(neg){
						drawCurr1 = 1;
						if(movePlat[0].x >= 140){
							movePlat[0].x += dx[level][universe][3];
							movePlat[0].y += dy[level][universe][3];
						}
						else{
							neg = false;
							dx[level][universe][3]*= -1;
							dy[level][universe][3] *= -1;
							movePlat[0].x += dx[level][universe][3];
							movePlat[0].y += dy[level][universe][3];
						}
					}
					else{
						drawCurr1 = 0;
						if(movePlat[0].x <= 1200){
							movePlat[0].x += dx[level][universe][3];
							movePlat[0].y += dy[level][universe][3];
						}
						else{
							neg = true;
							dx[level][universe][3] *= -1;
							dy[level][universe][3] *= -1;
							movePlat[0].x += dx[level][universe][3];
							movePlat[0].y += dy[level][universe][3];
						}
					}
				}
				else{
					if(!init1){
						moving1 = new Image[1][2];
						ImageIcon ii = new ImageIcon("src/Environment/pump1-1-0.png");
						moving1[0][0] = ii.getImage();
						ii = new ImageIcon("src/Environment/pump1-1-1.png");
						moving1[0][1] = ii.getImage();
						sizeX[0] = ii.getIconWidth();
						sizeY[0] = ii.getIconHeight();
						init1 = true;
					}
				    g.drawImage(backgrounds[l][u], 0, 0, null);
					g.drawImage(moving1[0][drawCurr], 1325, 606, sizeX[0], sizeY[0], null);
				}
			}
			else if(l == 2){
				if(u == 0){
					if(!init){
						
					}
				}
				else{
					if(!init1){
						
					}
				}
			}
			
		}
		
}
