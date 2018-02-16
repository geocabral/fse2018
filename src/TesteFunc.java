import java.util.Random;

public class TesteFunc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(poisson(0.01, (new Random())));
		System.out.println(poisson(4, (new Random())));
	}

	
	public static int poisson(double lambda, Random r) {
        if (lambda < 100.0) {
            double product = 1.0;
            double sum = 1.0;
            double threshold = r.nextDouble() * Math.exp(lambda);
            int i = 1;
            int max = Math.max(100, 10 * (int) Math.ceil(lambda));
            while ((i < max) && (sum <= threshold)) {
                product *= (lambda / i);
                sum += product;
                i++;
            }
            return i - 1;
        }
        double x = lambda + Math.sqrt(lambda) * r.nextGaussian();
        if (x < 0.0) {
            return 0;
        }
        return (int) Math.floor(x);
    }
	
}

