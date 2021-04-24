
public class move_val {

	//private Cell cell;
	private double value;
	private Cell current_loc;
	private int row,col;
	public move_val(Cell c, double val,int row,int col)
	{
		
		this.current_loc=new Cell(c.Get_row(),c.Get_col());
		this.value=val;
		this.row=row;
		this.col=col;
	}


	public Cell getCurrent_loc() {
		return current_loc;
	}


	public void setCurrent_loc(Cell current_loc) {
		this.current_loc = current_loc;
	}


	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
	}


	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}


	public double get_Value() {
		return value;
	}

	public void set_Value(double value) {
		this.value = value;
	}
	
	
	
}
