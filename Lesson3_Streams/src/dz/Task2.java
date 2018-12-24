package dz;

import java.io.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Task2 {

    private static final String FILE_1 = "dz_task2_1.txt";
    private static final String FILE_2 = "dz_task2_2.txt";
    private static final String FILE_3 = "dz_task2_3.txt";
    private static final String FILE_4 = "dz_task2_4.txt";
    private static final String FILE_5 = "dz_task2_5.txt";

    public static void main(String[] args) throws IOException {
        Enumeration<InputStream> files = Collections.enumeration(List.of(
                new FileInputStream(FILE_1),
                new FileInputStream(FILE_2),
                new FileInputStream(FILE_3),
                new FileInputStream(FILE_4),
                new FileInputStream(FILE_5)
        ));
        SequenceInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new SequenceInputStream(files);
            out = new BufferedOutputStream(new FileOutputStream("dz_task2_full.txt"));

            int data;
            while ( (data = in.read()) != -1 ) {
                out.write(data);
            }
        } finally {
            in.close();
            out.close();
        }
    }
}