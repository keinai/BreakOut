package FinalVersion;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BrickTwice extends AbstractBrick{
	boolean destroyed;
	int live;
    int score=10;
    BufferedImage image2;
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
    
    public BrickTwice(int x, int y, String bricksrc,String bricksrc2) {
      this.x = x;
      this.y = y;
      live=2;
      setDie=false;
      countDie=0;
      dieImg=new BufferedImage[6];

      try{
    	  image=ImageIO.read(new File(bricksrc));
    	  image2=ImageIO.read(new File(bricksrc2));
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

    public boolean isDestroyed(){
      return destroyed;
    }
    
    public boolean isSetDie(){
    	return setDie;
    }
    
    public void destroy(){
    	if(live==2){
    		Audio.ballBrickNotDie.playAudio();
    		image=image2;
    		width=image2.getWidth();
    		heigth=image2.getHeight();
    		live=1;
    	}else{
    		Audio.ballBrick.playAudio();
    		setDie=true;
    		//setDestroyed(true);
    	}
    }
    public void setDestroyed(boolean destroyed){
    	this.destroyed = destroyed;
    }
    
    public int getScore(){
    	return score;
    }
    public void draw(Graphics g){
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
    public int getLive(){
    	return live;
    }

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 2;
	}
}
