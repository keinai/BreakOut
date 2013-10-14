package FinalVersion;

import java.awt.Graphics;

import java.io.File;
import java.io.IOException;

import java.util.Random;

import javax.imageio.ImageIO;

public class Ball extends CObject{
	private int xdir;
	private int ydir;
	private int currentDir;
	boolean destroyed;
	protected String ballsrc = "images/Ver1/ball.png";

	public Ball() {
		Random random=new Random();
		int rs=random.nextInt(6);
		int xOrg=BreakOut.BALL_DX+BreakOut.GAMELEVEL-1;
		int yOrg=BreakOut.BALL_DY+BreakOut.GAMELEVEL-1;		
		switch(rs){
		case 0:
			xdir=-(xOrg+1);
			ydir=-(yOrg-1);
			break;
		case 1:
			xdir=-xOrg;
			ydir=-yOrg;
			break;
		case 2:
			xdir=-(xOrg-1);
			ydir=-(yOrg+1);
			break;
		case 3:
			xdir=xOrg-1;
			ydir=-(yOrg+1);
			break;
		case 4:
			xdir=xOrg;
			ydir=-yOrg;
			break;
		case 5:
			xdir=xOrg+1;
			ydir=-(yOrg-1);
		}		
		currentDir=(BreakOut.BALL_DX+BreakOut.BALL_DY+2*BreakOut.GAMELEVEL-2)/2;
		try{
			image=ImageIO.read(new File(ballsrc));
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
	    }
	    if (x >= BreakOut.WIDTH-BreakOut.DIFX-image.getWidth()) {
	        setXDir(-Math.abs(xdir));
	    }
	    if (y <= 0) {
	        setYDir(Math.abs(ydir));
	    }
	}
	public void resetState() {
	    x = BreakOut.BALL_RESET_X+(int)(85*Math.random());
	    y = BreakOut.BALL_RESET_Y;
	}
	public void setXDir(int x){
	    xdir = x;
	}
	public void setYDir(int y){
	    ydir = y;
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
