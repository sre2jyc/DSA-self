import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;



public class FastCollinearPoints {
   private LineSegment[] segments;
   /*public FastCollinearPoints(Point[] points){
      Checker(points);
      Point[] p = Arrays.copyOf(points, points.length);
      ArrayList <LineSegment> LS = new ArrayList<LineSegment>();
      //Arrays.sort(p);
      //print(p);
      for(int i=0;i<p.length;i++){
         Point [] pTemp = Arrays.copyOf(p, p.length);
         Arrays.sort(pTemp,p[i].slopeOrder());
         //print(pTemp);
         
         //StdOut.println("--------");
         ArrayList <Point> SameSlope = new ArrayList<Point>();
         //Arrays.fill(sameslope, null);
         SameSlope.add(pTemp[0]);
         for(int j=0;j<pTemp.length-1;j++){
            if(p[i].slopeTo(pTemp[j]) == p[i].slopeTo(pTemp[j+1])){
               SameSlope.add(pTemp[j+1]);
            }
            else{
               if(SameSlope.size() >= 3){
                  SameSlope.add(p[i]);
                   Collections.sort(SameSlope);
                   //PA(SameSlope);
                   LineSegment temp = new LineSegment(SameSlope.get(0), SameSlope.get(SameSlope.size()-1));
                   if(LS.contains(temp) == false){
                      LS.add(temp);
                   }
               }
               SameSlope.clear();
               //StdOut.println(SameSlope.size());
               SameSlope.add(pTemp[j+1]);


            }
            
         }
      }

      segments = LS.toArray(new LineSegment[LS.size()]);
      
   }   */
   public FastCollinearPoints(Point[] points)
    {
        Checker(points);
        Point[] pointsCopySO = Arrays.copyOf(points, points.length);
        Point[] pointsCopyNO = Arrays.copyOf(points, points.length);
        ArrayList<LineSegment> segmentsList = new ArrayList<LineSegment>();
        Arrays.sort(pointsCopyNO);
        
        for (int i = 0; i < pointsCopyNO.length; ++i)
        {
            Point origin = pointsCopyNO[i];
            Arrays.sort(pointsCopySO);
            Arrays.sort(pointsCopySO, origin.slopeOrder());
            int count = 1;
            Point lineBeginning = null;
            for (int j = 0; j < pointsCopySO.length - 1; ++j)
            {
                if (pointsCopySO[j].slopeTo(origin) == pointsCopySO[j + 1].slopeTo(origin))
                {
                    count++;
                    if (count == 2)
                    {
                        lineBeginning = pointsCopySO[j];
                        count++;
                    }
                    else if (count >= 4 && j + 1 == pointsCopySO.length - 1)
                    {
                        if (lineBeginning.compareTo(origin) > 0)
                        {
                            segmentsList.add(new LineSegment(origin, pointsCopySO[j + 1]));
                        }
                        count = 1;
                    }
                }
                else if (count >= 4)
                {
                    if (lineBeginning.compareTo(origin) > 0)
                    {
                        segmentsList.add(new LineSegment(origin, pointsCopySO[j]));
                    }
                    count = 1;
                }
                else
                {
                    count = 1;
                }


            }

        }
        segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);


    }
     // finds all line segments containing 4 or more points
   public           int numberOfSegments() {
      return segments.length;
   }       // the number of line segments
   public LineSegment[] segments()      {
      return segments;
   }          // the line segments



   private void Checker(Point[] points){
      if(points == null){
          throw new IllegalArgumentException();
      }
      for(int i = 0;i<points.length;i ++){
         if(points[i] == null){
             throw new IllegalArgumentException();
         }
     }
      for(int i=0;i<points.length-1;i++){

          for(int j=i+1;j<points.length;j++){
              if(points[i].equals(points[j]) ){
                  throw new IllegalArgumentException();
              }
          }
      }
  }

//   private void print(Point [] p){
//      for(int i=0;i<p.length;i++){
//         StdOut.println(p[i].toString());
//      }
//   }

//   private void PA(ArrayList <Point> p){
//      for(Point i : p){
//         StdOut.println(i.toString());
//      }
//   }



  public static void main(String[] args) {

   // read the n points from a file
   In in = new In(args[0]);
   int n = in.readInt();
   Point[] points = new Point[n];
   for (int i = 0; i < n; i++) {
       int x = in.readInt();
       int y = in.readInt();
       points[i] = new Point(x, y);
   }

   // draw the points
   StdDraw.enableDoubleBuffering();
   StdDraw.setXscale(0, 32768);
   StdDraw.setYscale(0, 32768);
   for (Point p : points) {
       p.draw();
   }
   StdDraw.show();

   // print and draw the line segments
   FastCollinearPoints collinear = new FastCollinearPoints(points);
   for (LineSegment segment : collinear.segments()) {
       StdOut.println(segment);
       segment.draw();
   }
   StdDraw.show();
}
}