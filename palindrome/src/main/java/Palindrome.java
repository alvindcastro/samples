import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Palindrome {

    public Palindrome() {}

    public boolean isPalindrome(String characters) {

        int n = characters.length();
        for (int i = 0; i < (n/2); ++i) {
            if (characters.charAt(i) != characters.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public TreeMap<Integer,List<Pair<String, Integer>>> findAllPalindromes(String input) {
        TreeMap<Integer,List<Pair<String, Integer>>> treeMap = new TreeMap<>();
        int stringLength = input.length();
        for(int start = 0; start < stringLength; start++) {
            for(int end = stringLength-1; end > start; end--) {

                char charAtStart = input.charAt(start);
                char charAtEnd = input.charAt(end);

                if(charAtStart == charAtEnd){
                    if(isPalindrome(input.substring(start,end+1))) {
                        savePalindrome(input, treeMap, start, end);
                        start = end+1;
                        end = stringLength;
                    }
                }
            }
        }
        TreeMap<Integer,List<Pair<String, Integer>>> reverseTreeMap = new TreeMap(Collections.reverseOrder());
        reverseTreeMap.putAll(treeMap);

        return reverseTreeMap;
    }

    private void savePalindrome(String inputString, TreeMap<Integer, List<Pair<String, Integer>>> treePairs, int start, int end) {
        int charLength = end-start+1;

        Pair<String, Integer> pair;
        pair = Pair.of(inputString.substring(start,end+1), start);

        if(!treePairs.containsKey(charLength)) {
            List<Pair<String, Integer>> pairs = new ArrayList<>();
            pairs.add(pair);
            treePairs.put(charLength, pairs);
        } else {
            List<Pair<String, Integer>> pairs = treePairs.get(charLength);
            pairs.add(pair);
            treePairs.put(charLength, pairs);
        }
    }

    public String printPalindrome(String palindrome, int index, int length) {
        return "Text: " + palindrome + ", " + "Index: " +  index + ", " + "Length: " + length + "\n";
    }

    public String printNLongestPalindromes(String input, int num) {

        if(input == null)
            return "Input is null\n";
        if(input.equals(""))
            return "Input is empty\n";

        Palindrome palindrome = new Palindrome();
        StringBuilder output = new StringBuilder();

        int count = 0;
        TreeMap<Integer, List<Pair<String, Integer>>> treeMap = palindrome.findAllPalindromes(input);
        for (Map.Entry<Integer,List<Pair<String, Integer>>> entry : treeMap.entrySet()) {

            if(count == num) continue;

            for(Pair<String, Integer> pair : entry.getValue()){
                output.append(printPalindrome(pair.getLeft(), pair.getRight(), entry.getKey()));
            }
            count++;
        }
        return output.toString();
    }

    public int countNLongestPalindromes(String inputString, int num) {

        Palindrome palindrome = new Palindrome();

        int count = 0;
        TreeMap<Integer, List<Pair<String, Integer>>> treeMap = palindrome.findAllPalindromes(inputString);
        for (Map.Entry<Integer,List<Pair<String, Integer>>> entry : treeMap.entrySet()) {
            if(count == num) continue;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        String input = "sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop";
        System.out.println(palindrome.printNLongestPalindromes(input,3));
    }
}
