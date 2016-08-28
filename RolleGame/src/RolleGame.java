// Alison Wu
// May 24, 2015
// APCS, Chris Robinson

/* 
 * 							~ Rolle and Ms. Pipette ~
 * 
 * This is a side/hidden game I've created to incorporate within another project I am currently working on.
 * Check out the site:    <<<<< http://fhtssa-rolle.webuda.com/ >>>>>
 * 
 * This site was created by Vicky Liu and Alison Wu as a capstone project. It is a simple
 * chatbot created as a java applet as well. The game applet I made is based on the
 * character ROLLE and his volumetric (lover) pipette.
 * 
 * Description:
 * To start, select a suspicious coloured liquid to fill up Rolle.
 * Black out! Help Rolle find Ms. Pipette by moving your mouse around in the darkness.
 * Score is based on how long it takes for you to find her(it?).
 * 
 */

import java.awt.*;
import java.applet.*;
import javax.imageio.ImageIO;

public class RolleGame extends Applet
{
	int appletWidth;
	int appletHeight;
    Image virtualMem;
	Graphics gBuffer;
	//clicking areas
	Rectangle red, blue, green, start, end, next, restart; 
	//scene numbers and backgrounds
	int page, setting;
	//imported images
	Image rolle, rolleRed, rolleBlue, rolleGreen, pipette;
	//coordinates of mouse
	int xMove, yMove;
	//coordinates of Ms.Pipette/goal (random every time)
	int endX,endY;
	//imported sound effects
	AudioClip ding,jingle;
	//timer and scoring variables
	long timeStart,timeEnd, score;
	
	public void init()
	{
		setSize(800,600);
		appletWidth = getWidth();
		appletHeight = getHeight();
		virtualMem = createImage(appletWidth,appletHeight);
		gBuffer = virtualMem.getGraphics();
		
		red = new Rectangle(50,150,100,100);
		blue = new Rectangle(250,150,100,100);
		green = new Rectangle(450,150,100,100);
		start = new Rectangle(5,5,75,50);
		next = new Rectangle(600, 400, 60, 30);
		restart = new Rectangle(600, 400, 101, 30);
		
		page = 0;
		
		//randomize goal location when initialized
		endX = (int) (Math.random() * (appletWidth-50-80)) + 80;
		endY = (int) (Math.random() * (appletHeight-50-60)) + 60;
		end = new Rectangle(endX,endY,50,50);
		
		rolleRed = getImage(getDocumentBase(), "flask red.png");
		rolleBlue = getImage(getDocumentBase(), "flask blue.png");
		rolleGreen = getImage(getDocumentBase(), "flask green.png");
		pipette = getImage(getDocumentBase(), "pipette.png");
		
		ding  = getAudioClip(getDocumentBase(), "ding.wav");
		jingle  = getAudioClip(getDocumentBase(), "jingle.wav");
	}
	
	public void paint(Graphics g)
	{
		//main paint method, controlled by 'page' number, and sets the setting
		switch(page)
		{
			case 0:
				page1(g);
				break;
			case 2:
				setting = 1;
				page2(g);
				break;
			case 3:
				setting = 2;
				page2(g);
				break;
			case 4:
				setting = 3;
				page2(g);
				break;
			case 5:
				page3(g);
				break;
		}
	}
	
