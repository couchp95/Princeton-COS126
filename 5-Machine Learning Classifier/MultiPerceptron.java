public class MultiPerceptron {
    private int m;
    private int n;
    private Perceptron[] perceptrons;

    // Creates a multi-perceptron object with m classes and n inputs.
    // It creates an array of m perceptrons, each with n inputs.
    public MultiPerceptron(int m, int n) {
        this.m = m;
        this.n = n;
        perceptrons = new Perceptron[m];
        for (int i = 0; i < m; i++)
            perceptrons[i] = new Perceptron(n);
    }

    // Returns the number of classes m.
    public int numberOfClasses() {
        return m;
    }

    // Returns the number of inputs n (length of the feature vector).
    public int numberOfInputs() {
        return n;
    }

    // Returns the predicted label (between 0 and m-1) for the given input.
    public int predictMulti(double[] x) {
        int index = 0;
        double sumMax = Double.MIN_VALUE;
        for (int i = 0; i < m; i++)
            if (perceptrons[i].weightedSum(x) > sumMax) {
                sumMax = perceptrons[i].weightedSum(x);
                index = i;
            }
        return index;
    }

    // Trains this multi-perceptron on the labeled (between 0 and m-1) input.
    public void trainMulti(double[] x, int label) {
        for (int i = 0; i < m; i++) {
            if (i == label) perceptrons[i].train(x, 1);
            else perceptrons[i].train(x, -1);
        }
    }

    // Returns a String representation of this MultiPerceptron, with
    // the string representations of the perceptrons separated by commas
    // and enclosed in parentheses.
    // Example with m = 2 and n = 3: ((2.0, 0.0, -2.0), (3.0, 4.0, 5.0))
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("(");
        for (int i = 0; i < m; i++)
            s.append(perceptrons[i] + ",");
        s.deleteCharAt(s.length() - 1);
        s.append(")");
        return s.toString();
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        int m = 2;
        int n = 3;

        double[] training1 = { 5.0, -4.0, 3.0 };  // class 1
        double[] training2 = { 2.0, 3.0, -2.0 };  // class 0
        double[] training3 = { 4.0, 3.0, 2.0 };  // class 1
        double[] training4 = { -6.0, -5.0, 7.0 };  // class 0
        MultiPerceptron perceptron = new MultiPerceptron(m, n);
        StdOut.println(perceptron);
        perceptron.trainMulti(training1, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training2, 0);
        StdOut.println(perceptron);
        perceptron.trainMulti(training3, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training4, 0);
        StdOut.println(perceptron);

    }
}
