package FinalVersion;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Stage{
	
	int[]  pos1={
			0,0,0,2,2,0,0,0,2,2,0,0,0,
			0,2,3,1,1,2,0,2,1,1,3,2,0,
			2,1,3,3,3,1,2,1,3,3,3,1,2,
			2,2,2,1,2,3,1,3,2,1,2,2,2,
			0,0,2,2,1,2,3,2,1,2,2,0,0,
			3,0,0,0,2,1,2,1,2,0,0,0,3,
			3,1,0,0,0,2,1,2,0,0,0,1,3,
			3,1,1,0,0,0,2,0,0,0,1,1,3,
			3,3,3,3,0,0,0,0,0,3,3,3,3
	};
	
	int[]  pos2={
			3,3,2,2,2,2,2,2,2,2,2,3,3,
			2,2,1,1,2,0,4,0,2,1,1,2,2,
			2,2,1,2,0,1,1,1,0,2,1,2,2,
			2,2,2,0,1,1,1,1,1,0,2,2,2,
			2,2,2,4,1,1,1,1,1,4,2,2,2,
			2,2,2,0,1,1,1,1,1,0,2,2,2,
			2,2,1,2,0,1,1,1,0,2,1,2,2,
			2,2,1,1,2,0,4,0,2,1,1,2,2,
			3,3,2,2,2,2,2,2,2,2,2,3,3
	};
	
	int[] pos21={
			0,0,0,0,0,0,1,0,0,0,0,0,0,
			0,0,0,0,0,1,2,1,0,0,0,0,0,
			0,0,0,0,1,2,2,2,1,0,0,0,0,
			0,0,0,1,2,2,1,2,2,1,0,0,0,
			0,0,1,2,2,1,1,1,2,2,1,0,0,
			0,1,2,2,2,2,1,2,2,2,2,1,0,
			0,1,2,3,3,3,3,3,3,3,2,1,0,
			0,1,2,2,2,2,1,2,2,2,2,1,0,
			0,1,2,2,2,1,1,1,2,2,2,1,0,
			0,0,1,2,2,2,1,2,2,2,1,0,0,
			0,0,0,1,2,2,2,2,2,1,0,0,0,
			0,0,0,0,1,2,2,2,1,0,0,0,0,
			0,0,0,0,0,1,2,1,0,0,0,0,0,
			0,0,0,0,0,0,1,0,0,0,0,0,0
	};
	
	int ID;
	int pos[];
	
	private int BRICKNUM=0;
	private int UNBREAK=0;
	private int TWICENUM=0;	
	
	private String bgsrc;
	
	private BufferedImage background;
	private Brick[] bricks;
	private BrickTwice[] brickstwice;
	private UnbreakBrick[] unbreaks;
	
	public Stage(int iID){		
		ID=iID;	
		switch(ID){
		case 1:
			pos=new int[pos1.length];
			System.arraycopy(pos1, 0, pos, 0, pos1.length);			
			break;
		case 2:
			pos=new int[pos2.length];
			System.arraycopy(pos2, 0, pos, 0, pos2.length);			
			break;
		case 21:
			pos=new int[pos21.length];
			System.arraycopy(pos21, 0, pos, 0, pos21.length);
			break;
		}		
		for(int i=0;i<pos.length;i++){
			if(pos[i]==1 || pos[i]==2){
				BRICKNUM++;
			}else if(pos[i]==3){
				TWICENUM++;
			}else if(pos[i]==4){
				UNBREAK++;
			}
		}
		
		/* BackGround */
		switch (ID){
		case 1: 
			bgsrc="images/prison.jpg";
			break;
		case 2:
			bgsrc="images/Ver1/Space_Space_star_and_planet.jpg";
			break;
		case 21:
			bgsrc="images/Ver1/Space_Star_way.jpg";
			break;
		}		
		try{
			background=ImageIO.read(new File(bgsrc));			
		}catch(IOException e){
		}
		
	}
	public void stageInit(){		
		int maxI;
		int twiceCount=0;
		int singleCount=0;
		int unbreakCount=0;
		String brickgreen="images/Ver1/brickie_green.png";
		String brickorange_cl="images/Ver1/brickie_orange_cl.png";
		String brickred="images/Ver1/brickie_red.png";
		String brickred2="images/Ver1/brickie_red_2.png";
		
		bricks=new Brick[BRICKNUM];		
		brickstwice=new BrickTwice[TWICENUM];
		unbreaks=new UnbreakBrick[UNBREAK];
		
		if (ID!=21){
			maxI=9;
		}else{
			maxI=14;
		}
		
		// Create list Brick
		//ArrayList<AbstractBrick> brickList = new ArrayList<AbstractBrick>();
		
		//Create Brick
		for (int i = 0,k=0; i < maxI; i++) {
            for (int j = 0; j < 13; j++) {            	
            	if (pos[k]==1)
           			bricks[singleCount++] = new Brick(j * 65 + 35, i * 30 + 50,brickgreen);
           		else if (pos[k]==2)
           			bricks[singleCount++] = new Brick(j * 65 + 35, i * 30 + 50,brickorange_cl);           			
           		else if (pos[k]==3)
           			brickstwice[twiceCount++]=new BrickTwice(j*65+35,i*30+50,brickred,brickred2);
           		else if (pos[k]==4)
           			unbreaks[unbreakCount++]=new UnbreakBrick(j*65+35,i*30+50);
           		k++;
           	}                
        }
		
		// Fix Code
		//this.fixBrickList(brickList);
		
	}
	
	public void stagePaint(Graphics g){
		for (int i = 0; i < BRICKNUM; i++) {
            if (!bricks[i].isDestroyed())
            	bricks[i].draw(g);
        }		
		for (int i = 0; i < TWICENUM; i++) {
            if (!brickstwice[i].isDestroyed())
            	brickstwice[i].draw(g);
        }
		for (int i=0; i < UNBREAK; i++){
			if(!unbreaks[i].isDestroyed()){
				unbreaks[i].draw(g);
			}
		}
	}

	public void stageCollision(Ball[] balls,int ballNum){
		for(int p=0;p<ballNum;p++){
			if(balls[p].isDestroyed()==false){
				Ball ball=balls[p];
						
				for (int i = 0; i < BRICKNUM; i++) {
					if ((ball.getRect()).intersects(bricks[i].getRect())) {						
						if (!bricks[i].isDestroyed() && !bricks[i].isSetDie()) {
							collision(ball,bricks[i].getRect());							
							GameBoard.setScore(GameBoard.getScore()+bricks[i].getScore());
							if((ID==1 && (i==50 || i==45)) || (ID==2 && i==35)){
								GameBoard.bonusAddBall=new BonusAddBall(bricks[i].getX(),bricks[i].getY());
								GameBoard.isSetAddBall=true;                        
							}       
							bricks[i].destroy();
						}
					}
				}				
				for (int i = 0; i < TWICENUM; i++) {
					if ((ball.getRect()).intersects(brickstwice[i].getRect())) {                
						if (!brickstwice[i].isDestroyed() && !brickstwice[i].isSetDie()) {
							collision(ball,brickstwice[i].getRect());
							GameBoard.setScore(GameBoard.getScore()+brickstwice[i].getScore());
							if(((ID==1 && i==18)) && brickstwice[i].getLive()==1){
								GameBoard.bonusAddLive=new BonusAddLive(brickstwice[i].getX(),brickstwice[i].getY());
								GameBoard.isSetAddLive=true;
							}
							brickstwice[i].destroy();
						}
					}
				}
				for (int i = 0; i < UNBREAK; i++) {
					if ((ball.getRect()).intersects(unbreaks[i].getRect())) {                
						if (!unbreaks[i].isDestroyed()) {
							Audio.ballBrickNotDie.playAudio();
							collision(ball,unbreaks[i].getRect());							
						}
					}
				}
			}
		}
	}
	public void collision(Ball ball, Rectangle rect){
		int minX=(int)ball.getRect().getMinX();		
		int minY=(int)ball.getRect().getMinY();
		int maxX=(int)ball.getRect().getMaxX();
		int maxY=(int)ball.getRect().getMaxY();
		int width=ball.getRect().width;
		int height=ball.getRect().height;
		
		Point topLeft = new Point(minX,minY);
		Point topRight = new Point(maxX,minY);
		Point bottomLeft = new Point(minX,maxY);
		Point bottomRight = new Point(maxX,maxY);
		
		Rectangle intersect=ball.getRect().intersection(rect);
		
		if(intersect.contains(new Point(minX,minY+height/2)) || intersect.contains(new Point(minX+width,minY+height/2))){
			ball.setXDir(-ball.getXDir());
		}else if(intersect.contains(new Point(minX+width/2,minY)) || intersect.contains(new Point(minX+width/2,minY+height))){
			ball.setYDir(-ball.getYDir());
		}else if(intersect.getHeight()>intersect.getWidth()){
			if((ball.getXDir()<0 && (intersect.contains(topRight) || intersect.contains(bottomRight)))||
				(ball.getXDir()>0 && (intersect.contains(topLeft) || intersect.contains(bottomLeft)))){
				ball.setYDir(-ball.getYDir());
			}else{
				ball.setXDir(-ball.getXDir());
			}
		}else{
			if((ball.getYDir()<0 && (intersect.contains(bottomRight) || intersect.contains(bottomLeft))) ||
				ball.getYDir()>0 && (intersect.contains(topRight) || intersect.contains(topLeft))){
				ball.setXDir(-ball.getXDir());
			}else{
				ball.setYDir(-ball.getYDir());
			}
		}
	}
	
	public void stageCollision(MultiBall[] balls, int ballNum){
		for(int p=0;p<ballNum;p++){
			if(balls[p].isDestroyed()==false){
				MultiBall ball=balls[p];
				for (int i = 0; i < BRICKNUM; i++) {
					if ((ball.getRect()).intersects(bricks[i].getRect())) {						
						if (!bricks[i].isDestroyed() && !bricks[i].isSetDie()) {
							collision(ball,bricks[i].getRect());	
							MultiPlayerBoard.setScore(ball.getPrevious(),bricks[i].getScore());							
							bricks[i].destroy();
						}
					}
				}				
				for (int i = 0; i < TWICENUM; i++) {
					if ((ball.getRect()).intersects(brickstwice[i].getRect())) {                
						if (!brickstwice[i].isDestroyed() && !brickstwice[i].isSetDie()) {
							collision(ball,brickstwice[i].getRect());
							MultiPlayerBoard.setScore(ball.getPrevious(),bricks[i].getScore());							
							brickstwice[i].destroy();
						}
					}
				}
				for (int i = 0; i < UNBREAK; i++) {
					if ((ball.getRect()).intersects(unbreaks[i].getRect())) {                
						if (!unbreaks[i].isDestroyed()) {
							Audio.ballBrickNotDie.playAudio();
							collision(ball,unbreaks[i].getRect());							
						}
					}
				}
			}
		}
	}
	
	public void collision(MultiBall ball, Rectangle rect){
		int minX=(int)ball.getRect().getMinX();		
		int minY=(int)ball.getRect().getMinY();
		int width=ball.getRect().width;
		int height=ball.getRect().height;
		
		Point topLeft = new Point(minX,minY);
		Point topRight = new Point(minX+width,minY);
		Point bottomLeft = new Point(minX,minY+height);
		Point bottomRight = new Point(minX+width,minY+height);
		
		Rectangle intersect=ball.getRect().intersection(rect);
		
		if(intersect.contains(new Point(minX,minY+height/2)) || intersect.contains(new Point(minX+width,minY+height/2))){
			ball.setXDir(-ball.getXDir());
		}else if(intersect.contains(new Point(minX+width/2,minY)) || intersect.contains(new Point(minX+width/2,minY+height))){
			ball.setYDir(-ball.getYDir());
		}else if(intersect.getHeight()>intersect.getWidth()){
			if((ball.getXDir()<0 && (intersect.contains(topRight) || intersect.contains(bottomRight)))||
				(ball.getXDir()>0 && (intersect.contains(topLeft) || intersect.contains(bottomLeft)))){
				ball.setYDir(-ball.getYDir());
			}else{
				ball.setXDir(-ball.getXDir());
			}
		}else{
			if((ball.getYDir()<0 && (intersect.contains(bottomRight) || intersect.contains(bottomLeft))) ||
				ball.getYDir()>0 && (intersect.contains(topRight) || intersect.contains(topLeft))){
				ball.setXDir(-ball.getXDir());
			}else{
				ball.setYDir(-ball.getYDir());
			}
		}	
	}
	
	public boolean isStageEmpty(){
		int j=0;
		for(int i=0;i<BRICKNUM;i++){
			if(bricks[i].isDestroyed()){
				j++;				
			}
		}
		for(int i=0;i<TWICENUM;i++){
			if(brickstwice[i].isDestroyed()){
				j++;				
			}
		}
		if(j==BRICKNUM+TWICENUM) return true;		
		return false;
	}
	public BufferedImage getBackground(){		
		return background;
	}
	
	// Fix code (Phu)
		public void fixBrickList(ArrayList<AbstractBrick> brickList) {

			int unbreakCount = 0;
			int singleCount = 0;
			int twiceCount = 0;
			System.out.println(brickList.size());
			for (int i = 0; i < brickList.size(); i++) {
				switch (brickList.get(i).getType()) {
				case 0:
					unbreaks[unbreakCount++] = (UnbreakBrick) brickList.get(i);
					break;
				case 1:
					bricks[singleCount++] = (Brick) brickList.get(i);
					break;
				case 2:
					brickstwice[twiceCount++] = (BrickTwice) brickList.get(i);
					break;

				}
			}

		}
	
	
}
