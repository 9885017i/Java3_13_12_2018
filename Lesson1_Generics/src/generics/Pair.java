package generics;

public class Pair<T, Err extends Exception> {

    private final T value;
    private final Err error;

    public Pair(T value, Err error) {
        this.value = value;
        this.error = error;
    }

    public T getValue() {
        return value;
    }

    public Err getError() {
        return error;
    }

    public boolean hasError() {
        return error != null;
    }

    public static <T1, Err1 extends Exception> Pair<T1, Err1> create(T1 value) {
        return new Pair<>(value, null);
    }
}
