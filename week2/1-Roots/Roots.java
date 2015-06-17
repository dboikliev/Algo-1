public class Roots {
    public double squareRoot(int number) {
        return squareRoot(number, 1, number);
    }

    private double squareRoot(double value, double left, double right) {
        double mid = (left + right) / 2;
        double precision = 0.00001;
        while (Math.abs(mid * mid - value) > precision){
            mid = (left + right) / 2;
            if (mid * mid - value > precision) {
                right = mid;
            } else if (mid * mid - value < -precision) {
                left = mid;
            }
        }
        return mid;
    }
}
