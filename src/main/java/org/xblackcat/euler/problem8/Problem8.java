package org.xblackcat.euler.problem8;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * https://projecteuler.net/problem=5
 * <p>
 * 16.11.2017 16:27
 *
 * @author xBlackCat
 */
public class Problem8 {
    private static final String BLOCK =
            "73167176531330624919225119674426574742355349194934" +
                    "96983520312774506326239578318016984801869478851843" +
                    "85861560789112949495459501737958331952853208805511" +
                    "12540698747158523863050715693290963295227443043557" +
                    "66896648950445244523161731856403098711121722383113" +
                    "62229893423380308135336276614282806444486645238749" +
                    "30358907296290491560440772390713810515859307960866" +
                    "70172427121883998797908792274921901699720888093776" +
                    "65727333001053367881220235421809751254540594752243" +
                    "52584907711670556013604839586446706324415722155397" +
                    "53697817977846174064955149290862569321978468622482" +
                    "83972241375657056057490261407972968652414535100474" +
                    "82166370484403199890008895243450658541227588666881" +
                    "16427171479924442928230863465674813919123162824586" +
                    "17866458359124566529476545682848912883142607690042" +
                    "24219022671055626321111109370544217506941658960408" +
                    "07198403850962455444362981230987879927244284909188" +
                    "84580156166097919133875499200524063689912560717606" +
                    "05886116467109405077541002256983155200055935729725" +
                    "71636269561882670428252483600823257530420752963450";

    public static void main(String[] args) {
        int till = 13;
        long start = System.currentTimeMillis();
        final Problem8 p = new Problem8();
        System.out.println(
                "Largest product in a series of " + till + " adjacent digits is " + p.largestProduct(till) +
                        " in " + (System.currentTimeMillis() - start) + " ms");
    }

    public long largestProduct(int seriesLength) {
        int seriesStart = 0;
        int limit = BLOCK.length() - seriesLength;

        SortedSet<S> series = new TreeSet<>();
        String possibleSeries = BLOCK.substring(0, seriesLength);
        int zeroIdx = possibleSeries.indexOf('0');
        if (zeroIdx >= 0) {
            seriesStart = zeroIdx + 1;
        } else {
            series.add(new S(possibleSeries));
            seriesStart++;
        }
        while (seriesStart < limit) {
            if (BLOCK.charAt(seriesStart + seriesLength - 1) == '0') {
                // Skip block with zeroes
                seriesStart += seriesLength;
                // Too lazy to check zero in the series after jump
                continue;
            }
            series.add(new S(BLOCK.substring(seriesStart, seriesStart + seriesLength)));
            seriesStart++;
        }

        return series.last().multiply();
    }

    private class S implements Comparable<S> {
        private final byte[] factors;

        private S(String part) {
            int length = part.length();
            factors = new byte[length];
            for (int i = 0; i < length; i++) {
                factors[i] = (byte) (part.charAt(i) - '0');
            }
            Arrays.sort(factors);
        }

        public long multiply() {
            long m = 1;
            for (byte f : factors) {
                m *= f;
            }
            return m;
        }

        @Override
        public int compareTo(S o) {
            for (int i = 0; i < factors.length; i++) {
                int c = factors[i] - o.factors[i];
                if (c != 0) {
                    return c;
                }
            }
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final S s = (S) o;
            return Arrays.equals(factors, s.factors);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(factors);
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            for (byte f : factors) {
                str.append((char) ('0' + f));
                str.append('*');
            }
            return str.substring(0, str.length());
        }
    }
}
