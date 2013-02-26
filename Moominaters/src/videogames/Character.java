package videogames;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class Character {
	public Rectangle character;
	public int chacWidth;
	public int chacHeight;
	private Image[] moomin;
	private Image[] moominl;  
	private Image moominCurr;
	private int dir;
	
	public Magnify glass;
	
	private Boolean hasJumped;
	
	private int x,y;
	private int mouseX, mouseY;
	private int pastX, pastY;
	private double dx,dy;
	
	private int imageCurr;
	
	public Character(){
		hasJumped = false;
		
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
		x = (Globals.width/2) - (Globals.chacWidth/2);
		y = (Globals.height/2) - (Globals.chacHeight/2);

		dx = 0;
		dy = 0;
		imageCurr = 0;
		 
		defineObjects();
	}
	
	public void defineObjects(){
		character = new Rectangle(Globals.width/2 - chacWidth/2,Globals.height/2 - chacHeight/2);
	}
	
	public Image getMoomin(){
		return moominCurr;
	}
	
   public void move() {
        x += dx;
        y += dy - (Globals.gravity)/2;
        if(x >= Globals.width-chacWidth){
        	x = Globals.width-chacWidth;	
        }
        else if(x <= 0)
        	x = 0;
        if(y >= Globals.height-(chacHeight+70)){
        	y = Globals.height-(chacHeight+70);
        	if(hasJumped == true){
        		  dy = 0;
        		  if(dir == 1)
        			  moominCurr = moomin[imageCurr];
        		  else
        			  moominCurr = moominl[imageCurr];
        	}
        }
        else if(y <= 0)
        	y = 0;
        if(dy == 0)
        	hasJumped = false;
        if(hasJumped == true){
        	if(dir == 1)
        		moominCurr = moomin[6];
        	else
        		moominCurr = moominl[6];
        }
        dy += 0.10;
    }
   
   public int getX(){
	   return x;
   }
   
   public int getY(){
	   return y;
   }
   
   public int getMouseX(){
	   return mouseX;
   }
   public int getMouseY(){
	   return mouseY;
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
       
        imageCurr = imageCurr % 6;
        
        if (key == KeyEvent.VK_SPACE){
        	if(hasJumped == false){
	        	y -= 10;
	        	dy = -10;
	        	hasJumped = true;
        	}
        	
        }
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
    }
    
    public void mousePressed(MouseEvent m){
    	int button = m.getButton();
    	mouseX = m.getX();
    	mouseY = m.getY();
    	if(button == MouseEvent.BUTTON1){
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
