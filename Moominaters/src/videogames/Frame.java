package videogames;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
	public Environment env;
	public Panel panel;
	public Frame(){
		panel = new Panel();
		env = new Environment();
		setLayout(new GridLayout(1,1,0,0));
		add(panel);
	}
}
  