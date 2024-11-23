package org.example.brackets;

import java.util.Scanner;

public class Brackets {

    public static void showCatalanNumber() {
        //Enter number from console
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number N: ");

        //Number of opening and closing brackets. The data type indicates that the number is an integer
        int N = scanner.nextInt();

        //Number must be a positive
        if (N < 0) {
            System.out.println("The number must be a positive");
            return;
        }

        //Finding the number of bracket expressions
        long requiredNumber = calculateBracketExpression(N);

        System.out.println("Number of correct bracket expressions for N = " + N + " is: " + requiredNumber);
    }

    private static long calculateBracketExpression(int n) {

        //According to the rule, if the number of brackets is 0,
        // then the number of bracket expressions equal to 1 is returned
        if (n == 0) {
            return 1;
        }

        //Array catNum[i] save number of correct bracket expressions for i pairs of brackets
        long[] catNum = new long[n + 1];
        catNum[0] = 1;

        //Let's implement the Catalan number formula through a double cycle c(n) = sum(n-1|i=0) c(i)*c(n-1-i)
        for (int i = 1; i <= n; i++) {
            catNum[i] = 0;
            for (int j = 0; j < i; j++) {
                catNum[i] += catNum[j] * catNum[i - j - 1];
            }
        }
        return catNum[n];
    }
}
