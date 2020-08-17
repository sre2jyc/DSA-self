import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

//package SampleTry;

public class BST <Key extends Comparable<Key>,Value> {
    private Node root ;

    private class Node{
        private Key key ;
        private Value val ;
        private Node left , right ;
        private int size ;

        public Node(Key key , Value val,int size){
            this.key = key ;
            this.val = val ;
            this.size = size ;
        }



    }

    public BST(){

    }

    public boolean isEmpty(){
        return size() == 0 ;
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x == null) return 0 ;
        return x.size ;
    }

    public boolean contains(Key key){
        if(key == null){
            throw new IllegalArgumentException() ;
        }
        return get(key) != null ;
    }

    /*
    GETTING A VALUE
    */

    public Value get(Key key){
        return get(root,key);
    }

    private Value get(Node x,Key key){
        if(key == null) throw new IllegalArgumentException() ;
        if(x == null) return null ;

        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            return get(x.left,key);

        }
        else if(cmp > 0){
            return get(x.right,key);
        }
        else{
            return x.val ;
        }
    }

    /*
    PUTTING A VALUE
    */

    public void put(Key key,Value val){
        if(key == null) throw new IllegalArgumentException();
        if(val == null){
            delete(key) ;
            return ;
        }

        root = put(root,key,val);
        assert check();
    }

    private Node put(Node x,Key key,Value val){
        if(x == null){
            return new Node(key, val, 1);

        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            x.left = put(x.left, key, val);
        }
        else if(cmp > 0){
            x.right = put(x.right,key,val);
        }
        else{
            x.val = val ;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x ;
    }

    /*
    DELETION
    */

    public void deleteMin(){
        if(isEmpty()) throw new NoSuchElementException();

        root = deleteMin(root);
        assert check();
    }
    private Node deleteMin(Node x){
        if(x.left == null){
            return x.right ;
        }
        x.left = deleteMin(x.left);
        x.size = 1  + size(x.left) + size(x.right) ;
        return x ;
    }

    public void deleteMax(){
        if(isEmpty()) throw new NoSuchElementException();

        root = deleteMax(root);
        assert check();
    }
    private Node deleteMax(Node x){
        if(x.right == null){
            return x.left ; 
        }

        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);

        return x ;
    }

    public void delete(Key key){
        if(key == null) throw new IllegalArgumentException();

        root = delete(root,key);
        assert check();
    }

    private Node delete(Node x,Key key){
        if(x == null) return null ;

        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            x.left = delete(x.left, key);
        }
        else if(cmp > 0){
            x.right = delete(x.right, key);
        }
        else{
            //check left right 
            if(x.right == null){
                return x.left ;
            }
            if(x.left == null){
                return x.right ;
            }
            // if neither right is null or left is null
            Node t = x ;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left ;

        }

        x.size = 1 + size(x.left) + size(x.right);
        return x ;

    }

    /*
    Returning max and min elements in tree
    */

    public Key min(){
        if(isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }
    private Node min(Node x){
        if(x.left == null){
            return x ;
        }
        else return min(x.left);
    }


    public Key max(){
        if(isEmpty())throw new NoSuchElementException();

        return max(root).key;

    }
    private Node max(Node x){
        if(x.right == null){
            return x ;
        }
        else{
            return max(x.right);
        }
    }


    public Key floor(Key key){
        if(key == null) throw new IllegalArgumentException();
        if(isEmpty()) throw new NoSuchElementException();
        Node x = floor(root,key);
        if(x == null){
            throw new NoSuchElementException();
        }
        else{
            return x.key ;
        }
    }

    private Node floor(Node x,Key key){
        if(x == null) return null ;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x ;
        if(cmp < 0) return floor(x.left,key);

        Node t = floor(x.right,key);
        if(t != null){
            return t ;
        }
        else{
            return x ;
        }
    }


    public Key ceil(Key key){
        if(key == null){
            throw new IllegalArgumentException();

        }
        if(isEmpty()) throw new NoSuchElementException();

        Node x = ceil(root,key);
        if(x == null){
            throw new NoSuchElementException();
        }
        else{
            return x.key ;
        }
    }


    private Node ceil(Node x,Key key){
        if(x == null) return null ;
        int cmp = key.compareTo(x.key);

        if(cmp == 0){
            return x;
        }
        if(cmp > 0){
            return ceil(x.right, key);
        }
        Node t = ceil(x.left, key);
        if(t != null){
            return t ;
        }
        else{
            return x ;
        }
    }


    /*
    KEY RANKINGS
    */



    public Key select(int rank){
        if(rank < 0 || rank >= size()){
            throw new IllegalArgumentException();
        }
        return select(rank,root);
    }

    private Key select(int rank,Node x){
        if(x == null){
            return null ;
        }
        int LeftSize = size(x.left);
        if(LeftSize == rank){
            return x.key ;
        }
        else if(LeftSize > rank){
            return select(rank,x.left);
        }
        else{
            return select(rank - LeftSize - 1,x.right);

        }
    }


    public int rank(Key key){
        if(key == null)throw new IllegalArgumentException();
        return rank(key,root);
    }

    private int rank(Key key,Node x){
        if(x == null){
            return 0 ;
        }
        int cmp = key.compareTo(x.key);
        if(cmp == 0){
            return size(x.left);
        }
        else if(cmp < 0){
            return rank(key,x.left);
        }
        else{
            return 1 + size(x.left) + rank(key, x.right);
        }
    }

    /*
    Iteration in a range
    */

    public Iterable<Key> keys(){
        if(isEmpty()) return new Queue<Key>();
        return keys(min(),max());
    }

    public Iterable<Key> keys(Key lo,Key hi){
        if(lo == null) throw new IllegalArgumentException();
        if(hi == null) throw new IllegalArgumentException();

        Queue <Key> queue = new Queue<>();
        keys(root,queue,lo,hi);
        return queue ;
    }
    
    // basically we do an inorder traversal
    private void keys(Node x,Queue<Key> queue,Key lo,Key hi){
        if(x == null)return ;

        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        if(cmplo < 0){
            // traverse more left
            keys(x.left, queue, lo, hi);
        }
        if(cmplo <= 0 && cmphi >= 0){
            queue.enqueue(x.key);
        }
        if(cmphi >= 0){
            // traverse right
            keys(x.right, queue, lo, hi);
        }
    }


    /*
    NUMBER OF Keys IN A GIVEN RANGE
    */

    public int size(Key lo,Key hi){
        if(lo == null) throw new IllegalArgumentException();
        if(hi == null) throw new IllegalArgumentException();

        if(lo.compareTo(hi) > 0) return 0 ;
        if(contains(hi)){
            return rank(hi) - rank(lo) + 1 ;
        }
        else{
            return rank(hi) - rank(lo) ;
        }
    }

    /*
    HEIGHT OF TREE
    */

    public int height(){
        return height(root);
    }
    private int height(Node x){
        if(x == null) return 0 ;

        return 1 + Math.max(height(x.left), height(x.right));
    }

    /*
    LEVEL ORDER TRAVERSAL
    */

    public Iterable<Key> levelOrder(){
        Queue <Key> keys = new Queue<>();
        Queue <Node> queue = new Queue<>();

        queue.enqueue(root);
        while(! queue.isEmpty()){
            Node x = queue.dequeue();
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }

        return keys ;
    }



    /*
    CHECKING INTEGRITY OF BST
    */

    private boolean check(){
        if(! isBST()){
            StdOut.println("Not in symmmetric order");
        }
        if(! isSizeConsistent()){
            StdOut.println("Subtree counts not consistent");
        }
        if(! isRankConsistent()){
            StdOut.println("Ranks not consistent");
        }

        return isBST() && isSizeConsistent() && isRankConsistent();
    }


    private boolean isBST(){
        return isBST(root,null,null);
    }
    private boolean isBST(Node x,Key max,Key min){
        if(x == null) return true ;

        if(min != null && min.compareTo(x.key) > 0){
            return false ;
        }
        if(max != null && max.compareTo(x.key) < 0){
            return false ;
        }

        return isBST(x.left,min,x.key) && isBST(x.right,x.key,max) ;
    }

    private boolean isSizeConsistent(){
        return isSizeConsistent(root);
    }
    private boolean isSizeConsistent(Node x){
        if(x == null) return true ;

        if(x.size != size(x.left) + size(x.right) + 1){
            return false ;
        }

        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    private boolean isRankConsistent(){
        
        for(int i=0;i<size();i++){
            if(rank(select(i)) != i){
                return false ;
            }

        }

        for(Key key : keys()){
            if(key.compareTo(select(rank(key))) != 0){
                return false ;
            }
        }

        return true ;
    }


    public static void main(String[] args) { 
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.levelOrder())
            StdOut.println(s + " " + st.get(s));

        StdOut.println();

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }





















































    
}