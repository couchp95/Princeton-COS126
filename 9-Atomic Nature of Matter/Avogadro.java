public class Avogadro {
    public static void main(String[] args) {
        double rr = 0.0;
        int n = 0;
        while (!StdIn.isEmpty()) {
            double r = StdIn.readDouble() * 0.175 * 0.000001;
            rr += r * r;
            n++;
        }
        double d = rr / n / 2;
        double boltzmann = d * 6 * Math.PI * 9.135 * 0.0001 * 0.5 * 0.000001 / 297;
        System.out.printf("Boltzmann = %.4e%n", boltzmann);
        double avogadro = 8.31446 / boltzmann;
        System.out.printf("Avogadro  = %.4e%n", avogadro);
    }
}
