package FinalVersion;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameBoard extends JPanel{    
	Timer timer;
    private String finalMessage;    
    Audio reflex;
    Ball[] balls;
    Paddle paddle;    
    Stage stage;
    
    private static int MAX_STAGE=2;
    private static int SCORE=0;
    private final int BALL_MAX=20;
    private final int SET_BALL=6;    
    private int lives;

    private int ballNum;
    private int ballLeft;
    
    private int curStage;
    
    private boolean menuHover;
    
    boolean ingame;
    boolean gameRunning;
    boolean gameStarted;
    boolean nextlive;
    boolean passStage;
    
    /*Bonus Area*/
    static BonusAddBall bonusAddBall;
    static boolean isSetAddBall;
    
    static BonusAddLive bonusAddLive;
    static boolean isSetAddLive;
        
    public GameBoard() {    	    	
    	addMouseListener(new MouseHandler());
    	addMouseMotionListener(new MouseHandlerHover());
    	setBounds(0,0,BreakOut.WIDTH,BreakOut.HEIGHT);
        addKeyListener(new TAdapter());
        setFocusable(true);   
        setDoubleBuffered(true);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);        
    }
    
    public void addNotify() {
    	super.addNotify();
        gameInit();
    }
    
    public void gameInit() { 
    	GameBoard.setScore(0);
    	curStage=1;
    	
    	finalMessage="Game Over";
    	
    	lives=3;
    	ballNum=1;
    	ballLeft=ballNum;
    	
    	menuHover=false;
    	passStage = false;
    	nextlive = false;
    	gameRunning=false;
    	gameStarted=false;
    	ingame=true;    	
    	isSetAddBall = false;
    	isSetAddLive = false;
    	
    	isSetAddBall = false;
    	isSetAddLive = false;
        balls = new Ball[BALL_MAX];
        for(int i=0;i<BALL_MAX;i++){
        	balls[i]=new Ball();        	
        }
        
        paddle = new Paddle();
        stage = new Stage(1);
        stage.stageInit();
    }    
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(stage.getBackground(),0,0,BreakOut.WIDTH,BreakOut.HEIGHT,null);
       	drawScore(g);
       	drawLive(g);
       	drawLevel(g);
       	drawStage(g);
       	drawMenu(g);
        for(int i=0;i<ballNum;i++){
           	if(!balls[i].isDestroyed()){
           		balls[i].draw(g);
           	}
        }        	
       	paddle.draw(g);
       	stage.stagePaint(g);
        /*Bonus*/
        if(isSetAddBall){
        	bonusAddBall.draw(g);
        }
        if(isSetAddLive){
        	bonusAddLive.draw(g);
        }
        /*Check victory or Game Over*/
        if(!ingame){
        	drawMessage(g,finalMessage);
        	if(!passStage)
        		drawReplay(g);
        }
        /*Start a game or pause game*/
        if(gameStarted==false){
        	drawMessage(g,"PRESS ENTER TO START");
        }else if(gameRunning==false){
        	drawMessage(g,"PAUSE");
        }
        /*Synchronize*/
        Toolkit.getDefaultToolkit().sync();
        g.dispose();        
    }    
    public void drawScore(Graphics g){
    	Font font = new Font("Verdana", Font.BOLD, 24);
        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString(""+GameBoard.getScore(),120,28);        
    }
    public void drawLevel(Graphics g){
    	Font font = new Font("Verdana", Font.BOLD, 24);
        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString("LEVEL "+BreakOut.GAMELEVEL,400,28);        
    }
    public void drawStage(Graphics g){
    	Font font = new Font("Verdana", Font.BOLD, 22);
        g.setColor(Color.ORANGE);
        g.setFont(font);
        g.drawString("Stage "+curStage,550,28);
    }
    public void drawMessage(Graphics g,String s){
    	Font font = new Font("Verdana", Font.BOLD, 25);
		FontMetrics metr = this.getFontMetrics(font);

		g.setColor(Color.MAGENTA);
		g.setFont(font);
		
		g.drawString(s,
				(BreakOut.WIDTH - metr.stringWidth(s)) / 2,350);

    }
    public void drawMenu(Graphics g){
    	Font font = new Font("Verdana", Font.BOLD, 18);		
		if(!menuHover){
			g.setColor(Color.ORANGE);
		}else{
			g.setColor(Color.RED);
		}
		g.setFont(font);
		g.drawString("MENU",830,28);
    }
    public void drawLive(Graphics g){
    	String livesrc="images/Ver1/ruby_org.png";
    	try{
    		BufferedImage liveimg=ImageIO.read(new File(livesrc));
    		for(int i=0;i<lives;i++){
    			g.drawImage(liveimg,20+i*20,10,15,20,null);
    		}
    	}catch(IOException e){
    	}
    	if(nextlive==true){
    		Font font = new Font("Verdana", Font.BOLD, 25);
    		FontMetrics metr = this.getFontMetrics(font);

    		g.setColor(Color.MAGENTA);
    		g.setFont(font);

    		String msnext="PRESS ENTER TO CONTINUE";
    		g.drawString(msnext,(BreakOut.WIDTH - metr.stringWidth(msnext)) / 2,350);
    	}
    }        
    public void drawReplay(Graphics g){
    	BufferedImage replayimg;
    	try{
    		replayimg=ImageIO.read(new File("images/Ver1/replay2.png"));
    		g.drawImage(replayimg,(BreakOut.WIDTH-replayimg.getWidth())/2,380,this);
    	}catch(IOException e){
    	}
    }
    public static int getScore(){
    	return SCORE;
    }
    
    public static void setScore(int IScore){
    	SCORE=IScore;
    }
    private class TAdapter extends KeyAdapter {
    	public void keyPressed(KeyEvent e) {
    		int k=e.getKeyCode(); 
    		/*Pause Game*/
            if(k==KeyEvent.VK_SPACE && gameStarted==true && ingame==true && nextlive==false){
            	if(gameRunning==true){
            		pauseGame();
            	}else{
            		resumeGame();
            	}
            }
            /*Start Game*/
            if(k==KeyEvent.VK_ENTER){
            	if(gameStarted==false){ 
            		//Start from begin
            		gameStarted=true;
            		gameRunning=true;
            	}else if(nextlive==true){ 
            		//Start next live
            		nextlive=false;            		
        			timer=new Timer();
        			timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);
            	}
            }
            paddle.keyPressed(e);                   
        }
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }        
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
    public void stopGame() {
    	/*Victory or Game Over*/
        ingame = false;
        timer.cancel();
    }
    private class MouseHandler extends MouseAdapter{    	
    	public void mousePressed(MouseEvent e){
    		int mx=e.getX();
    		int my=e.getY();
    		if(mx>830 && my>5 && my<28){
    			if(ingame && gameStarted){    				
    				pauseGame();
    				BreakOut.btSingleResume.setEnabled(true);
    			}else{
    				BreakOut.btSingleResume.setEnabled(false);
    			}
    			BreakOut.gameBoard.setVisible(false);
    			BreakOut.showSingleButton();
    		}
    		if(!ingame && mx>(BreakOut.WIDTH-120)/2 && mx<(BreakOut.WIDTH+120)/2 && my>380 && my<440){
    			replayGame();    			
    		}
    	}
    }
