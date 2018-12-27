package collections.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Supplier;

public class ConcurrentHashMap {

    public static final int THREADS = 10;
    public static final int TASK_COUNT = 60;

    static {
        executorService = Executors.newFixedThreadPool(THREADS);
    }

    private static ExecutorService executorService;

    public static void main(String[] args) throws Exception {
        try {
            test(Example1::new, new java.util.concurrent.ConcurrentHashMap<>() {{
                put("test", 0L);
                put("hex", 0L);
            }});

            test(Example2::new, new java.util.concurrent.ConcurrentHashMap<>() {{
                put("test", 0L);
                put("hex", 0L);
            }});
//
            test(Example3::new, new java.util.concurrent.ConcurrentHashMap<>());
//
            test(Example4::new, new java.util.concurrent.ConcurrentHashMap<>());
        } finally {
            executorService.shutdown();
        }

    }

    private static <V> void test(Supplier<MapExample<V>> exampleFactory, Map<String, V> storage) throws Exception {
        try (BufferedReader reader = new BufferedReader(createReader())) {
            MapExample<V> example = exampleFactory.get();
            example.setReader(reader);
            example.setStorage(storage);

            List<Future<?>> futures = new ArrayList<>();

            for (int i = 0; i < TASK_COUNT; i++) {
                futures.add(executorService.submit(example));
            }

            Thread.sleep(1000);

            for (Future<?> future : futures) {
                future.get();
            }

            System.out.println("------------------------");
            System.out.println(example.getClass().getSimpleName());
            System.out.println("'test' count is " + storage.get("test"));//28
            System.out.println("'hex' count is " + storage.get("hex"));//32

        }
    }

    private static InputStreamReader createReader() {
        ClassLoader classLoader = ConcurrentHashMap.class.getClassLoader();
        return new InputStreamReader(classLoader.getResourceAsStream("map_test.txt"));
    }

}
