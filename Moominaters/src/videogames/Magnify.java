package videogames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.QuadCurve2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Magnify extends JPanel{
	private Image img;

	private int levelNum;
	private int x, y;
	public Magnify(){
		ImageIcon ii = new ImageIcon("src/Backgrounds/hidden1.png");
		img = ii.getImage();
		levelNum = 1;
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
	/*public Boolean testEnv(int x, int y){
		if(levelNum == 1){
			System.out.print(x + " " + y + '\r');
			if(y > 1080-(((620*x)/645)-1200) && y < 1080-(((620*x)/645)-1310)){
				return true;
			}
		}
		
		return false;
	}*/
}
