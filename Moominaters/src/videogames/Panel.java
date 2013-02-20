package videogames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel  extends JPanel implements ActionListener{

	private Timer timer;
	private Character chac;
	private Image img;

	
	public Panel(){
	    addKeyListener(new TAdapter());
	    setFocusable(true);
	    setDoubleBuffered(true);
	    ImageIcon ii = new ImageIcon("src/Backgrounds/backdrop1.png");
	    img = ii.getImage();
	    
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
        }
    }


	
}
