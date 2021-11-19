package rocks.spaghetti.kindletest;

import org.apache.log4j.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Log {
    private Log() {
        throw new IllegalStateException("Utility Class");
    }

    public static final Logger LOGGER = Logger.getLogger(Log.class.getCanonicalName());
    static {
        try {
            Layout layout = new PatternLayout("(%d{HH:mm:ss}) [%t/%p]: %m%n");
            FileAppender appender = new FileAppender(layout, "/var/log/kindletest.log", true);
            LOGGER.addAppender(appender);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    public static void info(Object msg) {
        LOGGER.info(msg);
    }

    public static void warn(Object msg) {
        LOGGER.warn(msg);
    }

    public static void error(Object msg) {
        LOGGER.error(msg);
    }

    public static void catching(Throwable t) {
        LOGGER.error("Catching: ", t);
    }
}
