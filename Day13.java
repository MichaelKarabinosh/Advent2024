import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/InputFile");
        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();
        ArrayList<String> prize = new ArrayList<>();
        for (int i = 0; i <fileData.size(); i++)
        {
            if (fileData.get(i).contains("Button A"))
            {
                a.add(fileData.get(i));
            }
            if (fileData.get(i).contains("Button B"))
            {
                b.add(fileData.get(i));
            }
            if (fileData.get(i).contains("Prize"))
            {
                prize.add(fileData.get(i));
            }
        }
        long total = 0;
        for (int i = 0; i < a.size(); i++)
        {
            int prizeX;
            int prizeY;
            int bx;
            int by;
            int ax;
            int ay;
            String [] aReal = a.get(i).split(",");
            String [] bReal = b.get(i).split(",");
            String [] prizeReal = prize.get(i).split(",");


            for(int j = 0 ; j < aReal.length; j++)
            {
                aReal[j] = aReal[j].replaceAll("\\D", "");
                bReal[j] = bReal[j].replaceAll("\\D", "");
                prizeReal[j] = prizeReal[j].replaceAll("\\D", "");
            }


            prizeX = Integer.parseInt(prizeReal[0]);
            prizeY = Integer.parseInt(prizeReal[1]);
            ax = Integer.parseInt(aReal[0]);
            ay = Integer.parseInt(aReal[1]);
            bx = Integer.parseInt(bReal[0]);
            by = Integer.parseInt(bReal[1]);
            int numB = (prizeX*ay-prizeY*ax)/(ay*bx-by*ax);
            int numA = (prizeX*by-prizeY*bx)/(by*ax-bx*ay);
            if ((numA * ay) + (numB * by) == prizeY)
            {
                if (numA < 100 && numB < 100) {
                    total += (numB) + numA * 3L;
                }
            }
        }
        System.out.println("part one: " + total);
        
      
      total = 0;
        for (int i = 0; i < a.size(); i++)
        {
            final long f = 10000000000000L;
            long prizeX;
            long prizeY;
            long bx;
            long by;
            long ax;
            long ay;
            String [] aReal = a.get(i).split(",");
            String [] bReal = b.get(i).split(",");
            String [] prizeReal = prize.get(i).split(",");
            for(int j = 0 ; j < aReal.length; j++)
            {
                aReal[j] = aReal[j].replaceAll("\\D", "");
                bReal[j] = bReal[j].replaceAll("\\D", "");
                prizeReal[j] = prizeReal[j].replaceAll("\\D", "");
            }
            prizeX = Long.parseLong(prizeReal[0]) + f;
            prizeY = Long.parseLong(prizeReal[1]) + f;
            ax = Long.parseLong(aReal[0]);
            ay = Long.parseLong(aReal[1]);
            bx = Long.parseLong(bReal[0]);
            by = Long.parseLong(bReal[1]);
            long numB =  (prizeX*ay-prizeY*ax)/(ay*bx-by*ax);
            long numA = (prizeX*by-prizeY*bx)/(by*ax-bx*ay);
            if ((numA * ay) + (numB * by) == prizeY)
            {
                total += (numB) + (numA * 3);
            }
        }
        System.out.println("part two: " + total);
    }






    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<>();
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


