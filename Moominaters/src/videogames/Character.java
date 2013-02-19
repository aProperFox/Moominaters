package videogames;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Character {
	public Rectangle character;
	public int chacWidth = 100;
	public int chacHeight = 140;
	private Image[] moomin;
	private Image[] moominl;  
	private Image moominCurr;
	
	
	private int x,y;
	private int dx,dy;
	
	private int imageCurr;
	
	public Character(){
		moomin = new Image[6];
		moominl = new Image[6];
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
		moominCurr = moomin[0];
		
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
        y += dy;
        if(x >= Globals.width-chacWidth){
        	x = Globals.width-chacWidth;	
        }
        else if(x <= 0)
        	x = 0;
        if(y >= Globals.height-chacHeight)
        	y = Globals.height-chacHeight;
        else if(y <= 0)
        	y = 0;
    }
   
   public int getX(){
	   return x;
   }
   
   public int getY(){
	   return y;
   }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = -1;
            moominCurr = moominl[imageCurr];
            imageCurr += 1;
        }

        if (key == KeyEvent.VK_D) {
            dx = 1;
            moominCurr = moomin[imageCurr];
            imageCurr += 1;
        }

        if (key == KeyEvent.VK_W) {
            dy = -1;
        }

        if (key == KeyEvent.VK_S) {
            dy = 1;
        }
        imageCurr = imageCurr % 6;
    }
	   
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = 0;
        }

        if (key == KeyEvent.VK_D) {
            dx = 0;
        }

        if (key == KeyEvent.VK_W) {
            dy = 0;
        }

        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
    }
}
