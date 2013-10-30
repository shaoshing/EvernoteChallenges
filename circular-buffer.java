import java.io.*;
import java.util.*;
import java.lang.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int bufferSize = Integer.parseInt(br.readLine());

        CircularBuffer buffer = new CircularBuffer(bufferSize);

        while(true){
          String[] command = br.readLine().split(" ", 2);
          if (command[0].equals("Q")){
            break;
          }

          switch(command[0]){
            case "A":
              int itemsCount = Integer.parseInt(command[1]);
              String[] items = new String[itemsCount];
              for (int i = 0; i < itemsCount; i++){
                items[i] = br.readLine();
              }
              buffer.add(items);
              break;
            case "R":
              int removalCount = Integer.parseInt(command[1]);
              buffer.remove(removalCount);
              break;
            case "L":
              buffer.list();
              break;
          }
        }
    }

    public static class CircularBuffer {
        private String[] buffer;
        private int maxSize, size, startOfBuffer, endOfBuffer;

        public CircularBuffer(int size){
          this.buffer = new String[size];
          this.maxSize = size;
          this.size = 0;
        }

        public void add(String[] items){
          for (String item : items) {
            if (this.size == 0){
              this.startOfBuffer = 0;
              this.endOfBuffer = 0;
              this.size = 1;
            }else{
              this.endOfBuffer = (this.endOfBuffer+1)%this.maxSize;
              if (this.endOfBuffer == this.startOfBuffer){
                this.startOfBuffer = (this.startOfBuffer+1)%this.maxSize;
                this.size--;
              }
              this.size++;
            }
            this.buffer[this.endOfBuffer] = item;
          }
        }

        public void remove(int removalCount){
          this.startOfBuffer = (this.startOfBuffer+removalCount)%this.maxSize;
          this.size -= removalCount;
        }

        public void list(){
          for(int i = 0; i < this.size; i++){
            System.out.println(this.buffer[(this.startOfBuffer+i)%this.maxSize]);
          }
        }
    }
}

