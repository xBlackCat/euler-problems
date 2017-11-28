package org.xblackcat.euler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 28.11.2017 11:59
 *
 * @author xBlackCat
 */
public class Node {
    public static Node loadTriangle(String resourceName) throws IOException {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(Node.class.getResourceAsStream(resourceName)))) {
            String[] lines = r.lines().toArray(String[]::new);

            int[] lastLine = parseLine(lines[lines.length - 1]);
            Node[] nodesCache = Arrays.stream(lastLine).mapToObj(Node::new).toArray(Node[]::new);

            for (int i = lines.length - 2; i >= 0; i--) {
                int[] line = parseLine(lines[i]);
                for (int j = 0; j < line.length; j++) {
                    nodesCache[j] = new Node(line[j], nodesCache[j], nodesCache[j + 1]);
                }
            }
            return nodesCache[0];
        }
    }

    private static int[] parseLine(String line) {
        String[] parts = line.split(" ");
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }
        return numbers;
    }

    private final int value;
    private final Node left;
    private final Node right;

    private int treeMax = -1;

    public Node(int value) {
        this(value, null, null);
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getTreeMax() {
        if (treeMax < 0) {
            treeMax = value +
                    Math.max(
                            left == null ? 0 : left.getTreeMax(),
                            right == null ? 0 : right.getTreeMax()
                    );
        }
        return treeMax;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "<" + value + ">";
    }
}
