public class ImageClassifier {
    // Creates a feature vector (1D array) from the given picture.
    public static double[] extractFeatures(Picture picture) {
        double[] x = new double[picture.width() * picture.height()];
        for (int i = 0; i < picture.width(); i++)
            for (int j = 0; j < picture.height(); j++) {
                x[i * picture.width() + j] = picture.get(i, j).getRed();
            }
        return x;
    }

    // See below.
    public static void main(String[] args) {
        In training = new In(args[0]);
        In testing = new In(args[1]);
        int m = training.readInt();
        int width = training.readInt();
        int height = training.readInt();
        MultiPerceptron mp = new MultiPerceptron(m, width * height);
        while (!training.isEmpty()) {
            String imageFile = training.readString();
            int label = training.readInt();
            Picture picture = new Picture(imageFile);
            double[] x = extractFeatures(picture);
            mp.trainMulti(x, label);
        }
        m = testing.readInt();
        width = testing.readInt();
        height = testing.readInt();
        int error = 0;
        int sum = 0;
        while (!testing.isEmpty()) {
            String imageFile = testing.readString();
            int label = testing.readInt();
            Picture picture = new Picture(imageFile);
            double[] x = extractFeatures(picture);
            int mpLabel = mp.predictMulti(x);
            sum++;
            if (label != mpLabel) {
                error++;
                System.out.println(imageFile + ", label = " + label + ", predict = " + mpLabel);
            }
        }
        System.out.println("test error rate = " + (1.0 * error / sum));
    }
}
