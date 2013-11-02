package FinalVersion;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Brick extends AbstractBrick{	
		
    boolean destroyed;
    int score=10;
    int explode=0;
        
    /*For Explosion Effect*/    
    String die1src="images/Ver1/explode/ep21.png";
    String die2src="images/Ver1/explode/ep22.png";
    String die3src="images/Ver1/explode/ep23.png";
    String die4src="images/Ver1/explode/ep24.png";
    String die5src="images/Ver1/explode/ep25.png";
    String die6src="images/Ver1/explode/ep26.png";    
    BufferedImage die1img,die2img,die3img,die4img,die5img,die6img;
    BufferedImage[] dieImg;
    boolean setDie;
    int countDie;
    
    public Brick(int x, int y, String bricksrc) {
      this.x = x;
      this.y = y;      
      
      setDie=false;
      countDie=0;
      dieImg=new BufferedImage[6];      
      
      try{
    	  image=ImageIO.read(new File(bricksrc));
    	  dieImg[0]=ImageIO.read(new File(die1src));
    	  dieImg[1]=ImageIO.read(new File(die2src));
    	  dieImg[2]=ImageIO.read(new File(die3src));
    	  dieImg[3]=ImageIO.read(new File(die4src));
    	  dieImg[4]=ImageIO.read(new File(die5src));
    	  dieImg[5]=ImageIO.read(new File(die6src));
      }catch(IOException e){    	  
      }

      width = image.getWidth();
      heigth = image.getHeight();

      destroyed = false;
    }

    public void destroy(){
    	if(setDie==false){
    			setDie=true;    			
    	}
    }
    public boolean isDestroyed(){
      return destroyed;
    }
    
    public boolean isSetDie(){
    	return setDie;
    }
    
    public void setDestroyed(boolean destroyed){
      this.destroyed = destroyed;
    }
    
    public int getScore(){
    	return score;
    }
    public void draw(Graphics g){
    	
    	if ((setDie==true)&&(explode==0)){    		    		
    		Audio.ballBrick.playAudio();
    		explode=1;
    		
            //clip.open(audio);
            //clip.start();
    		
    	}
    		
    	if(setDie==true){
    		countDie++;    		    		
    		image=dieImg[countDie/5];
    		width=image.getWidth();
        	heigth=image.getHeight();    		
    	}    	
    	if(countDie==28){
    		setDestroyed(true);
    	}    	    	
    	g.drawImage(image,x,y,width,heigth,null);
    }

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 1;
	}    
}
