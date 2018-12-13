package generics;

import common.CommonStorage;
import common.Storage;
import generics.utils.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenericTest {

    public static void main(String[] args) {
//        IStorage<String> strStorage = new StorageImpl<>(5);
//        strStorage.add("1");
//        strStorage.add("2");
//        strStorage.add("3");
////        strStorage.add(4);
//
//        System.out.println("Find 2: " + strStorage.find("2"));
//
//        strStorage.display();
//
//        String valueLast = (String) strStorage.get(strStorage.getCurrentSize() - 1);
//        System.out.println("Last value: " + valueLast);

//        IStorage<Integer> intStorage = new StorageImpl<Integer>(String.class, 3);
        IStorage<Integer> intStorage = new StorageImpl(3);
        intStorage.add(3);
        intStorage.add(2);
        intStorage.add(1);
        intStorage.display();
//        intStorage.add("3");

//        intStorage = strStorage;

//        int intValue = intStorage.get(0);
//        System.out.println("First int value is " + intValue);

        System.out.println("Sort");
        intStorage.sort();
        intStorage.display();

//        Pair<Integer, IllegalArgumentException> pair = intStorage.indexOf(33);
//        if (pair.hasError()) {
//            throw pair.getError();
//        }
//        else {
//            System.out.println("Index for '3' is " + pair.getValue());
//        }

        List<Integer> values = new ArrayList<>();
        values.add(777);
        values.add(888);

        List<String> strValues = CollectionUtils.transform(values, String::valueOf);
//        List<String> strValues = CollectionUtils.transform(values, value -> String.valueOf(value));
//        List<String> strValues = CollectionUtils.transform(values, new Transformer<Integer, String>() {
//            @Override
//            public String transform(Integer value) {
//                return String.valueOf(value);
//            }
//        });

        for (String strValue : strValues) {
            System.out.println(strValue);
        }

        IStorage<LocalDate> dateStorage = new StorageImpl(3);
        dateStorage.add(LocalDate.now());
        dateStorage.add(LocalDate.ofYearDay(2017, 1));

        dateStorage.display();
        dateStorage.sort();
        dateStorage.display();
    }




}
