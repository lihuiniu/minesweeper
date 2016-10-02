package com.whale.minesweeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 9/27/2016
 * @author Whale
 *
 */
public class MineSweeper {

	MineBoard mBoard;
    
	/**
	 * Parameterized Constructor
	 * @param rows
	 * @param cols
	 * @param numOfMines
	 * @throws IOException
	 */
    public MineSweeper(int rows, int cols, int numOfMines) throws IOException{
        int row;
        int col;
        StringTokenizer strToken;
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        mBoard = new MineBoard(rows,cols,numOfMines);
        boolean won=false;
        
        //Accept user input and reveals the specified square
        //Stop when a mine is hit or win state is achieved
        do{
            System.out.println(mBoard);
            System.out.print("Please enter the sqare row followed by column>>>>>");
            
            strToken = new StringTokenizer(in.readLine(), " ,");
            if (!strToken.hasMoreTokens()){
            	row = -1;
            } else {
            	row = Integer.parseInt(strToken.nextToken());
            }
            
            if (!strToken.hasMoreTokens()){
            	col = -1;
            } else {
            	col = Integer.parseInt(strToken.nextToken());
            }
          
        } while (mBoard.reveal(row, col) && !(won = mBoard.hasWon()));
        
        for (row = 0; row < rows; row++){            
             for (col = 0; col < cols; col++){ 
                mBoard.reveal(row, col);       
             }
        }
        System.out.println(mBoard);
        
        if (won){
            System.out.println("<<<<<You are the winner! Congratulations!>>>>>");
        } else {
             System.out.println("<<<<<Game Over! You can retry now.>>>>>");
        }
    }
    public static void main(String args[])throws IOException{
   
        int rows = 8;
        int cols = 8;
        int numOfMines = 9;
        
        if (args.length == 3){
            rows = Integer.parseInt(args[0]);
            cols = Integer.parseInt(args[1]);
            numOfMines = Integer.parseInt(args[2]);
        }
        MineSweeper mSweeper = new MineSweeper(rows, cols, numOfMines);
    }
}