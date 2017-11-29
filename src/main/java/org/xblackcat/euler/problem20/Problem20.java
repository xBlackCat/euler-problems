package org.xblackcat.euler.problem20;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.FactorialCache;
import org.xblackcat.euler.util.NumberUtils;

import java.math.BigInteger;

/**
 * https://projecteuler.net/problem=20
 * <p>
 * 28.11.2017 12:36
 *
 * @author xBlackCat
 */
@InputData("100")
@ResultDescription("The sum of the digits in the number {1,number,#}! is {0,number,#}")
public class Problem20 {
    @EntryPoint
    public int digitSum(int n) {
        FactorialCache factorialCache = new FactorialCache();
        BigInteger f = factorialCache.factorial(n);
        int sum = NumberUtils.digitSum(f);
        System.out.println(n + ": = " + f + " (" + sum + ")");
        return sum;
    }
}
