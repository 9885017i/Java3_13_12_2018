package generics;

import java.lang.reflect.Array;

public class StorageImpl<E extends Comparable<E>> implements IStorage<E> {

    private E[] data;
    private int currentSize;

    public StorageImpl(Class<E> type, int maxSize) {
        this.data = (E[]) Array.newInstance(type, maxSize);
    }

    @Override
    public void add(E value) {
        add(value, currentSize);
    }

    @Override
    public void add(E value, int index) {
        data[index] = value;
        currentSize++;
    }

    @Override
    public void remove(int index) {
        data[index] = null;
        currentSize--;
    }

    @Override
    public E get(int index) {
        return data[index] = null;
    }

    @Override
    public boolean find(E value) {
        for (int i = 0; i < currentSize; i++) {
            if (value.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void display() {
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

    @Override
    public void sort() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                E a = data[i];
                E b = data[j];
                if (a.compareTo(b) > 0) {
                    exchange(i, j);
                }
            }
        }
    }

    private void exchange(int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

}
