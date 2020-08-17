import java.util.NoSuchElementException;

/**
 * BSTHeightNode
 */
public class BSTHeightNode<Key extends Comparable<Key>,Value> {

    private Node root ;

    private class Node{
        private Key key ;
        private Value val ;
        private Node left , right ;
        private int size ;
        private int height ;

        public Node(Key key , Value val,int size,int height){
            this.key = key ;
            this.val = val ;
            this.size = size ;
            this.height = height ;
        }
    }


    public BSTHeightNode(){

    }

    public void put(Key key,Value val){
        if(key == null){
            throw new IllegalArgumentException();
        }
        if(val == null){
            deleteNode(key);
            return;
        }
        else{
            root = put(root,key,val);

        }
    }

    private Node put(Node root,Key key,Value val){

        if(root == null){
            return new Node(key, val, 1, 0);
        }

        int cmp = key.compareTo(root.key) ;
        if(cmp == 0){
            root.val = val ;
        }
        else if(cmp > 0){
            root.right = put(root.right, key, val);
            //root.height = Math.max(root.right.height, root.left.height) + 1 ;
        }
        else{
            root.left = put(root.left,key,val);
        }

        root.size = 1 + getSize(root.left) + getSize(root.right) ;
        root.height = Math.max(getHeight(root.left),getHeight(root.right)) + 1 ;
        return root ;

    }

    public int getSize(Node root){
        if(root == null){
            return 0 ;
        }
        else{
            return root.size ;
        }
    }

    public int getHeight(Node root){
        if(root == null){
            return -1 ;
        }
        else{
            return root.height ;
        }
    }

    public void deleteNode(Key key){
        if(key == null){
            throw new IllegalArgumentException() ;
        }

        root = deleteNode(key,root);

    }
    private Node deleteNode(Key key,Node root){
        if(root == null){
            throw new NoSuchElementException();
        }

        int cmp = key.compareTo(root.key);

        if(cmp < 0){
            root.left = deleteNode(key, root.left);
        }
        else if(cmp > 0){
            root.right = deleteNode(key, root.right);

        }
        else{
            if(root.left == null){
                return root.right ;
            }
            else if(root.right == null){
                return root.left ;
            }
            else{
                Node temp = root ;
                root = min(temp.right);
                temp.right = delMin(temp.right);
                root.left = temp.left ;
            }
        }

        root.size = 1 + getSize(root.left) + getSize(root.right) ;
        root.height = Math.max(getHeight(root.left),getHeight(root.right)) + 1 ;
        return root ;
    }

    public Node min(Node root){
        if(root == null){
            throw new NoSuchElementException();
        }
        if(root.left == null){
            return root ;
        }

        root.left = min(root.left);
        return root ;
    }

    public Node delMin(Node root){
        if(root.left == null){
            return root.right ;
        }
        else{
            root.left = delMin(root.left);
        }

        root.size = 1 + getSize(root.left) + getSize(root.right) ;
        root.height = Math.max(getHeight(root.left),getHeight(root.right)) + 1 ;
        return root ;
    }

    public void visit(){
        Inorder(root);
    }
    private void Inorder(Node root){
        if(root == null){
            return ;
        }

        Inorder(root.left);
        System.out.println(root.key + " " + root.val + " "+getHeight(root));
        Inorder(root.right);
    }


    public static void main(String[] args) {
        BSTHeightNode <String,Integer> t = new BSTHeightNode<>();

        t.put("A", 2);
        t.put("B", 7);
         t.put("C", 8);
        t.put("X", 9);
         t.put("B", 5);
         t.put("R", 10);
         t.put("F",2);
         t.put("T", 21);
         t.put("Z", 5);
         t.put("K", 4);
        t.put("R", null);

        t.visit();
    }



}