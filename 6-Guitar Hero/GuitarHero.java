public class GuitarHero {
    public static void main(String[] args) {
        String keyboardString = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[37];
        double CONCERT_A = 440.0;
        for (int i = 0; i < 37; i++) {
            strings[i] = new GuitarString(CONCERT_A * Math.pow(2, (i - 24.0) / 12.0));
            // System.out.print(CONCERT_A * Math.pow(2, (i - 24.0) / 12.0) + ",  ");
        }

        // the main input loop
        Keyboard keyboard = new Keyboard();
        while (true) {

            // check if the user has played a key; if so, process it
            if (keyboard.hasNextKeyPlayed()) {

                // the key the user played
                char key = keyboard.nextKeyPlayed();
                int index = keyboardString.indexOf(key);
                // pluck the corresponding string
                if (index != -1) {
                    // System.out.println(index);
                    strings[index].pluck();
                }
            }

            // compute the superposition of the samples
            // double sample = stringA.sample();
            double sample = 0;
            for (GuitarString string : strings) {
                sample += string.sample();
            }
            // System.out.println(sample);
            // play the sample on standard audio
            // if (sample > 0) System.out.println(sample);
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (GuitarString string : strings) {
                string.tic();
            }
        }
    }
}
