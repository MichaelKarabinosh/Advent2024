import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Day17 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/InputFile");
        // you now have a list of Strings from the file "InputFile"
        int regA = 0;
        int regB = 0;
        int regC = 0;
        ArrayList<Integer> program = new ArrayList<>();
        for (int i = 0; i < fileData.size(); i++) {
            String sub = fileData.get(i);
            if (sub.contains("A")) {
                regA = Integer.parseInt(sub.replace("Register A: ", ""));
            }
            if (sub.contains("B")) {
                regB = Integer.parseInt(sub.replace("Register B: ", ""));
            }
            if (sub.contains("C")) {
                regC = Integer.parseInt(sub.replace("Register C: ", ""));
            }
            if (sub.contains("Program")) {
                sub = sub.replace("Program: ", "");
                String[] nums = sub.split(",");
                for (int j = 0; j < nums.length; j++) {
                    program.add(Integer.parseInt(nums[j]));
                }
            }
        }
        ArrayList<Integer> output = testNums(regA, regB, regC, program);
        String pOne = "";
        for (int i = 0; i < output.size(); i++) {
            if (i != output.size() - 1) {
                pOne += output.get(i) + ",";
            } else {
                pOne += output.get(i);
            }
        }
        System.out.println("part one: " + pOne);
        ArrayList<Long> possibleN = new ArrayList<>(); // possible nums to check
        ArrayList<Long> temp = new ArrayList<>(); // actual list to check from, if input works -> added to possibleN to repeat
        ArrayList<Long> finalAns = new ArrayList<>();
        for (int i = 0; i < program.size(); i++) {
            if (i == 0) { // if the list is empty find all possible nums
                for (int j = 0; j < 8; j++) {
                    int t1 = testNums(j, regB, regC, program).getFirst();
                    if (t1 == program.getLast()) {
                        possibleN.add((long) j);
                    }
                }
            } else { // otherwise check these nums and if they produce next last digit, add to possibleN for next round checks
                temp.addAll(possibleN);
                possibleN.clear();
                for (int j = 0; j < temp.size(); j++) {
                    long x = temp.get(j) * 8; // for every x^8, the last num will remain the same
                    for (int q = 0; q < 8; q++) { // 1 - 8 shift
                        if (testNums(x, regB, regC, program).getFirst().equals(program.get(program.size() - 1 - i))) {
                            possibleN.add(x);
                        }
                        if (testNums(x, regB, regC, program).equals(program)) {
                            finalAns.add(x);
                        }
                        x++;
                    }
                }
                temp.clear(); // clears temp for checked numbers that didnt work -- this one line of code saves 20 seconds
            }
        }
        finalAns.sort(null);
        System.out.println("part two: " + finalAns.getFirst());
    }

    public static ArrayList<Integer> testNums(long a, int b, int c, ArrayList<Integer> program)
    {
        long a1 = a;
        long b1 = b;
        long c1 = c;
        int index1 = 0;
        long comboOperand1;
        boolean jump1 = false;
        int opCode1;
        int operand1;
        ArrayList<Integer> output2 = new ArrayList<>();
        while (true) {
            try {
                opCode1 = program.get(index1);
                operand1 = program.get(index1 + 1);
            } catch (Exception _) {
                break;
            }
            comboOperand1 = switch (operand1) {
                case 4 -> a1;
                case 5 -> b1;
                case 6 -> c1;
                default -> operand1;
            };
            switch (opCode1) {
                case 0:
                    a1 = a1 / ((int) (Math.pow(2, comboOperand1)));
                    break;
                case 1:
                    b1 = b1 ^ operand1;
                    break;
                case 2:
                    b1 = comboOperand1 % 8;
                    break;
                case 3:
                    if (a1 != 0) {
                        int tempIndex1 = index1;
                        index1 = operand1;
                        if (index1 != tempIndex1) {
                            jump1 = true;
                        }
                    }
                    break;
                case 4:
                    b1 = b1 ^ c1;
                    break;
                case 5:
                    String out = "" + comboOperand1 % 8;
                    for (int i = 0; i < out.length(); i++) {
                        output2.add(Integer.parseInt(out.substring(i, i + 1)));
                    }
                    break;
                case 6:
                    b1 = a1 / (int) (Math.pow(2, comboOperand1));
                    break;
                case 7:
                    c1 = a1 / (int) (Math.pow(2, comboOperand1));
                    break;
            }
            if (!jump1) {
                index1 += 2;
            }
            jump1 = false;
        }
        return output2;
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



