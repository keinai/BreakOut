package FinalVersion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class OptionBoard extends JTabbedPane{
	String menusrc="images/Ver1/back_to_menu.gif";
	String bgsrc="images/Ver1/helpbg2.jpg";
	String optionsrc="images/Ver1/optionbg.png";
	Image menuimg,bgimg,optionimg;
	
	public OptionBoard(){		
		ImageIcon tmp=new ImageIcon(menusrc);
		menuimg=tmp.getImage();
		tmp=new ImageIcon(bgsrc);
		bgimg=tmp.getImage();
		tmp=new ImageIcon(optionsrc);
		optionimg=tmp.getImage();
		
		setBounds(80,40,BreakOut.WIDTH-160,BreakOut.HEIGHT-100);
		addTab("____Level____",new LevelOption());
		addTab("____KeyBoard____",new KeyBoardOption());		
		addMouseListener(new MouseHandler());
	}
	private class KeyBoardOption extends JPanel{
		public KeyBoardOption(){			
			setBackground(new Color(0,true));
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(bgimg,0,0,BreakOut.WIDTH-160,BreakOut.HEIGHT-100,null);
			g.drawImage(optionimg, 20, 20, BreakOut.WIDTH-200, BreakOut.HEIGHT-200,null);
			g.drawImage(menuimg,580,430,150,40,null);
			
		}
	}
	private class LevelOption extends JPanel implements ActionListener{
		Timer timer;
		public LevelOption(){
			timer=new Timer(100,this);			
			timer.start();
			setBackground(new Color(0,true));
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(bgimg,0,0,BreakOut.WIDTH-160,BreakOut.HEIGHT-100,null);
			if(BreakOut.GAMELEVEL==1){
				drawLevel(g,1,1);
				drawLevel(g,2,0);
				drawLevel(g,3,0);
				drawLevel(g,4,0);
				drawLevel(g,5,0);
			}else if(BreakOut.GAMELEVEL==2){
				drawLevel(g,1,1);
				drawLevel(g,2,1);
				drawLevel(g,3,0);
				drawLevel(g,4,0);
				drawLevel(g,5,0);
			}else if(BreakOut.GAMELEVEL==3){
				drawLevel(g,1,1);
				drawLevel(g,2,1);
				drawLevel(g,3,1);
				drawLevel(g,4,0);
				drawLevel(g,5,0);
			}else if(BreakOut.GAMELEVEL==4){
				drawLevel(g,1,1);
				drawLevel(g,2,1);
				drawLevel(g,3,1);
				drawLevel(g,4,1);
				drawLevel(g,5,0);
			}else if(BreakOut.GAMELEVEL==5){
				drawLevel(g,1,1);
				drawLevel(g,2,1);
				drawLevel(g,3,1);
				drawLevel(g,4,1);
				drawLevel(g,5,1);
			}
			drawText(g);
			drawGameLevel(g);
			g.drawImage(menuimg,580,430,150,40,null);
		}
		public void actionPerformed(ActionEvent e){
			repaint();
		}
		public void drawLevel(Graphics g,int level,int flag){
			int x,y,width,height;
			Color colorset=Color.GRAY;
			x=y=height=0;
			width=40;
			switch(level){
			case 1:
				x=270;y=300;height=40;colorset=Color.MAGENTA;
				break;
			case 2:
				x=310;y=260;height=80;colorset=Color.BLUE;
				break;
			case 3:
				x=350;y=220;height=120;colorset=Color.GREEN;
				break;
			case 4:
				x=390;y=180;height=160;colorset=Color.ORANGE;
				break;
			case 5:
				x=430;y=140;height=200;colorset=Color.RED;
				break;
			}			
			//Colorful			
			g.setColor(colorset);							
			if(flag==0)
				g.drawRect(x, y, width, height);
			else
				g.fillRect(x,y,width,height);
		}
		public void drawText(Graphics g){
			Font font=new Font("Verdana",Font.BOLD,18);			
			g.setFont(font);
			g.setColor(Color.MAGENTA);
			g.drawString("1", 280, 360);
			g.setColor(Color.BLUE);
			g.drawString("2", 320, 360);
			g.setColor(Color.GREEN);
			g.drawString("3", 360, 360);
			g.setColor(Color.ORANGE);
			g.drawString("4", 400, 360);
			g.setColor(Color.RED);
			g.drawString("5", 440, 360);
		}
		public void drawGameLevel(Graphics g){
			Font font=new Font("Verdana",Font.BOLD,30);			
			g.setFont(font);
			g.setColor(Color.RED);
			g.drawString("LEVEL",550,200);
			font=new Font("Verdana",Font.BOLD,40);
			g.drawString(BreakOut.GAMELEVEL+"",580,250);
		}
	}
	private class MouseHandler extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			int mx=e.getX();
			int my=e.getY();
			if(mx>580 && mx< 580+150 && my>450 && my<450+40){
				BreakOut.optionBoard.setVisible(false);
				BreakOut.showMenuButton();
			}else if(mx>270 && mx<310 && my>320 && my<360){
				BreakOut.GAMELEVEL=1;				
			}else if(mx>310 && mx<350 && my>280 && my<360){
				BreakOut.GAMELEVEL=2;
			}else if(mx>350 && mx<390 && my>240 && my<360){
				BreakOut.GAMELEVEL=3;
			}else if(mx>390 && mx<430 && my>200 && my<360){
				BreakOut.GAMELEVEL=4;
			}else if(mx>430 && mx<470 && my>160 && my<360){
				BreakOut.GAMELEVEL=5;
			}			
		}
	}
}
