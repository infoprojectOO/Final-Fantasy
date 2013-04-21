package sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.applet.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Sound {
	AudioFormat audioFormat;
    AudioInputStream audioInputStream;
    SourceDataLine sourceDataLine;

    boolean stopPlayback = false;

    public void playAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream("endless_fantasy.wav"));
        audioFormat = audioInputStream.getFormat();
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
        sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        new Thread(new PlayThread()).start();
    }

    class PlayThread implements Runnable {
        byte soundBuffer[] = new byte[10000];

        @Override
        public void run() {
            try {
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();
                int cnt;
                while ((cnt = audioInputStream.read(soundBuffer, 0,
                        soundBuffer.length)) != -1 && stopPlayback == false) {
                    if (cnt > 1) {
                        sourceDataLine.write(soundBuffer, 0, cnt);
                    }
                }
                sourceDataLine.drain();
                sourceDataLine.close();
                stopPlayback = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	public Sound() {
		// TODO Auto-generated constructor stub
	}
	
	public static void play() {
		try {
			URL url = Sound.class.getResource("endless_fantasy.wav");
			AudioClip clip = Applet.newAudioClip(url);
			clip.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static synchronized void playSound(final String name) {
		 new Thread(new Runnable() {
			  // The wrapper thread is unnecessary, unless it blocks on the
			  // Clip finishing; see comments.
			 public void run() {
				 try {
					 Clip clip = AudioSystem.getClip();
					 InputStream url = Sound.class.getResourceAsStream("endless_fantasy.wav");
					 AudioInputStream audio = AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream("endless_fantasy.wav"));
					 clip.open(audio);
					 clip.start(); 
					 System.out.println(clip.isActive());
					 
				 } catch (Exception e) {
					 System.err.println(e.getMessage());
				 }
			 }
		}).start();
	}
	
	public static void play2() {
		try {
			 InputStream instream = Sound.class.getResourceAsStream("endless_fantasy.wav");
			 AudioInputStream audio = AudioSystem.getAudioInputStream(instream);
			 Clip clip = AudioSystem.getClip();
			 clip.open(audio);
			 clip.start(); 
		 } catch (Exception e) {
			 System.err.println(e.getMessage());
		 }
	}
	
	public static void main(String[] args) {
		Sound s = new Sound();
		InputStream instream = Sound.class.getResourceAsStream("endless_fantasy.wav");
		Sound.playSound("endless_fantasy.wav");
		File file = new File("/sound/endless_fantasy.wav");
		try {
			new Sound().playAudio();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Sound.play2();
//		Sound.play();
	}
	
}
