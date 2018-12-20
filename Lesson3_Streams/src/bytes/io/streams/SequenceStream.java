package bytes.io.streams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;

public class SequenceStream {

    public static void main(String[] args) throws IOException {
        FileInputStream in1 = null, in2 = null;
        SequenceInputStream seq = null;
        FileOutputStream out = null;

        try {
            in1 = new FileInputStream("12345.txt");
            in2 = new FileInputStream("newFile.txt");
            seq = new SequenceInputStream(in1, in2);


            out = new FileOutputStream("out.txt");

            int rb;
            while ( (rb = seq.read()) != -1 ) {
                out.write(rb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            seq.close();
        }


    }
}
