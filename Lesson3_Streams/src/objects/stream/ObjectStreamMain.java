package objects.stream;

import java.io.*;

public class ObjectStreamMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person p1 = new Person("p1");
        Person p2 = new Person("p2");
        Person p3 = new Person("p3");

        p1.friend = p3;
        p2.friend = p3;

        p1.age = 25;

        System.out.println(p1);
        System.out.println(p2);
//

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("objects.txt"));
        out.writeObject(p1);
        out.writeObject(p2);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("objects.txt"));
        Person p4 = (Person) in.readObject();
        Person p5 = (Person) in.readObject();
        in.close();

        System.out.println(p4);
        System.out.println(p5);
    }

}
