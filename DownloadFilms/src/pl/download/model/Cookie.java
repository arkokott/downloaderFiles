package pl.download.model;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Artur Kokott
 */
public class Cookie {

    public void DownloadCookieFile() throws MalformedURLException, IOException {
        String path = new File(".").getCanonicalPath();
        String cookieToSplit = null;

        String urlParameters = "login=filebitinteriapl&password=file.bit@interia.pl&securityKey=1234";
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        String url = "https://filebit.pl/panel/login";
        HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
        httpConnection.setDoOutput(true); // Triggers POST.
        httpConnection.setRequestMethod("POST");

        httpConnection.setRequestProperty("User-Agent", "-");
        httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        httpConnection.setRequestProperty("Accept", "*/*");

        httpConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpConnection.setUseCaches(false);
        try (DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream())) {
            wr.write(postData);
        }

        cookieToSplit = httpConnection.getHeaderField(6);
        
        try (PrintWriter output = new PrintWriter(path + "\\cookie.txt")) {
            output.print(cookieToSplit.substring(10, cookieToSplit.length()-8));
        }
    }

}
