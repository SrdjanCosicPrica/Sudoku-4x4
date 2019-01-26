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
        return 0 < value && value < 10;
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
                    final boolean slotHasValue = this.slotHasValue(value, row, i);
                    if (!hasDiscrepancies) hasDiscrepancies = slotHasValue;
                }

                for (int i = row + 1; i < 4; i++) {
                    final boolean slotHasValue = this.slotHasValue(value, i, column);
                    if (!hasDiscrepancies) hasDiscrepancies = slotHasValue;
                }
            }
        }
        return !hasDiscrepancies;
    }

    private boolean slotHasValue(int value, int row, int column) {
        final int otherValue = this.slots[row][column];
        if (value == otherValue) {
            System.out.printf(
                "Discrepancy: [row %d, column %d] and [row %d, column %d] are the same%n",
                row, column, row, column
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
