package org.uob.a2.engine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.uob.a2.model.*;

public class SimulationState {

    private List<Producer> producers;
    private List<Converter> converters;
    private List<Consumer> consumers;
    private Map<String, Integer> tracker;
    private Map<ResourceType, Integer> inventory = new EnumMap<>(ResourceType.class);
    private List<Map<ResourceType, Integer>> resourceHistory = new ArrayList<>();

    public SimulationState(){
        //Initilises required arrays
        this.producers = new ArrayList<>();
        this.converters = new ArrayList<>();
        this.consumers = new ArrayList<>();
        this.tracker = new HashMap<String, Integer>();
    }

    public void addResource(ResourceType resource, int amount){
        //If the resource is already in the array, it adds the inputted amount to the amount already in the map
        //If the data is not already in the map, it adds it to the map with the amount specified
        if (this.inventory.get(resource) != null){
            this.inventory.replace(resource, this.inventory.get(resource) + amount);
        }
        else{
            this.inventory.put(resource, amount);
        }
    }

    public void updateResource(ResourceType resource, int amount){
        //If the resource is already in the array it replaces the amount currently stored with the inputted amount
        //If it is not in the resource is not in the map it adds it to the map
        if (this.inventory.get(resource) != null){
            this.inventory.replace(resource, amount);
        }
        else{
            this.inventory.put(resource, amount);
        }
    }

    public int getResourceAmount(ResourceType resource){
        //Gets the resource amount if it exists or returns 0
        if (this.inventory.get(resource) != null){
            return this.inventory.get(resource);
        }
        else{
            return 0;
        }
    }

    public boolean removeResource(ResourceType resource, int amount){
        //Reduces the specified resource by a specified amount and returns if it was successful
        if (this.inventory.get(resource) != null && this.inventory.get(resource) >= amount){
            this.inventory.replace(resource, this.inventory.get(resource) - amount);
            return true;
        }
        return false;
    }

    public void updateHistory(){
        //Updates history by adding the current inventory to the history list
        this.resourceHistory.add(new EnumMap<>(this.getInventory()));
    }
    
    public List<Producer> getProducers() {
        //Returns the list of producers
        return producers;
    }

    public void addProducer(Producer newProducer){
        //Adds the producer to the producer list
        this.producers.add(newProducer);
        //Adds the producers resource to the inventory
        if (this.getTracker(newProducer.getName()) == -1){
            this.addResource(newProducer.getProduct(), 0);
        }
    }

    public List<Converter> getConverters() {
        //Returns the list of converters
        return converters;
    }

    public void addConverter(Converter newConverter){
        //Adds the converter to the converter list
        this.converters.add(newConverter);
        //adds the producer's resource if it has never been used before
        if (this.getTracker(newConverter.getName()) == -1){
            this.addResource(newConverter.getOutput(), 0);
        }
    }

    public List<Consumer> getConsumers() {
        //returns the list of consumers
        return consumers;
    }

    public void addConsumer(Consumer newConsumer){
        //Adds the new consumer to the consumer list
        this.consumers.add(newConsumer);
    }

    public int getTracker(String entity){
        //finds if the building has been built before and if so how many
        if (this.tracker != null && this.tracker.get(entity) != null){
            return this.tracker.get(entity);
        }
        return -1;
    }

    public void increment(String entity){
        //Updates the number of how many of the building types exist
        if (this.tracker.get(entity) == null){
            this.tracker.put(entity, 0);
        }
        this.tracker.replace(entity, 1);
    }

    public List<Map<ResourceType, Integer>> getHistory(){
        //returns the history
        return this.resourceHistory;
    }
    
    public Map<ResourceType, Integer> getInventory(){
        //returns the whole inventory
        return this.inventory;
    }

    public void reset(){
        this.producers = new ArrayList<>();
        this.converters = new ArrayList<>();
        this.consumers = new ArrayList<>();
        this.tracker = new HashMap<String, Integer>();
        this.inventory = new EnumMap<>(ResourceType.class);
    }
}