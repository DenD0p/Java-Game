package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import java.util.List;

public class InvalidCommand extends Command
{  
    public InvalidCommand(List<String> commandToExec)
    {
        super(commandToExec);
    }
    
    @Override
    public String execute(Context ctx)
    {
        System.out.println("Unknown command, see help to explore your options!");
        return "";
    }

}