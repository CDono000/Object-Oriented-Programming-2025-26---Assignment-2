package org.uob.a2.engine;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.uob.a2.model.*;
import org.uob.a2.parser.BuildCommand;

public class Engine {

    private SimulationState currentSim;
    private int currentTick;

    public Engine(SimulationState sim){
        //Initilise required variables
        this.currentSim = sim;
        this.currentTick = 0;
        this.currentSim.addResource(ResourceType.CREDITS, 1000);
    }
    
    public int getCurrentTick(){
        return this.currentTick;
    }

    public String nextTick(){
        //Increment the tick
        this.currentTick++;

        //Get all the entities
        List<Producer> producers = this.currentSim.getProducers();
        List<Converter> converters = this.currentSim.getConverters();
        List<Consumer> consumers = this.currentSim.getConsumers();
        Context ctx = new Context(this, this.currentSim);
        //Ticks all the entities
        if (!producers.isEmpty()){
            for(int i = 0; i < producers.size(); i++){
                producers.get(i).tick(ctx);
            }
        }
        if (!converters.isEmpty()){
            for(int i = 0; i < converters.size(); i++){
                converters.get(i).tick(ctx);
            }
        }
        if (!consumers.isEmpty()){
            for(int i = 0; i < consumers.size(); i++){
                consumers.get(i).tick(ctx);
            }
        }
        //Updates the history
        this.currentSim.updateHistory();
        //Gives feedback to the player
        return "" + this.getCurrentTick();
    }

    public String save(String filename){
        //Wipes the file if it uses the same name 
        Path file = Paths.get("data/"+filename);
        try {Files.deleteIfExists(file);} 
        catch (Exception e) {}
        //Creates a list which houses all the information we need to store
        List<String> toAdd = new ArrayList<>();
        //Adds the current tick
        toAdd.add("CurrentTick," + this.getCurrentTick());
        toAdd.add("Resource, Amount");
        int amount;

        //Stores all possible resources and their names as strings
        Map<ResourceType, Integer> inventory = this.currentSim.getInventory();
        List<ResourceType> resources = List.of(ResourceType.ORE, ResourceType.METAL, ResourceType.CREDITS, ResourceType.WOOD, ResourceType.GEL, ResourceType.GOBLINEAR, ResourceType.ORKHIDE, ResourceType.IMPHORN, ResourceType.DRAGONSCALE, ResourceType.GOBLINSKEWER, ResourceType.ORKENJACKET, ResourceType.HELLFIREDAGGER, ResourceType.DRAGONSCALEARMOUR);
        List<String> resourceString = List.of("ORE", "METAL", "CREDITS", "WOOD", "GEL", "GOBLINEAR", "ORKHIDE", "IMPHORN", "DRAGONSCALE", "GOBLINSKEWER", "ORKENJACKET", "HELLFIREDAGGER", "DRAGONSCALEARMOUR");
        for (int i = 0; i < resources.size(); i++){
            amount = this.currentSim.getResourceAmount(resources.get(i));
            //If the resource is being stored then we add it to the list
            if (amount > 0){toAdd.add(resourceString.get(i) + "," + amount);}
        }
        //Adds all producers and converters
        toAdd.add("Entity, Amount");
        List<String> entityTypes = List.of("lumber yard", "slime ranch", "goblin hut", "ork grotto", "imp pit", "dragon lair", "kitchen", "tailor", "weapon forge", "armour forge");
        for (int i = 0; i < entityTypes.size(); i++){
            amount = this.currentSim.getTracker(entityTypes.get(i));
            if (amount != -1){toAdd.add(entityTypes.get(i) + "," + amount);}
        }
        //Adds all the consumers and their stock
        toAdd.add("Consumer,Amount");
        List<Consumer> consumers = this.currentSim.getConsumers();
        for (int i = 0; i < consumers.size(); i++){toAdd.add(consumers.get(i).toCSV());}
        try{
            //Writes the list to the file
            Files.createFile(file);
            OutputStream writer = new BufferedOutputStream(Files.newOutputStream(file));
            for (int i = 0; i < toAdd.size(); i++){
                writer.write((toAdd.get(i) + "\n").getBytes());
            }
            writer.flush();
            writer.close();
        }
        catch(Exception exception){
            return "FILE CREATION ERROR";
        }
        return "Saved to " + filename;
    }

