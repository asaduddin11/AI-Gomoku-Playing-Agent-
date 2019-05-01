z
import java.awt.Color;
import java.util.Arrays;

/** This is my class, it traverses the board and finds the empty positions, and using minimax algorithm and alpha beta pruning tries to find the best position to deploy the stone.
 *	Author: Asaduddin Bilal Mohammed
 **/
 class Player1 extends GomokuPlayer
 {
   public Move chooseMove(Color[][] board, Color me)
  {
    Color OppColr;
    OppColr = findColor(me);

    if(board[3][3] == null)
    return new Move(3,3);   // Trial1's algorithms begins the game at the centre. 
    else{


      int[][] boardPotentials = new int[8][8];
      boardPotentials = calculatePotential(board, me);






      int[][] score5 = new int[8][8];
      int[][] score6 = new int[8][8];
      int[][] score7 = new int[8][8];
      int[][] score8 = new int[8][8];
      int[][] score9 = new int[8][8];
      int[][] score10 = new int [8][8];
      score5 = countConsecutiveInRow1(board,0,0,me);
      score6 = countConsecutiveInCol1(board,0,0,me);
      score7 = countConsecutiveInDiag1(board,0,0,me);
      score8 = countConsecutiveInRow2(board,0,0,me);
      score9 = countConsecutiveInCol2(board,0,0,me);
      score10 = findMaxScoreBoard(score5, score6, score7, score8, score9);
      //System.out.println("Successful score5");
      int[] best = findMaxScore(score10);
      if(Block(board, OppColr) != null)
      {
        return Block(board, OppColr);
      }

      while(board[best[0]][best[1]] != null) {
        int row = (int) (Math.random() * 8);	// values are from 0 to 7
        int col = (int) (Math.random() * 8);
        if (board[row][col] == null)			// is the square vacant?
          return new Move(row, col);
      }

      return new Move(best[0],best[1]);

      //for (int row = 0; row < GomokuBoard.ROWS; row++)
  			//for (int col = 0; col < GomokuBoard.COLS; col++)
  				//if (board[row][col] == null)
  					//return new Move(row, col);

}
 //return null;
}//choosemove


public int[][] calculatePotential(Color[][] board, Color me){

  

}


public Move Block(Color[][] board, Color Opp)
{
  int[][] score5 = new int[8][8];
  int[][] score6 = new int[8][8];
  int[][] score7 = new int[8][8];
  int[][] score8 = new int[8][8];
  int[][] score9 = new int[8][8];
  int[][] score10 = new int [8][8];
  score5 = countConsecutiveInRow1(board,0,0,Opp);
  score6 = countConsecutiveInCol1(board,0,0,Opp);
  score7 = countConsecutiveInDiag1(board,0,0,Opp);
  score8 = countConsecutiveInRow2(board,0,0,Opp);
  score9 = countConsecutiveInCol2(board,0,0,Opp);
  score10 = findMaxScoreBoard(score5, score6, score7, score8, score9);

  for (int row = 0; row < GomokuBoard.ROWS; row++)
             {
             for (int col = 0; col < GomokuBoard.COLS; col++)
               {
                   System.out.println("OppColr At:"+row+","+col+"is:"+score10[row][col]);
                     if(score10[row][col] >= 100)
                     {
                       if(board[row][col] == null)
                       return new Move(row,col);
                     }
                   }
                 }
return null;
}


public int[][] countConsecutiveInRow1(Color[][] board, int StartRow, int StartCol, Color MyColr)
{
int MyCount = 0;
int row = StartRow;
int col = StartCol;
int[][] score = new int[8][8];
for (row =  StartRow; row < GomokuBoard.ROWS; row++)
  {
    MyCount =0;
    for(col = StartCol; col < GomokuBoard.COLS; col++)
    {
      if(board[row][col] != null)
      {
        if(board[row][col] == MyColr)
        {
          MyCount++;
        }
        else
        {
          MyCount = 0;
        }
      }
      else
      {
        score[row][col] = evaluate(MyCount);
        //System.out.println("Score at Row = " +row+" Column = "+col+" is = "+score[row][col]);
        MyCount = 0;
      }
    }

}
return score;
}

public int[][] countConsecutiveInRow2(Color[][] board, int StartRow, int StartCol, Color MyColr)
{
int MyCount = 0;
int row = StartRow;
int col = StartCol;
int[][] score = new int[8][8];
for (row =  StartRow; row < GomokuBoard.ROWS; row++)
  {
    MyCount =0;
    for(col = GomokuBoard.COLS - 1; col >= 0; col--)
    {
      if(board[row][col] != null)
      {
        if(board[row][col] == MyColr)
        {
          MyCount++;
        }
        else
        {
          MyCount = 0;
        }
      }
      else
      {
        score[row][col] = evaluate(MyCount);
        //System.out.println("Score at Row = " +row+" Column = "+col+" is = "+score[row][col]);
        MyCount = 0;
      }
    }

}
return score;
}


public int[] findMaxScore(int[][] score)
{
  int[][] score5 = new int[8][8];
  int MaxVal = 0;
  int[] best = new int[2];
  for (int row = 0; row < GomokuBoard.ROWS; row++)
  {
  for (int col = 0; col < GomokuBoard.COLS; col++)
    {
      if(score[row][col] > MaxVal)
      {
        MaxVal = score[row][col];
        best[0] = row;
        best[1] = col;
      }
    }
  }
  return best;
  //System.out.println("Best move to block found at:"+bestRow+","+bestCol+"is:"+MaxVal);
}

public int[][] findMaxScoreBoard(int[][] score1, int[][] score2, int[][] score3, int[][] score4, int[][] score5)
{
  int[][] finalscore1 = new int[8][8];
  int[][] finalscore2 = new int[8][8];
  int[][] finalscore3 = new int[8][8];
  int[][] finalscore4 = new int[8][8];
  for (int row = 0; row < GomokuBoard.ROWS; row++)
  {
  for (int col = 0; col < GomokuBoard.COLS; col++)
    {
      if(score1[row][col] >= score2[row][col])
      {
        finalscore1[row][col] = score1[row][col];
      }
      else
      {
        finalscore1[row][col] = score2[row][col];
      }
    }
  }
  for (int row = 0; row < GomokuBoard.ROWS; row++)
  {
  for (int col = 0; col < GomokuBoard.COLS; col++)
    {
      if(score3[row][col] >= score4[row][col])
      {
        finalscore2[row][col] = score3[row][col];
      }
      else
      {
        finalscore2[row][col] = score4[row][col];
      }
    }
  }
  for (int row = 0; row < GomokuBoard.ROWS; row++)
  {
  for (int col = 0; col < GomokuBoard.COLS; col++)
    {
      if(finalscore1[row][col] >= finalscore2[row][col])
      {
        finalscore3[row][col] = finalscore1[row][col];
      }
      else
      {
        finalscore3[row][col] = finalscore2[row][col];
      }
    }
  }
  for (int row = 0; row < GomokuBoard.ROWS; row++)
  {
  for (int col = 0; col < GomokuBoard.COLS; col++)
    {
      if(finalscore3[row][col] >= score5[row][col])
      {
        finalscore4[row][col] = finalscore3[row][col];
      }
      else
      {
        finalscore4[row][col] = score5[row][col];
      }
    }
  }
  return finalscore4;
  //System.out.println("Best move to block found at:"+bestRow+","+bestCol+"is:"+MaxVal);
}

public int[][] countConsecutiveInCol1(Color[][] board, int StartRow, int StartCol, Color MyColr)
{
int MyCount = 0;
int row = StartRow;
int col = StartCol;
int[][] score = new int[8][8];
for (col =  StartCol; col < GomokuBoard.COLS; col++)
  {
    MyCount =0;
    for(row = StartRow; row < GomokuBoard.ROWS; row++)
    {
      if(board[row][col] != null)
      {
        if(board[row][col] == MyColr)
        {
          MyCount++;
        }
        else
        {
          MyCount = 0;
        }
      }
      else
      {
        score[row][col] = evaluate(MyCount);
        //System.out.println("Score at Row = " +row+" Column = "+col+" is = "+score[row][col]);
        MyCount = 0;
      }
    }

}
return score;
}

public int[][] countConsecutiveInCol2(Color[][] board, int StartRow, int StartCol, Color MyColr)
{
int MyCount = 0;
int row = StartRow;
int col = StartCol;
int[][] score = new int[8][8];
for (col =  StartCol; col < GomokuBoard.COLS; col++)
  {
    MyCount =0;
    for(row = GomokuBoard.ROWS - 1; row >= 0; row--)
    {
      if(board[row][col] != null)
      {
        if(board[row][col] == MyColr)
        {
          MyCount++;
        }
        else
        {
          MyCount = 0;
        }
      }
      else
      {
        score[row][col] = evaluate(MyCount);
        //System.out.println("Score at Row = " +row+" Column = "+col+" is = "+score[row][col]);
        MyCount = 0;
      }
    }

}
return score;
}


public int[][] countConsecutiveInDiag1(Color[][] board, int StartRow, int StartCol, Color MyColr)
{
int MyCount = 0;
int row = StartRow;
int col = StartCol;
int[][] score = new int[8][8];
for (col =  StartCol; col < GomokuBoard.COLS; col++)
  {
    MyCount =0;
    if(board[col][col] != null)
    {
      if(board[col][col] == MyColr)
      {
        MyCount++;
      }
      else
      {
        MyCount = 0;
      }
    }
    else
    {
      score[col][col] = evaluate(MyCount);
      //System.out.println("Score at Row = " +row+" Column = "+col+" is = "+score[row][col]);
      MyCount = 0;
    }
}
return score;
}


public Color findColor(Color me)
{
  Color OppColr;
  if(me == Color.white)
  {
        OppColr = Color.black;
  }
  else
      OppColr = Color.white;

    return OppColr;
}





    public int evaluate(int eval)
    {
      switch(eval){
      case 0:
      return 0;
      case 1:
      return 1;
      case 2:
      return 10;
      case 3:
      return 100;
      case 4:
      return 1000;
      default:
      break;
    }
    return eval;
  }


}//classend
