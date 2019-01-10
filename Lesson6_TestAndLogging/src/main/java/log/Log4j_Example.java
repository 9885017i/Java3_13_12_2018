//package log;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class Log4j_Example {
//
//    private static final Logger log = LogManager.getLogger(Log4j_Example.class);
//
//    public static void main(String[] args)
//    {
//        if (log.isTraceEnabled()) {
//            log.trace("Some long-running operation returned {}", expensiveOperation());
//        }
//
//
//        log.debug("Debug Message Logged !!!");
//        String name = "Oleg";
//        log.info("Info Message Logged !!! My name is " + name);
//        log.warn("Info Message Logged !!! My name is {}, age is {}", "Oleg", "25");
//
//        log.error("Error Message Logged !!!", new NullPointerException("NullError"));
//    }
//
//    private static Throwable expensiveOperation() {
//        return null;
//    }
//}
