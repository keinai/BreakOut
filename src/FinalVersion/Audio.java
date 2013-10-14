package FinalVersion;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Audio {
	String audioFileName = new String();
	AudioInputStream audio;
	Clip clip;
	
	public static Audio ballPaddle;
	public static Audio ballBrick;
	public static Audio ballBrickNotDie;
	
	public Audio(String fileAudio)
	{
		audioFileName = fileAudio; 
	}
	public String getAudioName(){
		return this.audioFileName;
	}
	public AudioInputStream getAudio(){
		return this.audio;
	}
	public Clip getClip(){
		return this.clip;
	}
	public void playAudio(){
		 try {
             audio = AudioSystem.getAudioInputStream(new File(this.getAudioName()));
             clip = AudioSystem.getClip();
             clip.open(audio);
             clip.start();             
             
            //clip.stop();
     }
     
     catch(UnsupportedAudioFileException uae) {
             System.out.println(uae);
     }
     catch(IOException ioe) {
             System.out.println(ioe);
     }
     catch(LineUnavailableException lua) {
             System.out.println(lua);
     }
	}
}

