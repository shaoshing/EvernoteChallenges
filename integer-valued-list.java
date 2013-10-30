import java.io.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberLength = Integer.parseInt(br.readLine());

        int[] numbers = new int[numberLength];
        for (int i = 0; i < numberLength; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        long totalProduct = 1;
        int countOfZero = 0;
        for (int i = 0; i < numberLength; i++) {
            if (numbers[i] != 0){
              totalProduct *= numbers[i];
            }else{
              countOfZero ++;
              if (countOfZero == 2){
                break;
              }
            }
        }

        for (int i = 0; i < numberLength; i++) {
            if (countOfZero == 2){
              System.out.println("0");
            }else if (countOfZero == 1){
              if (numbers[i] == 0){
                System.out.println(totalProduct);
              }else{
                System.out.println("0");
              }
            }else{
              System.out.println(totalProduct/numbers[i]);
            }
        }
    }
}
