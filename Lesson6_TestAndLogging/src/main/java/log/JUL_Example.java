package log;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class JUL_Example {

    private static final Logger LOGGER = Logger.getLogger(JUL_Example.class.getName());

    public static void main(String[] args) throws SecurityException, IOException {
        ClassLoader classLoader = JUL_Example.class.getClassLoader();
        LogManager.getLogManager().readConfiguration(classLoader.getResourceAsStream("jul.properties"));

        LOGGER.info("Logger Name: " + LOGGER.getName());

        LOGGER.log(Level.FINEST,"Logger Name: {0}. My name is {1}", new Object[]{LOGGER.getName(), "Oleg"});

        LOGGER.warning("Can cause ArrayIndexOutOfBoundsException");

        //An array of size 3
        int[] a = {1, 2, 3};
        int index = 4;
        LOGGER.info("a = " + Arrays.toString(a));
        LOGGER.warning("Can cause ArrayIndexOutOfBoundsException");
        LOGGER.config("index is set to " + index);

        try {
            System.out.println(a[index]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            LOGGER.log(Level.SEVERE, "Exception occur", ex);
        }
    }

}
