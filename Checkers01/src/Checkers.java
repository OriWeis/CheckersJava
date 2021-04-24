//Checkers03 Checkers Class (MouseListener Class)
/**
 * 
 *@author Ori Weis Checkers Class
 *21.4.21
 * 
**/
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;
public class Checkers extends JPanel implements ActionListener,MouseListener {//add MouseListener in implimations

	public static int width = 800, height = width;
	static BufferedImage crownImage = null;
	private static int DEFAULT_S=8;
	public static final int tileSize = width/DEFAULT_S; //8 Tiles for checkers board
	
	private boolean inPlay=false;
	//Variable Playing_now Is For The One which is Playing.
	//It Defines who is Starting by The Random Function.
	private int game_4_each_side=0,storedCol,storedRow,playing_now;
	
	private static int num_of_Black,num_of_white;//Ill Use This Sometime
	
	public int white_left,red_left,white_kings,red_kings;
	
	private static int game_type;//0 for 1v1 1 for Human vs AI
	
	//Data Of The Game
	private matrix gameData;
	
	private boolean ai_playing; //AI plays the Red Cells
	/**
	 * Values For gameData 
	 * Just Reminding That Red Pieces replace the black pieces
	 */
	public static final int EMPTY = 0, WHITE = 1, RED= 2, WHITE_KING = 3, RED_KING=4;  
	
	public void printStart()
	{
		if(this.playing_now==0)
			System.out.println("White");
		else
			System.out.println("Black");
	}
	
	public Checkers(int game_type)
	{
		window(this.height,this.width,this);
		Random rand=new Random();
		this.playing_now=rand.nextInt(2);//*Gives Values Between 0-1*. 0 for White, 1 for Red.
		this.playing_now=1;//delete after AI works
		this.printStart();
		this.gameData=new matrix();
		repaint();
		this.if_AI();
		
	}
	
	public void update_num_of()
	{
		for(int row = 0; row < DEFAULT_S; row++)
		{
			for(int col = 0; col < DEFAULT_S; col++)
			{
				if(this.gameData.get_mat_i_j(row, col)==this.WHITE||this.gameData.get_mat_i_j(row, col)==this.WHITE_KING)
				{
					this.num_of_white++;
				}
				if(this.gameData.get_mat_i_j(row, col)==this.RED||this.gameData.get_mat_i_j(row, col)==this.RED_KING)
				{
					this.num_of_Black++;
				}
			}
		}
		
	}
	
	public void if_AI()
	{
		this.update_num_of();
		//System.out.println("num of cells "+this.num_of_Black+" , "+this.num_of_white);
		if (this.playing_now==1)
		{
			this.ai_playing=true;
			this.ai_move(this.gameData);
			System.out.println("\n AI Played \n");
			this.ai_playing=false;
		}
	}
	
	public void window(int width, int height, Checkers game){ //draw the frame and add exit functionality
		JFrame frame = new JFrame();
		//height=height+100;
		frame.setSize(width, height);
		frame.setIconImage(crownImage);
		frame.setBackground(Color.cyan);
		frame.setLocationRelativeTo(null);
		frame.pack();
		Insets insets = frame.getInsets();
		int frameLeftBorder = insets.left;
		int frameRightBorder = insets.right;
		int frameTopBorder = insets.top;
		int frameBottomBorder = insets.bottom;
		frame.setPreferredSize(new Dimension(width + frameLeftBorder + frameRightBorder, height + frameBottomBorder + frameTopBorder));
		frame.setMaximumSize(new Dimension(width + frameLeftBorder + frameRightBorder, height + frameBottomBorder + frameTopBorder));
		frame.setMinimumSize(new Dimension(width + frameLeftBorder + frameRightBorder, height + frameBottomBorder + frameTopBorder));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addMouseListener((MouseListener) this);
		frame.requestFocus();
		frame.setVisible(true);
		frame.add(game);
	}
	
		public static void drawPiece(int col, int row, Graphics g, Color color){
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g.setColor(color);
			// These 2 and 4 values are arbitrary values that compensate for a smaller piece size than tileSize
			g.fillOval((col*tileSize)+2, (row*tileSize)+2, tileSize-4, tileSize-4);
		}
		
