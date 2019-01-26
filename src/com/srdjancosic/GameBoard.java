package com.srdjancosic;

import java.util.Arrays;

public class GameBoard {
    private int[][] slots;

    GameBoard() {
        this.initializeBoard();
    }

    private void initializeBoard() {
        this.slots = new int[][]{
            {0, 0, 3, 0},
            {0, 2, 0, 1},
            {1, 0, 2, 0},
            {0, 3, 0, 0}
        };
    }

    public void clear() {
        this.initializeBoard();
    }

    @Override
    public String toString() {
        return Arrays.deepToString(
            this.slots
        ).replace("[[", ""
        ).replaceAll("[\\[\\]]", System.lineSeparator()
        ).replace("]]", ""
        ).replace(",", "");
    }
}
