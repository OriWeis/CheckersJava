import java.util.*;

//Checkers03 matrix Class
/**
 * 
 *@author Ori Weis
 *19.4.21
 * 
**/

/** 
 *Just in case that you didn't notice I used **Red Cells** in my Project because it looks better 
**/


/**
 *This Class is for sorting by row the moving opt, because the implemetion in the main program must be sorted
**/
class Sortbyrow implements Comparator<move_opt> {
    // Used for sorting in ascending order of
    // *row number*
	@Override
	public int compare(move_opt o1, move_opt o2) {
		// TODO Auto-generated method stub
		return o1.get_i()-o2.get_i();
	}
}

class Sortbycol implements Comparator<move_opt> {
    // Used for sorting in ascending order of
    // *row number*
	@Override
	public int compare(move_opt o1, move_opt o2) {
		// TODO Auto-generated method stub
		return o1.get_j()-o2.get_j();
	}
}


public class matrix
{
	
	private int[][] mat;
	private int DEFAULT_LEN=8;//Defualt length of 2D Checkers/Chess Board
	//private int clickedCol,clickedRow;
	
	//private ArrayList<Cell> whites;
	private static int whc=1, whck=3;//white Cell Equals To whc White King Equals To 3
	
	//private ArrayList<Cell> black_s;//In The main it will be Red Cells Because it looks Better
	private static int blc=2, blck=4;//Black Means Red Cell Equals To 2 Black King Equals To 4
	
	public static final int EMPTY = 0;
	
	private ArrayList<move_opt> moving_opt;//Move Options for Cell//
	
	private ArrayList<move_opt> skipped;//skipped for eating twice
	
	private int row_Twice, col_Twice;
	
	private boolean chose_to_eat;
	
	public int white_left,red_left,white_kings,red_kings;
	
	private Cell current_location; 
	private int chosen_row,chosen_col;
	
	private boolean already_Ate;
	private int counter_eat;	
	
	public int get_White_Left()
	{
		return this.white_left;
	}
	
	public void set_White_Left(int wl)
	{
		this.white_left=wl;
	}
	
	
	public void set_White_Kings(int wk)
	{
		this.white_kings=wk;
	}
	
	
	public int get_White_Kings()
	{
		return this.white_kings;
	}
	
	public int get_Red_Left()
	{
		return this.red_left;
	}
	
	public void set_Red_Left(int rl)
	{
		this.red_left=rl;
	}
	
	public int get_Red_Kings()
	{
		return this.red_kings;
	}
	
	public void set_Red_Kings(int rk)
	{
		this.red_kings=rk;
	}
	
	public int get_r_Twice()
	{
		return this.row_Twice;
	}
	
	public int get_c_Twice()
	{
		return this.col_Twice;
	}
	
	public ArrayList<move_opt> getMovement()
	{
		return this.moving_opt;
	}
	
	/**
	 * Function To Initialize matrix Object  
	 */
	public void init_mat()
	{
		for (int i=0; i<8;i++)
		{
			for (int j=0; j<8;j++)
			{
				if (i<3)
				{
				if((i+j)%2==whc)
				{
					//Cell c1=new Cell(this.blc);
					this.mat[i][j]=2;
					//this.black_s.add(c1);
				}
				else
					this.mat[i][j]=0;
				}
				if(i>4)
				{
					if((i+j)%2==whc)
					{
						//Cell c1=new Cell(whc);
						this.mat[i][j]=whc;
						//this.whites.add(c1);
					}
					else
						this.mat[i][j]=0;
				}
			}
		}
	}
	
	/**
	 * Constructor
	 */
	
	public matrix()
	{
		this.mat=new int[DEFAULT_LEN][DEFAULT_LEN];
		//this.whites=new ArrayList<Cell>();
		//this.black_s=new ArrayList<Cell>();
		this.moving_opt=new ArrayList<move_opt>();//Length Starts From 0 When there is Nothing inside. 
		this.skipped=new ArrayList<move_opt>();
		//System.out.println("\nThe Length Of Arraylist is ->"+this.moving_opt.size());
		this.counter_eat=0;
		this.already_Ate=false;
		init_mat();
		
	}
	
