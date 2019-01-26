package com.srdjancosic.commands;

import com.srdjancosic.GameBoard;

public class ResetCommand implements Command {
    @Override
    public void call(GameBoard gameBoard) {
        gameBoard.clear();
    }
}
