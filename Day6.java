import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        // you now have a list of Strings from the file "InputFile"
        String [] [] a = new String[130][130];
        for (int i = 0; i < fileData.size(); i++)
        {
            String line = fileData.get(i);
            for(int j = 0; j <line.length(); j++)
            {
                a[i][j] = line.substring(j, j + 1);
                if (a[i][j].equals("^"))
                {
                    System.out.println(i + "" + j);
                }
            }
        }
        String[][] c = deepCopy(a);
        System.out.println(countVisits(a));;
        int loops = 0;
        for (int i = 0; i < 130; i++)
        {
            for (int j = 0; j < 130; j++)
            {
                String[][] b = deepCopy(c);
                    b[i][j] = "#";
                if (isLoop(b))
                {
                    loops++;
                }
            }
        }
        System.out.println(loops);
    }


    public static int countVisits(String[][] a)
    {
        int steps = 0;
        int ypos = 75;
        int xpos = 73;
        boolean up = true;
        boolean right = false;
        boolean down = false;
        boolean left = false;
        boolean outOfBounds = false;
        int visits = 0;
        boolean loop = false;
        a[ypos - 1][xpos] = "^";
        while (xpos < 131)
        {
            if (steps > 10000)
            {
                loop = true;
                break;
            }
            if (xpos == 130)
            {
                break;
            }
            if (ypos == 130)
            {
                break;
            }
            if (xpos == -1)
            {
                break;
            }
            if (ypos == -1)
            {
                break;
            }
            try {
                if (up) {
                    if (a[ypos - 1][xpos].equals("#")) {
                        up = false;
                        right = true;
                    }
                }
                if (right) {
                    if (a[ypos][xpos + 1].equals("#")) {
                        right = false;
                        down = true;
                    }
                }
                if (down) {

                    if (a[ypos + 1][xpos].equals("#")) {
                        down = false;
                        left = true;
                    }
                }
                if (left) {
                    if (a[ypos][xpos - 1].equals("#")) {
                        left = false;
                        up = true;
                    }
                }
            }
            catch (Exception _)
            {

            }
            if (up)
            {
                a[ypos][xpos] = "X";
                ypos--;
                steps++;
            }
            if (right)
            {
                a[ypos][xpos] = "X";
                xpos++;
                steps++;
            }
            if (down)
            {
                a[ypos][xpos] = "X";
                ypos++;
                steps++;
            }
            if (left)
            {
                a[ypos][xpos] = "X";
                xpos--;
                steps++;
            }
        }
        int count = 0;
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < a[i].length; j++)
            {
                if (a[i][j].equals("X"))
                {
                    count++;
                }
            }
        }
        return count;
    }


    public static boolean isLoop(String[][] a)
    {
        int steps = 0;
        int ypos = 75;
        int xpos = 73;
        boolean up = true;
        boolean right = false;
        boolean down = false;
        boolean left = false;
        while (steps < 99999)
        {
            if (steps > 10000)
            {
                return true;
            }
            if (xpos == 130)
            {
                break;
            }
            if (ypos == 130)
            {
                break;
            }
            if (xpos == -1)
            {
                break;
            }
            if (ypos == -1)
            {
                break;
            }
            try {
                if (up) {
                    if (a[ypos - 1][xpos].equals("#")) {
                        up = false;
                        right = true;
                    }
                }
                if (right) {
                    if (a[ypos][xpos + 1].equals("#")) {
                        right = false;
                        down = true;
                    }
                }
                if (down) {

                    if (a[ypos + 1][xpos].equals("#")) {
                        down = false;
                        left = true;
                    }
                }
                if (left) {
                    if (a[ypos][xpos - 1].equals("#")) {
                        left = false;
                        up = true;
                    }
                }
            }
            catch (Exception _)
            {

            }
            if (up)
            {
                a[ypos][xpos] = "X";
                ypos--;
                steps++;
            }
            if (right)
            {
                a[ypos][xpos] = "X";
                xpos++;
                steps++;
            }
            if (down)
            {
                a[ypos][xpos] = "X";
                ypos++;
                steps++;
            }
            if (left)
            {
                a[ypos][xpos] = "X";
                xpos--;
                steps++;
            }
        }
        return false;
    }

    public static String[][] deepCopy(String[][] original) {
        if (original == null) {
            return null;
        }

        final String[][] result = new String[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
            // For Java versions prior to Java 6 use the next:
            // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
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
