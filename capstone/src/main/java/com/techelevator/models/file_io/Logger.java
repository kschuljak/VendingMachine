package com.techelevator.models.file_io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static String fileType = ".log";

    private static String directory = "logs";

    public Logger(String directory)
    {
        this.directory = directory;
    }

    public static void createLogEntry(String message)
    {
        String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String file = directory + "/" + today + fileType;
        String timePattern = "hh:mm:ss a";
        File logFile = new File(file);

        try (FileWriter fileWriter = new FileWriter(logFile, true);
             PrintWriter printWriter = new PrintWriter(fileWriter))
        {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(timePattern));
            printWriter.println(today + " " + time + " " + message);

        } catch (IOException ex) {System.out.println(ex.getMessage());}
    }

    public Logger() {

    }
}
