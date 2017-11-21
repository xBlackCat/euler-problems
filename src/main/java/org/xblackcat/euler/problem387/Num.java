package org.xblackcat.euler.problem387;

import org.xblackcat.euler.util.NumberUtils;

/**
 * 21.11.2017 10:17
 *
 * @author xBlackCat
 */
class Num {
    private final long value;
    private final int digitSum;
    private final boolean isHarshad;
    private final long quotient;

    Num(long value) {
        this(value, NumberUtils.digitSum(value));
    }

    private Num(long value, int digitSum) {
        this.value = value;
        this.digitSum = digitSum;
        isHarshad = value % digitSum == 0;
        quotient = value / digitSum;
    }

    Num append(int digit) {
        return new Num(value * 10 + digit, digitSum + digit);
    }

    public long getValue() {
        return value;
    }

    public int getDigitSum() {
        return digitSum;
    }

    public boolean isHarshad() {
        return isHarshad;
    }

    public long getQuotient() {
        return quotient;
    }

    @Override
    public String toString() {
        return value + " (" + digitSum + "/" + quotient + "). " + (isHarshad ? "H " : "");
    }
}
