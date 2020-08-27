import java.util.NoSuchElementException;

/**
 * SplayTree
 */
public class SplayTree <Key extends Comparable<Key>,Value> {
    
    private Node root;

    /**
     * Node
     */
    public class Node {
    
        private Key key;
        private Value val;
        private Node left,right;

        public Node(Key key,Value val) {
            this.key = key;
            this.val = val;

        }
    }

    /**************************************************************************** */
    /*   
     
    
    
    
    SPLAY FUNCTION




    */

    private Node splay(Node h, Key key) {
        if(h == null){
            return null;
        }

        int cmp1 = key.compareTo(h.key);

        if(cmp1 < 0){
            // if node not in tree
            if(h.left == null){
                return h;
            }

            int cmp2 = key.compareTo(h.left.key);

            if(cmp2 < 0){
                // zig zig condition 
                h.left.left = splay(h.left.left, key);
                h = rotateRight(h); // zig-zig
            }
            else if(cmp2 > 0){
                // zig zag condition
                h.left.right = splay(h.left.right, key);
                if(h.left.right != null){
                h.left = rotateLeft(h.left); // zig zag
                }

            }

            if(h.left == null){
                return h;
            }
            else{
                return rotateRight(h);
                // brings key to root
            }
        }
        else if(cmp1 > 0){
            // if no key in right
            if(h.right == null){
                return h;
            }

            int cmp2 = key.compareTo(h.right.key);

            if(cmp2 > 0){
                // zig zig
                h.right.right = splay(h.right.right, key);
                h = rotateLeft(h);
            }
            else if(cmp2 < 0){
                // zig zag
                h.right.left = splay(h.right.left, key);
                if(h.right.left != null){
                h.right = rotateRight(h.right);
                }
            }

            if(h.right == null){
                return h;
            }
            else{
                return rotateLeft(h);
            }
        }

        else{
            return h;
        }
    }

    /*
    *************************************************************************
    */

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        root = splay(root,key);
        int cmp = key.compareTo(root.key);
        if(cmp == 0) return root.val;
        else return null;
    }

    public void put(Key key, Value val) {
        if(root == null){
            root = new Node(key, val);
        }

        root = splay(root, key);
        // 
        int cmp = key.compareTo(root.key);

        if(cmp < 0){
            Node newroot = new Node(key, val);
            newroot.left = root.left;
            newroot.right = root;
            root.left = null;
            root = newroot;
        }
        else if(cmp > 0){
            Node newroot = new Node(key, val);
            newroot.left = root;
            newroot.right = root.right;
            root.right = null;
            root = newroot;
        }
        else{
            root.val = val;
        }

    }

    public void delete(Key key) {
        if(root == null){
            return ;
        }

        root = splay(root, key);
        int cmp = key.compareTo(root.key);

        if(cmp == 0){
            if(root.left == null){
                root = root.right;
            }
            else{
                Node x = root.right;
                root = root.left;
                root = splay(root, key);
                root.right = x;

            }
        }
    }

    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }
    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }
    
}