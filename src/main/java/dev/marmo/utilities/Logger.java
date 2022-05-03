package dev.marmo.utilities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Logger {

    public static void log(String message, LogLevel level){
        // Log level + message + timestamp
        String logMessage = level.name() +" " + message + " " + new Date() + "\n";

        try{
            Files.write(Paths.get("C:\\users\\Paul\\Desktop\\JavaProjects\\ReimbursementRestAPI\\applogs.log"),
                    logMessage.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
