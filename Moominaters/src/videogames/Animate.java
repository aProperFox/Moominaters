package videogames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Animate {
	private RainDrop[] drops, water;
	private int dropAmount, amount;
	private int universe, level;
	private Image[] moving, moving1;
	private Image[][] backgrounds;
	private int sizeX, sizeY;
	private int drawCurr, drawCurr1;
	private int rotate, rotate1;
	private int repitition;
	
	private boolean test;
	private boolean neg, neg1;
	private boolean init, init1;
	
	public Animate(){
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
	
	public void drawEnv(Graphics g, int l, int u){
		if(l == 0){
			if(u == 0){
				//Only run when initializing
				if(!init){
					moving = new Image[21];
					ImageIcon ii = new ImageIcon("src/Environment/tree0-0--5.png");
					moving[0] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-0--4.png");
					moving[1] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-0--3.png");
					moving[2] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-0--2.png");
					moving[3] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-0--1.png");
					moving[4] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-0-0.png");
					moving[5] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-0-1.png");
					moving[6] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-0-2.png");
					moving[7] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-0-3.png");
					moving[8] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-0-4.png");
					moving[9] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-0-5.png");
					moving[10] = ii.getImage();
					sizeX = ii.getIconWidth();
					sizeY = ii.getIconHeight();
					drawCurr = 0;
					init = true;
				}
			    g.drawImage(backgrounds[l][u], 0, 0, null);
				g.drawImage(moving[drawCurr], 555, 0, sizeX, sizeY, null);
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
					moving1 = new Image[10];
					ImageIcon ii = new ImageIcon("src/Environment/tree0-1--4.png");
					moving1[0] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-1--3.png");
					moving1[1] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-1--2.png");
					moving1[2] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-1--1.png");
					moving1[3] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-1-0.png");
					moving1[4] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-1-1.png");
					moving1[5] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-1-2.png");
					moving1[6] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-1-3.png");
					moving1[7] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-1-4.png");
					moving1[8] = ii.getImage();
					ii = new ImageIcon("src/Environment/tree0-1-5.png");
					moving1[9] = ii.getImage();
					sizeX = ii.getIconWidth();
					sizeY = ii.getIconHeight();
					drawCurr1 = 0;
					init1 = true;
				}
			    g.drawImage(backgrounds[l][u], 0, 0, null);
				g.drawImage(moving1[drawCurr1], 555, 0, sizeX, sizeY, null);
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
					moving = new Image[2];
					ImageIcon ii = new ImageIcon("src/Environment/pump1-0-0.png");
					moving[0] = ii.getImage();
					ii = new ImageIcon("src/Environment/pump1-0-1.png");
					moving[1] = ii.getImage();
					sizeX = ii.getIconWidth();
					sizeY = ii.getIconHeight();
					drawCurr = 0;
					init = true;
				}
			    g.drawImage(backgrounds[l][u], 0, 0, null);
				g.drawImage(moving[drawCurr], 1325, 606, sizeX, sizeY, null);

			}
			else{
				if(!init1){
					moving1 = new Image[2];
					ImageIcon ii = new ImageIcon("src/Environment/pump1-1-0.png");
					moving1[0] = ii.getImage();
					ii = new ImageIcon("src/Environment/pump1-1-1.png");
					moving1[1] = ii.getImage();
					sizeX = ii.getIconWidth();
					sizeY = ii.getIconHeight();
					init1 = true;
				}
			    g.drawImage(backgrounds[l][u], 0, 0, null);
				g.drawImage(moving1[drawCurr], 1325, 606, sizeX, sizeY, null);
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
