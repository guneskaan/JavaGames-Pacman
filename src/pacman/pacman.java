package pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class pacman extends JPanel implements ActionListener,KeyListener{
	static int row,column,pacx,pacy;
	static int array[][];
	static JFrame f = new JFrame();
	static BufferedImage full,rightopen,downopen,leftopen,upopen,rightopenwide,leftopenwide,downopenwide,upopenwide;
	static BufferedImage ghost1,ghost2,ghost3,ghosta,ghostb,ghostc,ghostx,ghosty,ghostz,ghost4,ghost5,ghost6;
	static boolean move1=true,moveright=false,moveleft=false,movedown=false,moveup=false;
	static int direction=2,dghost=1,dghosta=1,dghostx=1,dghost4=1;
	static int move=1;
	static int turn=1;
	static int foodcounter=1,maxfood=0;
    static int score=0,finalscore=0;
    static boolean game=true,cheat=false;
    static int ghost1x,ghost1y,ghostax,ghostay,ghostxx,ghostxy,ghost4x,ghost4y;
    static int ghostmovetimer=0,ghostmovetimera=0;
    static boolean ghostgoleft=true,ghostgolefta=true,ghostgoleftx=true,ghostgoleft4=true;
	public static void main(String[] args) throws IOException {
		read();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pacman p = new pacman();
		p.init();
		f.add(p);
		f.setBackground(Color.black);
		f.setSize(650,550);
		f.setVisible(true);
		Timer t = new Timer(50, p);
		t.start();
	}
	public void init() {
		f.addKeyListener(this);
		f.setFocusable(true);
	}
	public void paintComponent(Graphics g) {
		if(game==true){
		for (int i = 0; i<array[0].length; i++){
	        for (int j = 0; j<array.length; j++){
	        	g.setColor(Color.blue);
	        	if(array[j][i]==1)g.drawRect(i*25, j*25, 25, 25);
	        	g.setColor(Color.yellow);
	        	if(array[j][i]==0)g.fillOval((i*25)+9, (j*25)+9, 5, 5);
	        	
	            if(array[j][i]==2)
	            {
	            	if(move==1)g.drawImage(ghost1, (i*25)+4, (j*25)+4, 20, 20, this);
                	else if(move==2)g.drawImage(ghost2, (i*25)+4, (j*25)+4, 20, 20, this);
                	else if(move==3)g.drawImage(ghost3, (i*25)+4, (j*25)+4, 20, 20, this);
	            }
	            if(array[j][i]==4)
	            {
	            	if(move==1)g.drawImage(ghost4, (i*25)+4, (j*25)+4, 20, 20, this);
                	else if(move==2)g.drawImage(ghost5, (i*25)+4, (j*25)+4, 20, 20, this);
                	else if(move==3)g.drawImage(ghost6, (i*25)+4, (j*25)+4, 20, 20, this);
	            }
	            if(array[j][i]==5)
	            {
	            	if(move==1)g.drawImage(ghostx, (i*25)+4, (j*25)+4, 20, 20, this);
                	else if(move==2)g.drawImage(ghosty, (i*25)+4, (j*25)+4, 20, 20, this);
                	else if(move==3)g.drawImage(ghostz, (i*25)+4, (j*25)+4, 20, 20, this);
	            }
	            if(array[j][i]==6)
	            {
	            	if(move==1)g.drawImage(ghosta, (i*25)+4, (j*25)+4, 20, 20, this);
                	else if(move==2)g.drawImage(ghostb, (i*25)+4, (j*25)+4, 20, 20, this);
                	else if(move==3)g.drawImage(ghostc, (i*25)+4, (j*25)+4, 20, 20, this);
	            }
	           	if(array[j][i]==3)
	           		{
	           	         
        
	                     if(direction==1){
	                    	if(move==1)g.drawImage(full, (i*25)+4, (j*25)+4, 20, 20, this);
	                    	else if(move==2)g.drawImage(upopen, (i*25)+4, (j*25)+4, 20, 20, this);
	                    	else if(move==3)g.drawImage(upopenwide, (i*25)+4, (j*25)+4, 20, 20, this);
	                     }
	                     else if(direction==2){
	                      	if(move==1)g.drawImage(full, (i*25)+4, (j*25)+4, 20, 20, this);
	                      	else if(move==2)g.drawImage(rightopen, (i*25)+4, (j*25)+4, 20, 20, this);
	                      	else if(move==3)g.drawImage(rightopenwide, (i*25)+4, (j*25)+4, 20, 20, this);
	                                }
	                     else if(direction==3){
	                      	if(move==1)g.drawImage(full, (i*25)+4, (j*25)+4, 20, 20, this);
	                      	else if(move==2)g.drawImage(downopen, (i*25)+4, (j*25)+4, 20, 20, this);
	                      	else if(move==3)g.drawImage(downopenwide, (i*25)+4, (j*25)+4, 20, 20, this);
	                                }
	                     else if(direction==4){
	                      	if(move==1)g.drawImage(full, (i*25)+4, (j*25)+4, 20, 20, this);
	                      	else if(move==2)g.drawImage(leftopen, (i*25)+4, (j*25)+4, 20, 20, this);
	                      	else if(move==3)g.drawImage(leftopenwide, (i*25)+4, (j*25)+4, 20, 20, this);
	                                }
	                     
	           		}

	        }
		}
		}
               else if(game==false){
            
            	   finalscore=score;
            		g.setColor(Color.white);
            	   Font font = new Font("Comic Sans", Font.PLAIN, 96);
                   g.setFont(font);
            	   if(cheat==false)g.drawString("Game over!", 100, 200);
            	   if(cheat==true)g.drawString("YOU WON!", 100, 200);
            	   if(cheat==false)g.drawString("Score = "+finalscore, 100, 300);
            	   if(cheat==true)g.drawString("Score=9000", 100, 300);
            	   Font donovansucks = new Font("Comic Sans", Font.PLAIN, 32);
            	   g.setFont(donovansucks);
            	   g.drawString("(press spacebar to restart)", 100, 500);
            	  
			
		}
		
	}
	public static void read() throws IOException {
		int a, b;
		int rows, columns;
		try {
			ghost1= ImageIO.read(new File("ghost1.png"));
			ghost2= ImageIO.read(new File("ghost2.png"));
			ghost3= ImageIO.read(new File("ghost3.png"));
			ghosta= ImageIO.read(new File("ghosta.png"));
			ghostb= ImageIO.read(new File("ghostb.png"));
			ghostc= ImageIO.read(new File("ghostc.png"));
			ghost4= ImageIO.read(new File("ghost4.png"));
			ghost5= ImageIO.read(new File("ghost5.png"));
			ghost6= ImageIO.read(new File("ghost6.png"));
			ghostx= ImageIO.read(new File("ghostx.png"));
			ghosty= ImageIO.read(new File("ghosty.png"));
			ghostz= ImageIO.read(new File("ghostz.png"));
			full= ImageIO.read(new File("closed.png"));
			rightopen= ImageIO.read(new File("rightopen.png"));
			upopen= ImageIO.read(new File("upopen.png"));
			leftopen= ImageIO.read(new File("leftopen.png"));
			downopen= ImageIO.read(new File("downopen.png"));
			upopenwide= ImageIO.read(new File("upopenwide.png"));
			leftopenwide= ImageIO.read(new File("leftopenwide.png"));
			downopenwide= ImageIO.read(new File("downopenwide.png"));
			rightopenwide= ImageIO.read(new File("rightopenwide.png"));
			FileReader fr=new FileReader("maze.txt");
			BufferedReader br=new BufferedReader(fr);
			String LineIn, LineOut;
			LineIn=br.readLine();
			row=Integer.parseInt(LineIn);
			rows=row;
			LineIn=br.readLine();
			column=Integer.parseInt(LineIn);
			columns=column;
			LineIn=br.readLine();
			array = new int [row][column];
			StringTokenizer tokens=new StringTokenizer(LineIn);
		
			do{
				while(tokens.hasMoreTokens()){
					for (a=0;a<row;++a){
						for (b=0;b<column;b++){
							array[a][b]=Integer.parseInt(tokens.nextToken());
							if(array[a][b]==3){
								pacx=a;
								pacy=b;
							}
							if(array[a][b]==2){
								ghost1x=a;
								ghost1y=b;
							}
							if(array[a][b]==4){
								ghost4x=a;
								ghost4y=b;
							}
							if(array[a][b]==5){
								ghostxx=a;
								ghostxy=b;
							}
							if(array[a][b]==6){
								ghostax=a;
								ghostay=b;
							}
							if(array[a][b]==0){
								maxfood=maxfood+1;}
						}
						LineIn=br.readLine();
						if(LineIn!=null)
							tokens=new StringTokenizer(LineIn);
					}
					maxfood=maxfood-45;
				}
			}
			while(LineIn!=null); 
			br.close();
			fr.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("file not found");
		}
		
	
}
	@Override
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		if (key == KeyEvent.VK_RIGHT && array[pacx][pacy+1]!=1){
			moveleft=false;
			moveright=true;
			moveup=false;
			movedown=false;
			direction=2;
		}
		if (key == KeyEvent.VK_LEFT && array[pacx][pacy-1]!=1){
			moveleft=true;
			moveright=false;
			moveup=false;
			movedown=false;
			direction=4;
		}
		if (key == KeyEvent.VK_UP && array[pacx-1][pacy]!=1){
			moveup=true;
			moveleft=false;
			moveright=false;
			movedown=false;
			direction=1;
		}
		if (key == KeyEvent.VK_M){
			cheat=true;
			game=false;
			
		}
		if (key == KeyEvent.VK_DOWN && array[pacx+1][pacy]!=1){
			
			moveup=false;
			movedown=true;
			moveleft=false;
			moveright=false;
			direction=3;
		}
		if (key == KeyEvent.VK_SPACE && game==false)
			try {
				restart();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void keyReleased(KeyEvent k) {
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void actionPerformed(ActionEvent arg0) {
		countfood();
			
		
		if(turn==1)
			{
			
			    if(ghostgoleft==true){
			    	ghostymove();
			    	ghost1move();
			    }
			    else if(ghostgoleft==false){
			    	ghost2move();
			    	
			    	ghostxmove();
			    }
				if(ghostgolefta==true) {
					ghost4move();
					ghostamove();
				}
				else if(ghostgolefta==false){
					ghostbmove();
					ghost5move();
				}
			
			
			turn=turn+1;
			switchup();
			f.repaint();
			}
		else{
		pacmove();
		turn=turn+1;
		if(turn==3)turn=1;
	  }
		if(score==maxfood)game=false;
	}
	public void switchup(){
		if(move==1)move=2;
		else if(move==2)move=3;
		else if(move==3)move=1;
	}
	public void countfood(){
		foodcounter=0;
		for (int a=0;a<array.length;++a){
			for (int b=0;b<array.length;b++){
			
				 if(array[a][b]==0)foodcounter=foodcounter+1;
			}
			}
		//score=maxfood-foodcounter;
	}
	public void restart() throws IOException{
		
		read();
		score=0;
		finalscore=0;
		game=true;
	}
	public void moveleft(){
		if(array[ghost1x][ghost1y-1]==9)array[ghost1x][ghost1y]=9;
		else if(array[ghost1x][ghost1y-1]==0)array[ghost1x][ghost1y]=0;
		else if(array[ghost1x][ghost1y-1]==3||array[ghost1x][ghost1y]==3)game=false;
		array[ghost1x][ghost1y-1]=2;
	
	     ghost1y=ghost1y-1;
	     dghost=4;
	}
	public void movelefta(){
		if(array[ghostax][ghostay-1]==9)array[ghostax][ghostay]=9;
		else if(array[ghostax][ghostay-1]==0)array[ghostax][ghostay]=0;
		else if(array[ghostax][ghostay-1]==3||array[ghostax][ghostay]==3)game=false;
		array[ghostax][ghostay-1]=6;
	
	     ghostay=ghostay-1;
	     dghosta=4;
	}
	public void moveleftx(){
		if(array[ghostxx][ghostxy-1]==9)array[ghostxx][ghostxy]=9;
		else if(array[ghostxx][ghostxy-1]==0)array[ghostxx][ghostxy]=0;
		else if(array[ghostxx][ghostxy-1]==3||array[ghostxx][ghostxy]==3)game=false;
		array[ghostxx][ghostxy-1]=5;
	
	     ghostxy=ghostxy-1;
	     dghostx=4;
	}
	public void moveleft4(){
		if(array[ghost4x][ghost4y-1]==9)array[ghost4x][ghost4y]=9;
		else if(array[ghost4x][ghost4y-1]==0)array[ghost4x][ghost4y]=0;
		else if(array[ghost4x][ghost4y-1]==3||array[ghost4x][ghost4y]==3)game=false;
		array[ghost4x][ghost4y-1]=4;
	
	     ghost4y=ghost4y-1;
	     dghost4=4;
	}
	 public void moveup(){

		if(array[ghost1x-1][ghost1y]==9)array[ghost1x][ghost1y]=9;
		else if(array[ghost1x-1][ghost1y]==0)array[ghost1x][ghost1y]=0;
		else if(array[ghost1x-1][ghost1y]==3 ||array[ghost1x][ghost1y]==3 || array[ghost1x+1][ghost1y]==3)game=false;
		array[ghost1x-1][ghost1y]=2;
		ghost1x=ghost1x-1;
		dghost=1;
	}
	public void moveupa(){

		if(array[ghostax-1][ghostay]==9)array[ghostax][ghostay]=9;
		else if(array[ghostax-1][ghostay]==0)array[ghostax][ghostay]=0;
		else if(array[ghostax-1][ghostay]==3 ||array[ghostax][ghostay]==3 || array[ghostax+1][ghostay]==3)game=false;
		array[ghostax-1][ghostay]=6;
		ghostax=ghostax-1;
		dghosta=1;
	}
	public void moveupx(){

		if(array[ghostxx-1][ghostxy]==9)array[ghostxx][ghostxy]=9;
		else if(array[ghostxx-1][ghostxy]==0)array[ghostxx][ghostxy]=0;
		else if(array[ghostxx-1][ghostxy]==3 ||array[ghostxx][ghostxy]==3 || array[ghostxx+1][ghostxy]==3)game=false;
		array[ghostxx-1][ghostxy]=5;
		ghostxx=ghostxx-1;
		dghostx=1;
	}
	public void moveup4(){

		if(array[ghost4x-1][ghost4y]==9)array[ghost4x][ghost4y]=9;
		else if(array[ghost4x-1][ghost4y]==0)array[ghost4x][ghost4y]=0;
		else if(array[ghost4x-1][ghost4y]==3 ||array[ghost4x][ghost4y]==3 || array[ghost4x+1][ghost4y]==3)game=false;
		array[ghost4x-1][ghost4y]=4;
		ghost4x=ghost4x-1;
		dghost4=1;
	}
	public void moveright(){
		
		if(array[ghost1x][ghost1y+1]==9)array[ghost1x][ghost1y]=9; 
		else if(array[ghost1x][ghost1y+1]==0)array[ghost1x][ghost1y]=0; 
		else if(array[ghost1x][ghost1y+1]==3 || array[ghost1x][ghost1y]==3)game=false; 
		array[ghost1x][ghost1y+1]=2;
		ghost1y=ghost1y+1;	
		dghost=2;
	}
   public void moverighta(){
		
		if(array[ghostax][ghostay+1]==9)array[ghostax][ghostay]=9; 
		else if(array[ghostax][ghostay+1]==0)array[ghostax][ghostay]=0; 
		else if(array[ghostax][ghostay+1]==3 || array[ghostax][ghostay]==3)game=false; 
		array[ghostax][ghostay+1]=6;
		ghostay=ghostay+1;	
		dghosta=2;
	}
   public void moverightx(){
		
		if(array[ghostxx][ghostxy+1]==9)array[ghostxx][ghostxy]=9; 
		else if(array[ghostxx][ghostxy+1]==0)array[ghostxx][ghostxy]=0; 
		else if(array[ghostxx][ghostxy+1]==3 || array[ghostxx][ghostxy]==3)game=false; 
		array[ghostxx][ghostxy+1]=5;
		ghostxy=ghostxy+1;	
		dghostx=2;
	}
   public void moveright4(){
		
		if(array[ghost4x][ghost4y+1]==9)array[ghost4x][ghost4y]=9; 
		else if(array[ghost4x][ghost4y+1]==0)array[ghost4x][ghost4y]=0; 
		else if(array[ghost4x][ghost4y+1]==3 || array[ghost4x][ghost4y]==3)game=false; 
		array[ghost4x][ghost4y+1]=4;
		ghost4y=ghost4y+1;	
		dghost4=2;
	}
	public void moveback(){
		
		if(array[ghost1x+1][ghost1y]==9)array[ghost1x][ghost1y]=9;		
		else if(array[ghost1x+1][ghost1y]==0)array[ghost1x][ghost1y]=0;
		else if(array[ghost1x+1][ghost1y]==3||array[ghost1x][ghost1y]==3)game=false;
		array[ghost1x+1][ghost1y]=2;
		ghost1x=ghost1x+1;
		dghost=4;
	}
public void movebacka(){
		
		if(array[ghostax+1][ghostay]==9)array[ghostax][ghostay]=9;		
		else if(array[ghostax+1][ghostay]==0)array[ghostax][ghostay]=0;
		else if(array[ghostax+1][ghostay]==3||array[ghostax][ghostay]==3)game=false;
		array[ghostax+1][ghostay]=6;
		ghostax=ghostax+1;
		dghosta=4;
	}
public void movebackx(){
	
	if(array[ghostxx+1][ghostxy]==9)array[ghostxx][ghostxy]=9;		
	else if(array[ghostxx+1][ghostxy]==0)array[ghostxx][ghostxy]=0;
	else if(array[ghostxx+1][ghostxy]==3||array[ghostxx][ghostxy]==3)game=false;
	array[ghostxx+1][ghostxy]=5;
	ghostxx=ghostxx+1;
	dghostx=4;
}
public void moveback4(){
	
	if(array[ghost4x+1][ghost4y]==9)array[ghost4x][ghost4y]=9;		
	else if(array[ghost4x+1][ghost4y]==0)array[ghost4x][ghost4y]=0;
	else if(array[ghost4x+1][ghost4y]==3||array[ghost4x][ghost4y]==3)game=false;
	array[ghost4x+1][ghost4y]=4;
	ghost4x=ghost4x+1;
	dghost4=4;
}
	
	public void ghost1move(){
		ghostmovetimer=ghostmovetimer+1;
		if(ghostmovetimer==40){
			ghostgoleft=false;
			ghostmovetimer=0;
		}
		
		if(dghost==1){
			if(array[ghost1x][ghost1y-1]==9 || array[ghost1x][ghost1y-1]==0)moveleft();		
			else if(array[ghost1x-1][ghost1y]==9 || array[ghost1x-1][ghost1y]==0)moveup();
			else if(array[ghost1x][ghost1y+1]==9 || array[ghost1x][ghost1y+1]==0)moveright();
			else if(array[ghost1x+1][ghost1y]==9 || array[ghost1x+1][ghost1y]==0)moveback();					
		}
		if(dghost==2){
			if(array[ghost1x-1][ghost1y]!=1 )moveup();
			else if(array[ghost1x][ghost1y+1]!=1)moveright();
			else if(array[ghost1x+1][ghost1y]!=1)moveback();	
			else if(array[ghost1x][ghost1y-1]!=1)moveleft();		
					
		}
		if(dghost==3){
	     	if(array[ghost1x][ghost1y+1]!=1)moveright();
	     	else if(array[ghost1x+1][ghost1y]!=1)moveback();
	     	else if(array[ghost1x][ghost1y-1]!=1)moveleft();	
	     	else if(array[ghost1x-1][ghost1y]!=1)moveup();			
		}
		if(dghost==4){
			if(array[ghost1x+1][ghost1y]!=1)moveback();
			else if(array[ghost1x][ghost1y-1]==0 || array[ghost1x][ghost1y-1]==9)moveleft();	
	     	else if(array[ghost1x-1][ghost1y]==0 || array[ghost1x-1][ghost1y]==9)moveup();
			else if(array[ghost1x][ghost1y+1]==0 || array[ghost1x][ghost1y+1]==9)moveright();
		}
	}
	public void ghost2move(){
		ghostmovetimer=ghostmovetimer+1;
		if(ghostmovetimer==40){
			ghostgoleft=true;
			ghostmovetimer=0;
		}
		if(dghost==1){
			if(array[ghost1x][ghost1y+1]!=1)moveright();
			else if(array[ghost1x-1][ghost1y]!=1)moveup();
			else if(array[ghost1x][ghost1y-1]!=1)moveleft();		
			else if(array[ghost1x+1][ghost1y]!=1)moveback();					
		}
		if(dghost==2){
			if(array[ghost1x+1][ghost1y]!=1)moveback();
			else if(array[ghost1x][ghost1y+1]!=1)moveright();
			else if(array[ghost1x-1][ghost1y]!=1 )moveup();
			else if(array[ghost1x][ghost1y-1]!=1)moveleft();
			
		}
		if(dghost==3){
			if(array[ghost1x][ghost1y-1]!=1)moveleft();	
	     	else if(array[ghost1x+1][ghost1y]!=1)moveback();
	     	else if(array[ghost1x][ghost1y+1]!=1)moveright();
	     	else if(array[ghost1x-1][ghost1y]!=1)moveup();			
		}
		if(dghost==4){
			if(array[ghost1x-1][ghost1y]==0 || array[ghost1x-1][ghost1y]==9)moveup();
			else if(array[ghost1x][ghost1y-1]==0 || array[ghost1x][ghost1y-1]==9)moveleft();	
			else if(array[ghost1x+1][ghost1y]!=1)moveback();
			else if(array[ghost1x][ghost1y+1]==0 || array[ghost1x][ghost1y+1]==9)moveright();
		}
	}
	public void ghostamove(){
		ghostmovetimera=ghostmovetimera+1;
		if(ghostmovetimera==60){
			ghostgolefta=false;
			ghostmovetimera=0;
		}
		
		if(dghosta==1){
			if(array[ghostax][ghostay-1]!=1)movelefta();		
			else if(array[ghostax-1][ghostay]!=1)moveupa();
			else if(array[ghostax][ghostay+1]!=1)moverighta();
			else if(array[ghostax+1][ghostay]!=1)movebacka();					
		}
		if(dghosta==2){
			if(array[ghostax-1][ghostay]!=1 )moveupa();
			else if(array[ghostax][ghostay+1]!=1)moverighta();
			else if(array[ghostax+1][ghostay]!=1)movebacka();	
			else if(array[ghostax][ghostay-1]!=1)movelefta();		
					
		}
		if(dghosta==3){
	     	if(array[ghostax][ghostay+1]!=1)moverighta();
	     	else if(array[ghostax+1][ghostay]!=1)movebacka();
	     	else if(array[ghostax][ghostay-1]!=1)movelefta();	
	     	else if(array[ghostax-1][ghostay]!=1)moveupa();			
		}
		if(dghosta==4){
			if(array[ghostax+1][ghostay]!=1)movebacka();
			else if(array[ghostax][ghostay-1]==0 || array[ghostax][ghostay-1]==9)movelefta();	
	     	else if(array[ghostax-1][ghostay]==0 || array[ghostax-1][ghostay]==9)moveupa();
			else if(array[ghostax][ghostay+1]==0 || array[ghostax][ghostay+1]==9)moverighta();
		}
	}
	public void ghostbmove(){
		ghostmovetimera=ghostmovetimera+1;
		if(ghostmovetimera==60){
			ghostgolefta=true;
			ghostmovetimera=0;
		}
		if(dghosta==1){
			if(array[ghostax][ghostay+1]!=1)moverighta();
			else if(array[ghostax-1][ghostay]!=1)moveupa();
			else if(array[ghostax][ghostay-1]!=1)movelefta();		
			else if(array[ghostax+1][ghostay]!=1)movebacka();					
		}
		if(dghosta==2){
			if(array[ghostax+1][ghostay]!=1)movebacka();
			else if(array[ghostax][ghostay+1]!=1)moverighta();
			else if(array[ghostax-1][ghostay]!=1 )moveupa();
			else if(array[ghostax][ghostay-1]!=1)movelefta();
			
		}
		if(dghosta==3){
			if(array[ghostax][ghostay-1]!=1)movelefta();	
	     	else if(array[ghostax+1][ghostay]!=1)movebacka();
	     	else if(array[ghostax][ghostay+1]!=1)moverighta();
	     	else if(array[ghostax-1][ghostay]!=1)moveupa();			
		}
		if(dghosta==4){
			if(array[ghostax-1][ghostay]==0 || array[ghostax-1][ghostay]==9)moveupa();
			else if(array[ghostax][ghostay-1]==0 || array[ghostax][ghostay-1]==9)movelefta();	
			else if(array[ghostax+1][ghostay]!=1)movebacka();
			else if(array[ghostax][ghostay+1]==0 || array[ghostax][ghostay+1]==9)moverighta();
		}
	}
	public void ghostxmove(){
		ghostmovetimera=ghostmovetimera+1;
		if(ghostmovetimera==60){
			ghostgolefta=false;
			ghostmovetimera=0;
		}
		
		if(dghosta==1){
			if(array[ghostxx][ghostxy-1]!=1)moveleftx();		
			else if(array[ghostxx-1][ghostxy]!=1)moveupx();
			else if(array[ghostxx][ghostxy+1]!=1)moverightx();
			else if(array[ghostxx+1][ghostxy]!=1)movebackx();					
		}
		if(dghosta==2){
			if(array[ghostxx-1][ghostxy]!=1 )moveupx();
			else if(array[ghostxx][ghostxy+1]!=1)moverightx();
			else if(array[ghostxx+1][ghostxy]!=1)movebackx();	
			else if(array[ghostxx][ghostxy-1]!=1)moveleftx();		
					
		}
		if(dghosta==3){
	     	if(array[ghostxx][ghostxy+1]!=1)moverightx();
	     	else if(array[ghostxx+1][ghostxy]!=1)movebackx();
	     	else if(array[ghostxx][ghostxy-1]!=1)moveleftx();	
	     	else if(array[ghostxx-1][ghostxy]!=1)moveupx();			
		}
		if(dghosta==4){
			if(array[ghostxx+1][ghostxy]!=1)movebackx();
			else if(array[ghostxx][ghostxy-1]==0 || array[ghostxx][ghostxy-1]==9)moveleftx();	
	     	else if(array[ghostxx-1][ghostxy]==0 || array[ghostxx-1][ghostxy]==9)moveupx();
			else if(array[ghostxx][ghostxy+1]==0 || array[ghostxx][ghostxy+1]==9)moverightx();
		}
	}
	public void ghostymove(){
		ghostmovetimera=ghostmovetimera+1;
		if(ghostmovetimera==60){
			ghostgolefta=true;
			ghostmovetimera=0;
		}
		if(dghosta==1){
			if(array[ghostxx][ghostxy+1]!=1)moverightx();
			else if(array[ghostxx-1][ghostxy]!=1)moveupx();
			else if(array[ghostxx][ghostxy-1]!=1)moveleftx();		
			else if(array[ghostxx+1][ghostxy]!=1)movebackx();					
		}
		if(dghosta==2){
			if(array[ghostxx+1][ghostxy]!=1)movebackx();
			else if(array[ghostxx][ghostxy+1]!=1)moverightx();
			else if(array[ghostxx-1][ghostxy]!=1 )moveupx();
			else if(array[ghostxx][ghostxy-1]!=1)moveleftx();
			
		}
		if(dghosta==3){
			if(array[ghostxx][ghostxy-1]!=1)moveleftx();	
	     	else if(array[ghostxx+1][ghostxy]!=1)movebackx();
	     	else if(array[ghostxx][ghostxy+1]!=1)moverightx();
	     	else if(array[ghostxx-1][ghostxy]!=1)moveupx();			
		}
		if(dghosta==4){
			if(array[ghostxx-1][ghostxy]==0 || array[ghostxx-1][ghostxy]==9)moveupx();
			else if(array[ghostxx][ghostxy-1]==0 || array[ghostxx][ghostxy-1]==9)moveleftx();	
			else if(array[ghostxx+1][ghostxy]!=1)movebackx();
			else if(array[ghostxx][ghostxy+1]==0 || array[ghostxx][ghostxy+1]==9)moverightx();
		}
	}
	public void ghost4move(){
		ghostmovetimera=ghostmovetimera+1;
		if(ghostmovetimera==60){
			ghostgolefta=false;
			ghostmovetimera=0;
		}
		
		if(dghosta==1){
			if(array[ghost4x][ghost4y-1]!=1)moveleft4();		
			else if(array[ghost4x-1][ghost4y]!=1)moveup4();
			else if(array[ghost4x][ghost4y+1]!=1)moveright4();
			else if(array[ghost4x+1][ghost4y]!=1)moveback4();					
		}
		if(dghosta==2){
			if(array[ghost4x-1][ghost4y]!=1 )moveup4();
			else if(array[ghost4x][ghost4y+1]!=1)moveright4();
			else if(array[ghost4x+1][ghost4y]!=1)moveback4();	
			else if(array[ghost4x][ghost4y-1]!=1)moveleft4();		
					
		}
		if(dghosta==3){
	     	if(array[ghost4x][ghost4y+1]!=1)moveright4();
	     	else if(array[ghost4x+1][ghost4y]!=1)moveback4();
	     	else if(array[ghost4x][ghost4y-1]!=1)moveleft4();	
	     	else if(array[ghost4x-1][ghost4y]!=1)moveup4();			
		}
		if(dghosta==4){
			if(array[ghost4x+1][ghost4y]!=1)moveback4();
			else if(array[ghost4x][ghost4y-1]==0 || array[ghost4x][ghost4y-1]==9)moveleft4();	
	     	else if(array[ghost4x-1][ghost4y]==0 || array[ghost4x-1][ghost4y]==9)moveup4();
			else if(array[ghost4x][ghost4y+1]==0 || array[ghost4x][ghost4y+1]==9)moveright4();
		}
	}
	public void ghost5move(){
		ghostmovetimera=ghostmovetimera+1;
		if(ghostmovetimera==60){
			ghostgolefta=true;
			ghostmovetimera=0;
		}
		if(dghosta==1){
			if(array[ghost4x][ghost4y+1]!=1)moveright4();
			else if(array[ghost4x-1][ghost4y]!=1)moveup4();
			else if(array[ghost4x][ghost4y-1]!=1)moveleft4();		
			else if(array[ghost4x+1][ghost4y]!=1)moveback4();					
		}
		if(dghosta==2){
			if(array[ghost4x+1][ghost4y]!=1)moveback4();
			else if(array[ghost4x][ghost4y+1]!=1)moveright4();
			else if(array[ghost4x-1][ghost4y]!=1 )moveup4();
			else if(array[ghost4x][ghost4y-1]!=1)moveleft4();
			
		}
		if(dghosta==3){
			if(array[ghost4x][ghost4y-1]!=1)moveleft4();	
	     	else if(array[ghost4x+1][ghost4y]!=1)moveback4();
	     	else if(array[ghost4x][ghost4y+1]!=1)moveright4();
	     	else if(array[ghost4x-1][ghost4y]!=1)moveup4();			
		}
		if(dghosta==4){
			if(array[ghost4x-1][ghost4y]==0 || array[ghost4x-1][ghost4y]==9)moveup4();
			else if(array[ghost4x][ghost4y-1]==0 || array[ghost4x][ghost4y-1]==9)moveleft4();	
			else if(array[ghost4x+1][ghost4y]!=1)moveback4();
			else if(array[ghost4x][ghost4y+1]==0 || array[ghost4x][ghost4y+1]==9)moveright4();
		}
	}
	public void pacmove()
	{
		if(moveright==true &&array[pacx][pacy+1]!=1)
		{
		array[pacx][pacy]=9;
		array[pacx][pacy+1]=3;
		pacy=pacy+1;
		}
		if(moveup==true &&array[pacx-1][pacy]!=1)
		{
		array[pacx][pacy]=9;
		array[pacx-1][pacy]=3;
		pacx=pacx-1;
		}
		if(movedown==true &&array[pacx+1][pacy]!=1)
		{
		array[pacx][pacy]=9;
		array[pacx+1][pacy]=3;
		pacx=pacx+1;
		}
		if(moveleft==true&&array[pacx][pacy-1]!=1)
		{
		array[pacx][pacy]=9;
		array[pacx][pacy-1]=3;
		pacy=pacy-1;
		}
	}
	}


