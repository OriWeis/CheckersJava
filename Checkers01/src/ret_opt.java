
public class ret_opt {

	private double value;
	private matrix new_mat;
	
	public ret_opt(matrix m1,double val)
	{
		this.new_mat=new matrix();
		this.new_mat.set_matrix(m1);
		this.value=val;
	}
	
	public double getValue() {
		return value;
	}

	public void set_Evaluation()
	{
		 this.value=this.new_mat.eval_Func();
	}
	
	public void setValue(double value)
	{
		this.value = value;
	}

	public matrix getNew_mat() 
	{
		return new_mat;
	}

	public void setNew_mat(matrix new_mat) 
	{
		this.new_mat = new_mat;
	}
	

}
