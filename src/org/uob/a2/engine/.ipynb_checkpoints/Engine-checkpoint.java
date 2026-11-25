package org.uob.a2.engine;

import org.uob.a2.model.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Engine {

    private int currentTick;
    private SimulationState state;

    public Engine(SimulationState state)
    {
        this.state = state;
        currentTick = 0;
        state.updateResource(ResourceType.CREDITS, 1000);
    }
    // USED FOR TESTING
    public void initialiseDefaults() {
        state.setProducers(new IronMine("Iron Mine", ResourceType.IRON_ORE, 3));
        state.setProducers(new Forest("Forest", ResourceType.WOOD, 5));
        state.setProducers(new WaterPump("River Water Pump", ResourceType.WATER_BARREL, 10));
        state.setProducers(new FlowerGarden("Flower Field", ResourceType.FLOWER, 5));
        state.setConverters(new Carpentry("Carpentry", ResourceType.WOOD, 10, ResourceType.WOODEN_PLANK, 2));
        state.setConverters(new Furnace("Furnace", ResourceType.IRON_ORE, 15, ResourceType.WOOD, 10, ResourceType.IRON_BAR, 1));
        state.setConverters(new AlchemicalFacility("Alchemistry", ResourceType.FLOWER, 100, ResourceType.WATER_BARREL, 100, ResourceType.IRON_BAR, 10, ResourceType.GOLDEN_BAR, 1));
        state.setConverters(new WoodcuttingFacility("Woodcarving", ResourceType.WOODEN_PLANK, 5, ResourceType.FURNITURE, 1));
        state.setConsumers(new AuctionHouse("Metalurgical Auction House", ResourceType.GOLDEN_BAR, 1, ResourceType.CREDITS, 1000));
        state.setConsumers(new AuctionHouse("Household item shop", ResourceType.FURNITURE, 1, ResourceType.CREDITS, 50));
    }

    public String nextTick()
    {
        System.out.println("Day " + currentTick);
        Context context = new Context(this, state);
        for(Tickable s : state.getProducers())
        {
            s.tick(context);
        }
        for(Tickable s : state.getConverters())
        {
            s.tick(context);
        }
        for(Tickable s : state.getConsumers())
        {
            s.tick(context);
        }
        state.updateHistory();
        currentTick += 1;

        return "Next day";
    }

    public String save(String filename)
    {
        File dataDir = new File("data");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/" + filename)))
        {
            bw.write("Resources");
            bw.newLine();
            bw.write("CurrentTick,"  + currentTick);
            bw.newLine();
            for (Map.Entry<ResourceType, Integer> entry : state.getInventory().entrySet()) 
            {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
            for(Producer p : state.getProducers())
            {
                bw.write(p.toCSV()); //string formating to store it as 2 first letters representing class and rest being name of the object, could store lvl as well
                bw.newLine();
            }
            for(Converter p : state.getConverters())
            {
                bw.write(p.toCSV()); //string formating to store it as 2 first letters representing class and rest being name of the object, could store lvl as well
                bw.newLine();
            }
            for(Consumer p : state.getConsumers())
            {
                bw.write(p.toCSV()); //string formating to store it as 2 first letters representing class and rest being name of the object, could store lvl as well
                bw.newLine();
            }
            return "Successfully saved the progress!";
        }
        catch(IOException e)
        {
            return "Something went wrong when saving, please try again.";
        }
    }

    public String load(String filename)
    {
        try(BufferedReader br = new BufferedReader(new FileReader("data/" + filename)))
        {    
            clearInfo(state.getInventory(), state);
            String line;
            while((line = br.readLine()) != null)
            {
                String[] splitLine = line.split(",");
                if(splitLine[0].equals("CurrentTick"))
                {
                    try
                    {
                        currentTick = Integer.parseInt(splitLine[1]);
                    }
                    catch(NumberFormatException e)
                    {
                        return "Something went wrong reading the savefile. Credits records do not have a numerical value.";
                    }
                }
                else if(splitLine[0].equals("Resources")) {continue;}
                else
                {
                    if(splitLine[0].toUpperCase().equals(splitLine[0]))
                    {
                        try
                        {
                            ResourceType resource = ResourceType.valueOf(splitLine[0]);
                            int amount = Integer.parseInt(splitLine[1]);
                            state.updateResource(resource, amount);
                        }
                        catch(Exception e)
                        {
                            return "Something went wrong when reading the contents of a file! Please, Try again.";
                        }
                    }
                    else
                    {
                        loadBuildings(line, state);
                    }
                }
            }
        }
        catch(Exception e)
        {
            return "File not found.";
        }

        return "Successfully read the file!";
    }

    public void clearInfo(Map<ResourceType, Integer> inventory, SimulationState state)
    {
        for(Map.Entry<ResourceType, Integer> e : state.getInventory().entrySet())
        {
            state.updateResource(e.getKey(), 0);
        }
        state.removeBuildings();
    }

    public void loadBuildings(String line, SimulationState state)
    {
        String[] splitLine = line.split(",");
        if(line.startsWith("Ir"))
        {
            state.setProducers(new IronMine(splitLine[0].substring(2), 
                                            ResourceType.IRON_ORE, Integer.parseInt(splitLine[1])));
        }
        else if(line.startsWith("Fo"))
        {
            state.setProducers(new Forest(splitLine[0].substring(2), 
                                          ResourceType.WOOD, Integer.parseInt(splitLine[1])));
        }
        else if(line.startsWith("Fl"))
        {
            state.setProducers(new FlowerGarden(splitLine[0].substring(2), 
                                                ResourceType.FLOWER, Integer.parseInt(splitLine[1])));
        }
        else if(line.startsWith("Wa"))
        {
            state.setProducers(new WaterPump(splitLine[0].substring(2), 
                                             ResourceType.WATER_BARREL, Integer.parseInt(splitLine[1])));
        }
        else if(line.startsWith("Ca"))
        {
            state.setConverters(new Carpentry(splitLine[0].substring(2), 
                                              ResourceType.WOODEN_PLANK, Integer.parseInt(splitLine[1]), 
                                              ResourceType.FURNITURE, Integer.parseInt(splitLine[2])));
        }
        else if(line.startsWith("Wo"))
        {
            state.setConverters(new WoodcuttingFacility(splitLine[0].substring(2), 
                                                        ResourceType.WOOD, Integer.parseInt(splitLine[1]), 
                                                        ResourceType.WOODEN_PLANK, Integer.parseInt(splitLine[2])));
        }
        else if(line.startsWith("Al"))
        {
            state.setConverters(new AlchemicalFacility(splitLine[0].substring(2), 
                                                       ResourceType.WATER_BARREL, Integer.parseInt(splitLine[1]), 
                                                       ResourceType.FLOWER, Integer.parseInt(splitLine[2]), 
                                                       ResourceType.IRON_BAR, Integer.parseInt(splitLine[3]), 
                                                       ResourceType.GOLDEN_BAR, Integer.parseInt(splitLine[4])));
        }
        else if(line.startsWith("Fu"))
        {
            state.setConverters(new Furnace(splitLine[0].substring(2), 
                                            ResourceType.WOOD, Integer.parseInt(splitLine[1]), 
                                            ResourceType.IRON_ORE, Integer.parseInt(splitLine[2]), 
                                            ResourceType.IRON_BAR, Integer.parseInt(splitLine[3])));
        }
        else if(line.startsWith("Au"))
        {
            state.setConsumers(new AuctionHouse(splitLine[0].substring(2), 
                                                ResourceType.GOLDEN_BAR, Integer.parseInt(splitLine[1]), 
                                                ResourceType.CREDITS, Integer.parseInt(splitLine[2])));
        }
        else if(line.startsWith("Ho"))
        {
            state.setConsumers(new HouseholdShop(splitLine[0].substring(2), 
                                                 ResourceType.FURNITURE, Integer.parseInt(splitLine[1]), 
                                                 ResourceType.CREDITS, Integer.parseInt(splitLine[2])));
        }
    }   

    public int getCurrentTick(){return currentTick;}
}