
/**
 * ReverseBST
 */
public class ReverseBST<Key extends Comparable<Key>,Value> {

    private class Node{
        private Key key ;
        private Value val ;
        private Node left,right;

        public Node(Key key,Value val){
            this.key = key ;
            this.val = val ;
        }

    }

    private Node root ;

    public ReverseBST(){

    }

    public void put(Key key,Value val){
        if(key == null){
            throw new IllegalArgumentException() ;
        }

        root = put(key,val,root);
    }

    private Node put(Key key,Value val,Node root){
        if(root == null){
            return new Node(key, val);
        }

        int cmp = key.compareTo(root.key);
        if(cmp < 0){
            root.left = put(key, val, root.left);
        }
        else if(cmp > 0){
            root.right = put(key, val, root.right);
        }
        else{
            root.val = val ;
        }

        return root ;
    }


    public void ReverseTree(){
        root = reverseTree(root);
    }



    
        private Node reverseTree(Node root) {
            if(root == null) return null ;
            
            swap(root);
            root.left = reverseTree(root.left);
            root.right = reverseTree(root.right);
            return root ;
            
            
        }
        
        private void swap(Node x){
            Node temp ;
            temp = x.left ;
            x.left = x.right ;
            x.right = temp ;
            
        }

        
         
        public void visit(){

            Inorder(root);
            
        }
        private void Inorder(Node x){
            if(x == null){
                return ;
            }
            Inorder(x.left);
            System.out.println(x.key + " "+x.val);
            
            Inorder(x.right);
        }



        public static void main(String[] args) {
            ReverseBST <String,Integer> t = new ReverseBST<>();
    
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
            
    
            t.visit();

            t.ReverseTree();
            t.visit();
            
        }
    
    

    
}