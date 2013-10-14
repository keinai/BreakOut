package FinalVersion;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class MultiPlayerBoard extends JPanel{
	public static int Player1Score = 0;
	public static int Player2Score = 0;
	
	private String name1="Player1";
	private String name2="Player2";
	
	private final int BALL_MAX=2;
	
	private boolean menuHover;
	private boolean ingame;
	private boolean gameStarted;
	private boolean gameRunning;
    
	MultiBall[] balls;    
    Stage stage;
	private MultiPaddle paddle1,paddle2;
	
	private Timer timer;
	
	public MultiPlayerBoard(){
		setBounds(0,0,BreakOut.WIDTH,BreakOut.HEIGHT);
		addMouseListener(new MouseHandler());
        addKeyListener(new TAdapter());
        setFocusable(true);
        requestFocusInWindow(true);
        setDoubleBuffered(true);        
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);
	}
	
	public void addNotify() {
    	super.addNotify();
        gameInit();
    }
	
	public void gameInit(){
		Player1Score = 0;
		Player2Score = 0;
		
		menuHover=false;
		ingame=true;
		gameStarted=false;
		gameRunning=false;
		
		balls = new MultiBall[BALL_MAX];
        balls[0]=new MultiBall(1);
        balls[1]=new MultiBall(2);
        
        stage = new Stage(21);
        stage.stageInit();
        
		paddle1=new MultiPaddle(1);
		paddle2=new MultiPaddle(2);
	}
	public void paint(Graphics g){		
		name1=BreakOut.multiPlayerNameInput.getName1();
		name2=BreakOut.multiPlayerNameInput.getName2();
				
		super.paint(g);
		g.drawImage(stage.getBackground(),0,0,BreakOut.WIDTH,BreakOut.HEIGHT,null);
		drawScore(g);
		drawLevel(g);
		drawMenu(g);
		balls[0].draw(g);
		balls[1].draw(g);
		paddle1.draw(g);
		paddle2.draw(g);
		stage.stagePaint(g);
		/*Start game or Pause Game*/
		if(gameStarted==false){
        	drawMessage(g,"PRESS ENTER TO START");
        }else if(gameRunning==false){
        	drawMessage(g,"PAUSE");
        }
		if(!ingame){
			drawFinal(g);
		}
		
		g.dispose();
	}
	
	public void drawScore(Graphics g){
		Font font = new Font("Verdana", Font.BOLD, 20);
		FontMetrics metr = this.getFontMetrics(font);
        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString(name1,5,20);
        g.drawString(name2,BreakOut.WIDTH-BreakOut.DIFX-5-metr.stringWidth(name2),20);
        g.setColor(Color.RED);
        g.drawString(""+Player1Score,5,40);        
        g.drawString(""+Player2Score,BreakOut.WIDTH-BreakOut.DIFX-5-metr.stringWidth(Integer.toString(Player2Score)),40);
	}
	public void drawLevel(Graphics g){
    	Font font = new Font("Verdana", Font.BOLD, 20);
        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString("LEVEL "+BreakOut.GAMELEVEL,200,20);        
    }
	public void drawMenu(Graphics g){
    	Font font = new Font("Verdana", Font.BOLD, 20);		
		if(!menuHover){
			g.setColor(Color.ORANGE);
		}else{
			g.setColor(Color.RED);
		}
		g.setFont(font);
		g.drawString("Menu",420,20);
    }
	 public void drawMessage(Graphics g,String s){
	    Font font = new Font("Verdana", Font.BOLD, 25);
		FontMetrics metr = this.getFontMetrics(font);
		g.setColor(Color.MAGENTA);
		g.setFont(font);		
		g.drawString(s,(BreakOut.WIDTH - metr.stringWidth(s)) / 2,50);

	 }
	public void drawFinal(Graphics g){
		Font font = new Font("Verdana", Font.BOLD, 20);
		FontMetrics metr = this.getFontMetrics(font);
		g.setFont(font);
		g.setColor(Color.MAGENTA);
		String finalstr;
		if(Player1Score>Player2Score){
			finalstr=name1+" Wins !";		
		}else if(Player1Score<Player2Score){
			finalstr=name2+" WINS !";
		}else finalstr="DRAW!";
		g.drawString(finalstr,(BreakOut.WIDTH-metr.stringWidth(finalstr))/2,BreakOut.HEIGHT/2);
	}
	public void stopGame() {
    	/*Victory or Game Over*/
        ingame = false;
        timer.cancel();
    }
	public void pauseGame(){
		/*When Space is Pressed or "Menu" is Clicked*/
		gameRunning=false;
		repaint();
		timer.cancel();
	}
	public void resumeGame(){
		/*Being used when game Paused*/
		gameRunning=true;            		
		timer=new Timer();
		timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);
	}
	public void replayGame(){
    	gameInit();
    	timer=new Timer();
		timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);
    }
	private class TAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			int k=e.getKeyCode();		
			
			if(k==KeyEvent.VK_ENTER){
				if(!gameStarted){
					gameStarted=true;
					gameRunning=true;
				}
			}
			
			if(k==KeyEvent.VK_SPACE && gameStarted==true && ingame==true){
            	if(gameRunning==true){
            		pauseGame();
            	}else{
            		resumeGame();
            	}
            }
			
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
	        paddle1.keyReleased(e);
	        paddle2.keyReleased(e);
	    }
	}
	 class ScheduleTask extends TimerTask {
		 public void run() {
			 if(gameRunning){				 
				 balls[0].move();
				 balls[1].move();
				 paddle1.move();
				 paddle2.move();
				 checkCollision();
				 repaint();
			 }
		 }		 
	 }
	 public void checkCollision(){
		 if(stage.isStageEmpty()){	        	
			 stopGame();
		 }
		 MultiPaddle[] paddles= new MultiPaddle[2];
		 paddles[0]=paddle1;
		 paddles[1]=paddle2;
		
		 for(int i=0;i<BALL_MAX;i++){
			 for(int j=0;j<2;j++){
		        	if ((balls[i].getRect()).intersects(paddles[j].getRect())) {        
		        		MultiBall ball=balls[i];
		        		MultiPaddle paddle=paddles[j];		        				        	
		        		
		        		int paddleLPos = (int)paddle.getRect().getMinY();
		        		int ballLPos = (int)ball.getRect().getMinY();
		        		int distance=ballLPos+ball.getHeight()/2-paddleLPos;
		        		int height=paddle.getHeight();
		        		
		        		Audio.ballPaddle.playAudio();
		        		
		        		/*if((j==0 && ball.getXDir()<0) || (j==1 && ball.getXDir()>0)){
		        			Audio.ballPaddle.playAudio();
		        		}*/
		        		
		        		if(distance<height/6){		        			
		        			if(j==0 && ball.getXDir()<0){
		        				//Paddle Left
		        				ball.setYDir(-1*(ball.getCurrentDir()+1));
		        				ball.setXDir((ball.getCurrentDir()-1));
		        			}else if(j==1 && ball.getXDir()>0){
		        				//Paddle Right
		        				ball.setYDir(-1*(ball.getCurrentDir()+1));
		        				ball.setXDir(-1*(ball.getCurrentDir()-1));
		        			}
		        		}else if(distance<height/3){
		        			if(j==0 && ball.getXDir()<0){
		        				//Paddle Left
		        				ball.setYDir(-1*(ball.getCurrentDir()));
		        				ball.setXDir((ball.getCurrentDir()));
		        			}else if(j==1 && ball.getXDir()>0){
		        				//Paddle Right
		        				ball.setYDir(-1*(ball.getCurrentDir()));
		        				ball.setXDir(-1*(ball.getCurrentDir()));
		        			}
		        		}else if(distance<height/2){
		        			if(j==0 && ball.getXDir()<0){
		        				//Paddle Left
		        				ball.setYDir(-1*(ball.getCurrentDir()-1));
		        				ball.setXDir((ball.getCurrentDir()+1));
		        			}else if(j==1 && ball.getXDir()>0){
		        				//Paddle Right
		        				ball.setYDir(-1*(ball.getCurrentDir()-1));
		        				ball.setXDir(-1*(ball.getCurrentDir()+1));
		        			}
		        		}else if(distance<2*height/3){
		        			if(j==0 && ball.getXDir()<0){
		        				//Paddle Left
		        				ball.setYDir((ball.getCurrentDir()-1));
		        				ball.setXDir((ball.getCurrentDir()+1));
		        			}else if(j==1 && ball.getXDir()>0){
		        				//Paddle Right
		        				ball.setYDir((ball.getCurrentDir()-1));
		        				ball.setXDir(-1*(ball.getCurrentDir()+1));
		        			}
		        		}else if(distance<5*height/6){
		        			if(j==0 && ball.getXDir()<0){
		        				//Paddle Left
		        				ball.setYDir((ball.getCurrentDir()));
		        				ball.setXDir((ball.getCurrentDir()));
		        			}else if(j==1 && ball.getXDir()>0){
		        				//Paddle Right
		        				ball.setYDir((ball.getCurrentDir()));
		        				ball.setXDir(-1*(ball.getCurrentDir()));
		        			}
		        		}else{
		        			if(j==0 && ball.getXDir()<0){
		        				//Paddle Left
		        				ball.setYDir((ball.getCurrentDir()+1));
		        				ball.setXDir((ball.getCurrentDir()-1));
		        			}else if(j==1 && ball.getXDir()>0){
		        				//Paddle Right
		        				ball.setYDir((ball.getCurrentDir()+1));
		        				ball.setXDir(-1*(ball.getCurrentDir()-1));
		        			}
		        		}
		        		balls[i].setPrevious(j+1);
		        		balls[i].setImage(paddles[j].getBallImage());	        		
		        	}		        	
			 }
		 }	       
	     stage.stageCollision(balls,BALL_MAX);
	 }
	 public static void setScore(int id,int addScore){
		 switch(id){
		 case 1: 
			 Player1Score+=addScore;
			 break;
		 case 2:
			 Player2Score+=addScore;
		 }
	 }
	 public static int getScore(int id){
		 int rs=0;
		 switch(id){
		 case 1:
			 rs = Player1Score;
		 case 2:
			 rs = Player2Score;
		 }
		 return rs;
	 }
	 private class MouseHandler extends MouseAdapter{    	
	    	public void mousePressed(MouseEvent e){
	    		int mx=e.getX();
	    		int my=e.getY();
	    		if(mx>420 && mx<480 && my>0 && my<20){
	    			if(ingame && gameStarted){
	    				pauseGame();
	    				BreakOut.btMultiResume.setEnabled(true);
	    			}else{
	    				BreakOut.btMultiResume.setEnabled(false);
	    			}
	    			BreakOut.multiPlayerBoard.setVisible(false);
	    			BreakOut.showMultiButton();
	    		}
	    		if(!ingame && mx>(BreakOut.WIDTH-120)/2 && mx<(BreakOut.WIDTH+120)/2 && my>380 && my<440){
	    			replayGame();    			
	    		}
	    	}
	    }
}
