package com.whale.minesweeper;

import java.util.Random;

public class MineBoard {

	 private Square[][] board;
	    private int rows;
	    private int cols;
	    private int numOfMines;
	    private int numOfUncovered;      //to keep track number of squares uncovered (for win condition)
	    
	    
	    /**
	     * 
	     * @param rows
	     * @param cols
	     * @param numOfMines
	     */
	    public MineBoard(int rows, int cols, int numOfMines) {
			this.rows = rows;
			this.cols = cols;
			this.numOfMines = numOfMines;
			board = new Square[rows][cols];
			numOfUncovered = 0;
			fillSquares();
		}
		
	    /**
	     * No_parameter constructor
	     * 8x8 game with 9 mines
	     */
	    public MineBoard(){
	        this(8,8,9);
	    }
	    
	    /**
	     * Initialize the squares, and then places the mines randomly.
	     */
	    private void fillSquares(){
	        
	        int row;
	        int col;
	        int countOfMines = numOfMines;
	        
	        Random rand = new Random();
	        for (row = 0; row < rows; row++){
	            for (col = 0; col <cols; col++){
	                board[row][col]=new Square('?');      
	            }
	        }
	        
	        //Place the mines randomly.
	        while (countOfMines > 0){                            
	            row = rand.nextInt(rows);
	            col = rand.nextInt(cols);
	            if (board[row][col].getValue() =='*'){
	            	continue;//If one square already has mine, then continue.
	            }
	            board[row][col] = new Square('*');
	            countOfMines--;
	        }
	        
	    }
	    
	    /**
	     * 
	     * @param row
	     * @param col
	     * @return an integer for the number of mines surrounding the square at board[row][col].
	     */
	    private int calculateMines(int row, int col){
	        
	        int count=0;
	        for (int y = -1; y <= 1; y++){
	            for (int x = -1; x <= 1; x++){
	                if (y == 0 && x == 0){
	                	continue;
	                } 
	                if (row + y <0 || row + y >= rows){
	                	continue;
	                }
	                if (col + x <0 || col + x >= cols){
	                	continue;
	                }
	                if (board[row + y][col + x].getValue()=='*'){
	                    count++;
	                }
	            }
	        }
	        return count;
	    }
	    
	    /**
	     * 
	     * @param row
	     * @param col
	     * @return  false if a mine is hit; otherwise, return true.
	     */
	    public boolean reveal(int row, int col){
	        
	        //Boundary Checks
	        if (row < 0 || row >= rows || col < 0 || col >= cols || board[row][col].isRevealed()){
	        	return true;
	        }
	        
	        //Place revealed
	        board[row][col].setRevealed(true);          
	   
	        //If hit a mine, return false.
	        if (board[row][col].getValue()=='*'){
	        	return false;          
	        }
	        char ch = (char)(calculateMines(row, col) + '0');
	        board[row][col].setValue(ch);
	        numOfUncovered++;
	        //???
	        //?*?
	        //?*?
	        if (board[row][col].getValue()=='0'){      
	        	reveal(row - 1, col - 1); //left-upper
	        	reveal(row - 1, col); //upright
	        	reveal(row - 1, col + 1);//right-upper
	            reveal(row, col - 1);//left              
	            reveal(row, col + 1);//right
	            reveal(row + 1, col - 1);//left-below
	            reveal(row + 1, col);//below
	            reveal(row + 1, col + 1);//right-below
	        }
	        return true;

	    }
	    
	    /**
	     * 
	     * @return true if uncovered all the squares that are not mines, otherwise return false.
	     */
	    public boolean hasWon(){
	    	int countOfNonMines = rows * cols - numOfMines;
	        return numOfUncovered == countOfNonMines;
	    }
	    
	    @Override
	    public String toString(){

	    	int row;
	    	int col;
	        String output="  |";
	        
	        for (col = 0; col < cols; col++){                    
	            output += Integer.toString(col)+" ";
	        }
	        output += "\n==+"; 
	        
	        for (col = 0; col <cols; col++){
	        	output += "==";    
	        }
	        output += "\n";                             
	        
	        for (row = 0; row < rows; row++){                  
	        	output += (row + " |");                      
	            for (col = 0; col < cols; col++){               
	            	output += (board[row][col]+ " ");        
	            }                                   
	            output += "\n";
	        }
	        return output;
	    }
	}