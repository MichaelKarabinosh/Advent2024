import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        HashMap<String, String> pairs;
        int len = fileData.size() - 1;
        pairs = findPairs(fileData);
        Set<String> antennas = pairs.keySet();
        HashMap<ArrayList<Integer>, Integer> possiblePositions = doPartOne(pairs, antennas, len);
        System.out.println("part one: " + possiblePositions.size());
        possiblePositions = doPartTwo(pairs, antennas, len);
        System.out.println("part two: " + possiblePositions.size());
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

    public static HashMap<String, String> findPairs(ArrayList<String> data)
    {
        HashMap<String, String> pairs = new HashMap<>();
        for (int i = 0; i < data.size(); i++)
        {
            for (int j = 0; j < data.get(i).length(); j++)
            {
                String character = data.get(i).substring(j, j+ 1);
                if (!(character.equals(".")))
                {
                    if (pairs.get(character) == null)
                    {
                        pairs.put(character, "|" + j + ":" + i + "|");
                    }
                    else {
                        pairs.put(character, pairs.get(character) + j + ":" + i + "|");
                    }
                }
            }
        }
        return pairs;
    }

    public static HashMap<ArrayList<Integer>, Integer> doPartOne(HashMap<String, String> pairs, Set<String> antennas, int len)
    {
        HashMap<ArrayList<Integer>, Integer> possiblePositions = new HashMap<>();
        for (String antenna : antennas)
        {
            String code = pairs.get(antenna);
            String[] coords = code.split("\\|");
            for (int i = 1; i < coords.length; i++)
            {
                for (int j = 1; j < coords.length; j++)
                {
                    if (i != j) {
                        String[] pair1 = coords[i].split(":");
                        String[] pair2 = coords[j].split(":");
//                        System.out.println(Arrays.toString(pair1));
                        int xDiff = Integer.parseInt(pair1[0]) - Integer.parseInt(pair2[0]);
                        int yDiff = Integer.parseInt(pair1[1]) - Integer.parseInt(pair2[1]);
//                        System.out.print(Arrays.toString(pair1) + "" + Arrays.toString(pair2) + " " + xDiff + " " + yDiff);
                        if (Integer.parseInt(pair1[0]) + xDiff >= 0 && Integer.parseInt(pair1[0]) + xDiff <= len && Integer.parseInt(pair1[1]) + yDiff >= 0 && Integer.parseInt(pair1[1]) + yDiff <= len)
                        {
                            ArrayList<Integer> posAnts = new ArrayList<>();
                            posAnts.add(Integer.parseInt(pair1[0]) + xDiff);
                            posAnts.add(Integer.parseInt(pair1[1]) + yDiff);
                            possiblePositions.put(posAnts, 1);
//                            System.out.println(" " + (Integer.parseInt(pair1[0]) + xDiff) + " " + (Integer.parseInt(pair1[1]) + yDiff));

                        }
                        if (Integer.parseInt(pair2[0])  - xDiff >= 0 && Integer.parseInt(pair2[0]) - xDiff <= len && Integer.parseInt(pair2[1]) - yDiff >= 0 && Integer.parseInt(pair2[1]) - yDiff <= len)
                        {
                            ArrayList<Integer> posAnts = new ArrayList<>();
                            posAnts.add(Integer.parseInt(pair2[0]) - xDiff);
                            posAnts.add(Integer.parseInt(pair2[1]) - yDiff);
                            possiblePositions.put(posAnts, 1);
                        }

                    }

                }
            }
//            System.out.println(antennas);
        }
        return possiblePositions;
    }
    public static HashMap<ArrayList<Integer>, Integer> doPartTwo(HashMap<String, String> pairs, Set<String> antennas, int len)
    {
        HashMap<ArrayList<Integer>, Integer> possiblePositions = new HashMap<>();
        for (String antenna : antennas)
        {
            String code = pairs.get(antenna);
            String[] coords = code.split("\\|");
            for (int i = 1; i < coords.length; i++)
            {
                for (int j = 1; j < coords.length; j++)
                {
                    if (i != j) {
                        String[] pair1 = coords[i].split(":");
                        String[] pair2 = coords[j].split(":");
//                        System.out.println(Arrays.toString(pair1));
                        int xDiff = Integer.parseInt(pair1[0]) - Integer.parseInt(pair2[0]);
                        int yDiff = Integer.parseInt(pair1[1]) - Integer.parseInt(pair2[1]);
                        int xUp = 0;
                        int yUp = 0;
                        int xDown = 0;
                        int yDown = 0;
                        boolean exceedsBounds = false;
                        while (!exceedsBounds) {
                            ArrayList<Integer> posAnts = new ArrayList<>();
//                        System.out.print(Arrays.toString(pair1) + "" + Arrays.toString(pair2) + " " + xDiff + " " + yDiff);
                            if (Integer.parseInt(pair1[0]) + xUp >= 0 && Integer.parseInt(pair1[0]) + xUp <= len && Integer.parseInt(pair1[1]) + yUp >= 0 && Integer.parseInt(pair1[1]) + yUp <= len) {

                                posAnts.add(Integer.parseInt(pair1[0]) + xUp);
                                posAnts.add(Integer.parseInt(pair1[1]) + yUp);
                                possiblePositions.put(posAnts, 1);
                            }
                            else {
                                exceedsBounds = true;
                            }
                            xUp += xDiff;
                            yUp += yDiff;

                        }
                        exceedsBounds = false;
//                            System.out.println(" " + (Integer.parseInt(pair1[0]) + xDiff) + " " + (Integer.parseInt(pair1[1]) + yDiff));
                        while (!exceedsBounds) {
                            ArrayList<Integer> posAnts = new ArrayList<>();

                            if (Integer.parseInt(pair2[0]) - xDown >= 0 && Integer.parseInt(pair2[0]) - xDown <= len && Integer.parseInt(pair2[1]) - yDown >= 0 && Integer.parseInt(pair2[1]) - yDown <= len) {
                                posAnts.add(Integer.parseInt(pair2[0]) - xDown);
                                posAnts.add(Integer.parseInt(pair2[1]) - yDown);
                                possiblePositions.put(posAnts, 1);
                            }
                            else {
                                exceedsBounds = true;
                            }
                            xDown -= xDiff;
                            yDown -= yDiff;

                        }


                    }

                }
            }
//            System.out.println(antennas);
        }
        return possiblePositions;
    }


}
