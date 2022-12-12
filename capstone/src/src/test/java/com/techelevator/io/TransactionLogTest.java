package com.techelevator.io;

import com.techelevator.io.logs.TransactionLog;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class TransactionLogTest
{
    TransactionLog transactionLog;
    String today;

    @Before
    public void setup()
    {
        transactionLog = new TransactionLog();
        today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Test
    public void createLogEntry_ShouldCreateLogFile()
    {
        // arrange
        String fileType = ".txt";
        String path = "logs/transactions";
        File outputFile = new File(path + "/" + today + fileType);

        // act
        TransactionLog.createLogEntry("test");
        boolean actual = outputFile.exists();

        // assert
        assertTrue("Method should create a log file with the name of the current date + .txt extension",actual);
    }

//    @Test
//    public void createLogEntry_ShouldCreateLogFileWithTransactionInformation()
//    {
//        // arrange
//        String fileType = ".txt"
//        String path = "logs/transactions";
//        File log = new File(path + "/" + today + fileType);
//
//        // act
//
//        TransactionLog.createLogEntry("");
//        try (Scanner reader = new Scanner(log))
//        {
//            String actual = reader.nextLine();
//        }
//        catch (FileNotFoundException ex)
//        {
//            System.out.println(ex.getMessage());
//        }
//
//        // assert
//    }
}
