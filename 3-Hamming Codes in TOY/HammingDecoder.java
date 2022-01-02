/* *****************************************************************************
 *
 *  Description: Read in sequences of seven (7) Hamming-encoded bits at a time
 *               from standard input, and print four (4) corrected bits to
 *               standard output. Assumes input is a multiple of seven (7) bits
 *               (or is terminated with FFFF).
 *
 *  Compilation:  javac-introcs HammingDecoder.java
 *  Execution:    java-introcs HammingDecoder < all7.txt
 *
 *  > less decode3.txt
 *  0001 0000 0000 0001 0001 0000 0000
 *  0000 0001 0001 0000 0000 0000 0000
 *  0001 0001 0001 0001 0001 0001 0000
 *  FFFF
 *
 *  > java-introcs HammingDecoder < decode3.txt
 *  0001
 *  0001
 *  0000
 *  0001
 *  0001
 *  0001
 *  0001
 *  0000
 *  0001
 *  0001
 *  0001
 *  0001
 *
 * ************************************************************************** */

public class HammingDecoder {

    // return an integer corresponding to the 4-digit hex string
    public static int fromHex(String s) {
        return Integer.parseInt(s, 16) & 0xFFFF;
    }

    public static void main(String[] args) {

        while (!StdIn.isEmpty()) {
            int m1 = fromHex(StdIn.readString());
            if (m1 == 0xFFFF) break;
            int m2 = fromHex(StdIn.readString());
            int m3 = fromHex(StdIn.readString());
            int m4 = fromHex(StdIn.readString());
            int p1 = fromHex(StdIn.readString());
            int p2 = fromHex(StdIn.readString());
            int p3 = fromHex(StdIn.readString());

            // check parity bits - are they "even parity" - by xor'ing each
            // bit: 0 means even, 1 means odd
            int c1 = p1 ^ m1 ^ m2 ^ m4;
            int c2 = p2 ^ m1 ^ m3 ^ m4;
            int c3 = p3 ^ m2 ^ m3 ^ m4;

            // flip incorrect bit if necessary
            if (c1 + c2 + c3 == 3) // all checks fail
                m4 = 1 ^ m4;
            else if (c1 + c2 == 2) // c1 and c2 fail only
                m1 = 1 ^ m1;
            else if (c1 + c3 == 2) // c1 and c3 fail only
                m2 = 1 ^ m2;
            else if (c2 + c3 == 2) // c2 and c3 fail only
                m3 = 1 ^ m3;

            // print the corrected bits to standard output
            // %04d prints the integer with leadings 0s
            StdOut.printf("%04d\n%04d\n%04d\n%04d\n", m1, m2, m3, m4);
        }
    }
}
