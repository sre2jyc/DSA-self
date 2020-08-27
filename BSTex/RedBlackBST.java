import java.security.Key;
import java.util.NoSuchElementException;

/**
 * RedBlackBST
 */
public class RedBlackBST<CKey extends Comoarable<Key>,Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    /**
     * Node
     */
    private class Node {
        private Key key;
        private Value val;
        private Node left,right;
        private boolean color;
        private int size;

        public Node(Key key,Value val,boolean color,int size) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.color = color;
        }
    }

    public  RedBlackBST() {
        
    }


    /*
    Node helper methods
    */
    private boolean isRed(Node x){
        if(x == null){
            return false;
        }

        if(x.color == RED){
            return true;
        }
        else{
            return false;
        }
    }

    private int size(Node x){
        if(x == null){
            return 0;
        }
        return x.size;
    }

    public int size(){
        return size(root);
    }

    public boolean isEmpty() {
        if(root == null){
            return true;
        }
        else{
            return false;
        }
    }

    /*
    LLRB Tree search ,  similar to BST
    */

    public Value get(Key key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        return get(root,key);
    }
    private Value get(Node x, Key key) {
        if(x == null){
            return null;
        }

        int cmp = key.compareTo(x.key);
        if(cmp == 0){
            return x.val;
        }
        else if(cmp < 0){
            return get(x.left,key);
        }
        else{
            return get(x.right,key);
        }
    }

    public boolean contains(Key key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        
        if(get(key) != null){
            return true;
        }
        else{
            return false;
        }
    }

    /*
    Left Leaning Red Black Tree Helper Functions
    1. rotateLeft
    2. rotateRight
    3. moveRedLeft
    4. MoveRedright
    5. flipColors
    6. balanceTree

    */

    // Rotating a Node left
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;

    }

    // Rotating a Node right
    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }
    // Flipping colors of a Node and its 2 childs
    private Void flipColors(Node h){
        changeColor(h);
        changeColor(h.left);
        changeColor(h.right);
    }
    private void changeColor(Node x) {
        x.color = !x.color;
        
    }

    /*
    By this move , if ( h.color = RED && h.left.color == h.left.left.color == BLACK)
    then we make h.left red or children of h.left red
    */

    private Node moveRedLeft(Node h){

        // change h to black
        h.color = BLACK;
        // change left node red
        h.left.color = RED;
        // check whether h.right.left is red, 
        /* if(h.right.left == red){
            we rotate h.right so that both child of h is red ,
            then we rotate h left so that h = h.right && h.leftv= h 
            hence passing a red node leftwards down

        }
        else{
            simply make h.right red
            ie. ultimately flipping the color of h 

        }
        */
        if(isRed(h.right.left)){
            h.right = rotateRight(h.right);
            h = rotateLeft(h.left);
        }
        else{
            h.right.color = RED;
        }

        return h;
    
    }
    
    /*
    By this move , if(h.color == RED && h.left.color == h.right.color == BLACK)
    we make h.right or children of h.right red
    */
    private Node moveRedRight(Node h){
        // change h to Black
        h.color = BLACK;
        // change h.right red
        h.right.color = RED;
        // check whether h.left.left is Red 
        /*
        if(h.left.left == RED){
            rightRotate h 
            thus passing h.right downwards
          
        }
        else{
            simply change color of h.left to red
        }
        */
        if(isRed(h.left.left)){
            h = rotateRight(h);
            h.color = RED;
            h.left.color = BLACK;

        }
        else{
            h.left.color = RED;
        }

        return h;
    }

    // restoring balance in LLRB tree
    private Node balance(Node h){

        if(isRed(h.right)){
            h = rotateLeft(h);
        }
        if(isRed(h.left) && isRed(h.left.left)){
            h = rotateRight(h);

        }
        if(isRed(h.left) && isRed(h.right)){
            flipColors(h);
        }

        h.size = 1 + size(h.left) + size(h.right);
        return h;
    }


    /*
    Insertions
    */

    public void put(Key key,Val val) {
        if(key == null){
            throw new IllegalArgumentException();
        }
        if(val == null){
            delete(key);
            return;
        }

        root = put(root,key,val);
        root.color = BLACK;

        
    }
    private Node put(Node x,Key key,Value val) {
        if(x == null){
            return new Node(key,val,RED,1);
        }

        int cmp = key.compareTo(x.key);
        if(cmp > 0){
            x.right = put(x.right,key,val);
        }
        else if(cmp < 0){
            x.left = put(x.left, key, val);
        }
        else{
            x.val = val;
        }

        // balancing links upwards
        if(isRed(x.right) && !isRed(x.left)){
            x = rotateLeft(x);
        }
        if(isRed(x.left) && isRed(x.left.left)){
            x = rotateRight(x);
        }
        if(isRed(x.left) && isRed(x.right)){
            flipColors(x);
        }

        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /*
    Deletions : 
    deleteMin, deletemax, delete
    */

    public void deleteMin() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        // if both children of root are black , set root red
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }

        root = deleteMin(root);
        // reset root to Black
        if(!isEmpty()){
            root.color = BLACK;

        }
        
    }

    private Node deleteMin(Node h) {
        if(h.left == null){
            return null;
        }

        /*
        we basically want to pass a red node downwards so that we can delte a red node finally
        */
        if(!isRed(h.left) && !isRed(h.left.left)){
            moveRedLeft(h);
        }

        h.left = deleteMin(h.left);
        // after deletion we balance the tree
        return balance(h);
        
    }

    public void deleteMax() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        // if both children of root are black , set root red
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }

        root = deleteMax(root);

        if(!isEmpty()){
            root.color = BLACK;
        }
        
        
    }
    private Node deleteMax(Node h) {
        if(h.right == null){
            return null;
        }

        // check whether right children of current root is red , if not pass red node downwards
        if(!isRed(h.right) && !isRed(h.right.left)){
            h = moveRedRight(h);
        }

        h.right = deleteMax(h.right);
        return balance(h);

    }

    // deleting a specific key
    public void delete(Key key) {
        if(key == null){
            throw new IllegalArgumentException();
        }
        if(!contains(key)){
            throw new NoSuchElementException();
        }

        // if both child black , set root to red
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }

        root = delete(root,key);
        
        if(!isEmpty()){
            root.color = BLACK;
        }
        
    }

    private Node delete(Node h, Key key) {
        
        int cmp = key.compareTo(h.key);

        if(cmp < 0){
            // similar to deleteMin at this step
            if(!isRed(h.left) && !isRed(h.left.left)){
                // pass red node downwards
                moveRedLeft(h);
            }

            h.left = delete(h.left, key);
        }
        else{
            if(isRed(h.left)){
                h = rotateRight(h);
            }
            // if the target node has no inorder successor
            if(cmp == 0 && h.right == null){
                return null;
            }

            if(!isRed(h.right) && !isRed(h.right.left)){
                //  similar to deleteMax at this stage
                h = moveRedRight(h);
            }
            if(cmp == 0){
                Node x = min(h.right); // x is inorder successor
                h.key = x.key;
                h.val = x.val;

                h.right = deleteMax(h.right); // deleting x
            }
            else{
                h.right = delete(h.right,key);
            }


        }

        return balance(h);
    }

    /*
    Maximum and Minimum elements
    */

    public Key max() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return max(root).key;
    }
    private Node max(Node x){

        if(x.right == null){
            return x;
        }
        else{
            return max(x.right);
        }
    }

    public Key min(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        return min(root).key;
    }
    private Node min(Node x){
        if(x.left == null){
            return x;
        }
        else{
            return min(x.left);
        }
    }

    

 


    public static void main(String[] args) {
        
    }



    
}