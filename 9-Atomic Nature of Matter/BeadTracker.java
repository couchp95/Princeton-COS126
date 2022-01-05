public class BeadTracker {
    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);
        String[] imageFiles = new String[args.length - 3];
        for (int i = 3; i < args.length; i++)
            imageFiles[i - 3] = args[i];
        Picture picture1 = new Picture(imageFiles[0]);
        BeadFinder bf1 = new BeadFinder(picture1, tau);
        Blob[] b1 = bf1.getBeads(min);
        for (int i = 0; i < imageFiles.length - 1; i++) {
            Picture picture2 = new Picture(imageFiles[i + 1]);
            BeadFinder bf2 = new BeadFinder(picture2, tau);
            Blob[] b2 = bf2.getBeads(min);
            for (Blob b : b1) {
                double distance = Double.MAX_VALUE;
                for (Blob c : b2) {
                    double d = b.distanceTo(c);
                    if (d < delta && d < distance) {
                        distance = d;
                    }
                }
                if (distance < delta) System.out.printf("%.4f%n", distance);
            }
            System.out.println();
            picture1 = picture2;
            bf1 = bf2;
            b1 = b2;
        }
    }

}
