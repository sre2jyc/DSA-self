import java.security.Key;
import java.util.Queue;

//import jdk.internal.org.objectweb.asm.tree.analysis.Value;

/**
 * LinearProbingHashST
 */
public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;          // number of key-value pairs in ST
    private int m;          // size of linear probing table
    private Key[] keys;     // keys
    private Value[] vals;   // values

    public LinearProbingHashST(){
        this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int capacity){
        m = capacity;
        n = 0;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return size() == 0;
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public boolean contains(Key key){
        if(key == null){
            throw new IllegalArgumentException();
        }

        return get(key) != null;
    }

    public Value get(Key key){
        int i = hash(key);
        while(keys[i] != key){
            i = (i + 1) % m;
        }
        return vals[i];
    }



    private void resize(int capacity){
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST(capacity);
        for(int i = 0; i < m; i ++){
            if(keys[i] != null){
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }

    public void put(Key key, Value val){
        if(key == null){
            throw new IllegalArgumentException();
        }

        if(val == null){
            delete(key);
            return;
        }

        // resize if table is 50% full
        if(n >= m/2){
            resize(m*2);
        }

        //int i = hash(key);
        /*

        // NORMAL 
        int i;
        for(i = hash(key); keys[i] != null; i = (i+1) % m){
            if(keys[i].equals(key)){
                vals[i] = val;
                return;
            }
            
        }
        keys[i] = key;
        vals[i] = val;
        n ++ ;
        
        
        */

        
        // LAST Come First Serve
        /*
        int i = hash(key);
        if(keys[i] != null){
            Key tk = keys[i];
            Value tv = vals[i];
            keys[i] = key;
            vals[i] = val;
            i = (i + 1)% m;
            while(keys[i] != null){
                swap(tk,keys[i]);
                swap(tv,vals[i]);
                i = (i + 1) % m;
            }
            keys[i] = tk;
            vals[i] = tv;
            n ++;
        }
        else{
            keys[i] = key;
            vals[i] = val;
            n ++ ;
        }
        */
        

        // Robin Hood
        int i = hash(key);
        


        if(keys[i] != null){

            Key ck = key;
            Value cv = val;
            while(keys[i] != null){
                int ckh = hash(ck);
                int fkh = hash(keys[i]);
                if(Math.abs(ckh - i) > Math.abs(fkh - i)){
                    swap(ck,keys[i]);
                    swap(cv,vals[i]);
                }
                
                i = (i + 1) % m;
            }

            keys[i] = ck;
            vals[i] = cv;
            n ++;
        }
        else{
            keys[i] = key;
            vals[i] = val;
            n ++;
        }
    

        

    } 

    private void swap(Object a, Object b){
        // to implement Last Come First Serve && Robin Hood
        Object t = a;
        a = b;
        b = t;
    }



    public void delete(Key key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        if(! contains(key)){
            return;
        }

        int i = hash(key);
        while(keys[i] != key){
            i = (i + 1) % m;
        }

        keys[i] = null;
        vals[i] = null;

        // rehash and insert the cluster of keys after deleted key
        i = (i + 1) % m;
        while(keys[i] != null){
            Key keyToRehash = keys[i];
            Value valueToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n -- ;
            put(keyToRehash, valueToRehash);
            i = (i + 1) % m;
        }

        n -- ;

        // resize array if it is less than 12.5% filled
        if(n > 0 && n <= m/8){
            resize(m/2);
        }

        //assert check();
    }

    public Iterable<Key> keys(){
        Queue <Key> q = new Queue<>();
        for(int i = 0; i < m; i ++){
            if(keys[i] != null){
                q.add(keys[i]);
            }
        }
        return q;
    }

   public static void main(String[] args) {
       
   }



    
}