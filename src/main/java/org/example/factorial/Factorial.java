package org.example.factorial;

import java.math.BigInteger;
import java.util.Scanner;

public class Factorial {

    public static void showFactorial() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number equal to or less than 100");
        int number = scanner.nextInt();

        if (number > 100) {
            System.err.println("The number must be equal to or less than 100");
            return;
        }

        BigInteger factorial = calculateFactorial(number);
        System.out.println(number + "! = " + factorial);

        int sumOfDigits = sumOfDigits(factorial);
        System.out.println("The sum of digits in number " + number + "! : " + sumOfDigits);
    }

    private static BigInteger calculateFactorial(int number) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    private static int sumOfDigits(BigInteger number) {
        String numberAsString = number.toString();
        int sum = 0;
        for (char digit : numberAsString.toCharArray()) {
            sum += Character.getNumericValue(digit);
        }
        return sum;
    }
}
