package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import java.util.List;


public class InventoryCommand extends Command{

    public InventoryCommand(){
        super(List.of("inventory"));
    }

    public String execute(Context ctx){
        //Iterates through the inventory and adds to the string if any of that resource is currently being stored
        String inventory = "\n";
        ResourceType[] resources = {ResourceType.ORE, ResourceType.METAL, ResourceType.CREDITS, ResourceType.WOOD, ResourceType.GEL, ResourceType.GOBLINEAR, ResourceType.ORKHIDE, ResourceType.IMPHORN, ResourceType.DRAGONSCALE, ResourceType.GOBLINSKEWER, ResourceType.ORKENJACKET, ResourceType.HELLFIREDAGGER, ResourceType.DRAGONSCALEARMOUR};
        String[] resourceNames = {"Ore", "Metal", "Gold", "Wood", "Gel", "Goblin Ear", "Ork Hide", "Imp Horn", "Dragon Scale", "Goblin Skewer", "Orken Jacket", "Hellfire Dagger", "Dragon Scale Armour"};
        for (int i = 0; i < resources.length; i++){
            int amount = ctx.state().getResourceAmount(resources[i]);
            if (amount != 0){inventory += resourceNames[i] + ": " + amount + "\n";}
        }
        return inventory;
    }
}