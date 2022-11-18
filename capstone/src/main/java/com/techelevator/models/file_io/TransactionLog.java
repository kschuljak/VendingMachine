package com.techelevator.models.file_io;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;



public class TransactionLog extends Logger {

    public TransactionLog()
    {
        super();
    }

    private static final String fileType = ".txt";

    private static String directory = "logs/transactions";

    public static void createLogEntry(String message)
    {
        String today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String timePattern = "hh:mm:ss a";
        String now = LocalTime.now().format(DateTimeFormatter.ofPattern(timePattern));

        String file;




    }
}
