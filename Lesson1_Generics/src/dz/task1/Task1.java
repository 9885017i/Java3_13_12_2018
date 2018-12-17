package dz.task1;

public class Task1 {

    public static <T> void swapElements(T[] array, int index1, int index2) {
        if (array == null)
            throw new IllegalArgumentException("Array is required");

        checkIndex(array, index1);
        checkIndex(array, index2);

        swap(array, index1, index2);
    }

    private static <T> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static <T> void checkIndex(T[] array, int index) {
        if (index < 0 || index >= array.length)
            throw new IllegalArgumentException("Invalid index");
    }


    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3};
        display(intArray);
        swapElements(intArray, 0, 2);
        display(intArray);

        String[] strArray = {"s", "t", "r"};
        display(strArray);
        swapElements(strArray, 0, 1);
        display(strArray);
    }

    private static void display(Object[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        System.out.println("---------");
    }

}
