package videogames;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

/* Controls
~WASD to move
~SPACE to jump
~Hold SHIFT and press Q to change universes
~Hold CTRL and click mouse to move yourself (for debugging)
~Hold Middle Mouse Button to view alternate universe
~Click Left Mouse Button to interact
~Press N to change levels
~Press Enter to reload level and properties
*/

public class Character {
	
	
	public Environment env;
	
	public Rectangle character;

	private Image[] moomin;
	private Image[] moominl;  
	private Image moominCurr;
	private Image[] device;
	private Image currDevice;
	
	private int devW, devH;
	private int rotate;
	private int imageCurr;
	public int chacWidth;
	public int chacHeight;
	private int x,y;
	private int mouseX, mouseY;
	private int pastX, pastY;
	private int dir;
	private int level;
	private int universe;
	
	public Magnify glass;


	private Point p1, p2, center;
	private Point devLoc;

	private double dx,dy;
	
	private boolean inc;
	private boolean canDraw;
	private boolean isCtrl;
	private boolean neg;
	private Boolean hasJumped;
	private Boolean frozen;
	private Boolean objectsDefined;

	
	public Character(){
		isCtrl = false;
		env = new Environment();
		hasJumped = true;
		
		glass = new Magnify();
		
		moomin = new Image[11];
		moominl = new Image[11];
		
		device = new Image[10];
		
		ImageIcon ii = new ImageIcon("src/Sprites/muminright-4.png");
		moomin[0] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminright-3.png");
		moomin[1] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminright-2.png");
		moomin[2] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminright-1.png");
		moomin[3] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminright1.png");
		moomin[4] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminright2.png");
		moomin[5] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminright3.png");
		moomin[6] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminright4.png");
		moomin[7] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminjumpr.png");
		moomin[8] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminrightslide.png");
		moomin[9] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminrightattack.png");
		moomin[10] = ii.getImage();
		moominCurr = moomin[0];
		dir = 1;
		
		ii = new ImageIcon("src/Sprites/muminleft-4.png");
		moominl[0] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleft-3.png");
		moominl[1] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleft-2.png");
		moominl[2] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleft-1.png");
		moominl[3] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleft1.png");
		moominl[4] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleft2.png");
		moominl[5] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleft3.png");
		moominl[6] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleft4.png");
		moominl[7] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminjumpl.png");
		moominl[8] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleftslide.png");
		moominl[9] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleftattack.png");
		moominl[10] = ii.getImage();
		
		ii = new ImageIcon("src/Sprites/spatula.png");
		device[0] = ii.getImage();
		currDevice = device[0];
		devW = 23;
		devH = 100;
		
		chacWidth = Globals.width/12;
		chacHeight = Globals.height/6;
		Globals.chacHeight = chacHeight;
		Globals.chacWidth = chacWidth;
		x = env.getInit(level).x;
		y = env.getInit(level).y;
		p1 = new Point(x +(chacWidth/4),y+chacHeight);
		p2 = new Point(x +(3*chacWidth/4),y+chacHeight);
		dx = 0;
		dy = 0;
		devLoc = new Point(x+chacWidth-30,y+14);
		center = new Point(p1.x+chacWidth/2,p1.y+(chacHeight/2));
		frozen = false;
		imageCurr = 4;
		rotate = 0;
		neg = false;
		canDraw = false;
		defineObjects();
		inc = false;
	}
	
	public void defineObjects(){
		character = new Rectangle((Globals.width/2) - (chacWidth/2),(Globals.height/2) - (chacHeight/2),
				chacWidth, chacHeight);
		objectsDefined = true;
	}
	
