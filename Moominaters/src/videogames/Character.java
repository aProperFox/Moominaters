package videogames;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class Character {
	
	
	public Environment env;
	
	public Rectangle character;

	private Image[] moomin;
	private Image[] moominl;  
	private Image moominCurr;
	
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

	private double dx,dy;
	
	private boolean isCtrl;
	private Boolean hasJumped;
	private Boolean frozen;
	private Boolean objectsDefined;

	
	public Character(){
		isCtrl = false;
		env = new Environment();
		hasJumped = true;
		
		glass = new Magnify();
		
		moomin = new Image[7];
		moominl = new Image[7];
		
		ImageIcon ii = new ImageIcon("src/Sprites/muminright1.png");
		moomin[0] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminright2.png");
		moomin[1] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminright3.png");
		moomin[2] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminright4.png");
		moomin[3] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminright5.png");
		moomin[4] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminright6.png");
		moomin[5] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminjumpr.png");
		moomin[6] = ii.getImage();
		moominCurr = moomin[0];
		dir = 1;
		
		ii = new ImageIcon("src/Sprites/muminleft1.png");
		moominl[0] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleft2.png");
		moominl[1] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleft3.png");
		moominl[2] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleft4.png");
		moominl[3] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleft5.png");
		moominl[4] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminleft6.png");
		moominl[5] = ii.getImage();
		ii = new ImageIcon("src/Sprites/muminjumpl.png");
		moominl[6] = ii.getImage();
		
		chacWidth = Globals.width/13;
		chacHeight = Globals.height/6;
		Globals.chacHeight = chacHeight;
		Globals.chacWidth = chacWidth;
		x = env.getInit(level).x;
		y = env.getInit(level).y;
		p1 = new Point(x +(chacWidth/4),y+chacHeight);
		p2 = new Point(x +(3*chacWidth/4),y+chacHeight);
		dx = 0;
		dy = 0;
		center = new Point(p1.x+chacWidth/2,p1.y+(chacHeight/2));
		frozen = false;
		imageCurr = 0;
		 
		defineObjects();
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
	
   public void move() {
	   if(!frozen){
		p1 = new Point(x +(chacWidth/3),y+chacHeight);
		p2 = new Point(x +(2*chacWidth/3),y+chacHeight);
		center = new Point(x+chacWidth/2,y+(chacHeight/2));
		
        x += dx;
        y += dy - (Globals.gravity)/2;
	   
        if(x >= Globals.width-chacWidth){
        	x = Globals.width-chacWidth;	
        }
        else if(x <= 0)
        	x = 0;
    	int temp;
        if((temp = env.isTop(level, universe, p1, p2) )!= 0){
        	y = temp-chacHeight;
    	    dy = Globals.gravity/2;
    	    if(hasJumped){
			    if(dir == 1)
			        moominCurr = moomin[imageCurr];
			    else
				    moominCurr = moominl[imageCurr];
    	    }
    	    hasJumped = false;
        }
        else
            dy += 0.10;
        if((temp = env.isLeft(level, universe, p1, p2))!= 0){
        	x = temp - (2*chacWidth/3);
        }
        if((temp = env.isRight(level,universe,p1,p2)) != 0){
        	x = temp - (chacWidth/3);
        }
        if(hasJumped == true){
        	if(dir == 1)
        		moominCurr = moomin[6];
        	else
        		moominCurr = moominl[6];
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

        if (key == KeyEvent.VK_A) {
            dx = -1;
            moominCurr = moominl[imageCurr];
            imageCurr += 1;
            dir = 0;
        }

        else if (key == KeyEvent.VK_D) {
            dx = 1;
            moominCurr = moomin[imageCurr];
            imageCurr += 1;
            dir = 1;
        }

        if (key == KeyEvent.VK_W) {
            if(dir == 0)
            	moominCurr = moominl[6];
            else
            	moominCurr = moomin[6];

        }

        else if (key == KeyEvent.VK_S) {
        }
       
        imageCurr = imageCurr % 5 + 1;
        
        if (key == KeyEvent.VK_SPACE){
        	if(hasJumped == false){
	        	y -= 10;
	        	dy = -10;
	        	hasJumped = true;
        	}
        }
        
        if (key == KeyEvent.VK_ENTER){
        	x = env.getInit(level).x;
        	y = env.getInit(level).y-chacHeight;
        	dy = 0;
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

        if (key == KeyEvent.VK_A) {
            dx = 0;
            moominCurr= moominl[0];
        }

        if (key == KeyEvent.VK_D) {
            dx = 0;
            moominCurr = moomin[0];
        }

        if (key == KeyEvent.VK_W) {
            if(dir == 0)
            	moominCurr = moominl[0];
            else
            	moominCurr = moomin[0];
        }

        if (key == KeyEvent.VK_S) {
           //dy = 0;
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
    	}
    	
    	if(button == MouseEvent.BUTTON2){
    	}
    	if(button == MouseEvent.BUTTON3){
    	}
    }
    
    public void mouseReleased(MouseEvent m){
    	int button = m.getButton();

    	if(button == MouseEvent.BUTTON1){
    	}
    	
    	if(button == MouseEvent.BUTTON2){

    	}

    }
    
}
