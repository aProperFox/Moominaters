package videogames;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
	public Panel panel;
	public Frame(){
		panel = new Panel();
		setLayout(new GridLayout(1,1,0,0));
		add(panel);
	}
}
 