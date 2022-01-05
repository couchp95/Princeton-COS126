public class Blob {
    private int mass;
    private double cx, cy;

    //  creates an empty blob
    public Blob() {
        mass = 0;
        cx = 0;
        cy = 0;
    }

    //  adds pixel (x, y) to this blob
    public void add(int x, int y) {
        cx = (cx * mass + x) / (mass + 1);
        cy = (cy * mass + y) / (mass + 1);
        mass++;
    }

    //  number of pixels added to this blob
    public int mass() {
        return mass;
    }

    //  Euclidean distance between the center of masses of the two blobs
    public double distanceTo(Blob that) {
        return Math.sqrt((that.cx - this.cx) * (that.cx - this.cx)
                                 + (that.cy - this.cy) * (that.cy - this.cy));
    }

    //  string representation of this blob (see below)
    public String toString() {
        return String.format("%2d (%8.4f, %8.4f)", mass, cx, cy);
    }

    public static void main(String[] args) {
        Blob b1 = new Blob();
        b1.add(10, 5);
        b1.add(11, 5);
        b1.add(12, 5);

        Blob b2 = new Blob();
        b2.add(20, 5);
        b2.add(21, 5);
        b2.add(22, 5);
        b2.add(23, 5);
        System.out.println("Blob 1: " + b1 + "\t mass: " + b1.mass());
        System.out.println("Blob 2: " + b2 + "\t mass: " + b2.mass());
        System.out.println("Distance b1 to b2: " + b1.distanceTo(b2));
    }
}
