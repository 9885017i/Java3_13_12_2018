package collections.map;

import java.io.IOException;
import java.util.concurrent.atomic.LongAdder;

public class Example3 extends MapExample<LongAdder> {

    @Override
    public void run() {
        String word;
        try {
            word = reader.readLine();
            if (word == null)
                return;
        } catch (IOException e) {
            return;
        }

        storage.computeIfAbsent(word, (k) -> new LongAdder()).increment();
    }
}
