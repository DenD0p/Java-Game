package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import java.util.List;

public class LoadCommand extends Command
{  
    public LoadCommand(List<String> words)
    {
        super(words);
    }
    
    public String execute(Context ctx)
    {
        Engine engine = ctx.engine();
        return engine.load(words.get(1));
    }

}