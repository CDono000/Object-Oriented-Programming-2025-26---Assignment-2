package org.uob.a2.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public Command parse(String command){
        //If the input is empty then it is simply a new tick
        if (command == ""){return new TickCommand(List.of("1"));}
        //Splits the entered command into seperate words in the array
        String[] split = command.split(" ");
        //Stores all valid command inputs
        String[] valid = {"build", "b", "info", "i", "graph", "g", "save", "s", 
                          "load", "l", "tick", "t", "help", "h", "cheat", "c", 
                          "quit", "q", "sell", "£", "inventory", "inv", "farm", "f"};
        //Makes sure we are analysing the first word
        int start = 0;
        while (split[start] == ""){start++;}
        //Converts all commands to their shorthand
        char type = 'a';
        if (Arrays.asList(valid).contains(split[start].toLowerCase())){
            type = split[start].toLowerCase().charAt(0);
        }
        if (split[start].equals("sell")){type = '£';}
        else if (split[start].equals("inventory") || split[start].equals("inv")){type = 'e';}
        //Adds all arguments after the initial command to a list to be passed
        List<String> argument = new ArrayList<>();
        for(int i=start+1;i<split.length;i++){argument.add(split[i].toLowerCase());}
        //Selects the correct type of command and returns it
        switch (type) {
            case 'b': return new BuildCommand(argument);
            case 'i': return new InfoCommand(argument);
            case 'g': return new GraphCommand(argument);
            case 's': return new SaveCommand(argument);
            case 'l': return new LoadCommand(argument);
            case 't': return new TickCommand(argument);
            case 'h': return new HelpCommand(argument);
            case 'c': return new CheatCommand();
            case 'q': return new QuitCommand();
            case '£': return new SellCommand();
            case 'e': return new InventoryCommand();
            case 'f': return new FarmCommand();
            default: return new InvalidCommand(argument);
        }
    }
}