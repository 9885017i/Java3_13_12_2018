import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class Main3 {

    public static void main(String[] args) throws IOException {
        File file = new File("testFile.txt");
        System.out.println("Is exists:" + file.exists());
        System.out.println("Length:" + file.length());
        System.out.println("Name: " + file.getName());

        System.out.println("can write:" + file.canWrite());
        System.out.println("last modified:" + new Date(file.lastModified()));

        System.out.println("Is dir: " + file.isDirectory());
        System.out.println("Is file: " + file.isFile());

        System.out.println(file.getAbsoluteFile().getParent());

        System.out.println("Parent name:" + file.getAbsoluteFile().getParentFile().getName());

        File testDir = new File("testDir");
        testDir.mkdir();

        for (File fileInDir : testDir.listFiles()) {
            System.out.println(fileInDir.getName());
        }

        Path path = Paths.get(".", "testDir2");

        Files.createDirectory(path);



    }
}
