import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        // you now have a list of Strings from the file "InputFile"
        String[][] map = new String[130][130];
        int xpos = 0;
        int ypos = 0;
        boolean up = true;
        boolean right = false;
        boolean down = false;
        boolean left = false;
        String temp = "";
        boolean roadblock = false;
        for (int i = 0; i < fileData.size(); i++) {
            String line = fileData.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.substring(j, j + 1).equals("^")) {
                    xpos = j;
                    ypos = i;
                }
                map[i][j] = line.substring(j, j + 1);
            }
        }
        while (xpos < 129 || ypos < 129) {
            try {
                if (map[ypos][xpos].equals("#")) {
                    roadblock = true;
                    if (up) {
                        up = false;
                        right = true;
                    } else if (right) {
                        right = false;
                        down = true;
                    } else if (down) {
                        down = false;
                        left = true;
                    } else {
                        left = false;
                        up = true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException _) {

            }
            try {
                if (!roadblock) {
                    map[ypos][xpos] = "X";
                }
                if (roadblock) {
                    if (right) {
                        ypos++;
                    }
                    if (down) {
                        xpos--;
                    }
                    if (left) {
                        ypos--;
                    }
                    if (up) {
                        xpos++;
                    }
                    roadblock = false;
                }

            } catch (ArrayIndexOutOfBoundsException _) {

            }
            try {
                if (up) {
                    ypos--;
                } else if (right) {
                    xpos++;
                } else if (down) {
                    ypos++;
                } else {
                    xpos--;
                }
            } catch (ArrayIndexOutOfBoundsException _) {

            }
            try {
                temp = map[ypos][xpos];
            } catch (ArrayIndexOutOfBoundsException _) {
                break;
            }
        }
            int count = 0;
            for (int i = 0; i < fileData.size(); i++) {
                String line = fileData.get(i);
                for (int j = 0; j < line.length(); j++) {
                    if (map[i][j].equals("X")) {
                        count++;
                    }
                }
            }
            System.out.println("part one: " + count);

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
