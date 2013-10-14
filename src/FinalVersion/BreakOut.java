package FinalVersion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BreakOut extends JFrame {
	
    /* Constant */
	public static final int WIDTH = 900;
    public static final int HEIGHT = 600;    
    public static final int DIFX=5;
    public static final int DIFY=30;    
    public static final int BALL_RESET_X = WIDTH/2-50;
    public static final int BALL_RESET_Y = 540;
    public static final int PADDLE_STEP = 7;
    public static final int BALL_RIGHT = 280; 
    
    /* Implementation */
	public static int GAMELEVEL=1;
	public static int BALL_DX = 2;
    public static int BALL_DY = 2;
	
	public static GameBoard gameBoard;
	public static HelpBoard helpBoard;
	public static OptionBoard optionBoard;
	public static MultiPlayerBoard multiPlayerBoard;
	public static MultiPlayerNameInput multiPlayerNameInput;
		
	public static JButton btSingleNewGame;
	public static JButton btSingleResume;
	public static JButton btBackSingle;
	public static JButton btMultiNewGame;
	public static JButton btMultiResume;
	public static JButton btBackMulti;
	public static JButton btSinglePlayer;
	public static JButton btMultiPlayers;
	public static JButton btOption;
	public static JButton btHelp;
	public static JButton btExit;
	
	
	private String bgsrc="images/Ver1/menubg3.jpg";
	private String newgamesrc="images/Ver1/btNewGame.jpg";
	private String resumesrc="images/Ver1/btResumeGameActive.jpg";
	private String singleplayersrc="images/Ver1/btSinglePlayer.jpg";
	private String multiplayerssrc="images/Ver1/btMultiPlayers.jpg";
	private String optionsrc="images/Ver1/btOption.jpg";
	private String helpsrc="images/Ver1/btHelp.jpg";
	private String exitsrc="images/Ver1/btExit.jpg";
	private String backsrc="images/Ver1/btBack.jpg";	
    
	public BreakOut()
    {    	    
    	ImageIcon img=new ImageIcon(bgsrc);
    	this.setContentPane(new JLabel(img));
    	
    	setLayout(null);
    	
    	/* Sound Definition*/
    	Audio.ballBrick=new Audio("./sound/smallExplode.wav");
    	Audio.ballPaddle=new Audio("./sound/EggLands.wav");
    	Audio.ballBrickNotDie=new Audio("./sound/BreakEgg.wav");
    	
    	addMenuButton();
    	addSinglePlayerButton();
    	hideSingleButton();
    	addMultiPlayersButton();
    	hideMultiButton();
    	
    	gameBoard=new GameBoard();
    	add(gameBoard);
    	gameBoard.setVisible(false);
    	
    	multiPlayerBoard=new MultiPlayerBoard();
    	add(multiPlayerBoard);
    	multiPlayerBoard.setVisible(false);
    	
    	optionBoard=new OptionBoard();
    	add(optionBoard);
    	optionBoard.setVisible(false);
    	
    	helpBoard=new HelpBoard();
    	add(helpBoard);
    	helpBoard.setVisible(false);
    	
    	multiPlayerNameInput=new MultiPlayerNameInput();
    	add(multiPlayerNameInput);
    	multiPlayerNameInput.setVisible(false);
    	
    	//remove(multiPlayerNameInput);
    	
        setTitle("ToyShop BreakOut Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BreakOut.WIDTH, BreakOut.HEIGHT);
        setLocationRelativeTo(null);
        //setIgnoreRepaint(true);
        setResizable(false);
        setVisible(true);
    }   
    public void addMenuButton(){   
		
		ImageIcon singleplayerimg=new ImageIcon(singleplayersrc);
		btSinglePlayer=new JButton("Single Player",singleplayerimg);		
		btSinglePlayer.addActionListener(new MenuButtonAction());
		btSinglePlayer.setBounds(700,290,140,35);
		add(btSinglePlayer);
		
		ImageIcon multiplayersimg=new ImageIcon(multiplayerssrc);
		btMultiPlayers=new JButton("Multi Players",multiplayersimg);		
		btMultiPlayers.addActionListener(new MenuButtonAction());
		btMultiPlayers.setBounds(700,340,140,35);
		add(btMultiPlayers);
		
		ImageIcon optionimg=new ImageIcon(optionsrc);
		btOption=new JButton("Option",optionimg);
		btOption.addActionListener(new MenuButtonAction());
		btOption.setBounds(700,390,140,35);
		add(btOption);
		
		ImageIcon helpimg=new ImageIcon(helpsrc);
    	btHelp=new JButton("Help",helpimg);
		btHelp.addActionListener(new MenuButtonAction());
		btHelp.setBounds(700,440,140,35);
		add(btHelp);
		
		ImageIcon exitimg=new ImageIcon(exitsrc);
    	btExit=new JButton("Exit",exitimg);
		btExit.addActionListener(new MenuButtonAction());
		btExit.setBounds(700,490,140,35);
		add(btExit);
    }
    
    public void addSinglePlayerButton(){
    	
    	ImageIcon newgameimg=new ImageIcon(newgamesrc);
    	btSingleNewGame=new JButton("New Game Single",newgameimg);
		btSingleNewGame.addActionListener(new MenuButtonAction());
		btSingleNewGame.setBounds(700,290,140,35);
		add(btSingleNewGame);
		
		ImageIcon singleplayerimg=new ImageIcon(resumesrc);
    	btSingleResume=new JButton("Resume Game Single",singleplayerimg);
    	btSingleResume.addActionListener(new MenuButtonAction());
    	btSingleResume.setBounds(700,340,140,35);
		add(btSingleResume);
		
		ImageIcon backimg=new ImageIcon(backsrc);
		btBackSingle=new JButton("Back to Menu",backimg);
		btBackSingle.addActionListener(new MenuButtonAction());
		btBackSingle.setBounds(700,390,140,35);
		add(btBackSingle);
    }
    
public void addMultiPlayersButton(){
    	
    	ImageIcon newgameimg=new ImageIcon(newgamesrc);
    	btMultiNewGame=new JButton("New Game Multi",newgameimg);
    	btMultiNewGame.addActionListener(new MenuButtonAction());
    	btMultiNewGame.setBounds(700,290,140,35);
		add(btMultiNewGame);
		
		ImageIcon singleplayerimg=new ImageIcon(resumesrc);
    	btMultiResume=new JButton("Resume Game Multi",singleplayerimg);
    	btMultiResume.addActionListener(new MenuButtonAction());
    	btMultiResume.setBounds(700,340,140,35);
		add(btMultiResume);	
		
		ImageIcon backimg=new ImageIcon(backsrc);
		btBackMulti=new JButton("Back to Name Input",backimg);
		btBackMulti.addActionListener(new MenuButtonAction());
		btBackMulti.setBounds(700,390,140,35);
		add(btBackMulti);
    }
    
    public static void hideMenuButton(){
    	btSinglePlayer.setVisible(false);
    	btMultiPlayers.setVisible(false);
    	btOption.setVisible(false);
    	btHelp.setVisible(false);
    	btExit.setVisible(false);
    }
    
    public static void showMenuButton(){
    	btSinglePlayer.setVisible(true);
    	btMultiPlayers.setVisible(true);
    	btOption.setVisible(true);
    	btHelp.setVisible(true);
    	btExit.setVisible(true);
    }
    
    public static void hideSingleButton(){
    	btSingleNewGame.setVisible(false);
    	btSingleResume.setVisible(false);
    	btBackSingle.setVisible(false);
    }
    
    public static void showSingleButton(){
    	btSingleNewGame.setVisible(true);
    	btSingleResume.setVisible(true);
    	btBackSingle.setVisible(true);
    }
    
    public static void hideMultiButton(){
    	btMultiNewGame.setVisible(false);
    	btMultiResume.setVisible(false);
    	btBackMulti.setVisible(false);
    }
    
    public static void showMultiButton(){
    	btMultiNewGame.setVisible(true);
    	btMultiResume.setVisible(true);
    	btBackMulti.setVisible(true);
    }
    
    public void paint(Graphics g){
    	super.paint(g);
    }
    
    public static BreakOut MainFrame;
    public static void main(String[] args){
    	MainFrame=new BreakOut();    	
    }   
    
    private class MenuButtonAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String btname=e.getActionCommand();
			if(btname.equals("Single Player")){				
				hideMenuButton();
				showSingleButton();
				btSingleResume.setEnabled(false);
			}else if(btname.equals("Option")){
				hideMenuButton();
				optionBoard.setVisible(true);				
			}else if(btname.equals("Multi Players")){
				hideMenuButton();
				multiPlayerNameInput.setVisible(true);
			}else if(btname.equals("Help")){
				hideMenuButton();
				helpBoard.setVisible(true);
			}else if(btname.equals("Exit")){
				System.exit(1);
			}else if(btname.equals("New Game Single")){
				hideSingleButton();
				gameBoard.stopGame();
				gameBoard.replayGame();
				gameBoard.setVisible(true);				
				gameBoard.requestFocus();
			}else if(btname.equals("Resume Game Single")){
				hideSingleButton();
				gameBoard.setVisible(true);				
				gameBoard.requestFocus();
			}else if(btname.equals("Back to Menu")){
				hideSingleButton();
				showMenuButton();								
			}else if(btname.equals("New Game Multi")){
				hideMultiButton();
				multiPlayerBoard.stopGame();
				multiPlayerBoard.replayGame();
				multiPlayerBoard.setVisible(true);				
				multiPlayerBoard.requestFocus();
			}else if(btname.equals("Resume Game Multi")){
				hideMultiButton();
				multiPlayerBoard.setVisible(true);				
				multiPlayerBoard.requestFocus();
			}else if(btname.equals("Back to Name Input")){
				hideMultiButton();
				multiPlayerNameInput.setVisible(true);
			}
		}
	}
    public class MultiPlayerNameInput extends JPanel{
    	JTextField player1,player2;
    	JButton nextbt,backbt;
    	String nextsrc="images/Ver1/Next-icon.png";
    	String backsrc="images/Ver1/Back-icon.png";    	
    	public MultiPlayerNameInput(){
    		
    		setBounds(700,290,180,250);
    		setLayout(null);
    		
    		JLabel p=new JLabel("Player Name");
    		p.setForeground(Color.RED);
    		JLabel p1=new JLabel("Player1");
    		p1.setForeground(Color.BLUE);
    		JLabel p2=new JLabel("Player2");
    		p2.setForeground(Color.BLUE);
    		player1=new JTextField("Player1",30);
    		player2=new JTextField("Player2",30);
    		ImageIcon nextimg=new ImageIcon(nextsrc);
    		ImageIcon backimg=new ImageIcon(backsrc);
    		nextbt=new JButton("Next",nextimg);
    		backbt=new JButton("Back",backimg);
    		nextbt.addActionListener(new PlayerNameInputAction());
    		backbt.addActionListener(new PlayerNameInputAction());
    		
    		p.setBounds(40,5,120,25);
    		p1.setBounds(10,30,120,25);
    		p2.setBounds(10,90,120,25);
    		player1.setBounds(10,60, 140, 25);
    		player2.setBounds(10,120, 140, 25);
    		nextbt.setBounds(120,190,50,50);
    		backbt.setBounds(10,190,50,50);
    		
    		add(p);
    		add(p1);
    		add(p2);
    		add(player1);
    		add(player2);
    		add(nextbt);
    		add(backbt);    		
    	}
    	public String getName1(){
    		return player1.getText();
    	}
    	public String getName2(){
    		return player2.getText();
    	}
    	private class PlayerNameInputAction implements ActionListener{
    		public void actionPerformed(ActionEvent e){
    			String btName=e.getActionCommand();
    			if(btName.equals("Next")){
    				BreakOut.multiPlayerNameInput.setVisible(false);        				
    				BreakOut.showMultiButton();
    				BreakOut.btMultiResume.setEnabled(false);
    			}else if(btName.equals("Back")){
    				BreakOut.multiPlayerNameInput.setVisible(false);
    				BreakOut.showMenuButton();
    			}
    		}
    	}
    }
}
