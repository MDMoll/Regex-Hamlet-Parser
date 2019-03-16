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
    private String result;
    private String hamletData;
    
    public HamletParser() {
        this.hamletData = loadFile();
    }
    
    public static void main(String[] args) throws IOException {
        String hamletString = loadDataFromFile("/Users/markmoll/zcw/Regex-Hamlet-Parser/src/main/resources/hamlet.txt");
        String shakespeareGotLeoned = changeItAll(hamletString);
        System.out.println(shakespeareGotLeoned);
    }
    
    public static String changeItAll(String input) {
        String leonIsHamlet = findAndReplaceNormalCase(input, "Hamlet", "Leon");
        String leonIsHamletUpperCase = findAndReplaceUpperCase(leonIsHamlet, "Hamlet", "Leon");
        String tariqIsHoratio = findAndReplaceNormalCase(leonIsHamletUpperCase, "Horatio", "Tariq");
        return findAndReplaceUpperCase(tariqIsHoratio, "Horatio", "Tariq");
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
        System.out.println(sb.toString());
        String hamletString = sb.toString();
        return hamletString;
    }
    
    private String loadFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("hamlet.txt")).getFile());
        StringBuilder result = new StringBuilder();
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return result.toString();
    }
    
    public static String findAndReplaceNormalCase(String inputFile, String originalName, String newName) {
        String pattern = ("\\b(" + originalName + ")");
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(inputFile);
        m.matches();
        m.reset();
        return inputFile.replaceAll(originalName, newName);
    }
    
    public static String findAndReplaceUpperCase(String inputFile, String originalName, String newName) {
        String input = inputFile;
        String pattern2 = ("\\b(" + originalName.toUpperCase() + ")");
        Pattern p2 = Pattern.compile(pattern2);
        Matcher m2 = p2.matcher(input);
        m2.matches();
        m2.reset();
        return input.replaceAll(originalName, newName.toUpperCase());
    }
    
    public String getHamletData() {
        return hamletData;
    }
    
}
