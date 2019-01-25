package pl.download.model;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Artur Kokott
 */
public class ModelShow {

    /**
     * Reads the file and passes its contents on. Checks if the file exists.
     *
     * @param numberFile is number file to read
     * @throws java.io.FileNotFoundException this is exception
     * @return file content
     */
    private String readFile(int numberFile) throws FileNotFoundException, IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        File file = new File(tempDir + "\\lista_" + numberFile + ".txt");
        String textFromFile = "";

        if (file.exists() && !file.isDirectory()) {
            Scanner in = new Scanner(file);
            textFromFile = in.nextLine();
            in.close();
        } else {
            JOptionPane.showMessageDialog(null, "Brak pliku.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return textFromFile;
    }

    /**
     * Reads the file, create new file, combines individual files into one and
     * passes its contents on. Checks if the file exists.
     *
     * @param numberFile number of file to open and read
     * @param lastFile number to compare with numberFile
     * @throws java.io.FileNotFoundException this is exception
     */
    private void readAndSaveFile(int numberFile, int lastFile) throws FileNotFoundException, IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        File file = new File(tempDir + "\\lista_" + numberFile + ".txt");
        String textFromFile = "";
        if (file.exists() && !file.isDirectory()) {
            Scanner in = new Scanner(file);
            textFromFile = in.nextLine();
            in.close();
        } else {
            JOptionPane.showMessageDialog(null, "Brak pliku.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        List<String> listAllText = new LinkedList<String>();

        if (numberFile == 1) {
            //dla pierwsze pliku, wycinamy tylko koniec
            File newfile = new File(tempDir + "\\lista_0.txt");
            newfile.createNewFile();
            textFromFile = textFromFile.substring(0, textFromFile.length() - 4);

            PrintWriter output = new PrintWriter(tempDir + "lista_0.txt");
            output.print(textFromFile + "\"},\"");
            output.close();
        } else if (numberFile == lastFile) {
            //dla ostatniego wycinamy tylko poczatek

            listAllText = Arrays.asList(textFromFile.split("array"));
            textFromFile = listAllText.get(1).substring(4, listAllText.get(1).length());

            //textFromFile = textFromFile.substring(26, textFromFile.length());
            Writer output = new BufferedWriter(new FileWriter(tempDir + "lista_0.txt", true));
            output.append(textFromFile);
            output.close();
        } else {
            //tutaj dla srodkowych plikow, wycinamy poczatek i koniec

            listAllText = Arrays.asList(textFromFile.split("array"));
            textFromFile = listAllText.get(1).substring(4, listAllText.get(1).length() - 4);

            //textFromFile = textFromFile.substring(26, textFromFile.length() - 4);
            Writer output = new BufferedWriter(new FileWriter(tempDir + "lista_0.txt", true));
            output.append(textFromFile + "\"},\"");
            output.close();
        }
    }

    /**
     * Divided the content received from readFile(). Splited value, create
     * listed and put data.
     *
     * @param option value passed to readFile()
     * @return value from map and number page
     * @throws java.io.FileNotFoundException this is exception
     */
    public String divisionLine(int option) throws FileNotFoundException, IOException {
        String allText = readFile(option);
        //sprawdzenie czy allText jest puste
        if (allText == null || allText == "") {
            //System.err.println("File not found and return null.");
            return "error";
        } else {
            List<String> listAllText = new LinkedList<String>();

            String[] allPages = allText.substring(10).split("\",\"");

            listAllText = Arrays.asList(allText.split("array"));

            if (listAllText.get(1).substring(2, listAllText.get(1).length()).equals("[]}")) {
                return "emptyValue";
            } else {
                allText = listAllText.get(1).substring(4, listAllText.get(1).length() - 4);

                String lessNumber, lessName, lessSize;
                String numberFile, nameFile, sizeFile, linkFile;
                String result = "";
                Integer key = 1;
                List<String> listFull = new LinkedList<String>();
                List<String> listNumber = new LinkedList<String>();
                List<String> listName = new LinkedList<String>();
                List<String> listSize = new LinkedList<String>();
                List<String> listLink = new LinkedList<String>();
                NavigableMap<Object, Object> map = new TreeMap<>().descendingMap();

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

                    key = Integer.parseInt(numberFile);

                    //result += numberFile + " - " + nameFile + " - " + sizeFile + "MB\n";
                    map.put(key, numberFile + " - " + nameFile + " - " + sizeFile + "MB");
                }

                for (Entry<Object, Object> ent : map.entrySet()) {
                    result += ent.getValue() + "\n";
                }
                return allPages[0] + "!@" + result;
            }
        }
    }

    /**
     * Downlaod files from www. Check if exist file with cookie hash.
     *
     * @param searchWord searched phrase
     * @param page number to download file
     * @throws java.net.MalformedURLException this is exception
     */
    public void downloadFile(String searchWord, int page) throws MalformedURLException, IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] buffer = new byte[2048];
        int length;
        String tempDir = System.getProperty("java.io.tmpdir");

        //read cookie from file
        String getCookie = "";
        String path = new File(".").getCanonicalPath();
        File cookie = new File(path + "\\cookie.txt");
        if (cookie.exists() && !cookie.isDirectory()) {
            Scanner in = new Scanner(cookie);
            getCookie = in.nextLine();
            in.close();
        } else {
            JOptionPane.showMessageDialog(null, "Brak pliku z cookie.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        //end read cookie from file

        String urlParameters = "a=doSearch&query=" + searchWord + "&hosting=&page=" + page;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        String url = "https://filebit.pl/includes/ajax.php";
        HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
        httpConnection.setDoOutput(true); // Triggers POST.
        httpConnection.setRequestMethod("POST");

        httpConnection.setRequestProperty("User-Agent", "-");
        httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        //httpConnection.setRequestProperty("Cookie", "PHPSESSID=nj20drdosubqqqdl5hhk0n8jp6");
        httpConnection.setRequestProperty("Cookie", "PHPSESSID=" + getCookie);
        httpConnection.setRequestProperty("Accept", "*/*");

        httpConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        httpConnection.setUseCaches(false);
        try (DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream())) {
            wr.write(postData);
        }

        inputStream = httpConnection.getInputStream();
        outputStream = new FileOutputStream(tempDir + "\\lista_" + page + ".txt");

        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.close();
        inputStream.close();
    }

    /**
     *
     * @param searchWord
     * @param page number gived by user
     * @param pageMax number to compare with page
     * @return data from file
     * @throws IOException this is exception
     */
    public String showAllResults(String searchWord, Integer page, Integer pageMax) throws IOException {
        String all;

        if (page > pageMax) {
            JOptionPane.showMessageDialog(null, "Podana liczba jest większa od maksymalnej.", "Info", JOptionPane.INFORMATION_MESSAGE);
            all = divisionLine(1);
        } else if (page <= 1) {
            all = divisionLine(1);
        } else {
            for (int i = 2; i <= page; i++) {
                downloadFile(searchWord, i);
            }

            for (int i = 1; i <= page; i++) {
                readAndSaveFile(i, page);
            }

            all = divisionLine(0);

        }
        return all;
    }
}
