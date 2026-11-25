package org.uob.a2.engine;

import java.util.Map;
import org.uob.a2.model.ResourceType;
import org.uob.a2.engine.SimulationState;
import org.uob.a2.engine.Context;
import java.util.Map;
import java.util.HashMap;


public class AlchemicalFacility extends Converter implements Tickable
{
    protected ResourceType input2;
    protected ResourceType input3;
    protected int inputAmount2;
    protected int inputAmount3;
    private static Map<ResourceType, Integer> buildingCost;

    
    public AlchemicalFacility(String name, ResourceType input1, int inputAmount1, ResourceType input2, int inputAmount2, ResourceType input3, int inputAmount3, ResourceType output, int outputAmount)
    {
        super(name, input1, inputAmount1, output, outputAmount);
        this.input2 = input2;
        this.input3 = input3;
        this.inputAmount2 = inputAmount2;
        this.inputAmount3 = inputAmount3;
    }
    
    public static Map<ResourceType, Integer> getBuildingCost() 
    {
        buildingCost = new HashMap<ResourceType, Integer>();
        buildingCost.put(ResourceType.CREDITS, 100);
        buildingCost.put(ResourceType.IRON_BAR, 10);
        buildingCost.put(ResourceType.WOODEN_PLANK, 10);
        return buildingCost;
    }

    @Override
    public void convert(Context ctx)
    {
        Map<ResourceType, Integer> inventory = ctx.state().getInventory();
        if((inventory.getOrDefault(input, 0) < inputAmount) || 
           (inventory.getOrDefault(input2, 0) < inputAmount2) || 
           (inventory.getOrDefault(input3, 0) < inputAmount3))
        {
            if(inventory.getOrDefault(input, 0) < inputAmount)
            {
                System.out.println("Cannot convert resources, not enough " + input.name());
            }
            if(inventory.getOrDefault(input2, 0) < inputAmount2)
            {
                System.out.println("Cannot convert resources, not enough " + input2.name());  
            }
            if(inventory.getOrDefault(input3, 0) < inputAmount3)
            {
                System.out.println("Cannot convert resources, not enough " + input3.name());  
            }
        }
        else
        {
            ctx.state().addResource(output, outputAmount);
            ctx.state().removeResource(input, inputAmount);
            ctx.state().removeResource(input2, inputAmount2);
            ctx.state().removeResource(input3, inputAmount3);
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
        return "Al" + name + "," + inputAmount + "," + inputAmount2 + "," + inputAmount3 + "," + outputAmount;
    }

}