

/**
 * TLR
 */
public class TLR {

    /*
    Create double links between 2 nodes
    */
    public static void join(Node a,Node b){
        a.right = b ;
        b.left = a ;
    }

    /*
    Joins two circular linked list
    */
    public static Node append(Node a,Node b){
        if(a == null){
            return b ;
        }
        if(b == null){
            return a;
        }

        Node tempA = a.left ;
        Node tempB = b.left ;

        //appending b to end of a
            
        join(tempB,a);
        join(tempA,b);

        return a ;



    }
    /*
        Converting a tree to a LL
        */

    public static Node treeToList(Node root){
        if(root == null){
            return root; 
        }

        Node tempA = treeToList(root.left);
        Node tempB = treeToList(root.right);

        //isolate the root
        root.left = root ;
        root.right = root;

        // Joining 3 LL
        tempA = append(tempA, root);
        tempA = append(tempA, tempB);

        return tempA ;


    }


    public static void treeInsert(Node root,int newdata){

        if(newdata <= root.data){
            if(root.left != null){
                treeInsert(root.left, newdata);
            }
            else{
                root.left = new Node(newdata);
            }

        }
        else{
            if(root.right != null){
                treeInsert(root.right, newdata);
            }
            else{
                root.right = new Node(newdata);
            }
        }
    }

    /*
    Print Tree
    */

    public static void printTree(Node root){
        if(root == null){
            return ;
        }

        printTree(root.left);
        System.out.print(root.data + " ");
        printTree(root.right);
    }

    /*
    Print List
    */

    public static void printList(Node head){
        Node current = head ;

        while(current != null){
            System.out.print(current.data + " ");
            current = current.right ;

            if(current == head){
                break ;
            }
        }
    }




    public static void main(String[] args) {
    
        
        Node root = new Node(4);
        treeInsert(root, 2);
        treeInsert(root, 1);
        treeInsert(root, 3);
        treeInsert(root, 5);
        
        System.out.println("tree:");
        printTree(root);   // 1 2 3 4 5
        System.out.println();
        
        System.out.println("list:");
        Node head = treeToList(root);
        printList(head);   // 1 2 3 4 5   yay!
    }
    
    





    
}

/**
 * Node
 */

