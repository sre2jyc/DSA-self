import java.awt.Color;
import java.awt.Image;

import edu.princeton.cs.algs4.Picture;

public class Potential {

    
    public static void main(String[] args) {

        // read in n point charges
        int n = StdIn.readInt();
        Charge[] a = new Charge[n];
        for (int k = 0; k < n; k++) {
            double x0 = StdIn.readDouble();
            double y0 = StdIn.readDouble();
            double q0 = StdIn.readDouble();
            a[k] = new Charge(x0, y0, q0);
        }

        // compute the potential at each point and plot
        int SIZE = 800;
        Picture pic = new Picture(SIZE, SIZE);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                double potential = 0.0;
                for (int k = 0; k < n; k++) {
                    double x = 1.0 * i / SIZE;
                    double y = 1.0 * j / SIZE;
                    potential += a[k].potentialAt(x, y);
                }
                potential = 128 + potential / 2.0e10;
                int t = 0;
                if      (potential <   0) t = 0;
                else if (potential > 255) t = 255;
                else                      t = (int) potential;
                // Color c = new Color(t, t, t);
                Color c = Color.getHSBColor(t / 255.0f, 0.9f, 0.9f);
                // Color c = Color.getHSBColor(0.75f*t/40, 0.8f, 0.8f);
                // int gray = (t * 37) % 255;
                // Color c = new Color(gray, gray, gray);
                pic.set(i, SIZE-1-j, c);
            }
        }
        pic.show();
    }
}