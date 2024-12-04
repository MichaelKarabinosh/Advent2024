import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Day2 {
    public static void main(String[] args) {


        ArrayList<String> fileData = getFileData("src/InputFile");
        // you now have a list of Strings from the file "InputFile"
        int count = 0;

        ArrayList<String> updatedFile = new ArrayList<String>();
        int checkIndex = 0;

        for (String line : fileData) {
            String[] a = line.split(" ");
            String q = "";
            if(isSafe(line))
            {
                count++;
            }
            else {

            }
        }
        System.out.println("part one: " + count);
        count = 0;

        for (String line : fileData) {
            String[] a = line.split(" ");
            String q = "";
            if(isSafe(line))
            {
                count++;
            }
            else {
                if (isSafeRemoval(line))
                {
                    count++;
                }

            }
        }
        System.out.println("part two: " + count);



    }

    public static boolean isSafeRemoval(String l)
    {
        String[] a = l.split(" ");
        String b = "";
       int checkIndex = -1;
       for (int i = 0; checkIndex < a.length; i++)
       {
           for (int j = 0; j < a.length; j++)
           {
               if (checkIndex != j)
               {
                   b += a[j] + " ";
               }
           }
           if (isSafe(b))
           {
               return true;
           }
           b = "";
           checkIndex++;
       }
       return false;
    }


    public static boolean isSafe(String l)
    {
        String[] a = l.split(" ");
        ArrayList<Integer> q = new ArrayList<Integer>();
        for (int i = 0; i < a.length; i++)
        {
            q.add(Integer.parseInt(a[i]));

        }
        ArrayList<Integer> regOrder = new ArrayList<Integer>(q);
        ArrayList<Integer> revOrder = new ArrayList<Integer>(q);
        Collections.sort(regOrder);
        revOrder.sort(Collections.reverseOrder());
        if (!(q.equals(regOrder)) && !(q.equals(revOrder)))
        {
            return false;
        }
        for (int j = 0; j < q.size() - 1 ; j++)
        {
            int diff = Math.abs(q.get(j) - q.get(j+1));
            if (!(diff >= 1 && diff <= 3))
            {
                return false;
            }
        }
        return true;
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





