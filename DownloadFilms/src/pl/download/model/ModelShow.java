package pl.download.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Artur Kokott
 */
public class ModelShow {

    /*    public String read() throws FileNotFoundException {
        File file = new File("d:\\Pobrane\\vcd.txt");
        Scanner in = new Scanner(file);
        String textInFile = in.nextLine();
        return textInFile;
    }*/
    public String[] read() throws FileNotFoundException {
        File file = new File("d:\\Pobrane\\vcd.txt");
        Scanner in = new Scanner(file);
        String textInFile = in.nextLine();

        String[] splitedArray = null;
        splitedArray = textInFile.split("\"},\"");
        

        return splitedArray;
    }
}
