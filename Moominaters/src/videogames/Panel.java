package videogames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel  extends JPanel implements ActionListener{

	private Timer timer;
	private Character chac;

	
	private int imageCurr;
	
	public Panel(){
		setBackground(Color.WHITE);
	    addKeyListener(new TAdapter());
	    setFocusable(true);
	    setDoubleBuffered(true);

        chac = new Character();
	    timer = new Timer(4,this);
	    timer.start();
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
