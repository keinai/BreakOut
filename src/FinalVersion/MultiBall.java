package FinalVersion;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MultiBall extends CObject{	
	private int xdir;
	private int ydir;
	private int currentDir;
	boolean destroyed;
	protected String ball1src = "images/Ver1/multiball1.png";
	protected String ball2src = "images/Ver1/multiball2.png";
	int id;
	int previous;

	public MultiBall(int Iid) {
		id=Iid;
		previous=id;
		switch (id){
		case 1:
			xdir = BreakOut.BALL_DX+BreakOut.GAMELEVEL-1;
			ydir = BreakOut.BALL_DY+BreakOut.GAMELEVEL-1;			
			break;
		case 2:
			xdir = -1* (BreakOut.BALL_DX+BreakOut.GAMELEVEL-1);
			ydir = BreakOut.BALL_DY+BreakOut.GAMELEVEL-1;
		}
		currentDir=(BreakOut.BALL_DX+BreakOut.BALL_DY+2*BreakOut.GAMELEVEL-2)/2;
		try{
			switch(id){
			case 1:
				image=ImageIO.read(new File(ball1src));
				break;
			case 2:
				image=ImageIO.read(new File(ball2src));
				break;
			}			
		}catch(IOException e){		
		}
		width = image.getWidth();
		heigth = image.getHeight();
		destroyed=false;

		resetState();
	}
	
	public void move(){
		x += xdir;
	    y += ydir;
	    if (x <= 0) {
	        setXDir(Math.abs(xdir));
	        MultiPlayerBoard.setScore(2, 100);
	    }
	    if (x >= BreakOut.WIDTH-BreakOut.DIFX-image.getWidth()) {
	        setXDir(-Math.abs(xdir));
	        MultiPlayerBoard.setScore(1, 100);
	    }
	    if (y <= 0) {
	        setYDir(Math.abs(ydir));
	    }
	    if(y >= BreakOut.HEIGHT-BreakOut.DIFY-image.getHeight()){
	    	setYDir(-Math.abs(ydir));
	    }
	}
	public void setPrevious(int x){
		previous=x;
	}
	public int getPrevious(){
		return previous;
	}
	public void resetState() {
		switch (id){
		case 1:
			x = 15;
	    	y = (BreakOut.HEIGHT-80)/2+(int)(60*Math.random());
	    	break;
		case 2:
			x = BreakOut.WIDTH-BreakOut.DIFX-27;
			y = (BreakOut.HEIGHT-80)/2+(int)(60*Math.random());
		}
	}
	public void setXDir(int x){
	    xdir = x;
	}
	public void setYDir(int y){
	    ydir = y;
	}
	public void setImage(BufferedImage img){
		image=img;
	}
	public void setCurrentStep(int curdir){
		currentDir=curdir;
	}
	public int getXDir(){
		return xdir;
	}
	public int getYDir(){
		return ydir;
	}
	public int getCurrentDir(){
		return currentDir;
	}
	public boolean isDestroyed(){
		return destroyed;
	}
	public void setDestroyed(boolean set){
		destroyed=set;
	}
	public void draw(Graphics g){
    	g.drawImage(image,x,y,width,heigth,null);
    }
}
