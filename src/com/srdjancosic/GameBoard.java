package com.srdjancosic;

import java.util.Arrays;

class GameBoard {
    private int[][] slots;

    private int[][] reservedSlots = {
        {0, 2},
        {1, 1}, {1, 3},
        {2, 0}, {2, 2},
        {3, 1}
    };

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

    void insert(int row, int column, int value) {
        this.slots[row][column] = value;
    }

    boolean indexesAreValid(int row, int column) {
        if (row < 0 || row > 3) return false;
        if (column < 0 || column > 3) return false;
        for (int[] reservedSlot : this.reservedSlots) {
            if (reservedSlot[0] == row && reservedSlot[1] == column) return false;
        }
        return true;
    }

    boolean isValueValid(int value) {
        return 0 < value  && value < 10;
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
