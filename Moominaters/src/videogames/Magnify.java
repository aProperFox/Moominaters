package videogames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.QuadCurve2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Magnify extends JPanel{
	private Image img;

	//private int levelNum;
	private int x, y;
	public Magnify(){
		ImageIcon ii = new ImageIcon("src/Backgrounds/hidden1.png");
		img = ii.getImage();
	}
	
	public void drawArea(int x, int y){
		//levelNum = level;
		this.x = x;
		this.y = y;
		repaint();
	}
	public Image getImg(){
		return img;
		
	}
	public void paint(Graphics g){
		super.paint(g);
		Graphics g2d = (Graphics2D) g;
		QuadCurve2D circle = new QuadCurve2D.Float(x,y-10,x,y,x,y-10);
		circle.setCurve(x,y-10,x,y,x,y-10);
		g2d.setClip(circle);
		g2d.drawImage(img, x,y, 20,20,null);
		
	}
}
