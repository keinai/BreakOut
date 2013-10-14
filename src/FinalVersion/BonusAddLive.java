package FinalVersion;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BonusAddLive extends CObject{
	String addLivesrc="images/Ver1/ruby.png";
	String addLivesrc2="images/Ver1/livex.png";
	int dy=2;
	BufferedImage secondImg;
	boolean destroyed,isToDie;
	public BonusAddLive(int Ix,int Iy){
		x=Ix;
		y=Iy;
		destroyed=false;
		isToDie=false;
		try{
			image=ImageIO.read(new File(addLivesrc));
			secondImg=ImageIO.read(new File(addLivesrc2));
		}catch(IOException e){
		}
		width=image.getWidth();
		heigth=image.getHeight();
	}
	public void move(){
		y+=dy;
	}
	public void toDie(){
		image=secondImg;
		width=image.getWidth();
		heigth=image.getHeight();
		dy=-1;
		isToDie=true;
	}
	public void setDestroyed(boolean d){
		destroyed=d;
	}
	public boolean getIsDestroyed(){
		return destroyed;
	}
	public boolean getIsToDie(){
		return isToDie;
	}
	public void draw(Graphics g){
    	g.drawImage(image,x,y,width,heigth,null);
    }
}
