package org.uob.a2.parser;

import org.uob.a2.*;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


public class Parser 
{
    private String command;
    public Parser()
    {
        this.command = "";
    }

    public Command parse(String command)
    {
        List<String> toParse = Arrays.asList(command.trim().split(" "));
        switch(toParse.get(0).toLowerCase())
        {
            case "build":
            case "b":
                return new BuildCommand(toParse);
            case "cheat":
            case "c":
                return new CheatCommand(toParse);
            case "graph":
            case "g":
                return new GraphCommand(toParse);
            case "help":
            case "h":
                return new HelpCommand(toParse);
            case "info":
            case "i":
                return new InfoCommand(toParse);
            case "launch":
            case "la":
                return new LaunchCommand(toParse);
            case "load":
            case "lo":
                return new LoadCommand(toParse);
            case "quit":
            case "q":
                return new QuitCommand(toParse);
            case "save":
            case "s":
                return new SaveCommand(toParse);
            case "tick":
            case "t":
            case "":
                return new TickCommand(toParse);
            default:
                return new InvalidCommand(toParse);
        }
    }
}

    
