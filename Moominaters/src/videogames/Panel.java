package videogames;

import java.awt.Color;

import javax.swing.*;
import java.awt.*;

public class Panel  extends JPanel{
	public Rectangle character;
	public int chacWidth = 12;
	public int chacHeight = 20;
	public Panel(){
		setBackground(Color.WHITE);
		
		defineObjects();
	}
	
	public void defineObjects(){
		character = new Rectangle(Update.width/2 - chacWidth/2,Update.height/2 - chacHeight/2);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
	}
}
