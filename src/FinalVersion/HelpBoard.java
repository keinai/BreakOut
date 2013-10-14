package FinalVersion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class HelpBoard extends JPanel{
	String bgsrc="images/Ver1/helpbg2.jpg";
	String menusrc="images/Ver1/back_to_menu.gif";	
	Image bgimg,menuimg;
	public HelpBoard(){
		ImageIcon tmp=new ImageIcon(bgsrc);
		bgimg=tmp.getImage();
		tmp=new ImageIcon(menusrc);
		menuimg=tmp.getImage();
		
		addMouseListener(new MouseHandler());
		
		setBounds(80,80,BreakOut.WIDTH-160,BreakOut.HEIGHT-160);
		setBackground(new Color(0,true));
	}
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(bgimg,0,0,BreakOut.WIDTH-160,BreakOut.HEIGHT-160,this);
		g.drawImage(menuimg,580,390,150,40,this);
		
		Font font = new Font("Serif", Font.BOLD, 18);
        g.setColor(Color.RED);
        g.setFont(font);               		
        g.drawString("BREAKOUT GAME", 300, 30);
        
        font = new Font("Serif",Font.BOLD+Font.ITALIC,16);
        g.setColor(Color.BLUE);
        g.setFont(font);
		String helpstr="* Pressing Left or Right Key to move the paddle to ricochet the ball against the bricks and eliminate them. ";				
		g.drawString(helpstr, 20, 60);
		helpstr="* Initially, You have three turns to knock down as many bricks as possible.";
		g.drawString(helpstr, 20, 80);
		helpstr="___Developed by ToyShop Group _ Object Oriented Programming Class _ 2012";
		g.drawString(helpstr, 20, 420);
	}
	private class MouseHandler extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			int mx=e.getX();
			int my=e.getY();
			if(mx>580 && mx<580+150 && my>390 && my<390+40){
				BreakOut.helpBoard.setVisible(false);
				BreakOut.showMenuButton();
			}
		}
	}
}
