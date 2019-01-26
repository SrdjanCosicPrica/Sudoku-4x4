package com.srdjancosic.commands;

import com.srdjancosic.GameBoard;

public class DoneCommand implements Command {
    @Override
    public void call(GameBoard gameBoard) {
        final boolean isCorrect = gameBoard.correct();
        if (isCorrect) {
            System.out.println("Well done!");
            System.exit(0);
        }
    }
}
