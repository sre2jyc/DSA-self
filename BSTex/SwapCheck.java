import java.util.ArrayList;
import java.util.List;

/**
 * SwapCheck
 */
public class SwapCheck<Key extends Comparable<Key>,Value> {

    /**
     * Node
     */
    private class Node {
       
        private Key key ;
        private Value val ;
        private Node left,right ;

        public Node(Key key,Value val){
            this.key = key ;
            this.val = val ;
        }

        
    }

    private Node root ;


    private List<Key> a = new ArrayList<Key> () ;

    public void visit(){
        Inorder(root);
    }

    private void Inorder(Node x){
        if(x == null){
            return ;
        }

        Inorder(x.left);
        a.add(x.key);
        Inorder(x.right);
    }

    public void CompareOrders(Key [] x){
        List <Integer> indexes = new ArrayList<>();
        for(int i=0;i<x.length-1 ;i++){
           int cmp = x[i].compareTo(x[i+1]);
           if(cmp > 0){
               indexes.add(i);

           }
        }

        System.out.println("The keys at indexes "+indexes.get(0)+" and "+indexes.get(1)+" are swapped");
    }





    




    public static void main(String[] args) {
        SwapCheck<String,Integer> bst = new SwapCheck<>();
        String [] a = {"A","B","H","D","E","F","G","C"};
        bst.CompareOrders(a);
        
        
    }
}