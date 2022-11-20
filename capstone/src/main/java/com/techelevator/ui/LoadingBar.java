package com.techelevator.ui;

import com.techelevator.models.file_io.Logger;

public class LoadingBar
{
    public static void displayLoadingBar()
    {
        System.out.println("\n-Dispensing-");

        for (int i = 0; i <= 100; i = i + 10)
        {
            populateBar(i, 100);

            try
            {
                Thread.sleep(250);
            }
            catch (InterruptedException exception)
            {
                Logger.createLogEntry(exception.getMessage());
            }
        }
        System.out.println();
    }

    public static void populateBar(int remainingTime, int total)
    {
        int barSize = 10;
        int remainingPercent = ((100 * remainingTime) / total) / barSize;
        char barFiller = '.';
        String bar = new String(new char[barSize]).replace('\0', ' ') + "]";
        StringBuilder fullBar = new StringBuilder();
        fullBar.append("[");
        for (int j = 0; j < remainingPercent; j++)
        {
            fullBar.append(barFiller);
        }
        String remainingBar = bar.substring(remainingPercent);
        System.out.print("\r" + fullBar + remainingBar + " ");
    }
}
