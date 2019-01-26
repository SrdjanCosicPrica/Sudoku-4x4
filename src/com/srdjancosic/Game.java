package com.srdjancosic;

import com.srdjancosic.commands.Command;
import com.srdjancosic.commands.HelpCommand;
import com.srdjancosic.commands.QuitCommand;
import com.srdjancosic.commands.ResetCommand;

import java.util.Map;
import java.util.Scanner;

class Game {
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
        System.out.print("Enter a row index between 0-3: ");
        if (scanner.hasNextInt()) {
            int row = scanner.nextInt();
            if (row < 1 || row > 3)
                return awaitColumnInput(row);
        }
        final String choice = scanner.next();
        choices.get(choice).call(gameBoard);
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
        System.out.print("Enter a value between 1-9: ");
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
