package org.uob.a2.engine;

import org.uob.a2.model.ResourceType;
import java.util.Map;
import java.util.HashMap;

public class Carpentry extends Converter
{
    private static Map<ResourceType, Integer> buildingCost;
    
    public Carpentry(String name, ResourceType input, int inputAmount, ResourceType output, int outputAmount)
    {
        super(name, input, inputAmount, output, outputAmount);
    }
    
    public static Map<ResourceType, Integer> getBuildingCost() 
    {
        buildingCost = new HashMap<ResourceType, Integer>();
        buildingCost.put(ResourceType.CREDITS, 300);
        buildingCost.put(ResourceType.WOODEN_PLANK, 10);
        return buildingCost;
    }

    @Override
    public void convert(Context ctx)
    {
        Map<ResourceType, Integer> inventory = ctx.state().getInventory();
        if(inventory.getOrDefault(input, 0) < inputAmount)
        {
            System.out.println("Cannot convert resources, not enough " + input.name());
        }
        else
        {
            ctx.state().addResource(output, outputAmount);
            ctx.state().removeResource(input, inputAmount);
        }
    }

    @Override
    public void tick(Context ctx)
    {
        convert(ctx);
    }
    
    @Override
    public String toCSV()
    {
        return "Ca" + name + "," + inputAmount + "," + outputAmount;
    }
}