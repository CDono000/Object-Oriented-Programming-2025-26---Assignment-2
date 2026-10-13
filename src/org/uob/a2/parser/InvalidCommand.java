package org.uob.a2.parser;

import java.util.List;
import org.uob.a2.engine.*;


public class InvalidCommand extends Command{

    public InvalidCommand(List<String> words){
        super(words);
    }

    public String execute(Context ctx){
        //Explains that the command was not recognised and outputs the command again
        //for the player to see
        String command = "";
        for (int i = 0; i < this.words.size(); i++){
            command += this.words.get(i);
        }
        return "Could not recognise '" + command + "', did you spell it right?";
    }
}