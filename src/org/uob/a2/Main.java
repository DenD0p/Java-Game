package org.uob.a2;
import org.uob.a2.engine.*;
import org.uob.a2.parser.*;
import java.util.Scanner;
import org.uob.a2.model.ResourceType;
import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        SimulationState state = new SimulationState();
        Engine engine = new Engine(state);
        Context ctx = new Context(engine, state);
        Scanner input = new Scanner(System.in);
        Parser parser = new Parser();
        System.out.println(
            """
            Welcome, player.
            Thanks for playing my game.
            To begin if you need assistance, use \"help\".
            Good luck with your game exploration!""");
        while(true)
        {
            String userInput = input.nextLine();
            Command cmd = parser.parse(userInput);
            try
            {
                cmd.execute(ctx);
            }
            catch(Exception e)
            {
                System.out.println("Unknown command, see help to explore your options!");
            }
        }
    }
} 