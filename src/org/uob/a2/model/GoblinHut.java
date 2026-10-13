package org.uob.a2.model;

import org.uob.a2.model.ResourceType;
import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class GoblinHut extends Producer{

    public GoblinHut(){
        super("goblin hut", ResourceType.GOBLINEAR, 4);
    }

    public void produce(Context ctx){
        ctx.state().addResource(this.product, this.amount);
    
    }

    public void tick(Context ctx){
        this.produce(ctx);
    }

    public String toCSV(){
        return this.name+";GOBLINEARS;4";
    }
}