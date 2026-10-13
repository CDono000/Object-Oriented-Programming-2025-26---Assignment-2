package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import java.util.List;
import org.uob.a2.model.ResourceType;


public class SellCommand extends Command{

    public SellCommand(){
        super(List.of("selling"));
    }

    public String execute(Context ctx){
        //Stores the initial amount of gold
        int before = ctx.state().getResourceAmount(ResourceType.CREDITS);
        //Iterates through all consumers and sells their stock
        List<Consumer> consumers = ctx.state().getConsumers();
        for (int i = 0; i < consumers.size(); i++){consumers.get(i).sell(ctx);}
        //Returns an appropriate message
        int after = ctx.state().getResourceAmount(ResourceType.CREDITS);
        return "All Inventory has been exported to their respective establishments\nGold: " + before + " --> " + after + " (+" + (after - before) + ")";
    }
}