package com.techelevator.ui;

import com.sun.jdi.Method;

import java.util.HashMap;
import java.util.Map;

public class Spinner {

    public static String[] product = new String[]{
            "\r  %%%%%                 ",
            "\r   %%%%%                ",
            "\r    %%%%%               ",
            "\r     %%%%%              ",
            "\r      %%%%%             ",
            "\r       %%%%%            ",
            "\r        %%%%%           ",
            "\r         %%%%%          ",
            "\r          %%%%%         ",
            "\r           %%%%%        ",
            "\r            %%%%%       ",
            "\r             %%%%%      ",
            "\r              %%%%%     ",
            "\r               %%%%%    ",
            "\r                %%%%%   ",
            "\r                 %%%%%  ",
            "\r                  %%%%% ",
            "\r                   %%%%%"
    };

    public static String[] spinnerLine1 = new String[]{
            "\r  .-.-.   .-.-.   .",
            "\r   .-.-.   .-.-.   ",
            "\r.   .-.-.   .-.-.  ",
            "\r-.   .-.-.   .-.-. ",
            "\r.-.   .-.-.   .-.-.",
            "\r-.-.   .-.-.   .-.-",
            "\r.-.-.   .-.-.   .-.",
            "\r .-.-.   .-.-.   .-",
            "\r  .-.-.   .-.-.   .",
            "\r  .-.-.   .-.-.   .",
            "\r   .-.-.   .-.-.   ",
            "\r.   .-.-.   .-.-.  ",
            "\r-.   .-.-.   .-.-. ",
            "\r.-.   .-.-.   .-.-.",
            "\r-.-.   .-.-.   .-.-",
            "\r.-.-.   .-.-.   .-.",
            "\r .-.-.   .-.-.   .-",
            "\r  .-.-.   .-.-.   ."
    };

    public static String[] spinnerLine2 = new String[]{
            "\r / / \\ \\ / / \\ \\ / ",
            "\r\\ / / \\ \\ / / \\ \\ /",
            "\r \\ / / \\ \\ / / \\ \\ ",
            "\r\\ \\ / / \\ \\ / / \\ \\",
            "\r \\ \\ / / \\ \\ / / \\ ",
            "\r/ \\ \\ / / \\ \\ / / \\",
            "\r / \\ \\ / / \\ \\ / / ",
            "\r/ / \\ \\ / / \\ \\ / /",
            "\r / / \\ \\ / / \\ \\ / ",
            "\r / / \\ \\ / / \\ \\ / ",
            "\r\\ / / \\ \\ / / \\ \\ /",
            "\r \\ / / \\ \\ / / \\ \\ ",
            "\r\\ \\ / / \\ \\ / / \\ \\",
            "\r \\ \\ / / \\ \\ / / \\ ",
            "\r/ \\ \\ / / \\ \\ / / \\",
            "\r / \\ \\ / / \\ \\ / / ",
            "\r/ / \\ \\ / / \\ \\ / /",
            "\r / / \\ \\ / / \\ \\ / "
    };

    public static String[] spinnerLine3 = new String[]{
            "\r`-'   `-`-'   `-`-'",
            "\r-`-'   `-`-'   `-`-",
            "\r`-`-'   `-`-'   `-`",
            "\r `-`-'   `-`-'   `-",
            "\r  `-`-'   `-`-'   `",
            "\r   `-`-'   `-`-'   ",
            "\r'   `-`-'   `-`-'  ",
            "\r-'   `-`-'   `-`-' ",
            "\r`-'   `-`-'   `-`-'",
            "\r`-'   `-`-'   `-`-'",
            "\r-`-'   `-`-'   `-`-",
            "\r`-`-'   `-`-'   `-`",
            "\r `-`-'   `-`-'   `-",
            "\r  `-`-'   `-`-'   `",
            "\r   `-`-'   `-`-'   ",
            "\r'   `-`-'   `-`-'  ",
            "\r-'   `-`-'   `-`-' ",
            "\r`-'   `-`-'   `-`-'"
    };

    public static void displaySpinner(){

        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();
        StringBuilder string3 = new StringBuilder();
        StringBuilder string4 = new StringBuilder();
        StringBuilder string5 = new StringBuilder();

        for (int i = 0; i < product.length; i++){
            string1.append(product[i]);
            string2.append(spinnerLine1[i]);
            string3.append(spinnerLine2[i]);
            string4.append(spinnerLine3[i]);
            string5.append(product[i]);

            System.out.println(string1);
            System.out.println(string2);
            System.out.println(string3);
            System.out.println(string4);
            System.out.println(string5);

            try
            {
                Thread.sleep(200);
            }
            catch (Exception ignored) {}
        }
    }

}