public void nextStageGame(){    	
    	
       	ballNum=1;
       	ballLeft=ballNum;
       	
       	menuHover=false;
       	passStage = false;
       	nextlive = false;
       	gameRunning=false;
       	gameStarted=false;
       	ingame=true;    	        	
        	
        isSetAddBall = false;
        isSetAddLive = false;
        
        finalMessage="GameOver";
        	
        balls = new Ball[BALL_MAX];
        for(int i=0;i<BALL_MAX;i++){
        	balls[i]=new Ball();        	
        }
            
        paddle = new Paddle();        
        stage=new Stage(curStage);
        stage.stageInit();
        
        timer=new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);    		    	
    }
       
    private class MouseHandlerHover extends MouseMotionAdapter{
    	public void mouseMoved(MouseEvent e){
    		int mx=e.getX();
    		int my=e.getY();
    		if(mx>830 && mx<BreakOut.WIDTH && my>5 && my<28){
    			menuHover=true;
    		}else{
    			menuHover=false;
    		}
    	}
    }
    class ScheduleTask extends TimerTask {
        public void run() {        	
        	if(gameRunning){
        		/*Run ball*/
        		for(int i=0;i<ballNum;i++){
        			if(balls[i].isDestroyed()==false){
        				balls[i].move();
        			}
        		}
        		/*Bonus*/
        		if(isSetAddBall){
        			bonusAddBall.move();
        		}
        		if(isSetAddLive){
        			bonusAddLive.move();
        		}
        		paddle.move();
        		checkCollision();        		
        	}        	
        	repaint();
        }
    }    
    public void checkCollision() {    	
        for(int i=0;i<ballNum;i++){
        	if (balls[i].isDestroyed()==false && balls[i].getRect().getMaxY() > BreakOut.HEIGHT) {
        		balls[i].setDestroyed(true);
        		ballLeft--;        		
        	}
        }
        if(ballLeft==0){
        	lives--;
        	if(lives!=0){
        		balls = new Ball[BALL_MAX];
        		for(int i=0;i<BALL_MAX;i++){
        			balls[i]=new Ball();
        		}
        		ballNum=1;
        		ballLeft=1;
        		balls[0].setX(paddle.getX()+paddle.getWidth()/2);        		
        		nextlive=true;
        		timer.cancel();        		        	
        	}
        }
        if(lives==0){
        	/* Cho nay la chet. Goi den ham stopGame()*/
        	stopGame();
        }
        if(stage.isStageEmpty()){
        	finalMessage = "Victory";
        	passStage=true;
        	if(curStage<MAX_STAGE){
        		curStage++;        	
        		timer.cancel();
        		nextStageGame();
        	}else{
            stopGame();
        	}
        }
        /*Tat ca bong dap thanh */
        for(int i=0;i<ballNum;i++){
        	if ((balls[i].getRect()).intersects(paddle.getRect())) {        
        		Ball ball=balls[i];
        		
        		if(balls[i].getYDir()>0){
        			Audio.ballPaddle.playAudio();
        		}
        		        		
        		int paddleLPos = (int)paddle.getRect().getMinX();
        		int ballLPos = (int)ball.getRect().getMinX();
        		int distance=ballLPos+ball.getWidth()/2-paddleLPos;
        		int width=paddle.getWidth();
        		if(distance<width/6){
        			ball.setXDir(-1*(ball.getCurrentDir()+1));
        			ball.setYDir(-1*(ball.getCurrentDir()-1));
        		}else if(distance<width/3){
        			ball.setXDir(-1*ball.getCurrentDir());
        			ball.setYDir(-1*ball.getCurrentDir());
        		}else if(distance<width/2){
        			ball.setXDir(-1*(ball.getCurrentDir()-1));
        			ball.setYDir(-1*(ball.getCurrentDir()+1));
        		}else if(distance<2*width/3){
        			ball.setXDir(ball.getCurrentDir()-1);
        			ball.setYDir(-1*(ball.getCurrentDir()+1));
        		}else if(distance<5*width/6){
        			ball.setXDir(ball.getCurrentDir());
        			ball.setYDir(-1*ball.getCurrentDir());
        		}else{
        			ball.setXDir(ball.getCurrentDir()+1);
        			ball.setYDir(-1*(ball.getCurrentDir()-1));
        		}
            }  
        }
        ///////////////////
        stage.stageCollision(balls,ballNum);
        
        /*Bonus Area*/
        /*Add Ball*/
        /*Thanh dap voi bonus them bong */
        if(isSetAddBall && bonusAddBall.getRect().intersects(paddle.getRect())){
        	ballNum+=SET_BALL;
        	ballLeft+=SET_BALL;
        	isSetAddBall=false;
        }
        /*Add Live*/
        /*Thanh dap voi bonus mang*/
        if(isSetAddLive && !bonusAddLive.getIsToDie() && bonusAddLive.getRect().intersects(paddle.getRect())){
        	lives++;
        	bonusAddLive.toDie();        	
        }
        /*Cho live+1 chay den toa do 450*/
        if(isSetAddLive && bonusAddLive.getIsToDie() && bonusAddLive.getY()==450){
    		bonusAddLive.setDestroyed(true);
    		isSetAddLive=false;
    	}        	
    }    
}
