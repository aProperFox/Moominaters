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
	private Image img;
	private Boolean mag, found;
	

	private Magnify magnify;
	
	public Panel(){
		magnify = new Magnify();
		mag = found = false;
	    addKeyListener(new TAdapter());
	    addMouseListener(new MAdapter());
	    setFocusable(true);
	    setDoubleBuffered(true);
	    ImageIcon ii = new ImageIcon("src/Backgrounds/backdrop1.png");
	    img = ii.getImage();
		Globals.height = ii.getIconHeight();
		Globals.width = ii.getIconWidth();
	    
        chac = new Character();
	    timer = new Timer(4,this);
	    timer.start();
	    }  

	public void paintComponent(Graphics page)
	{
	    super.paintComponent(page);
	    page.drawImage(img, 0, 0, null);

	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics g2d = (Graphics2D) g;
		g2d.drawImage(chac.getMoomin(),chac.getX(),chac.getY(),Globals.chacWidth,Globals.chacHeight,null);
		if(found){
			g2d.drawImage(magnify.getImg(), 0,0, Globals.width,Globals.height,null);
		}
		else if(mag){
			PointerInfo a = MouseInfo.getPointerInfo();
			Point b = a.getLocation();
			int x = (int) b.getX();
			int y = (int) b.getY();
			Ellipse2D circle = new Ellipse2D.Float(x-25,y-25,50,50);
			g2d.setClip(circle);
			g2d.drawImage(magnify.getImg(), 0,0, Globals.width,Globals.height,null);
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
        }

        public void keyPressed(KeyEvent e) {
            chac.keyPressed(e);
            if(e.getKeyCode() == KeyEvent.VK_F)
            	found = false;
        }
        

        }
    private class MAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent m){
        	chac.mousePressed(m);
        	if(m.getButton() == MouseEvent.BUTTON2)
        		mag = true;
        	
        	if(m.getButton() == MouseEvent.BUTTON3){
        		if(magnify.testEnv(m.getX(), m.getY()))
        			found = true;;
        	}
        }
        public void mouseReleased(MouseEvent m){
        	chac.mouseReleased(m);
        	if(m.getButton() == MouseEvent.BUTTON2)
        		mag = false;
    }
  }

	
}
