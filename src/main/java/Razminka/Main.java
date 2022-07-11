package Razminka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(reverseString(input));
        System.out.println(getTime(1000,input));
        System.out.println(reverseString(input));
        System.out.println(getTime(10000,input));
        System.out.println(reverseString(input));
        System.out.println(getTime(100000,input));
    }

    public static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static long getTime(int count,String s){
        long end = 0;
        for (int i = 0; i < count; i++) {
            long startTime = System.nanoTime();
            reverseString(s);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            end += duration;
        }
        return end;
    }
}
