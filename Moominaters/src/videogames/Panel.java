package videogames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel  extends JPanel implements ActionListener{

	private Timer timer;
	private Character chac;
	private Image[][] img;
	private Image[] rain;
	private int curRain;
	private Boolean mag, frozen;
	private int level, universe;

	private Magnify magnify;
	
	public Panel(){
		img = new Image[10][2];
		rain = new Image[3];
		magnify = new Magnify();
		mag = frozen = false;
	    addKeyListener(new TAdapter());
	    addMouseListener(new MAdapter());
	    setFocusable(true);
	    setDoubleBuffered(true);
	    ImageIcon ii = new ImageIcon("src/Backgrounds/backdrop0-0.png");
	    img[0][0] = ii.getImage();
	    ii = new ImageIcon("src/Backgrounds/backdrop0-1.png");
	    img[0][1] = ii.getImage();
		Globals.height = ii.getIconHeight();
		Globals.width = ii.getIconWidth();
		
	    ii = new ImageIcon("src/Backgrounds/rain1.png");
	    rain[0] = ii.getImage();
	    ii = new ImageIcon("src/Backgrounds/rain2.png");
	    rain[1] = ii.getImage();
	    ii = new ImageIcon("src/Backgrounds/rain3.png");
	    rain[2] = ii.getImage();

	    
		level = 0;
		universe = 0;
		
		curRain = 0;
        chac = new Character();
	    timer = new Timer(4,this);
	    timer.start();
	    }  

	public void paintComponent(Graphics page)
	{
	    super.paintComponent(page);
	    page.drawImage(img[level][universe], 0, 0, null);

	}
	
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics g2d = (Graphics2D) g;
		//Draw Moomin
		g2d.drawImage(chac.getMoomin(),chac.getX(),chac.getY(),Globals.chacWidth,Globals.chacHeight,null);
		g2d.drawImage(rain[curRain],0,0,Globals.width,Globals.height,null);
		curRain = (curRain + 1)%3;
		//Get mouse info
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
		
		//Magnifying glass
		if(mag){
			Ellipse2D circle = new Ellipse2D.Float(x-25,y-25,80,80);
			g2d.setClip(circle);
			g2d.drawImage(img[level][1-universe], 0,0, Globals.width,Globals.height,null);
		}
		g.dispose();
		
	}
	
	
    public void actionPerformed(ActionEvent e) {
        chac.move();
        repaint();  
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
            	}
            	frozen = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_W){
	            if(chac.env.checExit(level, universe, chac.getCenter())){
	            	level += 1;
	            	universe = 0;
	            	chac.update(level);
	            }
            }
        }
        

        }
    private class MAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent m){
        	chac.mousePressed(m);
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
