package pl.download.model;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    
    private List<String> splitedArrayList = new ArrayList<>();
    
    /**
     *
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public String substring(int beginIndex) {
        return null;
    }
    
    public List<String> read() throws FileNotFoundException{
        File file = new File("d:\\Pobrane\\vcd.txt");
        Scanner in = new Scanner(file);
        String textInFile = in.nextLine();
        
        textInFile = textInFile.substring(26);
        
        
        
        

        splitedArrayList = Arrays.asList(textInFile.split("\"},\""));
        
        
        

        return splitedArrayList;
    }
}
