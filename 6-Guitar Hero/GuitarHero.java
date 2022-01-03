public class GuitarHero {
    public static void main(String[] args) {
        String keyboardString = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[37];
        double CONCERT_A = 110.0;
        for (int i = 0; i < 37; i++) {
            strings[i] = new GuitarString(CONCERT_A * Math.pow(2, (double) i / 12));
            // System.out.print(CONCERT_A * Math.pow(2, (double) i / 12) + ",  ");
        }

        GuitarString stringA = new GuitarString(440.0);
        // the main input loop
        Keyboard keyboard = new Keyboard();
        while (true) {

            // check if the user has played a key; if so, process it
            if (keyboard.hasNextKeyPlayed()) {

                // the key the user played
                char key = keyboard.nextKeyPlayed();

                // pluck the corresponding string
                if (keyboardString.indexOf(key) != -1) {
                    //    System.out.println(strings[keyboardString.indexOf(key)].sample());
                    stringA = strings[keyboardString.indexOf(key)];
                    strings[keyboardString.indexOf(key)].pluck();
                }
            }

            // compute the superposition of the samples
            double sample = stringA.sample();

            // play the sample on standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            stringA.tic();
        }
    }
}