	public void page1(Graphics g)
	{
		setting0();
		g.drawImage(virtualMem,0,0,this);
	}
	public void setting0()
	{
		gBuffer.setColor(Color.white);
		gBuffer.fillRect(0,0,appletWidth,appletHeight);
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Courier", Font.BOLD, 40));
		gBuffer.drawString("Select a colour:",155,90);
		gBuffer.setColor(Color.red);
		gBuffer.fillRect(50,150,100,100);
		gBuffer.setColor(Color.blue);
		gBuffer.fillRect(250,150,100,100);
		gBuffer.setColor(Color.green);
		gBuffer.fillRect(450,150,100,100);
	}
	public boolean mouseDown(Event e, int x, int y)
	{
		//mouse-click actions, dependent on which page currently displayed
		if(red.contains(x,y) && page < 1)
		{
			rolle = rolleRed;
			page = 2;
			ding.play();
		}
		else if(blue.contains(x,y) && page < 1)
		{
			rolle = rolleBlue;
			page = 2;
			ding.play();
		}
		else if(green.contains(x,y) && page < 1)
		{
			rolle = rolleGreen;
			page = 2;
			ding.play();
		}
		
		else if(page == 3)
			page = 3;
		
		else if(next.contains(x,y) && page == 4)
		{
			page = 5;
			ding.play();
		}
		else if(restart.contains(x,y) && page == 5)
		{
			page = 0;
			ding.play();
			endX = (int) (Math.random() * (appletWidth-50-80)) + 80;
			endY = (int) (Math.random() * (appletHeight-50-60)) + 60;
			end = new Rectangle(endX,endY,50,50);
		}
		else if(page == 4)
		{
			page = 4;
			jingle.play();
		}
		
		repaint();
		return true;
	}
	public void page2(Graphics g)
	{
		//chooses setting
		if(setting == 1)
		{
			setting1();
		}
		else if(setting == 2)
		{
			setting2();
		}
		else if(setting == 3)
		{
			setting3();
		}
		
		gBuffer.drawImage(rolle,xMove-25,yMove-30,this); //draws avatar that moves with mouse pointer
		g.drawImage(virtualMem,0,0,this);
	}
	public boolean mouseMove(Event e, int x, int y)
	{
		xMove = x;
		yMove = y;
		
		if(start.contains(x,y) && page == 2)
		{
			page = 3;
			timeStart = System.currentTimeMillis();
		}
		else if(end.contains(x,y) && page == 3)
		{
			page = 4;
			timeEnd = System.currentTimeMillis();
			score = timeEnd-timeStart;
		}
		
		repaint();
		return true;
	}
	public void setting1()
	{
		gBuffer.setColor(Color.white);
		gBuffer.fillRect(0,0,appletWidth,appletHeight);
		gBuffer.drawImage(pipette,10,20,this);
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.ITALIC, 15));
		gBuffer.drawString("come here bae",65,45);
	}
	public void setting2()
	{
		gBuffer.setColor(Color.black);
		gBuffer.fillRect(0,0,appletWidth, appletHeight);
		gBuffer.setColor(Color.white);
		gBuffer.setFont(new Font("Arial", Font.ITALIC, 20));
		gBuffer.drawString("AAHH! who turned off the lights?!",150,200);
		gBuffer.drawString("(help Rolle find his pipette)",160,230);
		gBuffer.setFont(new Font("Arial", Font.ITALIC, 15));
		gBuffer.drawString("(by hovering the mouse around in the dark)",160,250);
	}
	public void setting3()
	{	
		gBuffer.setColor(Color.white);
		gBuffer.fillRect(0,0,appletWidth, appletHeight);
		gBuffer.drawImage(pipette,endX,endY,this);
		gBuffer.setColor(Color.red);
		gBuffer.setFont(new Font("Arial", Font.BOLD, 30));
		gBuffer.drawString("<3",endX+20,endY);
		gBuffer.setFont(new Font("Arial", Font.BOLD, 20));
		gBuffer.drawString("Thanks for finding me :D", 100,100);
		gBuffer.setFont(new Font("Arial", Font.PLAIN, 18));
		gBuffer.drawString("(or at least turning on the lights)", 100,130);
		
		gBuffer.setColor(Color.orange);
		gBuffer.drawRoundRect(600, 400, 60, 30, 10, 10);
		gBuffer.setFont(new Font("Courier", Font.PLAIN, 20));
		gBuffer.drawString("NEXT", 605,423);
	}
	
	public void page3(Graphics g)
	{
		// (end screen) ANIMATION: heart scrolling down randomly
		
		int x = (int) (Math.random() * (appletWidth-90));
		
		for(int y = -90; y < 600; y++)
		{
			setting4();
			drawHeart(g,x,y);
			delay(3);
		}
	}
	public void setting4()
	{
		gBuffer.setColor(Color.white);
		gBuffer.fillRect(0,0,appletWidth, appletHeight);
		gBuffer.setColor(new Color(0,150,0));
		gBuffer.setFont(new Font("Courier", Font.BOLD, 25));
		gBuffer.drawString("SCORE = -" + score, 100,170);
		gBuffer.setFont(new Font("Courier", Font.ITALIC, 20));
		gBuffer.drawString("(yes it is negative, try and beat my highscore of 0 :P)", 100,200);
		
		gBuffer.setColor(Color.red);
		gBuffer.drawRoundRect(600, 400, 101, 30, 10, 10);
		gBuffer.setFont(new Font("Courier", Font.PLAIN, 20));
		gBuffer.drawString("RESTART", 605,423);
		gBuffer.setFont(new Font("Courier", Font.ITALIC, 17));
		gBuffer.drawString("click and wait until animation ends", 450,450);
	}
	public void drawHeart(Graphics g, int x, int y)
	{
		gBuffer.setColor(Color.magenta);
		gBuffer.fillOval(x, 0+y, 50, 50);
		gBuffer.fillOval(x+40, 0+y, 50, 50);
		Polygon heart = new Polygon();
		heart.addPoint(x+90,32+y);
		heart.addPoint(x+50,95+y);
		heart.addPoint(x,32+y);
		gBuffer.fillPolygon(heart);
		g.drawImage(virtualMem,0,0,this);
	}
	
	public void delay(int n)
	{
		//time delay method for animation
		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
			endDelay = System.currentTimeMillis();
	}
	public void update(Graphics g)
	{
		paint(g);
	}
}