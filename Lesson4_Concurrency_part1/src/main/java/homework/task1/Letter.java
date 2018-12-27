package homework.task1;

public class Letter {

    public static Letter A;
    public static Letter B;
    public static Letter C;

    static {
        createLetters();
    }

    private static void createLetters() {
        A = new Letter('a');
        B = new Letter('b');
        C = new Letter('c');

        A.setNextLetter(B);
        B.setNextLetter(C);
        C.setNextLetter(A);
    }


    private final char letter;
    private Letter nextLetter;

    private Letter(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    public Letter getNextLetter() {
        return nextLetter;
    }

    private void setNextLetter(Letter nextLetter) {
        this.nextLetter = nextLetter;
    }
}
