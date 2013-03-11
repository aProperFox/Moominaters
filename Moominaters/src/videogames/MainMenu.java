package videogames;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainMenu extends JPanel implements Runnable{
	private Rectangle start, settings;
	private boolean isStart;
	private Image img;
	private Thread animator;
	private int DELAY = 50;
	
	public MainMenu(boolean b){
		isStart = b;
		start = new Rectangle(815,370,315,130);
	    addMouseListener(new MAdapter());
	    setFocusable(true);
		setDoubleBuffered(true);
	    
	    ImageIcon ii = new ImageIcon("src/Backgrounds/MainMenu.png");
	    img = ii.getImage();
	    
	}
	
	public boolean getMenu(){
		return isStart;
	}
	
    public void addNotify(){
    	super.addNotify();
    	animator = new Thread(this);
    	animator.start();
    }
	
	public void paint(Graphics g)
	{
	   super.paintComponent(g);
	   g.drawImage(img, 0, 0,Globals.width,Globals.height,null);

	}
	public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {
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
	
	private class MAdapter extends MouseAdapter {
	    public void mousePressed(MouseEvent m){
	      	if(m.getButton() == MouseEvent.BUTTON1){
	       		Point p = new Point(m.getX(),m.getY());
	       		if(start.contains(p)){
	       			isStart = true;
	       		}
	        }
	        	
	       	if(m.getButton() == MouseEvent.BUTTON3){

	       	}
	    }
	    public void mouseReleased(MouseEvent m){
	       	if(m.getButton() == MouseEvent.BUTTON1){
	        		
	       	}
	    }
	}
		
}
