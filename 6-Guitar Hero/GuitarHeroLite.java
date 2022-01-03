/*******************************************************************************
 *
 *  Plays two guitar strings (concert A and concert C) when the user either
 *  types the corresponding keys in an interactive window.
 *
 ******************************************************************************/

public class GuitarHeroLite {

    public static void main(String[] args) {

        // create two guitar strings, for concert A and concert C
        double CONCERT_A = 440.0;
        double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0);
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);

        // the main input loop
        Keyboard keyboard = new Keyboard();
        while (true) {

            // check if the user has played a key; if so, process it
            if (keyboard.hasNextKeyPlayed()) {
 
                // the key the user played
                char key = keyboard.nextKeyPlayed();

                // pluck the corresponding string
                if (key == 'a') { stringA.pluck(); }
                if (key == 'c') { stringC.pluck(); }
            }

            // compute the superposition of the samples
            double sample = stringA.sample() + stringC.sample();

            // play the sample on standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            stringA.tic();
            stringC.tic();
        }
    }

}
