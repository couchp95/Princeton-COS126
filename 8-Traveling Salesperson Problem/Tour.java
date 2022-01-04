public class Tour {
    private int size;
    private Node first;

    private class Node {
        private Point p;
        private Node next;
    }

    // creates an empty tour
    public Tour() {
        size = 0;
        first = null;
    }

    // creates the 4-point tour  a->b->c->d->a (for debugging)
    public Tour(Point a, Point b, Point c, Point d) {
        Node nodeA = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        Node nodeD = new Node();
        nodeA.p = a;
        nodeB.p = b;
        nodeC.p = c;
        nodeD.p = d;
        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeA;
        first = nodeA;
        size = 4;
    }

    // returns the number of points in this tour
    public int size() {
        return size;
    }

    // returns the length of this tour
    public double length() {
        if (first == null) return 0;
        Node currentNode = first;
        double length = 0;
        for (int i = 0; i < size; i++) {
            length += currentNode.p.distanceTo(currentNode.next.p);
            currentNode = currentNode.next;
        }
        return length;
    }

    // returns a string representation of this tour
    public String toString() {
        if (first == null) return "";
        Node currentNode = first;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(currentNode.p + "\n");
            currentNode = currentNode.next;
        }
        return s.toString();
    }

    // draws this tour to standard drawing
    public void draw() {
        if (first == null) return;
        Node currentNode = first;
        for (int i = 0; i < size; i++) {
            currentNode.p.drawTo(currentNode.next.p);
            currentNode = currentNode.next;
        }
    }

    // inserts p using the nearest neighbor heuristic
    public void insertNearest(Point p) {
        if (p == null) throw new NullPointerException("Point p is null!");
        if (first == null) {
            first = new Node();
            first.p = p;
            first.next = first;
            size++;
            return;
        }
        Node currentNode = first;
        Node closestNode = first;
        double distance = Double.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            if (currentNode.p.distanceTo(p) < distance) {
                distance = currentNode.p.distanceTo(p);
                closestNode = currentNode;
            }
            currentNode = currentNode.next;
        }
        Node newNode = new Node();
        newNode.p = p;
        newNode.next = closestNode.next;
        closestNode.next = newNode;
        size++;
    }

    // inserts p using the smallest increase heuristic
    public void insertSmallest(Point p) {
        if (p == null) throw new NullPointerException("Point p is null!");
        if (first == null) {
            first = new Node();
            first.p = p;
            first.next = first;
            size++;
            return;
        }
        Node currentNode = first;
        Node smallestNode = first;
        double length = length();
        double shortest = Double.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            double tmpLength = length - currentNode.p.distanceTo(currentNode.next.p) + currentNode.p
                    .distanceTo(p) + p.distanceTo(currentNode.next.p);
            if (tmpLength < shortest) {
                smallestNode = currentNode;
                shortest = tmpLength;
            }
            currentNode = currentNode.next;
        }
        Node newNode = new Node();
        newNode.p = p;
        newNode.next = smallestNode.next;
        smallestNode.next = newNode;
        size++;
    }

    // tests this class by directly calling all constructors and instance methods
    public static void main(String[] args) {
        // define 4 points that are the corners of a square
        Point a = new Point(100.0, 100.0);
        Point b = new Point(500.0, 100.0);
        Point c = new Point(500.0, 500.0);
        Point d = new Point(100.0, 500.0);

        // create the tour a -> b -> c -> d -> a
        Tour squareTour = new Tour(a, b, c, d);

        // print the size to standard output
        int size = squareTour.size();
        StdOut.println("Number of points = " + size);
        // print the tour length to standard output
        double length = squareTour.length();
        StdOut.println("Tour length = " + length);
        // print the tour to standard output
        StdOut.println(squareTour);
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);
        //squareTour.draw();

        int width = StdIn.readInt();
        int height = StdIn.readInt();
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        Tour tour = new Tour();
        while (!StdIn.isEmpty()) {
            tour.insertSmallest(new Point(StdIn.readDouble(), StdIn.readDouble()));
        }
        StdOut.println("Number of points = " + tour.size);
        StdOut.println("Tour length = " + tour.length());
        tour.draw();

    }
}
