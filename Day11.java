import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Day11 {
    public static void main(String[] args) {


        ArrayList<String> fileData = getFileData("src/InputFile");
        // you now have a list of Strings from the file "InputFile"
        int repititions = 75;
        String data = fileData.get(0);
        String [] a = data.split(" ");
        ArrayList<String> b = new ArrayList<>(Arrays.asList(a));
        ArrayList<String> c = new ArrayList<>(b);



        for (int i = 0; i < repititions; i++)
        {
            c.clear();
            for (int j = 0; j < b.size(); j++)
            {
                if (b.get(j).equals("0"))
                {
                    c.add("1");
                }
                else if (b.get(j).length() % 2 == 0)
                {
                    String s = b.get(j);
                    String s1 = s.substring(0, s.length() /2 );
                    String s2 = s.substring(s.length() / 2);
                    s1 = s1.replaceFirst("^0+(?!$)", "");
                    s2 = s2.replaceFirst("^0+(?!$)", "");
                    c.add(s1);
                    c.add(s2);
                }
                else
                {
                    long p = (long) Double.parseDouble(b.get(j)) * 2024;
                    c.add("" + p);
                }
            }
            b = new ArrayList<>(c);
        }
        System.out.println("part one: " + b.size());


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

