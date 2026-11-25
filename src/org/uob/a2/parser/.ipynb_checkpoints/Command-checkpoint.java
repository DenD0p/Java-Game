package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import java.util.List;
import java.util.ArrayList;

public abstract class Command {

    protected List<String> words;
   

    public Command (List<String> words) {
       
        this.words = new ArrayList<>(words);
    }

    public Command () {}
    
    public abstract String execute(Context ctx);
}