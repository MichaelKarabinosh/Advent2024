import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Day5 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/InputFile");
        ArrayList<String> rules = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Integer> subL = new ArrayList<>();
        for (int i = 0; i < fileData.size(); i++) {
            if (fileData.get(i).contains("|")) {
                rules.add(fileData.get(i));
            } else if (!fileData.get(i).isEmpty()) {
                list.add(fileData.get(i));
            }
        }
        
        int total = 0;
        for (int i = 0; i < list.size(); i++) {
            String[] a = list.get(i).split(",");
            for (int j = 0; j < a.length; j++) {
                subL.add(Integer.parseInt(a[j]));
            }
            if (checkList(rules, subL)) {
                total += subL.get((subL.size() - 1) / 2);
            }
            subL.clear();
        }
        System.out.println("part one: " + total);
        
        int total2 = 0;
        for (int i = 0; i < list.size(); i++) {
            String[] a = list.get(i).split(",");
            for (int j = 0; j < a.length; j++) {
                subL.add(Integer.parseInt(a[j]));
            }
            if (!checkList(rules, subL)) {
                total2 += order(rules,subL);
            }
            subL.clear();
        }
        System.out.println("part two: " + total2);



        // you now have a list of Strings from the file "InputFile"
    }

    public static boolean checkList(ArrayList<String> rules, ArrayList<Integer> subList) {
        int loccurrences = 0;
        ArrayList<Integer> loccsList = new ArrayList<>();
        for (int i = 0; i < subList.size(); i++)
        {
            int test = subList.get(i);
            for (int j = 0; j <rules.size(); j++) {
                String[] ruleS = rules.get(j).split("\\|");
                if ((Integer.parseInt(ruleS[0]) == (test)) && subList.contains(Integer.parseInt(ruleS[1]))) {
                    loccurrences++;
                }
            }
            loccsList.add(loccurrences);
            loccurrences = 0;
        }
        ArrayList<Integer> sortedloccs = new ArrayList<>(loccsList);
        sortedloccs.sort(Comparator.reverseOrder());
        return sortedloccs.equals(loccsList);
    }

    public static Integer order(ArrayList<String> rules, ArrayList<Integer> subList) {
        HashMap<Integer, Integer> rulesPos = new HashMap<>();
        int loccurrences = 0;
        ArrayList<Integer> loccsList = new ArrayList<>();
        for (int i = 0; i < subList.size(); i++)
        {
            int test = subList.get(i);
            for (int j = 0; j <rules.size(); j++) {
                String[] ruleS = rules.get(j).split("\\|");
                if ((Integer.parseInt(ruleS[0]) == (test)) && subList.contains(Integer.parseInt(ruleS[1]))) {
                    loccurrences++;
                }
            }
            rulesPos.put(loccurrences, test);
            loccsList.add(loccurrences);
            loccurrences = 0;
        }
        ArrayList<Integer> sortedloccs = new ArrayList<>(loccsList);
        sortedloccs.sort(Comparator.reverseOrder());
        int occVal = sortedloccs.get((sortedloccs.size() - 1)/ 2);
        return rulesPos.get(occVal);
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

