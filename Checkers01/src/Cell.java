import java.util.ArrayList;

//Checkers03 Cell Class
/**
 * 
 *@author Ori Weis
 *20.4.21
 * 
**/

public class Cell {
	
	private int type; 
	private int i_index;
	private int j_index;
	
	private ArrayList<move_opt> moving_opt;//Move Options for Cell//
	
	/**
	 * 
	 * In order to solve the problem of get the full tree...
	 * I Should get For each cell the moving options this will ensure that all the info 
	 * wont get lost and will allow solving the Problem.   
	 * 
	 */
	//should change what the function gets
	
	public Cell(int row ,int col, ArrayList<move_opt> moving_options)
	{
		this.i_index=row;
		this.j_index=col;
		this.moving_opt=new ArrayList<move_opt>();
		for (int i=0;i<moving_options.size();i++)
		{
			this.moving_opt.add(moving_options.get(i));
		}
	}
	
	public Cell(int row ,int col)
	{
		this.i_index=row;
		this.j_index=col;
	}
	
	
	public Cell(Cell c1)
	{
		this.i_index=c1.Get_row();
		this.j_index=c1.Get_col();
		this.moving_opt=new ArrayList<move_opt>();
		for (int i=0;i<c1.get_valid_moves().size();i++)
			this.moving_opt.add(c1.get_valid_moves().get(i));;
	}
	
	public void MakeQueen()
	{
		if(this.type==3||this.type==4)
			return;
		this.type+=2;
	}
	
	public int getType()
	{	
		return this.type;
	}
	
	public void Set_i(int i)
	{
		this.i_index=i;
	}
	
	public void Set_j(int j)
	{
		this.j_index=j;
	}
	
	public int Get_row()
	{
		return this.i_index;
	}
	
	public void print_Valid_moves()
	{
		System.out.println("Printing Cell Options");
		for (move_opt o1: this.moving_opt)
		{
			System.out.println(o1);
		}
	}
	
	public ArrayList<move_opt> get_valid_moves()
	{
		return this.moving_opt;
	}
	public int Get_col()
	{
		return this.j_index;
	}
}
