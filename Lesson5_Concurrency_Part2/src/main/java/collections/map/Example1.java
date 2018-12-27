package collections.map;

import java.io.IOException;

public class Example1 extends MapExample<Long> {

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

        Long oldValue = storage.get(word);
        Long newValue = oldValue + 1;

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        storage.put(word, newValue);
    }
}
