public class GosperIsland {
    private static final double ANGLE = 19.106605350869094394;   // arccos(5 * sqrt(7) / 14)

    private Turtle turtle;
    private double size;


    public GosperIsland(int n) {
        size = (7.0 / 16.0) / Math.pow(Math.sqrt(7.0), n);


        // Gosper island - 6 gosper curves
        turtle = new Turtle(9.0/32.0, 1.0/8.0, 0.0);
        gosper(n);
        turtle.turnLeft(60);
        gosper(n);
        turtle.turnLeft(60);
        gosper(n);
        turtle.turnLeft(60);
        gosper(n);
        turtle.turnLeft(60);
        gosper(n);
        turtle.turnLeft(60);
        gosper(n);
        turtle.turnLeft(60);
    }

    // Gosper curve of order n
    public void gosper(int n) {
        if (n == 0) turtle.goForward(size);
        else {
            turtle.turnLeft(-ANGLE);
            gosper(n-1);
            turtle.turnLeft(60);
            gosper(n-1);
            turtle.turnLeft(-60);
            gosper(n-1);
            turtle.turnLeft(ANGLE);
        }
    }


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        new GosperIsland(n);
    }
}
