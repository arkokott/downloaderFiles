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

    public String link(String fileNumber, int radioSelected) {
        int numberSrv = rand.nextInt(23) + 1;
        String link = "";
        Integer fileNumberToMD5 = Integer.parseInt(fileNumber) + rand.nextInt(99) + 1;

        String hashFile = getMd5(fileNumberToMD5.toString());

        if (radioSelected == 0) {
            link = "http://s" + numberSrv + ".filebit.pl/plik-" + hashFile + "/RD/" + fileNumber + "/40526/" + fileNumberToMD5 + ".rar";
        } else {
            link = "wget -U - -S --content-disposition http://s" + numberSrv + ".filebit.pl/plik-" + hashFile + "/RD/" + fileNumber + "/40526/" + fileNumberToMD5 + ".rar";
        }

        return link;
    }

}
