package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import java.util.List;

public class SaveCommand extends Command
{  
    public SaveCommand(List<String> words)
    {
        super(words);
    }
    
    public String execute(Context ctx)
    {
        Engine engine = ctx.engine();
        return engine.save(words.get(1));
    }

}