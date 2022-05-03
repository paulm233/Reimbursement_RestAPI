package dev.marmo.loggertests;

import dev.marmo.utilities.LogLevel;
import dev.marmo.utilities.Logger;
import org.junit.jupiter.api.Test;

public class LoggerTests {

    @Test
    void info_log_test(){
        Logger.log("Hello", LogLevel.INFO);
        Logger.log("Wassup", LogLevel.DEBUG);
    }
}
