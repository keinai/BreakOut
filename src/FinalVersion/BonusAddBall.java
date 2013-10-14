package FinalVersion;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BonusAddBall extends CObject{
	String addBallsrc="images/Ver1/multiball6.png";
	int dy=2;
	public BonusAddBall(int Ix,int Iy){
		x=Ix;
		y=Iy;
		try{
			image=ImageIO.read(new File(addBallsrc));
		}catch(IOException e){
		}
		width=image.getWidth();
		heigth=image.getHeight();
	}
	public void move(){
		y+=dy;
	}
	public void draw(Graphics g){
    	g.drawImage(image,x,y,width,heigth,null);
    }
}
