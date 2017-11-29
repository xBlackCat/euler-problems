package org.xblackcat.euler.problem22;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://projecteuler.net/problem=22
 * <p>
 * 28.11.2017 13:11
 *
 * @author xBlackCat
 */
@InputData("/problem22/names.txt")
@ResultDescription("The total of all the name scores in the file {1} is {0,number,#}")
public class Problem22 {
    @EntryPoint
    public long scoresSum(String resource) throws IOException {
        final String[] names;
        try (BufferedReader r = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(resource)))) {
            // Read CSV file..
            String line = r.readLine();
            names = line.substring(1, line.length() - 1).split("\",\"");
        }

        Arrays.sort(names);

        long sum = 0L;
        for (int i = 0; i < names.length; i++) {
            sum += (i + 1) * nameScore(names[i]);
        }
        return sum;
    }

    protected long nameScore(String name) {
        long score = 0;
        for (int i = 0; i < name.length(); i++) {
            score += Character.toLowerCase(name.charAt(i)) - 'a' + 1;
        }
        return score;
    }
}
