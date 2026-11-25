package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import java.util.List;

public class QuitCommand extends Command
{  
    public QuitCommand(List<String> commandToExec)
    {
        super(commandToExec);
    }

    @Override
    public String execute(Context ctx)
    {
        System.out.println("Thanks for playing, hope to see you again!");
        System.exit(0);
        return "";
    }

}