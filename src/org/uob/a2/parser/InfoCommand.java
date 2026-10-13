package org.uob.a2.parser;

import java.util.List;
import org.uob.a2.engine.*;


public class InfoCommand extends Command{

    public InfoCommand(List<String> info){
        super(info);
    }

    public String execute(Context ctx){
        try {
            //Checks against all entities, resources and commands and gives a short description
            //If it has a gameplay feature then that is explained as well (e.g. entities and commands)
            switch (this.words.get(0)){
                case "lumber": return "Produces 6 Wood per tick. Costs 100 Gold to build";
                case "slime": return "Produces 5 Gel per tick. Costs 50 Gold and 10 Wood to build";
                case "goblin":
                    if (this.words.get(1) != null){
                        String specifier = this.words.get(1);
                        if (specifier.equals("hut")){return "Produces 4 Goblin Ears per tick. Costs 100 Gold and 20 Wood to build";}
                        else if (specifier.equals("ear")){return "A tasty delicacy when cooked properly";}
                        else if (specifier.equals("skewer")){return "A quick tasty treat. Offered at most establishments";}
                    }
                    break;
                case "ork":
                    if (this.words.get(1) != null){
                        String specifier = this.words.get(1); 
                        if (specifier.equals("grotto")){return "Produces 3 Ork Hide per tick. Costs 150 Gold and 30 Wood to build";}
                        else if (specifier.equals("hide")){return "A thick tough hide. If processed correctly can be used to create warm, durable clothing";}
                    }
                    break;
                case "imp":
                    if (this.words.get(1) != null){
                        String specifier = this.words.get(1);
                        if (specifier.equals("pit")){return "Produces 2 Imp Horns per tick. Costs 200 Gold and 40 Wood to build";}
                        else if (specifier.equals("horn")){return "A hot horn that if sharpened properly can cut through most materials";}
                    }
                    break;
                case "dragon":
                    if (this.words.get(1) != null){
                        String specifier = this.words.get(1);
                        if (specifier.equals("lair")){
                            return "Produces 1 Dragon Scale per tick. Costs 300 Gold and 60 Wood to build";
                        }
                        else if (specifier.equals("scale")){
                            if (this.words.get(2) == null){
                                return "Incredibly tough scales that can defend against most types of damage";
                            }
                            else if (this.words.get(2).equals("armour")){
                                return "The best armour on the market. Can defend against almost any damage and are very comfortable";
                            }
                        }
                    }
                case "kitchen": return "Takes in 8 Gel and 6 Goblin Ears to cook up 1 Goblin Skewer. Costs 150 Gold and 30 Wood to build";
                case "tailor": return "Takes in 15 Gel and 7 Ork Hide to produce 1 Ork Jacket. Costs 200 Gold and 40 Wood to build";
                case "weapon": 
                    if (this.words.get(1) != null){
                        String specifier = this.words.get(1);
                        if (specifier.equals("forge")){return "Takes in 20 Gel and 5 Imp Horns to produce 1 set of Hellfire Daggers. Costs 250 Gold and 50 Wood to build";}
                        else if (specifier.equals("shop")){return "Takes in 1 set of Hellfire Dagger per tick which are sold for 40 Gold each. Costs 250 Gold and 50 Wood to build";}
                    }
                case "armour": return "Takes in 20 Gel and 4 Dragon Scales to produce 1 set of Dragon Scale Armour. Costs 300 Gold and 60 Wood to build";
                case "armoury": return "Takes in 1 Dragon Scale Armour per tick which are sold for 50 Gold each. Costs 300 Gold and 60 Wood to build";
                case "gel": 
                    if (this.words.get(1) != null && this.words.get(1).equals("shop")){
                        return "Takes in 2 Gel per tick which are sold for 1 Gold per Gel. Costs 50 Gold and 15 Wood to build";
                    }
                    else{return "A multipurpose resource. Usind in all kinds of crafting";}
                case "restaurant": return "Takes in 1 Goblin Skewer per tick which are sold for 18 Gold each. Costs 150 Gold and 25 Wood";
                case "clothing": return "Takes in 1 Orken Jacket per tick which are sold for 33 Gold each. Costs 200 Gold and 35 Wood";
                case "wood": return "A core resource in all building projects";
                case "gold": return "A valuable currency, used in transactions for resources or contracts";
                case "orken": return "A tough jacket made from the best materials. It will keep you warm no matter the temperature";
                case "hellfire": return "A pair of glowing red daggers. If your not careful you will be burned but they can cut through almost any material";
                case "build": return "Builds the provided building (Shorthand - b)";
                case "info": return "Provides information about the given resource/building/command (Shorthand - i)";
                case "graph": return "Displays a graph of the amount of a resource you have had over a given time (Shorthand - g)";
                case "save": return "Saves the current game to the specified file (Shorthand - s)";
                case "load": return "Loads the game from the specified file (Shorthand - l)";
                case "tick": return "Advances the simulation by the given amount or 1 if no amount is given (Shorthand - t or if nothing is entered)";
                case "help": return "Provides help on a specified topic (Shorthand - h)";
                case "cheat": return "gives you a lot of every resource (Shorthand - c)";
                case "quit": return "Quits the game (Shorthand - q)";
                case "sell": return "Sells all stock in your shops and gives gold based on the amount sold (Shorthand - £)";
                case "farm": return "Lists all buildings on your farm (Shorthand - f)";
                case "inventory": return "Lists all items in your inventory (Shorthand - inv)";
            }
        } catch (Exception e) {}
        return "Could not recognise item";
    }
}