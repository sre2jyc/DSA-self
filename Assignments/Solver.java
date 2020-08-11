import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.Stack;

public class Solver {

    private final int moves;
    private final boolean isSolvable;
    private Stack<Board> solution = new Stack<>();

    private class SearchNode implements Comparable<SearchNode>{
        private int moves;
        private Board board;
        private SearchNode previous;
        public int priority;

        public SearchNode(Board board,int moves,SearchNode previous){

            this.moves = moves;
            this.board = board;
            this.previous = previous;

            priority = board.manhattan() + moves ;
        }

        public int compareTo(SearchNode that){
            if(this.priority > that.priority){
                return +1 ;
            }
            else if(this.priority < that.priority){
                return -1 ;
            }
            return 0 ;
        }
    }

    private class SolutionObj{
        public final SearchNode solution;
        public final int moves;
        public final boolean isSolvable;

        private SolutionObj(SearchNode solution, int moves, boolean isSolvable) {
            this.moves = moves;
            this.isSolvable = isSolvable;
            this.solution = solution;
        }
    }



    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
        if(initial == null){
            throw new IllegalArgumentException();
        }
        SolutionObj sol ;
        if(initial.isGoal()){
            sol = new SolutionObj(new SearchNode(initial, 0, null), 0, true);

        }
        else{

        MinPQ <SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(initial, 0, null));

        //Board twin = initial.twin();
        MinPQ <SearchNode> tpq = new MinPQ<>();
        tpq.insert(new SearchNode(initial.twin(), 0, null));
        
        // StdOut.println("pq = "+pq.size()+"  tpq = "+tpq.size());

         sol = solve(pq,tpq);
        }
        this.isSolvable = sol.isSolvable;
        this.moves = sol.moves;
        this.solution = buildSolution(sol.solution);


    }

    private SolutionObj solve(MinPQ <SearchNode> pq,MinPQ <SearchNode> tpq){
        SearchNode node, tnode ;
        node = pq.min();
        tnode = tpq.min();
        //tnode = tpq.delMin();

        while(!node.board.isGoal() || !tnode.board.isGoal()){
           node = pq.delMin();
            for(Board n : node.board.neighbors()){
                //StdOut.println(n.toString());
                if(n.isGoal()){
                    node = new SearchNode(n, node.moves + 1, node);
                   // pq.insert(Gnode);
                    return new SolutionObj(node, node.moves,true);
                }
                else if(node.previous == null || !node.previous.board.equals(n)){
                    pq.insert(new SearchNode(n, node.moves + 1, node));
                }
            } 
            

            tnode = tpq.delMin();
            for(Board n : tnode.board.neighbors()){
                if(n.isGoal()){
                    // SearchNode Gnode = new SearchNode(null, -1, false);
                    return new SolutionObj(null, -1,false);
                }
                else if(tnode.previous == null || !tnode.previous.board.equals(n)){
                    tpq.insert(new SearchNode(n, tnode.moves + 1, tnode));
                }
            }
             

             // StdOut.println("pq = "+pq.size()+"  tpq = "+tpq.size());
             if(pq.size() == 0 || tpq.size() == 0){
                 break ;
             }
        }

        if(node.board.isGoal()){
            return new SolutionObj(node, node.moves, true);
        }
        else{
            return new SolutionObj(null, -1, false);
        }
        
    }

    private Stack <Board> buildSolution(SearchNode finl){
        Stack <Board> S = new Stack<>();
        while(finl.previous != null){
            S.add(finl.board);
            finl = finl.previous;
        }
        S.add(finl.board);

        return S ;

        
    }



    // is the initial board solvable? (see below)
    public boolean isSolvable(){
        return this.isSolvable;

    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        return this.moves;

    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        
        if(moves != -1){
            return new solution();
        }
        else return null ;

    }
    private class solution implements Iterable<Board> {

        public Iterator<Board> iterator() { return new SolutionBoardsIterator(); }

        private class SolutionBoardsIterator implements Iterator<Board> {

            public boolean hasNext() {
                if(solution.empty()){
                    return false ;
                }
                return true ;
             }

            public Board next() { 
                return solution.pop();

             }

            public void remove() { 
                throw new UnsupportedOperationException(); 
            }
        }
    }

    // test client (see below) 
    public static void main(String[] args){
        
    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            tiles[i][j] = in.readInt();
    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
    }

}