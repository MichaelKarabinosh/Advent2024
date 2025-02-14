import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
       String data = fileData.getFirst();
       ArrayList<String> diskAsList = new ArrayList<>();
       int num = 0;
       for (int i = 0; i < data.length(); i++)
       {
            String char1 = data.substring(i, i+1);
            String numAsStr = num + "";
           if (i % 2 ==  0)
           {
               for (int j = 0; j < Integer.parseInt(char1); j++)
               {
                   diskAsList.add(numAsStr);
               }
               num++;
           }
           else {
               for (int j = 0; j < Integer.parseInt(char1); j++) {
                   diskAsList.add(".");
               }
           }
       }
       System.out.println("part one: " + doPartOne(diskAsList));



    }

    public static long doPartOne(ArrayList<String> diskAsList)
    {
        ArrayList<Integer> freeSpaces = new ArrayList<>();
        int numNums = 0;
        for (int i = 0; i < diskAsList.size(); i++)
        {

            if (diskAsList.get(i).equals(".")) {
                freeSpaces.add(i);
            }
            else {
                numNums++;
            }
        }
        int index = diskAsList.size() - 1;
        while (!freeSpaces.isEmpty())
        {
            while (diskAsList.get(index).equals("."))
            {
                index--;
            }
            diskAsList.set(freeSpaces.getFirst(), diskAsList.get(index));
            index--;
            freeSpaces.removeFirst();
        }
        long total = 0;
        for (int i = 0; i < numNums; i++)
        {
            total += i * Long.parseLong(diskAsList.get(i));
        }
        return total;
    }



    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.isEmpty())
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}
