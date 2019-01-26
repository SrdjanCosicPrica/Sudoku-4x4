package com.srdjancosic;

import com.srdjancosic.commands.Command;
import com.srdjancosic.commands.HelpCommand;
import com.srdjancosic.commands.QuitCommand;
import com.srdjancosic.commands.ResetCommand;

import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static Map<String, Command> choices = Map.of(
        "reset", new ResetCommand(),
        "quit", new QuitCommand(),
        "help", new HelpCommand()
    );

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();

        boolean printInfo = true;
        while (true) {
            if (printInfo) {
                System.out.println(gameBoard.toString());
                System.out.println("Tip: You can write a command at any time. " +
                    "Write \"help\" to see list of commands"
                );
            } else printInfo = true;


            final String choice = scanner.next();
            choices.get(choice).call(gameBoard);

            if (choice.equals("help")) printInfo = false;
        }
    }
}
