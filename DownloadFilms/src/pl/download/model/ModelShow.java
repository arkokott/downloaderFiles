package pl.download.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Artur Kokott
 */
public class ModelShow {

    public String substring(int beginIndex, int endIndex) {
        return "";
    }

    public String readFile() throws FileNotFoundException {
        File file = new File("d:\\Pobrane\\vcd.txt");
        Scanner in = new Scanner(file);
        String textInFile = in.nextLine();

        textInFile = textInFile.substring(26, textInFile.length() - 4);

        return textInFile;
    }

    public String divisionLine() throws FileNotFoundException {
        String allText = readFile();
        String allLineForName;
        String numberFile;
        String nameFile;
        String numberAndNameFile = "";
        List<String> listFull = new LinkedList<String>();
        List<String> listNumber = new LinkedList<String>();
        List<String> listName = new LinkedList<String>();
        
        listFull = Arrays.asList(allText.split("\"},\""));

        for (String oneElement : listFull) {

            listNumber = Arrays.asList(oneElement.split("\":\\{\"nazwa\":\""));

            //allLineForName = oneElement.substring(20);
            allLineForName = listNumber.get(1);
            listName = Arrays.asList(allLineForName.split("\","));

            numberFile = listNumber.get(0);
            nameFile = listName.get(0);

            numberAndNameFile += numberFile + " - " + nameFile + "\n";
        }

        return numberAndNameFile;
    }
}
