import java.io.*;
import java.util.*;
import java.lang.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int wordLength = Integer.parseInt(br.readLine());

        ArrayList<String> words = new ArrayList<String>();
        for (int i = 0; i < wordLength; i++) {
            words.add(br.readLine());
        }

        HashMap<String, Integer> frequencies = new HashMap<String, Integer>();
        for (int i = 0; i < wordLength; i++) {
          Integer frequency = frequencies.get(words.get(i));
          if (frequency == null){
            frequency = 0;
          }
          frequencies.put(words.get(i), frequency+1);
        }

        ArrayList<String> sortedWords = new ArrayList<String>(frequencies.keySet());
        Collections.sort(sortedWords, new FrequencyCompare(frequencies));

        int outputWordLength = Integer.parseInt(br.readLine());
        for (int i = 0; i < outputWordLength; i++) {
          System.out.println(sortedWords.get(i));
        }
    }

    public static class FrequencyCompare implements Comparator<String> {
        private HashMap<String, Integer> frequencies;

        public FrequencyCompare(HashMap<String, Integer> frequencies){
          this.frequencies = frequencies;
        }

        @Override
        public int compare(String a, String b) {
            int frequency = this.frequencies.get(b).compareTo(this.frequencies.get(a));
            if (frequency == 0){
              return a.compareTo(b);
            } else{
              return frequency;
            }
        }
    }
}

