public class Sierpinski {
    // Height of an equilateral triangle with the specified side length.
    public static double height(double length) {
        return length * Math.sqrt(3) / 2;
    }

    // Draws a filled equilateral triangle with the specified side length
    // whose bottom vertex is (x, y).
    public static void filledTriangle(double x, double y, double length) {
        double h = height(length);
        double[] xTriangle = new double[3];
        double[] yTriangle = new double[3];
        xTriangle[0] = x;
        xTriangle[1] = x - length / 2;
        xTriangle[2] = x + length / 2;
        yTriangle[0] = y;
        yTriangle[1] = y + h;
        yTriangle[2] = y + h;
        // StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledPolygon(xTriangle, yTriangle);
    }

    // Draws a Sierpinski triangle of order n, such that the largest filled
    // triangle has the specified side length and bottom vertex (x, y).
    public static void sierpinski(int n, double x, double y, double length) {
        if (n == 0) return;
        filledTriangle(x, y, length);
        sierpinski(n - 1, x - length / 2, y, length / 2);
        sierpinski(n - 1, x + length / 2, y, length / 2);
        sierpinski(n - 1, x, y + height(length), length / 2);
    }

    // Takes an integer command-line argument n;
    // draws the outline of an upwards equilateral triangle of length 1
    // whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0);
    // and draws a Sierpinski triangle of order n that fits inside the outline.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double[] xTriangle = { 0, 1, 0.5 };
        double[] yTriangle = { 0, 0, 0 };
        yTriangle[2] = height(1);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.polygon(xTriangle, yTriangle);
        sierpinski(n, 0.5, 0, 0.5);

    }
}
