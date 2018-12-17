package dz.task2;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    public static <T> List<T> transform(T[] array) {
        List<T> list = new ArrayList<>();
        for (T value : array) {
            list.add(value);
        }
        return list;
    }


    public static void main(String[] args) {
//        String[] strArr = {"s", "t", "r"};
        Integer[] intArr = {1, 2, 3};
//        List<String> strList = transform(strArr);
        List<Integer> strList = transform(intArr);
        System.out.println(strList.getClass().getSimpleName() + " - " + strList);
    }

}
