import java.util.Arrays;

import jdk.internal.perf.Perf;
import sun.awt.www.content.image.png;

/**
 * PerfectBalanceBST
 */
public class PerfectBalanceBST <Key extends Comparable<Key>,Value> {

    private Node root ;

    private class Node{
        private Key key ;
        private Value val ;
        private Node left,right ;

        public Node(Key key,Value val){
            this.key = key ;
            this.val = val ;
        }

    }

    public PerfectBalanceBST(){

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

    public void perfect(PerfectBalanceBST bst,String [] a){
         Arrays.sort(a);
         perfect(bst,a,0,a.length-1);


    }

    private void perfect(PerfectBalanceBST bst,String[] a,int low,int high) {
       
        if(high < low){
            return ;
        }

        int mid = low + (high - low)/2 ;
        bst.put(a[mid], mid);

        System.out.println(a[mid]);
        perfect(bst,a,mid+1,high);
        perfect(bst, a, low, mid-1);
        
    }


    public static void main(String[] args) {
        String[] words = StdIn.readAllStrings();
        PerfectBalanceBST<String, Integer> bst = new PerfectBalanceBST<>();
        bst.perfect(bst, words);
    }


}