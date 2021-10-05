package edu.neu.coe.info6205.union_find;

import java.util.Random;
import java.util.Scanner;

public class UFClient {

    public static int count(int n) {
        UF_HWQUPC uf = new UF_HWQUPC(n);
        Random random = new Random();
        int count = 0;
        while (uf.components() > 1) {
            int p = random.nextInt(n);
            int q = random.nextInt(n);
            // make sure that if p and q is connected, if is not connected then union p and q
            uf.connect(p, q);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        // step 2 : input a number from the command line （eg. 100）
        System.out.println("please enter a number from the command line （eg. 100）");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("the number of objects is " + n + ", and the number of connections is " + count(n));

        System.out.println("part 3, test the relationship between m and n");
        // step 3 : determine the relationship between n and m
        for (int i = 1000; i < 160000; i *= 2) {
            int sum = 0;
            // test 10 times to get the average number
            for (int j = 0; j < 10; j++) {
                sum += count(i);
            }
            int meanNumber = sum / 10;
            System.out.println("the number of objects is " + i + ", and the number of pairs is " + meanNumber);
        }
    }
}
