import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day14 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        ArrayList<String> pos = new ArrayList<>();
        ArrayList<String> vel = new ArrayList<>();
        ArrayList<String> newPos = new ArrayList<>();
        for (int i = 0; i < fileData.size(); i++) {
            String[] a = fileData.get(i).split(" ");
            pos.add(a[0].replaceAll("p=", ""));
            vel.add(a[1].replaceAll("v=", ""));
        }
        int blinks = 100;
        int quad1 = 0;
        int quad2 = 0;
        int quad3 = 0;
        int quad4 = 0;
        for (int i = 0; i < pos.size(); i++) {
            int wide = 101;
            int tall = 103;
            String[] p = pos.get(i).split(",");
            String[] v = vel.get(i).split(",");
            int xvel = Integer.parseInt(v[0]);
            int yvel = Integer.parseInt(v[1]);
            int xpos = Integer.parseInt(p[0]);
            int ypos = Integer.parseInt(p[1]);
            int a = xpos + (xvel * blinks);
            int b = wide;
            xpos = ((a % b) + b) % b; // used google to find formula for calculating neg numbers mod a pos number since java does it wrong :(
            b = tall;
            a = ypos + (yvel * blinks);
            ypos = ((a % b) + b) % b; // used google to find formula for calculating neg numbers mod a pos number since java does it wrong :(
            newPos.add(xpos + "," + ypos);
            if (xpos < (wide - 1) / 2 && ypos < (tall - 1) / 2) {
                quad1++;
            } else if (xpos < (wide - 1) / 2 && ypos > (tall - 1) / 2) {
                quad2++;
            } else if (xpos > (wide - 1) / 2 && ypos < (tall - 1) / 2) {
                quad3++;
            } else if (xpos > (wide - 1) / 2 && ypos > (tall - 1) / 2) {
                quad4++;
            }
        }
        System.out.println("part one: " + quad1 * quad2 * quad3 * quad4);
        int blinks2 = 0;
        while (blinks2 < 10000) {
            for (int i = 0; i < pos.size(); i++) {
                int wide = 101;
                int tall = 103;
                String[] p = pos.get(i).split(",");
                String[] v = vel.get(i).split(",");
                int xvel = Integer.parseInt(v[0]);
                int yvel = Integer.parseInt(v[1]);
                int xpos = Integer.parseInt(p[0]);
                int ypos = Integer.parseInt(p[1]);
                int a = xpos + (xvel * blinks2);
                int b = wide;
                xpos = ((a % b) + b) % b; // used google to find formula for calculating neg numbers mod a pos number since java does it wrong :(
                b = tall;
                a = ypos + (yvel * blinks2);
                ypos = ((a % b) + b) % b; // used google to find formula for calculating neg numbers mod a pos number since java does it wrong :(
                newPos.add(xpos + " " + ypos);
            }
            if (isUnique(newPos) && blinks2 > 5000) // sometimes this brute force incorrectly outputs a number of seconds that doesn't form a tree when blinks 2 < 5000
            {
                System.out.println("part two: " + blinks2);
            }
            newPos.clear();
            blinks2++;
        }
    }


    public static boolean isUnique(ArrayList<String> e) // brute force for part 2: checks if any positions are repeated because the tree probably doesn't have robots in repeat places
    {
        for (int i = 0; i < e.size(); i++) {
            for (int j = 0; j < e.size(); j++) {
                    if (e.get(i).equals(e.get(j)) && (i != j)) {
                        return false;
                    }
            }
        }
        return  true;
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
