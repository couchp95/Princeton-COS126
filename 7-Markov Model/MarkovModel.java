public class MarkovModel {
    private static final int ASCII = 128;
    private ST<String, Integer> st;
    private ST<String, int[]> st2;
    private int k;

    // creates a Markov model of order k based on the specified text
    public MarkovModel(String text, int k) {
        this.k = k;
        st = new ST<String, Integer>();
        st2 = new ST<String, int[]>();
        text = text + text.substring(0, k);
        for (int i = 0; i < text.length() - k; i++) {
            String s = String.valueOf(text.charAt(i));
            if (st.contains(s))
                st.put(s, st.get(s) + 1);
            else st.put(s, 1);
            char s2 = text.charAt(i + k);
            int[] frequency;
            if (st2.contains(s)) frequency = st2.get(s);
            else frequency = new int[ASCII];
            frequency[s2]++;
            st2.put(s, frequency);
        }

    }

    // returns the order of the model (also known as k)
    public int order() {
        return k;
    }

    // returns a String representation of the model (more info below)
    public String toString() {
        String result = "";
        for (String key : st2.keys()) {
            result += key + ": ";
            // get the character frequency array
            int[] frequency = st2.get(key);
            // for each non-zero entry, append the character and the frequency
            for (char i = 0; i < frequency.length; i++)
                if (frequency[i] > 0) result += Character.toString(i) + " " + frequency[i] + " ";
            // append a newline character
            result += "\n";
        }
        return result;
    }

    // returns the # of times 'kgram' appeared in the input text
    public int freq(String kgram) {
        if (k != 0 && kgram.length() != k) throw new IllegalArgumentException();
        if (!st.contains(kgram)) return 0;
        return st.get(kgram);
    }

    // returns the # of times 'c' followed 'kgram' in the input text
    public int freq(String kgram, char c) {
        if (kgram.length() != k) throw new IllegalArgumentException();
        if (!st2.contains(kgram)) return 0;
        int[] freq = st2.get(kgram);
        return freq[c];
    }

    // returns a random character, chosen with weight proportional to the
    // number of times each character followed 'kgram' in the input text
    public char random(String kgram) {
        if (kgram.length() != k) throw new IllegalArgumentException();
        if (k > 0 && st2.contains(kgram)) {
            int[] freq = st2.get(kgram);
            return (char) StdRandom.discrete(freq);
        }
        else {
            int[] freq = new int[ASCII];
            for (char i = 0; i < ASCII; i++) {
                // System.out.print("i=" + i + " , " + String.valueOf(i) + " ");
                if (st.get(String.valueOf(i)) != null) {
                    freq[i] = st.get(String.valueOf(i));
                    // System.out.println("i=" + i + "  " + freq[i]);
                }
                else freq[i] = 0;
            }
            return (char) StdRandom.discrete(freq);
        }
    }

    // tests all instance methods to make sure they're working as expected
    public static void main(String[] args) {
        String text1 = "banana";
        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println("freq(\"an\", 'a')    = " + model1.freq("an", 'a'));
        StdOut.println("freq(\"na\", 'b')    = " + model1.freq("na", 'b'));
        StdOut.println("freq(\"na\", 'a')    = " + model1.freq("na", 'a'));
        StdOut.println("freq(\"na\")         = " + model1.freq("na"));
        StdOut.println();

        String text3 = "one fish two fish red fish blue fish";
        MarkovModel model3 = new MarkovModel(text3, 4);
        StdOut.println("freq(\"ish \", 'r') = " + model3.freq("ish ", 'r'));
        StdOut.println("freq(\"ish \", 'x') = " + model3.freq("ish ", 'x'));
        StdOut.println("freq(\"ish \")      = " + model3.freq("ish "));
        StdOut.println("freq(\"tuna\")      = " + model3.freq("tuna"));

        MarkovModel model4 = new MarkovModel(text3, 0);
        StdOut.println("i=" + model4.freq("i"));
        StdOut.println("e=" + model4.freq("e"));
    }
}
