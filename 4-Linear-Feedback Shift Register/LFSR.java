public class LFSR {
    private int[] cells;
    private int tap;

    // creates an LFSR with the specified seed and tap
    public LFSR(String seed, int tap) {
        cells = new int[seed.length()];
        for (int i = 0; i < cells.length; i++)
            cells[i] = seed.charAt(i) - 48;
        this.tap = tap;
    }

    // returns the number of bits in this LFSR
    public int length() {
        return cells.length;
    }

    // returns the ith bit of this LFSR (as 0 or 1)
    public int bitAt(int i) {
        return cells[cells.length - i];
    }

    // returns a string representation of this LFSR
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < cells.length; i++)
            s.append(cells[i]);
        return s.toString();
    }

    // simulates one step of this LFSR and returns the new bit (as 0 or 1)
    public int step() {
        int tmp = cells[0] ^ cells[cells.length - tap];
        for (int i = 0; i < cells.length - 1; i++)
            cells[i] = cells[i + 1];
        cells[cells.length - 1] = tmp;
        return tmp;
    }

    // simulates k steps of this LFSR and returns the k bits as a k-bit integer
    public int generate(int k) {
        int sum = 0;
        for (int i = k; i > 0; i--)
            sum += Math.pow(2, i - 1) * step();
        return sum;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
        LFSR lfsr0 = new LFSR("01101000010", 9);
        StdOut.println(lfsr0); // outputs 01101000010
        StdOut.println(lfsr0.length());
        
        LFSR lfsr1 = new LFSR("01101000010", 9);
        StdOut.println(lfsr1);
        for (int i = 0; i < 10; i++) {
            int bit = lfsr1.step();
            StdOut.println(lfsr1 + " " + bit);
        }
        LFSR lfsr2 = new LFSR("01101000010", 9);
        StdOut.println(lfsr2);
        for (int i = 0; i < 10; i++) {
            int r = lfsr2.generate(5);
            StdOut.println(lfsr2 + " " + r);
        }

    }
}
