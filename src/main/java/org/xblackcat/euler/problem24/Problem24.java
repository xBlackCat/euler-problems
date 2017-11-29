package org.xblackcat.euler.problem24;

import gnu.trove.list.TCharList;
import gnu.trove.list.array.TCharArrayList;
import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.FactorialCache;

import java.math.BigInteger;

/**
 * https://projecteuler.net/problem=24
 * <p>
 * 29.11.2017 15:04
 *
 * @author xBlackCat
 */

@InputData({"1000000"})
@ResultDescription("The {1,number,#}th lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9 is {0}")
public class Problem24 {
    @EntryPoint
    public String nthPermutation(long n) {
        FactorialCache factorialCache = new FactorialCache();

        n--;
        BigInteger limit = factorialCache.factorial(10);
        if (limit.longValueExact() <= n) {
            return "9876543210";
        }

        TCharList chars = new TCharArrayList(new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
        StringBuilder p = new StringBuilder();
        for (int i = 9; i > 0; i--) {
            long d = factorialCache.factorial(i).longValueExact();
            int amount = (int) (n / d);
            p.append(chars.removeAt(amount));
            n -= d * amount;
        }
        p.append(chars.removeAt(0));
        return p.toString();
    }
}
