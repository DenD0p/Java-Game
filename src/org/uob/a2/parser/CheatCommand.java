package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import java.util.List;

public class CheatCommand extends Command
{  
    public CheatCommand(List<String> commandToExec)
    {
        super(commandToExec);
    }
    
    public String execute(Context ctx)
    {
        ctx.state().addResource(ResourceType.CREDITS, 10000);
        return "Successfully redeemed the cheat code!";
    }

}