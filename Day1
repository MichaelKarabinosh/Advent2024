import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        // you now have a list of Strings from the file "InputFile"
        ArrayList<Integer> left = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();
        for (int i = 0; i < fileData.size(); i++)
        {
            String s = fileData.get(i);
            left.add(Integer.parseInt(s.substring(0, 5)));
            right.add(Integer.parseInt(s.substring(8)));
        }
        left.sort(null);
        right.sort(null);
        int totalDistance = 0;
        for (int i = 0; i <fileData.size(); i++)
        {
            totalDistance += Math.abs(left.get(i) - right.get(i));
        }
        System.out.println("part one: " + totalDistance);


        ArrayList<Integer> left2 = new ArrayList<Integer>();
        ArrayList<Integer> right2 = new ArrayList<Integer>();
        int count = 0;
        int simScore = 0;
        for (int i = 0; i < fileData.size(); i++)
        {
            String s = fileData.get(i);
            String [] a = s.split("   ");
            left2.add(Integer.parseInt(a[0]));
            right2.add(Integer.parseInt(a[1]));
        }
        for (int i = 0; i < left2.size(); i++)
        {
            for (int j = 0; j < left2.size(); j++)
            {
                if (left2.get(i).equals(right2.get(j)))
                {
                    count++;
                }
            }
            simScore += count * left2.get(i);
            count = 0;
        }
        System.out.println("part two: " + simScore);

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

