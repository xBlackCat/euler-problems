package org.xblackcat.euler.problem19;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * https://projecteuler.net/problem=19
 * <p>
 * 28.11.2017 12:23
 *
 * @author xBlackCat
 */
@InputData({"1 Jan 1901", "31 Dec 2000"})
@ResultDescription("{0} Sundays fell on the first of the month during ({1} to {2})")
public class Problem19 {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.US);

    @EntryPoint
    public int firstMonthDaySundays(String fromStr, String toStr) {
        LocalDate from = LocalDate.parse(fromStr, FORMAT);
        LocalDate to = LocalDate.parse(toStr, FORMAT);

        if (from.getDayOfMonth() > 1) {
            from = from.withDayOfMonth(1).plusMonths(1);
        }

        int counts = 0;

        while (from.isBefore(to)) {
            if (from.getDayOfWeek() == DayOfWeek.SUNDAY) {
                counts++;
            }
            from = from.plusMonths(1);
        }

        return counts;
    }
}
