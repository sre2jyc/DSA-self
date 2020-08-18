import java.util.LinkedList;
import java.util.Queue;



/**
 * LOcheck
 */
public class LOcheck {

    static Node ROOT ;

    static class Node{
        int data ;
        int max , min ;
        Node left,right ;

        public Node(int data,int max,int min){
            this.data = data ;
            this.max = max ;
            this.min = min ;
            this.left = null ;
            this.right = null ;
        }
    }

    static boolean CheckLevelOrder(int a[]){
        int n = a.length ;
        if(n == 0){
            return true ;
        }

        Queue <Node> q = new LinkedList<>();
        int i = 0 ;
        ROOT = new Node(a[i], Integer.MAX_VALUE, Integer.MIN_VALUE);
        q.add(ROOT);
        i ++ ;

        while(i != n && q.size() > 0){
            Node root = q.peek();
            q.remove();

            // check whether next element is left node of root

            if(i < n && a[i] > root.min && a[i] < root.data){
                Node lc = new Node(a[i],root.data,Integer.MIN_VALUE) ;
                root.left = lc ;
                q.add(lc);
                i ++ ;
            }


            // check whether next element is a right node of root

            if(i < n && a[i] > root.data && a[i] < root.max){
                Node rc = new Node(a[i], Integer.MAX_VALUE, root.data);
                root.right = rc ;
                q.add(rc);
                i ++ ;
            }


        }

        if(i == n){
            return true ;
        }
        else{
            return false ;
        }
    }


    static void Inorder(){
         Inorder(ROOT);
    }
    static void Inorder(Node x){
        
        if(x == null){
            return ;
        }

        Inorder(x.left);
        System.out.print(x.data + " ");
        Inorder(x.right);
    }



    public static void main(String[] args) {
        int [] a = {10,3,12,1,7,11,17,15,14};

        //System.out.println(CheckLevelOrder(a));
        if(CheckLevelOrder(a)){
            System.out.println("It is a level order");
            Inorder();
        }
        else{
            System.out.println("NOT Level order");
        }

        //Inorder();
    }
}