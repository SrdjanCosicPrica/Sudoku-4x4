package com.srdjancosic;

import com.srdjancosic.commands.Command;
import com.srdjancosic.commands.HelpCommand;
import com.srdjancosic.commands.QuitCommand;
import com.srdjancosic.commands.ResetCommand;

import java.util.Map;
import java.util.Scanner;

public class Game {
    private static Scanner scanner = new Scanner(System.in);
    private GameBoard gameBoard;

    private static Map<String, Command> choices = Map.of(
        "reset", new ResetCommand(),
        "quit", new QuitCommand(),
        "help", new HelpCommand()
    );

    Game() {
        this.gameBoard = new GameBoard();
    }

    void play() {
        boolean printInfo = true;
        while (true) {
            if (printInfo) {
                System.out.println(this.gameBoard.toString());
                System.out.println("Tip: You can write a command at any time. " +
                    "Write \"help\" to see list of commands"
                );
            }
            printInfo = this.awaitInput(this.gameBoard);
        }
    }

    private boolean awaitInput(GameBoard gameBoard) {
        final String choice = scanner.next();
        choices.get(choice).call(gameBoard);
        return !choice.equals("help");
    }
}