		public void paint(Graphics g)
		{ 	// This method paints the board And REPAINTS IT.
			//Actually it prints the
			this.num_of_white=0;
			this.num_of_Black=0;
			this.white_left=0;
			this.white_kings=0;
			this.red_kings=0;
			this.red_left=0;
			int index1=0;
			super.paintComponent(g);
			if(this.gameData.gameOver()==false)
			{
				this.gameData.print_mat();
			for(int row = 0; row < DEFAULT_S; row++)
			{
				for(int col = 0; col < DEFAULT_S; col++)
				{
					if((row%2 == 0 && col%2 == 0) || (row%2 != 0 && col%2 != 0))
					{ 
						// This assigns the checkerboard pattern		
						/**
						 * This is the opposite Because
						 * Rectangle r = new Rectangle(xPos, yPos, width, height)
						**/
						g.setColor(Color.gray);
						g.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);	
						
					}
					else
					{
						g.setColor(Color.darkGray);
						g.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
						if(this.gameData.get_mat_i_j(row, col)!=EMPTY)
						{
							g.setColor(Color.darkGray.darker());
							g.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
						}
						
						//Checks the movement options.
						if(this.gameData.getMovement().size()>index1)
						if(this.gameData.getMovement().get(index1).get_i()==row&&this.gameData.getMovement().get(index1).get_j()==col)
						{
							//System.out.println("\nsize of is "+this.gameData.getMovement().size());
							//System.out.println("index is : "+index1);
							g.setColor(Color.CYAN.darker());
							g.fillRect(this.gameData.getMovement().get(index1).get_j()*tileSize, this.gameData.getMovement().get(index1).get_i()*tileSize, tileSize, tileSize);
							index1++;
						}
						
						if(this.gameData.get_mat_i_j(row, col) == WHITE)
							drawPiece(col, row, g, Color.white);
						else if(this.gameData.get_mat_i_j(row, col) == WHITE_KING)
						{
							drawPiece(col, row, g, Color.white.darker());//might wont work
							//g.drawImage(crownImage, (col*tileSize)+6, (row*tileSize)+6, tileSize-12, tileSize-12, null);
						}
						else if(this.gameData.get_mat_i_j(row, col) == RED)
							drawPiece(col, row, g, Color.red);
						else if(this.gameData.get_mat_i_j(row, col) == RED_KING)
						{
							drawPiece(col, row, g, Color.red.darker());
							//g.drawImage(crownImage, (col*tileSize)+6, (row*tileSize)+6, tileSize-12, tileSize-12, null);
						}
						
					}
					if(this.gameData.get_mat_i_j(row, col)==this.WHITE||this.gameData.get_mat_i_j(row, col)==this.WHITE_KING)
					{
						this.num_of_white++;
						if(this.gameData.get_mat_i_j(row, col)==this.WHITE)
							this.white_left++;
						else 
							this.white_kings++;
					}
					if(this.gameData.get_mat_i_j(row, col)==this.RED||this.gameData.get_mat_i_j(row, col)==this.RED_KING)
					{
						this.num_of_Black++;
						
						if(this.gameData.get_mat_i_j(row, col)==this.RED)
							this.red_left++;
						else 
							this.red_kings++;
						
					}
					
					}
						
				}
			}
			else //displaying Game Over Screen
				this.gameOverDisplay(g);
			
