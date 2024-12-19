import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Day7 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/InputFile");
        ArrayList<Long> finalN = new ArrayList<>();
        ArrayList<String> combos = new ArrayList<>();
for (int i = 0; i < fileData.size(); i++)
{
    String [] nums = fileData.get(i).split(":");
    finalN.add((Long.parseLong(nums[0])));
    String s = nums[1];
     s = s.replaceFirst(" ", "");
    combos.add(s);
}
long numOps;
long totalS = 0;
long totalR =0;
for (int i = 0; i < finalN.size(); i++)
{
    String c = combos.get(i);
    String [] s = c.split(" ");
    numOps = (int) Math.pow(2, s.length - 1);
    for (int j = 0; j < numOps; j++)
    {
        String bin = Integer.toBinaryString(j);
        int numPads = s.length - 1 - bin.length();
        String padder = "";
        for (int k = 0; k < numPads; k++)
        {
            padder += "0";
        }
        bin = padder + bin;
        for (int k = 0; k < bin.length(); k++)
        {
            if (k == 0)
            {
                totalS = Integer.parseInt(s[0]);
            }

                try {
                    if (bin.charAt(k) == '0') {
                        totalS += Integer.parseInt(s[k+1]);
                    } else if (bin.charAt(k) == '1') {
                        totalS *= Integer.parseInt(s[k+1]);
                    }
                } catch (Exception _) {

                }
        }
        if (totalS == finalN.get(i))
        {
            totalR += finalN.get(i);
            break;
        }
        totalS = 0;
    }
}
System.out.println("part one: " + totalR);
totalR = 0;
        for (int i = 0; i < finalN.size(); i++)
        {
            String c = combos.get(i);
            String [] s = c.split(" ");
            numOps = (long) Math.pow(3, s.length - 1);
            for (int j = 0; j < numOps; j++)
            {
                String bin = "" + asBase3(j);
                int numPads = s.length - 1 - bin.length();
                String padder = "";
                for (int k = 0; k < numPads; k++)
                {
                    padder += "0";
                }
                bin = padder + bin;
                for (int k = 0; k < bin.length(); k++)
                {
                    if (k == 0)
                    {
                        totalS = Integer.parseInt(s[0]);
                    }
                    try {
                        if (bin.charAt(k) == '0') {
                            totalS += Long.parseLong(s[k+1]);
                        } else if (bin.charAt(k) == '1') {
                            totalS *= Long.parseLong(s[k+1]);
                        }
                        else if (bin.charAt(k) == '2') {
                            String fac = totalS + s[k+1];
                            totalS = Long.parseLong(fac);
                        }

                    } catch (Exception _) {
                    }
                }
                if (totalS == finalN.get(i))
                {
                    totalR += finalN.get(i);
                    break;
                }
                totalS = 0;
            }
        }
        System.out.println("part two: " + totalR);

        // you now have a list of Strings from the file "InputFile"
    }


    public static long asBase3(int num) {
        long ret = 0, factor = 1;
        while (num > 0) {
            ret += num % 3 * factor;
            num /= 3;
            factor *= 10;
        }
        return ret;
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

