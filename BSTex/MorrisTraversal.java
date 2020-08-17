

/**
 * MorrisTraversal
 */
public class MorrisTraversal {

    public void Inorder(Node root) {

        Node current = root ;

        while(current != null){
            if(current.left == null){
                // print current , move right
                System.out.print(current.data + " ");
                current = current.right;
            }
            else{
                // find predecessor
                Node predecessor = current.left ;

                // to find predecessor , go right until right node is null or right node points to current
                while(predecessor.right != null && predecessor.right != current){
                    predecessor = predecessor.right ;
                }

                // if predecessor right is null , establish link between predecessor and current , and move current to left

                if(predecessor.right == null){
                    predecessor.right = current ;
                    current = current.left ;
                }

                // if predecessor right points to current , the path is already visited , print current and move right

                else{
                    predecessor.right = null ;
                    System.out.print(current.data + " ");
                    current = current.right;

                }


            }
        }
        
    }
}