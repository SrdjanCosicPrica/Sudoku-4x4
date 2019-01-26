package com.srdjancosic;

import com.srdjancosic.commands.*;

import java.util.Map;
import java.util.Scanner;

class Game {
    private static Scanner scanner = new Scanner(System.in);
    private GameBoard gameBoard;

    private static Map<String, Command> choices = Map.of(
        "done", new DoneCommand(),
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
        System.out.print("Enter a row index between 0-3: ");
        if (scanner.hasNextInt()) {
            int row = scanner.nextInt();
            return awaitColumnInput(row);
        }
        final String choice = scanner.next();
        try {
            choices.get(choice).call(gameBoard);
        } catch (NullPointerException ignored) {
            System.out.printf("Unrecognized command %s%n", choice);
        }
        return !choice.equals("help");
    }

    private boolean awaitColumnInput(int row) {
        System.out.print("Enter a column index between 0-3: ");
        if (scanner.hasNextInt()) {
            int column = scanner.nextInt();
            if (!this.gameBoard.indexesAreValid(row, column)) {
                System.out.println("Those indexes are not valid. (They might be reserved)");
                return false;
            }
            return this.awaitValueInput(row, column);
        }
        System.out.println("That value is not a number");
        return false;
    }

    private boolean awaitValueInput(int row, int column) {
        System.out.print("Enter a value between 1-4: ");
        if (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            if (!this.gameBoard.isValueValid(value)) {
                System.out.println("That value is invalid");
                return false;
            }
            this.gameBoard.insert(row, column, value);
            return true;
        }
        System.out.println("That value is not a number");
        return false;
    }
}
