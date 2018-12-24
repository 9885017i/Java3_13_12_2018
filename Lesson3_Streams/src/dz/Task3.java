package dz;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Task3 {

    private static final int TEN_MB = 10 * 1024 * 1024;
    private static final int PAGE_SIZE = 1800;

    public static void main(String[] args) throws IOException {
        System.out.println("Starting app: " + now());
        File bigFile = new File("dz_task3.txt");
        if (!bigFile.exists()) {
            createNewBigFile(bigFile);
        }
        System.out.println("App is started: " + now());


        long pageCount = bigFile.length() / PAGE_SIZE;

        RandomAccessFile randomAccessFile = new RandomAccessFile(bigFile, "r");
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter page number");
                int pageNumber = in.nextInt();
                if (pageNumber < 0 || pageNumber > pageCount) {
                    System.out.println("Invalid page number. Max number is " + pageCount);
                    continue;
                }
                int position = PAGE_SIZE * (pageNumber);
                randomAccessFile.seek(position);
                System.out.println("Start read page: " + now());
                for (int i = 0; i < PAGE_SIZE / 4; i++) {
                    System.out.println(randomAccessFile.readInt());
                }
                System.out.println("Finish read page: " + now());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FINISH APP");
        } finally {
            randomAccessFile.close();
        }

    }

    private static String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:MM:ss"));
    }

    private static void createNewBigFile(File bigFile) throws IOException {
        bigFile.createNewFile();

        int fileSize = calculateFileSize();
        int data = 0;

        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(bigFile), TEN_MB));
        while (bigFile.length() < fileSize) {
            for (int i = 0; i < PAGE_SIZE / 4; i++) {
                out.writeInt(data);
            }
            data++;
            data %= 10;
        }
        out.close();
    }

    private static int calculateFileSize() {
        int fileSize = PAGE_SIZE;
        while (fileSize < TEN_MB) {
            fileSize += PAGE_SIZE;
        }
        return fileSize;
    }
}