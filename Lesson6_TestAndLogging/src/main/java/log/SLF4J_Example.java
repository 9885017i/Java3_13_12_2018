package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class SLF4J_Example {

    private static Logger logger = LoggerFactory.getLogger(SLF4J_Example.class);

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = SLF4J_Example.class.getClassLoader();
        LogManager.getLogManager().readConfiguration(classLoader.getResourceAsStream("jul.properties"));

        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.info("{}, {}!", "Hello", "World");
        logger.error("Error log message");
    }
}