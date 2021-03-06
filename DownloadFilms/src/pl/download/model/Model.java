package pl.download.model;

import java.math.BigInteger;
import java.util.Random;
import java.security.*;

/**
 *
 * @author Artur Kokott
 */
public class Model {

    Random rand = new Random();

    /**
     * Create hash in MD5 needed to create a link.
     *
     * @param input string from which it is created hash
     * @return md5 hash
     */
    public static String getMd5(String input) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate link to download file. Checks if generate with wget or no.
     *
     * @param fileNumber is number file which we want download
     * @param checkBoxWgetSelected option wget
     * @return ready link to download
     */
    public String link(String fileNumber, int checkBoxWgetSelected) {
        int numberSrv = rand.nextInt(23) + 1;
        String link = "";
        Integer fileNumberToMD5 = Integer.parseInt(fileNumber) + rand.nextInt(99) + 1;

        String hashFile = getMd5(fileNumberToMD5.toString());

        if (checkBoxWgetSelected == 0) {
            link = "http://s" + numberSrv + ".filebit.pl/plik-" + hashFile + "/RD/" + fileNumber + "/40526/" + fileNumberToMD5 + ".rar";
        } else {
            link = "wget -U - -S --content-disposition http://s" + numberSrv + ".filebit.pl/plik-" + hashFile + "/RD/" + fileNumber + "/40526/" + fileNumberToMD5 + ".rar";
        }

        return link;
    }

}
