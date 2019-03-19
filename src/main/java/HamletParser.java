import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thook on 10/7/15.
 */
public class HamletParser {
    private String hamletData;
    
    public static void main(String[] args) throws IOException {
        String hamletString = loadDataFromFile("/Users/markmoll/zcw/Regex-Hamlet-Parser/src/main/resources/hamlet.txt");
        String shakespeareGotLeoned = changeItAll(hamletString);
        System.out.println(shakespeareGotLeoned);
    }
    
    public static String changeItAll(String input) {
        String leonIsHamlet = findAndReplaceNormalCase(input, "Hamlet", "Leon");
        String leonIsHamletUpperCase = findAndReplaceNormalCase(leonIsHamlet, "Hamlet".toUpperCase(), "Leon".toUpperCase());
        String tariqIsHoratio = findAndReplaceNormalCase(leonIsHamletUpperCase, "Horatio", "Tariq");
        return findAndReplaceNormalCase(tariqIsHoratio, "Horatio".toUpperCase(), "Tariq".toUpperCase());
    }
    
    public static String loadDataFromFile(String filePath) throws IOException {
        BufferedReader fileInput = new BufferedReader(new java.io.FileReader(filePath));
        String currentLine;
        ArrayList<String> fileStrings = new ArrayList<>();
        try {
            while ((currentLine = fileInput.readLine()) != null) {
                fileStrings.add(currentLine);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        StringBuilder sb = new StringBuilder();
        for (String s : fileStrings) {
            {
                sb.append(s);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    public static String findAndReplaceNormalCase(String inputFile, String originalName, String newName) {
        String pattern = ("\\b(" + originalName + ")");
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(inputFile);
        return inputFile.replaceAll(originalName, newName);
    }
    
    public String getHamletData() {
        return hamletData;
    }
    
}
