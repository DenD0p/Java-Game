package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import java.util.List;

public class TickCommand extends Command
{  
    public TickCommand(List<String> commandToExec)
    {
        super(commandToExec);
    }
    
    public String execute(Context ctx)
    {
        ctx.engine().nextTick();
        return "Next day";
    }

}