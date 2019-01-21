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
        String lessNumber, lessName, lessSize;
        String numberFile, nameFile, sizeFile, linkFile;
        String result = "";
        List<String> listFull = new LinkedList<String>();
        List<String> listNumber = new LinkedList<String>();
        List<String> listName = new LinkedList<String>();
        List<String> listSize = new LinkedList<String>();
        List<String> listLink = new LinkedList<String>();
        
        listFull = Arrays.asList(allText.split("\"},\""));

        for (String oneElement : listFull) {

            listNumber = Arrays.asList(oneElement.split("\":\\{\"nazwa\":\""));

            lessNumber = listNumber.get(1);
            listName = Arrays.asList(lessNumber.split("\",\"rozmiar\":\""));
            
            lessName = listName.get(1);
            listSize = Arrays.asList(lessName.split("\",\"url\":\""));
            
            lessSize = listSize.get(1);
            listLink = Arrays.asList(lessSize.split("\",\"gfx\":\""));

            numberFile = listNumber.get(0);
            nameFile = listName.get(0);
            sizeFile = listSize.get(0);
            linkFile = listLink.get(0).replace("\\/", "/"); //nie wyświetlane w result

            result += numberFile + " - " + nameFile + " - " + sizeFile + "MB\n";
        }

        return result;
    }
}
