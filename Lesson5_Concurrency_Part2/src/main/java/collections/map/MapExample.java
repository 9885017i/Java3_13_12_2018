package collections.map;

import java.io.BufferedReader;
import java.util.Map;

public abstract class MapExample<V> implements Runnable {

    protected BufferedReader reader;
    protected Map<String, V> storage;

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public void setStorage(Map<String, V> storage) {
        this.storage = storage;
    }
}
