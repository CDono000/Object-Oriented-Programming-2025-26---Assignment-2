package org.uob.a2.parser;

import java.util.List;
import org.uob.a2.engine.*;


public class TickCommand extends Command{

    public TickCommand(List<String> tick){
        super(tick);
    }

    public String execute(Context ctx){
        //If nothing is passed, then tick 1 time
        if (words.get(0) == null){
            ctx.engine().nextTick();
            return "1 day has passed";
        }
        else{
            String currentDay = "";
            //If not tick the specified amount of times
            int goal = Integer.parseInt(words.get(0));
            for(int i = 0; i < goal; i++){
                currentDay = ctx.engine().nextTick();
            }
            if (goal == 1){return "1 day has passed";}
            else{return goal + " days have passed\nCurrent Day: " + currentDay;}
        }
    }
}