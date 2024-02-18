package tests;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LireData {
    public static void main(String[] args) {
        try {
            FileReader flot = new FileReader("tests/" + args[0]) ;
            Scanner sc = new Scanner(flot);
            int[] alph = new int[26];
            Arrays.fill(alph, 0);
            while(sc.hasNextLine()){
                alph[sc.nextLine().charAt(0) - 'a']++;
            }
            for(int i = 0; i < 26; i++)
            {
                System.out.print((char)(i + 'a'));
                System.out.println(" - " + String.valueOf(alph[i]));
            }
            sc.close();
        } catch (IOException e) {}
    }
}
