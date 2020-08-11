import java.awt.Color;

public class KochRainbow {
    private Turtle turtle;      // for turtle graphics
    private double size;        // size of each line segment
    private double color;       // current color
    private double increment;   // change in color

    public KochRainbow(int n) {
        int width = 512;
        int height = (int) (2 * width / Math.sqrt(3));
        size = width / Math.pow(3.0, n);
        turtle = new Turtle(0, width * Math.sqrt(3) / 2, 0.0);
        turtle.SetCanvasSize(width, height);
        turtle.SetXscale(0, width);
        turtle.SetYscale(0, height);

        // rainbow of colors
        color = 0.0;
        increment = Math.pow(4.0, -n) / 3.0;


        // three Koch curves in the shape of an equilateral triangle
        koch(n);
        turtle.turnLeft(-120);
        koch(n);
        turtle.turnLeft(-120);
        koch(n);
    }



    public void koch(int n) {
        if (n == 0) {
            turtle.SetPenColor(Color.getHSBColor((float) color, 1.0f, 1.0f));
            color += increment;
            turtle.goForward(size);
        }
        else {
            koch(n-1);
            turtle.turnLeft(60);
            koch(n-1);
            turtle.turnLeft(-120);
            koch(n-1);
            turtle.turnLeft(60);
            koch(n-1);
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        new KochRainbow(n);
    }
}
