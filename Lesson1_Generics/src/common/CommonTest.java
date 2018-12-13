package common;

public class CommonTest {

    public static void main(String[] args) {
//        testIntStorage();
//        testStringStorage();
        testCommonStorage();
    }

    private static void testIntStorage() {
        IntStorage storage = new IntStorage(5);
        storage.add(1);
        storage.add(2);
        storage.add(3);

        System.out.println("Find 2: " + storage.find(2));

        storage.display();

        int valueLast = storage.get(storage.getCurrentSize() - 1);
        System.out.println("Last value: " + valueLast);
    }

    private static void testStringStorage() {
        StringStorage storage = new StringStorage(5);
        storage.add("1");
        storage.add("2");
        storage.add("3");


        System.out.println("Find 2: " + storage.find("2"));

        storage.display();

        String valueLast = storage.get(storage.getCurrentSize() - 1);
        System.out.println("Last value: " + valueLast);
    }


    private static void testCommonStorage() {
        Storage strStorage = new CommonStorage(5);
        strStorage.add("1");
        strStorage.add("2");
        strStorage.add("3");
//        strStorage.add(4);

        System.out.println("Find 2: " + strStorage.find("2"));

        strStorage.display();

        String valueLast = (String) strStorage.get(strStorage.getCurrentSize() - 1);
        System.out.println("Last value: " + valueLast);

        Storage intStorage = new CommonStorage(3);
        intStorage.add(1);
        intStorage.add(2);
        intStorage.add(3);

        intStorage = strStorage;

        int intValue = (int) intStorage.get(0);
        System.out.println("First int value is " + intValue);
    }
}
