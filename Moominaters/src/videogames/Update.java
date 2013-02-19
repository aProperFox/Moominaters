package videogames;

import javax.swing.JFrame;

public class Update {
	public static Frame frame;
	public Update(){

	}
	public static void main(String[] args){
		frame = new Frame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(Globals.width,Globals.height);
		frame.setTitle("Moominaters");
		frame.setLocationRelativeTo(null);
		 
		
		
	} 
}
