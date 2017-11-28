package org.xblackcat.euler.problem18;

import org.xblackcat.euler.ann.EntryPoint;
import org.xblackcat.euler.ann.InputData;
import org.xblackcat.euler.ann.ResultDescription;
import org.xblackcat.euler.util.Node;

import java.io.IOException;

/**
 * https://projecteuler.net/problem=18
 * <p>
 * 28.11.2017 10:38
 *
 * @author xBlackCat
 */
@InputData("/problem18/triangle.txt")
@ResultDescription("Max sum is {0,number,#}")
public class Problem18 {
    @EntryPoint
    public int maxSumOfTriangle(String resourceName) throws IOException {
        Node triangle = Node.loadTriangle(resourceName);

        return triangle.getTreeMax();
    }
}
