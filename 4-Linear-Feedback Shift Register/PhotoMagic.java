import java.awt.Color;

public class PhotoMagic {
    // returns a new picture that has a transformed copy of the given picture, using the given lfsr.
    public static Picture transform(Picture picture, LFSR lfsr) {
        Picture after = new Picture(picture.width(), picture.height());
        for (int x = 0; x < picture.width(); x++)
            for (int y = 0; y < picture.height(); y++) {
                int r = picture.get(x, y).getRed() ^ lfsr.generate(8);
                int g = picture.get(x, y).getGreen() ^ lfsr.generate(8);
                int b = picture.get(x, y).getBlue() ^ lfsr.generate(8);
                after.set(x, y, new Color(r, g, b));
            }
        return after;
    }

    // takes the name of an image file and a description of an lfsr as command-line arguments;
    // displays a copy of the image that is "encrypted" using the LFSR.
    public static void main(String[] args) {
        Picture before = new Picture(args[0]);
        LFSR lfsr = new LFSR(args[1], Integer.parseInt(args[2]));
        Picture after = transform(before, lfsr);
        after.show();
    }
}
