import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Day6 {
    public static void main(String[] args) {


        ArrayList<String> fileData = getFileData("src/InputFile");
        // you now have a list of Strings from the file "InputFile"
        int len = fileData.size();
        String [] [] a = new String[len][len];
        int x1 = 0;
        int y1 = 0;
        for (int i = 0; i < fileData.size(); i++)
        {
            String line = fileData.get(i);
            for(int j = 0; j <line.length(); j++)
            {
                a[i][j] = line.substring(j, j + 1);
                if (a[i][j].equals("^"))
                {
                    x1 = i;
                    y1 = j;
                }
            }
        }
        String[][] c = deepCopy(a);
        System.out.println("part one: " +countVisits(a, x1, y1));
        int loops = 0;
        for (String visit : visits) {
            String[][] b = deepCopy(c);
            String[] pop = visit.split(" ");
            b[Integer.parseInt(pop[0])][Integer.parseInt(pop[1])] = "#";
            if (isLoop(b, x1, y1)) {
                loops++;
            }


        }
        System.out.println("part two: " + loops);
    }


    public static ArrayList<String> visits = new ArrayList<>();




    public static int countVisits(String[][] a, int x, int y)
    {
        int count = 0;
        int len = a.length;
        int ypos = x;
        int xpos = y;
        boolean up = true;
        boolean right = false;
        boolean down = false;
        boolean left = false;
        while (xpos < len + 1)
        {
            if (xpos == len)
            {
                break;
            }
            if (ypos == len)
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
            a[ypos][xpos] = "X";
            if (up)
            {
                ypos--;
            }
            if (right)
            {
                xpos++;
            }
            if (down)
            {
                ypos++;
            }
            if (left)
            {
                xpos--;
            }
        }
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < a[i].length; j++)
            {
                if (a[i][j].equals("X"))
                {
                    visits.add(i + " " + j);
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean isLoop(String[][] a, int x, int y)
    {
        int len = a.length;
        int steps = 0;
        int ypos = x;
        int xpos = y;
        boolean up = true;
        boolean right = false;
        boolean down = false;
        boolean left = false;
        int checkForManyObs = 0;
        while (steps < 10000)
        {
            if (xpos == len - 1 || ypos == len - 1 || xpos == 0 || ypos == 0)
            {
                return false;
            }
            while (checkForManyObs < 2) {
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
                checkForManyObs++;
            }
            checkForManyObs = 0;
            a[ypos][xpos] = "X";
            if (up)
            {
                ypos--;
            }
            if (right)
            {
                xpos++;
            }
            if (down)
            {
                ypos++;
            }
            if (left)
            {
                xpos--;
            }
            steps++;
        }
        return true;
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

