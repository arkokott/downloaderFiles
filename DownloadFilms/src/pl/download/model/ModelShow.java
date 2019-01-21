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
        List<String> splitedArrayList = new LinkedList<String>();
        List<String> splitedOneLineName = new LinkedList<String>();

        splitedArrayList = Arrays.asList(allText.split("\"},\""));

        for (String splitedArrayListOneElement : splitedArrayList) {
            
            List<String> splitedOneLineNumber = Arrays.asList(splitedArrayListOneElement.split("\":"));

            allLineForName = splitedArrayListOneElement.substring(20);
            splitedOneLineName = Arrays.asList(allLineForName.split("\","));

            numberFile = splitedOneLineNumber.get(0);
            nameFile = splitedOneLineName.get(0);

            numberAndNameFile += numberFile + " - " + nameFile + "\n";
        }

        return numberAndNameFile;
    }
}
