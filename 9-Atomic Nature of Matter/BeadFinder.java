public class BeadFinder {
    private Queue<Blob> blobs;
    private int[][] pic;
    private Picture picture;

    //  finds all blobs in the specified picture using luminance threshold tau
    public BeadFinder(Picture picture, double tau) {
        this.picture = picture;
        blobs = new Queue<Blob>();
        pic = new int[picture.width()][picture.height()];
        for (int x = 0; x < picture.width(); x++)
            for (int y = 0; y < picture.height(); y++) {
                Blob b = new Blob();
                dfs(x, y, tau, b);
                if (b.mass() > 0) {
                    blobs.enqueue(b);
                    //  System.out.println("b.mass() = " + b.mass());
                }
            }
    }

    private Blob dfs(int x, int y, double tau, Blob b) {
        // System.out.println("Blob size: " + b.mass());
        if (pic[x][y] == 0 && Luminance.intensity(picture.get(x, y)) > tau) {
            b.add(x, y);
            pic[x][y] = -1;
            if (x > 0) dfs(x - 1, y, tau, b);
            if (x < picture.width() - 1) dfs(x + 1, y, tau, b);
            if (y > 0) dfs(x, y - 1, tau, b);
            if (y < picture.height() - 1) dfs(x, y + 1, tau, b);
        }
        return b;
    }

    //  returns all beads (blobs with >= min pixels)
    public Blob[] getBeads(int min) {
        int size = countBeads(min);
        Blob[] result = new Blob[size];
        int i = 0;
        for (Blob b : blobs)
            if (b.mass() >= min) result[i++] = b;
        return result;
    }

    private int countBeads(int min) {
        int size = 0;
        for (Blob b : blobs)
            if (b.mass() >= min) size++;
        return size;
    }

    //  test client, as described below
    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        Picture picture = new Picture(args[2]);
        BeadFinder bf = new BeadFinder(picture, tau);
        Blob[] blobs = bf.getBeads(min);
        for (Blob b : blobs)
            System.out.println(b);
    }
}
