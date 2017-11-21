package org.xblackcat.euler.problem4;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;

/**
 * https://projecteuler.net/problem=4
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
@InputData("3")
@ResultDescription("Largest palindrome product for {1} digits: {0,number,#}")
public class Problem4 {
    @EntryPoint
    public long largestPalindromeWithFactorOfDigits(int digits) {
        final long powLower = (long) (Math.pow(10, digits - 1));
        final long pow = (long) (Math.pow(10, digits));

        long maxPalindrome = 0;

        long subFactor = pow / 11;
        while (subFactor > 0) {
            long firstFactor = 11 * subFactor;
            if (firstFactor < powLower) {
                break;
            }
            long part = pow;

            while (part-- > 0) {
                final long palindrome = makePalindrome(part);
                long secondFactor = palindrome / firstFactor;
                if (secondFactor < powLower) {
                    break;
                }
//                System.out.println(palindrome + ": " + firstFactor + " * " + secondFactor + " + " + (palindrome % firstFactor));
                if (palindrome % firstFactor == 0 && secondFactor < pow) {
                    if (maxPalindrome < palindrome) {
                        maxPalindrome = palindrome;
                    }
                }
            }
            subFactor--;
        }

        return maxPalindrome;
    }

    protected static long makePalindrome(long number) {
        long n = number;
        long suffix = 0;
        while (n > 0) {
            suffix *= 10;
            number *= 10;
            suffix += n % 10;
            n /= 10;
        }

        return number + suffix;
    }
}
