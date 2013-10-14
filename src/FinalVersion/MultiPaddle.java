package FinalVersion;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MultiPaddle extends CObject {
	String paddlesrc1="images/Ver1/multipaddle1.png";
	String paddlesrc2="images/Ver1/multipaddle2.png";
	String multiball1="images/Ver1/multiball1.png";
	String multiball2="images/Ver1/multiball2.png";
		
	BufferedImage ballImage;
	
	int id;
	int dy;
	public MultiPaddle(int Iid){
		id=Iid;
		switch (id){
		case 1:
			try{
				image=ImageIO.read(new File(paddlesrc1));
				ballImage=ImageIO.read(new File(multiball1));
			}catch(IOException e){
			}
			break;
		case 2:
			try{
				image=ImageIO.read(new File(paddlesrc2));
				ballImage=ImageIO.read(new File(multiball2));
			}catch(IOException e){
			}
		}		
		width=image.getWidth();
		heigth=image.getHeight();
		resetState();
	}
	public void move(){
		y+=dy;
		if(y<=2){
			y=2;
		}
		if(y>=BreakOut.HEIGHT-BreakOut.DIFY-heigth){
			y=BreakOut.HEIGHT-BreakOut.DIFY-heigth;
		}
	}
	public void keyPressed(KeyEvent e){
		int k=e.getKeyCode();
		switch (id){
		case 1:
			if(k==KeyEvent.VK_W){
				dy=-BreakOut.PADDLE_STEP;
			}else if(k==KeyEvent.VK_S){
				dy=BreakOut.PADDLE_STEP;
			}
			break;
		case 2:
			if(k==KeyEvent.VK_UP){
				dy=-BreakOut.PADDLE_STEP;
			}else if(k==KeyEvent.VK_DOWN){
				dy=BreakOut.PADDLE_STEP;
			}
		}
	}
	public void  keyReleased(KeyEvent e){
		int k=e.getKeyCode();
		switch (id){
		case 1:
			if(k==KeyEvent.VK_W){
				dy=0;
			}else if(k==KeyEvent.VK_S){
				dy=0;
			}
			break;
		case 2:
			if(k==KeyEvent.VK_UP){
				dy=0;
			}else if(k==KeyEvent.VK_DOWN){
				dy=0;
			}
		}
	}
	public void resetState(){
		switch(id){
		case 1: 
			x=0;
			y=(BreakOut.HEIGHT-heigth)/2;
			break;
		case 2:
			x=BreakOut.WIDTH-BreakOut.DIFX-width;
			y=(BreakOut.HEIGHT-heigth)/2;
		}
	}
	public BufferedImage getBallImage(){
		return ballImage;
	}
	public void draw(Graphics g){
    	g.drawImage(image,x,y,width,heigth,null);
    }
}
