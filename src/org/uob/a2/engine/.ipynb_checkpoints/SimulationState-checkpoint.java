package org.uob.a2.engine;

import org.uob.a2.model.*;

import java.util.List;
import java.util.ArrayList;

import java.util.EnumMap;
import java.util.Map;
import java.util.HashMap;

public class SimulationState {

    private List<Producer> producers = new ArrayList<Producer>();
    private List<Converter> converters = new ArrayList<Converter>();
    private List<Consumer> consumers = new ArrayList<Consumer>();
    private Map<ResourceType, Integer> inventory = new EnumMap<>(ResourceType.class);
    private List<Map<ResourceType, Integer>> resourceHistory = new ArrayList<>();

    public List<Producer> getProducers() {
        return producers;
    }

    public List<Converter> getConverters() {
        return converters;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public void setProducers(Producer producer)
    {
        producers.add(producer);
    }

    public void setConverters(Converter converter)
    {
        converters.add(converter);
    }

    public void setConsumers(Consumer consumer)
    {
        consumers.add(consumer);
    }

    public void addResource(ResourceType resource, int amount)
    {
        inventory.merge(resource, amount, Integer::sum);
    }
    
    public void updateResource(ResourceType resource, int amount)
    {
        inventory.put(resource, amount);
    }

    public boolean removeResource(ResourceType resource, int amount)
    {    
        int tempStorage = inventory.get(resource);
        inventory.merge(resource, -amount, Integer::sum);
        
        if(inventory.get(resource) < 0)
        {
            System.out.println("Impossible to perform operation, not enough resources.");
            inventory.put(resource, tempStorage);
            return false;
        }
        return true;
    }

    public int getResourceAmount(ResourceType resource)
    {
        return inventory.getOrDefault(resource, 0);
    }

    public void updateHistory()
    {
        resourceHistory.add(new HashMap<ResourceType, Integer>(inventory));
    }
    
    public List<Map<ResourceType, Integer>> getHistory()
    {
        return resourceHistory;
    }

    public void removeBuildings()
    {
        producers.clear();
        converters.clear();
        consumers.clear();
    }
    
    public Map<ResourceType, Integer> getInventory(){return inventory;}
    
}