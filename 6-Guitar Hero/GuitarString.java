/*******************************************************************************
 *
 *  This is a template file for GuitarString.java. It lists the constructors
 *  and methods you need, along with descriptions of what they're supposed
 *  to do.
 *
 *  Note: it won't compile until you fill in the constructors and methods
 *        (or at least commment out the ones whose return type is non-void).
 *
 ******************************************************************************/

public class GuitarString {
    private RingBuffer rb;

    // creates a guitar string of the specified frequency,
    // using sampling rate of 44,100
    public GuitarString(double frequency) {
        int SAMPLING_RATE = 44100;
        int n = (int) Math.round(SAMPLING_RATE / frequency);
        rb = new RingBuffer(n);
        for (int i = 0; i < n; i++)
            rb.enqueue(frequency);
    }

    // creates a guitar string whose size and initial values are given by
    // the specified array
    public GuitarString(double[] init) {
        rb = new RingBuffer(init.length);
        for (double d : init)
            rb.enqueue(d);
    }

    // returns the number of samples in the ring buffer
    public int length() {
        return rb.size();
    }

    // plucks the guitar string (by replacing the buffer with white noise)
    public void pluck() {
        for (int i = 0; i < rb.size(); i++) {
            double r = rb.dequeue();
            r = StdRandom.uniform(-0.5, 0.5);
            rb.enqueue(r);
        }
    }

    // advances the Karplus-Strong simulation one time step
    public void tic() {
        double first = rb.dequeue();
        double second = rb.peek();
        rb.enqueue(0.996 * 0.5 * (first + second));
    }

    // returns the current sample
    public double sample() {
        return rb.peek();
    }


    // tests and calls every constructor and instance method in this class
    public static void main(String[] args) {
        double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
        GuitarString testString = new GuitarString(samples);

        int m = 25; // number of tics
        for (int i = 0; i < m; i++) {
            double sample = testString.sample();
            StdOut.printf("%6d %8.4f\n", i, sample);
            testString.tic();
        }
    }

}
