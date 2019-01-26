package com.srdjancosic.commands;

import com.srdjancosic.GameBoard;

public class QuitCommand implements Command {
    @Override
    public void call(GameBoard gameBoard) {
        System.exit(0);
    }
}
