package org.xblackcat.euler.util;

import gnu.trove.map.TLongByteMap;
import gnu.trove.map.hash.TLongByteHashMap;

/**
 * 15.11.2017 16:41
 *
 * @author xBlackCat
 */
public class PrimalCache {
    private final TLongByteMap foundPrimals = new TLongByteHashMap(
            new long[]{1, 2, 3, 5, 7, 9, 11, 13, 15, 17, 19},
            new byte[]{0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1}
    );

    public boolean isPrimal(long num) {
//        long start = System.currentTimeMillis();
        final boolean primal = checkPrimal(num);
//        System.out.println(
//                "[-] Number " + num + " has been checked for primal [" + primal + "] in " + (System.currentTimeMillis() - start)
//        );
        return primal;
    }

    private boolean checkPrimal(long num) {
        if (foundPrimals.containsKey(num)) {
            return foundPrimals.get(num) == 1;
        }

        if (num % 2 == 0) {
            // Even numbers not primal
            return false;
        }

        // Long way
        long limit = (long) Math.sqrt(num);
        for (long i = 3; i <= limit; i += 2) {
            if (checkPrimal(i)) {
                if (num % i == 0) {
                    foundPrimals.put(num, (byte) 0);
                    return false;
                }
            }
        }

        foundPrimals.put(num, (byte) 1);
        return true;
    }
}
