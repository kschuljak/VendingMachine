package com.techelevator.models.file_io;

public class ExceptionLog {

    private static final String FILE_TYPE = ".log";

    private String directory;

    public ExceptionLog(String directory)
    {
        this.directory = directory;
    }

    public static void logException(String message)
    {

    }
}
