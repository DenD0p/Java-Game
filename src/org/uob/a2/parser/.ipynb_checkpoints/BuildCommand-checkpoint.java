package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class BuildCommand extends Command
{  
    public BuildCommand(List<String> commandToExec)
    {
        super(commandToExec);
    }

    public String execute(Context ctx)
    {
        switch(words.get(1).toLowerCase())
        {
            case "iron_mine":
                if(affordable(ctx.state(), IronMine.getBuildingCost()))
                {
                    ctx.state().setProducers(new IronMine(words.get(2), ResourceType.IRON_ORE, 1));
                }
                else
                {
                    System.out.println("Not enough resources, you cannot build it yet.");
                }
                break;
            case "water_pump":
                if(affordable(ctx.state(), WaterPump.getBuildingCost()))
                {
                    ctx.state().setProducers(new WaterPump(words.get(2), ResourceType.WATER_BARREL, 10));
                }
                else
                {
                    System.out.println("Not enough resources, you cannot build it yet.");
                }
                break;
            case "forest":
                if(affordable(ctx.state(), Forest.getBuildingCost()))
                {
                    ctx.state().setProducers(new Forest(words.get(2), ResourceType.WOOD, 5));
                }
                else
                {
                    System.out.println("Not enough resources, you cannot build it yet.");
                }
                break;
            case "flower_garden":
                if(affordable(ctx.state(), FlowerGarden.getBuildingCost()))
                {
                    ctx.state().setProducers(new FlowerGarden(words.get(2), ResourceType.FLOWER, 10));
                }
                else
                {
                    System.out.println("Not enough resources, you cannot build it yet.");
                }
                break;
            case "alchemical_facility":
                if(affordable(ctx.state(), AlchemicalFacility.getBuildingCost()))
                {
                    ctx.state().setConverters(new AlchemicalFacility(words.get(2), ResourceType.FLOWER, 100, ResourceType.IRON_BAR, 50, ResourceType.WATER_BARREL, 1000, ResourceType.GOLDEN_BAR, 1));
                }
                else
                {
                    System.out.println("Not enough resources, you cannot build it yet.");
                }
                break;
            case "woodcutting_facility":
                if(affordable(ctx.state(), WoodcuttingFacility.getBuildingCost()))
                {
                    ctx.state().setConverters(new WoodcuttingFacility(words.get(2), ResourceType.WOOD, 10, ResourceType.WOODEN_PLANK, 2));
                }
                else
                {
                    System.out.println("Not enough resources, you cannot build it yet.");
                }
                break;
            case "furnace":
                if(affordable(ctx.state(), Furnace.getBuildingCost()))
                {
                    ctx.state().setConverters(new Furnace(words.get(2), ResourceType.WOOD, 5, ResourceType.IRON_ORE, 5, ResourceType.IRON_BAR, 1));
                }
                else
                {
                    System.out.println("Not enough resources, you cannot build it yet.");
                }
                break;
            case "carpentry":
                if(affordable(ctx.state(), Carpentry.getBuildingCost()))
                {
                    ctx.state().setConverters(new Carpentry(words.get(2), ResourceType.WOODEN_PLANK, 10, ResourceType.FURNITURE, 1));
                }
                else
                {
                    System.out.println("Not enough resources, you cannot build it yet.");
                }
                break;
            case "auction_house":
                if(affordable(ctx.state(), AuctionHouse.getBuildingCost()))
                {
                    ctx.state().setConsumers(new AuctionHouse(words.get(2), ResourceType.GOLDEN_BAR, 1, ResourceType.CREDITS, 1000));
                }
                else
                {
                    System.out.println("Not enough resources, you cannot build it yet.");
                }
                break;
            case "household_item_shop":
                if(affordable(ctx.state(), HouseholdShop.getBuildingCost()))
                {
                    ctx.state().setConsumers(new HouseholdShop(words.get(2), ResourceType.FURNITURE, 1, ResourceType.CREDITS, 50));
                }
                else
                {
                    System.out.println("Not enough resources, you cannot build it yet.");
                }
                break;
            default:
                System.out.println(new InvalidCommand(words).execute(ctx));
                break;
        }
    return "";
    }

    private boolean affordable(SimulationState state, Map<ResourceType, Integer> cost)
    {
        for (Map.Entry<ResourceType, Integer> m : cost.entrySet()) {
            int available = state.getInventory().getOrDefault(m.getKey(), 0);
            if (available < m.getValue()) {
                return false;
            }
        }
        for(Map.Entry<ResourceType, Integer> m : cost.entrySet())
        {
            state.removeResource(m.getKey(), m.getValue());
        }
        return true;
    }

}