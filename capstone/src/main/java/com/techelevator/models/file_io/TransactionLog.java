package com.techelevator.models.file_io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;



public class TransactionLog extends Logger {

    public TransactionLog()
    {
        super();
    }

    private static final String fileType = ".txt";

    private static String directory = "C:\\Users\\Student\\workspace\\module-1-week-4-pair-8\\capstone\\logs\\transactions";

    public static void createLogEntry(String message)
    {
        String today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String timePattern = "hh:mm:ss a";
        String now = LocalTime.now().format(DateTimeFormatter.ofPattern(timePattern));
        String file = directory + "/" + today + fileType;

        File logFile = new File(file);

        try (FileWriter writer = new FileWriter(logFile, true);
             PrintWriter print = new PrintWriter(writer))
        {
            print.println(today + " " + now + " " + message);
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
