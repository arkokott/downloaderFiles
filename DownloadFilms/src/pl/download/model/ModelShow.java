package pl.download.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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

    public String divisionLine() throws FileNotFoundException, IOException {
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

        downloadFile();
        return result;
    }

    public void downloadFile() throws MalformedURLException, IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] buffer = new byte[2048];
        int length;

        URL url = new URL("https://www.w3.org/Protocols/rfc2616/rfc2616.txt");

        URLConnection con = url.openConnection();

        inputStream = con.getInputStream();
        outputStream = new FileOutputStream("D:\\Pobrane\\test.txt");

        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.close();
        inputStream.close();
    }
}