	public matrix(matrix m1)
	{
		this.mat=m1.getMat();
	}
	public void set_matrix(matrix m1)
	{
		for(int i=0; i<this.DEFAULT_LEN;i++)
		{
			for(int j=0; j<this.DEFAULT_LEN;j++)
			{
				this.mat[i][j]=m1.getMat()[i][j];
			}
		}
	}
	
	public matrix(matrix mat1,Cell curr, int row,int col)
	{
		this((mat1));
		this.chosen_row=row;
		this.chosen_col=col;
		this.current_location=curr;
	}

	public Cell get_CurrentLoc()
	{
		return this.current_location;
	}
	
	
	public int getChosen_row() {
		return chosen_row;
	}

	public void setChosen_row(int chosen_row) {
		this.chosen_row = chosen_row;
	}

	public int getChosen_col() {
		return chosen_col;
	}

	public void setChosen_col(int chosen_col) {
		this.chosen_col = chosen_col;
	}

	public void init_twice()
	{
		this.row_Twice=-1;
		this.col_Twice=-1;
	}
	public int get_mat_i_j(int i,int j)
	{
		return this.mat[i][j];
	}
	
	//Just A Little Function To Print The Matrix 
	public void print_mat()
	{
		
		for (int i=0; i<8;i++)
		{
			for (int j=0; j<8;j++)
			{
				System.out.print(this.mat[i][j]+" ");
			}
			System.out.println("");
		}	
	}
	
	public int[][] copyMat()
	{
		int [][] mat2=new int [this.DEFAULT_LEN][this.DEFAULT_LEN];
		for (int i=0;i<this.DEFAULT_LEN;i++)
		{
			for (int j=0;j<this.DEFAULT_LEN;j++)
			{
				mat2[i][j]=this.mat[i][j];
			}
		}
		return mat2;
	}
	
	
	//Return the Matrix 
	public int[][] getMat()
	{
		return this.mat;
	}
	
	public int return_type(int i, int j)
	{
		if (j!=-1 && j!=this.DEFAULT_LEN && i!=-1 && i!=this.DEFAULT_LEN)
			return this.mat[i][j];	
		return -1;
	}
	
	
	public void delete_from_Mat(int i, int j,int type)
	{
		this.mat[i][j]=0;
	}
	
	public void eat_left_up(int i, int j)
	{
		this.delete_from_Mat(i-1, j-1,mat[i-1][j-1]);
		this.mat[i-2][j-2]=this.mat[i][j];
		this.mat[i][j]=EMPTY;
		this.row_Twice=i-2;
		this.col_Twice=j-2;
	}
	
	public void eat_right_up(int i, int j)
	{
		
		this.delete_from_Mat(i-1, j+1,mat[i-1][j+1]);
		System.out.println("i-2 is : "+(i-2)+" j+2 is : "+(j+2));
		this.mat[i-2][j+2]=this.mat[i][j];
		this.mat[i][j]=0;
		this.row_Twice=i-2;
		this.col_Twice=j+2;
	}
	
	public void eat_right_down(int i, int j)
	{
		
		this.delete_from_Mat(i+1, j+1,mat[i+1][j+1]);
		this.mat[i+2][j+2]=this.mat[i][j];
		this.mat[i][j]=0;
		this.row_Twice=i+2;
		this.col_Twice=j+2;
	}
	
