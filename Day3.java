import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        // you now have a list of Strings from the file "InputFile"


boolean go = true;
int sum = 0;
        String q = fileData.getFirst();
        Pattern instruction = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Matcher matcher = instruction.matcher(q);
        while (matcher.find())
        {
                String s = matcher.group().substring(4);
                s = s.replace("(","");
                s = s.replace(")", "");
                String[] a = s.split(",");
                int x = Integer.parseInt(a[0]);
                int y = Integer.parseInt(a[1]);
                sum += x * y;
        }
        System.out.println("part one: " + sum);

        }





    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}
