public class Transform2D {
    // Returns a new array object that is an exact copy of the given array.
    // The given array is not mutated.
    public static double[] copy(double[] array) {
        double[] newArray = new double[array.length];
        for (int i = 0; i < array.length; i++)
            newArray[i] = array[i];
        return newArray;
    }

    // Scales the polygon by the factor alpha.
    public static void scale(double[] x, double[] y, double alpha) {
        for (int i = 0; i < x.length; i++) {
            x[i] = x[i] * alpha;
            y[i] = y[i] * alpha;
        }
    }

    // Translates the polygon by (dx, dy).
    public static void translate(double[] x, double[] y, double dx, double dy) {
        for (int i = 0; i < x.length; i++) {
            x[i] = x[i] + dx;
            y[i] = y[i] + dy;
        }
    }

    // Rotates the polygon theta degrees counterclockwise, about the origin.
    public static void rotate(double[] x, double[] y, double theta) {
        double[] xi = copy(x);
        double[] yi = copy(y);
        for (int i = 0; i < x.length; i++) {
            x[i] = xi[i] * Math.cos(Math.toRadians(theta)) - yi[i] * Math
                    .sin(Math.toRadians(theta));
            y[i] = yi[i] * Math.cos(Math.toRadians(theta)) + xi[i] * Math
                    .sin(Math.toRadians(theta));
        }
    }

    // Tests each of the API methods by directly calling them.
    public static void main(String[] args) {
        // Set the x- and y-scale
        StdDraw.setScale(-5.0, 5.0);

        // Create original polygon
        double[] x = { 0, 1, 1, 0 };
        double[] y = { 0, 0, 2, 1 };

        // Copy original polygon
        double[] cx = copy(x);
        double[] cy = copy(y);

        // Rotate and translate the copy
        rotate(cx, cy, -45.0);
        translate(cx, cy, 1.0, 2.0);

        // Draw the copy in blue
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(cx, cy);

        // Draw the original polygon in red
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.polygon(x, y);

        // Scale polygon by 2.0
        scale(x, y, 2.0);

        // Draw scaled polygon in blue
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.polygon(x, y);

        // Translate polygon by
        // 2.0 in the x-direction
        // 1.0 in the y-direction
        translate(x, y, 2.0, 1.0);

        // Draw translated polygon in blue
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.polygon(x, y);
        // Rotate polygon by 45 degrees ccw
        rotate(x, y, 45.0);

        // Draw rotated polygon in blue
        StdDraw.setPenColor(StdDraw.PINK);
        StdDraw.polygon(x, y);
    }
}
