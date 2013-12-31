//Koushik Krishnan

public class Board {
	private int length;
	private int width;
	private int[][] board;
    public void setDimensions(int l, int w){
    	board = new int [l][w];
    }
    public int[][] getBoardArray(){
    	board = new int[this.length][this.width];
    	return board;
    }
	public int getLength(){
		return board.length;
	}
	public int getWidth(){
		return board[0].length;
	}
    public Board (int l, int w){
    	this.length = l;
    	this.width = w;
    	setDimensions(length,width);

    }
    public int getValue(int length, int width){
    	return board[length][width];
    }
    public void toggleCell(int length, int width){
    	if(isLegal(length,width)){
    	if(board[length][width] == 0){
    		board[length][width] = 1;
    	}
    	else board[length][width] = 0;
    	}
    }
    public  int getNeighbors(int length, int width){
    	int numAlive=0;
    	if(isLegal(length+1,width)&&board[length+1][width] == 1 )
    		numAlive+=1;
    		else numAlive+=0;
    	if(isLegal(length,width+1)&&board[length][width+1] == 1)
    		numAlive+=1;
    		else numAlive+=0;
    	if(isLegal(length+1,width+1)&&board[length+1][width+1] == 1)
    		numAlive+=1;
    		else numAlive+=0;
    	if(isLegal(length-1,width)&& board[length-1][width]==1 )
    		numAlive+=1;
    		else numAlive+=0;
    	if( isLegal(length-1,width-1)&& board[length-1][width-1]==1)
    		numAlive+=1;
    		else numAlive+=0;
    	if( isLegal(length,width-1)&&board[length][width-1]==1 )
    		numAlive+=1;
    		else numAlive+=0;
    	if( isLegal(length+1,width-1)&&board[length+1][width-1]==1 )
    		numAlive+=1;
    		else numAlive+=0;
    	if( isLegal(length-1,width+1)&&board[length-1][width+1]==1 )
    		numAlive+=1;
    		else numAlive+=0;
    	return numAlive;
    }
   public void printBoard(){
   		System.out.println("------------------\n");
    	for(int i=0; i<board.length; i++){
    		for(int j = 0; j<board[i].length; j++){
    			System.out.print("\t" + board[i][j]);
    		}
    		System.out.println("\n");
    	}
    	System.out.println("------------------\n");
    }
    public int getCellPopulation(){
    	int numAlive=0;
    	for(int i = 0; i < board.length; i++){
    		for(int j = 0; j<board[i].length; j++){
    			if(board[i][j] == 1)
    				numAlive+=1;
    		}
    	}
    	return numAlive;
    }
    public int[][] clearBoard(){
    	for(int i=0; i<board.length; i++){
    		for(int j = 0; j < board[i].length; j++){
    			board[i][j] = 0;
    		}
    	}
    	return board;
    }
    public boolean isLegal(int length, int width){
    	boolean isLegal;
    	if(length < board.length && length >=0 && width >= 0 && width < board[0].length)
    		isLegal=true;
    	else {isLegal = false;}
    	return isLegal;

    }
    public void setACell(int length, int width, int value){
    	if(isLegal(length,width))
    	board[length][width] = value;
    }
    public boolean isEqual(Board a){
    	boolean isEquals = true;
    	for(int i = 0; i < this.length; i++){
    		for(int j = 0; j < this.width; j++){
    			if(board[this.length][this.width] != a.getValue(i,j)){
    				isEquals = false;
    			}
    		}
    	}
    	return isEquals;
    }
     public boolean copyAndEqual(Board a){
    	boolean isEquals = true;
    	for(int i = 0; i < this.length; i++){
    		for(int j = 0; j < this.width; j++){
    			if(board[i][j] != a.getValue(i,j) && a.isLegal(i,j) && this.isLegal(i,j)){
    				isEquals = false;
    				break;
    			}
    			if(board[this.length][this.width] == a.getValue(i,j) && a.isLegal(i,j) && this.isLegal(i,j)){
    				isEquals = true;
    			}
    		}
    	}
    	return isEquals;
    }
    //makes "this" board equal to Board a
    public void copy(Board a){
    	for(int i = 0; i < this.length; i++){
    		for(int j = 0; j < this.width; j++){
    			setACell(i,j,a.getValue(i,j));
    			}
    		}
    	}
    }



