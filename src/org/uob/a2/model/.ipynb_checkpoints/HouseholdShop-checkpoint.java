package org.uob.a2.engine;

import org.uob.a2.*;
import org.uob.a2.model.*;
import java.util.Map;
import java.util.HashMap;

public class HouseholdShop extends Consumer implements Tickable
{
    protected ResourceType output;
    protected int outputAmount;
    private static Map<ResourceType, Integer> buildingCost;
    
    public HouseholdShop(String name, ResourceType resource, int amount, ResourceType output, int outputAmount)
    {
        super(name, resource, amount);
        this.output = output;
        this.outputAmount = outputAmount;
    }

    public static Map<ResourceType, Integer> getBuildingCost() 
    {
        buildingCost = new HashMap<ResourceType, Integer>();
        buildingCost.put(ResourceType.CREDITS, 400);
        buildingCost.put(ResourceType.FURNITURE, 5);
        return buildingCost;
    }

    @Override
    public void consume(Context ctx)
    {
        SimulationState sim = ctx.state();
        if(sim.getInventory().getOrDefault(consumedResource, 0) < amount)
        {
            System.out.println("Unable to sell material " + consumedResource.name() + ". Lacking resources.");
        }
        else
        {
            sim.removeResource(consumedResource, amount);
            sim.addResource(output, outputAmount);
        }
    }

    @Override
    public void tick(Context ctx)
    {
        consume(ctx);
    }

    @Override
    public String toCSV()
    {
        return "Ho" + name + "," + amount + "," + outputAmount;
    }
}