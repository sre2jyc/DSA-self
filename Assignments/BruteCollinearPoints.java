import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

   private final LineSegment[] segments;

   private void Checker(final Point[] points) {
       if (points == null) {
           throw new IllegalArgumentException();
       }
       for(int i = 0;i<points.length;i ++){
           if(points[i] == null){
               throw new IllegalArgumentException();
           }
       }
       for (int i = 0; i < points.length - 1; i++) {

           for (int j = i + 1; j < points.length; j++) {
               if (points[i].equals(points[j])) {
                   throw new IllegalArgumentException();
               }
           }
       }
   }

  public BruteCollinearPoints(final Point[] points){
       Checker(points);
       final ArrayList <LineSegment> LS = new ArrayList<LineSegment>();
       final Point [] p = Arrays.copyOf(points, points.length);
       Arrays.sort(p);
       for(int i=0;i<p.length-3;i++){
           for(int j=i+1;j<p.length-2;j++){
               for(int k=j+1;k<p.length-1;k++){
                   for(int l=k+1;l<p.length;l++){
                       if(p[i].slopeTo(p[j]) == p[i].slopeTo(p[k]) && p[i].slopeTo(p[j]) == p[i].slopeTo(p[l])){
                          final LineSegment temp = new LineSegment(p[i], p[l]);
                          if(! LS.contains(temp)){
                              LS.add(temp);
                          }
                       }
                   }
               }
           }
       }

       segments = LS.toArray(new LineSegment[LS.size()]);

   }    // finds all line segments containing 4 points
   public           int numberOfSegments(){
       return segments.length;
   }        // the number of line segments
   public LineSegment[] segments()     {
       return segments;
   }           // the line segments
}