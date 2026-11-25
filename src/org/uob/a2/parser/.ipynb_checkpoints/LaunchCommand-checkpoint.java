package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import java.util.List;

public class LaunchCommand extends Command
{  
    public LaunchCommand(List<String> commandToExec)
    {
        super(commandToExec);
    }
    
    public String execute(Context ctx)
    {
        for(Consumer c : ctx.state().getConsumers())
        {
            c.tick(ctx);
        }
        return "";
    }

}