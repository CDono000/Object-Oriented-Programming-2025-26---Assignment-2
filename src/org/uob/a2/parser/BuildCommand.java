package org.uob.a2.parser;

import java.util.List;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;



public class BuildCommand extends Command{

    public BuildCommand(List<String> command){
        super(command);
    }

    public String execute(Context ctx){
        //Initilises required variables
        //Gets the amount of building resources the player has
        int credits = ctx.state().getResourceAmount(ResourceType.CREDITS);
        int wood = ctx.state().getResourceAmount(ResourceType.WOOD);
        int requiredCredits = 0;
        int requiredWood = 0;
        boolean insufficient = false;
        String output = "Building Not Recognised";
        //Goes through possible buildings
        //Checks if they have enough resources
        //Sets the required resources to this amount to take away later
        try {
            switch (this.words.get(0)){
                case "lumber":
                    if (credits >= 100){
                        requiredCredits = 100;
                        ctx.state().addProducer(new LumberYard());
                        ctx.state().increment("lumber yard");
                        output = "Created a new Lumber Yard";
                    }
                    else{insufficient = true;}
                    break;
                case "slime":
                    if (credits >= 50 && wood >= 10){
                        requiredCredits = 50;
                        requiredWood = 10;
                        ctx.state().addProducer(new SlimeRanch());
                        ctx.state().increment("slime ranch");
                        output = "Created a new Slime Ranch";
                    }
                    else{insufficient = true;}
                    break;
                case "goblin":
                    if (credits >= 100 && wood >= 20){
                        requiredCredits = 100;
                        requiredWood = 20;
                        ctx.state().addProducer(new GoblinHut());
                        ctx.state().increment("goblin hut");
                        output = "Created a new Goblin Hut";
                    }
                    else{insufficient = true;}
                    break;
                case "ork":
                    if (credits >= 150 && wood >= 30){
                        requiredCredits = 150;
                        requiredWood = 30;
                        ctx.state().addProducer(new OrkGrotto());
                        ctx.state().increment("ork grotto");
                        output = "Created a new Ork Grotto";
                    }
                    else{insufficient = true;}
                    break;
                case "imp":
                    if (credits >= 200 && wood >= 40){
                        requiredCredits = 200;
                        requiredWood = 40;
                        ctx.state().addProducer(new ImpPit());
                        ctx.state().increment("imp pit");
                        output = "Created a new Imp Pit";
                    }
                    else{insufficient = true;}
                    break;
                case "dragon":
                    if (credits >= 300 && wood >= 60){
                        requiredCredits = 300;
                        requiredWood = 60;
                        ctx.state().addProducer(new DragonLair());
                        ctx.state().increment("dragon lair");
                        output = "Created a new Lumber Yard";
                    }
                    else{insufficient = true;}
                    break;
                case "kitchen":
                    if (credits >= 150 && wood >= 30){
                        requiredCredits = 150;
                        requiredWood = 30;
                        ctx.state().addConverter(new Kitchen());
                        ctx.state().increment("kitchen");
                        output = "Created a new Kitchen";
                    }
                    else{insufficient = true;}
                    break;
                case "tailor":
                    if (credits >= 200 && wood >= 40){
                        requiredCredits = 200;
                        requiredWood = 40;
                        ctx.state().addConverter(new Tailor());
                        ctx.state().increment("tailor");
                        output = "Created a new Tailor";
                    }
                    else{insufficient = true;}
                    break;
                case "armour":
                    if (credits >= 300 && wood >= 60){
                        requiredCredits = 300;
                        requiredWood = 60;
                        ctx.state().addConverter(new ArmourForge());
                        ctx.state().increment("armour forge");
                        output = "Created a new Armour Forge";
                    }
                    else{insufficient = true;}
                    break;
                case "gel":
                    if (credits >= 50 && wood >= 15){
                        requiredCredits = 50;
                        requiredWood = 15;
                        ctx.state().addConsumer(new GelShop());
                        ctx.state().increment("gel shop");
                        output = "Created a new Gel Shop";
                    }
                    else{insufficient = true;}
                    break;
                case "restaurant":
                    if (credits >= 150 && wood >= 25){
                        requiredCredits = 150;
                        requiredWood = 25;
                        ctx.state().addConsumer(new Restaurant());
                        ctx.state().increment("restaurant");
                        output = "Created a new Restaurant";
                    }
                    else{insufficient = true;}
                    break;
                case "clothing":
                    if (credits >= 200 && wood >= 35){
                        requiredCredits = 200;
                        requiredWood = 35;
                        ctx.state().increment("clothing store");
                        ctx.state().addConsumer(new ClothingStore());
                        output = "Created a new Restaurant";
                    }
                    else{insufficient = true;}
                    break;
                case "armoury":
                    if (credits >= 300 && wood >= 60){
                        requiredCredits = 300;
                        requiredWood = 60;
                        ctx.state().addConsumer(new Armoury());
                        ctx.state().increment("armoury");
                        output = "Created a new Armoury";
                    }
                    else{insufficient = true;}
                    break;
                case "weapon":
                    if (this.words.get(1).equals("forge") && credits >= 250 && wood >= 50){
                        requiredCredits = 250;
                        requiredWood = 50;
                        ctx.state().addConverter(new WeaponForge());
                        ctx.state().increment("weapon forge");
                        output = "Created a new Weapon Forge";
                    }
                    else if (this.words.get(1).equals("shop") && credits >= 250 && wood >= 50){
                        requiredCredits = 250;
                        requiredWood = 50;
                        ctx.state().addConsumer(new WeaponShop());
                        ctx.state().increment("weapon shop");
                        output = "Created a new Weapon Shop";
                    }
                    else{insufficient = true;}
                    break;
            }
            //If they dont have enough resources, return that information
            if (insufficient){output = "Insufficient materials";}
            //removes the costed resources
            ctx.state().removeResource(ResourceType.CREDITS, requiredCredits);
            ctx.state().removeResource(ResourceType.WOOD, requiredWood);
        } catch (Exception e) {}
        return output;
    }
}