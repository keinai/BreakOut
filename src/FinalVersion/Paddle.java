package FinalVersion;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Paddle extends CObject {
    String paddlesrc = "images/Ver1/paddle.png";
    int dx;
    public Paddle() {
    	try{
    		image=ImageIO.read(new File(paddlesrc));
    	}catch(IOException e){
    	}
        width = image.getWidth();
        heigth = image.getHeight();        
        resetState();
    }
    
    public void move() {
        x += dx;
        if (x <= 2) 
          x = 2;
        if (x >= BreakOut.WIDTH-BreakOut.DIFX-image.getWidth())
          x = BreakOut.WIDTH-BreakOut.DIFX-image.getWidth();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -1*BreakOut.PADDLE_STEP;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = BreakOut.PADDLE_STEP;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
    
    public void resetState() {
        x = BreakOut.WIDTH/2-width/2;    	
        y = BreakOut.HEIGHT-BreakOut.DIFY-image.getHeight();
    }
    public void draw(Graphics g){
    	g.drawImage(image,x,y,width,heigth,null);
    }
}