    public String load(String filename){
        Path file = Paths.get("data/"+filename);
        try{
            //Gets the contents of the file
            InputStream input = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            //Reset the simulation
            this.currentSim.reset();
            
            //Gets the current tick
            String string = reader.readLine();
            this.currentTick = Integer.parseInt(string.split(",")[1]);
            reader.readLine();
            string = reader.readLine();
            List<ResourceType> resources = List.of(ResourceType.ORE, ResourceType.METAL, ResourceType.CREDITS, ResourceType.WOOD, ResourceType.GEL, ResourceType.GOBLINEAR, ResourceType.ORKHIDE, ResourceType.IMPHORN, ResourceType.DRAGONSCALE, ResourceType.GOBLINSKEWER, ResourceType.ORKENJACKET, ResourceType.HELLFIREDAGGER, ResourceType.DRAGONSCALEARMOUR);
            for (int i=0; i<resources.size(); i++){this.currentSim.updateResource(resources.get(i), 0);}
            //Updates all the resources
            while (string != null && !(string.equals("Entity, Amount"))){
                String[] split = string.split(",");
                int amount = Integer.parseInt(split[1]);
                switch (split[0]){
                    case "CREDITS": this.currentSim.updateResource(ResourceType.CREDITS, amount); break;
                    case "METAL": this.currentSim.updateResource(ResourceType.METAL, amount); break;
                    case "ORE": this.currentSim.updateResource(ResourceType.ORE, amount); break;
                    case "WOOD": this.currentSim.updateResource(ResourceType.WOOD, amount); break;
                    case "GEL": this.currentSim.updateResource(ResourceType.GEL, amount); break;
                    case "GOBLINEAR": this.currentSim.updateResource(ResourceType.GOBLINEAR, amount); break;
                    case "ORKHIDE": this.currentSim.updateResource(ResourceType.ORKHIDE, amount); break;
                    case "IMPHORN": this.currentSim.updateResource(ResourceType.IMPHORN, amount); break;
                    case "DRAGONSCALE": this.currentSim.updateResource(ResourceType.DRAGONSCALE, amount); break;
                    case "GOBLINSKEWER": this.currentSim.updateResource(ResourceType.GOBLINSKEWER, amount); break;
                    case "ORKENJACKET": this.currentSim.updateResource(ResourceType.ORKENJACKET, amount); break;
                    case "HELLFIREDAGGER": this.currentSim.updateResource(ResourceType.HELLFIREDAGGER, amount); break;
                    case "DRAGONSCALEARMOUR": this.currentSim.updateResource(ResourceType.DRAGONSCALEARMOUR, amount); break;
                }
                string = reader.readLine();
            }
            //Adds all the Producers and Converters
            string = reader.readLine();
            while (string != null && !(string.equals("Consumer,Amount"))){
                String[] split = string.split(",");
                int amount = Integer.parseInt(split[1]);
                List<String> buildingType = List.of(split[0].split(" "));
                Context ctx = new Context(this, this.currentSim);
                for (int i = 0; i < amount; i++){new BuildCommand(buildingType).execute(ctx);}
                string = reader.readLine();
            }
            string = reader.readLine();
            while (string != null){
                //Initilises data types
                String[] split = string.split(",");
                int amount = Integer.parseInt(split[1]);
                List<String> consumerType = List.of(split[0].split(" "));
                Context ctx = new Context(this, this.currentSim);
                int stock = Integer.parseInt(split[1]);
                //Builds the consumer and sets the stock
                new BuildCommand(consumerType).execute(ctx);
                List<Consumer> consumers = this.currentSim.getConsumers();
                consumers.get(consumers.size() -1).setStock(stock);
                string = reader.readLine();
            }
            reader.close();
        }
        catch(Exception exception){
            System.out.println(exception);
            return "FILE DOES NOT EXIST";
        }
        return "Loaded " + filename;
    }

    // USED FOR TESTING
    public void initialiseDefaults() {
        //ADD ALL THE ENTITIES YOU CREATE TO THE PRODUCER, CONVERTER AND CONSUMER LISTS IN SIMULATIONSTATE HERE.
        this.currentSim.addProducer(new LumberYard());
        this.currentSim.addProducer(new SlimeRanch());
        this.currentSim.addProducer(new GoblinHut());
        this.currentSim.addProducer(new OrkGrotto());
        this.currentSim.addProducer(new ImpPit());
        this.currentSim.addProducer(new DragonLair());
        this.currentSim.addConverter(new Kitchen());
        this.currentSim.addConverter(new Tailor());
        this.currentSim.addConverter(new WeaponForge());
        this.currentSim.addConverter(new ArmourForge());
        this.currentSim.addConsumer(new GelShop());
        this.currentSim.addConsumer(new Restaurant());
        this.currentSim.addConsumer(new ClothingStore());
        this.currentSim.addConsumer(new WeaponShop());
        this.currentSim.addConsumer(new Armoury());

        //GEL is a core resource for all converters and is needed for the converter tests
        this.currentSim.addResource(ResourceType.GEL, 1000);
    }
}