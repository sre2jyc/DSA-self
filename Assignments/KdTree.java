

import java.util.Comparator;
import java.util.LinkedList ;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private class Node{
        private Point2D data ;
        private Node left , right ;
        private int level ;

        public Node(Point2D data,int level,Node left,Node right){
            this.data = data;
            this.level = level;
            this.left = left ;
            this.right = right ;
        }



        
    }

    private Node root ;
    private int size = 0 ;





    // construct an empty set of points 
    public         KdTree()        {



    }       
    // is the set empty?                 
    public           boolean isEmpty(){
        if(this.size == 0){
            return true ;
        }
        else{
            return false;
        }


    }
     // number of points in the set                       
    public               int size(){
        return this.size ;



    }
    // add the point to the set (if it is not already in the set)                        
    public              void insert(Point2D p) {
       
            
        
        
        if (p == null) throw new IllegalArgumentException();
        if (root == null) {
            root =new Node(p, 0,null, null);
            size++;
        } else if (!contains(p)) {
            insert(p,root, true);
            size++;
        }


    }

    private void insert(Point2D p,Node root,boolean compX){
        
        Comparator<Point2D> comp = compX ? Point2D.X_ORDER : Point2D.Y_ORDER;
        //int cmp = comp.compare(p,root.data);

        if (comp.compare(root.data, p) > 0) {
            if (root.left == null){
                root.left = new Node(p, root.level + 1, null, null);
            } else {
                insert(p,root.left,  !compX);
            }
        } else if (root.right == null){
            root.right = new Node(p, root.level + 1,null, null);
        } else {
            insert(p,root.right,  !compX);
        }

        
        

       
       // return root ;
    }


    // does the set contain point p?              
    public           boolean contains(Point2D p) {

        if(p == null){
            throw new IllegalArgumentException();
        }

        if( isEmpty()){
            return false ;
        }

        return find(p,root,true);




    }

    private boolean find(Point2D p,Node root,boolean compX){
        Comparator<Point2D> comp = compX ? Point2D.X_ORDER : Point2D.Y_ORDER;

        if (root.data.equals(p)) return true;
        if (comp.compare(root.data, p) > 0) {
            if (root.left == null){
                return false;
            } else {
                return find( p, root.left, !compX);
            }
        } else if (root.right == null){
            return false;
        } else {
            return find(p,root.right, !compX);
        }

    }
    // draw all points to standard draw            
    public              void draw()   {

        draw(root,new RectHV(0, 0, 1, 1));
    }

    private void draw(Node root,RectHV r){
        if(root == null){
            return ;
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        //StdDraw.setPenRadius(0.02);
        root.data.draw();

        //StdDraw.setPenRadius(0.001);

        if(root.level % 2 == 0){
            // Vertical , Red

            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(root.data.x(), r.ymin(), root.data.x(), r.ymax());

            draw(root.left, new RectHV(r.xmin(), r.ymin(), root.data.x(), r.ymax()));
            draw(root.right, new RectHV(root.data.x(),r.ymin(), r.xmax(), r.ymax()));
        }
        else{
            // Horizontal , Blue

            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(r.xmin(), root.data.y(), r.xmax(), root.data.y());

            draw(root.left,new RectHV(r.xmin(), r.ymin(), r.xmax(), root.data.y()));
            draw(root.right ,new RectHV(r.xmin(), root.data.y(), r.xmax(), r.ymax()));
        }


    }
    // all points that are inside the rectangle (or on the boundary)                       
    public Iterable<Point2D> range(RectHV rect){

        if(rect == null){
            throw new IllegalArgumentException();
        }

        List <Point2D> pl = new LinkedList<>();

        range(root,rect,pl);

        return pl ;



    }

    private void range(Node root,RectHV r,List <Point2D> pl){
        if(root == null){
            return ;
        }

        if(r.contains(root.data)){
            pl.add(root.data);
        }

        double x = root.data.x();
        double y = root.data.y();

        if(root.level % 2 == 0){
            // go left or right

            if(r.xmin() <= x && r.xmax() >= x ){
                // go both sides
                range(root.left,r,pl);
                range(root.right, r, pl);
            }
            else if(r.xmin() > x){
                // go right
                range(root.right, r, pl);
            }
            else if(r.xmax() < x){
                // go left
                range(root.left,r,pl);
            }

        }
        else{
            // go top or bottom

            if(r.ymin() <= y && r.ymax() >= y){
                // go both sides
                range(root.left, r, pl);
                range(root.right, r, pl);
            }
            else if(r.ymin() > y){
                // go up
                range(root.right, r, pl);
            }
            else if(r.ymax() < y){
                // go down
                range(root.left, r, pl);
            }
        }

    }
    // a nearest neighbor in the set to point p; null if the set is empty              
    public           Point2D nearest(Point2D p)    {

        if(p == null || isEmpty()){
            throw new IllegalArgumentException() ;
        }

        Point2D nearestNeighbour = nearestTo(p,root.data,root);

        return nearestNeighbour ;
        




    }         

    private Point2D nearestTo(Point2D p,Point2D currentNear , Node root){

        if(root == null){
            return currentNear;
        }

        if(currentNear.distanceSquaredTo(p) > root.data.distanceSquaredTo(p)){
            currentNear = root.data ;
        }

        if(root.level % 2 == 0){
            // have to check left and right
            if(p.x() >root.data.x()){
                // query at right

                Point2D rightNearest = nearestTo(p,currentNear, root.right);

                // check whether there can be a possible point in left rectangle which is nearer
                if(rightNearest.distanceSquaredTo(p) > Math.abs(p.x() - root.data.x())){
                    Point2D leftNearest = nearestTo(p, rightNearest, root.left);

                    if(p.distanceSquaredTo(rightNearest) > p.distanceSquaredTo(leftNearest)){
                        return leftNearest ;
                    }
                }
                else{
                    return rightNearest ;

                }
            }
            else{
                // query on left

                Point2D leftNearest = nearestTo(p, currentNear, root.left);

                if(leftNearest.distanceSquaredTo(p) > Math.abs(p.x() - root.data.x())){
                    Point2D rightNearest = nearestTo(p, leftNearest, root.right);

                    if(p.distanceSquaredTo(rightNearest) < p.distanceSquaredTo(leftNearest)){
                        return rightNearest ;
                    }
                }

                return leftNearest ;
            }

        }
        else{
            //have to check top & bottom
            
            if(p.y() > root.data.y()){
                // check top

                Point2D topNearest = nearestTo(p, currentNear, root.right);
                if(p.distanceSquaredTo(topNearest) > Math.abs(p.y()-root.data.y())){
                    Point2D bottomNearest = nearestTo(p, topNearest, root.left);

                    if(p.distanceSquaredTo(bottomNearest) < p.distanceSquaredTo(topNearest)){
                        return bottomNearest;
                    }
                }
                return topNearest ;
            }
            else{
                // check bottom

                Point2D bottomNearest = nearestTo(p, currentNear, root.left);

                if(Math.abs(p.y() - root.data.y()) < p.distanceSquaredTo(bottomNearest)){
                    Point2D topNearest = nearestTo(p, bottomNearest, root.right);

                    if(p.distanceSquaredTo(topNearest) < p.distanceSquaredTo(bottomNearest)){
                        return topNearest ;
                    }

                    return bottomNearest ;
                }


            }

            
        }

        return currentNear ;

    }

    
 // unit testing of the methods (optional) 
    public static void main(String[] args)    {

    }              
 }