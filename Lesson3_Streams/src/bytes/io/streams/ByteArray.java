package bytes.io.streams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteArray {

    public static void main(String[] args) throws IOException {
        readByteArray();
        System.out.println();
        writeByteArray();

    }

    private static void writeByteArray() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(10);
        out.write(11);
        byte[] bytes = out.toByteArray();
        for (byte aByte : bytes) {
            System.out.print(aByte + " ");
        }
    }

    private static void readByteArray() throws IOException {
        byte[] arr = {100, 25, 50};
        InputStream is = new ByteArrayInputStream(arr);
        int x;
        while ( (x = is.read()) != -1 ) {
            System.out.print(x + " ");
        }
    }
}
