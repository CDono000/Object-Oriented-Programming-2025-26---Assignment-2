package org.uob.a2.parser;

import java.util.List;
import org.uob.a2.engine.*;


public class HelpCommand extends Command{

    public HelpCommand(List<String> help){
        super(help);
    }

    public String execute(Context ctx){
        try {
            //Iterates through all the different types of help the player may want
            //Explain different parts of the game
            //If it does not recognise the argument then it gives suggestions
            switch (this.words.get(0)){
                case "buildings":
                    System.out.println("There are 3 types of buildings: Farms, Processors and Shops");
                    System.out.println("Farms produce a resource");
                    System.out.println("Processors convert this resource into an item we can sell");
                    System.out.println("Shops sell our item using the sell command");
                    System.out.println("Here is a list of every different building and how they work");
                    System.out.println("Farms:");
                    System.out.println("    Lumber Yard - " + new InfoCommand(List.of("lumber", "yard")).execute(ctx));
                    System.out.println("    Slime Ranch - " + new InfoCommand(List.of("slime", "ranch")).execute(ctx));
                    System.out.println("    Goblin Hut - " + new InfoCommand(List.of("goblin", "hut")).execute(ctx));
                    System.out.println("    Ork Grotto - " + new InfoCommand(List.of("ork", "grotto")).execute(ctx));
                    System.out.println("    Imp Pit - " + new InfoCommand(List.of("imp", "pit")).execute(ctx));
                    System.out.println("    Dragon Lair - " + new InfoCommand(List.of("dragon", "lair")).execute(ctx));
                    System.out.println("Processors:");
                    System.out.println("    Kitchen - " + new InfoCommand(List.of("kitchen")).execute(ctx));
                    System.out.println("    Tailor - " + new InfoCommand(List.of("tailor")).execute(ctx));
                    System.out.println("    Weapon Forge - " + new InfoCommand(List.of("weapon", "forge")).execute(ctx));
                    System.out.println("    Armour Forge - " + new InfoCommand(List.of("armour", "forge")).execute(ctx));
                    System.out.println("Shops");
                    System.out.println("    Gel Shop - " + new InfoCommand(List.of("gel", "shop")).execute(ctx));
                    System.out.println("    Restaurant - " + new InfoCommand(List.of("restaurant")).execute(ctx));
                    System.out.println("    Clothing Store - " + new InfoCommand(List.of("clothing", "store")).execute(ctx));
                    System.out.println("    Weapon Shop - " + new InfoCommand(List.of("weapon", "shop")).execute(ctx));
                    System.out.println("    Armoury - " + new InfoCommand(List.of("armoury")).execute(ctx));
                    break;
                case "resources": 
                    System.out.println("Wood - " + new InfoCommand(List.of("wood")).execute(ctx));
                    System.out.println("Gel - " + new InfoCommand(List.of("gel")).execute(ctx));
                    System.out.println("Gold - " + new InfoCommand(List.of("gold")).execute(ctx));
                    System.out.println("Goblin Ear - " + new InfoCommand(List.of("goblin", "ear")).execute(ctx));
                    System.out.println("Ork Hide - " + new InfoCommand(List.of("ork", "hide")).execute(ctx));
                    System.out.println("Imp Horn - " + new InfoCommand(List.of("imp", "horn")).execute(ctx));
                    System.out.println("Dragon Scale - " + new InfoCommand(List.of("dragon", "scale")).execute(ctx));
                    System.out.println("Goblin Skewer - " + new InfoCommand(List.of("goblin", "skewer")).execute(ctx));
                    System.out.println("Orken Jacket - " + new InfoCommand(List.of("orken", "jacket")).execute(ctx));
                    System.out.println("Hellfire Dagger - " + new InfoCommand(List.of("hellfire", "dagger")).execute(ctx));
                    System.out.println("Dragon Scale Armour - " + new InfoCommand(List.of("dragon", "scale", "armour")).execute(ctx));
                    break;
                case "commands":
                    System.out.println("build <building> - " + new InfoCommand(List.of("build")).execute(ctx));
                    System.out.println("info <resource/building/command> - " + new InfoCommand(List.of("info")).execute(ctx));
                    System.out.println("graph <resource> - " + new InfoCommand(List.of("resource")).execute(ctx));
                    System.out.println("save <filename> - " + new InfoCommand(List.of("save")).execute(ctx));
                    System.out.println("load <filename> - " + new InfoCommand(List.of("load")).execute(ctx));
                    System.out.println("tick <amount> - " + new InfoCommand(List.of("tick")).execute(ctx));
                    System.out.println("help <resources/buildings/commands> - " + new InfoCommand(List.of("help")).execute(ctx));
                    System.out.println("cheat - " + new InfoCommand(List.of("cheat")).execute(ctx));
                    System.out.println("quit - " + new InfoCommand(List.of("quit")).execute(ctx));
                    System.out.println("sell - " + new InfoCommand(List.of("sell")).execute(ctx));
                    System.out.println("farm - " + new InfoCommand(List.of("farm")).execute(ctx));
                    System.out.println("inventory - " + new InfoCommand(List.of("inventory")).execute(ctx));
                    break;
                default:
                    System.out.println("Could not recognise what kind of help you needed");
                    System.out.println("Try:");
                    System.out.println("    help buildings");
                    System.out.println("    help resources");
                    System.out.println("    help commands");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Could not recognise what kind of help you needed");
            System.out.println("Try:");
            System.out.println("    help buildings");
            System.out.println("    help resources");
            System.out.println("    help commands");
        }
        return "";
    }
}