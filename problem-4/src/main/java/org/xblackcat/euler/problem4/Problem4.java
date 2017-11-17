package org.xblackcat.euler.problem4;

/**
 * https://projecteuler.net/problem=3
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
public class Problem4 {
    public static void main(String[] args) {
        int digits = 3;
        long start = System.currentTimeMillis();
        final Problem4 p = new Problem4();
        System.out.println(
                "Largest palindrome product for  " + digits + " digits: " + p.largestPalindromeWithFactorOfDigits(digits) +
                        " in " + (System.currentTimeMillis() - start) + " ms");
    }

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
