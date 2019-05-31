package org.xblackcat.euler.problem124;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.TFactorizer;

import java.util.Comparator;
import java.util.stream.LongStream;

/**
 * https://projecteuler.net/problem=124
 * <p>
 * 28.11.2017 12:23
 *
 * @author xBlackCat
 */
@InputData({"10000", "100000"})
@ResultDescription("E({1,number,#}) for 1<=n<={2,number,#} is {0,number,#}")
public class Problem124 {
    private final TFactorizer factorizer = new TFactorizer();

    @EntryPoint
    public long firstMonthDaySundays(int k, int n) {
        return LongStream.rangeClosed(1, n)
                .mapToObj(i -> new long[]{rad(i), i})
                .sorted(Comparator.<long[]>comparingLong(l -> l[0]).thenComparing(l -> l[1]))
                .skip(k-1)
                .findFirst()
                .orElseThrow()[1];
    }

    private long rad(long i) {
        if (factorizer.getPrimalCache().isPrimal(i)) {
            return i;
        } else {
            long rad = 1;
            for (long f : factorizer.factorize(i).keys()) {
                rad *= f;
            }
            return rad;
        }
    }
}
