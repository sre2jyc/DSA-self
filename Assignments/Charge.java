
/**
 * Charge
 */
public class Charge {
    private double x , y , charge ;

    public Charge(double x0,double y0,double q0){
        x = x0 ;
        y = y0 ;
        charge = q0;
    }

    public double potentialAt(double x,double y){
        double r = Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
        double k = 8.99 * Math.pow(10, 9) ;

        double Potential = 1.00 * k*this.charge/r ;

        return Potential ;
    }
}