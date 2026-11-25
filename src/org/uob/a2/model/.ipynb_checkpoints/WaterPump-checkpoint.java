package org.uob.a2.engine;

import org.uob.a2.model.ResourceType;
import java.util.Map;
import java.util.HashMap;

public class WaterPump extends Producer implements Tickable
{
    private static Map<ResourceType, Integer> buildingCost;
    
    public WaterPump(String name, ResourceType resource, int amount)
    {
        super(name, resource, amount);
    }
    
    public static Map<ResourceType, Integer> getBuildingCost() 
    {
        buildingCost = new HashMap<ResourceType, Integer>();
        buildingCost.put(ResourceType.CREDITS, 500);
        buildingCost.put(ResourceType.FURNITURE, 1);
        buildingCost.put(ResourceType.WOODEN_PLANK, 5);
        return buildingCost;
    }

    @Override
    public void produce(Context ctx)
    {
        ctx.state().addResource(product, amount);
    }

    @Override
    public void tick(Context ctx)
    {
        produce(ctx);
    }

    @Override
    public String toCSV()
    {
        return "Wa" + name + "," + amount;
    }

}