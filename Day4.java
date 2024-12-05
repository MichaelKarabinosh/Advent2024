import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;


public class Day4 {
    public static void main(String[] args) {


        ArrayList<String> fileData = getFileData("src/InputFile");
        // you now have a list of Strings from the file "InputFile"
        int x = 0;
        int y = 0;
        for (int i = 0; i < fileData.size(); i++)
        {
            x= 0;
            String line = fileData.get(i);
            for (int j = 0; j < line.length(); j++)
            {
                x++;
            }
            y++;
        }
        String[][]a = new String[x][y];
        for (int i = 0; i < fileData.size(); i++)
        {
            String line = fileData.get(i);
            for (int j = 0; j < line.length(); j++)
            {
                a[i][j] = line.substring(j, j + 1);
            }
        }
        int count = 0;
        for (int i = 0; i < fileData.size(); i++)
        {
            String line = fileData.get(i);
            for (int j = 0; j < line.length(); j++)
            {
                if (a[i][j].equals("X"))
                {
                    try {
                        if (a[i + 1][j].equals("M") && a[i + 2][j].equals("A") && a[i + 3][j].equals("S")) {
                            count++;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException _)
                    {

                    }
                    try {
                        if (a[i - 1][j].equals("M") && a[i - 2][j].equals("A") && a[i - 3][j].equals("S")) {
                            count++;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException _)
                    {

                    }
                    try {
                        if (a[i][j + 1].equals("M") && a[i][j + 2].equals("A") && a[i][j + 3].equals("S")) {
                            count++;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException _)
                    {

                    }
                    try {
                        if (a[i][j - 1].equals("M") && a[i][j - 2].equals("A") && a[i][j - 3].equals("S")) {
                            count++;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException _)
                    {

                    }
                    try {
                        if (a[i + 1][j + 1].equals("M") && a[i + 2][j + 2].equals("A") && a[i + 3][j + 3].equals("S")) {
                            count++;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException _)
                    {

                    }
                    try {
                        if (a[i + 1][j - 1].equals("M") && a[i + 2][j - 2].equals("A") && a[i + 3][j - 3].equals("S")) {
                            count++;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException _)
                    {

                    }
                    try {
                        if (a[i - 1][j - 1].equals("M") && a[i - 2][j - 2].equals("A") && a[i - 3][j - 3].equals("S")) {
                            count++;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException _)
                    {

                    }
                    try {
                        if (a[i - 1][j + 1].equals("M") && a[i - 2][j + 2].equals("A") && a[i - 3][j + 3].equals("S")) {
                            count++;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException _)
                    {

                    }
                }
            }
        }
        System.out.println("part one: " + count);
        count = 0;
        for (int i = 0; i < fileData.size(); i++)
        {
            String line = fileData.get(i);
            for (int j = 0; j < line.length(); j++)
            {
                if (a[i][j].equals("A"))
                {
                    try {
                        if (a[i - 1][j +1].equals("M") && a[i - 1][j -1].equals("S") && a[i + 1][j + 1].equals("M") && a[i + 1][j - 1].equals("S")) {
                            count++;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException _)
                    {

                    }
                    try {
                        if (a[i - 1][j +1].equals("M") && a[i - 1][j -1].equals("M") && a[i + 1][j + 1].equals("S") && a[i + 1][j - 1].equals("S")) {
                            count++;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException _)
                    {

                    }
                    try {
                        if (a[i - 1][j +1].equals("S") && a[i - 1][j -1].equals("S") && a[i + 1][j + 1].equals("M") && a[i + 1][j - 1].equals("M")) {
                            count++;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException _)
                    {

                    }
                    try {
                        if (a[i - 1][j +1].equals("S") && a[i - 1][j -1].equals("M") && a[i + 1][j + 1].equals("S") && a[i + 1][j - 1].equals("M")) {
                            count++;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException _)
                    {

                    }
                }
            }
        }
        System.out.println("part two: " + count);
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