	public Image getMoomin(){
		return moominCurr;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getUniverse(){
		return universe;
	}
	
	public void update(int level){
		this.level = level;
		universe = 0;
		Point temp = env.getInit(level);
		x = temp.x;
		y = temp.y - chacHeight;
	}
	
	public Point getCenter(){
		return center;
	}
	
	public int getDir(){
		return dir;
	}
	
	public int getDevH(){
		return devH;
	}
	
	public int getDevW(){
		return devW;
	}
	
	public Image getDevice(){
		return currDevice;
	}
	
	public Point getDevLoc(){
		return devLoc;
	}
	
	public int getImageCurr(){
		return imageCurr;
	}
	
	public void draw(){
		rotate += 1;
		if(rotate % 5 == 0){
			rotate = 0;
			if(neg){
				if(imageCurr == 0){
					neg = false;
					imageCurr +=1;							
				}
				else{
					imageCurr -= 1;
				}
			}
			else{
				if(imageCurr == 7){
					neg = true;
					imageCurr -= 1;
				}
				else{
					imageCurr += 1;
				}
			}
			if(dir == 0){
	            moominCurr = moominl[imageCurr];
			}
			else{
	            moominCurr = moomin[imageCurr];
			}
		}
	}
	
   public void move() {
	   if(!frozen){
		p1 = new Point(x +(chacWidth/3),y+chacHeight);
		p2 = new Point(x +(2*chacWidth/3),y+chacHeight);
		center = new Point(x+chacWidth/2,y+(chacHeight/2));
		
		if(canDraw)
			draw();
        x += dx;
        y += dy - (Globals.gravity)/2;
	   
        if(x >= Globals.width-chacWidth){
        	x = Globals.width-chacWidth;	
        }
        else if(x <= 0)
        	x = 0;
    	int [] top = env.isTop(level, universe, p1, p2);
    	int temp;
        if(top[1]!= 0){
        	y = top[1]-chacHeight;
    	    dy = Globals.gravity/2;
    	    if(inc){
    	    	dx += top[0];
    	    	inc = false;
    	    }
    	    if(hasJumped){
			    if(dir == 1)
			        moominCurr = moomin[imageCurr];
			    else
				    moominCurr = moominl[imageCurr];
    	    }
    	    hasJumped = false;
        }
        else{
        	inc = true;
            dy += 0.10;
        }
        if((temp = env.isLeft(level, universe, p1, p2))!= 0){
        	x = temp - (2*chacWidth/3);
        }
        if((temp = env.isRight(level,universe,p1,p2)) != 0){
        	x = temp - (chacWidth/3);
        }
        if(hasJumped == true){
        	if(dir == 1)
        		moominCurr = moomin[8];
        	else
        		moominCurr = moominl[8];
        }
		if(dir == 0){
    		devLoc = new Point(x+11+imageCurr,y+14);
		}
		else{
    		devLoc = new Point(x+chacWidth-30-imageCurr,y+14);
		}
	   }
	   
    }
   
   public int getX(){
	   return x;
   }
   
   public int getY(){
	   return y;
   }
   
   public void setdX(double dx){
	   this.dx = dx;
   }
   
   public void setdY(double dy){
	   this.dy = dy;
   }
   
   public double getdX(){
	   return dx;
   }
   
   public double getdY(){
	   return dy;
   }
   
   
   public int getMouseX(){
	   return mouseX;
   }
   public int getMouseY(){
	   return mouseY;
   }
   
   public void setUniverse(int universe){
	   this.universe = universe;
   }
   
   public void setLevel(int level){
	   this.level = level;
   }
   
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if(!frozen){
	        if (key == KeyEvent.VK_A) {
	        	canDraw = true;
	            dx = -1;
	            dir = 0;
	        }
	
	        else if (key == KeyEvent.VK_D) {
	        	canDraw = true;
	        	dx = 1;
	            dir = 1;
	        }
	
	        if (key == KeyEvent.VK_W) {
	            if(dir == 0)
	            	moominCurr = moominl[4];
	            else
	            	moominCurr = moomin[4];
	
	        }
	
	        else if (key == KeyEvent.VK_S) {
	        }
	        
	        if (key == KeyEvent.VK_ENTER){
	        	x = env.getInit(level).x;
	        	y = env.getInit(level).y-chacHeight;
	        	dy = 0;
	        }
        }
        
        if (key == KeyEvent.VK_SPACE){
        	if(hasJumped == false){
	        	y -= 10;
	        	dy = -10;
	        	hasJumped = true;
        	}
        }
        
        if (key == KeyEvent.VK_SHIFT){
        	frozen = true;
        }
        
        if (key == KeyEvent.VK_Q){
        }
        if (key == KeyEvent.VK_CONTROL){
        	isCtrl = true;
        }


        move();
    }
	   
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(!frozen){
	        if (key == KeyEvent.VK_A) {
	            dx += 1;
	            moominCurr= moominl[4];
	            canDraw = false;
	        }
	
	        if (key == KeyEvent.VK_D) {
	            dx -= 1;
	            moominCurr = moomin[4];
	            canDraw = false;
	        }
	
	        if (key == KeyEvent.VK_W) {
	            if(dir == 0)
	            	moominCurr = moominl[4];
	            else
	            	moominCurr = moomin[4];
	        }
	
	        if (key == KeyEvent.VK_S) {
	           //dy = 0;
	        }
        }
        if (key == KeyEvent.VK_SHIFT){
        	frozen = false;
        }
        
        if (key == KeyEvent.VK_CONTROL){
        	isCtrl = false;
        }
    }
    
    public void mousePressed(MouseEvent m){
    	int button = m.getButton();
    	mouseX = m.getX();
    	mouseY = m.getY();
    	
    	//If control is held, mouse will relocate character, only here for debugging
    	if(button == MouseEvent.BUTTON1){
    		if(isCtrl){
    			x = m.getX()-(chacWidth/2);
    			y = m.getY()-(chacHeight/2);
    			dx = dy = 0;
    		}
    		else{
    			frozen = true;
    			imageCurr = 10;
    			if(dir == 0){
    				devLoc = new Point(x+30,y-10);
        			moominCurr = moominl[imageCurr];
    			}
    			else{
    				devLoc = new Point(x+chacWidth-50,y-10);
        			moominCurr = moomin[imageCurr];
    			}

    		}
    	}
    	
    	if(button == MouseEvent.BUTTON2){
    	}
    	if(button == MouseEvent.BUTTON3){
    	}
    }
    
    public void mouseReleased(MouseEvent m){
    	int button = m.getButton();

    	if(button == MouseEvent.BUTTON1){
    		frozen = false;
    		imageCurr = 4;
			if(dir == 0){
				devLoc = new Point(x+11,y+14);
    			moominCurr = moominl[imageCurr];
			}
			else{
	    		devLoc = new Point(x+chacWidth-30-imageCurr,y+14);
    			moominCurr = moomin[imageCurr];
			}
    	}
    	
    	if(button == MouseEvent.BUTTON2){

    	}

    }
    
}
