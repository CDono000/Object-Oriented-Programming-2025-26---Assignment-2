package org.uob.a2.parser;

import java.util.List;
import org.uob.a2.engine.*;


public class QuitCommand extends Command{

    public QuitCommand(){
        super(List.of("quit"));
    }

    public String execute(Context ctx){
        return "QUITTING";
    }
}