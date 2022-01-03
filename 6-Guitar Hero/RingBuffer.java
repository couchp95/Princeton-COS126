/*******************************************************************************
 *
 *  This is a template file for RingBuffer.java. It lists the constructors
 *  and methods you need to implement, along with descriptions of what they're
 *  supposed to do.
 *
 *  Note: it won't compile until you fill in the constructors and methods
 *        (or at least commment out the ones whose return type is non-void).
 *
 ******************************************************************************/

public class RingBuffer {
    private double[] rb;
    private int first;
    private int last;
    private int size;

    // creates an empty ring buffer with the specified capacity
    public RingBuffer(int capacity) {
        rb = new double[capacity];
        first = 0;
        last = 0;
        size = 0;
    }

    // return the capacity of this ring buffer
    public int capacity() {
        return rb.length;
    }

    // return number of items currently in this ring buffer
    public int size() {
        return size;
    }

    // is this ring buffer empty (size equals zero)?
    public boolean isEmpty() {
        return (size == 0);
    }

    // is this ring buffer full (size equals capacity)?
    public boolean isFull() {
        return (size == rb.length);
    }

    // adds item x to the end of this ring buffer
    public void enqueue(double x) {
        if (isFull()) throw new RuntimeException();
        rb[last] = x;
        if (last < rb.length - 1) last++;
        else last = 0;
        size++;
    }

    // deletes and returns the item at the front of this ring buffer
    public double dequeue() {
        if (isEmpty()) throw new RuntimeException();
        double tmp = rb[first];
        if (first < rb.length - 1) first++;
        else first = 0;
        size--;
        return tmp;
    }

    // returns the item at the front of this ring buffer
    public double peek() {
        if (isEmpty()) throw new RuntimeException();
        return rb[first];
    }

    // tests and calls every instance method in this class
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        RingBuffer buffer = new RingBuffer(n);
        for (int i = 1; i <= n; i++) {
            buffer.enqueue(i);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        StdOut.println("Size after wrap-around is " + buffer.size());

        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        StdOut.println(buffer.peek());
    }

}
