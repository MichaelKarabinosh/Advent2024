import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Day11 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/InputFile");
        // you now have a list of Strings from the file "InputFile"
        int repititions = 25;
        String data = fileData.getFirst();
        String [] a = data.split(" ");
        ArrayList<String> b = new ArrayList<>(Arrays.asList(a));
        ArrayList<String> c = new ArrayList<>(b);
        HashMap<Long, Long> longs = new HashMap<>();
        for (String string : a) {
            longs.put(Long.parseLong(string), 1L);
        }
        for (int i = 0; i < repititions; i++)
        {
            c.clear();
            for (String string : b) {
                if (string.equals("0")) {
                    c.add("1");
                } else if (string.length() % 2 == 0) {
                    String s1 = string.substring(0, string.length() / 2);
                    String s2 = string.substring(string.length() / 2);
                    s1 = s1.replaceFirst("^0+(?!$)", "");
                    s2 = s2.replaceFirst("^0+(?!$)", "");
                    c.add(s1);
                    c.add(s2);
                } else {
                    long p = (long) Double.parseDouble(string) * 2024;
                    c.add("" + p);
                }
            }
            b = new ArrayList<>(c);
        }
        System.out.println("part one: " + b.size());

        repititions = 75;
        HashMap<Long, Long> blink = longs;
        for (int i = 0; i < repititions; i++)
        {
            blink = doBlink(blink);
        }
        Set<Long> keys = blink.keySet();
        Long size = 0L;
        for (Long key: keys)
        {
            size += blink.get(key);
        }
        System.out.println("part two: " + size);
    }

    

    public static HashMap<Long, Long> doBlink(HashMap<Long, Long> stones)
    {
        HashMap<Long, Long> afterBlink = new HashMap<>();
        Set<Long> keys = stones.keySet();
        for (Long key: keys)
        {
            String keyAsStr = key + "";
//
            if (key == 0)
            {
                if (afterBlink.get(key) == null) {
                    afterBlink.put(1L, stones.get(key));
                }
                else {
                    afterBlink.put(1L, stones.get(key) + 1L);
                }
            }
            else if (keyAsStr.length() % 2 == 0) {
                String  s1 = keyAsStr.substring(0, keyAsStr.length()/2);
                String s2 = keyAsStr.substring(keyAsStr.length()/2);
                Long l1 = Long.parseLong(s1);
                Long l2 = Long.parseLong(s2);

                if (afterBlink.get(l1) == null)
                {
                    afterBlink.put(l1, stones.get(key));
                }
                else {
                    afterBlink.put(l1, stones.get(key) + afterBlink.get(l1));
                }
                if (afterBlink.get(l2) == null)
                {
                    afterBlink.put(l2, stones.get(key));
                }
                else {
                    afterBlink.put(l2, stones.get(key) + afterBlink.get(l2));
                }
            }
            else {
                if (afterBlink.get(key * 2024) == null)
                {
                    afterBlink.put(key * 2024, stones.get(key));
                }
                else {
                    afterBlink.put(key * 2024, stones.get(key) + 1L);
                }
            }
        }
        return afterBlink;
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
