import java.util.ArrayList;

public class Board {

    private int N, board[][];

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    
    public Board(int[][] tiles){
        N = tiles.length;
        //col = tiles[0].length;
        board = new int [N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                board[i][j] = tiles[i][j];
            }
        }

    }
                                           
    // string representation of this board
    public String toString(){
        String s = "" + N +"\n";
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                // if(board[i][j]){
                //     board[i][j] = 0;
                // }
                s += ("" + board[i][j]+" ");
            }
            s += "\n";
        }


        return s;
    }

    // board dimension n
    public int dimension(){
        return N;

    }

    // number of tiles out of place
    public int hamming(){
        int c = 1 ;
        int mismatch = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(board[i][j] != c && board[i][j] != 0){
                   mismatch ++ ;
                }
                c ++ ;
            }
        }

        return mismatch;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int MD = 0 ;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(board[i][j] == 0){
                    continue;
                }
                int row = (board[i][j]-1)/N;
                int col = (board[i][j]-1)%N;

                MD += (Math.abs(row - i) + Math.abs(col - j));
            }
        }

        return MD;
    }

    // is this board the goal board?
    public boolean isGoal(){
        if(manhattan() == 0){
            return true;
        }
        else{
            return false;
        }
    }

    // does this board equal y?
    public boolean equals(Object y){
        if(y.getClass() != this.getClass() || y == null){
             return false ;
        }

        return y.toString().equals(this.toString());
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        ArrayList <Board> neighbourlist = new ArrayList<Board>() ;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(board[i][j] == 0){
                    if(j != 0){
                        Board b1 = new Board(this.board);
                        
                        b1.board[i][j] = b1.board[i][j-1];
                        b1.board[i][j-1] = 0;

                       // swap(b1.board[i][j-1], b1.board[i][j]);
                        neighbourlist.add(b1);

                        
                        
                    }
                    if(j != N-1){
                        Board b1 = new Board(this.board);
                        b1.board[i][j] = b1.board[i][j+1];
                        b1.board[i][j+1] = 0 ;
                        

                        //swap(b1.board[i][j], b1.board[i][j+1]);
                        neighbourlist.add(b1);

                    }
                    if(i != 0){
                        Board b1 = new Board(this.board);
                        int t = b1.board[i-1][j];
                        b1.board[i][j] = t;
                        b1.board[i-1][j] = 0 ;
                        

                        //swap(b1.board[i-1][j], b1.board[i][j]);
                        neighbourlist.add(b1);
                    }
                    if(i != N-1){
                        Board b1 = new Board(this.board);
                        b1.board[i][j] = b1.board[i+1][j];
                        b1.board[i+1][j] = 0 ;
                        

                        // swap(b1.board[i+1][j], b1.board[i][j]);
                        neighbourlist.add(b1);
                    }

                    break ;

                }
            }
        }

        return neighbourlist;



    }

    // private void swap(int a,int b){
    //     int t = a ;
    //     a = b ;
    //     b = t ;
    // }

    

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){

        Board b1 = new Board(this.board);
        
        if(b1.board[0][0] == 0 || b1.board[0][1] == 0){
            //swap(b1.board[1][0], b1.board[1][1]);
            int t = b1.board[1][0];
            b1.board[1][0] = b1.board[1][1];
            b1.board[1][1] = t ;
            
        }
        else{
            //swap(b1.board[0][0], b1.board[0][1]);
            int t = b1.board[0][1];
            b1.board[0][1] = b1.board[0][0];
            b1.board[0][0] = t;
        }


        return b1;



    }

    // unit testing (not graded)
    public static void main(String[] args){
        


    }

}