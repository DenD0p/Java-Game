package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import java.util.List;
import java.util.Map;
import org.uob.a2.model.ResourceType;

public class InfoCommand extends Command
{  
    public InfoCommand(List<String> commandToExec)
    {
        super(commandToExec);
    }
    
    public String execute(Context ctx)
    {
        String result = "";
        result += "Producers: ";
        for(Producer b : ctx.state().getProducers())
        {
            result += "\n"+ b.getClass().getSimpleName() + " - " + "\"" + b.getName() + "\"";
        }
        result += "\n------------------------------------------------\nConverters:";
        for(Converter b : ctx.state().getConverters ())
        {
            result += "\n"+ b.getClass().getSimpleName() + " - " + "\"" + b.getName() + "\"";
        }
        result += "\n------------------------------------------------ \nConsumers:";
        for(Consumer b : ctx.state().getConsumers())
        {
            result += "\n"+ b.getClass().getSimpleName() + " - " + "\"" + b.getName() + "\"";
        }
        result += " \n------------------------------------------------";
        System.out.println(result);
        
        System.out.println("Inventory:");
        Map<ResourceType, Integer> inventory = ctx.state().getInventory();
        for(Map.Entry<ResourceType, Integer> r : inventory.entrySet())
        {
            System.out.println(r.getKey() + " - " + r.getValue());
        }
        System.out.println("------------------------------------------------");
        return "";
    }

}