import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        // you now have a list of Strings from the file "InputFile"
        ArrayList<String> SimpleMatches = new ArrayList<String>();
        ArrayList<String> AdvMatches = new ArrayList<String>();

        boolean go = true;
        int sum = 0;
        String q = fileData.getFirst();
        Pattern advinstruction = Pattern.compile("mul\\(\\d+,\\d+\\)|don't\\(\\)|do\\(\\)");
        Pattern simpleinstruction = Pattern.compile("mul\\(\\d+,\\d+\\)");
//        Pattern good = Pattern.compile("do\\(\\)");
//        Pattern bad = Pattern.compile("don't\\(\\)");
        Matcher advmatcher = advinstruction.matcher(q);
        Matcher simplematcher = simpleinstruction.matcher(q);
        while (simplematcher.find()) {
            SimpleMatches.add(simplematcher.group());
        }
        while (advmatcher.find())
        {
            AdvMatches.add(advmatcher.group());
        }
        System.out.println(SimpleMatches);
        for (int i = 0; i < SimpleMatches.size(); i++)
        {
            String s = SimpleMatches.get(i).substring(4);
            s = s.replace("(","");
            s = s.replace(")", "");
            String[] a = s.split(",");
            int x = Integer.parseInt(a[0]);
            int y = Integer.parseInt(a[1]);
            sum += x * y;
        }
        System.out.println("part one: " + sum);
        sum = 0;

        for (int i = 0; i < AdvMatches.size(); i++)
        {
            String s = AdvMatches.get(i);
            if (s.equals("do()"))
            {
                go = true;
            }
            else if (s.equals("don't()"))
            {
                go = false;
            }
            else if (go && s.contains("mul("))
            {
                s = s.substring(4);
                s = s.replace("(","");
                s = s.replace(")", "");
                String[] a = s.split(",");
                int x = Integer.parseInt(a[0]);
                int y = Integer.parseInt(a[1]);
                sum += x * y;
            }
        }
        System.out.println("part two: " + sum);
        {


        }

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
