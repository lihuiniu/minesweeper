package com.whale.minesweeper;


/**
 * 
 * @author Whale
 * Square class has two fields: revealed and value
 */
public class Square {

	//Declare the instance variable
	private boolean revealed;
    private char value;
    
    //Constructor with value as parameter
	public Square(char value) {
		super();
		this.value = value;
	}
	
	//getter
	public boolean isRevealed() {
		return revealed;
	}
	
	//setter
	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}
	
	//getter
	public char getValue() {
		return value;
	}
	
	//setter
	public void setValue(char value) {
		this.value = value;
	}

	@Override
	public String toString() {
		 if (revealed){
	            return (value+"");
		 } else {
	            return "-";
		 }
	}  
}
