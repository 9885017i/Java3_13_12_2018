package generics;

@FunctionalInterface
public interface Transformer<T, R> {

    R transform(T value);
}