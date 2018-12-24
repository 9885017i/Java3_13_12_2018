package dz;

import java.io.FileInputStream;
import java.io.IOException;

public class Task1 {

    public static void main(String[] args) throws IOException {
        try (FileInputStream in = new FileInputStream("dz_task1.txt")) {
            System.out.println("Available: " + in.available());
            byte[] bytes = in.readAllBytes();
            System.out.println("Read " + bytes.length + " bytes");
            for (byte aByte : bytes) {
                System.out.print(aByte + " ");
            }
        }
    }
}
