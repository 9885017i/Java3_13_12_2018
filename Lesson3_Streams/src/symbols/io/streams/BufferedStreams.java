package symbols.io.streams;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BufferedStreams {

    public static void main(String[] args) throws IOException {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter("input.txt"))) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("input.txt"))) {
            for (int i = 0; i < 20; i++) {
                bw.write("Java");
                bw.newLine();
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        }

    }
}
