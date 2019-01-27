package com.srdjancosic;

import java.util.Arrays;

public class GameBoard {
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
        return 0 < value && value < 5;
    }

    public boolean correct() {
        boolean hasDiscrepancies = false;
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                final int value = this.slots[row][column];
                if (value == 0) {
                    hasDiscrepancies = true;
                    System.out.printf(
                        "Discrepancy: [row %d, column %d] has an empty value%n",
                        row, column
                    );
                    continue;
                }

                for (int i = column + 1; i < 4; i++) {
                    final boolean slotHasValue = this.compareSlots(row, column, row, i);
                    if (!hasDiscrepancies) hasDiscrepancies = slotHasValue;
                }

                for (int i = row + 1; i < 4; i++) {
                    final boolean slotHasValue = this.compareSlots(row, column, i, column);
                    if (!hasDiscrepancies) hasDiscrepancies = slotHasValue;
                }
            }
        }
        return !hasDiscrepancies;
    }

    private boolean compareSlots(int row1, int column1, int row2, int column2) {
        if (this.slots[row1][column1] == this.slots[row2][column2]) {
            System.out.printf(
                "Discrepancy: [row %d, column %d] and [row %d, column %d] are the same%n",
                row1, column1, row2, column2
            );
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(
            this.slots
        ).replace("[[", ""
        ).replaceAll("[\\[\\]]", System.lineSeparator()
        ).replace("]]", ""
        ).replace(",", ""
        ).replace("0", "-");
    }
}
