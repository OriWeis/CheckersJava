
//Checkers03
/**
 * 
 *@author Ori Weis
 *25.3.21
 * 
**/

public class move_opt {
	
	private int i_index;
	private int j_index;
	/**
	 * move type can be:
	 * mv -> normal move
	 * Eating Functions : eat_right_up, eat_right_down, eat_left_up, eat_left_down 
	 */
	private String m_type;
	
	
	public move_opt(int i, int j, String move_type)
	{
		this.i_index=i;
		this.j_index=j;
		this.m_type=move_type;
		
	}
	
	public String ret_Type()
	{
		return this.m_type;
	}
	public move_opt()
	{
		this.i_index=-1;
		this.j_index=-1;
		
	}
	
	public int get_i()
	{
		return this.i_index;
	}
	
	public int get_j()
	{
		return this.j_index;
	}
	
	public void set_Move_opt(int i, int j,String str1)
	{

		this.i_index=i;
		this.j_index=j;
		this.m_type=str1;
		
	}
	public void printOpt()
	{
		System.out.println("Move opt "+ "{"+this.i_index+","+this.j_index+"}");
	}
}
