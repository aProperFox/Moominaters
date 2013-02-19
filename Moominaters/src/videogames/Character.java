package videogames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Character {
	public Rectangle character;
	public int chacWidth = 12;
	public int chacHeight = 20;
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
		
		x = 400;
		y = 320;
		dx = 0;
		dy = 0;
		imageCurr = 0;
		 
		defineObjects();
	}
	
	public void defineObjects(){
		character = new Rectangle(Update.width/2 - chacWidth/2,Update.height/2 - chacHeight/2);
	}
	
	public Image getMoomin(){
		return moominCurr;
	}
	
   public void move() {
        x += dx;
        y += dy;
    }
   
   public int getX(){
	   return x;
   }
   
   public int getY(){
	   return y;
   }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
            moominCurr = moominl[imageCurr];
            imageCurr += 1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
            moominCurr = moomin[imageCurr];
            imageCurr += 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
        imageCurr = imageCurr % 6;
    }
	   
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
