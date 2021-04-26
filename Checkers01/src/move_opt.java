
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
	
	private String prev_m_type;
	private int sec_row_from, sec_col_from;
	/**
	 * move type can be:
	 * mv -> normal move
	 * Eating Functions : eat_right_up, eat_right_down, eat_left_up, eat_left_down 
	 */
	private String m_type,sec_m_type;
	
	
	public move_opt(int i, int j, String move_type)
	{
		this.i_index=i;
		this.j_index=j;
		this.m_type=move_type;
		this.sec_m_type="";
		this.sec_row_from=-1;
		this.sec_col_from=-1;
		
	}
	
	//this moving option is for what is skipped
	/**
	 * 
	 * This Constructor is built for eating Twice 
	 * @param i=row
	 * @param j=column
	 * @param move_type
	 * @param prev_eat_row
	 * @param prev_eat_col
	 */
	public move_opt(int i, int j, String move_type,String secm,int sec_row_from,int sec_col_from)
	{
			this.i_index=i;
			this.j_index=j;
			this.m_type=move_type;
			this.sec_m_type=secm;
			this.sec_row_from=sec_row_from;
			this.sec_col_from=sec_col_from;
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
	
	public String get_Sec_m_type() {
		return sec_m_type;
	}

	public void set_Sec_m_type(String sec_m_type) {
		this.sec_m_type = sec_m_type;
	}

	public int getSec_row_from() {
		return sec_row_from;
	}

	public void setSec_row_from(int sec_row_from) {
		this.sec_row_from = sec_row_from;
	}

	public int getSec_col_from() {
		return sec_col_from;
	}

	public void setSec_col_from(int sec_col_from) {
		this.sec_col_from = sec_col_from;
	}

	public void printOpt()
	{
		System.out.println("Move opt "+ "{"+this.i_index+","+this.j_index+"}");
	}
}
