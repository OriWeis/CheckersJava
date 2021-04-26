import java.util.ArrayList;
import java.lang.Math.*;

/**
 * 
 * AI Algorithm Class
 *@author Ori Weis
 *21.4.21
 * 
**/
public class AI_Algorithm 
{
	private static double infi=Double.MAX_VALUE;
	private static double min_infi=Double.MIN_VALUE;
	
	//private double value;
	private matrix mat_to_ret;
	
	/**
	 * This Constructor Should get 
	 */
	public AI_Algorithm(matrix m,int depth,Checkers c)
	{	
		matrix mat12= new matrix();
		ret_opt ret12=new ret_opt(mat12,0);
		matrix m1=new matrix(m);
		ret_opt ret1=AlphaBeta(m,depth,true,Double.MIN_VALUE,Double.MAX_VALUE,c,ret12);
		this.mat_to_ret=ret1.getNew_mat();
		
		System.out.println("Printing Final Matrix");
		ret1.getNew_mat().print_mat();
	}
	
	/**
	 *m1 is the matrix state
	 * depth is the depth of the tree that we want
	 * max_player is a boolean value that tells us if we want to minimize the value or maximize it
	 * 
	 **/
	public ret_opt AlphaBeta(matrix m1, int depth,boolean max_player,double alpha,double beta,Checkers c1,ret_opt r1)
	{
		double maxEval,minEval,evaluation;
		
		if (depth ==0 ||m1.gameOver()==true)
		{
			r1.set_Evaluation();
			return r1;
		}
		
		//System.out.println("Running Minimax Algo");
		
		if (max_player==true)
		{
			maxEval=(double)this.min_infi;
			matrix best_move=new matrix();
			ArrayList<matrix> moves = this.get_all_moves(m1,"red", c1);
			for (matrix mat :moves)
			{
				//System.out.println("Entered To For loop of moves");
				//mat.print_mat();
				//System.out.println("\ndepth is ->"+depth+"\n");
				evaluation=AlphaBeta(mat,depth -1,false,alpha,beta,c1,r1).getValue();
				maxEval=Math.max(maxEval, evaluation);
				if(evaluation==maxEval)
					best_move.set_matrix(mat);
				/**
				 * for Prunning Alpha-Beta
				 */
				alpha=Math.max(alpha, maxEval);
				if (alpha >= beta)
						break;
				
			}
			//System.out.println("depth is "+depth);
			r1=new ret_opt(best_move,maxEval);
			//System.out.println("MaxEval is -> "+maxEval);
			return r1;
		}
		
		else
		{
			minEval=(double)this.infi;
			matrix best_move=new matrix();
			ArrayList<matrix> moves = this.get_all_moves(m1,"white", c1);
			for (matrix mat :moves)
			{
				//mat.print_mat();
				//System.out.println("\ndepth is ->"+depth);
				evaluation=AlphaBeta(mat,depth -1,true,alpha,beta,c1,r1).getValue();
				minEval=Math.min(minEval, evaluation);
				if(minEval==evaluation)
					best_move.set_matrix(mat);
				/**
				 * for Prunning Alpha-Beta
				 */
				beta=Math.min(beta, minEval);
				if (beta <= alpha)
						break;
			}
			//System.out.println("depth is "+depth);
			r1=new ret_opt(best_move,minEval);
			//System.out.println("MinEval is -> "+minEval);
			return r1;
		}
				
	}
	
	public matrix return_new_board()
	{
		return this.mat_to_ret;
	}
	
	/**
	 * simulate_move function
	 * if you make that move the new board will look like this!!.
	**/
	public matrix simulate_move(matrix m1,Cell c1,int move_row,int move_col)
	{
		//System.out.println("simulating a move");
		m1.make_Move(move_row, move_col, c1.Get_row(), c1.Get_col(),c1.get_valid_moves());
		return m1;
	}
	
	
	/**
	 * 
	 * Now This Function Is Fcking Working
	 * @param m1
	 * @param Color
	 * @param c1
	 * @return
	 */
	public ArrayList<matrix> get_all_moves(matrix m1,String Color,Checkers c1)
	{
		ArrayList<matrix> board_movings=new ArrayList<matrix>();

		ArrayList<Cell> arr=m1.get_Cells_Arr_by_Color(Color);
		
		//System.out.println("array size is "+arr.size());
		
		for(int i=0;i<arr.size();i++)
		{
			//System.out.println("array of Cells exists");
			
			ArrayList<move_opt> moving_options=arr.get(i).get_valid_moves();
			
			//System.out.println(arr.get(i).get_valid_moves().size());
			for (int j=0; j<moving_options.size();j++)
			{
				//System.out.println("\nentered to all valid moves of "+Color+"\n");
				Cell cell1=new Cell(arr.get(i));
				matrix temp_board=new matrix();
				temp_board.set_matrix(m1);
				matrix new_board=simulate_move(temp_board,cell1,
					   moving_options.get(j).get_i(),moving_options.get(j).get_j());
				matrix b1= new matrix(new_board);
				board_movings.add(b1);
			}
			
		}
		
		return board_movings;
		
	}
	
}
