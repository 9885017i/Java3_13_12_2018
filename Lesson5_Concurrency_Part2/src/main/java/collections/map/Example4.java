package collections.map;

import java.io.IOException;

public class Example4 extends MapExample<Long> {

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

        storage.merge(word,1L, Long::sum);
    }
}
