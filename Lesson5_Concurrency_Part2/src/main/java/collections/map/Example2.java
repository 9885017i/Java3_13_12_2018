package collections.map;

import java.io.IOException;

public class Example2 extends MapExample<Long> {

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

        Long oldValue;
        Long newValue;

        do {
            oldValue = storage.get(word);
            newValue = oldValue + 1;

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (!storage.replace(word, oldValue, newValue));
    }
}
