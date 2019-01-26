package com.srdjancosic.commands;

import com.srdjancosic.GameBoard;

public interface Command {
    void call(GameBoard gameBoard);
}
