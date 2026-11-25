package org.uob.a2.engine;

import org.uob.a2.model.ResourceType;
import java.util.Map;
import java.util.HashMap;

public class Furnace extends Converter implements Tickable
{
    protected int inputAmount2;
    protected ResourceType input2;
    private static Map<ResourceType, Integer> buildingCost;
    
    public Furnace(String name, ResourceType input, int inputAmount, ResourceType input2, int inputAmount2, ResourceType output, int outputAmount)
    {
        super(name, input, inputAmount, output, outputAmount);
        this.inputAmount2 = inputAmount2;
        this.input2 = input2;
    }

    public static Map<ResourceType, Integer> getBuildingCost() 
    {
        buildingCost = new HashMap<ResourceType, Integer>();
        buildingCost.put(ResourceType.CREDITS, 500);
        buildingCost.put(ResourceType.WOOD, 10);
        buildingCost.put(ResourceType.IRON_ORE, 10);
        return buildingCost;
    }
    
    @Override
    public void convert(Context ctx)
    {
        Map<ResourceType, Integer> inventory = ctx.state().getInventory();
        if(inventory.getOrDefault(input, 0) < inputAmount || inventory.getOrDefault(input2, 0) < inputAmount2)
        {
            if(inventory.getOrDefault(input, 0) < inputAmount)
            {
                System.out.println("Cannot convert resources, not enough " + input.name());
            }
            else
            {
                System.out.println("Cannot convert resources, not enough " + input2.name());
            }
        }
        else
        {
            ctx.state().addResource(output, outputAmount);
            ctx.state().removeResource(input, inputAmount);
            ctx.state().removeResource(input2, inputAmount2);
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
        return "Fu" + name + "," + inputAmount + "," + inputAmount2 + "," + outputAmount;
    }
}