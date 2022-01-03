public class TextGenerator {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        String s = StdIn.readString();
        MarkovModel mm = new MarkovModel(s, k);
        String kgram = s.substring(0, k);
        System.out.print(kgram);
        for (int i = 0; i < t - k; i++) {
            String c = Character.toString(mm.random(kgram));
            System.out.print(c);
            if (k > 0) kgram = kgram.substring(1, kgram.length()) + c;
        }
        System.out.println();
    }
}
