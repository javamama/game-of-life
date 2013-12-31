/*
 *Koushik Krishnan
 *12-5-11
 */

/*
 * The sole purpose of the GameOfLife class is to calculate the next generation
 * for a board. All of the other methods life clear and print simply call the
 * respective methods in the Board class
 */

public class GameOfLife {
	//declare a Board object where the next generation will be calculated
	private Board lifeBoard;
	//set life board to the given number of rows and columns
    public GameOfLife(int rows, int columns) {
    	lifeBoard = new Board(rows, columns);
    }
    //when the parameters are empty, set the rows and columns
    //to 0
    public GameOfLife(){
    	lifeBoard = new Board(0,0);
    }
    //set the rows and columns of lifeBoard to the rows and columns of the
    //given Board
    public GameOfLife(Board a){
    	lifeBoard = new Board(lifeBoard.getLength(), lifeBoard.getWidth());
    }
    //returns the number of rows in the board
    public int getBoardLength(){
    	return lifeBoard.getLength();
    }
    //returns the number of columns in the board
    public int getBoardWidth(){
    	return lifeBoard.getWidth();
    }
    //set a cell to alive or dead at the specified index
    public void setCell(int rows, int columns, int value){
    	lifeBoard.setACell(rows,columns,value);
    }
    //toggles the cell at the specified index
    // alive -> dead
    // dead -> alive
    public void toggleACell(int rows, int column){
    	lifeBoard.toggleCell(rows,column);
    }
    //returns the number of alive neighbors the cell has
    public int getCellNeighbors(int row, int column){
    	return lifeBoard.getNeighbors(row,column);
    }
    //returns value of the cell
    //alive = 1
    //dead = 0
    public int getCellValue(int row, int column){
    	return lifeBoard.getValue(row, column);
    }
    //sets all the indexes of the board to 0
    public void clear(){
    	lifeBoard.clearBoard();
    }
    /*
     *Calculates the next generation:
     *if the number of neighbors is 4 or above, the cell dies
     *if the number of neighbors is 3, a cell is born
     *if the number of neighbors is less than 2, the cell dies
     *if the number of neighbors is 2, nothing happens
     *
     *The method has two boards, lifeBoard and nextBoard
     *It will calculate the nextGeneration based on the lifeBoard
     *and will set the respective cells on the nextBoard.
     *
     *At the end, the nextBoard is copied onto the lifeBoard
     *and the nextBoard is cleared
     */
    public void nextGeneration(){
		Board nextBoard = new Board(lifeBoard.getLength(), lifeBoard.getWidth());
    		for(int i = 0; i < lifeBoard.getLength(); i++){
    			for(int j = 0; j < lifeBoard.getWidth(); j++){
    				if(lifeBoard.getNeighbors(i,j) == 3){
    					nextBoard.setACell(i,j,1);
    				}
    				if(lifeBoard.getNeighbors(i,j) >= 4){
    					nextBoard.setACell(i,j,0);
    				}
    				if(lifeBoard.getNeighbors(i,j) == 0 || lifeBoard.getNeighbors(i,j) == 1){
    					nextBoard.setACell(i,j,0);
    				}
    				if(lifeBoard.getNeighbors(i,j) == 2 && lifeBoard.getValue(i,j) == 1){
    					nextBoard.setACell(i,j,1);
    				}
    			}
    	}
    	lifeBoard.copy(nextBoard);
		nextBoard.clearBoard();
    }
    //prints a String representation of the board
    public void print(){
    	lifeBoard.printBoard();
    }

    public static void main(String args[]){
    	//create a GameOfLife object, and make a graphical representation of it
		GameOfLife a = new GameOfLife(11,11);
		Output.makeFrame(a);
    }
}