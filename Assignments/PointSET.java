import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {


    private Set<Point2D> ps  ;
    private int psSize ;
// construct an empty set of points 
    public         PointSET(){
        // ps = new Set<Point2D>();
        ps = new TreeSet<>();
        psSize = 0 ;
    }      
    
     // is the set empty? 
    public           boolean isEmpty(){
        if(psSize == 0){
            return true ;
        }
        else{
            return false ;
        }



    }  
    
    // number of points in the set 
    public               int size(){
        return psSize ;

    }   
    
    // add the point to the set (if it is not already in the set)
    public              void insert(Point2D p)  {
        if(p == null){
            throw new IllegalArgumentException();
        }
        
        if(! ps.contains(p)){
            ps.add(p);
            psSize ++ ;
        }

    }  
    
    // does the set contain point p? 
    public           boolean contains(Point2D p) {
        if(p == null){
            throw new IllegalArgumentException();
        }

        return ps.contains(p);

    }     
    
    // draw all points to standard draw
    public              void draw()            {
        
        for(Point2D i : ps){
            i.draw();
        }

    }      
    
    // all points that are inside the rectangle (or on the boundary) 
    public Iterable<Point2D> range(RectHV rect) {
        if(rect == null){
            throw new IllegalArgumentException() ;
        }
        ArrayList <Point2D> pr = new ArrayList <Point2D>();

        for(Point2D i : ps){
            if(rect.contains(i)){
                pr.add(i);
            }
        }

        return pr;


    }            

    // a nearest neighbor in the set to point p; null if the set is empty 
    public           Point2D nearest(Point2D p) {


        //int dmin = Integer.MAX_VALUE;
        double dmin = Double.MAX_VALUE;
        Point2D pNearest = null ;
        if(p == null){
            throw new IllegalArgumentException();
        }
        if(ps == null){
            return pNearest;
        }


        for(Point2D i : ps){
            double d = i.distanceSquaredTo(p);
            if(d < dmin){
                dmin = d;
                pNearest = i ;
            }
            
        }

        return pNearest;

    }            
 // unit testing of the methods (optional) 
    public static void main(String[] args)      {
        //System.out.print("Hello");

    }            
 }