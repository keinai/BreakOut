package FinalVersion;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UnbreakBrick extends AbstractBrick{
	String bricksrc = "images/Ver1/unbreakBrick.png";
    boolean destroyed;
    int score=10;
    public UnbreakBrick(int x, int y) {
      this.x = x;
      this.y = y;

      try{
    	  image=ImageIO.read(new File(bricksrc));
      }catch(IOException e){    	  
      }

      width = image.getWidth();
      heigth = image.getHeight();

      destroyed = false;
    }

    public boolean isDestroyed(){
      return destroyed;
    }
    
    public void setDestroyed(boolean destroyed){
      this.destroyed = destroyed;
    }
    
    public int getScore(){
    	return score;
    }
    
    public void draw(Graphics g){
    	g.drawImage(image,x,y,width,heigth,null);
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSetDie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}
}
