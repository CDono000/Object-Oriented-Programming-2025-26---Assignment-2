package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import java.util.List;


public class FarmCommand extends Command{

    public FarmCommand(){
        super(List.of("farm"));
    }

    public String execute(Context ctx){
        //Initilises the string
        String farm = "\n";
        //Iterates through the buildings and adds their names and amounts to the string
        List<Producer> producers = ctx.state().getProducers();
        for (int i = 0; i < producers.size(); i++){
            String currentProducer = producers.get(i).getName();
            int amount = ctx.state().getTracker(currentProducer);
            if (amount != -1){farm += currentProducer + ": " + amount + "\n";}
        }
        List<Converter> converters = ctx.state().getConverters();
        for (int i = 0; i < converters.size(); i++){
            String currentConverter = converters.get(i).getName();
            int amount = ctx.state().getTracker(currentConverter);
            if (amount != -1){farm += currentConverter + ": " + amount + "\n";}
        }
        List<Consumer> consumers = ctx.state().getConsumers();
        for (int i = 0; i < consumers.size(); i++){
            String currentConsumer = consumers.get(i).getName();
            int amount = ctx.state().getTracker(currentConsumer);
            if (amount != -1){farm += currentConsumer + ": " + amount + "\n";}
        }
        return farm;
    }
}