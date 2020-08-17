import jdk.nashorn.api.tree.Tree;

/**
 * TreeListRecursion
 */
public class TreeListRecursion<Key extends Comparable<Key>,Value > {

    
    public TreeListRecursion(){

    }
    /**
     * Node
     */
    
    private class Node {
        private Key key ;
        private Node left,right ;
        private Value val ;

        public  Node(Key key,Value val) {
            this.key = key ;
            this.val = val ; 
        }
        
       
        
    }
    private Node root ;


    /**
     * Treelist
     */
    
        /*
        Create double links between 2 nodes
        */
        public void join(Node a,Node b){
            a.right = b ;
            b.left = a ;
        }

        /*
        Joins two circular linked list
        */
        public Node append(Node a,Node b){
            if(a == null){
                return b ;
            }
            if(b == null){
                return a;
            }

            Node tempA = a.left ;
            Node tempB = b.left;

            //appending b to end of a
            
            join(tempB,a);
            join(tempA,b);

            return a ;



        }
        /*
        Converting a tree to a LL
        */

        public Node treeTolist(){
            return treetolist(root);
        }

        private Node treetolist(Node root){
            if(root == null){
                return root; 
            }

            Node tempA = treetolist(root.left);
            Node tempB = treetolist(root.right);

            //isolate the root
            root.left = root ;
            root.right = root;

            // Joining 3 LL
            tempA = append(tempA, root);
            tempA = append(tempA, tempB);

            return tempA ;


        }

        /*
        Inserting key
        */

        public void Treeinsert(Key key, Value val){
            root = insert(root,key,val);
        }
        private Node insert(Node root,Key key,Value val){
            if(root == null){
                return new Node(key, val);
            }
            int cmp = key.compareTo(root.key);
            if(cmp > 0){
                root.right = insert(root.right, key, val);
            }
            else if(cmp < 0){
                root.left = insert(root.left, key, val);
            }
            else{
                root.val = val ;
            }
            return root ;
        }


        /*
        Print tree (Inorder)
        */

        public void PrintTree(){
            Inorder(root);
        }
        private void Inorder(Node x){
            if(x == null){
                return ;
            }
            Inorder(x.left);
            System.out.print(x.key + " ");
            Inorder(x.right);
        }


        /*
        Print list
        */

        public void PrintList(Node head){
            Node x = head ;

            while(x != null){
                System.out.print(x.key + " ");
                x = x.right ;
                if(x == head){
                    break ;
                }
            }
        }




    
        
    




    public static void main(String[] args) {
    
        TreeListRecursion<String,Integer> bst = new TreeListRecursion<String,Integer>();
        
        bst.Treeinsert("A",9);
        bst.Treeinsert("P",6);
        bst.Treeinsert("S",0);
        bst.Treeinsert("C",8);
        bst.Treeinsert("W",79);
        bst.Treeinsert("A",2);
        bst.Treeinsert("X",5);

        bst.PrintTree();
        System.out.println();
        //Node head = treeTolist();
        bst.PrintList(bst.treeTolist());
        
        
    }
}