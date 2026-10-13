package org.uob.a2.parser;

import java.util.List;
import org.uob.a2.engine.*;


public class LoadCommand extends Command{

    public LoadCommand(List<String> load){
        super(load);
    }

    public String execute(Context ctx){
        try {
            //Checks if the filename is in csv
            //Saves it to the filename if valid
            String filename = this.words.get(0);
            if (filename.substring(filename.length() - 4).equals(".csv")){
                return ctx.engine().load(this.words.get(0));
            }
        } catch (Exception e) {}
        return "File type not recognised. Please add .csv to your file name";
    }
}