import javax.swing.border.Border;

import edu.princeton.cs.algs4.Stack;

public class Board {
 private int grid;
 private char[] board;
 private int hammingNumber;
 private int manhattanNumber;
 
 public Board(int[][] blocks)        // construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
 {
  grid=blocks.length;
  if(grid<2){
   throw new NullPointerException();
  }
  
  board=new char[grid*grid];
  hammingNumber=0;
  manhattanNumber=0;
  
  for(int i=0;i<grid;i++){
   for(int j=0;j<grid;j++){
    int currentValue=blocks[i][j];
    board[(i*grid)+j]=(char)currentValue;
    
    if(currentValue!=0){
     if(currentValue!=((i*grid)+j+1)){
      hammingNumber++;
     }
    
    
    int col = (currentValue - 1) % grid;
    int row = (currentValue - col - 1) / grid;
    manhattanNumber+= (((col > j) ? (col - j) : (j - col)) + ((row > i) ? (row - i) : (i - row)));
    }
   }
  }
  
 }                                    
 public int dimension()                 // board dimension n
 {
  return grid;
 }
 public int hamming()                   // number of blocks out of place
 {
  return hammingNumber;
 }
 public int manhattan()                 // sum of Manhattan distances between blocks and goal
 {
  return manhattanNumber;
 }
 public boolean isGoal()                // is this board the goal board?
 {
  return hammingNumber == 0;
 }
 public Board twin()                    // a board that is obtained by exchanging any pair of blocks
 {
  int[][] twin = new int[grid][grid];
    
          for (int i = 0; i < grid; i++) {
                for (int j = 0; j < grid; j++) {
                    twin[i][j] = (int) board[(i * grid) + j];
                }
            }
    
            if ((twin[0][0] == 0) || (twin[0][1] == 0)) {
                swap(twin, 1, 0, 1, 1);
            } else {
                swap(twin, 0, 0, 0, 1);
            }
    
            return new Board(twin);
 }
 public boolean equals(Object y)        // does this board equal y?
 {
  if(y==this){
   return true;
  }
  
  if((y==null)||(y.getClass()!=this.getClass())){
   return false;
  }
  
  Board that = (Board) y;
            if (that.dimension() != grid) return false;
            for (int i = 0; i < (grid * grid); i++) {
               if (this.board[i] != that.board[i]) {
                   return false;
               }
           }
   return true;
 }
 public Iterable<Board> neighbors()     // all neighboring boards
 {
  int blankrow=0;
  int blankcol=0;
  Stack<Board> neighbours=new Stack<Board>();
  int[][] clone=new int[grid][grid];
  
  for(int i=0;i<grid;i++){
   for(int j=0;j<grid;j++){
    clone[i][j]=(int)board[(i*grid)+j];
    if(clone[i][j]==0){
     blankrow=i;
     blankcol=j;
    }
   }
  }
  
  if(blankcol!=0){
   swap(clone, blankrow, blankcol-1, blankrow, blankcol);
   neighbours.push(new Board(clone));
   swap(clone, blankrow, blankcol-1, blankrow, blankcol);
  }
  
  if(blankcol!=(grid-1)){
   swap(clone, blankrow, blankcol+1, blankrow, blankcol);
   neighbours.push(new Board(clone));
   swap(clone, blankrow, blankcol+1, blankrow, blankcol);
  }
  if(blankrow!=0){
   swap(clone, blankrow-1, blankcol, blankrow, blankcol);
   neighbours.push(new Board(clone));
   swap(clone, blankrow-1, blankcol, blankrow, blankcol);
  }
  if(blankrow!=(grid-1)){
   swap(clone, blankrow+1, blankcol, blankrow, blankcol);
   neighbours.push(new Board(clone));
   swap(clone, blankrow+1, blankcol, blankrow, blankcol);
  }
  return neighbours;
 }
 public String toString()               // string representation of this board (in the output format specified below)
 {
  StringBuilder stringBuilder=new StringBuilder();
  stringBuilder.append(grid+"\n");
  for(int i=0;i<grid;i++){
   for(int j=0;j<grid;j++){
    stringBuilder.append(String.format("%2d ", (int)board[(i*grid)+j]));
   }
  stringBuilder.append("\n");
  }
  return stringBuilder.toString();
 }
 private void swap(int[][] array, int i, int j, int a, int b) {
           int temp = array[i][j];
           array[i][j] = array[a][b];
           array[a][b] = temp;
       }
   
 public static void main(String[] args) // unit tests (not graded)
 {
  
 }
}
