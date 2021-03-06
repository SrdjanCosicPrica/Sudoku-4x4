package com.srdjancosic.commands;

import com.srdjancosic.GameBoard;

public class HelpCommand implements Command {
    @Override
    public void call(GameBoard gameBoard) {
        System.out.println("" +
            "done: Corrects the Sudoku board" + System.lineSeparator() +
            "reset: Clears the game board back to initial values" + System.lineSeparator() +
            "quit: Quits the game"
        );
    }
}