			System.out.println("eventually "+this.num_of_white+" | "+this.num_of_Black);
			/**this.gameData.set_White_Left(this.white_left);	
			this.gameData.set_White_Kings(this.white_kings);
			this.gameData.set_Red_Left(this.red_left);
			this.gameData.set_Red_Kings(this.red_kings);**/
				
			}
			
		public void gameOverDisplay(Graphics g) { //Displays the game over message
			 String msg = "Game Over";
		     Font small = new Font("Helvetica", Font.BOLD, 100);
		     FontMetrics metr = getFontMetrics(small);
		     g.setColor(Color.white);
		     g.setFont(small);
		     g.drawString(msg, (width - metr.stringWidth(msg)) / 2, width / 2);
		     MessageBox m1=new MessageBox();
		}
		
	/**	public boolean gameOver()
		{ 
			//Wrapper for gameOverInternal
			if(this.num_of_Black==0 || this.num_of_white==0)
			{
				System.out.println("Game over Bye Bye");
				return true;
			}
			if(this.num_of_white==4 && this.num_of_white==4)
				if(this.game_4_each_side==20)
					return true;
				else
					this.game_4_each_side++;
			return false;
		}**/
		
		
		public boolean game_Over_check()
		{
			return false;
		}
		
		public void resetPlay()
		{	
			this.storedCol=-1;
			this.storedRow=-1;
			this.gameData.init_move_arr();
			repaint();
		}
		
		//Gives Values Between 0-1. *0 for White* , *1 for Red*//
		
		public int side_choosen(int row,int col )
		{
			int type1=this.gameData.get_mat_i_j(row, col);
			if(type1==this.WHITE||type1==this.WHITE_KING)
				return 0;
			else if(type1==0)
				return -1;
			return 1;
					
		}
		
		public void switch_sides()
		{
			repaint();
			this.ai_playing=false;
		}
		
		
		public void ai_move(matrix board)
		{
			matrix sendmat =new matrix();
			sendmat.set_matrix(board);
			
			AI_Algorithm player_AI=new AI_Algorithm(sendmat,8,this);
			sendmat=player_AI.return_new_board();
			board.set_matrix(sendmat);
			repaint();
			System.out.println("AI played and Repainted");
			if (this.gameData==null )
				System.out.println("Returned Null Expression");
			else
			{
				System.out.println("\n\n\nPrinting Mat From Checkers !!!! \n");
				this.gameData.print_mat();
			}
			this.switch_sides();
		}
		
		public boolean checkTouch(int row,int col)
		{
			int type1=this.gameData.get_mat_i_j(row, col);
			if(type1==this.WHITE||type1==this.WHITE_KING ||type1==EMPTY)
				return true;
			return false;
		}
		
		public void mousePressed(MouseEvent e ) 
		  {
	    	int col = (e.getX()-8) / tileSize; // 8 is left frame length
	        int row = (e.getY()-30) / tileSize; // 30 is top frame length
	      
	        System.out.println("("+col+","+row+")");
	        if(this.ai_playing==false && this.checkTouch(row, col))
			if((this.inPlay==false && this.gameData.get_mat_i_j(row, col) !=EMPTY)
				|| (this.inPlay == true && this.gameData.get_mat_i_j(row, col) !=EMPTY))
			{
				System.out.println("In play ->\t"+this.inPlay);
				resetPlay();
				this.storedCol = col;
				this.storedRow = row; // Sets the current click to instance variables to be used elsewhere
				this.gameData.where_To_Go(this.storedRow,this.storedCol);
				
				if(this.gameData.eat_Twice()==true)
			    {
			        	
		        	System.out.println("\n\n\nClearing MV movement opt");
					this.gameData.clear_Mv(row, col);
					
		        }
				
				this.inPlay=true;
				repaint();
			}
			else if(this.inPlay == true )
			{
				System.out.println("Arrived To Make A move !!!!\n\n");
				if(this.gameData.is_Availible(row, col)==true)
				{
					System.out.println("A move has Happened");
					this.gameData.make_Move(row,col,this.storedRow,this.storedCol,this.gameData.getMovement());
					//thing=this.gameData.get_mat_i_j(this.storedRow,this.storedCol);
					if(this.gameData.eat_Twice()==false)
					{
						this.gameData.init_twice();
						this.switch_sides();
						//repaint();
						this.inPlay=false;
					}
					this.gameData.update_Kings();
					resetPlay();
					this.ai_playing=true;
				}
			}
			else if(this.inPlay == true && this.gameData.getMovement().size()==0)
			{
				resetPlay();
			}
	        
	        if(this.ai_playing==true)
	        {
	        	repaint();
	        	this.ai_move(this.gameData);
	        }
	        
		  }
		
		public void mouseClicked(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void actionPerformed(ActionEvent e) {}
			
	}



