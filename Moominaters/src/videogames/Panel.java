package videogames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class Panel  extends JPanel implements Runnable{

	
	private Character chac;
	private Animate anime;
	
	private Image[][] img;
	private Boolean mag, frozen;
	private int level, universe;
	private int DELAY = 5;
	
	private Magnify magnify;
	
	private Thread animator;
	
	public Panel(){
		anime = new Animate();
		img = new Image[10][2];
		magnify = new Magnify();
		mag = frozen = false;
	    addKeyListener(new TAdapter());
	    addMouseListener(new MAdapter());
	    setFocusable(true);
		setDoubleBuffered(true);
	    
		level = 0;
		universe = 0;
        chac = new Character();
        
	    }  

	
	//Paint background, call animate's draw functions, draw character, handle mag glass
	public void paint(Graphics g){
		super.paint(g);
		Graphics g2d = (Graphics2D) g;
		anime.update();
		anime.paint(g);
		
		//Draw Moomin
		g2d.drawImage(chac.getMoomin(),chac.getX(),chac.getY(),Globals.chacWidth,Globals.chacHeight,null);
		
		//Get mouse info
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
		
		//Magnifying glass
		if(mag){
			Ellipse2D circle = new Ellipse2D.Float(x-25,y-25,80,80);
			g2d.drawOval(x-26,y-26,82,82);
			g2d.setClip(circle);
			anime.drawEnv(g2d,level,1-universe);
		}
		g.dispose();
		
	}
	
	//Called every 5 ms in from run(), is kind of the main loop
    public void animationCycle() {
        chac.move();
    	if(chac.env.isInside(level, universe, chac.getCenter())== true){
    		if(anime.getRepitition() > 0)
    			if(Math.abs(chac.getdY()) < Math.abs((-anime.getRepitition())*2))
    				chac.setdY(-(anime.getRepitition()*2));
    	}
    }



    //Start the thread
    public void addNotify(){
    	super.addNotify();
    	animator = new Thread(this);
    	animator.start();
    }
    
    //only called oncem so the while loop calls functions needed every 5 ms
	public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

        	animationCycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }
    }
		
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            chac.keyReleased(e);
            if(e.getKeyCode() == KeyEvent.VK_SHIFT)
            	frozen = false;
        }

        public void keyPressed(KeyEvent e) {
            chac.keyPressed(e);
            if(e.getKeyCode() == KeyEvent.VK_SHIFT)
            	frozen = true;
            if(e.getKeyCode() == KeyEvent.VK_Q){
            	//If shift is held, switch universes
            	if(frozen) {
            		universe = 1 - universe;
            		chac.setUniverse(universe);
                	anime.changeUniverse(universe);
            	}
            }
            if(e.getKeyCode() == KeyEvent.VK_W){
	            if(chac.env.checExit(level, universe, chac.getCenter())){
	            	level += 1;
	            	universe = 0;
	            	chac.update(level);
	            	anime.changeLevel(level);
	            	anime.changeUniverse(universe);
	            }
            }
            
            if(e.getKeyCode() == KeyEvent.VK_N){
            	level += 1;
            	universe = 0;
            	chac.update(level);
            	anime.changeLevel(level);
            	anime.changeUniverse(universe);
            }
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
            	anime.init();
            }
        }
        

        }
    private class MAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent m){
        	chac.mousePressed(m);
        	Point mouse = new Point(m.getX(),m.getY());
        	//Called for point and click interactivity
        	if(m.getButton() == MouseEvent.BUTTON1){
	            if(chac.env.checkInter(level, universe, mouse)){
	            	if(anime.getRepitition() >= 5){
	            		anime.repeat(0);
	            	}
	            	else
		            	anime.repeat(1);
	            }
        	}
        	if(m.getButton() == MouseEvent.BUTTON2){
        		mag = true;
        	}
        	
        	if(m.getButton() == MouseEvent.BUTTON3){

        	}
        }
        public void mouseReleased(MouseEvent m){
        	chac.mouseReleased(m);
        	if(m.getButton() == MouseEvent.BUTTON2)
        		mag = false;
        }
    }
	
}
