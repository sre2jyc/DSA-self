import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item [] queue ;
    private int size = 0 , last = -1 , N = 1 ;

    // construct an empty randomized queue
    public RandomizedQueue(){
        queue = (Item[]) new Object[N] ;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        if(size == 0){
            return true ;
        }
        else{

            return false ;
        }
    }

    // return the number of items on the randomized queue
    public int size(){
        return size ;
    }

    // add the item
    public void enqueue(Item item){
        if(item == null){
            throw new IllegalArgumentException();
        }

        queue[++last] = item ;
        size ++ ;

        if(last + 1 == N){
            if(size < N/2){
                change(N);
            }
            else{
                change(N*2) ;
            }
        }
    }


    private void change(int capacity){
        int count = 0 ;
        Item[] copy = (Item[]) new Object[capacity];

        for(int i =0;i<N;i++){
            if(queue[i] != null){
                copy[count] = queue[i];
                count ++ ;
            }
        }

        queue = copy ;
        N = capacity ;
        last = count - 1 ;
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        int rand = StdRandom.uniform(0, size);
        while(queue[rand] == null){
            rand = StdRandom.uniform(0, size);
        }
        
        Item item = queue[rand];

        queue[rand] = null ;
        size -- ;

        if(size < N/4){
            change(N/2);
        }

        return item ;

    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        int rand = StdRandom.uniform(0, size);
        while(queue[rand] == null){
            rand = StdRandom.uniform(0, size);
        }


        return queue[rand];
        
        
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] copy = (Item[]) new Object[N];
        private int count = 0 ;
        private int index = 0 ;

        public RandomizedQueueIterator(){
            for(int i=0;i<last + 1;i++){
            if(queue[i] != null){
                copy[count] = queue[i];
                count ++ ;
            }
        }

        Item[] randarr = (Item[]) new Object[count];
        for(int i =0;i<count;i++){
            randarr[i] = copy[i];
        }

        copy = randarr ;
        StdRandom.shuffle(copy);
        }
        

        

        public boolean hasNext(){
            if(index == count){
                return false ;
            }
            else{
                return true ;
            }
        }

        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            Item nextitem = copy[index];
            index ++ ;

            return nextitem ;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }



        
        
        
    }

    // unit testing (required)
    public static void main(String[] args){
         RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
         StdOut.println(rq.isEmpty());
        rq.enqueue(10);
        rq.enqueue(20);
        rq.enqueue(55);
        rq.enqueue(58);
        rq.enqueue(100);
        rq.enqueue(20);
        rq.enqueue(1);

        StdOut.println(rq.size());
        StdOut.println(rq.sample());
        StdOut.println(rq.sample());
        StdOut.println(rq.size());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
    }

}