	public void eat_left_down(int i, int j)
	{
		this.delete_from_Mat(i+1, j-1,mat[i+1][j-1]);
		this.mat[i+2][j-2]=this.mat[i][j];
		this.mat[i][j]=0;
		this.row_Twice=i+2;
		this.col_Twice=j-2;
		
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return Whether the place is Empty or Not
	 */
	public boolean Is_Exist(int row,int col)
	{
		return this.mat[row][col]!=EMPTY;
	}
	
	public void init_move_arr()
	{
		if(this.moving_opt!=null)
			for (int i=this.moving_opt.size()-1;i>=0;i--)
			{
				this.moving_opt.remove(i);
			}
		else
			this.moving_opt=new ArrayList<move_opt>();
	}
	
	public boolean is_King(int row,int col)
	{
		return this.mat[row][col]>2;
	}
	
	/**
	 * 
	 * Second_Eat_Check_Function
	 * @param i
	 * @param j
	 * @param type
	 * @param prev_row
	 * @param prev_col
	 * @return
	 */
	public boolean second_check(int i,int j,int type,String m_type,int prev_row,int prev_col)
	{
		move_opt e1;
		boolean b1=false;
		//int r_check,l_check;
		if (type==this.whc)
		if(i<=1)
			b1= false;
		else 
		{
			if(j<6)//Checks where to eat//
				if(return_type(i-1,j+1)!=type && return_type(i-1,j+1)!=type+2 && return_type(i-1,j+1)!=EMPTY)
					if(return_type(i-2,j+2)==EMPTY)
					{
						
							System.out.println("adding to moving_opt this is what will be displayed"+"("+(i-2)+","+(j+2)+")");
							e1=new move_opt(i-2, j+2,m_type,"eat_right_up",prev_row,prev_col);
							this.moving_opt.add(e1);
							//eat_right_up(i,j);
							b1=true;
					}
			if(j>1)
				if(return_type(i-1,j-1)!=type&& return_type(i-1,j-1)!=type+2 && return_type(i-1,j-1)!=EMPTY)
					if(return_type(i-2,j-2)==EMPTY)
					{
						
						System.out.println("adding to moving_opt"+"("+(i-2)+","+(j-2)+")");
						e1=new move_opt(i-2, j-2,m_type,"eat_left_up",prev_row,prev_col);
						this.moving_opt.add(e1);
						//eat_left_up(i,j);
						b1=true;
					}
		}
		else
		if(type==this.blc)
			if(i>=this.DEFAULT_LEN-2)
				b1= false;
			else 
			{
				if(j<6)//Checks where to eat//
					if(return_type(i+1,j+1)!=type && return_type(i+1,j+1)!=type+2 && return_type(i+1,j+1)!=EMPTY)
						if(return_type(i+2,j+2)==EMPTY)
						{
							
							System.out.println("adding to moving_opt"+"("+(i+2)+","+(j+2)+")");
							e1=new move_opt(i+2, j+2,m_type,"eat_right_down",prev_row,prev_col);
							this.moving_opt.add(e1);
							//eat_right_down(i,j);
							b1=true;
						}
				if(j>1)
					if(return_type(i+1,j-1)!=type&&return_type(i+1,j-1)!=type+2 && return_type(i+1,j-1)!=EMPTY)
						if(return_type(i+2,j-2)==EMPTY)
						{
							System.out.println("adding to moving_opt"+"("+(i+2)+","+(j-2)+")");
							e1=new move_opt(i+2, j-2,m_type,"eat_left_down",prev_row,prev_col);
							this.moving_opt.add(e1);
							//eat_left_down(i,j);
							b1=true;
						}
			}
		else
		if(this.is_King(i, j))
		{
			System.out.println("\n\nEntered to KINGGGGGGGGGGG\n\n");
			
			if(i>1)
			{
				if(j<6)//Checks where to eat//
					if(return_type(i-1,j+1)!=type && return_type(i-1,j+1)!=type-2 && return_type(i-1,j+1)!=EMPTY)
						if(return_type(i-2,j+2)==EMPTY)
						{
									e1=new move_opt(i-2, j+2,m_type,"eat_right_up",prev_row,prev_col);
									this.moving_opt.add(e1);
									//eat_right_up(i,j);
									b1=true;
						}
				if(j>1)
					if(return_type(i-1,j-1)!=type&& return_type(i-1,j-1)!=type-2 && return_type(i-1,j-1)!=EMPTY)
						if(return_type(i-2,j-2)==EMPTY)
						{
							e1=new move_opt(i-2, j-2,m_type,"eat_left_up",prev_row,prev_col);
							this.moving_opt.add(e1);
							//eat_left_up(i,j);
							b1=true;
						}
			}
			
			if(i<this.DEFAULT_LEN-2)
			{
			System.out.println("Entered to i<Default_SIZZEEEEEEEE");
			if(j<6)//Checks where to eat//
				if(return_type(i+1,j+1)!=type && return_type(i+1,j+1)!=type-2 && return_type(i+1,j+1)!=EMPTY )
					if(return_type(i+2,j+2)==EMPTY)
					{
						
						e1=new move_opt(i+2, j+2,m_type,"eat_right_down",prev_row,prev_col);
						this.moving_opt.add(e1);
						//eat_right_down(i,j);						
						b1=true;
						
					}
			if(j>1)
				if(return_type(i+1,j-1)!=type&&return_type(i+1,j-1)!=type-2 && return_type(i+1,j-1)!=EMPTY)
					if(return_type(i+2,j-2)==EMPTY)
					{
						System.out.println("Should eat...");
						e1=new move_opt(i+2, j-2,m_type,"eat_left_down",prev_row,prev_col);
						this.moving_opt.add(e1);
						//eat_left_down(i,j);
						b1=true;
					}
		}		
		}
		return b1;
	}
	
	/**
	 * 
	 * Function that checks all the eating options (also double eats)
	 * @param i means row
	 * @param j means colloumn
	 * @param type
	 * @return
	 */
	public boolean can_eat(int i, int j, int type)
	{
		move_opt e1;
		boolean b1=false;
		//int r_check,l_check;
		if (type==this.whc)
		if(i<=1)
			b1= false;
		else 
		{
			if(j<6)//Checks where to eat//
				if(return_type(i-1,j+1)!=type && return_type(i-1,j+1)!=type+2 && return_type(i-1,j+1)!=EMPTY)
					if(return_type(i-2,j+2)==EMPTY)
					{
							if(second_check(i-2,j+2,this.mat[i][j],"eat_right_up",i-2,j+2)==false)//the mat[i-1][j+1] should be deleted 
							{
								e1=new move_opt(i-2, j+2,"eat_right_up");
								this.moving_opt.add(e1);
								//eat_right_up(i,j);
								
							}
							
							b1=true;
						
					}
			if(j>1)
				if(return_type(i-1,j-1)!=type&& return_type(i-1,j-1)!=type+2 && return_type(i-1,j-1)!=EMPTY)
					if(return_type(i-2,j-2)==EMPTY)
					{
						if(second_check(i-2,j-2,this.mat[i][j],"eat_left_up",i-2,j-2)==false)
						{
							e1=new move_opt(i-2, j-2,"eat_left_up");
							this.moving_opt.add(e1);
						}
						//eat_left_up(i,j);
						b1=true;	
					}
		}
		
		else
		if(type==this.blc)
			if(i>=this.DEFAULT_LEN-2)
				b1= false;
			else 
			{
				if(j<6)//Checks where to eat//
					if(return_type(i+1,j+1)!=type && return_type(i+1,j+1)!=type+2 && return_type(i+1,j+1)!=EMPTY)
						if(return_type(i+2,j+2)==EMPTY)
						{
							
							if(second_check(i+2,j+2,this.mat[i][j],"eat_right_down",i+2,j+2)==false)
							{
								e1=new move_opt(i+2, j+2,"eat_right_down");
								System.out.println("found a way to eat right down \n\n\n\n");
								this.moving_opt.add(e1);
							}
							//eat_right_down(i,j);
							b1=true;
						}
				if(j>1)
					if(return_type(i+1,j-1)!=type&&return_type(i+1,j-1)!=type+2 && return_type(i+1,j-1)!=EMPTY)
						if(return_type(i+2,j-2)==EMPTY)
						{
							if(second_check(i+2,j-2,this.mat[i][j],"eat_left_down",i+2,j-2)==false)
							{
									e1=new move_opt(i+2, j-2,"eat_left_down");
									this.moving_opt.add(e1);
									//eat_left_down(i,j);
							}
							b1=true;
						}
			}
		else
		if(this.is_King(i, j))
		{
			System.out.println("\n\nEntered to KINGGGGGGGGGGG\n\n");
			
			if(i>1)
			{
				if(j<6)//Checks where to eat//
					if(return_type(i-1,j+1)!=type && return_type(i-1,j+1)!=type-2 && return_type(i-1,j+1)!=EMPTY)
						if(return_type(i-2,j+2)==EMPTY)
						{
							if(second_check(i-2,j+2,this.mat[i][j],"eat_right_up",i-2,j+2)==false)
							{
									e1=new move_opt(i-2, j+2,"eat_right_up");
									this.moving_opt.add(e1);
									//eat_right_up(i,j);
							}
							b1=true;
						}
				if(j>1)
					if(return_type(i-1,j-1)!=type&& return_type(i-1,j-1)!=type-2 && return_type(i-1,j-1)!=EMPTY)
						if(return_type(i-2,j-2)==EMPTY)
						{
							if(second_check(i-2,j-2,this.mat[i][j],"eat_left_up",i-2,j-2)==false)
							{
							e1=new move_opt(i-2, j-2,"eat_left_up");
							this.moving_opt.add(e1);
							//eat_left_up(i,j);
							}
							b1=true;
						}
			}
			
			if(i<this.DEFAULT_LEN-2)
			{
			System.out.println("Entered to i<Default_SIZZEEEEEEEE");
			if(j<6)//Checks where to eat//
				if(return_type(i+1,j+1)!=type && return_type(i+1,j+1)!=type-2 && return_type(i+1,j+1)!=EMPTY )
					if(return_type(i+2,j+2)==EMPTY)
					{
						
						if(second_check(i+2,j+2,this.mat[i][j],"eat_right_down",i+2,j+2)==false)
						{
							e1=new move_opt(i+2, j+2,"eat_right_down");
							this.moving_opt.add(e1);
							//eat_right_down(i,j);						
							b1=true;
						}
						
					}
			if(j>1)
				if(return_type(i+1,j-1)!=type&&return_type(i+1,j-1)!=type-2 && return_type(i+1,j-1)!=EMPTY)
					if(return_type(i+2,j-2)==EMPTY)
					{
						if(second_check(i+2,j-2,this.mat[i][j],"eat_left_down",i+2,j-2)==false)
						{
						System.out.println("Should eat...");
							e1=new move_opt(i+2, j-2,"eat_left_down");
						this.moving_opt.add(e1);
						}
						//eat_left_down(i,j);
						b1=true;
					}
		}		
		}
		return b1;
	}

	public void printMoving()
	{
		for	(move_opt o1: this.moving_opt)
		{
			o1.printOpt();
		}
	}
	
	public void set_Cell(int row,int col, int type1)
	{
		
		this.mat[row][col]=type1;
		
	}
	
	public ArrayList<move_opt> where_To_Go(int row,int col)
	{
		//move_opt m1=new move_opt();
		int type1=return_type(row,col);
		if(this.already_Ate==false)
		{
		if(type1==this.blc||type1==this.blck)
		if(return_type(row,col)!=-1)
		{
			if(return_type(row+1,col+1)==EMPTY)
			{
				move_opt m1=new move_opt(row+1, col+1,"mv");
				this.moving_opt.add(m1);
			}
			if(return_type(row+1,col-1)==EMPTY)
			{
				move_opt m1=new move_opt(row+1, col-1,"mv");
				this.moving_opt.add(m1);
			}
		}
		
		if(type1==this.whc||type1==this.whck)
			if(return_type(row,col)!=-1)
			{
				if(return_type(row-1,col+1)==EMPTY)
				{
					move_opt m1=new move_opt(row-1, col+1,"mv");
					this.moving_opt.add(m1);
				}
				if(return_type(row-1,col-1)==EMPTY)
				{
					move_opt m1=new move_opt(row-1, col-1,"mv");
					this.moving_opt.add(m1);
				}
			}
		
		if(type1==this.whck)
		{
			if(return_type(row+1,col+1)==EMPTY)
			{
				move_opt m1=new move_opt(row+1, col+1,"mv");
				this.moving_opt.add(m1);
			}
			if(return_type(row+1,col-1)==EMPTY)
			{
				move_opt m1=new move_opt(row+1, col-1,"mv");
				this.moving_opt.add(m1);
			}
		}
		if(type1==this.blck)
		{
			if(return_type(row-1,col+1)==EMPTY)
			{
				move_opt m1=new move_opt(row-1, col+1,"mv");
				this.moving_opt.add(m1);
			}
			if(return_type(row-1,col-1)==EMPTY)
			{
				move_opt m1=new move_opt(row-1, col-1,"mv");
				this.moving_opt.add(m1);
			}
		}
		}
		
		this.can_eat(row, col, this.mat[row][col]);//Checks all the options of eating //*maximum 4*//
		
		Collections.sort(this.moving_opt,new Sortbyrow());
		Collections.sort(this.moving_opt,new Sortbycol());
		Collections.sort(this.moving_opt,new Sortbyrow());
		/**this.printMoving();
		System.out.println();**/
		return this.moving_opt;
		//System.out.println("Length Of moving options "+this.moving_opt.size());
	}
		
	public boolean is_Availible(int row,int col)
	{
		boolean b1=false;
		for(move_opt m1:this.moving_opt)
		{
			if(m1.get_i()==row && m1.get_j()==col)
				return true;
		}
		return b1;
	}
	
	public void send_to_which_move(int storedrow, int storedcol,String m_type,String sec_type,int sec_row_f,int sec_col_f)
	{
		
		which_move(storedrow, storedcol,m_type);
		which_move(sec_row_f,sec_col_f,sec_type);
		
	}
	
	//This Function shows which eat function 
	public void which_move(int storedrow, int storedcol,String m_type)
	{
		if(m_type.equals("eat_right_up"))
			eat_right_up(storedrow, storedcol);
		if(m_type.equals("eat_right_down"))
			eat_right_down(storedrow, storedcol);
		if(m_type.equals("eat_left_up"))
			eat_left_up(storedrow, storedcol);
		if(m_type.equals("eat_left_down"))
			eat_left_down(storedrow, storedcol);
		
	}
	
	public void make_King(int row,int col)
	{
		if(mat[row][col]<3 && mat [row][col]!=EMPTY)
			mat[row][col]+=2;
	}
	public void update_Kings()
	{
		for	(int col=0;col<this.DEFAULT_LEN;col++)
		{
			if (mat[0][col]==this.whc)
				this.make_King(0,col);
			if(mat[this.DEFAULT_LEN-1][col]==this.blc)
				this.make_King(this.DEFAULT_LEN-1, col);
		}
	}
	
	public void clear_Mv(int row, int col)
	{
		
		if(this.moving_opt.size()>0)
			for(int i=0;i<this.moving_opt.size();i++)
			{
				//if(this.moving_opt.get(i).get_i()!=row&&this.moving_opt.get(i).get_j()!=col)
				if(this.moving_opt.get(i).ret_Type().equals("mv"))
					this.moving_opt.remove(i);
			}
		
	}
	
	
	public void set_Movement(ArrayList<move_opt> moving)
	{
		this.moving_opt=moving;
	}
	
	public void make_Move(int row,int col,int storedRow,int storedCol,ArrayList<move_opt> moving)
	{
		
		this.chose_to_eat=false;
		set_Movement(moving);
		for	(move_opt m1: this.moving_opt)
		{
			if(m1.get_i()==row && m1.get_j()==col)
				if(m1.ret_Type().equals("mv"))
				{
					this.mat[row][col]=this.mat[storedRow][storedCol];
					this.mat[storedRow][storedCol]=0;
				}
				else
				{
					this.chose_to_eat=true;
					this.send_to_which_move(storedRow,storedCol,m1.ret_Type(),m1.get_Sec_m_type(),m1.getSec_row_from(),m1.getSec_col_from());//calling eat function
					/**if(this.can_eat_Twice(row, col))
					{
						this.clear_Mv(row,col);
					}**/					
				}
				
		}
		
	}
	
	public void update_Cells()
	{
		this.white_left=0;
		this.white_kings=0;
		this.red_kings=0;
		this.red_left=0;	
		for(int row = 0; row < this.DEFAULT_LEN; row++)
		{
			for(int col = 0; col < this.DEFAULT_LEN; col++)
			{
				if(this.mat[row][col]==this.whc||this.mat[row][col]==this.whck)
				{
					if(this.mat[row][col]==this.whc)
						this.white_left++;
					else 
						this.white_kings++;
				}
				if(this.mat[row][col]==this.blc||this.mat[row][col]==this.blck)
				{	
					if(this.mat[row][col]==this.blc)
						this.red_left++;
					else 
						this.red_kings++;
					
				}
			}
		}
	}
	
	/**
	 * 
	 * Needs To Be Fixed
	 * @return the Evaluation for the board
	 */
	public double eval_Func()
	{
		int num_of_pieces=0;
		short middle=0;
		double evaluate=0;
		//this.update_Cells();//updates the number of regular Cells and kings.
		for (int i=0; i<this.DEFAULT_LEN;i++)
		{
			for (int j=0; j<this.DEFAULT_LEN;j++)
			{
				if(this.mat[i][j]!=0)
				{
					if(this.mat[i][j]==this.blck)
						evaluate+=10;
					if(this.mat[i][j]==this.whck)
						evaluate-=10;
					if(this.mat[i][j]==this.blc)
						if(i>=4)
							evaluate+=7;
						else
							evaluate+=5;
					if(this.mat[i][j]==this.whc)
						if(i<4)
							evaluate-=7;
						else
							evaluate-=5;
					num_of_pieces+=1;
				}
				
				
			}
			
		}
		return evaluate/num_of_pieces;
	}
	
	public void print_Movings(ArrayList<move_opt> moving)
	{
		
		for(move_opt o1: moving)
		{
			o1.printOpt();
		}
		
	}
	
	public ArrayList<Cell> get_Cells_Arr_by_Color(String Color)
	{
		//System.out.println("Entered to get_Cells_Arr_by_Color");
		ArrayList<Cell> arr_Cells=new ArrayList<Cell>();
		
		for (int i=0; i<this.DEFAULT_LEN;i++)
		{
			for (int j=0; j<this.DEFAULT_LEN;j++)
			{
				if(Color.equals("white"))
					if(this.mat[i][j]==this.whc||this.mat[i][j]==this.whck)
					{
						ArrayList<move_opt> moving_options=this.where_To_Go(i, j);
						//System.out.println("Adding Cell To Arr");
						Cell c1=new Cell(i,j,moving_options);
						//this.print_Movings(c1.get_valid_moves());
						System.out.println("");
						arr_Cells.add(c1);
					}
				if (Color.equals("red"))
						if(this.mat[i][j]==this.blck||this.mat[i][j]==this.blc)
						{
							{
								ArrayList<move_opt> moving_options=this.where_To_Go(i, j);
								//System.out.println("Adding Cell To Arr");
								Cell c1=new Cell(i,j,moving_options);
								//this.print_Movings(c1.get_valid_moves());
								System.out.println("");
								arr_Cells.add(c1);
							}
						}
				this.init_move_arr();
			}
		}
		return arr_Cells;
	}
	
	public void init_num_cells()
	{
		this.white_left=0;
		this.white_kings=0;
		this.red_left=0;
		this.red_kings=0;
		
	}
	
	//Need To Check 2 things
	//no movement of players
	//4v4 thing
	public boolean gameOver()
	{ 
		//Wrapper for gameOverInternal
		this.init_num_cells();
		this.update_Cells();
		this.update_Kings();
		System.out.println("game over check :\nComputer : "+this.red_left+" \nPlayer : "+this.white_left+"\n");
		if((this.red_left==0&&this.red_kings==0) || (this.white_left==0 && this.white_kings==0 ))
		{
			System.out.println("Game over Bye Bye");
			return true;
		}
		return false;
	}
	
}
