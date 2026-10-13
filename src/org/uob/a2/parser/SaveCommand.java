package org.uob.a2.parser;

import java.util.List;
import org.uob.a2.engine.*;


public class SaveCommand extends Command{

    public SaveCommand(List<String> save){
        super(save);
    }

    public String execute(Context ctx){
        try {
            //Checks the file is valid
            //Calls the save command is the filename is valid
            String filename = this.words.get(0);
            if (filename.substring(filename.length() - 4).equals(".csv")){
                return ctx.engine().save(this.words.get(0));
            }
        } catch (Exception e) {}
        return "File type not recognised. Please add .csv to your file name";
    }
}