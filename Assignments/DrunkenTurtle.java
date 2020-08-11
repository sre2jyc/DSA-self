public class DrunkenTurtle {
    public static void main(String[] args) {
        int trials = Integer.parseInt(args[0]);        // number of steps
        double step = Double.parseDouble(args[1]);     // step size

        Turtle turtle = new Turtle(0.5, 0.5, 0.0);
        for (int t = 0; t < trials; t++) {
            double randomAngle = StdRandom.uniform(0.0, 360.0);
            turtle.turnLeft(randomAngle);
            turtle.goForward(step);
        }
    }
}