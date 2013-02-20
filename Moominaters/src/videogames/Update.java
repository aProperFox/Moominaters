package videogames;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Update {
	public static Frame frame;

	public Update(){

	}
	public static void main(String[] args){
		SourceDataLine soundLine = null;
		int BUFFER_SIZE = 64*1024;
		frame = new Frame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(Globals.width,Globals.height);
		frame.setTitle("Moominaters");
		frame.setLocationRelativeTo(null);
		try {
	         // Open an audio input stream.
	         File soundFile = new File("src/Sounds/hippo1.wav");
	         AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
	         AudioFormat audioMat = audioIn.getFormat();
	         // Get a sound clip resource.
	         DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioMat);
	         soundLine = (SourceDataLine) AudioSystem.getLine(info);
	         // Open audio clip and load samples from the audio input stream.
	         soundLine.open(audioMat);
	         soundLine.start();
	         int nBytesRead = 0;
	         byte[] sampleData = new byte[BUFFER_SIZE];
	         while (nBytesRead != -1) {
	             nBytesRead = audioIn.read(sampleData, 0, sampleData.length);
	             if (nBytesRead >= 0) {
	                // Writes audio data to the mixer via this source data line.
	                soundLine.write(sampleData, 0, nBytesRead);
	             }
	          }
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      } finally {
	         soundLine.drain();
	         soundLine.close();
	      }
	}
		
}
