import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class Deque<Item> implements Iterable<Item> {
    private class Node{
       Item item ;
       Node before ;
       Node after ;
    }
    private Node first , last ;
    private int size ;

    // construct an empty deque
    public Deque(){
        first = null ;
        last = null ;
        size = 0 ;
    }

    // is the deque empty?
    public boolean isEmpty(){
        if(size == 0){
            return true ;

        }
        else{
            return false ;
        }
    }

    // return the number of items on the deque
    public int size(){
        return size ;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item == null){
            throw new IllegalArgumentException() ;
        }
        
            Node oldfirst = first ;
            first = new Node() ;
            first.item = item ;
            first.after = oldfirst ;
            first.before = null ;
            if(oldfirst == null){
                last = first ;
            }
            else{
                oldfirst.before= first;
            }
        
        size ++ ;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item == null){
            throw new IllegalArgumentException();
        }
        
        
            Node oldlast = last ;
            last = new Node();
            last.item = item;
            last.before = oldlast;
            last.after = null ;
            if(oldlast == null){
                first = last;
            }
            else{
                oldlast.after = last ;
            }
        
        size ++ ;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        Node oldfirst = first ;
        if(size == 1){
            
            first = null ;
            last = null ;
            
            
        }
        else{
            
            first = first.after ;

        }
        size -- ;
        return oldfirst.item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        Node oldlast = last ;
        if(size == 1){
             first = null ;
             last = null ;
        }
        else{
            last = last.before ;
        }
        size -- ;
        return oldlast.item ;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        private Node currNode = first ;
        public boolean hasNext(){
            if(currNode.after != null && currNode != null){
                return true ;
            }
            else{
                return false ;
            }
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if(currNode == null){
                throw new NoSuchElementException();
            }
            Item currentitem = currNode.item;
            currNode = currNode.after ;

            return currentitem ;
        }


    }

    // unit testing (required)
    public static void main(String[] args){
        Deque <Integer> dq = new Deque<Integer>() ;
        StdOut.println(dq.isEmpty());
        dq.addFirst(10);
        dq.addFirst(20);
        dq.addLast(55);
        dq.addLast(58);
        dq.addLast(100);
        dq.addFirst(20);
        dq.addLast(1);

        StdOut.println(dq.size());
        StdOut.println(dq.removeFirst());
        StdOut.println(dq.removeLast());
        StdOut.println(dq.removeFirst());

        dq.iterator().next();
        dq.iterator().hasNext() ; 

    }

}