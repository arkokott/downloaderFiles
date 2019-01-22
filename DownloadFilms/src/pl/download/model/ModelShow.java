package pl.download.model;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

    private String readFile(String page) throws FileNotFoundException, IOException {
        File file = new File("d:\\Pobrane\\lista_" + page + ".txt");
        Scanner in = new Scanner(file);
        String textInFile = in.nextLine();

        textInFile = textInFile.substring(26, textInFile.length() - 4);

        return textInFile;
    }

    public String divisionLine(String searchWord, String page) throws FileNotFoundException, IOException {
        //downloadFile(searchWord, page);
        
        String allText = readFile(page);
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

    private void downloadFile(String searchWord, String page) throws MalformedURLException, IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] buffer = new byte[2048];
        int length;

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
        httpConnection.setRequestProperty("Cookie", "PHPSESSID=sijc5liid66b52rdtd4f316ji4");
        httpConnection.setRequestProperty("Accept", "*/*");

        httpConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        httpConnection.setUseCaches(false);
        try (DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream())) {
            wr.write(postData);
        }

        inputStream = httpConnection.getInputStream();
        outputStream = new FileOutputStream("D:\\Pobrane\\lista_" + page + ".txt");

        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.close();
        inputStream.close();
    }
}
